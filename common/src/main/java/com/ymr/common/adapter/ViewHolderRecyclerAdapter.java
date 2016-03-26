package com.ymr.common.adapter;

import android.content.Context;
import android.view.View;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class ViewHolderRecyclerAdapter<Data> extends HolderRecyclerAdapter<Data, View> {
    private final ViewHolderDelegate<Data> viewHolderDelegate = new ViewHolderDelegate<>(getContext());

    public ViewHolderRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    protected View createRealView(View view) {
        return viewHolderDelegate.createRealView(view);
    }

    @Override
    protected View createView(IViewHolder<Data, View> viewHolder) {
        return viewHolderDelegate.createView(viewHolder);
    }
}
