package com.mapsh.sdk.mvp;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by mapsh on 2016/10/10.
 */

public class RxPresenter<V extends IMvpView> implements IMvpPresenter<V>, LifecycleProvider<PresenterEvent> {

    private final BehaviorSubject<PresenterEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<PresenterEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull PresenterEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycle.bind(lifecycleSubject);
    }

    @Override
    public void attachView(V view) {

    }

    @Override
    public void detachView(boolean retainInstance) {
        lifecycleSubject.onNext(PresenterEvent.DETACH);
    }
}
