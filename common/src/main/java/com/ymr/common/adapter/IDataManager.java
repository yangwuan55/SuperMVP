package com.ymr.common.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by ymr on 15/11/4.
 */
public interface IDataManager<D>{

    void addDatas(List<D> datas);

    void setDatas(List<D> datas);

    List<D> getDatas();

    int getCount();

    D getItem(int position);

    long getItemId(int position);

    Context getContext();

    void notifyDataSetChanged();
}
