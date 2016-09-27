package com.exitedcode.supermvp.android.databinding;

import android.databinding.ViewDataBinding;

import com.exitedcode.supermvp.android.IAndroidView;

/**
 * Created by ymr on 16/3/26.
 */
interface IDatabindingView {

    void finishCreateDataBinding(ViewDataBinding viewDataBinding);

    int getContentLayout();
}
