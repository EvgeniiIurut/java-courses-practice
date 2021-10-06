package edu.java.course.core.task_01;

import java.lang.*;
import java.util.*;

public class StringUtils {

    public static String normalize(String value) {
        return value.toLowerCase();
//        throw new UnsupportedOperationException("Implement it!");
    }

    public static List<String> splitOnLines(String text) {
        System.out.println("new ArrayList<>(Arrays.asList(text.split(\"\\n\")))");
        return new ArrayList<>(Arrays.asList(text.split("\n")));

//        throw new UnsupportedOperationException("Implement it!");
    }

    public static String mergeToSingleString(List<String> lines) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
        }
        return stringBuilder.toString().trim();
//        throw new UnsupportedOperationException("Implement it!");
    }

    public static Map<Character, Integer> countChars(String text) {
        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        char[] strArray = text.toCharArray();
        for (char c : strArray) {
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1);
            } else {
                charCountMap.put(c, 1);
            }
        }
        return charCountMap;
//        throw new UnsupportedOperationException("Implement it!");
    }

    public static String replaceAllDots(String text) {
        return text.replace(".", ",");
//        throw new UnsupportedOperationException("Implement it!");
    }

    public static String reverse(String text) {
        return (new StringBuilder(text).reverse().toString());
//        throw new UnsupportedOperationException("Implement it!");
    }
}

