package com.Kone_Fanhatcha_S1803435.proxima.adapters;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Kone Fanhatcha - Student ID: S1803435.
 */
public class VerticalSpace extends RecyclerView.ItemDecoration {

    int Space;
    public VerticalSpace(int Space){
        this.Space=Space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=Space;
        outRect.bottom=Space;
        outRect.right=Space;
        if (parent.getChildLayoutPosition(view)==0){
            outRect.top=Space;
        }
    }
}
