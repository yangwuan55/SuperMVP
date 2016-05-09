package com.ymr.supermvp.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.ymr.supermvp.common.IView;

/**
 * Created by ymr on 16/3/26.
 */
public interface IAndroidView<P extends IAndroidPresenter> extends IView<P> {
    void finishCreatePresenter();
    P createPresenter(Context context);
    Context getContext();
    void gotoActivity(Class<? extends Activity> activityClass);
    void gotoActivity(Class<? extends Activity> activityClass,int requestCode);
    void gotoActivity(Intent intent);
    void gotoActivity(Intent intent,int requestCode);
}
