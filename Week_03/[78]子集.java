//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
//        return solution1(nums);
        return solution2(nums);
    }

    /**
     * 递归法：每个元素尝试放与不放
     * @param nums
     * @return
     */
    private List<List<Integer>> solution2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        recursive4Solution2(nums, result, new ArrayList<>(), 0);
        return result;
    }

    private void recursive4Solution2(int[] nums, List<List<Integer>> result, List<Integer> tmp, int level) {
        if (level == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        recursive4Solution2(nums, result, tmp, level + 1);
        tmp.add(nums[level]);
        recursive4Solution2(nums, result, tmp, level + 1);
        tmp.remove(tmp.size() - 1);
    }

    /**
     * 迭代法：按顺序处理每个元素，追加在之前产生的结果集元素中，产生新的组合方式
     * @param nums
     * @return
     */
    private List<List<Integer>> solution1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int size = result.size();
            for (int j = 0; j < size; j++) {
                List<Integer> element = new ArrayList<>(result.get(j));
                element.add(nums[i]);
                result.add(element);
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
