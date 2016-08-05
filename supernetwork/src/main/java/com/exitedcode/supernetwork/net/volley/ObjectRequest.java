package com.exitedcode.supernetwork.net.volley;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.exitedcode.supernetwork.Env;
import com.exitedcode.supernetwork.net.ErrorListener;
import com.exitedcode.supernetwork.net.bean.IApiBase;
import com.exitedcode.supernetwork.net.util.ParseUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ymr on 15/6/25.
 */
public class ObjectRequest<D> extends StringRequest {

    private static final String TAG = "ObjectRequest";
    private Map<String, String> mHeader = new HashMap<String, String>();
    public ObjectRequest(int method, final String url, final Response.Listener<IApiBase<D>> listener, final Response.ErrorListener errorListener, final Class<D> tClass) {
        super(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                ParseUtil.generateObject(s, url, tClass, listener, new ErrorListener() {
                    @Override
                    public void onErrorResponse(Exception e) {
                        errorListener.onErrorResponse(new VolleyError(e));
                    }
                });
            }
        }, errorListener);
    }

    public ObjectRequest(final String url, final Response.Listener<IApiBase<D>> listener, final Response.ErrorListener errorListener, final Class<D> tClass) {
        super(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                ParseUtil.generateObject(s, url, tClass, listener, new ErrorListener() {
                    @Override
                    public void onErrorResponse(Exception e) {
                        errorListener.onErrorResponse(new VolleyError(e));
                    }
                });
            }
        }, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (Env.sHeaders != null && !Env.sHeaders.isEmpty()) {
            mHeader.putAll(Env.sHeaders);
        }
        return mHeader;
    }

    public void setCookies(String cookies) {
        if (!TextUtils.isEmpty(cookies)) {
            String oldCookie = mHeader.get("Cookie");
            if(!TextUtils.isEmpty(oldCookie)){
                mHeader.put("Cookie",oldCookie+cookies);
            }else {
                mHeader.put("Cookie", cookies);
            }
        }
    }

    public void setHeaders(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            mHeader.putAll(map);
        }
    }
}
