package com.johnhiott.stehtosample;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class MainApplication extends Application {

    private static final String PICASSO_CACHE = "picasso-cache";
    private static final long PICASSO_CACHE_SIZE = 100 * 1024 * 1024;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(new MyDumperPluginsProvider())
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new StethoInterceptor());

        File cache = new File(this.getCacheDir(), PICASSO_CACHE);
        if (!cache.exists()) {
            cache.mkdirs();
        }

        client.setCache(new Cache(cache, PICASSO_CACHE_SIZE));

        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttpDownloader(client))
                .build();
        Picasso.setSingletonInstance(picasso);


        ActiveAndroid.initialize(this);


    }


    class MyDumperPluginsProvider implements DumperPluginsProvider {
        @Override
        public Iterable<DumperPlugin> get() {
            ArrayList<DumperPlugin> plugins = new ArrayList<>();
            plugins.add(new MyPlugin());
            for(DumperPlugin plugin:Stetho.defaultDumperPluginsProvider(getApplicationContext()).get()){
                plugins.add(plugin);
            }
            return plugins;
        }
    }
}
