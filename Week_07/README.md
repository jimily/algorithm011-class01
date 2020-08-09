学习笔记

### 单词搜索Ⅱ：时间复杂度分析

1. 程序划分为构造字典树以及深搜找答案的两段主要逻辑
2. 假设board为m * n的矩阵，words有k个单词，平均单词长度为L

#### 构造字典树时间复杂度
```java
public List<String> findWords(char[][] board, String[] words) {
    ////省略代码////
    //构造字典树
    for (String word : words) {
        insert(word);
    }
    ////省略代码////
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
```
时间复杂度为O(K * L)

#### 查找board的时间复杂度
```java
public List<String> findWords(char[][] board, String[] words) {
    ////省略代码////
    //dfs
    List<String> result = new ArrayList<>();
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            dfs(i, j, this.trie, result);
        }
    }
    ////省略代码////
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
```
1. 需要遍历board的每个格子m * n
2. 每个格子，最坏的情况是有4个有效方向探索，而在回溯过程中，最多有3个方向可以探索，一个单词假设长度为L，则dfs的过程为4 * 3 ^ (L - 1)。
3. 查找答案的时间复杂度为O(m * n * 4 * 3 ^ (L - 1))。