package com.exitedcode.mvpdemo;

import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.ListAdapter;

import com.exitedcode.superadapter.base.IViewHolder;
import com.exitedcode.superadapter.databinding.DataBindingAdapter;
import com.exitedcode.mvpdemo.databinding.LayoutListItemBinding;
import com.exitedcode.supermvp.android.databinding.activity.DatabindingActivityPresenter;

import java.util.ArrayList;

/**
 * Created by ymr on 16/3/26.
 */
public class MainPresenter extends DatabindingActivityPresenter<MainView> {

    private DataBindingAdapter<Test> mDataBindingAdapter;

    private Handler mHandler = new Handler();

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
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Test> datas = createSomeDatas();
                mDataBindingAdapter.setDatas(datas);
                getView().hideLoading();
            }
        },3000);
    }

    @NonNull
    private ArrayList<Test> createSomeDatas() {
        ArrayList<Test> datas = new ArrayList<>();
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        datas.add(new Test("aaaa"));
        return datas;
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
}
