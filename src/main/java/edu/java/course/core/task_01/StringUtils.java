package edu.java.course.core.task_01;

import java.lang.*;
import java.util.*;

public class StringUtils {

    public static String normalize(String value) {
        return value.toLowerCase();
    }

    public static List<String> splitOnLines(String text) {
        return Arrays.asList(text.split("\n"));
    }

    public static String mergeToSingleString(List<String> lines) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : lines
        ) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public static Map<Character, Integer> countChars(String text) {
        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        char[] strArray = text.toCharArray();
        for (char c : strArray) {
            charCountMap.compute(c, (key, value) -> value == null ?  1 : ++value);
        }
        return charCountMap;
    }

    public static String replaceAllDots(String text) {
        return text.replace(".", ",");
    }

    public static String reverse(String text) {
        return new StringBuilder(text).reverse().toString();
    }
}

