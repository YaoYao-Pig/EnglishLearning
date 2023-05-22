package com.example.englishlearning.enity;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemDecor extends RecyclerView.ItemDecoration {
    private int space;

    public ItemDecor(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if(parent.getChildLayoutPosition(view) != 0) {
            outRect.top = space;
        }
    }
}