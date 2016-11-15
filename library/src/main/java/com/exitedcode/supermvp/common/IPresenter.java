package com.exitedcode.supermvp.common;

import android.support.annotation.Nullable;

/**
 * Created by ymr on 16/3/26.
 */
public interface IPresenter<V extends IView> {

    void attachView(V view);

    void detachView(boolean retainInstance);

    @Nullable
    V getView();

    boolean isViewAttached();
}
