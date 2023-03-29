package com.company.list.onewaylist;

import java.util.Iterator;

public class Merger {
    public static OneWayLinkedList<Integer> merge(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
        OneWayLinkedList<Integer> result = new OneWayLinkedList<>();
        Iterator<Integer> iterator1 = list1.iterator();
        Iterator<Integer> iterator2 = list2.iterator();
        
        Integer node1 = (iterator1.hasNext() ? iterator1.next() : null);
        Integer node2 = (iterator1.hasNext() ? iterator2.next() : null);
        while (node1 != null || node2 != null) {
            if (node2 == null || (node1 != null && node1 < node2)) {
                result.add(node1);
                node1 = (iterator1.hasNext() ? iterator1.next() : null);
            }
            else {
                result.add(node2);
                node2 = (iterator2.hasNext() ? iterator2.next() : null);
            }
        }
        return result;
    }
}
