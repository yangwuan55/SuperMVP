package com.exitedcode.supernetwork.net.model;

import android.content.Context;

import com.exitedcode.supernetwork.net.params.NetRequestParams;


/**
 * Created by ymr on 15/7/9.
 */
public class SimpleResultNetWorkModel extends SimpleNetWorkModel<Object> {

    public interface SimpleRequestListener {
        void onSuccess();
        void onFail(Error error);
    }

    public SimpleResultNetWorkModel(Context context) {
        super(context,null);
    }

    public void sendRequest(NetRequestParams params, final SimpleRequestListener listener) {
        updateDatas(params, new UpdateListener<Object>() {
            @Override
            public void finishUpdate(Object result) {
                listener.onSuccess();
            }

            @Override
            public void onError(Error error) {
                listener.onFail(error);
            }
        });
    }
}
