package com.company.map;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Function;
import com.company.exceptions.DuplicateKeyException;

public class HashMap<TKey, TValue> {

    private class Node {
        private int hash;
        private TKey key;
        private TValue value;

        public Node(int hash, TKey key, TValue value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public TKey getKey() {
            return key;
        }

        public void setKey(TKey key) {
            this.key = key;
        }

        public TValue getValue() {
            return value;
        }

        public void setValue(TValue value) {
            this.value = value;
        }
    }
    private double loadFactor;
    private int size;
    private int initialSize;

    private int num_of_elements = 0;
    private Function<TKey, Integer> hashFunction;

    private LinkedList<Node>[] map;

    public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {
        // TODO: Zainicjuj nową instancję klasy HashMap według podanych parametrów.
        //    InitialSize - początkowy rozmiar HashMap
        //    LoadFactor - stosunek elementów do rozmiaru HashMap po przekroczeniu którego należy podwoić rozmiar HashMap.
        //    HashFunction - funkcja, według której liczony jest hash klucza.
        //       Przykład użycia:   int hash = hashFunction.apply(key);

        this.loadFactor = loadFactor;
        this.size = initialSize;
        this.initialSize = initialSize;
        this.hashFunction = hashFunction;
        this.map = new LinkedList[size];
    }

    private Node getNode(TKey key) {
        int hashCode = hashFunction.apply(key);
        int index = hashCode % size;
        if (map[index] != null) {
            for (Node node : map[index]) {
                if (node.getKey().equals(key) && hashCode == node.getHash()) {
                    return node;
                }
            }
        }
        return null;
    }

    private void checkLoadFactor() {
        if ((double) num_of_elements / size > loadFactor) {
            LinkedList<Node>[] newMap = new LinkedList[size * 2];
            System.arraycopy(map, 0, newMap, 0, size);
            size *= 2;
            this.map = newMap;
        }
    }

    private void addToEnd(TKey key, TValue value) {
        int hashCode = hashFunction.apply(key);
        int index = hashCode % size;
        if (map[index] == null) {
            map[index] = new LinkedList<>();
        }
        map[index].add(new Node(hashCode, key, value));
        num_of_elements++;

        checkLoadFactor();
    }

    public void add(TKey key, TValue value) throws DuplicateKeyException {
        // TODO: Dodaj nową parę klucz-wartość. Rzuć wyjątek DuplicateKeyException, jeżeli dany klucz już istnieje w HashMap.
        Node node = getNode(key);
        if (node != null) {
            throw new DuplicateKeyException();
        }
        addToEnd(key, value);
    }

    public void clear() {
        // TODO: Wyczyść zawartość HashMap.
        for (LinkedList<Node> list : map) {
            if (list != null) {
                list.clear();
            }
        }
        size = initialSize;
        num_of_elements = 0;
    }

    public boolean containsKey(TKey key) {
        // TODO: Sprawdź, czy HashMap zawiera już dany klucz.
        Node node = getNode(key);
        return node != null;
    }

    public boolean containsValue(TValue value) {
        // TODO: Sprawdź, czy HashMap zawiera już daną wartość.
        for (LinkedList<Node> list : map) {
            if (list != null) {
                for (Node node : list) {
                    if (node.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int elements() {
        // TODO: Zwróć liczbę par klucz-wartość przechowywaną w HashMap.
        return num_of_elements;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        // TODO: Pobierz wartość powiązaną z danym kluczem. Rzuć wyjątek NoSuchElementException, jeżeli dany klucz nie istnieje.
        Node node = getNode(key);
        if (node != null) {
            return node.getValue();
        }
        throw new NoSuchElementException();
    }

    public void put(TKey key, TValue value) {
        // TODO: Przypisz daną wartość do danego klucza.
        //   Jeżeli dany klucz już istnieje, nadpisz przypisaną do niego wartość.
        //   Jeżeli dany klucz nie istnieje, dodaj nową parę klucz-wartość.
        Node node = getNode(key);
        if (node != null) {
            node.setValue(value);
        }
        else {
            addToEnd(key, value);
        }
    }

    public TValue remove(TKey key) {
        // TODO: Usuń parę klucz-wartość, której klucz jest równy podanej wartości.
        Node node = getNode(key);
        if (node != null) {
            TValue value = node.getValue();
            int index = node.hashCode() % size;
            map[index].remove(node);
            num_of_elements--;
            return value;
        }
        return null;
    }

    public int size() {
        // TODO: Zwróć obecny rozmiar HashMap.
        return size;
    }
}
