package com.company.stackqueue;

import com.company.exceptions.EmptyQueueException;
import com.company.exceptions.FullQueueException;
import com.company.exceptions.FullStackException;

public class Inverter {

    // Odwróć kolejność elementów w kolejce wykorzystując do tego stos zaimplementowany w klasie ArrayStack
    public static <T> void invert(IQueue<T> queue) throws EmptyQueueException, FullQueueException, FullStackException {
        int size = queue.size();
        ArrayStack<T> stack = new ArrayStack<>(size);
        for (int i = 0; i < size; i++) {
            stack.push(queue.dequeue());
        }
        for (int i = 0; i < size; i++) {
            queue.enqueue(stack.pop());
        }
    }
}
