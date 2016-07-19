package com.ymr.common.adapter.databinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;

import com.ymr.common.adapter.IViewHolder;

public class DatabindingHolderDelegate<D> {

    private final Context mContext;

    public DatabindingHolderDelegate(Context context) {
        mContext = context;
    }

    protected View createRealView(ViewDataBinding viewDataBinding) {
        return viewDataBinding.getRoot();
    }

    protected ViewDataBinding createView(IViewHolder<D, ViewDataBinding> viewHolder) {
        return DataBindingUtil.inflate(LayoutInflater.from(mContext), viewHolder.getItemLayout(), null, false);
    }
}