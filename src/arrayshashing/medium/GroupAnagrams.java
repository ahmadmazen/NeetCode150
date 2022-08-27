package arrayshashing.medium;

import java.lang.reflect.Array;
import java.util.*;

public class GroupAnagrams {
    /**
     * this is the optimized approach, in terms of time complexity since we don't need to do sorting
     * we only need a char[26] to use it to count each character in the string by utilizing the order of
     * alphapetic char (the place of the character in alphapetic sequence and then convert this char[] into
     * String to be the hash key for a hashTable(Map) and the value will be the list of anagram strings
     * time complexity as following:
     * n is the number of strings in the input string[]
     * m is the number of characters in each word
     * so n(26 + m ) --> 26n + nm --> since 26 is constant we drop it --> n + nm -> we remove the lowest order
     * so the final time complexity will be O(nm)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] hash = new char[26];
            for (int x = 0; x < s.length(); x++) {
                hash[s.charAt(x) - 'a']++; // a - a --> 0, c - a--> 2 so on..
            }
            String key = new String(hash);
            map.computeIfAbsent(key, k -> new ArrayList<>());
            map.get(key).add(s);
        }
        result.addAll(map.values());
        return result;
    }

    /*
     * other non-effecient approach - that I tried first as a brute-force solution
     * sorting the characters in each string of input array Of strings, and store the sorted string
     * in hashMap as the key and the value will be the list of its anagrams
     * finally add the map.values to the result list<list<String>..
     * time complexity is O(nlogn) since  we need to do sorting for each string
     */
    public static List<List<String>> groupAnagrams_sorting(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        result.addAll(map.values());
        return result;
    }

    public static void main(String[] args) {

        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagrams(new String[]{""}));
        System.out.println(groupAnagrams(new String[]{"a"}));
    }
}
