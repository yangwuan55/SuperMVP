package com.ymr.supernetwork.net.model;

import com.ymr.supernetwork.net.model.IModel;
import com.ymr.supernetwork.net.model.ObserveAbleModel;

/**
 * Created by ymr on 15/7/27.
 */
public class SimpleModel implements IModel {

    private final ObserveAbleModel mObserveAbleModel;

    public SimpleModel() {
        mObserveAbleModel = new ObserveAbleModel();
    }

    @Override
    public void registeListener(Listener listener) {
        mObserveAbleModel.registeListener(listener);
    }

    @Override
    public void unregisteListener(Listener listener) {
        mObserveAbleModel.unregisteListener(listener);
    }

    @Override
    public void notifyListeners() {
        mObserveAbleModel.notifyListeners();
    }

    @Override
    public void registeListener(Listener2 listener) {
        mObserveAbleModel.registeListener(listener);
    }

    @Override
    public void unregisteListener(Listener2 listener) {
        mObserveAbleModel.unregisteListener(listener);
    }

    @Override
    public void notifyListeners(String args) {
        mObserveAbleModel.notifyListeners(args);
    }
}
