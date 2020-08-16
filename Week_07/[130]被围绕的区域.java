//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 277 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int[] parent;
    private int row;
    private int col;
    private int[] rowOffset = new int[]{-1, 0, 0, 1};
    private int[] colOffset = new int[]{0, -1, 1, 0};
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        //搭建并查集
        row = board.length;
        col = board[0].length;
        init(row * col + 1);
        int border = row * col;

        //遍历board处理成并查集
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X') {
                    continue;
                }
                //将O的联合
                //如果是边界上的O，则与border节点进行联合，标记起来
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    union(node(i, j), border);
                    continue;
                }
                //如果不是边界上的O，则与上下左右的O进行联合
                for (int k = 0; k < 4; k++) {
                    int newi = i + rowOffset[k];
                    int newj = j + colOffset[k];
                    if (newi >= 0 && newi < row && newj >= 0 && newj < col && board[newi][newj] == 'O') {
                        union(node(i, j), node(newi, newj));
                    }
                }
            }
        }
        //处理board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!isUnion(node(i, j), border)) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    private int node(int i, int j) {
        return i * col + j;
    }

    private boolean isUnion(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            parent[rootP] = rootQ;
        }
    }

    private void init(int n) {
        parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
