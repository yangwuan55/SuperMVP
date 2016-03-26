package com.ymr.common.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymr on 15/11/4.
 */
public class DataManagerDelegate<D> implements IDataManager<D> {

    private final IDataManager mDataManager;
    private List<D> mDatas = new ArrayList<>();

    public DataManagerDelegate(IDataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void addDatas(List<D> datas) {
        if (datas != null) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @Override
    public void setDatas(List<D> datas) {
        if (datas != null) {
            mDatas = datas;
        } else {
            mDatas.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    public List<D> getDatas() {
        return mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public D getItem(int position) {
        if (position < mDatas.size()) {
            return mDatas.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Context getContext() {
        return mDataManager.getContext();
    }

    @Override
    public void notifyDataSetChanged() {
        mDataManager.notifyDataSetChanged();
    }
}
