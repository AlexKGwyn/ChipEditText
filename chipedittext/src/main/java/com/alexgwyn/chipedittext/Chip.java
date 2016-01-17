package com.alexgwyn.chipedittext;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

/**
 * The class that represents a chip inside an edittext
 */
public abstract class Chip {
    /**
     * The text displayed in the chip
     *
     * @return The text inside a chip
     */
    public abstract String getText();

    /**
     * The image to display in the chip
     *
     * @param context The context to use to load the image
     * @return The image for the chip
     */
    public abstract Bitmap getImage(Context context);

    /**
     * A method you can override to use a custom chip
     *
     * @param context The context to work in
     * @return A drawable to display as a chip
     */
    public android.graphics.drawable.Drawable createChipDrawable(Context context) {
        int backgroundColor = ContextCompat.getColor(context, R.color.chip_default_background);
        int textColor = ContextCompat.getColor(context, R.color.chip_default_text);
        return new ChipDrawable(context, getImage(context), backgroundColor, getText(), textColor);
    }

}
