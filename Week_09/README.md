学习笔记

#### 不同路径Ⅱ 状态转移方程解释
链接：https://leetcode-cn.com/problems/unique-paths-ii/

### 题目
- 一个机器人位于一个 m x n 网格的左上角 ，起始点为obstacleGrid[0][0]。
-  机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角obstacleGrid[m-1][n-1]。
- 网格中的障碍物和空位置分别用 1 和 0 来表示。
- m 和 n 的值均不超过 100。
- 求：从左上角到右下角将会有多少条不同的路径？

### 二维DP
从终点递推走回起点的路径。<br/>
假设当前格子为obstacleGri[i][j]，map[i][j]为这个格子走到终点的不同路径和。<br/>
当前格子走到终点，只可能是向下走或者向右走，所以：
> map[i][j] = map[i + 1][j] + map[i][j + 1]

额外要注意的是障碍物，如果当前格子存在障碍物，则路径和map[i][j]直接赋值为0，因为不可能由有障碍物的格子走到终点。<br/>
递推到最后map[0][0]则为本题答案。

```java
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
```

### 一维DP
逻辑跟二维DP基本一致，只是换了个方向，且按行为顺序递推，这样能达到压缩空间的目的。
<br/><br/>
从起点递推至终点。<br/>
假设当前格子为obstacleGri[i][j]，map[j]为从起点走到当前格子的不同路径和。<br/>
当前格子只可能是从obstacleGri[i - 1][j]，或者由obstacleGri[i][j - 1]走到的，所以：
> map[j] = map[j] + map[j - 1]
- 为了压缩空间，用一维数组进行dp状态的存储，按行进行递推之后，map[j]的重新赋值之前，则为走到obstacleGri[i - 1][j]的结果
- 而map[j - 1]因为在这之前已经递推运算过，所以对应的值为走到obstacleGri[i][j - 1]的结果


额外要注意的是障碍物，如果当前格子存在障碍物，则路径和map[j]直接赋值为0，因为不可能由有障碍物的格子走到终点。<br/>
递推到最后map[n - 1]则为本题答案。
```java
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
```