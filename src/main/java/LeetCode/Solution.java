package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @version: 1.0
 * @description:
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: zhiwei.liao
 * @create: 2020-11-30 10:46:26
 **/
public class Solution {
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int i1 = target - nums[i];
            Integer integer = map.get(i1);
            if (integer != null && !integer.equals(i)) {
                res[0] = i;
                res[1] = integer;
                return res;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] ints = Solution.twoSum(nums, target);
        System.out.println();
    }
}
