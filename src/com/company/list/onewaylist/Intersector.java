package com.company.list.onewaylist;

import java.util.Iterator;

public class Intersector {
    public static OneWayLinkedList<Integer> intersect(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
        Iterator<Integer> iter1 = list1.iterator();
        Iterator<Integer> iter2 = list2.iterator();

        OneWayLinkedList<Integer> result = new OneWayLinkedList<>();

        Integer node1 = (iter1.hasNext() ? iter1.next() : null);
        Integer node2 = (iter2.hasNext() ? iter2.next() : null);

        while (node1 != null && node2 != null) {
            if (node1 == node2) {
                result.add(node1);
                node1 = iter1.next();
                node2 = iter2.next();
            }
            else if (node1 < node2) {
                node1 = (iter1.hasNext() ? iter1.next() : null);
            }
            else {
                node2 = (iter2.hasNext() ? iter2.next() : null);
            }
        }
        
        return result;
    }
}
