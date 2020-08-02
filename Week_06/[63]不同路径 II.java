//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
//
// 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 说明：m 和 n 的值均不超过 100。 
//
// 示例 1: 
//
// 输入:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//输出: 2
//解释:
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// 
// Related Topics 数组 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        return solution1(obstacleGrid);
        return solution2(obstacleGrid);
    }

    /**
     * 一维数组，动态规划
     * 时间：0ms，O(mn)
     * 空间：37.8MB, O(n)
     * @param obstacleGrid
     * @return
     */
    private int solution2(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int map[] = new int[col];
        if (obstacleGrid[0][0] == 1 || obstacleGrid[row - 1][col - 1] == 1) {
            return 0;
        }

        map[0] = 1 ^ obstacleGrid[0][0];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    map[j] = 0;
                    continue;
                }
                if (j > 0 && obstacleGrid[i][j - 1] == 0) {
                    map[j] += map[j - 1];
                }
            }
        }
        return map[col - 1];
    }

    /**
     * 二维数组，动态规划
     * 时间：1ms，O(mn)
     * 空间：39.2MB, O(mn)
     * @param obstacleGrid
     * @return
     */
    private int solution1(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int map[][] = new int[row][col];
        if (obstacleGrid[row - 1][col - 1] == 1) {
            return 0;
        }
        map[row - 1][col - 1] = 1;
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (map[i][j] != 0) continue;
                if (obstacleGrid[i][j] == 1) {
                    map[i][j] = 0;
                    continue;
                }
                int down = i + 1 < row ? map[i + 1][j] : 0;
                int right = j + 1 < col ? map[i][j + 1] : 0;
                map[i][j] = down + right;
            }
        }

        return map[0][0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.uniquePathsWithObstacles(new int[][]{{1}, {0}});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
