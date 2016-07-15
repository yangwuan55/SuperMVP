package com.ymr.supernetwork;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.ymr.supernetwork.net.bean.IApiBase;
import com.ymr.supernetwork.net.params.CommonParamsGetter;
import com.ymr.supernetwork.net.params.DomainUrl;

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
        initImageLoader(application);
    }

    private static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);

        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
