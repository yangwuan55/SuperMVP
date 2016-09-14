package com.exitedcode.supermvp.common;

/**
 * Created by ymr on 16/3/26.
 */
public interface IView<P extends IPresenter> {
    P getPresenter();
    boolean isVisible();
}
