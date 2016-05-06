package com.ymr.common.adapter;

import android.content.Context;
import android.view.View;

public class ViewHolderDelegate<Data> {

    private final Context mContext;

    public ViewHolderDelegate(Context context) {
        mContext = context;
    }

    protected View createRealView(View view) {
        return view;
    }

    protected View createView(IViewHolder<Data, View> viewHolder) {
        return View.inflate(mContext, viewHolder.getItemLayout(), null);
    }
}