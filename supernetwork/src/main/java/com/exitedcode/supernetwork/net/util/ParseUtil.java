package com.exitedcode.supernetwork.net.util;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.exitedcode.supernetwork.Env;
import com.exitedcode.supernetwork.net.ErrorListener;
import com.exitedcode.supernetwork.net.bean.IApiBase;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ymr on 15/8/21.
 */
public class ParseUtil {
    private static final String TAG = "ParseUtil";

    public static <T> void generateObject(String response, String url, Class<T> tClass, Response.Listener<IApiBase<T>> listener, ErrorListener errorListener) {
        LOGGER.i(TAG, "URL = " + url);
        ParameterizedType type = null;
        if (tClass != null) {
            type = type(Env.sApiBase, tClass);
            LOGGER.i(TAG, "type = " + type);
        }
        IApiBase<T> rtn = null;
        Gson gson = new GsonBuilder().create();
        if (type != null) {
            try {
                rtn = gson.fromJson(response, type);
            } catch (Exception e) {
                errorListener.onErrorResponse(e);
            }
        } else {
            rtn = gson.fromJson(response, Env.sApiBase);
        }
        listener.onResponse(rtn);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
