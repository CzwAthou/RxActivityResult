# RxActivityResult
make onActivityResult to be rxjava Observable

## use in java 
```
 RxResult.with(this)
                .putString("key","shuaige")
                .start(new Intent(this ,ThirdActivity.class))
                .subscribe(new Consumer<Intent>() {
                    @Override
                    public void accept(Intent intent) throws Exception {
                        Toast.makeText(ThirdActivity.this, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
                    }
                });
```
## use in kotlin
```
jump.setOnClickListener { view ->
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
        }
```        
## gradle
```
compile 'com.gengqiquan:rxactivityresult:0.0.1'
```
## maven
```
<dependency>
  <groupId>com.gengqiquan</groupId>
  <artifactId>rxactivityresult</artifactId>
  <version>0.0.1</version>
  <type>pom</type>
</dependency>
```
