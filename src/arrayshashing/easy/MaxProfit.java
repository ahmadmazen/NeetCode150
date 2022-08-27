package arrayshashing.easy;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stoc
 */
public class MaxProfit {
    public static long solution(int[] prices) {

        int min = Integer.MAX_VALUE;
        int ans = Integer.MIN_VALUE;
        for (int val : prices) {
            min = Math.min(min, val);
            ans = Math.max(ans, val - min);
        }
        return ans;

    }

    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 1;
        int max = Integer.MIN_VALUE;
        while (right < prices.length) {
            if (prices[right] > prices[left]) {
                int profit = prices[right] - prices[left];
                max = Math.max(profit, max);
            } else {
                left = right;
            }
            right++;
        }
        return max;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{6, 0, -1, 10};
        int[] nums1 = new int[]{13, 10, 8, 4, 10};
        System.out.println(solution(nums));
        System.out.println(solution(nums1));
    }
}
