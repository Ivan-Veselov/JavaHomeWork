package ru.spbau.bachelor2015.veselov.hw01;

public class HashTable {
    private LinkedList[] mBuckets;

    public HashTable(int aBucketsNumber) {
        if (aBucketsNumber <= 0) {
            throw new IllegalArgumentException("There must be positive number of buckets in HashTable!");
        }

        mBuckets = new LinkedList[aBucketsNumber];
    }

    public int size() {
        // TODO

        return 0;
    }

    public boolean contains(String aKey) {
        return bucket(aKey).contains(aKey);
    }

    public String get(String aKey) {
        return bucket(aKey).get(aKey);
    }

    public String put(String aKey, String aValue) {
        return bucket(aKey).put(aKey, aValue);
    }

    public String remove(String aKey) {
        return bucket(aKey).remove(aKey);
    }

    public void clear() {
        for (LinkedList fBucket : mBuckets) {
            fBucket.clear();
        }
    }

    private LinkedList bucket(String aKey) {
        return mBuckets[aKey.hashCode() % mBuckets.length];
    }
}
