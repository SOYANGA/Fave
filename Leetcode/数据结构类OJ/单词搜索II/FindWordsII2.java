package FindWordsII;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: LeetCode
 * @Description: 212. 单词搜索 II 优化 将 char 改为 String 存储
 * @Author: SOYANGA
 * @Create: 2019-06-09 01:46
 * @Version 1.0
 */
public class FindWordsII2 {


    class Solution {
        List<String> result = new ArrayList<>();

        public List<String> findWords(char[][] board, String[] words) {
            TrieNode trieNode = buildTrie(words);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    dfs(board, i, j, trieNode);
                }
            }
            return result;
        }

        public void dfs(char[][] board, int i, int j, TrieNode trieNode) {
            char c = board[i][j];
            if ('#' == c || null == trieNode.next[c - 'a']) return;
            trieNode = trieNode.next[c - 'a'];
            if (null != trieNode.word) {
                result.add(trieNode.word);
                trieNode.word = null;     //去重
            }
            board[i][j] = '#';
            if (i > 0) dfs(board, i - 1, j, trieNode);
            if (j > 0) dfs(board, i, j - 1, trieNode);
            if (i < board.length - 1) dfs(board, i + 1, j, trieNode);
            if (j < board[0].length - 1) dfs(board, i, j + 1, trieNode);
            board[i][j] = c;
        }

        private class TrieNode {
            private TrieNode[] next = new TrieNode[26];
            private String word;
        }

        public TrieNode buildTrie(String[] words) {
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode trieNode = root;
                for (char c : word.toCharArray()) {
                    int i = c - 'a';
                    if (null == trieNode.next[i]) {
                        trieNode.next[i] = new TrieNode();
                    }
                    trieNode = trieNode.next[i];
                }
                trieNode.word = word;
            }
            return root;
        }
    }
}
/*
DFS+回溯+剪枝 ：却点：每次搜索都需要从board中的一个位置开始DFS。耗时旧

Trie :先将将候选词放入tire中（预处理）
1.words\[ ]数组中的内容 依次放入字典树中
2.然后在4\*5维数组中依次遍历，查看是否在Tire中存在，存在则加入List中，
3.反之则回溯回到上一个节点（没有将相邻单元格访问完的的节点）（直到将所有节点遍历完）
4.在遍历过程中由于 同一个单元格内的字母在一个单词中不允许被重复使用。
所以在正在遍历过的节点进行标识，
5.当该节点的相邻节点都被访问完毕时再将其恢复。（恢复现场）


优化：修改Tire树，将树中的字符取消到，存储String字符串（且只在一个Tire的中完整单词的末尾节点处存储word！= null，其余不进行存储word == null，但是节点不为null）

 */