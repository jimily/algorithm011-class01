//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int[] prime = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            int product = calculate(strs[i]);
            List<String> list = map.get(product);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(strs[i]);
            map.put(product, list);
        }

        return new ArrayList<>(map.values());
    }

    private int calculate(String word) {
        int result = 1;
        for (int i = 0; i < word.length(); i++) {
            result *= prime[word.charAt(i) - 'a'];
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
