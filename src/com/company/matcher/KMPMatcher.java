package com.company.matcher;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class KMPMatcher implements IStringMatcher {
    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        // TODO: Zaimplementuj algorytm dopasowywania napisów Knutha-Morrisa-Pratta
        List<Integer> result = new ArrayList<>();
        int[] table = new int[patternToFind.length()];
        for (int i = 0; i < patternToFind.length(); i++) {
            String sub = patternToFind.substring(0, i);
            table[i] = checkMatching(sub);
        }
        int q = 0;
        int i = 1;
        while (i <= textToSearch.length()) {
            if (textToSearch.charAt(i-1) == patternToFind.charAt(q)) {
                q++;
                i++;
            }
            else {
                if (q == 0) {
                    i++;
                }
                q = table[q];

            }
            if (q == patternToFind.length()) {
                result.add(i - q-1);
                q = table[q-1];
            }

        }
        return result;
    }
}
