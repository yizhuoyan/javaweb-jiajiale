package com.yizhuoyan.common;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 内部使用ArrayList实现的Map
 * 特征：
 * - 只读map，不支持删除
 * - 不支持修改，即不去重，重复调用put，会放入多个键值对
 * - 仅用于快速迭代
 * - 不支持null键，支持null值
 */
public class ArrayListMap implements Map<String,Object> {
    private final ArrayList<KeyValueEntry> entryList;

    private ArrayListMap(int capacity) {
        this.entryList=new ArrayList<>(capacity);
    }

    @Override
    public int size() {
        return this.entryList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.entryList.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        if(key==null)return false;
        for(KeyValueEntry entry:entryList){
            if(entry.key.equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(KeyValueEntry entry:entryList){
            if(value==null?entry.value==null:value.equals(entry.value)){
                return true;
            }
        }
        return false;
    }

    /**
     * 通过key获取值，如果有重复key，则返回找到的第一个
     * @param key
     * @return
     */
    @Override
    public Object get(Object key) {
        if(key==null)throw  new NullPointerException();
        for(KeyValueEntry entry:entryList){
            if(entry.key.equals(key)){
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public Object put(String key, Object value) {
        if(key==null)throw  new NullPointerException();
        entryList.add(new KeyValueEntry(key,value));
        return null;
    }

    @Override
    public Object remove(Object key) {
        throw  new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        for(Map.Entry<? extends String, ?> entry:m.entrySet()){
            this.put(entry.getKey(),entry.getValue());
        }
    }

    @Override
    public void clear() {
        this.entryList.clear();
    }

    @Override
    public Set<String> keySet() {
        ArrayListSet<String> keyset=new ArrayListSet<>(this.size());
        for(KeyValueEntry entry:entryList){
            keyset.add(entry.key);
        }
        return keyset;
    }

    @Override
    public Collection<Object> values() {
        return entryList.stream().map(e->e.value)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return new ArrayListSet(this.entryList);
    }

    private static class KeyValueEntry implements Map.Entry<String,Object>{
        private final String key;
        private Object value;

        public KeyValueEntry(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return this.key;
        }
        @Override
        public Object getValue() {
            return this.value;
        }

        @Override
        public Object setValue(Object value) {
            Object oldValue=this.value;
            this.value=value;
            return oldValue;
        }
    }
}
