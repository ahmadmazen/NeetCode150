package twopointers.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted
 */
public class TwoSum {
    /**
     * very easy problem since the array is sorted
     * very effecient solution could come up with it from the first shoot
     * time complexity O(n) , space complexity O(1)
     */
    public static int[] twoSum(int[] numbers, int target) {
        int S = 0;
        int E = numbers.length - 1;

        while(S < E){
            int sum = numbers[S] + numbers[E];
            if(sum > target){
                E--;
                continue;
            }else if(sum < target){
                S++;
                continue;
            }
            return new int[]{++S, ++E};
        }
        return null;
    }

    public static void main(String[] args) {
        Arrays.stream(twoSum(new int[]{2,3,4}, 6)).forEach(n-> System.out.println(n));
        System.out.println("=========================");
        Arrays.stream(twoSum(new int[]{-1,0}, -1)).forEach(n-> System.out.println(n));
        System.out.println("=========================");
        Arrays.stream(twoSum(new int[]{2,7,11,15}, 9)).forEach(n-> System.out.println(n));
        System.out.println("=========================");

    }
}
