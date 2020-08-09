//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
// 
//
// 示例: 
//
// 输入: 
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"] 
//
// 说明: 
//你可以假设所有输入都由小写字母 a-z 组成。 
//
// 提示: 
//
// 
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？ 
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。 
// 
// Related Topics 字典树 回溯算法 
// 👍 208 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private Trie trie = new Trie();
    private int[] rowOffset = new int[]{-1, 0, 0, 1};
    private int[] colOffset = new int[]{0, -1, 1, 0};
    private char[][] board;
    private int m;
    private int n;

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0) {
            return new ArrayList<>();
        }
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;

        //构造字典树
        for (String word : words) {
            insert(word);
        }
        //dfs
        List<String> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, this.trie, result);
            }
        }
        return result;
    }
    private void dfs(int row, int col, Trie current, List<String> result) {
        //越界
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return;
        }
        char letter = this.board[row][col];
        if (letter == '@' || current.next[letter - 'a'] == null) {
            //字母已被使用或者前缀树不存在
            return;
        }
        current = current.next[letter - 'a'];
        if (current.word != null) {
            //存在单词
            result.add(current.word);
            current.word = null;
        }

        this.board[row][col] = '@';
        for (int i = 0; i < 4; i++) {
            dfs(row + rowOffset[i], col + colOffset[i], current, result);
        }
        this.board[row][col] = letter;
    }

    private void insert(String word) {
        Trie current = this.trie;
        char[] letter = word.toCharArray();
        for (int i = 0; i < letter.length; i++) {
            if (current.next[letter[i] - 'a'] == null) {
                current.next[letter[i] - 'a'] = new Trie();
            }
            current = current.next[letter[i] - 'a'];
        }
        current.word = word;
    }
}
class Trie {
    public String word;
    public Trie[] next;
    public Trie(){
        this.next = new Trie[26];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
