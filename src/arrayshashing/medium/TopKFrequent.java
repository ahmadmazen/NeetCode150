package arrayshashing.medium;

import java.sql.Array;
import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequent {
    /*
    * First approach: time complexity O(klogn)
    * space complexity: O(n)
    */
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] ans = new int[k];
        Map<Integer, Integer> seenMap = new HashMap<>();
        for (int x = 0; x < nums.length; x++) {
           seenMap.put(nums[x], seenMap.getOrDefault(nums[x], 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue)
        );
        //each  enqueuing or dequeuing takes O(logn)
        //and since we do that k times so the time complexity O(klogn)
        for(Map.Entry<Integer, Integer> e : seenMap.entrySet()){
            pq.add(e);
            if(pq.size() > k){
                pq.poll();
            }
        }
        int i = k;
        while(!pq.isEmpty()){
           ans[--i] = pq.poll().getKey();
        }
        return ans;
    }
    /**
     * following better approach to decrease the time complexity down to O(n)
     * by adding one more hashTable to store the frequencies as keys and the list of numbers that happen to have this
     * specific frequency
     * then, knowing that the length of input array is the maximum frequency can happen,
     * so we can start a reverse-loop  from the length of input array and check if there is frequency existed
     * in the second table if so will add its list of numbers to the result array..so on..
     *
     */
    public static int[] topKFrequent_better(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> seenMap = new HashMap<>();
        for (int x = 0; x < nums.length; x++) {
            seenMap.put(nums[x], seenMap.getOrDefault(nums[x], 0) + 1);
        }
        Map<Integer, List<Integer>> countTable = new HashMap<>();

        for(Map.Entry<Integer, Integer> e : seenMap.entrySet()){
          countTable.computeIfAbsent(e.getValue(), v-> new ArrayList<>());
          countTable.get(e.getValue()).add(e.getKey());
        }
       for(int x = nums.length; x > 0 && ans.size() < k; x--){
           if(countTable.containsKey(x)){
               ans.addAll(countTable.get(x));
           }
       }
       return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(topKFrequent_better(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }
}
