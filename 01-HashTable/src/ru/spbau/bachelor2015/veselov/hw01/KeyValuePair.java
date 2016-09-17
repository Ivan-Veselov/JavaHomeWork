package ru.spbau.bachelor2015.veselov.hw01;

public class KeyValuePair {
    private String mKey = null;
    private String mValue = null;

    public KeyValuePair() {
    }

    public KeyValuePair(String aKey, String aValue) {
        if (aKey == null) {
            throw new IllegalArgumentException("Key in KeyValuePair must not be null!");
        }

        mKey = aKey;
        mValue = aValue;
    }

    public String key() {
        return mKey;
    }

    public String value() {
        return mValue;
    }

    public String setValue(String aValue) {
        String fPreviousValue = mValue;
        mValue = aValue;

        return fPreviousValue;
    }
}
