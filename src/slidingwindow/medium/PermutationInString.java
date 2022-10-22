package slidingwindow.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/permutation-in-string/
 */
public class PermutationInString {
    /**
     * The idea behind this approach is that one string will be a permutation of another string only
     * if both of them contain the same characters the same number of times.
     * In order to check this, we can sort the two strings and compare them.
     * We sort the short string s1s1 and all the substrings of s2s2, sort them and compare them with the sorted s1s1 string.
     * If the two match completely, s1s1's permutation is a substring of s2s2, otherwise not.
     * Complexity Analysis
     * let l1 be the length of string s1, let l2 be the length of string s2
     * Time complexity: O(l1 log(l1) + (l2 - l1). l1 log(l1)
     * Space complexity: O(l1) sortedArray of chars is used.
     */
    public static boolean checkInclusion(String s1, String s2) {

        String sortedS1 = sortString(s1);
        for (int x = 0; x < s2.length() - s1.length(); x++) {
            if (sortString(s2.substring(x, x + s1.length())).equals(sortedS1)) {
                return true;
            }
        }

        return false;
    }

    private static String sortString(String s) {
        char[] stringChars = s.toCharArray();
        Arrays.sort(stringChars);
        return new String(stringChars);
    }

    /**
     * using HashMap: sliding window -- hashMap can also be replaced with arr[26] to count the occurences
     * As discussed above, one string will be a permutation of another string only if both of them contain the same characters with the same frequency.
     * We can consider every possible substring in the long string s2s2 of the same length as that of s1s1 and check the frequency of occurence of the characters appearing in the two.
     * If the frequencies of every letter match exactly,
     * then only s1s1's permutation can be a substring of s2s2.
     * <p>
     * In order to implement this approach, instead of sorting and then comparing the elements for equality,
     * we make use of a hashmap s1maps1map which stores the frequency of occurence of all the characters in the short string s1s1.
     * We consider every possible substring of s2s2 of the same length as that of s1s1, find its corresponding hashmap as well,
     * namely s2maps2map. Thus, the substrings considered can be viewed as a window of length as that of s1s1 iterating over s2s2.
     * If the two hashmaps obtained are identical for any such window,
     * we can conclude that s1s1's permutation is a substring of s2s2, otherwise not.
     * Time complexity:O(l1 + 26l1(l2-l1)
     * Space complexity: O(1)
     */
    public static boolean checkInclusion_hash(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        Map<Character, Integer> s1Map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            s1Map.put(s1.charAt(i), s1Map.getOrDefault(s1.charAt(i), 0) + 1);
        }
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            Map<Character, Integer> s2Map = new HashMap<>();
            for (int j = 0; j < s1.length(); j++) {
                s2Map.put(s2.charAt(i + j), s2Map.getOrDefault(s2.charAt(i + j), 0) + 1);
            }

            if (compareMaps(s1Map, s2Map)) {
                return true;
            }
        }

        return false;
    }

    private static boolean compareMaps(Map<Character, Integer> s1Map, Map<Character, Integer> s2Map) {
        for (Character ch : s1Map.keySet()) {
            if (s1Map.get(ch) - s2Map.getOrDefault(ch, -1) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(checkInclusion("ab", "eidbaooo"));
//
//        System.out.println(checkInclusion("ab", "eidboaoo"));
//
//        System.out.println(checkInclusion("ab", "ab"));

//===========approacth 2 ===============
//        System.out.println(checkInclusion_hash("ab", "eidbaooo"));
//
//        System.out.println(checkInclusion_hash("ab", "eidboaoo"));
//
//        System.out.println(checkInclusion_hash("ab", "ab"));

        System.out.println(checkInclusion_hash("adc", "dcda"));
    }
}
