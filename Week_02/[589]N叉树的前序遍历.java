//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};


class Solution {
    List<Integer> result = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if (root == null) {
            return result;
        }
        result.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            preorder(root.children.get(i));
        }
        return result;
    }
}

//class Solution {
//    public List<Integer> preorder(Node root) {
//        List<Integer> result = new ArrayList<>();
//        if (root == null) {
//            return result;
//        }
//        Stack<Node> stack = new Stack<>();
//        stack.add(root);
//        while (!stack.empty()) {
//            Node current = stack.pop();
//            result.add(current.val);
//            for (int i = current.children.size() - 1; i >= 0 ; i--) {
//                stack.add(current.children.get(i));
//            }
//        }
//        return result;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
