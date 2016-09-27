package com.exitedcode.supermvp.android.databinding;

import com.exitedcode.supermvp.android.IActivityView;
import com.exitedcode.supermvp.android.databinding.activity.DatabindingActivityPresenter;

/**
 * Created by ymr on 2016/9/27.
 */
public interface IDatabindingActivityView<P extends DatabindingActivityPresenter> extends IActivityView<P>,IDatabindingView {
}
