package com.ymr.common.adapter;

/**
 * Created by ymr on 15/6/13.
 */
public interface DataReceiver<T> {
    void onReceiveData(T data);
}
