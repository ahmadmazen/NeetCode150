package slidingwindow.easy;

public class BestTimeBuySell {
    /**
     * brute force solution
     * try all the compinations and maximize the profit
     * time complexity is O(n^2), space complexity O(1)
     */
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int x = 0; x < prices.length - 1; x++) {
            for (int j = x + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[x];
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        return maxProfit;
    }

    /**
     * 2nd approach : sliding window = we will have two pointers left = 0; right = 1
     * this is the initial state of our sliding window, while right boundary is inside the range of array
     * we check if the element at the left bigger than the one at right if so this is not the situation we would maximize
     * the profit, so we move the left boundary to the right and right pointer to right as if we pushed our sliding window
     * otherwise (left lesser than right) so we take the differnce in a variable (max_profit) and keep expanding the window from
     * the right (right++) untill we reach the boundary (prices.length - 1 )
     * time complxity : O(n), space complexity : O(1)
     */
    public static int maxProfit_slidingWindow(int[] prices) {
        int maxProfit = 0;
        int left = 0, right = left + 1;
        while (right < prices.length) {
            if (prices[left] > prices[right]) {
                left = right;
                right = left + 1;
            } else {
                maxProfit = Math.max(maxProfit, prices[right] - prices[left]);
                right++;
            }

        }
        return maxProfit;
    }

    /**
     * 3rd approach: one pass through the entire array, at each element checking the minimum price and updating the temp var of min_price
     * and evaluting the max_profit by subtracting the current element from the min_price so far and update the max_profit if it's
     * bigger profit than the previous one and so on ...
     */
    public static int maxProfit_onePass(int[] prices) {
        int min_price = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int x = 0; x < prices.length; x++) {
            min_price = Math.min(min_price, prices[x]);
            maxProfit = Math.max(maxProfit, prices[x] - min_price);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
//        System.out.println("========brute force approach ====================");
//        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
//        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
//        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
//        System.out.println("========onePass approach ====================");
//        System.out.println(maxProfit_onePass(new int[]{7, 1, 5, 3, 6, 4}));
//        System.out.println(maxProfit_onePass(new int[]{7, 6, 4, 3, 1}));
//        System.out.println(maxProfit_onePass(new int[]{7, 6, 4, 3, 1}));
        System.out.println("========sliding window approach ====================");
        System.out.println(maxProfit_slidingWindow(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit_slidingWindow(new int[]{7, 6, 4, 3, 1}));
        System.out.println(maxProfit_slidingWindow(new int[]{7, 6, 4, 3, 1}));

    }
}
