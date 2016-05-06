package com.ymr.supernetwork.net.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.ymr.supernetwork.net.bean.IApiBase;
import com.ymr.supernetwork.net.INetWork;
import com.ymr.supernetwork.net.params.FileParams;
import com.ymr.supernetwork.net.params.NetRequestParams;

import java.util.Map;

/**
 * Created by ymr on 15/5/7.
 */
public class VolleyUtil implements INetWork {
    //最大缓存50mb
    public static final long MAX_DISK_SIZE = 8 * 1024 * 1024 * 50;
    private static final String TAG = "VolleyUtil";
    public static final String DEFAULT_TAG = "default_tag";
    private static VolleyUtil sInstance;
    private final Context mContext;
    private final RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public interface ImageLoadSuccessLisener{
        void onLoadSuccess(Bitmap bitmap);
        void onFail();
    }
    private VolleyUtil(Context context) {
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext, new MultiPartStack());
        mImageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
    }

    public static VolleyUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new VolleyUtil(context);
        }
        return sInstance;
    }

    public void cancelAll() {
        mRequestQueue.cancelAll(DEFAULT_TAG);
    }

    public void addRequest(Request request) {
        request.setTag(DEFAULT_TAG);
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        mRequestQueue.add(request);
    }

    public void loadImage(String url, ImageView view, int defultRes, int errorRes) {
        mImageLoader.displayImage(url, view, new DisplayImageOptions.Builder()
                .showImageOnLoading(defultRes)
                .showImageOnFail(errorRes)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build());
    }

    public void loadImage(String url,final ImageLoadSuccessLisener listener){
        mImageLoader.loadImage(url, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                listener.onFail();
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                listener.onLoadSuccess(bitmap);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                listener.onFail();
            }
        });
    }

    public void loadImage(String url, ImageView imageView) {
        mImageLoader.displayImage(url, imageView, new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build());
    }

    public <T,P extends NetRequestParams> void addRequest(final P params, final RequestListner<IApiBase<T>> requestListner, Class<T> tClass, Map<String,String> headers, String cookies) {
        Request request = null;
        if (params instanceof FileParams) {
            request = getFileUploadRequest(((FileParams) params), requestListner, tClass,headers,cookies);
        } else {
            request = getNormalObjectRequest(params, requestListner, tClass,headers,cookies);
        }
        addRequest(request);
    }

    @Nullable
    private <T, P extends NetRequestParams> ObjectRequest<T> getNormalObjectRequest(final P params, final RequestListner<IApiBase<T>> requestListner, final Class<T> tClass,Map<String,String> headers,String cookies) {
        ObjectRequest<T> gsonRequest = null;

        final Response.Listener<IApiBase<T>> listener = new Response.Listener<IApiBase<T>>() {
            @Override
            public void onResponse(IApiBase<T> response) {
                requestListner.onSuccess(response);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                requestListner.onFail(volleyError);
            }
        };

        switch (params.getMethod()) {
            case Request.Method.GET:
                gsonRequest = new ObjectRequest<T>(Request.Method.GET, params.getUrl(),
                        listener,errorListener,tClass);
                break;

            case Request.Method.POST:
                gsonRequest = new ObjectRequest<T>(Request.Method.POST, params.getUrl(),
                        listener,errorListener,tClass) {
                    @Override
                    protected Map<String, String> getParams() {
                        return params.getPostParams();
                    }
                };
                break;
        }
        gsonRequest.setCookies(cookies);
        gsonRequest.setHeaders(headers);
        return gsonRequest;
    }

    @NonNull
    private <T, P extends FileParams> FileUploadRequest<T> getFileUploadRequest(final P params, final RequestListner<IApiBase<T>> requestListner, final Class<T> tClass,Map<String,String> headers,String cookies) {
        FileUploadRequest<T> fileUploadRequest = null;

        final Response.Listener<IApiBase<T>> listener = new Response.Listener<IApiBase<T>>() {
            @Override
            public void onResponse(IApiBase<T> response) {
                requestListner.onSuccess(response);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                requestListner.onFail(volleyError);
            }
        };

        fileUploadRequest = new FileUploadRequest(params.getUrl(),listener, errorListener, tClass);
        fileUploadRequest.addFileUpload(params.getFileMap());
        if (params.getPostParams() != null) {
            fileUploadRequest.addStringUpload(params.getPostParams());
        }
        fileUploadRequest.setCookies(cookies);
        fileUploadRequest.setHeaders(headers);
        fileUploadRequest.setRetryPolicy(//关闭retry
                new DefaultRetryPolicy(
                        Integer.MAX_VALUE,
                        0,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return fileUploadRequest;
    }
}
