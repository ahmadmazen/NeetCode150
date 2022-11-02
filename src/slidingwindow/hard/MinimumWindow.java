package slidingwindow.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindow {
    /**
     * https://leetcode.com/problems/minimum-window-substring/solution/
     */
    public static String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        Map<Character, Integer> tCountMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tCountMap.put(c, tCountMap.getOrDefault(c, 0) + 1);
        }
        int requiredMatch = tCountMap.size();
        int matched = 0;
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int startOfSubstring = 0;
        Map<Character, Integer> windowMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            Character currentChar = s.charAt(right);
            windowMap.put(currentChar, windowMap.getOrDefault(currentChar, 0) + 1);
            if (tCountMap.containsKey(currentChar) && tCountMap.get(currentChar).intValue() == windowMap.get(currentChar).intValue()) {
                matched++;
            }
            while (left <= right && requiredMatch == matched) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    startOfSubstring = left;
                }

                currentChar = s.charAt(left);
                windowMap.put(currentChar, windowMap.get(currentChar) - 1);
                if (tCountMap.containsKey(currentChar) && tCountMap.get(currentChar).intValue() > windowMap.get(currentChar).intValue()) {
                    matched--;
                }
                left++;
            }
        }
        if (minLength > s.length()) {
            return "";
        }

        return s.substring(startOfSubstring, startOfSubstring + minLength);
    }
    private static String sortString(String s) {
        char[] stringChars = s.toCharArray();
        Arrays.sort(stringChars);
        return new String(stringChars);
    }
    public static void main(String[] args) {
      //  System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        String s = "0123456789567";
        System.out.println(s.substring(9, 13));
    }
}
