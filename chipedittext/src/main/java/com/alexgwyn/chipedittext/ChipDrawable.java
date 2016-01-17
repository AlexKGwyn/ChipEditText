package com.alexgwyn.chipedittext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextPaint;

/**
 * The class that handles drawing the chip in the edittext
 */
public class ChipDrawable extends android.graphics.drawable.Drawable {
    private String mUserName;
    private int mHeight;
    private int mInnerPadding;
    private TextPaint mTextPaint = new TextPaint();
    private Paint mBackgroundPaint = new Paint();
    private Rect mBackgroundRect = new Rect();
    private RectF mLeftOval = new RectF();
    private Rect mLeftOvalOut = new Rect();
    private RectF mRightOval = new RectF();
    private RoundedBitmapDrawable mUserImage;
    private Context mContext;

    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mPaddingBottom;


    public ChipDrawable(Context context, Bitmap userImage, int backgroundColor, String name, int textColor) {
        this(context, backgroundColor, name, textColor);
        if (userImage != null) {

            mUserImage = RoundedBitmapDrawableFactory.create(context.getResources(), userImage);
            mUserImage.setCircular(true);
        }
    }

    public ChipDrawable(Context context, int backgroundColor, String name, int textColor) {
        mContext = context;
        mHeight = context.getResources().getDimensionPixelSize(R.dimen.chip_height);
        mInnerPadding = context.getResources().getDimensionPixelSize(R.dimen.chip_space);
        mTextPaint.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.chip_text));
        mPaddingLeft = mPaddingRight = context.getResources().getDimensionPixelSize(R.dimen.chip_horizontal_padding);
        mPaddingTop = mPaddingBottom = context.getResources().getDimensionPixelSize(R.dimen.chip_vertical_padding);

        mTextPaint.setColor(textColor);
        mBackgroundPaint.setColor(backgroundColor);
        mUserName = name;
        mTextPaint.setSubpixelText(true);
        mTextPaint.setAntiAlias(true);
        mBackgroundPaint.setAntiAlias(true);
    }


    public void setFont(Typeface font) {
        mTextPaint.setTypeface(font);
        invalidateSelf();
    }

    public void setTextSize(int textSize) {
        mTextPaint.setTextSize(textSize);
        invalidateSelf();
    }

    public void setTextColor(int color) {
        mTextPaint.setColor(color);
        invalidateSelf();
    }

    public void setBackgroundColor(int color) {
        mBackgroundPaint.setColor(color);
        invalidateSelf();
    }

    @Override
    public int getMinimumWidth() {
        return getPaddingLeft() + (int) (getUnpaddedHeight() + mInnerPadding + mTextPaint.measureText(mUserName) + mInnerPadding + mInnerPadding) + getPaddingRight();
    }

    @Override
    public int getIntrinsicHeight() {
        return getMinimumHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        return getMinimumWidth();
    }

    @Override
    public int getMinimumHeight() {
        return mHeight;
    }

    public int getPaddingLeft() {
        return mPaddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        mPaddingLeft = paddingLeft;
    }

    public int getPaddingRight() {
        return mPaddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        mPaddingRight = paddingRight;
    }

    public int getPaddingTop() {
        return mPaddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        mPaddingTop = paddingTop;
    }

    public int getPaddingBottom() {
        return mPaddingBottom;
    }

    public void setPaddingBottom(int paddingBottom) {
        mPaddingBottom = paddingBottom;
    }

    private int getUnpaddedHeight() {
        return mHeight - mPaddingTop - mPaddingBottom;
    }

    private int getUnpaddedWidth() {
        return getMinimumWidth() - mPaddingLeft - mPaddingRight;
    }

    @Override
    public void draw(Canvas canvas) {
        mBackgroundRect.set(getBounds());
        mBackgroundRect.inset((int) (getUnpaddedHeight() / 2.0), 0);
        mBackgroundRect.set(mBackgroundRect.left + mPaddingLeft, mBackgroundRect.top + mPaddingTop, mBackgroundRect.right - getPaddingRight(), mBackgroundRect.bottom - mPaddingBottom);
        mLeftOval.set(getPaddingLeft(), getPaddingTop(), getUnpaddedHeight() + getPaddingLeft(), mHeight - getPaddingBottom());
        int width = getMinimumWidth();
        mRightOval.set(width - getUnpaddedHeight() - getPaddingRight(), getPaddingTop(), width - getPaddingRight(), mHeight - getPaddingBottom());
        canvas.drawRect(mBackgroundRect, mBackgroundPaint);
        canvas.drawArc(mLeftOval, 90, 180, true, mBackgroundPaint);
        canvas.drawArc(mRightOval, 270, 180, true, mBackgroundPaint);
        mLeftOval.round(mLeftOvalOut);
        if (mUserImage != null) {
            mUserImage.setBounds(mLeftOvalOut);
            mUserImage.draw(canvas);
        }
        canvas.drawText(mUserName, getUnpaddedHeight() + getPaddingLeft() + mInnerPadding, (mHeight) / 2.0f + mTextPaint.descent(), mTextPaint);
    }


    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    public void setBitmap(Bitmap bitmap) {
        mUserImage = RoundedBitmapDrawableFactory.create(mContext.getResources(), bitmap);
        mUserImage.setCircular(true);
        invalidateSelf();
    }


}
