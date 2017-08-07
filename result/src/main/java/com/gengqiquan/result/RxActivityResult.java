package com.gengqiquan.result;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by gengqiquan on 2017/7/3.
 */

public class RxActivityResult {
    static PublishSubject<Result> subject = PublishSubject.create();

    private RxActivityResult() {
    }

    public static Builder with(Context context) {
        if (context instanceof FragmentActivity)
            return new Builder((FragmentActivity) context);
        else if (context instanceof Activity)
            return new Builder((Activity) context);
        else
            throw new RuntimeException("context must be activity or fragment");
    }

    public static class Builder {
        boolean isSuperV4;
        Bundle data = new Bundle();
        android.app.FragmentTransaction appTransaction;
        FragmentTransaction v4Transaction;

        private Builder() {
        }

        @SuppressLint("CommitTransaction")
        private Builder(android.app.Activity t) {
            appTransaction = ((android.app.Activity) t).getFragmentManager()
                    .beginTransaction();
        }

        @SuppressLint("CommitTransaction")
        private Builder(FragmentActivity t) {
            isSuperV4 = true;
            v4Transaction = t.getSupportFragmentManager()
                    .beginTransaction();
        }

        public Observable<Result> startActivityWithResult(final Intent intent) {
            if (intent == null) {
                throw new RuntimeException("intent can not be null");
            }

            intent.putExtras(data);
            final Request request = new Request(intent, intent.hashCode());

            if (isSuperV4) {
                final V4Fragment v4Fragment = new V4Fragment();
                v4Fragment.setRequest(request);
                v4Transaction.replace(android.R.id.content, v4Fragment)
                        .commitAllowingStateLoss();
                v4Transaction = null;
            } else {
                final AppFragment appFragment = new AppFragment();
                appFragment.setRequest(request);
                appTransaction.replace(android.R.id.content, appFragment)
                        .commitAllowingStateLoss();
                v4Transaction = null;
            }

            return subject.filter(new Predicate<Result>() {
                @Override
                public boolean test(Result result) throws Exception {
                    return request.code == result.requestCode;
                }
            });
        }

        public Builder putBoolean(String key, boolean value) {
            data.putBoolean(key, value);
            return this;
        }

        public Builder putInt(String key, int value) {
            data.putInt(key, value);
            return this;
        }

        public Builder putLong(String key, long value) {
            data.putLong(key, value);
            return this;
        }

        public Builder putDouble(String key, double value) {
            data.putDouble(key, value);
            return this;
        }

        public Builder putString(String key, String value) {
            data.putString(key, value);
            return this;
        }

        public Builder putBundle(String key, Bundle value) {
            data.putBundle(key, value);
            return this;
        }

        public Builder putAll(Bundle value) {
            data.putAll(value);
            return this;
        }

    }

    protected static void post(Result result) {
        subject.onNext(result);
    }

}
