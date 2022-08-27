package arrayshashing.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
   https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {
    /*
      my solution
      space complexity is O(n) we used auxiliary space for hashMap,
      time complexity is O(
     */
    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null) { //O(1)
            return false;
        }
        if (s.length() != t.length()) {  //O(1)
            return false;
        }
        Map<Character, Integer> occurencesTable = new HashMap<Character, Integer>();  //O(1)
        for (int i = 0; i < s.length(); i++) { // this loops n times
            char character = s.charAt(i);
            if (occurencesTable.containsKey(character)) {
                occurencesTable.put(character, occurencesTable.get(character) + 1);
            } else {
                occurencesTable.put(character, 1);
            }
        }
        //the best thing here is that the following code in the best case whenever it encounters the first
        // inequality in one of the characters will abort and return false, so no need to continue traversing
        // all the entryset of Map , but untill that happens we might have traversed the most of the entryset to
        // find the inequality so the time complexity in worst case might approach the O(n*m) where n is the length
        // of first string and m is the length of the second string
        for (Map.Entry o : occurencesTable.entrySet()) { // n times
            Character ch = (Character) o.getKey();
            int charOcc = 0;
            for (int x = 0; x < t.length(); x++) { // this loops n * m where m is the length of the second string
                if (ch == t.charAt(x)) {
                    charOcc++;
                }
            }
            if ((Integer) o.getValue() != charOcc) {
                return false;
            }
        }
        return true;

    }

    //in the below we enhanced the time complexity to O(n), the space complexity is abit bigger since wed
    // added a new space for the second string m --  O(n + m)
    public static boolean isAnagram_twoHashMaps(String s, String t) {
        if (s == null || t == null) { //O(1)
            return false;
        }
        if (s.length() != t.length()) {  //O(1)
            return false;
        }
        Map<Character, Integer> occurencesS = new HashMap<Character, Integer>();
        Map<Character, Integer> occurencesT = new HashMap<Character, Integer>();
        for (int x = 0; x < s.length(); x++) {
            char sChar = s.charAt(x);
            char tChar = t.charAt(x);
            occurencesS.put(sChar, occurencesS.getOrDefault(sChar, 0) + 1);
            occurencesT.put(tChar, occurencesT.getOrDefault(tChar, 0) + 1);
        }
        if (!occurencesT.equals(occurencesS)) {
            return false;
        }
        return true;
    }

    /* https://www.youtube.com/watch?v=6C40mfRiTdA
     *  using ascii-code of the characters we can do increment and decrement the occurences in array of characters
     * each iteration we subtract the character from 'a' which will subtract the asciicode of that character
     * from a asciicode (97) , then we will increment the value of that index from the first string, and decrementing
     * the value of that index from the second string
     * so basically in clear terms we add 1 whenever we encounter the character in the String s and subtract 1 whenever
     * we encounter the character in String t .... to end up with array of 0s if both strings are anagrams.
     *time complexity is O(n), space complexity O(n)
     */
    public static boolean isAnagram_constantSpace(String s, String t) {
        if (s == null || t == null) { //O(1)
            return false;
        }
        if (s.length() != t.length()) {  //O(1)
            return false;
        }
        int[] countArray = new int[26]; // 26 is the number of letters in alphapetic
        for (int i = 0; i < s.length(); i++) {
            countArray[s.charAt(i) - 'a']++;
            countArray[t.charAt(i) - 'a']--;
        }
        if (Arrays.stream(countArray).anyMatch(c -> c != 0)) {
            return false;
        }
        return true;
    }

    //other approach is to sort the characters in each string and then check the equality, it's so simple but
    //the sort operation will cost us O(nlogn) depends on the sorting algorithm
    public static boolean isAnagram_Sorting(String s, String t) {
        if (s == null || t == null) { //O(1)
            return false;
        }
        if (s.length() != t.length()) {  //O(1)
            return false;
        }
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        if (!Arrays.equals(sArray, tArray)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram(s, t));
        System.out.println(isAnagram_twoHashMaps(s, t));
        System.out.println(isAnagram_constantSpace(s, t));
        System.out.println(isAnagram_Sorting(s, t));
        s = "rat";
        t = "car";
        System.out.println(isAnagram(s, t));
        System.out.println(isAnagram_twoHashMaps(s, t));
        System.out.println(isAnagram_constantSpace(s, t));
        System.out.println(isAnagram_Sorting(s, t));

    }

}
