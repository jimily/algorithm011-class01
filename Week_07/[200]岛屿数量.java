//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//[
//['1','1','1','1','0'],
//['1','1','0','1','0'],
//['1','1','0','0','0'],
//['0','0','0','0','0']
//]
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//[
//['1','1','0','0','0'],
//['1','1','0','0','0'],
//['0','0','1','0','0'],
//['0','0','0','1','1']
//]
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 700 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int count;
    private int[] parent;
    private int row;
    private int col;
    private int[] rowOffset = new int[]{0, 1};
    private int[] colOffset = new int[]{1, 0};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        row = grid.length;
        col = grid[0].length;
        count = row * col;
        parent = new int[row * col];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0') {
                    count --;
                    continue;
                }
                for (int k = 0; k < 2; k++) {
                    int newI = i + rowOffset[k];
                    int newJ = j + colOffset[k];
                    if (newI >= 0 && newI < row && newJ >= 0 && newJ < col && grid[newI][newJ] == '1'){
                        union(index(i, j), index(newI, newJ));
                    }
                }
            }
        }

        return count;
    }

    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            parent[rootP] = rootQ;
            count --;
        }
    }

    private int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    private int index(int i, int j) {
        return i * col + j;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
