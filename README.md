# RxActivityResult
 Obtain results intent from startActivityForResult() as an Observable.
 
 Use fragemnt as proxy

[rxjava2 to 2.x](https://github.com/gengqiquan/RxActivityResult/tree/2.x)

## use in java 
```
     RxActivityResult.with(MainActivity.this).putString("key", "笑一个")
                            .acceptCancel(true)//need deal with Activity.cancel or intent==null
                            .startActivityWithResult(new Intent(MainActivity.this, SecondActivity.class))
                            .subscribe(new Subscriber<Intent>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onNext(Intent intent) {
                                    tv.setText(intent.getStringExtra("msg"));
                                }
                            });
```

### use in kotlin with anko
call in activity or fragment
```
 startActivityWithResult<ThirdActivity>("key" to "哭一个", "number" to 100, acceptCancel = true)
                     .subscribe({ intent ->
                         Toast.makeText(this@SecondActivity, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show()
                     }) { e ->
                         e.printStackTrace()
                     }
```

## gradle
```
compile 'com.gengqiquan:RxActivityResult:0.0.4'
```
## maven
```
<dependency>
  <groupId>com.gengqiquan</groupId>
  <artifactId>RxActivityResult</artifactId>
  <version>0.0.4</version>
  <type>pom</type>
</dependency>
```
