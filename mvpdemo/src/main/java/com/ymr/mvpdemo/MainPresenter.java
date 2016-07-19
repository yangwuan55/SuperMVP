package com.ymr.mvpdemo;

import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.ListAdapter;

import com.ymr.common.adapter.databinding.DataBindingAdapter;
import com.ymr.common.adapter.IViewHolder;
import com.ymr.mvpdemo.databinding.LayoutListItemBinding;
import com.ymr.supermvp.android.databinding.DatabindingPresenter;

import java.util.ArrayList;

/**
 * Created by ymr on 16/3/26.
 */
public class MainPresenter extends DatabindingPresenter<MainView> {

    private DataBindingAdapter<Test> mDataBindingAdapter;

    private Handler mHandler = new Handler();

    public MainPresenter(MainView view) {
        super(view);
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
        mDataBindingAdapter = new DataBindingAdapter<Test>(getView().getContext()) {
            @Override
            protected IViewHolder<Test, ViewDataBinding> createViewHolder(int viewType) {
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
