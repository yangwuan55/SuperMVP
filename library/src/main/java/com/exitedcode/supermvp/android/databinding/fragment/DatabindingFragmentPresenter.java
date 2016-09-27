package com.exitedcode.supermvp.android.databinding.fragment;

import com.exitedcode.supermvp.android.BaseAndroidPresenter;
import com.exitedcode.supermvp.android.databinding.IDatabindingFragmentView;
import com.exitedcode.supermvp.android.databinding.IDatabindingPresenter;

/**
 * Created by ymr on 2016/9/27.
 */
public abstract class DatabindingFragmentPresenter<V extends IDatabindingFragmentView> extends BaseAndroidPresenter<V> implements IDatabindingPresenter {
    public DatabindingFragmentPresenter(V view) {
        super(view);
    }
}
