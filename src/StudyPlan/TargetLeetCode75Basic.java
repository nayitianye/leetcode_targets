package StudyPlan;

import java.util.*;

/**
 * @author yyb
 * leetcode_studyplan_LeetCode_75_基础
 * leetcode 学习计划 LeetCode 75 Level1
 */
public class TargetLeetCode75Basic {

    //region    自定义数据结构
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    //endregion

    //region    20230317    1. 两数之和

    /**
     * https://leetcode.cn/problems/two-sum/
     *
     * @param nums   一个整数数组 nums
     * @param target 一个整数目标值 target
     * @return 返回它们的数组下标
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{i, hashMap.get(target - nums[i])};
            } else {
                hashMap.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }
    //endregion

    //region    20230307    21. 合并两个有序链表

    /**
     * https://leetcode.cn/problems/merge-two-sorted-lists/
     *
     * @param list1 有序链表 list1
     * @param list2 有序链表 list1
     * @return 返回合并后的有序链表
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //endregion

    //region    20230315    62. 不同路径

    /**
     * https://leetcode.cn/problems/unique-paths/
     *
     * @param m 网格位置 m
     * @param n 网格位置 n
     * @return 到达路径数
     */
    public int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                    continue;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
    //endregion

    //region    20230314    70. 爬楼梯
    HashMap<Integer, Integer> hashMapClimbStairs = new HashMap<>();

    /**
     * https://leetcode.cn/problems/climbing-stairs/
     *
     * @param n n 阶楼梯
     * @return 多少种方法
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            if (hashMapClimbStairs.containsKey(n)) {
                return hashMapClimbStairs.get(n);
            } else {
                hashMapClimbStairs.put(n, climbStairs(n - 1) + climbStairs(n - 2));
                return hashMapClimbStairs.get(n);
            }
        }
    }
    //endregion

    //region    20230312    98. 验证二叉搜索树

    /**
     * https://leetcode.cn/problems/validate-binary-search-tree/description/
     *
     * @param root 二叉树的根节点 root
     * @return 判断其是否是一个有效的二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }
    //endregion

    //region    20230312    102. 二叉树的层序遍历

    /**
     * https://leetcode.cn/problems/binary-tree-level-order-traversal/
     *
     * @param root 二叉树的根节点 root
     * @return 二叉树的根节点 root
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode treeNode = queue.poll();
                level.add(treeNode.val);
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            res.add(level);
        }
        return res;
    }
    //endregion

    //region    20230309    121. 买卖股票的最佳时机

    /**
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
     *
     * @param prices 一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格
     * @return 这笔交易中获取的最大利润
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(prices[i] - min, max);
        }
        return max;
    }
    //endregion

    //region    20230308    142. 环形链表 II

    /**
     * https://leetcode.cn/problems/linked-list-cycle-ii/description/
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
    //endregion

    //region    20230318    200. 岛屿数量

    /**
     * https://leetcode.cn/problems/number-of-islands/
     *
     * @param grid 由 '1'（陆地）和 '0'（水）组成的的二维网格 grid
     * @return 计算岛屿的数量
     */
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    area(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void area(char[][] grid, int row, int col) {
        if (row >= grid.length || col >= grid[0].length || row < 0 || col < 0) {
            return;
        }
        if (grid[row][col] != '1') {
            return;
        }
        grid[row][col] = '2';
        area(grid, row - 1, col);
        area(grid, row + 1, col);
        area(grid, row, col - 1);
        area(grid, row, col + 1);
    }
    //endregion

    //region    20230306    205. 同构字符串

    /**
     * https://leetcode.cn/problems/isomorphic-strings/
     *
     * @param s 字符串 s
     * @param t 字符串 t
     * @return 判断它们是否是同构的
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> hashMapS = new HashMap<>();
        HashMap<Character, Character> hashMapT = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char chs = s.charAt(i);
            char cht = t.charAt(i);
            if ((hashMapT.containsKey(chs) && hashMapT.get(chs) != cht) || (hashMapS.containsKey(cht) && hashMapS.get(cht) != chs)) {
                return false;
            }
            hashMapT.put(chs, cht);
            hashMapS.put(cht, chs);
        }
        return true;
    }
    //endregion

    //region    20230307    206. 反转链表

    /**
     * https://leetcode.cn/problems/reverse-linked-list/
     *
     * @param head 链表 head
     * @return 反转链表 heed 并返回
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    //endregion

    //region    20230312    235. 二叉搜索树的最近公共祖先

    /**
     * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
     *
     * @param root 二叉搜索树根节点 root
     * @param p    节点 p
     * @param q    节点 q
     * @return 找出对于有根树 root 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }
    //endregion

    //region    20230312    278. 第一个错误的版本

    /**
     * https://leetcode.cn/problems/first-bad-version/
     *
     * @param n n 个版本 [1, 2, ..., n]
     * @return 导致之后所有版本出错的第一个错误的版本
     */
    public int firstBadVersion(int n) {
        int first = 0, last = n, mid = 0;
        while (first < last) {
            mid = first + (last - first) / 2;
            if (isBadVersion(mid)) {
                last = mid;
            } else {
                first = mid + 1;
            }
        }
        return isBadVersion(mid) ? mid : mid + 1;
    }

    public boolean isBadVersion(int n) {
        return n == 10;
    }
    //endregion

    //region    20230317    299. 猜数字游戏

    /**
     * https://leetcode.cn/problems/bulls-and-cows/
     *
     * @param secret 秘密数字 secret
     * @param guess  猜测的数字 guess
     * @return 返回对朋友这次猜测的提示
     */
    public String getHint(String secret, String guess) {
        int BullsNum = 0, CowsNum = 0;
        int[] cntS = new int[10];
        int[] cntG = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                BullsNum++;
            } else {
                ++cntS[secret.charAt(i) - '0'];
                ++cntG[guess.charAt(i) - '0'];
            }
        }
        for (int i = 0; i < 10; i++) {
            CowsNum += Math.min(cntS[i], cntG[i]);
        }
        return Integer.toString(BullsNum) + "A" + Integer.toString(CowsNum) + "B";
    }
    //endregion

    //region    20230306    392. 判断子序列

    /**
     * https://leetcode.cn/problems/is-subsequence/
     *
     * @param s 字符串 s
     * @param t 字符串 t
     * @return 判断 s 是否为 t 的子序列
     */
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == m;
    }
    //endregion

    //region    20230318    394. 字符串解码

    /**
     * https://leetcode.cn/problems/decode-string/
     *
     * @param s 定一个经过编码的字符串 s   编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次
     * @return 返回它解码后的字符串
     */
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for (int i = 0; i < cur_multi; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(stack_res.removeLast() + tmp);
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
    //endregion

    //region    20230309    409. 最长回文串

    /**
     * https://leetcode.cn/problems/longest-palindrome/
     *
     * @param s 给定一个包含大写字母和小写字母的字符串 s
     * @return 通过这些字母构造成的最长的回文串
     */
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            } else {
                hashMap.put(s.charAt(i), 1);
            }
        }
        int res = 0, maxSinge = 0;
        for (Integer value : hashMap.values()) {
            if (value % 2 == 0) {
                res += value;
            } else {
                res += value / 2 * 2;
                maxSinge = 1;
            }
        }
        return res + maxSinge;
    }
    //endregion

    //region    20230316    424. 替换后的最长重复字符

    /**
     * https://leetcode.cn/problems/longest-repeating-character-replacement/
     *
     * @param s 字符串 s
     * @param k 整数 k
     * @return 含相同字母的最长子字符串的长度
     */
    public int characterReplacement(String s, int k) {
        int[] nums = new int[26];
        int n = s.length();
        int maxn = 0;
        //left:左边界，用于滑动时减去头部或者计算长度
        //right:右边界，用于加上滑动窗口的尾巴或者计算长度
        int left = 0, right = 0;
        while (right < n) {
            int indexR = s.charAt(right) - 'A';
            nums[indexR]++;
            //求窗口中曾出现某字母的最大次数
            //计算某字母出现在某窗口中的最大次数，窗口长度自能增大或者不变（注意后面lef指针只移动了0-1次）
            //这样做的意义：我们求的是最长，如果找不到更长的
            maxn = Math.max(maxn, nums[indexR]);
            //长度len=right-left+1,以下简称len
            //len-字母出现最大次数>替换数目 => len>字母出现最大次数+替换数目
            //分析一下，替换数目是不变的=k,字母出现最大次数可能是变化的，因此，只有字母出现最大次数增加的情况
            //有不满足条件的情况下，left和right一起移动，len不变的
            if (right - left + 1 - maxn > k) {
                //这里要减的，因为left越过该点，会对最大值有影响
                nums[s.charAt(left) - 'A']--;
                left++;
            }
            //走完这里的时候，其实right会多走一步
            right++;
        }
        //因为right多走一步，结果为（right-1）-left+1=right-left;
        return right - left;
    }
    //endregion

    //region    20230316    438. 找到字符串中所有字母异位词

    /**
     * https://leetcode.cn/problems/find-all-anagrams-in-a-string/
     *
     * @param s 字符串 s
     * @param p 字符串 p
     * @return 找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引
     */
    public List<Integer> findAnagrams(String s, String p) {
        int slen = s.length(), plen = p.length();
        if (slen < plen) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int[] count = new int[26];
        for (int i = 0; i < p.length(); i++) {
            ++count[s.charAt(i) - 'a'];
            --count[p.charAt(i) - 'a'];
        }
        int differ = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                ++differ;
            }
        }
        if (differ == 0) {
            res.add(0);
        }
        for (int i = 0; i < slen - plen; i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                --differ;
            } else if (count[s.charAt(i) - 'a'] == 0) {
                ++differ;
            }
            --count[s.charAt(i) - 'a'];
            if (count[s.charAt(i + plen) - 'a'] == -1) {
                --differ;
            } else if (count[s.charAt(i + plen) - 'a'] == 0) {
                ++differ;
            }
            ++count[s.charAt(i + plen) - 'a'];
            if (differ == 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
    //endregion

    //region    20230314    509. 斐波拉契数列

    /**
     * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给定 n ，请计算 F(n) 。
     * <p>
     * 示例 1：
     * 输入：n = 2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     * 示例 2：
     * 输入：n = 3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
     * 示例 3：
     * 输入：n = 4
     * 输出：3
     * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
     * <p>
     * 提示：
     * 0 <= n <= 30
     *
     * @param n 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列
     * @return 计算 F(n)
     */
    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    HashMap<Integer, Integer> hashMapfib = new HashMap<>();

    public int fib1(int n) {
        if (hashMapfib.containsKey(n)) {
            return hashMapfib.get(n);
        }
        if (n == 0 || n == 1) {
            return n;
        } else {
            hashMapfib.put(n, fib1(n - 1) + fib1(n - 2));
            return fib1(n - 1) + fib1(n - 2);
        }
    }
    //endregion

    //region    20230312    589. N 叉树的前序遍历

    /**
     * https://leetcode.cn/problems/n-ary-tree-preorder-traversal/
     *
     * @param root n 叉树的根节点  root
     * @return n 叉树的根节点  root
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; --i) {
                queue.push(node.children.get(i));
            }
        }
        return res;
    }
    //endregion

    //region    20230319    692. 前K个高频单词

    /**
     * https://leetcode.cn/problems/top-k-frequent-words/
     * @param words 单词列表 words
     * @param k 一个整数 k
     * @return  返回前 k 个出现次数最多的单词
     */
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word : words) {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> priorityQueue = new PriorityQueue<Map.Entry<String, Integer>>(
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o1.getValue() == o2.getValue() ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue();
                    }
                }
        );
        for (Map.Entry<String,Integer> entry:hashMap.entrySet()){
            priorityQueue.offer(entry);
            if(priorityQueue.size()>k){
                priorityQueue.poll();
            }
        }
        List<String> res=new ArrayList<>();
        while (!priorityQueue.isEmpty()){
            res.add(priorityQueue.poll().getKey());
        }
        Collections.reverse(res);
        return res;
    }
    //endregion

    //region    20230312    704. 二分查找

    /**
     * https://leetcode.cn/problems/binary-search/
     *
     * @param nums   一个 n 个元素有序的（升序）整型数组 nums
     * @param target 一个 n 个元素有序的（升序）整型数组 nums
     * @return 一个 n 个元素有序的（升序）整型数组 nums
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    //endregion

    //region    20230305    724. 寻找数组的中心下标

    /**
     * https://leetcode.cn/problems/find-pivot-index/
     *
     * @param nums 整数数组 nums
     * @return 计算数组的 中心下标
     */
    public int pivotIndex(int[] nums) {
        int[] pivot = new int[nums.length];
        pivot[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pivot[i] = pivot[i - 1] + nums[i];
        }
        int left = 0;
        for (int i = 0; i < pivot.length; i++) {
            if (i != 0) {
                left = pivot[i - 1];
            }
            if (pivot[nums.length - 1] - pivot[i] == left) {
                return i;
            }
        }
        return -1;
    }
    //endregion

    //region    20230313    733. 图像渲染

    /**
     * https://leetcode.cn/problems/flood-fill/
     *
     * @param image    一幅以 m x n 的二维整数数组表示的图画 image ，其中 image[i][j] 表示该图画的像素值大小
     * @param sr       整数 sr
     * @param sc       整数 sc
     * @param newColor 整数 newColor
     * @return 从像素 image[sr][sc] 开始对图像进行上色填充,返回经过上色渲染后的图像。
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        helper(image, sr, sc, newColor, image[sr][sc]);

        return image;

    }

    public void helper(int[][] image, int sr, int sc, int newColor, int oldColor) {

        if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || image[sr][sc] != oldColor || newColor == oldColor) {
            return;
        }

        image[sr][sc] = newColor;
        helper(image, sr - 1, sc, newColor, oldColor);
        helper(image, sr + 1, sc, newColor, oldColor);
        helper(image, sr, sc - 1, newColor, oldColor);
        helper(image, sr, sc + 1, newColor, oldColor);
    }
    //endregion

    //region   20230315    746. 使用最小花费爬楼梯

    /**
     * https://leetcode.cn/problems/min-cost-climbing-stairs/
     *
     * @param cost 整数数组 cost
     * @return 计算并返回达到楼梯顶部的最低花费
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = 0;
        dp[1] = Math.min(cost[0], cost[1]);
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i], dp[i - 2] + cost[i - 1]);
        }
        return dp[cost.length - 1];
    }
    // endregion

    //region    20230318    844. 比较含退格的字符串

    /**
     * https://leetcode.cn/problems/backspace-string-compare/
     *
     * @param s 字符串 s
     * @param t 字符串 t
     * @return 如果两者相等，返回 true
     */
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stackT = getStack(t);
        Stack<Character> stackS = getStack(s);
        while (!stackS.isEmpty() || !stackT.isEmpty()) {
            if (!stackS.isEmpty() && stackT.isEmpty()) {
                return false;
            }
            if (stackS.isEmpty() && !stackT.isEmpty()) {
                return false;
            }
            if (stackS.peek() != stackT.peek()) {
                return false;
            }
            stackS.pop();
            stackT.pop();
        }
        return true;
    }

    private Stack<Character> getStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                stack.push(s.charAt(i));
            }
            if (!stack.isEmpty() && s.charAt(i) == '#') {
                stack.pop();
            }
        }
        return stack;
    }
    //endregion

    //region    20230308    876. 链表的中间结点

    /**
     * https://leetcode.cn/problems/middle-of-the-linked-list/
     *
     * @param head 链表 head
     * @return 返回链表 head 的中间结点
     */
    public ListNode middleNode(ListNode head) {
        int nums = 0;
        ListNode listNode = head;
        while (listNode != null) {
            listNode = listNode.next;
            nums++;
        }
        int mid = nums / 2;
        while (mid != 0) {
            head = head.next;
            mid--;
        }
        return head;
    }
    //endregion

    //region    20230319    1046. 最后一块石头的重量

    /**
     * https://leetcode.cn/problems/last-stone-weight/
     *
     * @param stones 一堆石头 stones
     * @return 每次取最大的两个石头对撞，求剩余的石头 stone 重量
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> (b - a));
        for (int i = 0; i < stones.length; i++) {
            priorityQueue.offer(stones[i]);
        }
        while (priorityQueue.size() > 1) {
            int a = priorityQueue.poll();
            int b = priorityQueue.poll();
            if (a > b) {
                priorityQueue.offer(a - b);
            }
        }
        return priorityQueue.isEmpty() ? 0 : priorityQueue.poll();
    }
    //endregion

    //region    20230305    1480. 一维数组的动态和

    /**
     * https://leetcode.cn/problems/running-sum-of-1d-array/
     *
     * @param nums 数组 nums
     * @return 数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) . 请返回 nums 的动态和
     */
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 1) {
            return nums;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        return nums;
    }
    //endregion

    public static void main(String[] args) {
        new TargetLeetCode75Basic().longestPalindrome("abccccdd");
    }
}


