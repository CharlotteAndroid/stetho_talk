# Stetho
## John Hiott
## @johnhiott

###Cardinal Solutions

---
#Setup Stetho

```gradle
dependencies {
  compile 'com.facebook.stetho:stetho:1.1.1'
  compile 'com.facebook.stetho:stetho-okhttp:1.1.1'
}
```

---
#Setup Stetho

```java
public class MyApplication extends Application {
  public void onCreate() {
    super.onCreate();
    Stetho.initialize(
      Stetho.newInitializerBuilder(this)
        .enableDumpapp(
            Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(
            Stetho.defaultInspectorModulesProvider(this))
        .build());
  }
}
```
---
#OkHttp

```java
OkHttpClient client = new OkHttpClient();
client.networkInterceptors().add(new StethoInterceptor());
```

---
Retrofit

```java
private ConferenceApi() {
    OkHttpClient client = new OkHttpClient();
    client.networkInterceptors().add(new StethoInterceptor());
    mRestAdapter = new RestAdapter.Builder()
            .setClient(new OkClient(client))
            .setEndpoint(API_URL)
            .build();
}
```

Picasso

```java
OkHttpClient client = new OkHttpClient();
client.networkInterceptors().add(new StethoInterceptor());

Picasso picasso = new Picasso.Builder(this)
        .downloader(new OkHttpDownloader(client))
        .build();
Picasso.setSingletonInstance(picasso);
```

---
# Setup dumpapp


```

brew install python3

git clone git@github.com:facebook/stetho.git ~/stetho

export PATH=$PATH:~/stetho/scripts

```

---
#New github

http://github.com/CharlotteAndroid/
