package twopointers.hard;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * https://www.youtube.com/watch?v=ZI2z5pq0TqA
 */
public class TrappingRainWater {
    /**
     * 1st approach : with extra space to hold the max left, max right then taking the min of both and subtracting the height
     * formula : min(max(left), max(right)) - height[i] --> then the summition
     * this approach is O(n) space complexity
     */
    public static int trap(int[] height) {
      int res = 0;
      int length = height.length;
      int max_left[] = new int[length];
      int max_right[] = new int[length];
      int max = 0;
      //filling the max height in the left of each element
      for(int x = 0; x < length; x++){
          max = Math.max(height[x], max);
          max_left[x] = max;
      }
      max = 0;
        //filling the max height in the right of each element
        for(int x = length - 1; x >= 0; x--){
            max = Math.max(height[x], max);
            max_right[x] = max;
        }
       // talking the minimum height (left, right) at each position then subtracting it from the height of this position
        //to count the amount of water can be trapped at this position.
        for(int x = 0; x < length; x++){
           res = res + (Math.min(max_left[x], max_right[x]) - height[x]);
        }

        return res;
    }

    /**
     * 2nd approach:
     * with O(1) space complexity using two pointers
     *
     */
    public static int trap_twopointers(int[] height) {
        int res = 0;
        int left = 0, right = height.length - 1 ;
        int max_left = height[left], max_right = height[right];

        if(height.length == 0){
            return  res;
        }
        while(left < right){
            if(max_left < max_right){
                left++;
                max_left = Math.max(max_left, height[left]);
                res += max_left - height[left];
            }else{
                right--;
                max_right = Math.max(max_right, height[right]);
                res += max_right - height[right];
            }

        }
        return res;

    }
    public static void main(String[] args) {
       // System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trap_twopointers(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
