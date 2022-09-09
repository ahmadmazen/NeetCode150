package arrayshashing.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 */
public class ProductOfArrayExceptSelf {
    /**
     *Brute-force solution is to use nested loop to get the product of the array except the current nums[i]
     * the time complexity is O(n^2) so we need to enhance that to reach O(n)
     */
    public static int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] ans = new int[nums.length];
        for (int x = 0; x < nums.length; x++) {
            int result = 1;
            for (int j = 0; j < nums.length ; j++) {
                if(x == j){
                    continue;
                }
                result = result * nums[j];
            }
            ans[x] = result;
        }
        return ans;
    }

    /**
     * with the help of division operation we can find better solution and simpler, given in the problem definition
     * that's not allowed to use the division, I will implement it just for learning purposes and understanding the problem
     * more...
     * you have to handle the case of zero..
     */
    public static int[] productExceptSelf_ByDivision(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] ans = new int[nums.length];
        int totalMultiplication = 1;
        for (int x = 0; x < nums.length; x++) {
            if(nums[x] == 0){
                continue;
            }
            totalMultiplication *= nums[x];
        }
        for(int i =0; i < nums.length; i++){
            if(nums[i] == 0){
                ans[i] = nums[i];
            }else{
                ans[i] = totalMultiplication / nums[i];
            }

        }
        return ans;
    }

    /**
     * optimal solution lies on the concept of each element in the output array will be
     * the product of leftside elements multiplied by the rightside elements
     * so we can traverse the input array twice once to get the left products and once more for the rightside products
     * then the final output array we get it by multiply each element from both arrays
     * that will give us nice time complexity O(3n) --> O(n) after dropping the constants
     * but space complexity ; O(n) since we would be using extra space to store the leftside products and rightside products
     * so to enhance that we can do our traversal in place by using the same output array..
     *
     */
    public static int[] _productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] ans = new int[nums.length];
        int product = 1;
        for(int x = 0; x < nums.length; x++){
            ans[x] = product;
            product *= nums[x];
        }
        product = 1;
        for(int x = nums.length - 1; x >= 0; x--){
            ans[x] = product * ans[x];
            product *= nums[x];
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(_productExceptSelf(new int[]{1, 2, 3, 4})));

        System.out.println(Arrays.toString(_productExceptSelf(new int[]{-1, 1, 0, -3, 3})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{-1, 1, 0, -3, 3})));

    }
}
