//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        generate(n, k, 1, new LinkedList<>(), result);
        return result;
    }
    private void generate(int n, int k, int first, Deque<Integer> deque, List<List<Integer>> result) {
        if (deque.size() == k || k == 0) {
            result.add(new ArrayList<>(deque));
            return ;
        }

        for (int i = first; i <= n && (k - deque.size() + i - 1) <= n; i++) {
            deque.add(i);
            generate(n, k, i + 1, deque, result);
            deque.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
