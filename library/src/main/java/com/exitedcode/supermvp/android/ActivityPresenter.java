package com.exitedcode.supermvp.android;

/**
 * Created by ymr on 2016/9/26.
 */
public abstract class ActivityPresenter<V extends IActivityView> extends BaseAndroidPresenter<V>{

    public ActivityPresenter(V view) {
        super(view);
    }

    public boolean onBackPress() {
        return false;
    }
}
