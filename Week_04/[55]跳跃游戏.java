//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个位置。 
//
// 示例 1: 
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
// 
//
// 示例 2: 
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
// 
// Related Topics 贪心算法 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canJump(int[] nums) {
//        return solution1(nums);
        return solution2(nums);
    }

    /**
     * 当前格子能到达的最远距离之内，每个格子都能达到，所以求能达到的最远距离即可
     * 时间：2ms
     * 空间：41.6 MB
     * @param nums
     * @return
     */
    private boolean solution2(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) {
                //到不了
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }

    /**
     * 每个格子的可能性遍历一次，把能到达的点进行标记
     * 时间：735 ms
     * 空间：41.6 MB
     * @param nums
     * @return
     */
    private boolean solution1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }
        boolean[] arrived = new boolean[nums.length];
        arrived[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if (!arrived[i]) {
                continue;
            }
            for (int j = 1; j <= nums[i] && i + j < nums.length; j++) {
                if (i + j == nums.length - 1) {
                    return true;
                }
                arrived[i + j] = true;
            }
        }

        return false;}


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.canJump(new int[]{3,2,1,0,4});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
