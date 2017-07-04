# RxActivityResult
 Obtain results intent from startActivityForResult() as an Observable.

## use in java 
```
     RxActivityResult.with(MainActivity.this).putString("key", "笑一个")
                        .startActivityWithResult(new Intent(MainActivity.this, SecondActivity.class))
                        .subscribe(new Consumer<Intent>() {
                            @Override
                            public void accept(Intent intent) throws Exception {
                                tv.setText(intent.getStringExtra("msg"));
                            }
                        });
```
## use in kotlin
### commonly
```
            RxResult.with(this)
                    .putString("key","笑一个")
                    .start(Intent(this@MainActivity, SecondActivity::class.java))
                    .subscribe({ intent ->
                        Toast.makeText(this@MainActivity, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show()
                    },
                            {
                                e ->
                                e.printStackTrace()
                            })

```
### anko
```
 startActivityWithResult<ThirdActivity>("key" to "哭一个", "number" to 100)
                    .subscribe({ intent ->
                        Toast.makeText(this@SecondActivity, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show()
                    }) { e ->
                        e.printStackTrace()
                    }
```

## gradle
```
compile 'com.gengqiquan:rxactivityresult:0.0.2'
```
## maven
```
<dependency>
  <groupId>com.gengqiquan</groupId>
  <artifactId>rxactivityresult</artifactId>
  <version>0.0.2</version>
  <type>pom</type>
</dependency>
```
