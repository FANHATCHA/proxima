package com.Kone_Fanhatcha_S1803435.proxima.utilities;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Kone Fanhatcha - Student ID: S1803435.
 */
public class VerticalSpacingItemDecorator extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public VerticalSpacingItemDecorator(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        outRect.bottom = verticalSpaceHeight;
    }
}
