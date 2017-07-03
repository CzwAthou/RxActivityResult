package com.gengqiquan.rxactivityresult

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.gengqiquan.result.RxResult

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jump.setOnClickListener { view ->
            RxResult.with(this).putString("key","笑一个")
                    .start(Intent(this@MainActivity, SecondActivity::class.java))
                    .subscribe({ intent ->
                        Toast.makeText(this@MainActivity, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show()
                    },
                            {
                                e ->
                                e.printStackTrace()
                            })
        }
    }

    }
}
