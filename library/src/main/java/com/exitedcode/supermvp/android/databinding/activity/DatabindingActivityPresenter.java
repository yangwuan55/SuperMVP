package com.exitedcode.supermvp.android.databinding.activity;

import com.exitedcode.supermvp.android.ActivityPresenter;
import com.exitedcode.supermvp.android.databinding.IDatabindingActivityView;
import com.exitedcode.supermvp.android.databinding.IDatabindingPresenter;
import com.exitedcode.supermvp.common.IModel;

/**
 * Created by ymr on 2016/9/27.
 */
public abstract class DatabindingActivityPresenter<V extends IDatabindingActivityView,M extends IModel> extends ActivityPresenter<V,M> implements IDatabindingPresenter {
    public DatabindingActivityPresenter(V view) {
        super(view);
    }
}
