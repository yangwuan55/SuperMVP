package com.exitedcode.supernetwork.net.model;

/**
 * Created by ymr on 15/7/27.
 */
public interface IObserveAbleModel {

    interface Listener {
        void onChange();
    }

    interface Listener2 {
        void onChange(String args);
    }

    void registeListener(Listener listener);

    void unregisteListener(Listener listener);

    void notifyListeners();

    void registeListener(Listener2 listener);

    void unregisteListener(Listener2 listener);

    void notifyListeners(String args);
}
