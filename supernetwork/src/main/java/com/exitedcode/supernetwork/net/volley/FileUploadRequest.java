package com.exitedcode.supernetwork.net.volley;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.exitedcode.supernetwork.Env;
import com.exitedcode.supernetwork.net.ErrorListener;
import com.exitedcode.supernetwork.net.bean.IApiBase;
import com.exitedcode.supernetwork.net.util.ParseUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ymr on 15/8/21.
 */
public class FileUploadRequest<D> extends MultiPartStringRequest {
    private static final String TAG = "FileUploadRequest";
    private Map<String, String> mHeader = new HashMap<String, String>();

    /**
     * Creates a new request with the given method.
     *
     * @param url           URL to fetch the string at
     * @param listener      Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public FileUploadRequest(final String url, final Response.Listener<IApiBase<D>> listener, final Response.ErrorListener errorListener, final Class<D> tClass) {
        super(Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ParseUtil.generateObject(response, url, tClass, listener, new ErrorListener() {
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
            mHeader.put("Cookie", cookies);
        }
    }

    public void setHeaders(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            mHeader.putAll(map);
        }
    }
}
