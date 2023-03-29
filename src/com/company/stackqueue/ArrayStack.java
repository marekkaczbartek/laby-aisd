package com.company.stackqueue;

import com.company.exceptions.FullStackException;
import java.util.EmptyStackException;

public class ArrayStack<T> implements IStack<T>{

    private T[] stack;
    private int index;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        // TODO
        index = 0;
        stack = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public boolean isEmpty() {
        // TODO
        return index == 0;
    }

    @Override
    public boolean isFull() {
        // TODO
        return index == capacity;
    }

    @Override
    public T top() throws EmptyStackException {
        // TODO
        if (this.isEmpty()) throw new EmptyStackException();
        return stack[index-1];
    }

    @Override
    public T pop() throws EmptyStackException {
        // TODO
        if (this.isEmpty()) throw new EmptyStackException();
        T val = stack[index-1];
        stack[index-1] = null;
        index--;
        return val;
    }

    @Override
    public void push(T value) throws FullStackException {
        // TODO
        if (this.isFull()) throw new FullStackException();
        stack[index] = value;
        index++;
    }

    @Override
    public int size() {
        // TODO
        return index;
    }
}
