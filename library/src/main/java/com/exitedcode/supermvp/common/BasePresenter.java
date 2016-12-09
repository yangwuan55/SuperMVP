package com.exitedcode.supermvp.common;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IView,M extends IModel> implements IPresenter<V,M> {
    private final M mModel;
    public WeakReference<V> viewRef;
    private boolean isDetached = false;

    public BasePresenter() {
        mModel = createModel();
    }

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
        onAttachView();
        isDetached = false;
    }

    /**
     * Get the attached view. You should always call {@link #isViewAttached()} to check if the view
     * is
     * attached to avoid NullPointerExceptions.
     *
     * @return <code>null</code>, if view is not attached, otherwise the concrete view instance
     */
    @Override
    @Nullable
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    @Override
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null && !isDetached;
    }

    @Override
    public void detachView(boolean dislodgeInstance) {
        onDetachView();
        isDetached = true;
        if (dislodgeInstance && viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    protected M getModel() {
        return mModel;
    }

    protected abstract void onAttachView();

    protected abstract void onDetachView();
}