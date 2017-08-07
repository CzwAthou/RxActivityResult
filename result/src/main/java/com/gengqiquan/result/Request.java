package com.gengqiquan.result;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gengqiquan on 2017/7/4.
 */

public class Request  {
    Intent intent;
    int code;
    public Request(Intent intent, int code) {
        this.intent = intent;
        this.code = code;
    }



}
