package com.ymr.supermvp.android;

import android.content.Context;

import com.ymr.supermvp.common.IView;

/**
 * Created by ymr on 16/3/26.
 */
public interface IAndroidView<P extends IAndroidPresenter> extends IView<P> {
    void finishCreatePresenter();
    P createPresenter(Context context);
    Context getContext();
}
