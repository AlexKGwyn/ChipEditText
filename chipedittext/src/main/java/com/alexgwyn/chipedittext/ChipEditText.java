package com.alexgwyn.chipedittext;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ChipEditText extends MultiAutoCompleteTextView implements TextWatcher {
    private ChipChangedListener mChipChangedListener;

    public ChipEditText(Context context) {
        super(context);
        init();
    }

    public ChipEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChipEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ChipEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        addTextChangedListener(this);
    }


    private Spannable getChipSpan(Chip chip) {
        SpannableString spannableString = new SpannableString(",");
        spannableString.setSpan(new ChipSpan(getContext(), chip), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    protected final CharSequence convertSelectionToString(Object selectedItem) {
        if (isPerformingCompletion()) {
            Chip chip = convertSelectionToChip(selectedItem);
            if (chip != null) {
                onChipAdded(chip);
                return getChipSpan(chip);
            }
        }
        return selectedItem.toString();
    }

    /**
     * Override this method to convert items in the autocomplete adapter into chips. You do not need this method if the item in your adapter extends Chip
     *
     * @param selectedItem The item to convert into a chip
     * @return The converted chip
     */
    protected Chip convertSelectionToChip(Object selectedItem) {
        if (selectedItem instanceof Chip) {
            return (Chip) selectedItem;
        }
        return new SimpleChip(selectedItem.toString(), null);
    }

    private ArrayList<ChipSpan> getChipSpans() {
        return new ArrayList<>(Arrays.asList(getText().getSpans(0, getText().length(), ChipSpan.class)));
    }


    /**
     * Get the list of all the chips in this view
     *
     * @return All the chips in this view
     */
    public ArrayList<Chip> getChips() {
        ArrayList<Chip> ret = new ArrayList<Chip>();
        for (ChipSpan chipSpans : Arrays.asList(getText().getSpans(0, getText().length(), ChipSpan.class))) {
            ret.add(chipSpans.getChip());
        }
        return ret;
    }


    /**
     * Add a chip to this view
     *
     * @param chip The chip to add
     */
    public void addChip(Chip chip) {
        Editable text = getText();
        Spannable span = getChipSpan(chip);
        text.append(span);
        setText(text);
        setSelection(text.length());
        onChipAdded(chip);
    }


    /**
     * Get a chip for certain text
     *
     * @param text The text to find in a chip
     * @return The chip that matches, or null
     */
    @Nullable
    public Chip getChip(String text) {
        for (ChipSpan chipSpan : getChipSpans()) {
            if (chipSpan.getChip().getText().equals(text)) {
                return chipSpan.getChip();
            }
        }
        return null;
    }


    /**
     * Remove the chip from this view that matches the given text
     *
     * @param text The text of the chip to remove
     * @return Whether or not a chip was removed
     */
    public boolean removeChip(String text) {
        for (ChipSpan chipSpan : getChipSpans()) {
            if (chipSpan.getChip().getText().equals(text)) {
                int start = getText().getSpanStart(chipSpan);
                Editable editText = getText().replace(start, start + 1, "");
                setText(editText);
                if (mChipChangedListener != null) {
                    mChipChangedListener.onChipRemoved(chipSpan.getChip());
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Remove the chip from this view
     *
     * @param chip The chip to remove
     * @return Whether or not a chip was removed
     */
    public boolean removeChip(Chip chip) {
        for (ChipSpan chipSpan : getChipSpans()) {
            if (chipSpan.getChip().equals(chip)) {
                int start = getText().getSpanStart(chipSpan);
                Editable editText = getText().replace(start, start + 1, "");
                setText(editText);
                if (mChipChangedListener != null) {
                    mChipChangedListener.onChipRemoved(chipSpan.getChip());
                }
                return true;
            }
        }
        return false;
    }


    /**
     * Set a listener for when a chip is added or removed
     *
     * @param chipChangedListener The listener
     */
    public void setChipChangedListener(ChipChangedListener chipChangedListener) {
        mChipChangedListener = chipChangedListener;
    }


    private void onChipRemoved(Chip chip) {
        if (mChipChangedListener != null) {
            mChipChangedListener.onChipRemoved(chip);
        }
    }

    private void onChipAdded(Chip chip) {
        if (mChipChangedListener != null) {
            mChipChangedListener.onChipAdded(chip);
        }
    }

    @Override
    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (after < count) {
            if (s instanceof Spanned) {
                ChipSpan[] removed = ((Spanned) s).getSpans(start, start + count, ChipSpan.class);
                for (ChipSpan removedSpan : removed) {
                    onChipRemoved(removedSpan.getChip());
                }
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    public interface ChipChangedListener {
        void onChipRemoved(Chip chip);

        void onChipAdded(Chip chip);
    }


}
