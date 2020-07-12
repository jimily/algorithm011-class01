//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        generate(new ArrayList<>(nums.length + 1), new boolean[nums.length], nums, result);
        return result;
    }
    private void generate(List<Integer> tmp, boolean[] exist, int[] nums, List<List<Integer>> result) {
        if (tmp.size() == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!exist[i]) {
                exist[i] = true;
                tmp.add(nums[i]);
                generate(tmp, exist, nums, result);
                tmp.remove(tmp.size() - 1);
                exist[i] = false;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
