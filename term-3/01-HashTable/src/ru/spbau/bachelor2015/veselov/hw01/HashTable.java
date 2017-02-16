package ru.spbau.bachelor2015.veselov.hw01;

/**
 * A collection of String keys with String values associated with each key.
 * Implemented as extendable hash table on keys.
 */
public class HashTable implements KeyValueMap {
    private LinkedList[] buckets;
    private int size = 0;

    private static final String NULL_KEY_EXCEPTION_MSG = "HashTable key must not be null!";

    private void initializeBuckets(int bucketsNumber) {
        buckets = new LinkedList[bucketsNumber];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList();
        }
    }

    /**
     * Constructs empty hash table.
     *
     * @param bucketsNumber a number of buckets in hash table
     */
    public HashTable(int bucketsNumber) {
        if (bucketsNumber <= 0) {
            throw new IllegalArgumentException("There must be positive number of buckets in HashTable!");
        }

        initializeBuckets(bucketsNumber);
    }

    /**
     * Returns size of hash table.
     *
     * @return number of elements currently stored in hash table
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether hash table contains element with a given key.
     * Complexity: constant on average; linear in hash table size in worst case
     *
     * @param key a key to search for
     * @return true if hash table contains element with key equal to key, false otherwise
     */
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException(NULL_KEY_EXCEPTION_MSG);
        }

        return bucket(key).contains(key);
    }

    /**
     * Returns value associated with a given key or null if there is no
     * element with such a key in hash table.
     * Complexity: constant on average; linear in hash table size in worst case
     *
     * @param key a key to search for
     * @return value associated with key or null if there is no element with key equal to key
     */
    public String get(String key) {
        if (key == null) {
            throw new IllegalArgumentException(NULL_KEY_EXCEPTION_MSG);
        }

        return bucket(key).get(key);
    }

    /**
     * Associates a given value with a given key by putting such a pair in hash table or
     * if there is already an element with such a key then replaces previous value
     * with a new one.
     * Complexity: constant on average; linear in hash table size in worst case
     *
     * @param key a key which will be put in hash table or which value will be replaced
     * @param value a value to associate with key
     * @return previous value that was associated with key or null if key was not presented in hash table before
     */
    public String put(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException(NULL_KEY_EXCEPTION_MSG);
        }

        if (size() == buckets.length) {
            extend();
        }

        LinkedList bucket = bucket(key);

        int sizeAlteration = -bucket.size();
        String previousValue = bucket.put(key, value);
        sizeAlteration += bucket.size();

        size += sizeAlteration;

        return previousValue;
    }

    /**
     * Removes element with a given key from hash table.
     * Complexity: constant on average; linear in hash table size in worst case
     *
     * @param key a key of element that will be removed
     * @return a value associated with key or null if there is no element with a key equal to key
     */
    public String remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException(NULL_KEY_EXCEPTION_MSG);
        }

        LinkedList bucket = bucket(key);

        int sizeAlteration = -bucket.size();
        String previousValue = bucket.remove(key);
        sizeAlteration += bucket.size();

        size += sizeAlteration;
        return previousValue;
    }

    /**
     * Removes all elements from hash table.
     * Complexity: linear in hash table size
     */
    public void clear() {
        for (LinkedList bucket : buckets) {
            bucket.clear();
        }

        size = 0;
    }

    private LinkedList bucket(String key) {
        // Bitwise AND converts negative hashCode to positive value by treating all bits as is
        return buckets[(int)((key.hashCode() & 0x00000000ffffffffL) % buckets.length)];
    }

    private void extend() {
        LinkedList[] oldBuckets = buckets;
        initializeBuckets(oldBuckets.length * 2 + 1);

        for (LinkedList bucket : oldBuckets) {
            for (KeyValuePair pair : bucket) {
                bucket(pair.key()).put(pair.key(), pair.value());
            }
        }
    }
}
