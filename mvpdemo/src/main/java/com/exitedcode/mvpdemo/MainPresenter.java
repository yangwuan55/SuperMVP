package com.exitedcode.mvpdemo;

import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.ListAdapter;

import com.exitedcode.mvpdemo.model.HomeModel;
import com.exitedcode.mvpdemo.model.IHomeModel;
import com.exitedcode.superadapter.base.IViewHolder;
import com.exitedcode.superadapter.databinding.DataBindingAdapter;
import com.exitedcode.mvpdemo.databinding.LayoutListItemBinding;
import com.exitedcode.supermvp.android.databinding.activity.DatabindingActivityPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymr on 16/3/26.
 */
public class MainPresenter extends DatabindingActivityPresenter<MainView,IHomeModel> {

    private DataBindingAdapter<Test> mDataBindingAdapter;

    public MainPresenter(MainView view) {
        super(view);
    }

    @Override
    protected boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void finishCreateView() {
        getView().showLoading();
        getModel().loadDatas(new IHomeModel.LoadDataListener() {
            @Override
            public void onRecive(List<Test> tests) {
                mDataBindingAdapter.setDatas(tests);
                getView().hideLoading();
            }
        });
    }

    private void updateView() {
        if (getView().isHelloShowing()) {
            getView().setBtnText("hide");
        } else {
            getView().setBtnText("show");
        }
    }

    public void clickShow() {
        if (getView().isHelloShowing()) {
            getView().hideHello();
        } else {
            getView().showHello();
        }
        updateView();
    }

    public ListAdapter getAdapter() {
        if (mDataBindingAdapter == null) {
            initAdapter();
        }
        return mDataBindingAdapter;
    }

    private void initAdapter() {
        mDataBindingAdapter = new DataBindingAdapter<Test>(getView().getActivity()) {
            @Override
            public IViewHolder<Test, ViewDataBinding> createViewHolder(int viewType) {
                return new IViewHolder<Test, ViewDataBinding>() {
                    @Override
                    public void reset(ViewDataBinding viewDataBinding) {

                    }

                    @Override
                    public void onSetView(Test item, ViewDataBinding viewDataBinding, int position) {
                        ((LayoutListItemBinding) viewDataBinding).text.setText(item.text);
                    }

                    @Override
                    public int getItemLayout() {
                        return R.layout.layout_list_item;
                    }
                };
            }
        };
    }

    @Override
    public IHomeModel createModel() {
        return new HomeModel();
    }
}
