package com.ymr.mvpdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.Toast;

import com.ymr.mvpdemo.databinding.ActivityMainBinding;
import com.ymr.supermvp.android.databinding.DataBindingActivity;

public class MainActivity extends DataBindingActivity<MainPresenter> implements MainView {

    private ActivityMainBinding mViewDataBinding;
    private ProgressDialog mProgressDialog;

    @Override
    public void finishCreateDataBinding(ViewDataBinding viewDataBinding) {
        mProgressDialog = new ProgressDialog(this);
        mViewDataBinding = ((ActivityMainBinding) viewDataBinding);
        mViewDataBinding.list.setAdapter(getPresenter().getAdapter());
        mViewDataBinding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().clickShow();
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter createPresenter(Context context) {
        return new MainPresenter(this);
    }

    @Override
    public boolean isHelloShowing() {
        return mViewDataBinding.hello.isShown();
    }

    @Override
    public void hideHello() {
        mViewDataBinding.hello.setVisibility(View.GONE);
    }

    @Override
    public void showHello() {
        mViewDataBinding.hello.setVisibility(View.VISIBLE);
    }

    @Override
    public void setBtnText(String text) {
        mViewDataBinding.btnShow.setText(text);
    }

    @Override
    public void showLoading() {
        mProgressDialog.setMessage("loadding");
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.dismiss();
    }
}
