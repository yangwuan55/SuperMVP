package com.ymr.supermvp.android.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;

import com.ymr.supermvp.android.BaseActivity;
import com.ymr.supermvp.android.databinding.DatabindingPresenter;
import com.ymr.supermvp.android.databinding.IDatabindingView;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class DataBindingActivity<P extends DatabindingPresenter> extends BaseActivity<P> implements IDatabindingView<P> {

    @Override
    public void finishCreatePresenter() {
        initDataBinding();
    }

    private void initDataBinding() {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(this), getContentLayout(), null, false);
        setContentView(viewDataBinding.getRoot());
        finishCreateDataBinding(viewDataBinding);
        getPresenter().finishCreateView();
    }
}
