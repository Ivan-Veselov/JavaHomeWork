package ru.spbau.bachelor2015.veselov.hw01;

public class KeyValuePair {
    private final String key;
    private String value = null;

    public KeyValuePair() {
        key = null;
    }

    public KeyValuePair(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException("Key in KeyValuePair must not be null!");
        }

        this.key = key;
        this.value = value;
    }

    public String key() {
        return key;
    }

    public String value() {
        return value;
    }

    public String setValue(String value) {
        String fPreviousValue = this.value;
        this.value = value;

        return fPreviousValue;
    }
}
