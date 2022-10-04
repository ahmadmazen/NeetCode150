package twopointers.medium;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/3sum/
 */
public class ThreeSum {
    /**
     * brute-force solution is to have three nested loops to check all the possible triplet compinations
     * that will cost us O(n^3) time complexity
     * didn't pass all leetcode test cases ex - -4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0
     */
    public static List<List<Integer>> threeSum_bf(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return res;
        }
        for (int x = 0; x <= nums.length - 3; x++) {
            for (int j = x + 1; j <= nums.length - 2; j++) {
                for (int k = j + 1; k <= nums.length - 1; k++) {
                    int sum = nums[x] + nums[j] + nums[k];
                    if (sum == 0) {
                        List<Integer> triplet = Arrays.asList(nums[x], nums[j], nums[k]);
                        if (!tripleAddedBefore(res, triplet)) {
                            res.add(triplet);
                        }
                    }
                }
            }
        }
        return res;
    }

    private static boolean tripleAddedBefore(List<List<Integer>> res, List<Integer> triple) {
        return res.stream().anyMatch(l -> l.containsAll(triple));
    }

    /**
     * time complexity O(n^2)
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        LinkedList<List<Integer>> sol = new LinkedList<List<Integer>>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int target = 0 - nums[i];
                int left = i + 1;
                int right = nums.length - 1;

                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        ArrayList<Integer> miniSol = new ArrayList<>();
                        miniSol.add(nums[i]);
                        miniSol.add(nums[left]);
                        miniSol.add(nums[right]);
                        sol.add(miniSol);
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return sol;
    }

    public static void main(String[] args) {
        // threeSum(new int[]{0,0,0}).stream().forEach(triple -> triple.forEach(e -> System.out.println(e)));

        threeSum(new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0}).
                stream()
                .forEach(triple -> triple.
                        forEach(e -> System.out.println(e)));
        threeSum(new int[]{-1, 0, 1, 2, -1, -4}).stream().forEach(triple -> triple.forEach(e -> System.out.println(e)));
//        System.out.println("===================================");
//        threeSum(new int[]{0,1,1}).stream().forEach(triple-> triple.forEach(e-> System.out.println(e)));
//
//        System.out.println("===================================");
//        threeSum(new int[]{0,0,0}).stream().forEach(triple-> triple.forEach(e-> System.out.println(e)));

    }
}
