package com.gengqiquan.result;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by gengqiquan on 2017/7/4.
 */

public class Result {
    public Intent data;
    int requestCode;
    public int resultCode;

    public Result(Intent intent, int requestCode, int resultCode) {
        this.data = intent;
        this.requestCode = requestCode;
        this.resultCode = resultCode;
    }

    public boolean isOK() {
        return resultCode == Activity.RESULT_OK;
    }
}
