package com.ymr.supermvp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class ViewDelegate<P extends IAndroidPresenter> implements IViewDelegate {
    private final IAndroidView<P> mAndroidView;
    P mPresenter;

    public ViewDelegate(IAndroidView<P> androidView) {
        mAndroidView = androidView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = mAndroidView.createPresenter();
        mAndroidView.finishCreatePresenter();
        if (mPresenter != null) {
            mPresenter.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onStop() {
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mPresenter != null) {
            mPresenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onPause() {
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    public void onStart() {
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    public void onResume() {
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    public P getPresenter() {
        return mPresenter;
    }
}