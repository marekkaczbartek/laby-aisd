package com.company.matcher;

import java.util.*;

public class AutomatonMatcher implements IStringMatcher {
    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        // TODO: Zaimplementuj algorytm dopasowywania napisów oparty na automacie skończonym
        List<Integer> result = new ArrayList<>();
        List<String> charSet = new ArrayList<>(new HashSet<>(Arrays.asList(patternToFind.split(""))));
        int[][] table = new int[patternToFind.length()+1][charSet.size()];
        for (int i = 0; i <= patternToFind.length(); i++) {
            String letter;
            if (i != patternToFind.length()) {
                letter = String.valueOf(patternToFind.charAt(i));
            }
            else {
                letter = "";
            }
            for (int j = 0; j < charSet.size(); j++) {
                if (letter.equals(charSet.get(j))) {
                    table[i][j] = i + 1;
                }
                else {
                    String toMatch = patternToFind.substring(0, i) + charSet.get(j);
                    table[i][j] = checkMatching(toMatch);
                }
            }
        }
        int state = 0;
        for (int i = 0; i < textToSearch.length(); i++) {
            String letter = String.valueOf(textToSearch.charAt(i));
            if (charSet.contains(letter)) {
                int newState = table[state][charSet.indexOf(letter)];
                if (newState == patternToFind.length()) {
                    result.add(i+1 - patternToFind.length());
                }
                state = newState;
            }
            else {
                state = 0;
            }
        }
        return result;

    }
}
