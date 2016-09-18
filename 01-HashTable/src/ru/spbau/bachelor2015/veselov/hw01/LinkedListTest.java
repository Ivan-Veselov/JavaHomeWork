package ru.spbau.bachelor2015.veselov.hw01;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {
    @Test
    public void testIterator() throws Exception {
        LinkedList linkedList = new LinkedList();

        assertNull(linkedList.put("A", "a"));
        assertNull(linkedList.put("B", "b"));
        assertNull(linkedList.put("C", "c"));

        int counter = 0;
        for (KeyValuePair pair : linkedList) {
            switch (counter) {
                case 0:
                    assertEquals("A", pair.key());
                    assertEquals("a", pair.value());
                    break;

                case 1:
                    assertEquals("B", pair.key());
                    assertEquals("b", pair.value());
                    break;

                case 2:
                    assertEquals("C", pair.key());
                    assertEquals("c", pair.value());
                    break;

                default:
                    fail();
            }

            counter++;
        }
    }
}