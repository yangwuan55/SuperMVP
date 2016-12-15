package com.exitedcode.supermvp.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.exitedcode.supermvp.common.BasePresenter;
import com.exitedcode.supermvp.common.IModel;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class BaseAndroidPresenter<V extends IAndroidView,M extends IModel> extends BasePresenter<V,M> implements IAndroidPresenter<V,M> {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onMessge(msg);
        }
    };

    @Override
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
        detachView(isDebug());
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
    }

    protected abstract boolean isDebug();

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

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached() && getActivity() != null;
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
