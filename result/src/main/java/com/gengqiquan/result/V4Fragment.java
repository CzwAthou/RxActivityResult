package com.gengqiquan.result;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by gengqiquan on 2017/7/3.
 */

public class V4Fragment extends Fragment {
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (request != null) {
                RxActivityResult.post(new Result(data, request.code));
            }
            request = null;
            this.getActivity().getSupportFragmentManager().beginTransaction().detach(this).commit();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    Request request;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (request != null) {
            Intent intent = request.intent;
            startActivityForResult(intent, 0);
        }
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
