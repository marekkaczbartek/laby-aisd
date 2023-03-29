package com.company.list.onewaylist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import com.company.list.IList;

public class OneWayLinkedList<T> implements IList<T> {

    private class Element {
        private T value;
        private Element next = null;

        public Element(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
        public void setValue(T value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }
        public void setNext(Element next) {
            this.next = next;
        }
    }

    private Element head = null;

    public OneWayLinkedList(){}
    @Override
    public void add(T value) {
        // TODO
        Element newElement = new Element(value);
        if (head == null) {
            head = newElement;
        }
        else {
            Element currentElement = head;
            while (currentElement.getNext() != null) {
                currentElement = currentElement.getNext();
            }
            currentElement.setNext(newElement);
        }
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        if (index < 0) {
            throw new NoSuchElementException();
        }
        Element newElement = new Element((value));
        if (index == 0) {
            newElement.setNext(head);
            head = newElement;
        }
        else {
            Element currentElement = head;
            for (int i = 0; i < index-1; i++) {
                if (currentElement.getNext() == null) {
                    throw new NoSuchElementException();
                }
                currentElement = currentElement.getNext();
            }
            newElement.setNext(currentElement.getNext());
            currentElement.setNext(newElement);
        }
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> iterator = this.iterator();
        T current;
        while (iterator.hasNext()) {
            current = iterator.next();
            if (current == value) {
                return true;
            }
            
        }
        return false;
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        if (index < 0 || head == null) {
            throw new NoSuchElementException();
        }
        Iterator<T> iterator = this.iterator();
        T value = iterator.next();
        for (int i = 1; i <= index; i++) {
            if (!iterator.hasNext()) {
                throw new NoSuchElementException();
            }
            value = iterator.next();
        }
        return value;
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        if (index < 0) {
            throw new NoSuchElementException();
        }
        Element currentElement = head;
        for (int i = 0; i < index; i++) {
            if (currentElement.getNext() == null) {
                throw new NoSuchElementException();
            }
            currentElement = currentElement.getNext();
        }
        currentElement.setValue(value);
    }

    @Override
    public int indexOf(T value) {
        Iterator<T> iterator = this.iterator();
        T current;
        int i = 0;
        while (iterator.hasNext()) {
            current = iterator.next();
            if (current == value) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        if (index < 0 || head == null) {
            throw new NoSuchElementException();
        }
        else if (index == 0) {
            T value = head.getValue();
            head = head.getNext();
            return value;
        }
        else {
            Element currentElement = this.head;
            Element next = head.getNext();
            for (int i = 0; i < index - 1; i++) {
                if (currentElement.getNext() == null) {
                    throw new NoSuchElementException();
                }
                next = currentElement.getNext();
                currentElement = next;
            }
            currentElement.setNext(next.getNext());
            return next.getValue();
        } 
    }

    @Override
    public boolean remove(T value) {
        if (head == null) {
            return false;
        }
        else if (head.getValue() == value) {
            head = head.getNext();
            return true;
        }
        Element currentElement = this.head;
        while (currentElement.getNext() != null) {
            Element next = currentElement.getNext();
            if (next.getValue() == value) {
                currentElement.setNext(next.getNext());
                return true;
            }
            currentElement = next;
        }
        return false;
    }

    @Override
    public int size() {
        int result;
        if (this.isEmpty()) {
            result = 0;
        }
        else {
            result = 1;
            Element currentElement = this.head;
            while (currentElement.getNext() != null) {
                result++;
                currentElement = currentElement.getNext();
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new OneWayLinkedListIterator();
    }

    private class OneWayLinkedListIterator implements Iterator<T> {

        Element currentElement;

        OneWayLinkedListIterator(){
            currentElement = head;
        }

        @Override
        public boolean hasNext() {
            // TODO
            return currentElement != null;
        }

        @Override
        public T next() throws NoSuchElementException {
            // TODO
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            T value = currentElement.getValue();
            currentElement = currentElement.getNext();
            return value;
        }
    }
}
