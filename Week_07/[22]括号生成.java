//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1221 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        recursion("", 0, n, result);
        return result;
    }

    private void recursion(String tmp, int leftCount, int n, List<String> result) {
        if (tmp.length() == 2 * n) {
            result.add(tmp);
            return;
        }

        if (leftCount < n) {
            recursion(tmp + "(", leftCount + 1, n, result);
        }
        if (tmp.length() - leftCount < leftCount) {
            recursion(tmp + ")", leftCount, n, result);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
