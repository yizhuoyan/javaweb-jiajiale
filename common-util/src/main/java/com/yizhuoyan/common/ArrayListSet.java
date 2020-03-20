package com.yizhuoyan.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 内部使用ArrayList实现的Set
 * 特征：
 * - 没有去重功能，请使用者保证数据唯一性
 * @param <T>
 */
public class ArrayListSet<T> implements Set<T> {

    private final ArrayList<T> list;

    public ArrayListSet(int capacity) {
        this.list =new ArrayList<>(capacity);
    }

    public ArrayListSet(ArrayList<T> list) {
        this.list =list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override
    public <T>T[] toArray(T[] a) {
        return this.list.toArray(a);
    }

    @Override
    public boolean add(T o) {
        return this.list.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return this.list.remove(o);
    }

    @Override
    public boolean containsAll(Collection c) {
        return this.list.contains(c);
    }

    @Override
    public boolean addAll(Collection c) {
        return this.list.addAll(c);
    }

    @Override
    public boolean retainAll(Collection c) {
        return this.list.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection c) {
        return this.list.removeAll(c);
    }

    @Override
    public void clear() {
        this.list.clear();
    }
}
