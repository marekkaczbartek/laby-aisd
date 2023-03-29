package com.company.stackqueue;

import com.company.list.twowaylist.TwoWayLinkedList;
import com.company.exceptions.*;

public class TwoWayLinkedListQueue<T> implements IQueue<T> {

    private TwoWayLinkedList<T> queue;
    private int cap;

    public TwoWayLinkedListQueue(int capacity) {
        // TODO
        queue = new TwoWayLinkedList<>();
        cap = capacity;
    }

    @Override
    public boolean isEmpty() {
        // TODO
        return queue.isEmpty();
    }

    @Override
    public boolean isFull() {
        // TODO
        return queue.size() == cap;
    }

    @Override
    public void enqueue(T value) throws FullQueueException {
        // TODO
        if (this.isFull()) throw new FullQueueException();
        queue.add(value);
    }

    @Override
    public T first() throws EmptyQueueException {
        // TODO
        if (this.isEmpty()) throw new EmptyQueueException();
        return queue.get(0);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        // TODO
        if (this.isEmpty()) throw new EmptyQueueException();
        return queue.removeAt(0);
    }

    @Override
    public int size() {
        // TODO
        return queue.size();
    }
}
