package com.ymr.supernetwork.net.model;

import android.content.Context;
import android.os.Handler;

import com.ymr.supernetwork.net.util.DeviceInfoUtils;
import com.ymr.supernetwork.net.util.NetResultDisposer;
import com.ymr.supernetwork.net.params.NetRequestParams;

/**
 * Created by ymr on 15/6/12.
 */
public class SimpleNetWorkModel<T> extends SimpleModel implements NetWorkModel<T> {

    private final Context mContext;
    private final Class<T> mTClass;
    private boolean isCancel;
    private Handler mHandler = new Handler();

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
        isCancel = false;
        if (DeviceInfoUtils.hasInternet(mContext)) {
            if (listener == null) {
                throw new RuntimeException("回调不可为空");
            }
            dispose(params, listener, forceFromServer);
        } else {
            final Error error = new Error();
            error.setErrorCode(10000);
            error.setMsg("无网络");
            error.setNetRequestParams(params);
            if (!isCancel) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onError(error);
                    }
                });
            }
        }
    }

    private void dispose(NetRequestParams params, final UpdateListener<T> listener, boolean forceFromServer) {
        NetResultDisposer.dispose(mContext, params, new UpdateListener<T>() {
            @Override
            public void finishUpdate(final T result) {
                if (!isCancel) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.finishUpdate(result);
                        }
                    });

                }
            }

            @Override
            public void onError(final Error error) {
                if (!isCancel) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onError(error);
                        }
                    });
                }
            }
        }, mTClass,params.getHeaders(),params.getCookies(),forceFromServer);
    }

    public Context getContext() {
        return mContext;
    }

    public void cancel() {
        isCancel = true;
    }
}