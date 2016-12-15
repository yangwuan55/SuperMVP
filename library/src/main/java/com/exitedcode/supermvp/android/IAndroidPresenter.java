package com.exitedcode.supermvp.android;

import android.os.Handler;

import com.exitedcode.supermvp.common.IModel;
import com.exitedcode.supermvp.common.IPresenter;

/**
 * Created by ymr on 16/3/26.
 */
public interface IAndroidPresenter<V extends IAndroidView,M extends IModel> extends IPresenter<V,M>,IViewDelegate {
    Handler getHandler();
}
