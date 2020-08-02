//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 示例: 
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
// 
// Related Topics 数组 动态规划 
// 👍 604 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][]map = new int[m][n];

        map[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            map[0][i] = grid[0][i] + map[0][i - 1];
        }

        for (int i = 1; i < m; i++) {
            map[i][0] = grid[i][0] + map[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                map[i][j] = Math.min(map[i - 1][j], map[i][j - 1]) + grid[i][j];
            }
        }

        return map[m - 1][n - 1];

    }
}
//leetcode submit region end(Prohibit modification and deletion)
