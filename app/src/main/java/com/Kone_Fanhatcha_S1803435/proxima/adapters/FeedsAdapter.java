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

//    private OnRssFeedListener mOnRssFeedListener;
//
//    public FeedsAdapter(List<FeedItem> mRssFeedModels, OnRssFeedListener onRssFeedListener) {
//        this.mRssFeedModels = mRssFeedModels;
//        this.mOnRssFeedListener = onRssFeedListener;
//    }

    //    public FeedsAdapter(Context context, List<FeedItem> rssFeedModels, FeedModelViewHolder.OnRssFeedListener onRssFeedListener) {
//        mRssFeedModels = rssFeedModels;
//        this.context=context;
//        this.mOnRssFeedListener = onRssFeedListener;
//    }

    public FeedsAdapter(Context context, List<FeedItem> rssFeedModels){
        mRssFeedModels = rssFeedModels;
        this.context=context;
    }

//    @Override
//    public FeedModelViewHolder onCreateViewHolder(ViewGroup parent, int type) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_rss_feed, parent, false);
//        FeedModelViewHolder holder = new FeedModelViewHolder(v, mOnRssFeedListener);
//        return holder;
//    }

    @NonNull
    @Override
    public FeedModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rss_feed, parent, false);
//        return new FeedModelViewHolder(view, mOnRssFeedListener, parent.getContext());
        return new FeedModelViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(FeedModelViewHolder holder, int position) {

        Utils utils = new Utils();

        final FeedItem rssFeedModel = mRssFeedModels.get(position);
//        ((TextView)holder.rssFeedView.findViewById(R.id.magnitudeText)).setText(utils.magnitude(rssFeedModel.getTitle()));
//        ((TextView)holder.rssFeedView.findViewById(R.id.titleText)).setText(utils.eventTitle(rssFeedModel.getTitle()));
//       ((TextView)holder.rssFeedView.findViewById(R.id.pubDateText)).setText(utils.eventDate(rssFeedModel.getPubDate()));
//        ((TextView)holder.rssFeedView.findViewById(R.id.descriptionText)).setText(rssFeedModel.getDescription());
//        ((TextView)holder.rssFeedView.findViewById(R.id.linkText)).setText(rssFeedModel.getLink());
        //((TextView)holder.rssFeedView.findViewById(R.id.pubDateText)).setText(rssFeedModel.getLink());

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

        //        TextView textTile;
//        OnRssFeedListener mOnRssFeedListener;
        public FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;

        }

//        public FeedModelViewHolder(View itemView, OnRssFeedListener onRssFeedListener, Context context) {
        public FeedModelViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            //timestamp = itemView.findViewById(R.id.note_timestamp);
//            textTile = itemView.findViewById(R.id.titleText);
            titleText = itemView.findViewById(R.id.titleText);
            magnitudeText = itemView.findViewById(R.id.magnitudeText);
            pubDateText = itemView.findViewById(R.id.pubDateText);
//            mOnRssFeedListener = onRssFeedListener;
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
//            mOnRssFeedListener.onRssFeedClicked(getAdapterPosition());
            Intent intent = new Intent(this.context, FeedDetails.class);
            intent.putExtra("selected_rss_feed", mRssFeedModels.get(getAdapterPosition()));

            context.startActivity(intent);
        }


    }

//    public interface OnRssFeedListener{
//        void onRssFeedClicked(int position);
//    }

    @Override
    public int getItemCount() {
        return mRssFeedModels.size();
    }
}
