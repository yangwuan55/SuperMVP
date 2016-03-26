package com.ymr.supermvp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class BaseFragment<P extends IAndroidPresenter> extends Fragment implements IAndroidView<P> {

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
        mViewDelegate.onResume();
    }

    @Override
    public P getPresenter() {
        return mViewDelegate.getPresenter();
    }
}
