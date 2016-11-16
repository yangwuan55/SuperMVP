package com.exitedcode.supermvp.android.databinding.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.exitedcode.supermvp.android.BaseActivity;
import com.exitedcode.supermvp.android.databinding.IDatabindingActivityView;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class DataBindingActivity<P extends DatabindingActivityPresenter> extends BaseActivity<P> implements IDatabindingActivityView<P> {

    @Override
    public void finishCreatePresenter(Bundle savedInstanceState) {
        initDataBinding(savedInstanceState);
    }

    private void initDataBinding(Bundle savedInstanceState) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(this), getContentLayout(), null, false);
        setContentView(viewDataBinding.getRoot());
        finishCreateDataBinding(viewDataBinding,savedInstanceState);
        if (getPresenter() != null) {
            getPresenter().finishCreateView();
        }
    }
}
