//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
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
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        return solution1(beginWord, endWord, wordList);
//        return solution2(beginWord, endWord, wordList);
//        return solution3(beginWord, endWord, wordList);
        return solution4(beginWord, endWord, wordList);
    }


    /**
     * 解法四：（按单词到达维度）将每个单词变化一个字母后能达到的单词进行预处理，然后再用双向bfs，bfs过程中，每次从节点数更少的一端遍历
     * 执行耗时:53 ms,内存消耗:45.5 MB
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int solution4(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Map<String, List<String>> targetWord = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String tmp = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> target = targetWord.getOrDefault(tmp, new ArrayList<>());
                target.add(word);
                targetWord.put(tmp, target);
            }
        }

        Queue<String> queueA = new LinkedList<>();
        Queue<String> queueB = new LinkedList<>();
        queueA.add(beginWord);
        queueB.add(endWord);
        List<String> visitedA = new LinkedList<>();
        List<String> visitedB = new LinkedList<>();
        visitedA.add(beginWord);
        visitedB.add(endWord);
        int step = 0;

        while (!queueA.isEmpty() && !queueB.isEmpty()) {
            step ++;
            if (queueA.size() > queueB.size()) {
                Queue<String> queue = queueA;
                queueA = queueB;
                queueB = queue;
                List<String> visited = visitedA;
                visitedA = visitedB;
                visitedB = visited;
            }

            int size = queueA.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queueA.poll();
                for (int i1 = 0; i1 < currentWord.length(); i1++) {
                    String tmp = currentWord.substring(0, i1) + "*" + currentWord.substring(i1 + 1);
                    List<String> target = targetWord.getOrDefault(tmp, new ArrayList<>());

                    for (String word : target) {
                        if (visitedA.contains(word)) {
                            continue;
                        }
                        if (visitedB.contains(word)) {
                            return step + 1;
                        }
                        visitedA.add(word);
                        queueA.add(word);
                    }
                    targetWord.remove(tmp);
                }
            }
        }

        return 0;
    }

    /**
     * 解法三：（按单词到达维度）将每个单词变化一个字母后能达到的单词进行预处理，然后再用双向bfs
     * 执行耗时:577 ms,内存消耗:47 MB
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int solution3(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Map<String, List<String>> targetWord = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String tmp = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> target = targetWord.getOrDefault(tmp, new ArrayList<>());
                target.add(word);
                targetWord.put(tmp, target);
            }
        }

        Queue<String> beginQueue = new LinkedList<>();
        Queue<String> endQueue = new LinkedList<>();
        beginQueue.add(beginWord);
        endQueue.add(endWord);
        boolean[] beginVisited = new boolean[wordList.size()];
        boolean[] endVisited = new boolean[wordList.size()];
        int beginStep = 1;
        int endStep = 1;

        int beginIndex = wordList.indexOf(beginWord);
        if (beginIndex != -1){
            beginVisited[beginIndex] = true;
        }
        int endIndex = wordList.indexOf(endWord);
        if (endIndex != -1) {
            endVisited[endIndex] = true;
        }

        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            int beginSize = beginQueue.size();
            for (int i = 0; i < beginSize; i++) {
                String currentWord = beginQueue.poll();
                for (int i1 = 0; i1 < currentWord.length(); i1++) {
                    String tmp = currentWord.substring(0, i1) + "*" + currentWord.substring(i1 + 1);
                    List<String> target = targetWord.getOrDefault(tmp, new ArrayList<>());
                    if (target.contains(endWord)) {
                        return beginStep + endStep;
                    }
                    for (String word : target) {
                        int index = wordList.indexOf(word);
                        if (!beginVisited[index]) {
                            if (endVisited[index]) {
                                return beginStep + endStep;
                            }
                            beginVisited[index] = true;
                            beginQueue.add(word);
                        }
                    }
                    targetWord.remove(tmp);
                }
            }
            beginStep ++;

            int endSize = endQueue.size();
            for (int j = 0; j < endSize; j++) {
                String currentWord = endQueue.poll();
                for (int j1 = 0; j1 < currentWord.length(); j1++) {
                    String tmp = currentWord.substring(0, j1) + "*" + currentWord.substring(j1 + 1);
                    List<String> target = targetWord.getOrDefault(tmp, new ArrayList<>());
                    if (target.contains(beginWord)) {
                        return beginStep + endStep;
                    }
                    for (String word : target) {
                        int index = wordList.indexOf(word);
                        if (!endVisited[index]) {
                            if (beginVisited[index]) {
                                return beginStep + endStep;
                            }
                            endVisited[index] = true;
                            endQueue.add(word);
                        }
                    }
                    targetWord.remove(tmp);
                }
            }
            endStep ++;
        }

        return 0;
    }

    /**
     * 解法二：（按单词到达维度）将每个单词变化一个字母后能达到的单词进行预处理，然后再用bfs
     * 执行耗时:63 ms,内存消耗:46.8 MB
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int solution2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Map<String, List<String>> targetWord = new HashMap<>();
        for (String s : wordList) {
            for (int i = 0; i < s.length(); i++) {
                String tmp = s.substring(0, i) + "*" + s.substring(i + 1);
                List<String> target = targetWord.getOrDefault(tmp, new ArrayList<>());
                target.add(s);
                targetWord.put(tmp, target);
            }
        }

        Queue<String> queue = new LinkedList<>();
        HashSet<String> exist = new HashSet<>(wordList);
        queue.add(beginWord);
        exist.remove(beginWord);
        int step = 1;
        while(!queue.isEmpty()) {
            step ++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                for (int j = 0; j < currentWord.length(); j++) {
                    String tmp = currentWord.substring(0, j) + "*" + currentWord.substring(j + 1);
                    List<String> target = targetWord.getOrDefault(tmp, new ArrayList<>());
                    if (target.contains(endWord)) {
                        return step;
                    }
                    target.forEach((word) -> {
                        if (exist.contains(word)) {
                            queue.add(word);
                            exist.remove(word);
                        }
                    });
                    targetWord.remove(tmp);
                }
            }
        }

        return 0;
    }

    /**
     * 解法一：（按字母到达维度）先将每个位置可能的的变化情况存起来，然后再用bfs
     * 执行耗时:97 ms,内存消耗:41.5 MB
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int solution1(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        //处理每一个位置可能的情况，方便后续遍历
        //即测试用例：wordList 为 ["hot","dot","dog","lot","log","cog"]会被处理成:
        //possibility[0]:(int)'h',(int)'d',(int)'l',(int)'c'
        List<List<Integer>> possibility = new ArrayList<>(beginWord.length());
        for (String s : wordList) {
            char[] word = s.toCharArray();
            for (int i = 0; i < word.length; i++) {
                if (possibility.size() <= i) {
                    possibility.add(new ArrayList<>());
                }
                if (possibility.get(i).contains((int)word[i])) {
                    continue;
                }
                possibility.get(i).add((int)word[i]);
            }
        }
        //bfs
        Set<String> exsit = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        exsit.remove(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            step ++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char[] currentWord = queue.poll().toCharArray();
                for (int j = 0; j < currentWord.length; j++) {
                    char oldLetter = currentWord[j];
                    List<Integer> targetLetter = possibility.get(j);
                    for (Integer letter : targetLetter) {
                        currentWord[j] = (char)letter.intValue();
                        String newWord = new String(currentWord);
                        if (endWord.equals(newWord)) {
                            return step;
                        }
                        if (exsit.contains(newWord)) {
                            queue.add(newWord);
                            exsit.remove(newWord);
                        }
                    }
                    currentWord[j] = oldLetter;
                }
            }

        }

        return 0;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.ladderLength("red","tax", Arrays.asList("ted","tex","red","tax","tad","den","rex","pee")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
