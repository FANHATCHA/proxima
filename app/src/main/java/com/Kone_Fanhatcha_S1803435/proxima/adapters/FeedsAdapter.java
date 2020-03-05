package com.Kone_Fanhatcha_S1803435.proxima.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Kone_Fanhatcha_S1803435.proxima.Models.FeedItem;
import com.Kone_Fanhatcha_S1803435.proxima.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kone Fanhatcha - Student ID: S1803435.
 */
public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedModelViewHolder>{


    private List<FeedItem> mRssFeedModels;
    Context context;

    public static class FeedModelViewHolder extends RecyclerView.ViewHolder {
        private View rssFeedView;

        public FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;
        }
    }

    public FeedsAdapter(Context context,List<FeedItem> rssFeedModels) {
        mRssFeedModels = rssFeedModels;
        this.context=context;
    }

    @Override
    public FeedModelViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rss_feed, parent, false);
        FeedModelViewHolder holder = new FeedModelViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeedModelViewHolder holder, int position) {
        final FeedItem rssFeedModel = mRssFeedModels.get(position);
        ((TextView)holder.rssFeedView.findViewById(R.id.titleText)).setText(rssFeedModel.getTitle());
        ((TextView)holder.rssFeedView.findViewById(R.id.descriptionText)).setText(rssFeedModel.getDescription());
        ((TextView)holder.rssFeedView.findViewById(R.id.linkText)).setText(rssFeedModel.getLink());
        //((TextView)holder.rssFeedView.findViewById(R.id.pubDateText)).setText(rssFeedModel.getLink());
    }

    @Override
    public int getItemCount() {
        return mRssFeedModels.size();
    }

}
