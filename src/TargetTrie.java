import java.util.TreeMap;

/**
 * @author yyb
 * leetcode_tag_trie
 * leetcode 标签 字典树
 */
public class TargetTrie {

    //region 208. 实现 Trie (前缀树)  2019/10/8  前缀树的实现
    /**
     * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
     * 示例:
     * Trie trie = new Trie();
     * trie.insert("apple");
     * trie.search("apple");   // 返回 true
     * trie.search("app");     // 返回 false
     * trie.startsWith("app"); // 返回 true
     * trie.insert("app");
     * trie.search("app");     // 返回 true
     * 说明:
     * 你可以假设所有的输入都是由小写字母 a-z 构成的。
     * 保证所有输入均为非空字符串。
     */
    class Trie {
        private class Node{
            public boolean isWord;
            public TreeMap<Character,Node> next;
            public Node(boolean isWord){
                this.isWord=isWord;
                next=new TreeMap<>();
            }

            public Node(){
                this(false);
            }
        }
        /** Initialize your data structure here. */
        private Node root;
        private int size;
        public Trie() {
            root=new Node();
            size=0;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node cur=root;
            for(int i=0;i<word.length();i++){
                char c=word.charAt(i);
                if(cur.next.get(c)==null){
                    cur.next.put(c,new Node()) ;
                }
                cur=cur.next.get(c);
            }
            if(!cur.isWord){
                cur.isWord=true;
                size++;
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node cur=root;
            for(int i=0;i<word.length();i++){
                char c=word.charAt(i);
                if(cur.next.get(c)==null){
                    return false;
                }
                cur=cur.next.get(c);
            }
            return cur.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node cur=root;
            for(int i=0;i<prefix.length(); i++){
                char ch=prefix.charAt(i);
                if(cur.next.get(ch)==null){
                    return false;
                }
                cur=cur.next.get(ch);
            }
            return true;
        }
    }
    //endregion

    //region 211. 添加与搜索单词 - 数据结构设计  2019/10/8  前缀树+正则匹配
    /**
     * 设计一个支持以下两种操作的数据结构：
     *
     * void addWord(word)
     * bool search(word)
     * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
     *
     * 示例:
     *
     * addWord("bad")
     * addWord("dad")
     * addWord("mad")
     * search("pad") -> false
     * search("bad") -> true
     * search(".ad") -> true
     * search("b..") -> true
     */
    class WordDictionary {
        private class Node{
            public boolean isWord;
            public TreeMap<Character,Node> next;
            public Node(boolean isWord){
                this.isWord=isWord;
                next=new TreeMap<>();
            }

            public Node(){
                this(false);
            }
        }
        private Node root;
        /** Initialize your data structure here. */
        public WordDictionary() {
            root=new Node();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            Node cur=root;
            for(int i=0;i<word.length();i++){
                char c=word.charAt(i);
                if(cur.next.get(c)==null){
                    cur.next.put(c,new Node()) ;
                }
                cur=cur.next.get(c);
            }
            if(!cur.isWord){
                cur.isWord=true;
            }
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return match(root,word,0);
        }

        private boolean match(Node node,String word,int index){
            if(index==word.length()){
                return node.isWord;
            }
            char c=word.charAt(index);
            if(c!='.'){
                if(node.next.get(c)==null){
                    return false;
                }
                return match(node.next.get(c),word,index+1);
            }else{
                for(char nextChar:node.next.keySet()) {
                    if(match(node.next.get(nextChar),word,index+1)){
                        return true;
                    }
                }
                return false;
            }
        }
    }
    //endregion

    //region 421. 数组中两个数的最大异或值  2021/5/16
    /**
     *给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
     * 进阶：你可以在 O(n) 的时间解决这个问题吗？
     *
     * 示例 1：
     * 输入：nums = [3,10,5,25,2,8]
     * 输出：28
     * 解释：最大运算结果是 5 XOR 25 = 28.
     * 示例 2：
     * 输入：nums = [0]
     * 输出：0
     * 示例 3：
     * 输入：nums = [2,4]
     * 输出：6
     * 示例 4：
     * 输入：nums = [8,10,2]
     * 输出：10
     * 示例 5：
     * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
     * 输出：127
     *  
     * 提示：
     * 1 <= nums.length <= 2 * 10^4
     * 0 <= nums[i] <= 2^31 - 1
     */
    Trie1 root = new Trie1();
    // 最高位的二进制位编号为 30
    static final int HIGH_BIT = 30;
    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        int x = 0;
        for (int i = 1; i < n; ++i) {
            // 将 nums[i-1] 放入字典树，此时 nums[0 .. i-1] 都在字典树中
            add(nums[i - 1]);
            // 将 nums[i] 看作 ai，找出最大的 x 更新答案
            x = Math.max(x, check(nums[i]));
        }
        return x;
    }
    //添加数
    public void add(int num) {
        Trie1 cur = root;
        for (int k = HIGH_BIT; k >= 0; --k) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (cur.left == null) {
                    cur.left = new Trie1();
                }
                cur = cur.left;
            }
            else {
                if (cur.right == null) {
                    cur.right = new Trie1();
                }
                cur = cur.right;
            }
        }
    }
    //查找数
    public int check(int num) {
        Trie1 cur = root;
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; --k) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                // a_i 的第 k 个二进制位为 0，应当往表示 1 的子节点 right 走
                if (cur.right != null) {
                    cur = cur.right;
                    x = x * 2 + 1;
                } else {
                    cur = cur.left;
                    x = x * 2;
                }
            } else {
                // a_i 的第 k 个二进制位为 1，应当往表示 0 的子节点 left 走
                if (cur.left != null) {
                    cur = cur.left;
                    x = x * 2 + 1;
                } else {
                    cur = cur.right;
                    x = x * 2;
                }
            }
        }
        return x;
    }
    //定义字典树
    class Trie1 {
    // 左子树指向表示 0 的子节点
    Trie1 left = null;
    // 右子树指向表示 1 的子节点
    Trie1 right = null;
}
    //endregion

    //region 677. 键值映射  2019/10/8  字典树+映射
    /**
     * 实现一个 MapSum 类里的两个方法，insert 和 sum。
     * 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。
     * 如果键已经存在，那么原来的键值对将被替代成新的键值对。
     * 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
     *
     * 示例 1:
     * 输入: insert("apple", 3), 输出: Null
     * 输入: sum("ap"), 输出: 3
     * 输入: insert("app", 2), 输出: Null
     * 输入: sum("ap"), 输出: 5
     */
    class MapSum {

        private class Node{
            public int value;
            public TreeMap<Character,Node>next;

            public Node(int value){
                this.value=value;
                next=new TreeMap<>();
            }

            public Node(){
                this(0);
            }
        }
        private  Node root;
        /** Initialize your data structure here. */
        public MapSum() {
            root=new Node();
        }

        public void insert(String word, int val) {
            Node cur=root;
            for(int i=0;i<word.length();i++){
                char c=word.charAt(i);
                if(cur.next.get(c)==null){
                    cur.next.put(c,new Node()) ;
                }
                cur=cur.next.get(c);
            }
            cur.value=val;
        }

        public int sum(String prefix) {
            Node cur=root;
            for(int i=0;i<prefix.length();i++){
                char c=prefix.charAt(i);
                if(cur.next.get(c)==null){
                    return 0;
                }
                cur=cur.next.get(c);
            }
            return sum(cur);
        }

        private int sum(Node node){
            if(node.next.size()==0){
                return node.value;
            }
            int res=node.value;
            for(char c:node.next.keySet()){
                res+=sum(node.next.get(c));
            }
            return res;
        }
    }
    //endregion

}
