package com.company.list.twowaylist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<T> {
    private class Element {
        private T value;
        private Element next;
        private Element previous;

        public Element(T value) {
            this.value = value;
            this.next = null;
            this.previous = null;
        }

        public T getValue() {
            return value;
        }
        public Element getNext() {
            return next;
        }
        public Element getPrevious() {
            return previous;
        }
        public void setNext(Element next) {
            this.next = next;
        }
        public void setPrevious(Element previous) {
            this.previous = previous;
        }
        public void setValue(T value) {
            this.value = value;
        }
    }

    private Element head;
    private Element tail;
    private int size;

    public TwoWayLinkedList() {}

    public Element getElement(int index) throws NoSuchElementException{
        if (index < 0 || index > size-1) {
            throw new NoSuchElementException();
        }
        Element current;
        if (index <= (size / 2)) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        }
        else {
            current = tail;
            for (int i = 0; i < size-index-1; i++) {
                current = current.getPrevious();
            }
        }
        return current;
    }

    public Element getElement(T value) throws NoSuchElementException {
        if (head.getValue() == value) return head;
        else if (tail.getValue() == value) return tail;

        Element current = head;
        while (current != null) {
            if (current.getValue() == value) {
                return current;
            }
            current = current.getNext();
        }
        throw new NoSuchElementException();
    }

    public void add(T value) {
        // TODO
        Element newElement = new Element(value);
        if (this.isEmpty()) {
            head = newElement;
            tail = newElement;
        }
        else {
            tail.setNext(newElement);
            newElement.setPrevious(tail);
            tail = newElement;
        }
        size++;
    }

    public void addAt(int index, T value) throws NoSuchElementException {
        // TODO
        Element newElement = new Element(value);
        if (index == 0) {
            newElement.setNext(head);
            if (!this.isEmpty()) head.setPrevious(newElement);
            else tail = newElement;
            head = newElement;
        }
        else if (index == size) {
            tail.setNext(newElement);
            newElement.setPrevious(tail);
            tail = newElement;
        }
        else {
            Element currentElement = getElement(index-1);
            Element nextElement = currentElement.getNext();

            newElement.setNext(nextElement);
            newElement.setPrevious(currentElement);
            nextElement.setPrevious(newElement);
            currentElement.setNext(newElement);
        }
        size++;
    }

    public void clear() {
        // TODO
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(T value) {
        // TODO
        if (head.getValue() == value || tail.getValue() == value) {
            return true;
        }
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            if (iter.next() == value) {
                return true;
            } 
        }
        return false;
    }

    public T get(int index) throws NoSuchElementException {
        // TODO
        return getElement(index).getValue();
    }

    public void set(int index, T value) throws NoSuchElementException {
        // TODO
        Element element = getElement(index);
        element.setValue(value);
    }

    public int indexOf(T value) {
        // TODO
        if (head.getValue() == value) return 0;
        else if (tail.getValue() == value) return size-1;
        int i = 0;
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            if (iter.next() == value) return i;
            i++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return head == null;
        // TODO
    }

    public T removeAt(int index) throws NoSuchElementException {
        // TODO
        T val;
        if (index == 0) {
            val = head.getValue();
            head = head.getNext();
            if (head != null) head.setPrevious(null);
        }
        else if (index == size-1) {
            val = tail.getValue();
            tail = tail.getPrevious();
            tail.setNext(null);
        }       
        else {
            Element element = getElement(index);
            Element prev = element.getPrevious();
            Element next = element.getNext();
            next.setPrevious(prev);
            prev.setNext(next);
            val = element.getValue();
        }
        size--;
        return val;
    }

    public boolean remove(T value) {
        // TODO
        if (head.getValue() == value) {
            head = head.getNext();
            head.setPrevious(null);
            return true;
        }
        else if (tail.getValue() == value) {
            tail = tail.getPrevious();
            tail.setNext(null);
            return true;
        }
        Element current = head;
        while (current != null) {
            if (current.getValue() == value) {
                Element prev = current.getPrevious();
                Element next = current.getNext();
                prev.setNext(next);
                next.setPrevious(prev);
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public int size() {
        // TODO
        return size;
    }

    public Iterator<T> iterator() {
        return new TwoWayLinkedListIterator();
    }

    private class TwoWayLinkedListIterator implements Iterator<T> {
        Element current;

        public TwoWayLinkedListIterator() {
            current = head;
        }
        @Override
        public boolean hasNext() {
            // TODO
            return current != null;
        }

        // public boolean hasPrevious() {
        //     return current.getPrevious() != null;
        // }

        @Override
        public T next() {
            // TODO
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            T value = current.getValue();
            current = current.getNext();
            return value;
        }

        // public T previous() {
        //     if (!this.hasPrevious()) {
        //         throw new NoSuchElementException();
        //     }
        //     current = current.getPrevious();
        //     return current.getValue();
        // }
    }

    public void insert(
            TwoWayLinkedList<T> anotherList,
            int beforeIndex) throws NoSuchElementException {
        // TODO
        Element current = this.getElement(beforeIndex);
        Element beforeCurrent = current.getPrevious();
        current.setPrevious(anotherList.tail);
        anotherList.tail.setNext(current);
        anotherList.head.setPrevious(beforeCurrent);
        if (beforeCurrent == null) {
            head = anotherList.head;
        }
        else {
            beforeCurrent.setNext(anotherList.head);
        }
    }

    public void insert(
            TwoWayLinkedList<T> anotherList,
            T beforeElement) throws NoSuchElementException {
        // TODO
        Element current = this.getElement(beforeElement);
        Element beforeCurrent = current.getPrevious();
        current.setPrevious(anotherList.tail);
        anotherList.tail.setNext(current);
        if (beforeCurrent == null) {
            head = anotherList.head;
        }
        else {
            beforeCurrent.setNext(anotherList.head);
            anotherList.head.setPrevious(beforeCurrent);
        }
    }
}
