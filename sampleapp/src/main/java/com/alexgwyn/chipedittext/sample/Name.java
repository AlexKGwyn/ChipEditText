package com.alexgwyn.chipedittext.sample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;

import com.alexgwyn.chipedittext.Chip;

/**
 * Created by Alex on 1/17/16.
 */
public class Name extends Chip {

    private String mName;
    private Bitmap mImage;

    public Name(String name) {
        mName = name;
    }

    @Override
    public String getText() {
        return mName;
    }

    @Override
    public Bitmap getImage(Context context) {
        if (mImage == null) {
            mImage = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        }
        return mImage;
    }

    @Override
    public String toString() {
        return mName;
    }
}
