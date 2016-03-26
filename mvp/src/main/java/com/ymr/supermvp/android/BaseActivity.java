package com.ymr.supermvp.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class BaseActivity<P extends IAndroidPresenter> extends AppCompatActivity implements IAndroidView<P> {

    private boolean mIsVisible;
    private final ViewDelegate<P> mViewDelegate = new ViewDelegate<>(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewDelegate.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewDelegate.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewDelegate.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        mIsVisible = false;
        mViewDelegate.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewDelegate.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsVisible = true;
        mViewDelegate.onResume();
    }

    @Override
    public boolean isVisible() {
        return mIsVisible;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public P getPresenter() {
        return mViewDelegate.getPresenter();
    }
}
