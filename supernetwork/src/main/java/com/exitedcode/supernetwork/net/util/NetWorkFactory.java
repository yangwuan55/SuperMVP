package com.exitedcode.supernetwork.net.util;

import android.content.Context;

import com.exitedcode.supernetwork.net.INetWork;
import com.exitedcode.supernetwork.net.volley.VolleyUtil;

/**
 * Created by ymr on 16/5/6.
 */
public class NetWorkFactory {
    public static INetWork getNetWorkUtil(Context context) {
        return VolleyUtil.getInstance(context);
    }
}
