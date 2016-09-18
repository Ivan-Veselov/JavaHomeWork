package ru.spbau.bachelor2015.veselov.hw01;

/**
 * A collection of String keys with String values associated with each key.
 * Implemented as extendable hash table on keys.
 */
public class HashTable implements KeyValueMap {
    private LinkedList[] mBuckets;
    private int mSize = 0;

    static private String sNullKeyExceptionMsg = "HashTable key must not be null!";

    private void initializeBuckets(int aBucketsNumber) {
        mBuckets = new LinkedList[aBucketsNumber];
        for (int i = 0; i < mBuckets.length; i++) {
            mBuckets[i] = new LinkedList();
        }
    }

    /**
     * Constructs empty hash table.
     *
     * @param aBucketsNumber a number of buckets in hash table
     */
    public HashTable(int aBucketsNumber) {
        if (aBucketsNumber <= 0) {
            throw new IllegalArgumentException("There must be positive number of buckets in HashTable!");
        }

        initializeBuckets(aBucketsNumber);
    }

    /**
     * Returns size of hash table.
     *
     * @return number of elements currently stored in hash table
     */
    public int size() {
        return mSize;
    }

    /**
     * Checks whether hash table contains element with a given key.
     * Complexity: constant on average; linear in hash table size in worst case
     *
     * @param aKey a key to search for
     * @return true if hash table contains element with key equal to aKey, false otherwise
     */
    public boolean contains(String aKey) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        return bucket(aKey).contains(aKey);
    }

    /**
     * Returns value associated with a given key or null if there is no
     * element with such a key in hash table.
     * Complexity: constant on average; linear in hash table size in worst case
     *
     * @param aKey a key to search for
     * @return value associated with aKey or null if there is no element with key equal to aKey
     */
    public String get(String aKey) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        return bucket(aKey).get(aKey);
    }

    /**
     * Associates a given value with a given key by putting such a pair in hash table or
     * if there is already an element with such a key then replaces previous value
     * with a new one.
     * Complexity: constant on average; linear in hash table size in worst case
     *
     * @param aKey a key which will be put in hash table or which value will be replaced
     * @param aValue a value to associate with aKey
     * @return previous value that was associated with aKey or null if aKey was not presented in hash table before
     */
    public String put(String aKey, String aValue) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        if (size() == mBuckets.length) {
            extend();
        }

        LinkedList fBucket = bucket(aKey);

        int fSizeAlteration = -fBucket.size();
        String fPreviousValue = fBucket.put(aKey, aValue);
        fSizeAlteration += fBucket.size();

        mSize += fSizeAlteration;

        return fPreviousValue;
    }

    /**
     * Removes element with a given key from hash table.
     * Complexity: constant on average; linear in hash table size in worst case
     *
     * @param aKey a key of element that will be removed
     * @return a value associated with aKey or null if there is no element with a key equal to aKey
     */
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

    /**
     * Removes all elements from hash table.
     * Complexity: linear in hash table size
     */
    public void clear() {
        for (LinkedList fBucket : mBuckets) {
            fBucket.clear();
        }

        mSize = 0;
    }

    private LinkedList bucket(String aKey) {
        // Bitwise AND converts negative hashCode to positive value by treating all bits as is
        return mBuckets[(int)((aKey.hashCode() & 0x00000000ffffffffL) % mBuckets.length)];
    }

    private void extend() {
        LinkedList[] fOldBuckets = mBuckets;
        initializeBuckets(fOldBuckets.length * 2 + 1);

        for (LinkedList fBucket : fOldBuckets) {
            for (KeyValuePair fPair : fBucket) {
                bucket(fPair.key()).put(fPair.key(), fPair.value());
            }
        }
    }
}
