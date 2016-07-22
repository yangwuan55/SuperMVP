package com.ymr.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ymr.common.R;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class HolderRecyclerAdapter<Data,GView> extends DataRecyclerAdapter<Data> {
    private static final int KEY_G_VIEW = 400;

    public HolderRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IViewHolder<Data, GView> viewHolder = createViewHolder(viewType);
        MyViewHolder recyclerViewHolder = new MyViewHolder(viewHolder);
        recyclerViewHolder.setIViewHolder(viewHolder);
        return recyclerViewHolder;
    }

    protected abstract IViewHolder<Data,GView> createViewHolder(int viewType);

    protected View inflate(IViewHolder<Data, GView> viewHolder) {
        GView gView = createView(viewHolder);
        View view = createRealView(gView);
        view.setTag(R.id.tag_g_view,gView);
        return view;
    }

    protected abstract View createRealView(GView gView);

    protected abstract GView createView(IViewHolder<Data, GView> viewHolder);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IViewHolder<Data, GView> iViewHolder = ((MyViewHolder) holder).getIViewHolder();
        iViewHolder.onSetView(getItem(position), (GView) holder.itemView.getTag(R.id.tag_g_view),position);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private IViewHolder<Data, GView> mIViewHolder;

        public MyViewHolder(IViewHolder<Data, GView> viewHolder) {
            super(HolderRecyclerAdapter.this.inflate(viewHolder));
        }

        public void setIViewHolder(IViewHolder<Data,GView> IViewHolder) {
            this.mIViewHolder = IViewHolder;
        }

        public com.ymr.common.adapter.IViewHolder<Data, GView> getIViewHolder() {
            return mIViewHolder;
        }
    }
}
