package com.exitedcode.supermvp.android.databinding.activity;

import com.exitedcode.supermvp.android.ActivityPresenter;
import com.exitedcode.supermvp.android.databinding.IDatabindingActivityView;
import com.exitedcode.supermvp.android.databinding.IDatabindingPresenter;

/**
 * Created by ymr on 2016/9/27.
 */
public abstract class DatabindingActivityPresenter<V extends IDatabindingActivityView> extends ActivityPresenter<V> implements IDatabindingPresenter {
    public DatabindingActivityPresenter(V view) {
        super(view);
    }
}
