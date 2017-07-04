package com.gengqiquan.result;

import android.content.Intent;

/**
 * Created by gengqiquan on 2017/7/4.
 */

public class Result {
    Intent intent;

    public Result(Intent intent, int code) {
        this.intent = intent;
        this.code = code;
    }

    int code;


}
