package com.johnhiott.stehtosample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.johnhiott.stehtosample.R;
import com.johnhiott.stehtosample.models.Beer;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {
    private ArrayList<Beer> mBeers;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.beer_name) TextView mName;
        @InjectView(R.id.beer_label) ImageView mBeerLabel;
        @InjectView(R.id.parent_view) RelativeLayout mParentView;
        Context mContext;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            mContext = view.getContext();
        }
    }

    public BeerAdapter(ArrayList<Beer> beers) {
        mBeers = beers;
        beers.get(2).save();
    }

    @Override
    public BeerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Beer beer = mBeers.get(position);
        holder.mName.setText(beer.getName());
        if (beer.getLabels() != null) {
            Picasso.with(holder.mContext)
                    .load(beer.getLabels().getLarge())
                    .fit()
                    .noFade()
                    .into(holder.mBeerLabel);
        }else {
            holder.mBeerLabel.setImageDrawable(holder.mContext.getDrawable(R.drawable.beer_head_foam));
        }
        holder.mParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beer.save();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeers.size();
    }
}