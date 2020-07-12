//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
//        return solution1(nums);
//        return solution2(nums);
        return solution3(nums);
    }

    /**
     * 解法三：迭代，每个元素尝试其在不同位置的组合，Set去重
     * @param nums
     * @return
     */
    private List<List<Integer>> solution3(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Set<List<Integer>> exist = new HashSet<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            while (result.peekFirst().size() == i) {
                List<Integer> current = result.pollFirst();
                //枚举nums[i]插入不同位置带来的组合情况
                for (int j = 0; j <= current.size(); j++) {
                    //减少扩容带来的耗时
                    List<Integer> newList = new ArrayList<>(i + 1);
                    newList.addAll(current.subList(0, j));
                    newList.add(nums[i]);
                    newList.addAll(current.subList(j, current.size()));
                    if (exist.add(newList)) {
                        result.add(newList);
                    }
                }
            }
        }

        return result;
    }

    /**
     * 解法二：将入参数组先排序，回溯选值时剪枝，每一层选值时都选取一个不一样的值
     * @param nums
     * @return
     */
    private List<List<Integer>> solution2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        generate4Solution2(new ArrayList<>(nums.length), new boolean[nums.length], nums, result);
        return result;
    }

    /**
     * 解法二的递归函数
     * @param tmp
     * @param exist
     * @param nums
     * @param result
     */
    private void generate4Solution2(List<Integer> tmp, boolean[] exist, int[] nums, List<List<Integer>> result) {
        if (tmp.size() == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        Integer preNums = null;
        for (int i = 0; i < nums.length; i++) {
            if (exist[i] || (preNums != null && preNums == nums[i])) {
                continue;
            }
            exist[i] = true;
            tmp.add(nums[i]);
            preNums = nums[i];
            generate4Solution2(tmp, exist, nums, result);
            tmp.remove(tmp.size() - 1);
            exist[i] = false;
        }
    }

    /**
     * 解法一：递归，用Set去重
     * @param nums
     * @return
     */
    private List<List<Integer>> solution1(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        generate4Solution1(new ArrayList<>(nums.length), new boolean[nums.length], nums, result);
        return new ArrayList<>(result);
    }

    /**
     * 解法一的递归函数
     * @param tmp
     * @param exist
     * @param nums
     * @param result
     */
    private void generate4Solution1(List<Integer> tmp, boolean[] exist, int[] nums, Set<List<Integer>> result) {
        if (tmp.size() == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!exist[i]) {
                exist[i] = true;
                tmp.add(nums[i]);
                generate4Solution1(tmp, exist, nums, result);
                tmp.remove(tmp.size() - 1);
                exist[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.permuteUnique(new int[]{1,1,2});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
