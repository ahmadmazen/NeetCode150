package slidingwindow.medium;

/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharReplacement {
    public static int characterReplacement(String s, int k) {
        int most_frequest = 0, longest = 0, left = 0;
        int[] mostFreqCharArray = new int[26];

        for(int right = 0; right < s.length(); right++){
            int rightCharPosition = s.charAt(right) - 'A'; //position of the char in alphapetic will be used as the index of it in the most frequest array;

            mostFreqCharArray[rightCharPosition]++;
            most_frequest = Math.max(most_frequest, mostFreqCharArray[rightCharPosition]);
            int window_size = (right - left) + 1;
            if(window_size - most_frequest > k){ //then will slide the window left boundary one step to the right
                int leftCharPosition = s.charAt(left) - 'A';
                mostFreqCharArray[leftCharPosition]--; // decrement the frequency of the abandoned character
                left++;
            }
            window_size = (right - left) + 1;
           longest = Math.max(longest, window_size);
        }
        return longest;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2));
        System.out.println(characterReplacement("AABABBA", 1));
    }
}
