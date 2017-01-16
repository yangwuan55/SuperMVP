package com.exitedcode.supermvp.android.databinding.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exitedcode.supermvp.android.BaseFragment;
import com.exitedcode.supermvp.android.databinding.IDatabindingFragmentView;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class DataBindingFragment<P extends DatabindingFragmentPresenter> extends BaseFragment<P> implements IDatabindingFragmentView<P> {

    private ViewDataBinding mViewDataBinding;

    @Override
    public void finishCreatePresenter(Bundle savedInstanceState) {

    }

    @Override
    protected View onCreateChildView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mViewDataBinding == null) {
            mViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), getContentLayout(), null, false);
            finishCreateDataBinding(mViewDataBinding, savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) mViewDataBinding.getRoot().getParent();
            if (parent != null) {
                parent.removeView(mViewDataBinding.getRoot());
            }
        }
        if (getPresenter() != null) {
            getPresenter().finishCreateView();
        }
        return mViewDataBinding.getRoot();
    }
}
