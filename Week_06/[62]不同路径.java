//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 例如，上图是一个7 x 3 的网格。有多少可能的路径？ 
//
// 
//
// 示例 1: 
//
// 输入: m = 3, n = 2
//输出: 3
//解释:
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
// 
//
// 示例 2: 
//
// 输入: m = 7, n = 3
//输出: 28 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 10 ^ 9 
// 
// Related Topics 数组 动态规划


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int uniquePaths(int m, int n) {
//        return solution1(m, n);
        return solution2(m, n);
    }

    /**
     * 一维数组，动态规划
     * 时间：0ms，O(mn)
     * 空间：36.5，O(n)
     * @param m
     * @param n
     * @return
     */
    private int solution2(int m, int n) {
        int[] map = new int[n];

        Arrays.fill(map, 1);
        //一共m层，第一层已经算过了，循环递推 m - 1 次
        for (int i = 1; i < m; i++) {
            //最右边永远都是1，不用参与更新
            for (int j = n - 2; j >= 0; j--) {
                map[j] += map[j + 1];
            }
        }

        return map[0];
    }

    /**
     * 二维数组，动态规划
     * 时间：0ms，O(mn)
     * 空间：36.5，O(mn)
     * @param m
     * @param n
     * @return
     */
    private int solution1(int m, int n) {
        int[][] map = new int[m][n];
        map[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (map[i][j] != 0) {
                    continue;
                }
                int down = i + 1 < m ? map[i + 1][j] : 0;
                int right = j + 1 < n ? map[i][j + 1] : 0;
                map[i][j] = down + right;
            }
        }

        return map[0][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
