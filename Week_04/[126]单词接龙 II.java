//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
//需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换后得到的单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return result;
        }
        //1.预处理单词表，将单词按前继变化存储，例如{<"*ot",["hot"]>, <"h*t",["hot"]>, <"ho*",["hot"]>}
        Map<String, List<String>> possible = new HashMap<>();
        dealWordList(wordList, possible);

        //2.单向或者双向bfs，按每个单词为维度，求每个单词的后继变化
        Map<String, Set<String>> dictionary = new HashMap<>();
        boolean reachable = bfs(beginWord, endWord, possible, dictionary);
        if (!reachable) {
            return result;
        }

        //3.回溯尝试组合出结果
        Deque<String> path = new ArrayDeque<>();
        path.add(beginWord);
        dfs(path, beginWord, endWord, dictionary, result);
        return result;
    }

    private void dfs(Deque<String> path, String beginWord, String endWord, Map<String, Set<String>> dictionary, List<List<String>> result) {
        if (beginWord.equals(endWord)) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (!dictionary.containsKey(beginWord)) {
            return;
        }
        Set<String> successor = dictionary.get(beginWord);
        for (String word : successor) {
            path.addLast(word);
            dfs(path, word, endWord, dictionary, result);
            path.removeLast();
        }
    }

    private boolean bfs(String beginWord, String endWord, Map<String, List<String>> possible,  Map<String, Set<String>> dictionary) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Set<String> exist = new HashSet<>();
        exist.add(beginWord);
        boolean reachable = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> nextLevelVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                for (int j = 0; j < currentWord.length(); j++) {
                    String tmp = currentWord.substring(0, j) + "*" + currentWord.substring(j + 1);
                    List<String> target = possible.getOrDefault(tmp, new ArrayList<>());
                    if (target.contains(endWord)) {
                        reachable = true;
                    }
                    for (String targetWord : target) {
                        if (exist.contains(targetWord)) {
                            continue;
                        }
                        if (!nextLevelVisited.contains(targetWord)) {
                            nextLevelVisited.add(targetWord);
                            queue.add(targetWord);
                        }
                        dictionary.computeIfAbsent(currentWord, value -> new HashSet<>());
                        dictionary.get(currentWord).add(targetWord);
                    }
                }
            }
            if (reachable) {
                break;
            }
            exist.addAll(nextLevelVisited);
        }
        return reachable;
    }

    private void dealWordList(List<String> wordList, Map<String, List<String>> possible){
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String tmp = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> target = possible.getOrDefault(tmp, new ArrayList<>());
                target.add(word);
                possible.put(tmp, target);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
