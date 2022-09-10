package arrayshashing.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 */
public class LongestConsecutiveSequence {
    /**
     * brute-force solution .. traverse the array and for each number we lookup the entire array searching for the next number if found
     * we count it and go further looking up the arr for the next and so on until no more next found,
     * so we are exhausting the search space with three loops nested..
     * the solution has bad time complexity O(n^3)  -- space complexity = o(1)
     */
    public static int longestConsecutive_bruteforce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int longest = 1;
        for (int num : nums) {
            int currentNum = num;
            int currentLongest = 1;

            while (arrayContains(nums, currentNum + 1)) {
                currentLongest += 1;
                currentNum += 1;

            }
            longest = Math.max(longest, currentLongest);
        }
        return longest;
    }

    private static boolean arrayContains(int[] nums, int num) {
        for (int x = 0; x < nums.length; x++) {
            if (num == nums[x]) {
                return true;
            }
        }
        return false;
    }

    /**
     * with the help of sorting we can improve the time complexity to O(nlogn)
     * Space complexity : O(1) since we sorted the array in place
     */
    public static int longestConsecutive_sorting(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int longest = 1;
        int currentLogest = 1;
        for (int x = 1; x < nums.length; x++) {
            if (nums[x] != nums[x - 1]) {
                if (nums[x] == nums[x - 1] + 1) {
                    currentLogest += 1;
                } else {
                    longest = Math.max(longest, currentLogest);
                    currentLogest = 1;
                }
            }
        }
        return Math.max(longest, currentLogest);
    }

    /**
     * Approach 3: HashSet and Intelligent Sequence Building
     * Intuition
     * =========
     * It turns out that our initial brute force solution was on the right track,
     * but missing a few optimizations necessary to reach O(n) time complexity.
     * Algorithm
     *==========
     * This optimized algorithm contains only two changes from the brute force approach:
     * the numbers are stored in a HashSet (or Set, in Python) to allow O(1) lookups,
     * and we only attempt to build sequences from numbers that are not already part of a longer sequence.
     * This is accomplished by first ensuring that the number that would immediately precede the current number in a sequence is not present,
     * as that number would necessarily be part of a longer sequence.
     *
     *
     *  Time complexity : O(n)O(n).
     *=============================
     * Although the time complexity appears to be quadratic due to the while loop nested within the for loop,
     * closer inspection reveals it to be linear.
     * Because the while loop is reached only when currentNum marks the beginning of a sequence (i.e. currentNum-1 is not present in nums),
     * the while loop can only run for nn iterations throughout the entire runtime of the algorithm.
     * This means that despite looking like O(n*n) complexity, the nested loops actually run in O(n + n) = O(n) time.
     * All other computations occur in constant time, so the overall runtime is linear.
     *
     * Space complexity : O(n).
     *=============================
     * In order to set up O(1) containment lookups, we allocate linear space for a hash table to store the O(n)numbers in nums.
     * Other than that, the space complexity is identical to that of the brute force solution.
     */
    public static int longestConsecutive_hashset(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
       Set<Integer> numberSet = new HashSet<>();
        for(int num : nums){
            numberSet.add(num);
        }
        int longest = 1;
        for (int num : nums) {
            if(!numberSet.contains(num - 1)){
                int count = 1;
                while(numberSet.contains(num + 1)){
                    num++;
                    count++;
                }
               longest = Math.max(count, longest);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive_bruteforce(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(longestConsecutive_bruteforce(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));

        System.out.println("============================================");

        System.out.println(longestConsecutive_sorting(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(longestConsecutive_sorting(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));

        System.out.println("============================================");

        System.out.println(longestConsecutive_hashset((new int[]{100, 4, 200, 1, 3, 2})));
        System.out.println(longestConsecutive_hashset(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));

    }
}
