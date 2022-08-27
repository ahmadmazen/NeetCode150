package arrayshashing.easy;

import java.util.HashSet;
import java.util.Set;

/*
   problem definition https://leetcode.com/problems/contains-duplicate/
 */
//time complexity O(n), space complexity O(n)
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }
        Set<Integer> unique = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(!unique.add(nums[i])){
                return true;
            }
        }
        return false;
    }
}
