package com.exitedcode.mvpdemo.model;

import com.exitedcode.mvpdemo.Test;
import com.exitedcode.supermvp.common.IModel;

import java.util.List;

/**
 * Created by ymr on 2016/12/9.
 */
public interface IHomeModel extends IModel {
    void loadDatas(LoadDataListener loadDataListener);

    interface LoadDataListener {
        void onRecive(List<Test> tests);
    }


}
