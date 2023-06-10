package com.company.matcher;

import java.util.List;

public interface IStringMatcher {
    List<Integer> validShifts(String textToSearch, String patternToFind);
    default int checkMatching(String text) {
        int result = 0;
        int i = 1;
        String front;
        String back;
        while (i <= text.length()/2) {
            front = text.substring(0, i);
            back = text.substring(text.length()-i);
            if (front.equals(back)) {
                result = i;
            }
            i++;
        }
        return result;
    }
}
