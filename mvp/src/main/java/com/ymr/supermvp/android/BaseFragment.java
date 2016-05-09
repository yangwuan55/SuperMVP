package com.ymr.supermvp.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class BaseFragment<P extends IAndroidPresenter> extends Fragment implements IAndroidView<P> {

    private final ViewDelegate<P> mViewDelegate = new ViewDelegate<>(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDelegate.onCreate(savedInstanceState);
        return onCreateChildView(inflater,container,savedInstanceState);
    }

    protected abstract View onCreateChildView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onStop() {
        super.onStop();
        mViewDelegate.onStop();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewDelegate.onDestroy();
    }

    @Override
    public void gotoActivity(Class<? extends Activity> activityClass) {
        gotoActivity(new Intent(getActivity(),activityClass));
    }

    @Override
    public void gotoActivity(Class<? extends Activity> activityClass, int requestCode) {
        gotoActivity(new Intent(getActivity(),activityClass),requestCode);
    }

    @Override
    public void gotoActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void gotoActivity(Intent intent, int requestCode) {
        startActivityForResult(intent,requestCode);
    }
}
