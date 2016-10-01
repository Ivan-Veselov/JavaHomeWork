package ru.spbau.bachelor2015.veselov.hw04;

import org.junit.Test;

public class BinaryTreeSetTest {
    @Test
    public void testConstructor() throws Exception {
        BinaryTreeSet<Integer> set = new BinaryTreeSet<Integer>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd1() throws Exception {
        BinaryTreeSet<Integer> set = new BinaryTreeSet<Integer>();
        set.add(null);
    }

    @Test
    public void testAdd2() throws Exception {
        BinaryTreeSet<Integer> set = new BinaryTreeSet<Integer>();
        set.add(1000);
        set.add(999);
        set.add(999);
        set.add(1000);
        set.add(1001);
        set.add(1002);
        set.add(998);
        set.add(1000);
    }

    @Test
    public void contains() throws Exception {

    }

    @Test
    public void size() throws Exception {

    }

}