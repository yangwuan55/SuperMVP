package com.exitedcode.supermvp.android;

import com.exitedcode.supermvp.common.IModel;

/**
 * Created by ymr on 2016/9/26.
 */
public abstract class ActivityPresenter<V extends IActivityView,M extends IModel> extends BaseAndroidPresenter<V,M>{

    public ActivityPresenter(V view) {
        super(view);
    }

    public boolean onBackPress() {
        return false;
    }
}
