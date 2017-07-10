package com.gengqiquan.result

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import rx.Observable
import java.io.Serializable


/**
 * Created by gengqiquan on 2017/6/15.
 */


inline fun <reified T : Activity> Context.startActivityWithResult(vararg params: Pair<String, Any>): Observable<Intent>
        = RxKtResult.with(this).startActivityWithResult(Intent(this, T::class.java), *params)

fun Bundle.pair(params: Array<out Pair<String, Any?>>): Bundle {
    val bundle = this
    params.forEach {
        val value = it.second
        when (value) {
            null -> bundle.putSerializable(it.first, null as Serializable?)
            is Int -> bundle.putInt(it.first, value)
            is Long -> bundle.putLong(it.first, value)
            is CharSequence -> bundle.putCharSequence(it.first, value)
            is String -> bundle.putString(it.first, value)
            is Float -> bundle.putFloat(it.first, value)
            is Double -> bundle.putDouble(it.first, value)
            is Char -> bundle.putChar(it.first, value)
            is Short -> bundle.putShort(it.first, value)
            is Boolean -> bundle.putBoolean(it.first, value)
            is Serializable -> bundle.putSerializable(it.first, value)
            is Bundle -> bundle.putBundle(it.first, value)
            is Parcelable -> bundle.putParcelable(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> bundle.putCharSequenceArrayList(it.first, value as ArrayList<CharSequence>)
                value.isArrayOf<String>() -> bundle.putStringArrayList(it.first, value as ArrayList<String>)
                value.isArrayOf<Parcelable>() -> bundle.putParcelableArrayList(it.first, value as ArrayList<out Parcelable>)
                else -> throw Exception("bundle extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            is IntArray -> bundle.putIntArray(it.first, value)
            is LongArray -> bundle.putLongArray(it.first, value)
            is FloatArray -> bundle.putFloatArray(it.first, value)
            is DoubleArray -> bundle.putDoubleArray(it.first, value)
            is CharArray -> bundle.putCharArray(it.first, value)
            is ShortArray -> bundle.putShortArray(it.first, value)
            is BooleanArray -> bundle.putBooleanArray(it.first, value)
            else -> throw Exception("bundle extra ${it.first} has wrong type ${value.javaClass.name}")
        }

    }
    return bundle
}

@SuppressLint("NewApi")
fun Bundle.pairs(vararg params: Pair<String, Any>): Bundle {
    val bundle = this
    params.forEach {
        val value = it.second
        when (value) {
            null -> bundle.putSerializable(it.first, null as Serializable?)
            is Int -> bundle.putInt(it.first, value)
            is Long -> bundle.putLong(it.first, value)
            is CharSequence -> bundle.putCharSequence(it.first, value)
            is String -> bundle.putString(it.first, value)
            is Float -> bundle.putFloat(it.first, value)
            is Double -> bundle.putDouble(it.first, value)
            is Char -> bundle.putChar(it.first, value)
            is Short -> bundle.putShort(it.first, value)
            is Boolean -> bundle.putBoolean(it.first, value)
            is Serializable -> bundle.putSerializable(it.first, value)
            is Bundle -> bundle.putBundle(it.first, value)
            is Parcelable -> bundle.putParcelable(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> bundle.putCharSequenceArrayList(it.first, value as ArrayList<CharSequence>)
                value.isArrayOf<String>() -> bundle.putStringArrayList(it.first, value as ArrayList<String>)
                value.isArrayOf<Parcelable>() -> bundle.putParcelableArrayList(it.first, value as ArrayList<out Parcelable>)
                else -> throw Exception("bundle extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            is IntArray -> bundle.putIntArray(it.first, value)
            is LongArray -> bundle.putLongArray(it.first, value)
            is FloatArray -> bundle.putFloatArray(it.first, value)
            is DoubleArray -> bundle.putDoubleArray(it.first, value)
            is CharArray -> bundle.putCharArray(it.first, value)
            is ShortArray -> bundle.putShortArray(it.first, value)
            is BooleanArray -> bundle.putBooleanArray(it.first, value)
            else -> throw Exception("bundle extra ${it.first} has wrong type ${value.javaClass.name}")
        }

    }
    return bundle
}