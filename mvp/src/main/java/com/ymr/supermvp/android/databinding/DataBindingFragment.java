package com.ymr.supermvp.android.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ymr.supermvp.android.BaseFragment;
import com.ymr.supermvp.android.databinding.DatabindingPresenter;
import com.ymr.supermvp.android.databinding.IDatabindingView;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class DataBindingFragment<P extends DatabindingPresenter> extends BaseFragment<P> implements IDatabindingView<P>{

    @Override
    public void finishCreatePresenter() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), getContentLayout(), null, false);
        finishCreateDataBinding(viewDataBinding,getPresenter());
        getPresenter().finishCreateView();
        return viewDataBinding.getRoot();
    }

}
