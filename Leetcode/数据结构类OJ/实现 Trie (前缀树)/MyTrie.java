package Tire;

/**
 * @program: LeetCode
 * @Description: 208. 实现 Trie (前缀树)
 * @Author: SOYANGA
 * @Create: 2019-06-08 00:10
 * @Version 1.0
 */
public class MyTrie {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));// 返回 true
        System.out.println(trie.search("app"));// 返回 false
        System.out.println( trie.startsWith("app"));// 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));// 返回 true
    }
}

class Trie {

    private TrieNode root = new TrieNode('/');

    public class TrieNode {
        char data;
        TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;

        public TrieNode(char data) {
            this.data = data;
        }
    }


    /**
     * Initialize your data structure here.
     */
    public Trie() {

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        char[] words = word.toCharArray();
        TrieNode node = root;
        for (char word1 : words) {
            int index = word1 - 'a';
            if (node.children[index] == null) {
                TrieNode newNode = new TrieNode(word1);
                node.children[index] = newNode;
            }
            node = node.children[index];
        }
        node.isEndingChar = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = root;
        char[] words = word.toCharArray();
        for (char word1 : words) {
            int index = word1 - 'a';
            if (node.children[index] == null) {
                //不存在 pattern
                return false;
            }
            node = node.children[index];
        }
        //不完全匹配，只是前返回false;
        return node.isEndingChar;

    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        char[] words = prefix.toCharArray();
        for (char word : words) {
            int index = word - 'a';
            if (node.children[index] == null) {
                //不存在 pattern
                return false;
            }
            node = node.children[index];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
