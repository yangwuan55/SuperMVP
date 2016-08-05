package com.exitedcode.supernetwork.net;

import com.android.volley.VolleyError;
import com.exitedcode.supernetwork.net.bean.IApiBase;
import com.exitedcode.supernetwork.net.params.NetRequestParams;

import java.util.Map;

/**
 * Created by ymr on 16/5/6.
 */
public interface INetWork {

    interface RequestListner<T> {
        void onSuccess(T t);
        void onFail(VolleyError error);
    }

    <T,P extends NetRequestParams> void addRequest(final P params, final RequestListner<IApiBase<T>> requestListner, Class<T> tClass, Map<String,String> headers, String cookies);
}
