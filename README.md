# RxActivityResult
 Obtain results intent from startActivityForResult() as an Observable.
 
 Use fragemnt as proxy

[rxjava1 to 1.x](https://github.com/gengqiquan/RxActivityResult/tree/1.x)

## use in java 
```
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
```
### use in kotlin with anko
call in activity or fragment
```
 startActivityWithResult<ThirdActivity>("key" to "哭一个", "number" to 100)
                    .filter { it.isOK }
                    .subscribe({ result ->
                        Toast.makeText(this@SecondActivity, result.data.getStringExtra("msg"), Toast.LENGTH_SHORT).show()
                    }) { e ->
                        e.printStackTrace()
                    }
```

## gradle
```
compile 'com.gengqiquan:Rx2ActivityResult:0.0.4'
```
## maven
```
<dependency>
  <groupId>com.gengqiquan</groupId>
  <artifactId>Rx2ActivityResult</artifactId>
  <version>0.0.4</version>
  <type>pom</type>
</dependency>
```
