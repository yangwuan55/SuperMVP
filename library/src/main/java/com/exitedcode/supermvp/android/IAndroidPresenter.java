package com.exitedcode.supermvp.android;

import com.exitedcode.supermvp.common.IPresenter;

/**
 * Created by ymr on 16/3/26.
 */
public interface IAndroidPresenter<V extends IAndroidView> extends IPresenter<V>,IViewDelegate {
    boolean onBackPressed();
}
