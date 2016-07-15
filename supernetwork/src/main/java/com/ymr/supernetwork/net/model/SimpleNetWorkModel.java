package com.ymr.supernetwork.net.model;

import android.content.Context;

import com.ymr.supernetwork.net.util.DeviceInfoUtils;
import com.ymr.supernetwork.net.util.NetResultDisposer;
import com.ymr.supernetwork.net.params.NetRequestParams;

/**
 * Created by ymr on 15/6/12.
 */
public class SimpleNetWorkModel<T> extends SimpleModel implements NetWorkModel<T> {

    private final Context mContext;
    private final Class<T> mTClass;

    public SimpleNetWorkModel(Context context, Class<T> tClass) {
        mContext = context;
        mTClass = tClass;
    }

    @Override
    public void updateDatas(NetRequestParams params, final UpdateListener<T> listener) {
        updateDatas(params, listener,false);
    }

    @Override
    public void updateDatas(NetRequestParams params, final UpdateListener<T> listener, boolean forceFromServer) {
        if (DeviceInfoUtils.hasInternet(mContext)) {
            if (listener == null) {
                throw new RuntimeException("回调不可为空");
            }
            NetResultDisposer.dispose(mContext, params, new UpdateListener<T>() {
                @Override
                public void finishUpdate(T result) {
                    listener.finishUpdate(result);
                }

                @Override
                public void onError(Error error) {
                    listener.onError(error);
                }
            }, mTClass,params.getHeaders(),params.getCookies(),forceFromServer);
        } else {
            Error error = new Error();
            error.setErrorCode(10000);
            error.setMsg("无网络");
            error.setNetRequestParams(params);
            listener.onError(error);
        }
    }

    public Context getContext() {
        return mContext;
    }
}