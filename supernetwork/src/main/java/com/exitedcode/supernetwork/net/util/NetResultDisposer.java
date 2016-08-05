package com.exitedcode.supernetwork.net.util;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.exitedcode.supernetwork.net.INetWork;
import com.exitedcode.supernetwork.net.bean.IApiBase;
import com.exitedcode.supernetwork.net.model.NetWorkModel;
import com.exitedcode.supernetwork.net.params.NetRequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ymr on 15/8/21.
 */
public class NetResultDisposer {
    private static final String TAG = "NetResultDisposer";
    /**
     * 缓存数据时间
     */
    public static final int SAVED_TIME = 2000;
    private static Handler sHandler = new Handler();
    private static HashMap<NetRequestParams,List<NetWorkModel.UpdateListener>> sParamListeners = new HashMap<>();
    private static HashMap<NetRequestParams,IApiBase> sSavedDatas = new HashMap<>();
    private static HashMap<NetRequestParams,VolleyError> sSavedVolleyError = new HashMap<>();
    private static final Object sync = new Object();

    /**
     *
     * @param context
     * @param params
     * @param listener
     * @param tClass
     * @param headers
     * @param cookies
     * @param forceFromServer 是否强制从服务端取数据
     * @param <T>
     */
    public static <T> void dispose(Context context, final NetRequestParams params, final NetWorkModel.UpdateListener<T> listener, Class<T> tClass, Map<String, String> headers, String cookies, boolean forceFromServer) {
        synchronized (sync) {
            if (!forceFromServer) {
                //如果不是强制从服务器取数据则符合缓存规则，进入缓存流程
                if (sSavedDatas.containsKey(params)) {
                    //如果缓存了此数据，从缓存中取得数据
                    finishUpdate(sSavedDatas.get(params),params,listener);
                    LOGGER.i(TAG,"return saved data:"+params);
                } else if (sSavedVolleyError.containsKey(params)) {
                    //如果缓存了此错误信息，从缓存中取得错误
                    failUpdate(sSavedVolleyError.get(params),params,listener);
                    LOGGER.i(TAG,"return saved error:"+params);
                } else if (sParamListeners.containsKey(params)) {
                    //如果请求列表中已经存在此请求，将回调对象加入回调对象列表
                    List<NetWorkModel.UpdateListener> updateListeners = sParamListeners.get(params);
                    updateListeners.add(listener);
                    sParamListeners.put(params, updateListeners);
                    LOGGER.i(TAG,"add listener:"+params);
                } else {
                    //真正从服务器取数据
                    updateFromServer(context, params, listener, tClass, headers, cookies);
                }
            } else {
                //直接从服务器取数据
                updateFromServer(context, params, listener, tClass, headers, cookies);
            }
        }
    }

    private static <T> void updateFromServer(Context context, final NetRequestParams params, NetWorkModel.UpdateListener<T> listener, Class<T> tClass, Map<String, String> headers, String cookies) {
        ArrayList<NetWorkModel.UpdateListener> updateListeners = new ArrayList<>();
        updateListeners.add(listener);
        sParamListeners.put(params, updateListeners);
        NetWorkFactory.getNetWorkUtil(context).addRequest(params, new INetWork.RequestListner<IApiBase<T>>() {
            @Override
            public void onSuccess(final IApiBase<T> data) {
                synchronized (sync) {
                    sSavedDatas.put(params,data);
                    finishUpdate(data, params);
                    sHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            removeMsg(params);
                            LOGGER.i(TAG,"success remove params:"+params);
                        }
                    }, SAVED_TIME);
                }
            }

            @Override
            public void onFail(VolleyError error) {
                synchronized (sync) {
                    sSavedVolleyError.put(params,error);
                    failUpdate(error, params);
                    sHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            removeMsg(params);
                            LOGGER.i(TAG,"fail    remove params:"+params);
                        }
                    }, SAVED_TIME);
                }
            }
        }, tClass,headers,cookies);
    }

    private static void removeMsg(NetRequestParams params) {
        sSavedDatas.remove(params);
        sSavedVolleyError.remove(params);
        sParamListeners.remove(params);
    }

    private static void failUpdate(VolleyError error, NetRequestParams params) {
        NetWorkModel.Error netError = getError(error, params);
        List<NetWorkModel.UpdateListener> updateListeners = sParamListeners.get(params);
        if (updateListeners != null) {
            for (NetWorkModel.UpdateListener listener : updateListeners) {
                listener.onError(netError);
            }
        } else {
            //CrashReport.postCatchedException(new Throwable("params = " + params + " error = " + error.toString()));
        }
    }

    @NonNull
    private static NetWorkModel.Error getError(VolleyError error, NetRequestParams params) {
        Throwable cause = error.getCause();
        NetWorkModel.Error netError = new NetWorkModel.Error();
        if (error instanceof ServerError) {
            netError.setMsg("接口错误：" + error.networkResponse.statusCode);
        } else if (error instanceof TimeoutError){
            netError.setMsg("请求超时");
        } else if (error instanceof NoConnectionError){
            netError.setMsg("无效链接");
        } else {
            netError.setMsg("服务器错误");
            String tag = "未知错误";
            if (error.networkResponse != null) {
                tag = "code:"+error.networkResponse.statusCode +"TimeMs:"+ error.getNetworkTimeMs();
            }
            if (cause != null) {
                cause.printStackTrace();
                netError.setTag(tag + error.getCause().toString());
            } else {
                netError.setTag(tag);
            }
        }
        netError.setNetRequestParams(params);
        if (error.networkResponse != null) {
            netError.setErrorCode(error.networkResponse.statusCode);
            netError.setMsg(new String(error.networkResponse.data));
        }
        return netError;
    }

    private static void failUpdate(VolleyError error, NetRequestParams params, NetWorkModel.UpdateListener listener) {
        NetWorkModel.Error netError = getError(error, params);
        listener.onError(netError);
    }

    private static <T> void finishUpdate(IApiBase<T> data, NetRequestParams params) {
        NetWorkModel.Error error = new NetWorkModel.Error();
        boolean isError = disposeResult(data, params, error);

        List<NetWorkModel.UpdateListener> updateListeners = sParamListeners.get(params);
        if (updateListeners != null) {
            if (isError) {
                for (NetWorkModel.UpdateListener listener : updateListeners) {
                    listener.onError(error);
                }
            } else {
                for (NetWorkModel.UpdateListener listener : updateListeners) {
                    listener.finishUpdate(data.getData());
                }
            }
        } else {
            //CrashReport.postCatchedException(new Throwable("params = " + params));
        }
    }

    private static <T> boolean disposeResult(IApiBase<T> data, NetRequestParams params, NetWorkModel.Error error) {
        boolean isError;
        error.setNetRequestParams(params);
        if (data != null) {
            if (data.getCode() == data.getSuccessCode()) {
                isError = false;
            } else {
                isError = true;
                error.setErrorCode(data.getCode());
                if (data.getData() != null && data.getData() instanceof Map) {
                    error.setMsg(data.getMsg());
                } else {
                    error.setMsg(data.getMsg());
                }
            }
        } else {
            isError = true;
            error.setMsg("server error 1");
        }
        return isError;
    }

    private static <T> void finishUpdate(IApiBase<T> data, NetRequestParams params, NetWorkModel.UpdateListener<T> listener) {
        NetWorkModel.Error error = new NetWorkModel.Error();
        boolean isError = disposeResult(data, params, error);

        if (isError) {
            listener.onError(error);
        } else {
            listener.finishUpdate(data.getData());
        }
    }
}
