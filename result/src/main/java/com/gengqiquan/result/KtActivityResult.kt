package com.gengqiquan.result

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import rx.Observable


/**
 * Created by gengqiquan on 2017/7/3.
 */

object RxKtResult {
    fun with(context: Context): Suilder {
        val s = Suilder()
        if (context is FragmentActivity) {
            s.bind(context)
        } else {
            s.bind(context as Activity)
        }
        return s
    }
}

class Suilder {
    internal var isSuperV4: Boolean = false
    internal var data = Bundle()
    internal var appTransaction: android.app.FragmentTransaction? = null
    internal var v4Transaction: FragmentTransaction? = null

    @SuppressLint("CommitTransaction")
    fun bind(t: Activity) {
        appTransaction = t.fragmentManager
                .beginTransaction()
    }

    @SuppressLint("CommitTransaction")
    fun bind(t: FragmentActivity) {
        isSuperV4 = true
        v4Transaction = t.supportFragmentManager
                .beginTransaction()

    }

    constructor()

    fun startActivityWithResult(intent: Intent?, vararg params: Pair<String, Any>): Observable<Intent> {
        data.putAll(Bundle().pair(params))
        return startActivityWithResult(intent)
    }

    fun startActivityWithResult(intent: Intent?): Observable<Intent> {
        if (intent == null) {
            throw RuntimeException("intent can not be null")
        }
        intent.putExtras(data)
        val request = Request(intent, intent.hashCode())
        if (isSuperV4) {
            val v4Fragment = V4Fragment()
            v4Fragment.setRequest(request)
            v4Transaction!!.replace(android.R.id.content, v4Fragment)
                    .commitAllowingStateLoss()
            v4Transaction = null
        } else {
            val appFragment = AppFragment()
            appFragment.setRequest(request)
            appTransaction?.replace(android.R.id.content, appFragment)
                    ?.commitAllowingStateLoss()
            v4Transaction = null
        }

        return RxActivityResult.subject
                .filter { result -> request.code == result.code }
                .map { result -> result.intent }
    }


    fun putBoolean(key: String, value: Boolean): Suilder {
        data.putBoolean(key, value)
        return this
    }

    fun putInt(key: String, value: Int): Suilder {
        data.putInt(key, value)
        return this
    }

    fun putLong(key: String, value: Long): Suilder {
        data.putLong(key, value)
        return this
    }

    fun putDouble(key: String, value: Double): Suilder {
        data.putDouble(key, value)
        return this
    }

    fun putString(key: String, value: String): Suilder {
        data.putString(key, value)
        return this
    }

    fun putBundle(key: String, value: Bundle): Suilder {
        data.putBundle(key, value)
        return this
    }

    fun putAll(value: Bundle): Suilder {
        data.putAll(value)
        return this
    }


}

