# ChipEditText
A view that can display chips as set out by the design guidelines 

# Gradle Dependency (jCenter)

Easily reference the library in your Android projects using this dependency in your module's `build.gradle` file:

```Gradle
dependencies {
    compile 'com.alexgwyn:chipedittext:1.0.0'
}
```

# Basic Usage
Basic usage is similar to usage for an `AutocompleteEditText`. You need to create an adapter to map your model to the choices in the `ChipEditText`

```java
mChipEditText = (ChipEditText) findViewById(R.id.chipEditText);
mChipEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
mChipEditText.setAdapter(mSampleAdapter);
mChipEditText.setChipChangedListener(this);
```
See the sample project for a full example of usage. 
