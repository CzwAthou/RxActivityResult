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
            RxResult.post(data);
            this.getActivity().getSupportFragmentManager().beginTransaction().detach(this).commit();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Intent intent = getArguments().getParcelable("data");
        startActivityForResult(intent, 0);
    }
}
