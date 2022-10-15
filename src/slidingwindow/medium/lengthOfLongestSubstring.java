package slidingwindow.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class lengthOfLongestSubstring {
    /**
     * brute force is to scan all the possible substrings and check the duplicates
     * using nested loops and HashSet to detect duplicates and update the length of longest
     * time complexity: O(n^3), space complexity O(n)
     */
    public static int lengthOfLongestSubstring(String s) {

        int longest = 0;

        for (int x = 0; x < s.length(); x++) {
            for (int j = x + 1; j < s.length(); j++) {
                if (!checkDuplicates(s, x, j)) {
                    longest = Math.max(longest, j - x + 1);
                }
            }
        }
        return longest;
    }

    private static boolean checkDuplicates(String s, int l, int r) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Set<Character> chars = new HashSet<>();
        for (int x = l; x <= r; x++) {
            if (chars.contains(s.charAt(x))) {
                return true;
            }
            chars.add(s.charAt(x));
        }
        return false;
    }

    /**
     * sliding window technique
     * time complexity : O(n), space complexity O(n)
     */
    public static int lengthOfLongestSubstring_slidingwindow(String s) {
        int left = 0;
        int longest = 0;
        Set<Character> chars = new HashSet<>();
        for (int right = 0; right < s.length(); right++) {
            while (chars.contains(s.charAt(right))) {
                chars.remove(s.charAt(left));
                left++;
            }
            chars.add(s.charAt(right));
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring(" "));
        System.out.println(lengthOfLongestSubstring("au"));
        System.out.println(lengthOfLongestSubstring("dvdf"));

    }
}
