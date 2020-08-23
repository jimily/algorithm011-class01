//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。 
//
// 
//
// 示例： 
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 哈希表 字符串 
// 👍 254 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int index = -1;
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] letter = s.toCharArray();
        int[] indexs = new int[27];
        HashMap<Integer, Integer> count = new HashMap<>(27);
        for (int i = 0; i < indexs.length; i++) {
            indexs[i] = -1;
        }
        for (int i = 0; i < letter.length; i++) {
            int num = letter[i] - 'a' + 1;
            if (indexs[num] == -1) {
                indexs[num] = i;
                count.put(num, 1);
                continue;
            }
            count.remove(num);
        }
        count.forEach((key, value) -> {
            if (value == 1 && (index == -1 || index > indexs[key])) {
                index = indexs[key];
            }
        });
        return index;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstUniqChar("leetcode"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
