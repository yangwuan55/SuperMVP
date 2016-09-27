package com.exitedcode.supermvp.android.databinding;

import com.exitedcode.supermvp.android.IAndroidPresenter;
import com.exitedcode.supermvp.android.IAndroidView;
import com.exitedcode.supermvp.android.databinding.IDatabindingView;

/**
 * Created by ymr on 2016/9/27.
 */
public interface IDatabindingFragmentView<P extends IAndroidPresenter> extends IAndroidView<P>,IDatabindingView {
}
