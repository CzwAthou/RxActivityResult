package com.gengqiquan.result;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

/**
 * Created by gengqiquan on 2017/7/3.
 */

public class AppFragment extends Fragment {
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        RxActivityResult.post(new Result(data, request.code, resultCode));
        request = null;
        this.getActivity().getFragmentManager().beginTransaction().detach(this).commit();
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
