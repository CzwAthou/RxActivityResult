package com.gengqiquan.rxactivityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
                        .subscribe(new Consumer<Intent>() {
                            @Override
                            public void accept(Intent intent) throws Exception {
                                tv.setText(intent.getStringExtra("msg"));
                                Toast.makeText(MainActivity.this,
                                        intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
