package ru.spbau.bachelor2015.veselov.hw06;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

public class MyUnbalancedTreeSet<E> extends AbstractSet<E> implements MyTreeSet<E> {
    public MyUnbalancedTreeSet() {
    }

    public MyUnbalancedTreeSet(Comparator<? super E> comparator) {
    }

    public int size() {
        return 0;
    }

    public boolean add(E e) {
        return false;
    }

    public boolean remove(Object o) {
        return false;
    }

    public E first() {
        return null;
    }

    public E last() {
        return null;
    }

    public E floor(E e) {
        return null;
    }

    public E ceiling(E e) {
        return null;
    }

    public E lower(E e) {
        return null;
    }

    public E higher(E e) {
        return null;
    }

    public Iterator<E> iterator() {
        return null;
    }

    public Iterator<E> descendingIterator() {
        return null;
    }

    public MyTreeSet<E> descendingSet() {
        return null;
    }
}
