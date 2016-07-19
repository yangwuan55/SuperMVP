package com.ymr.common.adapter.databinding;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.ymr.common.adapter.HolderAdapter;
import com.ymr.common.adapter.IViewHolder;

import java.util.List;

/**
 * Created by ymr on 15/10/13.
 */
public abstract class DataBindingAdapter<D> extends HolderAdapter<D,ViewDataBinding> {
    private final DatabindingHolderDelegate<D> databindingHolderDelegate = new DatabindingHolderDelegate<D>(getContext());

    public DataBindingAdapter(Context context) {
        super(context);
    }

    public DataBindingAdapter(Context context, List<D> ds) {
        super(context, ds);
    }

    @Override
    protected View createRealView(ViewDataBinding viewDataBinding) {
        return databindingHolderDelegate.createRealView(viewDataBinding);
    }

    @Override
    protected ViewDataBinding createView(IViewHolder<D, ViewDataBinding> viewHolder) {
        return databindingHolderDelegate.createView(viewHolder);
    }
}
