package arrayshashing.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        if(nums == null){
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i] , i);
        }
        throw new IllegalArgumentException("No two sums to target");
    }

    public static void main(String[] args) {
        System.out.println( Arrays.toString(twoSum(new int[]{2,7,11,15}, 9)));
        System.out.println( Arrays.toString(twoSum(new int[]{3,2,4}, 6)));
        System.out.println( Arrays.toString(twoSum(new int[]{3,3}, 6)));
    }
}
