package com.alexgwyn.chipedittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;


public class ChipSpan extends ImageSpan {
    private Drawable mChipDrawable;
    private Chip mChip;


    public ChipSpan(Context context, Chip chip) {
        super((ChipDrawable) null);
        mChip = chip;
        mChipDrawable = chip.createChipDrawable(context);
        mChipDrawable.setBounds(0, 0, mChipDrawable.getMinimumWidth(), mChipDrawable.getMinimumHeight());

    }

    @Override
    public Drawable getDrawable() {
        return mChipDrawable;
    }

    public Chip getChip() {
        return mChip;
    }
}
