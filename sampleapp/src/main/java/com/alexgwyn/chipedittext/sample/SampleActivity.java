package com.alexgwyn.chipedittext.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MultiAutoCompleteTextView;

import com.alexgwyn.chipedittext.Chip;
import com.alexgwyn.chipedittext.ChipEditText;

public class SampleActivity extends AppCompatActivity implements ChipEditText.ChipChangedListener {

    private ChipEditText mChipEditText;
    private SampleAdapter mSampleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mSampleAdapter = new SampleAdapter(this);
        fillAdapter();
        mChipEditText = (ChipEditText) findViewById(R.id.chipEditText);
        mChipEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        mChipEditText.setAdapter(mSampleAdapter);
        mChipEditText.setChipChangedListener(this);
    }

    private void fillAdapter() {
        String[] array = getResources().getStringArray(R.array.names);
        for (String name : array) {
            mSampleAdapter.add(new Name(name));
        }
    }


    @Override
    public void onChipRemoved(Chip chip) {
        Log.d("Removed chip", chip.getText());
    }

    @Override
    public void onChipAdded(Chip chip) {
        Log.d("Added chip", chip.getText());
    }
}
