package com.gengqiquan.rxactivityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gengqiquan.result.Result;
import com.gengqiquan.result.RxActivityResult;

import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView) findViewById(R.id.jump);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityResult.with(MainActivity.this).putString("key", "笑一个")
                        .startActivityWithResult(new Intent(MainActivity.this, SecondActivity.class))
                        .subscribe(new Consumer<Result>() {
                            @Override
                            public void accept(Result result) throws Exception {
                                if (result.isOK())
                                    tv.setText(result.data.getStringExtra("msg"));
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", this.getClass().getName());
    }
}
