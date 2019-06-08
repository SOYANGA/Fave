package FindWordsII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: LeetCode
 * @Description: 212. 单词搜索 II
 * @Author: SOYANGA
 * @Create: 2019-06-09 01:46
 * @Version 1.0
 */
public class FindWordsII {


    class Solution {
        Set<String> res = new HashSet<>();

        public List<String> findWords(char[][] board, String[] words) {

            Trie trie = new Trie();
            for (int i = 0; i < words.length; i++) {
                trie.insert(words[i]);
            }
            int m = board.length;
            int n = board[0].length;
            boolean[][] flag = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(board, i, j, "", flag, trie);
                }
            }
            return new ArrayList<String>(res);
        }

        private void dfs(char[][] board, int m, int n, String str, boolean[][] flag, Trie trie) {
            if (m < 0 || m >= board.length || n < 0 || n >= board[0].length) return;
            if (flag[m][n]) return;
            str += board[m][n];
            if (!trie.startWith(str)) return;
            if (trie.find(str)) {
                res.add(str);
            }
            flag[m][n] = true;
            dfs(board, m + 1, n, str, flag, trie);
            dfs(board, m - 1, n, str, flag, trie);
            dfs(board, m, n + 1, str, flag, trie);
            dfs(board, m, n - 1, str, flag, trie);
            flag[m][n] = false;
        }

        private class Trie {
            private TrieNode root;

            /**
             * Initialize your data structure here.
             */
            public Trie() {
                root = new TrieNode();
                root.val = ' ';
            }

            /**
             * Inserts a word into the trie.
             */
            public void insert(String word) {
                TrieNode ws = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (ws.children[c - 'a'] == null) {
                        ws.children[c - 'a'] = new TrieNode(c);
                    }
                    ws = ws.children[c - 'a'];
                }
                ws.isWorld = true;
            }

            /**
             * Returns if the word is in the trie.
             */
            public boolean find(String word) {
                TrieNode ws = root;
                for (int i = 0; i < word.length(); i++) {
                    if (ws.children[word.charAt(i) - 'a'] == null) {
                        return false;
                    }
                    ws = ws.children[word.charAt(i) - 'a'];
                }
                return ws.isWorld;
            }

            /**
             * Returns if there is any word in the trie that starts with the given prefix.
             */
            public boolean startWith(String prefix) {
                TrieNode ws = root;
                for (int i = 0; i < prefix.length(); i++) {
                    if (ws.children[prefix.charAt(i) - 'a'] == null) {
                        return false;
                    }
                    ws = ws.children[prefix.charAt(i) - 'a'];
                }
                return true;
            }

            private class TrieNode {
                public char val;
                public boolean isWorld;
                public TrieNode[] children = new TrieNode[26];

                public TrieNode() {
                }

                public TrieNode(char val) {
                    this.val = val;
                }
            }
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

 */
