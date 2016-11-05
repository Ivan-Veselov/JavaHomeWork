package ru.spbau.bachelor2015.veselov.hw06;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractSet;
import java.util.Iterator;

class MyTreeSetDescendingView<E> extends AbstractSet<E> implements MyTreeSet<E> {
    private MyTreeSet<E> origin;

    public MyTreeSetDescendingView(MyTreeSet<E> origin) {
        this.origin = origin;
    }

    @Override
    public int size() {
        return origin.size();
    }

    @Override
    public boolean add(@NotNull E element) {
        return origin.add(element);
    }

    @Override
    public boolean remove(@NotNull Object obj) {
        return origin.remove(obj);
    }

    @Override
    public @NotNull E first() {
        return origin.last();
    }

    @Override
    public @NotNull E last() {
        return origin.first();
    }

    @Override
    public @Nullable E floor(@NotNull E element) {
        return origin.ceiling(element);
    }

    @Override
    public @Nullable E ceiling(@NotNull E element) {
        return origin.floor(element);
    }

    @Override
    public @Nullable E lower(@NotNull E element) {
        return origin.higher(element);
    }

    @Override
    public @Nullable E higher(@NotNull E element) {
        return origin.lower(element);
    }

    @Override
    public @NotNull Iterator<E> iterator() {
        return origin.descendingIterator();
    }

    @Override
    public @NotNull Iterator<E> descendingIterator() {
        return origin.iterator();
    }

    @Override
    public @NotNull MyTreeSet<E> descendingSet() {
        return new MyTreeSetDescendingView<> (this);
    }
}
