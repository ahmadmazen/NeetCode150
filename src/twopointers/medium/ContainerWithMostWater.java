package twopointers.medium;

/**
 * https://leetcode.com/problems/container-with-most-water/
 */

public class ContainerWithMostWater {
    /**
     * Brute force is to test all compinations with two loops x = i, j = x + 1 where i is the index
     * time complexity is O(n^2)
     */
    public static int maxArea(int[] height) {
        int max = 0;
        for(int x = 0; x < height.length - 1; x++){
            for(int j = x + 1 ; j < height.length; j++){
                int area = (j - x) * Math.min(height[x], height[j]);
                max = Math.max(max, area);
            }
        }
        return max;

    }
    public static int maxArea_better(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            int containerLength = right - left;
            int area = containerLength * Math.min(height[left], height[right]);
            max = Math.max(max, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
    public static void main(String[] args) {
//        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
//        System.out.println(maxArea(new int[]{1,1}));


//        System.out.println(maxArea_better(new int[]{1,8,6,2,5,4,8,3,7}));
//        System.out.println(maxArea_better(new int[]{1,1}));
        System.out.println(maxArea_better(new int[]{2,3,4,5,18,17,6}));
    }

}
