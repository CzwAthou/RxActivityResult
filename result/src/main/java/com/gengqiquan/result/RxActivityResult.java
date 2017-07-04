package com.gengqiquan.result;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by gengqiquan on 2017/7/3.
 */

public class RxActivityResult {
    static PublishSubject<Intent> subject;

    private RxActivityResult() {
    }

    public static Builder with(Activity activity) {
        return new Builder(activity);
    }

    public static Builder with(FragmentActivity activity) {
        return new Builder(activity);
    }

    public static Builder with(android.app.Fragment fragment) {
        return new Builder(fragment);
    }

    public static Builder with(Fragment fragment) {
        return new Builder(fragment);
    }

    public static class Builder {
        boolean isSuperV4;
        Bundle data = new Bundle();
        android.app.FragmentTransaction appTransaction;
        FragmentTransaction v4Transaction;

        private Builder() {
        }

        @SuppressLint("CommitTransaction")
        private Builder(android.app.Fragment t) {
            appTransaction = t.getActivity()
                    .getFragmentManager()
                    .beginTransaction();

        }

        @SuppressLint("CommitTransaction")
        private Builder(Fragment t) {
            isSuperV4 = true;
            v4Transaction = t.getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();
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

        public Observable<Intent> startActivityWithResult(final Intent intent) {
            if (intent == null) {
                throw new RuntimeException("intent can not be null");
            }
            subject = PublishSubject.create();
            intent.putExtras(data);
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", intent);
            if (isSuperV4) {
                final V4Fragment v4Fragment = new V4Fragment();
                v4Fragment.setArguments(bundle);
                v4Transaction.replace(android.R.id.content, v4Fragment)
                        .commitAllowingStateLoss();
                v4Transaction = null;
            } else {
                final AppFragment appFragment = new AppFragment();
                appFragment.setArguments(bundle);
                appTransaction.replace(android.R.id.content, appFragment)
                        .commitAllowingStateLoss();
                v4Transaction = null;
            }

            return subject;
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


    }

    protected static void post(Intent intent) {

        if (intent != null) {
            subject.onNext(intent);
        } else {
            subject.onError(new Exception("intent is null"));
        }
    }

}
