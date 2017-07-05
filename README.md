# RxActivityResult
 Obtain results intent from startActivityForResult() as an Observable.
 Use fragemnt as proxy

[rxjava1 to 1.x](https://github.com/gengqiquan/RxActivityResult/tree/1.x)

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
call in activity or fragment
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
compile 'com.gengqiquan:Rx2ActivityResult:0.0.1'
```
## maven
```
<dependency>
  <groupId>com.gengqiquan</groupId>
  <artifactId>Rx2ActivityResult</artifactId>
  <version>0.0.1</version>
  <type>pom</type>
</dependency>
```
