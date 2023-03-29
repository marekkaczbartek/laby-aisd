package com.company.list.twowaylist;

import java.util.Iterator;

public class Distincter {
    public static TwoWayLinkedList<Integer> distinct(TwoWayLinkedList<Integer> list)
    {
        // TODO: Zwróć nową listę zawierającą unikalne wartości w liście źródłowej.
        // Możesz założyć, że lista na wejściu jest posortowana.
        // Przykład: [1, 1, 2, 3, 3] -> [1, 2, 3]
        TwoWayLinkedList<Integer> result = new TwoWayLinkedList<>();
        Iterator<Integer> iter = list.iterator();
        if (list.isEmpty()) return list;
        Integer current = iter.next();
        while (iter.hasNext()) {
            Integer prev = current;
            current = iter.next();
            if (prev != current) {
                result.add(prev);
            }
        }
        result.add(current);
        return result;
    }
}
