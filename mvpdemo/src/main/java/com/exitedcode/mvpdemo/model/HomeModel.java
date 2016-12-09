package com.exitedcode.mvpdemo.model;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.exitedcode.mvpdemo.Test;

import java.util.ArrayList;

/**
 * Created by ymr on 2016/12/9.
 */
public class HomeModel implements IHomeModel {
    private Handler mHandler = new Handler();

    @Override
    public void loadDatas(final LoadDataListener loadDataListener) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Test> datas = createSomeDatas();
                loadDataListener.onRecive(datas);
            }
        },3000);
    }

    @NonNull
    private ArrayList<Test> createSomeDatas() {
        ArrayList<Test> datas = new ArrayList<>();
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        return datas;
    }
}
