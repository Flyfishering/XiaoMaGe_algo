package com.mj;

public class BruteForce02 {
    public static int indexOf(String text, String pattern) {
        if (text == null || pattern == null) return -1;
        char[] textChars = text.toCharArray();
        int tlen = textChars.length;
        if (tlen == 0) return -1;
        char[] patternChars = pattern.toCharArray();
        int plen = patternChars.length;
        if (plen == 0) return -1;
        if (tlen < plen) return -1;

        int pi = 0, ti = 0;
        while (pi < plen && ti <= tlen - plen) {
            if (textChars[ti + pi] == patternChars[pi]) {
                pi++;
            } else {
                pi = 0;
                ti++;
            }
        }
        return (pi == plen) ? ti : -1;
    }

}
