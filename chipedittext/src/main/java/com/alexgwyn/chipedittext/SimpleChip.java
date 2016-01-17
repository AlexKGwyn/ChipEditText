package com.alexgwyn.chipedittext;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * An example chip that uses the name and image passed in the constructor
 */
public class SimpleChip extends Chip {
    private String mName;
    private Bitmap mBitmap;

    public SimpleChip(String name, Bitmap bitmap) {
        mName = name;
        mBitmap = bitmap;
    }

    @Override
    public String getText() {
        return mName;
    }

    @Override
    public Bitmap getImage(Context context) {
        return mBitmap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleChip chip = (SimpleChip) o;

        return !(mName != null ? !mName.equals(chip.mName) : chip.mName != null);

    }

    @Override
    public int hashCode() {
        return mName != null ? mName.hashCode() : 0;
    }
}
