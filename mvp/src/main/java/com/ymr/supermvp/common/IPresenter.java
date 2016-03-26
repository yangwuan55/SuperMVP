package com.ymr.supermvp.common;

/**
 * Created by ymr on 16/3/26.
 */
public interface IPresenter<V extends IView> {

    void attachView(V view);

    void detachView(boolean retainInstance);

    boolean isViewAttached();
}
