package com.ymr.common.adapter.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by ymr on 16/1/8.
 */
public abstract class DataBindingRecyclerViewHolder<D> extends RecyclerView.ViewHolder {

    private ViewDataBinding mDataBinding;

    private void init() {
        mDataBinding = DataBindingUtil.bind(itemView);
        onCreateDatabinding(mDataBinding);
    }

    public DataBindingRecyclerViewHolder(ViewGroup parent, int viewLayout) {
        super(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),viewLayout,parent,false).getRoot());
        init();
    }

    public ViewDataBinding getDataBinding() {
        return mDataBinding;
    }

    protected abstract void onCreateDatabinding(ViewDataBinding bind);

    public void setData(D data, int position) {
        onSetData(mDataBinding,data,position);
    }

    protected abstract void onSetData(ViewDataBinding dataBinding, D data, int position);
}
