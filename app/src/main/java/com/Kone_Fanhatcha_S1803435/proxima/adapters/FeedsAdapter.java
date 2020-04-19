package com.Kone_Fanhatcha_S1803435.proxima.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Kone_Fanhatcha_S1803435.proxima.FeedDetails;
import com.Kone_Fanhatcha_S1803435.proxima.Models.FeedItem;
import com.Kone_Fanhatcha_S1803435.proxima.R;

import java.util.ArrayList;
import java.util.List;

import com.Kone_Fanhatcha_S1803435.proxima.utilities.Utils;

/**
 * Created by Kone Fanhatcha - Student ID: S1803435.
 */
public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedModelViewHolder>{

    private static final String TAG = "FeedsAdapter";
    private List<FeedItem> mRssFeedModels = new ArrayList<>();
    Context context;


    public FeedsAdapter(Context context, List<FeedItem> rssFeedModels){
        mRssFeedModels = rssFeedModels;
        this.context=context;
    }


    @NonNull
    @Override
    public FeedModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rss_feed, parent, false);
        return new FeedModelViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(FeedModelViewHolder holder, int position) {

        Utils utils = new Utils();

        final FeedItem rssFeedModel = mRssFeedModels.get(position);
        holder.magnitudeText.setText(utils.magnitude(rssFeedModel.getTitle()));
        holder.titleText.setText(utils.eventTitle(rssFeedModel.getTitle()));
        holder.pubDateText.setText(utils.eventDate(rssFeedModel.getPubDate()));
    }


    public class FeedModelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View rssFeedView;
        private Context context;

        TextView magnitudeText;
        TextView titleText;
        TextView pubDateText;

        public FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;

        }

        public FeedModelViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            titleText = itemView.findViewById(R.id.titleText);
            magnitudeText = itemView.findViewById(R.id.magnitudeText);
            pubDateText = itemView.findViewById(R.id.pubDateText);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            Intent intent = new Intent(this.context, FeedDetails.class);
            intent.putExtra("activity_feed_details", mRssFeedModels.get(getAdapterPosition()));
            context.startActivity(intent);
        }


    }

    @Override
    public int getItemCount() {
        return mRssFeedModels.size();
    }
}
