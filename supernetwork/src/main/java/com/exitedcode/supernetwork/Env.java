package com.exitedcode.supernetwork;

import android.app.Application;

import com.exitedcode.supernetwork.net.bean.IApiBase;
import com.exitedcode.supernetwork.net.params.CommonParamsGetter;
import com.exitedcode.supernetwork.net.params.DomainUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ymr on 16/5/6.
 */
public class Env {

    public static CommonParamsGetter sCommonParamsGetter;

    public static DomainUrl sWebUrl;
    public static Map<String, String> sHeaders = new HashMap<>();
    public static Class<? extends IApiBase> sApiBase;
    private static Init sInit;

    public static boolean isDebug() {
        return sInit.isDebug();
    }

    public static Class<? extends IApiBase> getApiBase() {
        return null;
    }

    public static void init(Init init, Application application) {
        sInit = init;
        sWebUrl = sInit.getWebUrl();
        sHeaders = sInit.getHeader();
        sCommonParamsGetter = sInit.getCommonParamsGetter();
        sApiBase = sInit.getApibaseClass();
    }
}
