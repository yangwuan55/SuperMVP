package com.exitedcode.mvpdemo;

import com.exitedcode.supermvp.android.databinding.IDatabindingActivityView;

/**
 * Created by ymr on 16/3/26.
 */
public interface MainView extends IDatabindingActivityView<MainPresenter> {
    boolean isHelloShowing();

    void hideHello();

    void showHello();

    void setBtnText(String text);

    void showLoading();

    void hideLoading();
}
