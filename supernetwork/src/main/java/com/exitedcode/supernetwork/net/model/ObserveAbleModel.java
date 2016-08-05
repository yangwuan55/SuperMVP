package com.exitedcode.supernetwork.net.model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ymr on 15/7/27.
 */
public class ObserveAbleModel implements IObserveAbleModel {
    private List<WeakReference<Listener>> mListeners = new ArrayList<>();
    private List<WeakReference<Listener2>> mListener2s = new ArrayList<>();

    @Override
    public void registeListener(Listener listener) {
        if (!contains(listener)) {
            mListeners.add(new WeakReference<Listener>(listener));
        }
    }

    private boolean contains(Listener listener) {
        if (!mListeners.isEmpty()) {
            for (WeakReference<Listener> weakReference : mListeners) {
                Listener referenceListener = weakReference.get();
                if (referenceListener != null && referenceListener.equals(listener)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void unregisteListener(Listener listener) {
        Iterator<WeakReference<Listener>> iterator = mListeners.iterator();
        while (iterator.hasNext()) {
            WeakReference<Listener> next = iterator.next();
            Listener referenceListener = next.get();
            if (referenceListener != null && referenceListener.equals(listener)) {
                iterator.remove();
            }
        }
    }

    @Override
    public void notifyListeners() {
        if (!mListeners.isEmpty())
        for (WeakReference<Listener> weakReference : mListeners) {
            Listener listener = weakReference.get();
            if (listener != null) {
                listener.onChange();
            }
        }
    }

    @Override
    public void registeListener(Listener2 listener) {
        if (!contains(listener)) {
            mListener2s.add(new WeakReference<Listener2>(listener));
        }
    }

    private boolean contains(Listener2 listener) {
        if (!mListener2s.isEmpty()) {
            for (WeakReference<Listener2> weakReference : mListener2s) {
                Listener2 referenceListener = weakReference.get();
                if (referenceListener != null && referenceListener.equals(listener)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void unregisteListener(Listener2 listener) {
        Iterator<WeakReference<Listener2>> iterator = mListener2s.iterator();
        while (iterator.hasNext()) {
            WeakReference<Listener2> next = iterator.next();
            Listener2 referenceListener = next.get();
            if (referenceListener != null && referenceListener.equals(listener)) {
                iterator.remove();
            }
        }
    }

    @Override
    public void notifyListeners(String args) {
        if (!mListener2s.isEmpty()) {
            for (WeakReference<Listener2> weakReference : mListener2s) {
                Listener2 listener = weakReference.get();
                if (listener != null) {
                    listener.onChange(args);
                }
            }
        }
        notifyListeners();
    }

}
