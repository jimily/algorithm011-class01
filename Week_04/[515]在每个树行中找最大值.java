//您需要在二叉树的每一行中找到最大的值。 
//
// 示例： 
//
// 
//输入: 
//
//          1
//         / \
//        3   2
//       / \   \  
//      5   3   9 
//
//输出: [1, 3, 9]
// 
// Related Topics 树 深度优先搜索 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> largestValues(TreeNode root) {
//        return bfs(root);
        return dfs(root);
    }

    /**
     * 解法二：深搜
     * @param root
     * @return
     */
    private List<Integer> dfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        recursive4DFS(root, 0, result);
        return result;
    }
    private void recursive4DFS(TreeNode root, int level, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (result.size() <= level) {
            result.add(Integer.MIN_VALUE);
        }
        result.set(level, Math.max(result.get(level), root.val));
        recursive4DFS(root.left, level + 1, result);
        recursive4DFS(root.right, level + 1, result);
    }

    /**
     * 解法一：广搜，层序遍历
     * @param root
     * @return
     */
    private List<Integer> bfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer max = null;
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (max == null || max < current.val) {
                    max = current.val;
                }
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);

            }
            result.add(max);
        }
        return result;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
//leetcode submit region end(Prohibit modification and deletion)
