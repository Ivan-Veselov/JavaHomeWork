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
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull E last() {
        throw new UnsupportedOperationException();
    }

    @Override
    public @Nullable E floor(@NotNull E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public @Nullable E ceiling(@NotNull E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public @Nullable E lower(@NotNull E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public @Nullable E higher(@NotNull E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull MyTreeSet<E> descendingSet() {
        return new MyTreeSetDescendingView<> (this);
    }
}
