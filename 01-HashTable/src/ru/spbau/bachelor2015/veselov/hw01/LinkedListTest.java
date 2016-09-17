package ru.spbau.bachelor2015.veselov.hw01;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {
    @Test
    public void testIterator() throws Exception {
        LinkedList fLinkedList = new LinkedList();

        assertEquals(null, fLinkedList.put("A", "a"));
        assertEquals(null, fLinkedList.put("B", "b"));
        assertEquals(null, fLinkedList.put("C", "c"));

        int counter = 0;
        for (KeyValuePair fPair : fLinkedList) {
            switch (counter) {
                case 0:
                    assertEquals("A", fPair.key());
                    assertEquals("a", fPair.value());
                    break;

                case 1:
                    assertEquals("B", fPair.key());
                    assertEquals("b", fPair.value());
                    break;

                case 2:
                    assertEquals("C", fPair.key());
                    assertEquals("c", fPair.value());
                    break;

                default:
                    fail();
            }

            counter++;
        }
    }
}