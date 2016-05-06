package com.ymr.supermvp.android.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.ymr.supermvp.android.BaseFragment;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class DataBindingFragment<P extends DatabindingPresenter> extends BaseFragment<P> implements IDatabindingView<P>{

    private ViewDataBinding mViewDataBinding;

    @Override
    public void finishCreatePresenter() {

    }

    @Override
    protected View onCreateChildView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mViewDataBinding == null) {
            mViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), getContentLayout(), null, false);
        }
        ViewGroup parent = (ViewGroup) mViewDataBinding.getRoot().getParent();
        if (parent != null) {
            parent.removeView(mViewDataBinding.getRoot());
        }
        finishCreateDataBinding(mViewDataBinding);
        getPresenter().finishCreateView();
        return mViewDataBinding.getRoot();
    }
}
