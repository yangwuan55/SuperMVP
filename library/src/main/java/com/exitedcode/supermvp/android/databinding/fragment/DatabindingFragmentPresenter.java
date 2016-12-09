package com.exitedcode.supermvp.android.databinding.fragment;

import com.exitedcode.supermvp.android.BaseAndroidPresenter;
import com.exitedcode.supermvp.android.databinding.IDatabindingFragmentView;
import com.exitedcode.supermvp.android.databinding.IDatabindingPresenter;
import com.exitedcode.supermvp.common.IModel;

/**
 * Created by ymr on 2016/9/27.
 */
public abstract class DatabindingFragmentPresenter<V extends IDatabindingFragmentView,M extends IModel> extends BaseAndroidPresenter<V,M> implements IDatabindingPresenter {
    public DatabindingFragmentPresenter(V view) {
        super(view);
    }
}
