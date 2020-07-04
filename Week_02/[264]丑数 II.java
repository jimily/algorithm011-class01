//编写一个程序，找出第 n 个丑数。 
//
// 丑数就是质因数只包含 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
//与"剑指 Offer 49. 丑数"是同一道题
class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        for (int i = 1, p2 = 0, p3 = 0, p5 = 0; i < n; i++) {
            ugly[i] = Math.min(Math.min(ugly[p2] * 2, ugly[p3] *3), ugly[p5] * 5);
            if (ugly[i] == ugly[p2] * 2) p2++;
            if (ugly[i] == ugly[p3] * 3) p3++;
            if (ugly[i] == ugly[p5] * 5) p5++;
        }
        return ugly[n - 1];
    }

}
//leetcode submit region end(Prohibit modification and deletion)
