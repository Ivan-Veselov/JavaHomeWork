package ru.spbau.bachelor2015.veselov.hw01;

public class KeyValuePair {
    String mKey = null;
    String mValue = null;

    public KeyValuePair() {
    }

    public KeyValuePair(String aKey, String aValue) {
        mKey = aKey;
        mValue = aValue;
    }

    public String key() {
        return mKey;
    }

    public String value() {
        return mValue;
    }
}
