package ru.spbau.bachelor2015.veselov.hw01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListKeyValueMapTest extends KeyValueMapTest {
    protected KeyValueMap createMap() {
        return new LinkedList();
    }
}