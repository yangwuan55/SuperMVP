package com.exitedcode.supermvp.common;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {
    public WeakReference<V> viewRef;

    public BasePresenter() {
    }

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
        onAttachView();
    }

    /**
     * Get the attached view. You should always call {@link #isViewAttached()} to check if the view
     * is
     * attached to avoid NullPointerExceptions.
     *
     * @return <code>null</code>, if view is not attached, otherwise the concrete view instance
     */
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
        return viewRef != null && viewRef.get() != null;
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
        onDetachView();
    }

    protected abstract void onAttachView();

    protected abstract void onDetachView();
}