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

public class RxResult {
    Intent intent;
    static final PublishSubject<Intent> subject = PublishSubject.create();

//    public static Observable<Intent> start(Activity activity, final Intent intent) {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("data", intent);
//        if (activity instanceof FragmentActivity) {
//            final V4Fragment v4Fragment = new V4Fragment();
//            v4Fragment.setArguments(bundle);
//            ((FragmentActivity) activity).getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(android.R.id.content, v4Fragment)
//                    .commitAllowingStateLoss();
//        } else {
//            final AppFragment appFragment = new AppFragment();
//            appFragment.setArguments(bundle);
//            activity.getFragmentManager()
//                    .beginTransaction()
//                    .replace(android.R.id.content, appFragment)
//                    .commitAllowingStateLoss();
//        }
//        return subject;
//    }

    public static <T extends Activity> Builder with(T activity) {
        return new Builder(activity);
    }

    public static <T extends android.app.Fragment> Builder with(T fragment) {
        return new Builder(fragment);
    }

    public static <T extends Fragment> Builder with(T fragment) {
        return new Builder(fragment);
    }

    public static class Builder {
        boolean isSuperV4;
        Bundle data = new Bundle();
        android.app.FragmentTransaction appTransaction;
        FragmentTransaction v4Transaction;

        @SuppressLint("CommitTransaction")
        public <T> Builder(T t) {
            if (t instanceof android.app.Fragment) {
                appTransaction = ((android.app.Fragment) t).getActivity()
                        .getFragmentManager()
                        .beginTransaction();
            } else if (t instanceof Fragment) {
                isSuperV4 = true;
                v4Transaction = ((Fragment) t).getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction();
            } else if (t instanceof android.app.Activity) {
                appTransaction = ((android.app.Activity) t).getFragmentManager()
                        .beginTransaction();
            } else if (t instanceof Fragment) {
                isSuperV4 = true;
                v4Transaction = ((FragmentActivity) t).getSupportFragmentManager()
                        .beginTransaction();
            }

        }

        public Observable<Intent> start(final Intent intent) {
            if (intent == null) {
                throw new RuntimeException("intent can not be null");
            }
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
