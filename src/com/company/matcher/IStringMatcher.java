package com.company.matcher;

import java.util.List;

public interface IStringMatcher {
    List<Integer> validShifts(String textToSearch, String patternToFind);
}
