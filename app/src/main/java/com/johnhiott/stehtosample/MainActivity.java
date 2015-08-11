package com.johnhiott.stehtosample;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.google.gson.Gson;

import com.johnhiott.stehtosample.adapters.BeerAdapter;
import com.johnhiott.stehtosample.models.Beer;
import com.johnhiott.stehtosample.models.BreweryResponse;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.recycler_view) RecyclerView mRecyclerView;

    private OkHttpClient mOkHttpClient;
    private ArrayList<Beer> mBeers;
    private BeerAdapter mBeerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.networkInterceptors().add(new StethoInterceptor());
        getBrewery();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayBeers() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBeerAdapter = new BeerAdapter(mBeers);
                mRecyclerView.setAdapter(mBeerAdapter);
            }
        });
    }

    private void getBrewery() {

        String url = "https://api.brewerydb.com/v2/brewery/pcrctk/beers?key=3bb962de353dad9727a647d2167236dd";

        Request request = new Request.Builder().url(url).build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Gson gson = new Gson();
                BreweryResponse breweryResponse = gson.fromJson(response.body().string(), BreweryResponse.class);
                mBeers = breweryResponse.getBeers();
                displayBeers();
            }
        });
    }

    public class BeerViewHolder {
        @InjectView(R.id.beer_name) TextView mBeerName;
        @InjectView(R.id.beer_label) ImageView mBeerLabel;
    }
}
