package com.mapsh.sdk.mvp;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

/**
 * {@link IMvpPresenter} 的基本实现
 * <p/>
 * Created by mapsh on 16/4/28.
 */
public class MvpPresenter<V extends IMvpView> extends RxPresenter<V> {

    private WeakReference<V> viewRef;

    @UiThread
    @Override
    @CallSuper
    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
    }

    /**
     * Get the attached view. You should always call {@link #isViewAttached()} to check if the view
     * is
     * attached to avoid NullPointerExceptions.
     *
     * @return <code>null</code>, if view is not attached, otherwise the concrete view instance
     */
    @UiThread
    @Nullable
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    @UiThread
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @CallSuper
    @UiThread
    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
