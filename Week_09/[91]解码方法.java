//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划 
// 👍 453 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length()];

        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            if (s.charAt(i) != '0') {
                dp[i] = dp[i - 1];
            }
            int num = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
            if (10 <= num && num <= 26) {
                if (i == 1) {
                    dp[i] ++;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDecodings("20"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
