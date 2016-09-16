package ru.spbau.bachelor2015.veselov.hw01;

public class HashTable implements KeyValueMap {
    private LinkedList[] mBuckets;
    private int mSize = 0;

    static private String sNullKeyExceptionMsg = "HashTable key must not be null!";
    static private String sNonPositiveBucketsNumberExceptionMsg
                          = "There must be positive number of buckets in HashTable!";

    public HashTable(int aBucketsNumber) {
        if (aBucketsNumber <= 0) {
            throw new IllegalArgumentException(sNonPositiveBucketsNumberExceptionMsg);
        }

        mBuckets = new LinkedList[aBucketsNumber];
        for (int i = 0; i < mBuckets.length; i++) {
            mBuckets[i] = new LinkedList();
        }
    }

    public int size() {
        return mSize;
    }

    public boolean contains(String aKey) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        return bucket(aKey).contains(aKey);
    }

    public String get(String aKey) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        return bucket(aKey).get(aKey);
    }

    public String put(String aKey, String aValue) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        LinkedList fBucket = bucket(aKey);

        int fSizeAlteration = -fBucket.size();
        String fPreviousValue = fBucket.put(aKey, aValue);
        fSizeAlteration += fBucket.size();

        mSize += fSizeAlteration;
        return fPreviousValue;
    }

    public String remove(String aKey) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        LinkedList fBucket = bucket(aKey);

        int fSizeAlteration = -fBucket.size();
        String fPreviousValue = fBucket.remove(aKey);
        fSizeAlteration += fBucket.size();

        mSize += fSizeAlteration;
        return fPreviousValue;
    }

    public void clear() {
        for (LinkedList fBucket : mBuckets) {
            fBucket.clear();
        }

        mSize = 0;
    }

    private LinkedList bucket(String aKey) {
        return mBuckets[aKey.hashCode() % mBuckets.length];
    }
}
