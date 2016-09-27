package com.exitedcode.supermvp.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.exitedcode.supermvp.common.BasePresenter;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class BaseAndroidPresenter<V extends IAndroidView> extends BasePresenter<V> implements IAndroidPresenter<V> {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onMessge(msg);
        }
    };

    public Handler getHandler() {
        return mHandler;
    }

    protected void onMessge(Message msg) {

    }

    public BaseAndroidPresenter(V view) {
        attachView(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        detachView(true);
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    protected void onAttachView() {

    }

    @Override
    protected void onDetachView() {

    }

    public Activity getActivity() {
        if (getView() != null) {
            return getView().getActivity();
        }
        return null;
    }

    public Intent getIntent() {
        if (getView() != null) {
            return getView().getIntent();
        }
        return null;
    }
}
