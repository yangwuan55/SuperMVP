package com.ymr.supermvp.android.databinding;

import com.ymr.supermvp.android.BaseAndroidPresenter;

/**
 * Created by ymr on 16/3/26.
 */
public abstract class DatabindingPresenter<V extends IDatabindingView> extends BaseAndroidPresenter<V> {
    public abstract void finishCreateView();
}
