package com.mapsh.sdk.mvp;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * An Activity that uses an {@link IMvpPresenter} to implement a Model-View-Presenter
 * architecture.
 * <p/>
 * Created by mapsh on 16/4/28.
 */
public abstract class MvpActivity<V extends IMvpView, P extends IMvpPresenter<V>>
        extends RxAppCompatActivity implements MvpDelegateCallback<V, P>, IMvpView {

    protected MvpActivityDelegate<V, P> mvpDelegate;
    protected P presenter;
    protected boolean retainInstance = false;

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpDelegate().onPostCreate(savedInstanceState);
    }

    /**
     * Instantiate a presenter instance
     *
     * @return The {@link IMvpPresenter} for this view
     */
    @NonNull
    public abstract P createPresenter();

    /**
     * Get the mvp delegate. This is internally used for creating presenter, attaching and detaching
     * view from presenter.
     * <p/>
     * <p><b>Please note that only one instance of mvp delegate should be used per Activity
     * instance</b>.
     * </p>
     * <p/>
     * <p>
     * Only override this method if you really know what you are doing.
     * </p>
     *
     * @return {@link MvpActivityDelegateImpl}
     */
    @NonNull
    protected MvpActivityDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new MvpActivityDelegateImpl<>(this);
        }

        return mvpDelegate;
    }

    @NonNull
    public P getPresenter() {
        return presenter;
    }

    public void setPresenter(@NonNull P presenter) {
        this.presenter = presenter;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public V getMvpView() {
        return (V) this;
    }


    @Override
    public boolean isRetainInstance() {
        return retainInstance;
    }

    @Override
    public boolean shouldInstanceBeRetained() {
        return retainInstance && isChangingConfigurations();
    }

    @Override
    public void setRetainInstance(boolean retainInstance) {
        this.retainInstance = retainInstance;
    }
}
