package StudyPlan;

import java.util.*;

/**
 * @author yyb
 * leetcode_studyplan_Lcof
 * leetcode 学习计划 剑指offer
 */
public class TargetLcof {

    //region    20230309    自定义类
    //二叉树
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //双向链表
    public class DoubleNode {
        public int val;
        public DoubleNode left;
        public DoubleNode right;

        public DoubleNode() {
        }

        public DoubleNode(int _val) {
            val = _val;
        }

        public DoubleNode(int _val, DoubleNode _left, DoubleNode _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    ;
    //endregion

    //region    20230307    剑指 Offer 03. 数组中重复的数字

    /**
     * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
     *
     * @param nums 数组 nums
     * @return 找出数组中的重复数字
     */
    public int findRepeatNumber(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                return num;
            } else {
                hashMap.put(num, 1);
            }
        }
        return -1;
    }
    //endregion

    //region    20230308    剑指 Offer 04. 二维数组中的查找

    /**
     * https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
     *
     * @param matrix 数组 matrix
     * @param target 目标值 target
     * @return 判断数组 matrix 是否存在目标值 target
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
    //endregion

    //region    20230306    剑指 Offer 05. 替换空格

    /**
     * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
     *
     * @param s 字符串 s
     * @return 字符串 s 中的每个空格替换成"%20"
     */
    public String replaceSpace(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                stringBuilder.append(s.charAt(i));
            } else {
                stringBuilder.append("%20");
            }
        }
        return stringBuilder.toString();
    }
    //endregion

    //region    20230305    剑指 Offer 06. 从尾到头打印链表
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
     *
     * @param head 链表的头节点
     * @return 尾到头反过来返回每个节点的值（用数组返回）
     */
    public int[] reversePrint(ListNode head) {
        LinkedList<Integer> stack = new LinkedList<>();
        while (head != null) {
            stack.addLast(head.val);
            head = head.next;
        }
        int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = stack.removeLast();
        }
        return res;
    }
    //endregion

    //region    20230322    剑指 Offer 07. 重建二叉树

    /**
     * https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof
     *
     * @param preorder 二叉树前序遍历数组 perorder
     * @param inorder  二叉树中序遍历数组 inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }
    //endregion

    //region    20230305    剑指 Offer 09. 用两个栈实现队列

    /**
     * https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
     */
    static class CQueue {
        //两个栈，一个出栈，一个入栈
        private final Stack<Integer> stack1;
        private final Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            } else {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.isEmpty() ? -1 : stack2.pop();
            }
        }
    }

    //endregion

    //region    20230312    剑指 Offer 10- I. 斐波那契数列

    /**
     * https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
     *
     * @param n 输入 n
     * @return 求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        if (hashMap.containsKey(n)) {
            return hashMap.get(n);
        }
        hashMap.put(n, (fib(n - 1) + fib(n - 2)) % 1000000007);
        return hashMap.get(n);
    }

    HashMap<Integer, Integer> hashMap = new HashMap<>();
    //endregion

    //region    20230312    剑指 Offer 10- II. 青蛙跳台阶问题

    /**
     * https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
     *
     * @param n 青蛙跳上一个 n 级的台阶
     * @return 有多少种跳法
     */
    public int numWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (hashMap1.containsKey(n)) {
            return hashMap1.get(n);
        } else {
            hashMap1.put(n, (numWays(n - 1) + numWays(n - 2)) % 1000000007);
        }
        return hashMap1.get(n);
    }

    HashMap<Integer, Integer> hashMap1 = new HashMap<>();
    //endregion

    //region    20230308    剑指 Offer 11. 旋转数组的最小数字

    /**
     * https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
     *
     * @param numbers 旋转数组 nums
     * @return 旋转数组 nums 中的最小数字
     */
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                right -= 1;
            }
        }
        return numbers[left];
    }
    //endregion

    //region    20230317    剑指 Offer 12. 矩阵中的路径

    /**
     * https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/
     *
     * @param board 二维字符网格 board
     * @param word  字符串单词 word
     * @return 字符串单词 word
     */
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfsExist(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfsExist(char[][] board, char[] words, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != words[k]) {
            return false;
        }
        if (k == words.length - 1) {
            return true;
        }
        board[i][j] = '\0';
        boolean res = dfsExist(board, words, i + 1, j, k + 1) || dfsExist(board, words, i - 1, j, k + 1) || dfsExist(board, words, i, j + 1, k + 1) || dfsExist(board, words, i, j - 1, k + 1);
        board[i][j] = words[k];
        return res;
    }
    //endregion

    //region    20230318    面试题13. 机器人的运动范围

    /**
     * https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
     *
     * @param m m行
     * @param n n列
     * @param k 行坐标和列坐标的数位之和小于等于k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        boolean[][] vis = new boolean[m][n];
        int ans = 1;
        vis[0][0] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == 0 && j == 0) || get(i) + get(j) > k) {
                    continue;
                }
                //边界值判断
                if (i - 1 >= 0) {
                    vis[i][j] |= vis[i - 1][j];
                }
                if (j - 1 >= 0) {
                    vis[i][j] |= vis[i][j - 1];
                }
                ans += vis[i][j] ? 1 : 0;
            }
        }
        return ans;
    }

    public int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }
    //endregion、

    //region    20230327    剑指 Offer 14- I. 剪绳子

    /**
     * https://leetcode.cn/problems/jian-sheng-zi-lcof/
     *
     * @param n 长度为 n 的绳子
     * @return 请把绳子剪成整数长度的 m 段,并返回最大乘积。
     */
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }
    //endregion

    //region    20230403    剑指 Offer 14- II. 剪绳子 II

    /**
     * https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/
     *
     * @param n 长度为 n 的绳子
     * @return 请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少
     */
    public int cuttingRope1(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int b = n % 3, p = 1000000007;
        long rem = 1, x = 3;
        for (int i = n / 3 - 1; i > 0; i /= 2) {
            if (i % 2 == 1) {
                rem = (rem * x) % p;
            }
            x = (x * x) % p;
        }
        if (b == 0) {
            return (int) (rem * 3 % p);
        }
        if (b == 1) {
            return (int) (rem * 4 % p);
        }
        return (int) (rem * 6 % p);
    }
    //endregion

    //region    20230324    剑指 Offer 15. 二进制中1的个数

    /**
     * https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
     *
     * @param n 一个无符号整数（以二进制串的形式） n
     * @return 返回其二进制表达式中数字位数为 '1' 的个数
     */
    public int hammingWeight(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }
    //endregion

    //region    20230322    剑指 Offer 16. 数值的整数次方

    /**
     * https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
     *
     * @param x 数字 x
     * @param n n 次方
     * @return 计算 x 的 n 次幂函数
     */
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }
    //endregion

    //region    20230402    剑指 Offer 17. 打印从1到最大的n位数

    /**
     * https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
     *
     * @param n 数字 n
     * @return 顺序打印出从 1 到最大的 n 位十进制数
     */
    public int[] printNumbers(int n) {
        int end = (int) Math.pow(10, n) - 1;
        int[] res = new int[end];
        for (int i = 0; i < end; i++) {
            res[i] = i + 1;
        }
        return res;
    }
    //endregion

    //region    20230315    剑指 Offer 18. 删除链表的节点

    /**
     * https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/
     *
     * @param head 链表头节点 head
     * @param val  节点值 val
     * @return 删除后的链表的头节点
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        } else {
            head.next = deleteNode(head.next, val);
        }
        return head;
    }
    //endregion

    //region    20230401    剑指 Offer 19. 正则表达式匹配

    /**
     * https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/
     *
     * @param s 字符串 s
     * @param p 字符串 p
     * @return 现一个函数用来匹配包含'. '和'*'的正则表达式
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
    //endregion

    //region    20230329    剑指 Offer 20. 表示数值的字符串

    /**
     * https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
     *
     * @param s 字符串 s
     * @return 判断字符串是否表示数值
     */
    public boolean isNumber(String s) {
        Map[] states = {new HashMap<Character, Integer>() {{
            put(' ', 0);
            put('s', 1);
            put('d', 2);
            put('.', 4);
        }}, // 0.
                new HashMap<Character, Integer>() {{
                    put('d', 2);
                    put('.', 4);
                }},                           // 1.
                new HashMap<Character, Integer>() {{
                    put('d', 2);
                    put('.', 3);
                    put('e', 5);
                    put(' ', 8);
                }}, // 2.
                new HashMap<Character, Integer>() {{
                    put('d', 3);
                    put('e', 5);
                    put(' ', 8);
                }},              // 3.
                new HashMap<Character, Integer>() {{
                    put('d', 3);
                }},                                        // 4.
                new HashMap<Character, Integer>() {{
                    put('s', 6);
                    put('d', 7);
                }},                           // 5.
                new HashMap<Character, Integer>() {{
                    put('d', 7);
                }},                                        // 6.
                new HashMap<Character, Integer>() {{
                    put('d', 7);
                    put(' ', 8);
                }},                           // 7.
                new HashMap<Character, Integer>() {{
                    put(' ', 8);
                }}                                         // 8.
        };
        int p = 0;
        char t;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') t = 'd';
            else if (c == '+' || c == '-') t = 's';
            else if (c == 'e' || c == 'E') t = 'e';
            else if (c == '.' || c == ' ') t = c;
            else t = '?';
            if (!states[p].containsKey(t)) return false;
            p = (int) states[p].get(t);
        }
        return p == 2 || p == 3 || p == 7 || p == 8;
    }
    //endregion

    //region    20230316    剑指 Offer 21. 调整数组顺序使奇数位于偶数前面

    /**
     * https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
     *
     * @param nums 一个整数数组 nums
     * @return 一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
     */
    public int[] exchange(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 == 1) {
                left++;
                continue;
            }
            if (nums[right] % 2 == 0) {
                right--;
                continue;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
        return nums;
    }
    //endregion

    //region    20230315    剑指 Offer 22. 链表中倒数第k个节点

    /**
     * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/solutions/
     *
     * @param head 链表头节点 head  单指针法
     * @param k    倒数 k 个节点
     * @return 返回倒数 k 个节点
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        count = count - k;
        while (count > 0) {
            head = head.next;
            count--;
        }
        return head;
    }

    /**
     * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/solutions/
     *
     * @param head 链表头节点 head  双指针法
     * @param k    倒数 k 个节点
     * @return 返回倒数 k 个节点
     */
    public ListNode getKthFromEnd1(ListNode head, int k) {
        ListNode former = head, latter = head;
        for (int i = 0; i < k; i++) {
            former = former.next;
        }
        while (former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }
    //endregion

    //region    20230305    剑指 Offer 24. 反转链表

    /**
     * https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/
     *
     * @param head 一个链表的头节点
     * @return 反转该链表并输出反转后链表的头节点
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

    //region    20230315    剑指 Offer 25. 合并两个排序的链表

    /**
     * https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
     *
     * @param l1 有序链表 l1
     * @param l2 有序链表 l2
     * @return 返回链表 l1 和 l2 合并后的链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    //endregion

    //region    20230312    剑指 Offer 26. 树的子结构

    /**
     * https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/
     *
     * @param A 二叉树 A
     * @param B 二叉树 B
     * @return 判断B是不是A的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 若A与B其中一个为空,立即返回false
        if (A == null || B == null) {
            return false;
        }
        // B为A的子结构有3种情况,满足任意一种即可:
        // 1.B的子结构起点为A的根节点,此时结果为recur(A,B)
        // 2.B的子结构起点隐藏在A的左子树中,而不是直接为A的根节点,此时结果为isSubStructure(A.left, B)
        // 3.B的子结构起点隐藏在A的右子树中,此时结果为isSubStructure(A.right, B)
        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    /*
    判断B是否为A的子结构,其中B子结构的起点为A的根节点
    */
    private boolean recur(TreeNode A, TreeNode B) {
        // 若B走完了,说明查找完毕,B为A的子结构
        if (B == null) {
            return true;
        }
        // 若B不为空并且A为空或者A与B的值不相等,直接可以判断B不是A的子结构
        if (A == null || A.val != B.val) {
            return false;
        }
        // 当A与B当前节点值相等,若要判断B为A的子结构
        // 还需要判断B的左子树是否为A左子树的子结构 && B的右子树是否为A右子树的子结构
        // 若两者都满足就说明B是A的子结构,并且该子结构以A根节点为起点
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
    //endregion

    //region    20230312    剑指 Offer 27. 二叉树的镜像

    /**
     * https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/
     *
     * @param root 二叉树的根结点
     * @return 返回二叉树的镜像
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
    //endregion

    //region    20230312    剑指 Offer 28. 对称的二叉树

    /**
     * https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/
     *
     * @param root 二叉树根结点 root
     * @return 一棵二叉树和它的镜像一样，那么它是对称的
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    //endregion

    //region    20230328    剑指 Offer 29. 顺时针打印矩阵

    /**
     * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
     *
     * @param matrix 矩阵 matrix
     * @return 按照从外向里以顺时针的顺序依次打印出每一个数字
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[] order = new int[rows * columns];
        int index = 0;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                for (int row = bottom; row > top; row--) {
                    order[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
    //endregion

    //region    20230305    剑指 Offer 30. 包含min函数的栈

    /**
     * https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/
     */
    static class MinStack {

        private final Stack<Integer> xStack;
        private final Stack<Integer> minStack;

        public MinStack() {
            xStack = new Stack<>();
            minStack = new Stack<>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            xStack.push(x);
            minStack.push(Math.min(minStack.peek(), x));
        }

        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        public int top() {
            return xStack.peek();
        }

        public int min() {
            return minStack.peek();
        }
    }
    //endregion

    //region    20230328    剑指 Offer 31. 栈的压入、弹出序列

    /**
     * https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
     *
     * @param pushed 入栈序列
     * @param popped 出栈序列
     * @return 判断第二个序列是否为该栈的弹出顺序
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = pushed.length;
        for (int i = 0, j = 0; i < n; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
    //endregion

    //region    20230309    剑指 Offer 32 - I. 从上到下打印二叉树

    /**
     * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
     *
     * @param root 树的根节点
     * @return https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>() {{
            add(root);
        }};
        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
    //endregion

    //region    20230309    剑指 Offer 32 - II. 从上到下打印二叉树 II

    /**
     * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
     *
     * @param root 树的根节点 root
     * @return 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }
    //endregion

    //region    20230309    剑指 Offer 32 - III. 从上到下打印二叉树 III

    /**
     * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
     *
     * @param root 树的根节点 root
     * @return 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;
    }
    //endregion

    //region    20230324    剑指 Offer 33. 二叉搜索树的后序遍历序列

    /**
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
     *
     * @param postorder 一个整数数组 postorder
     * @return 判断该数组是不是某二叉搜索树的后序遍历结果
     */
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    public boolean recur(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        int p = i;
        while (postorder[p] < postorder[j]) {
            p++;
        }
        int m = p;
        while (postorder[p] > postorder[j]) {
            p++;
        }
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }
    //endregion

    //region    20230318    剑指 Offer 34. 二叉树中和为某一值的路径

    /**
     * https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
     *
     * @param root   二叉树的根节点 root
     * @param target 整数目标和 targetSum
     * @return 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        recur(root, target);
        return res;
    }

    public void recur(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(path));
        }
        recur(root.left, target);
        recur(root.right, target);
        path.removeLast();
    }

    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    //endregion

    //region    20230305    剑指 Offer 35. 复杂链表的复制
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    HashMap<Node, Node> cachedNode = new HashMap<>();

    /**
     * https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/
     *
     * @param head 复杂链表
     * @return 复制一个复杂链表
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!cachedNode.containsKey(head)) {
            Node headNew = new Node(head.val);
            cachedNode.put(head, headNew);
            headNew.next = copyRandomList(head.next);
            headNew.random = copyRandomList(head.random);
        }
        return cachedNode.get(head);
    }
    //endregion

    //region    20230318    剑指 Offer 36. 二叉搜索树与双向链表
    DoubleNode head, pre;

    /**
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
     *
     * @param root 二叉搜索树 root
     * @return 二叉搜索树转换成一个排序的循环双向链表
     */
    public DoubleNode treeToDoublyList(DoubleNode root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        pre.right = head;
        head.left = pre;//进行头节点和尾节点的相互指向，这两句的顺序也是可以颠倒的
        return head;
    }

    public void dfs(DoubleNode cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.left);
        //pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
        if (pre == null) {
            head = cur;
        }//反之，pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作。
        else {
            pre.right = cur;
        }
        cur.left = pre;//pre是否为null对这句没有影响,且这句放在上面两句if else之前也是可以的。
        pre = cur;//pre指向当前的cur
        dfs(cur.right);//全部迭代完成后，pre指向双向链表中的尾节点
    }
    //endregion

    //region    20230331    剑指 Offer 37. 序列化二叉树

    /**
     * https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof/
     */
    public class Codec {
        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        public TreeNode deserialize(String data) {
            String[] dataArray = data.split(",");
            List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
            return rdeserialize(dataList);
        }

        public String rserialize(TreeNode root, String str) {
            if (root == null) {
                str += "None,";
            } else {
                str += str.valueOf(root.val) + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        public TreeNode rdeserialize(List<String> dataList) {
            if (dataList.get(0).equals("None")) {
                dataList.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
            dataList.remove(0);
            root.left = rdeserialize(dataList);
            root.right = rdeserialize(dataList);

            return root;
        }
    }
    //endregion

    //region    20230331    剑指 Offer 38. 字符串的排列

    /**
     * https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof
     *
     * @param s 一个字符串 s
     * @return 打印出该字符串中字符的所有排列
     */
    public String[] permutation(String s) {
        result = new ArrayList<>();
        visited = new boolean[s.length()];
        char[] payloads = s.toCharArray();
        Arrays.sort(payloads);
        permuate(payloads, "");

        return result.stream().toArray(String[]::new);
    }

    private void permuate(char[] s, String p) {
        if (p.length() == s.length) {
            result.add(p);
            return;
        }
        for (int i = 0; i < s.length; i++) {
            if (visited[i] || (i > 0 && s[i] == s[i - 1] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            permuate(s, p + s[i]);
            visited[i] = false;
        }
    }

    private List<String> result;
    private boolean[] visited;
    //endregion

    //region    20230326    剑指 Offer 39. 数组中出现次数超过一半的数字

    /**
     * https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof
     *
     * @param nums 整数数组 nums
     * @return 返回数组中超过一半的数字
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
    //endregion

    //region    20230320    剑指 Offer 40. 最小的k个数

    /**
     * https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/
     *
     * @param arr 整数数组 arr
     * @param k   最小的 k 个数
     * @return 最小的 k 个数组成的数组
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k >= arr.length) {
            return arr;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(arr[i]);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }
    //endregion

    //region    20303020    剑指 Offer 41. 数据流中的中位数

    /**
     * https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
     */
    private class MedianFinder {

        PriorityQueue<Integer> queueMin;
        PriorityQueue<Integer> queueMax;

        public MedianFinder() {
            queueMin = new PriorityQueue<>((a, b) -> (b - a));
            queueMax = new PriorityQueue<>((a, b) -> (a - b));
        }

        public void addNum(int num) {
            if (queueMin.isEmpty() || num <= queueMin.peek()) {
                queueMin.offer(num);
                if (queueMax.size() + 1 < queueMin.size()) {
                    queueMax.offer(queueMin.poll());
                }
            } else {
                queueMax.offer(num);
                if (queueMax.size() > queueMin.size()) {
                    queueMin.offer(queueMax.poll());
                }
            }
        }

        public double findMedian() {
            if (queueMin.size() > queueMax.size()) {
                return queueMin.peek();
            }
            return (queueMin.peek() + queueMax.peek()) / 2.0;
        }
    }
    //endregion

    //region    20230312    剑指 Offer 42. 连续子数组的最大和
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    //endregion

    //region    20230403    剑指 Offer 43. 1～n 整数中 1 出现的次数

    /**
     * https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
     * @param n 一个整数 n
     * @return  求1～n这n个整数的十进制表示中1出现的次数
     */
    public int countDigitOne(int n) {
        // mulk 表示 10^k
        // 在下面的代码中，可以发现 k 并没有被直接使用到（都是使用10^k）
        // 但为了让代码看起来更加直观，这里保留了k
        long mulk = 1;
        int ans = 0;
        for (int i = 0; n >= mulk; i++) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }
    //endregion

    //region    20230403    剑指 Offer 44. 数字序列中某一位的数字

    /**
     * https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/
     *
     * @param n 第n位
     * @return 求任意第n位对应的数字
     */
    public int findNthDigit(int n) {
        int d = 1, count = 9;
        while (n > (long) d * count) {
            n -= d * count;
            d++;
            count *= 10;
        }
        int index = n - 1;
        int start = (int) Math.pow(10, d - 1);
        int num = start + index / d;
        int digitIndex = index % d;
        int digit = (num / (int) Math.pow(10, d - digitIndex - 1)) % 10;
        return digit;
    }
    //endregion

    //region    20230319    面试题45. 把数组排成最小的数

    /**
     * https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/description/
     *
     * @param nums 数组 nums
     * @return 返回数组组成的最小字符串
     */
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            res.append(str);
        }
        return res.toString();
    }
    //endregion

    //region    20230319    剑指 Offer 46. 把数字翻译成字符串

    /**
     * https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
     *
     * @param num 一个数字 num
     * @return 按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法
     */
    public int translateNum(int num) {
        char[] ch = String.valueOf(num).toCharArray();
        int len = ch.length;
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= len; i++) {
            int n = (ch[i - 2] - '0') * 10 + (ch[i - 1] - '0');
            if (n >= 10 && n <= 25) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[len];
    }
    //endregion

    //region    20230312    剑指 Offer 47. 礼物的最大价值

    /**
     * https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/
     *
     * @param grid 一个 m*n 的棋盘的每一格都放有一个礼物
     * @return 最多能拿到多少价值的礼物
     */
    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                }
                if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
                }
                dp[i][j] += grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
    //endregion

    //region    20230316    剑指 Offer 48. 最长不含重复字符的子字符串

    /**
     * https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
     *
     * @param s 字符串 s
     * @return 找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        HashSet<Character> hashSet = new HashSet<>();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) {
                //左指令向右移动一格，一出一个字符
                hashSet.remove(s.charAt(i - 1));
            }
            while (rk + 1 < s.length() && !hashSet.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                hashSet.add(s.charAt(rk + 1));
                ++rk;
            }
            //第i到rk个字符是一个极长无重复字符字串
            maxLength = Math.max(maxLength, rk - i + 1);
        }
        return maxLength;
    }
    //endregion

    //region    20230401    剑指 Offer 49. 丑数

    /**
     * https://leetcode.cn/problems/chou-shu-lcof
     *
     * @param n 第 n 个丑数
     * @return 从小到大的顺序的第 n 个丑数
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
    //endregion

    //region    20230308    剑指 Offer 50. 第一个只出现一次的字符

    /**
     * https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
     *
     * @param s 字符串 s
     * @return 返回字符串第一个只出现一次的字符
     */
    public char firstUniqChar(String s) {
        HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (frequency.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }
    //endregion

    //region    20230402    剑指 Offer 51. 数组中的逆序对

    /**
     * https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
     *
     * @param nums 数组 nums
     * @return 求出这个数组中的逆序对的总数
     */
    public int reversePairs(int[] nums) {
        int len = nums.length;

        if (len < 2) {
            return 0;
        }

        int[] copy = new int[len];
        for (int i = 0; i < len; i++) {
            copy[i] = nums[i];
        }

        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {

            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                count += (mid - i + 1);
            }
        }
        return count;
    }
    //endregion

    //region    20230315    剑指 Offer 52. 两个链表的第一个公共节点

    /**
     * https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
     *
     * @param headA 链表 headA
     * @param headB 链表 headB
     * @return 找出链表 headA 和链表 headB 的公共头节点的值
     */
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
    //endregion

    //region    20230307    剑指 Offer 53 - I. 在排序数组中查找数字 I

    /**
     * https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
     *
     * @param nums   排序数组 nums
     * @param target 目标值
     * @return 目标值的个数
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
                int tempLeft = mid - 1;
                int tempRight = mid + 1;
                while (tempRight <= nums.length - 1 && nums[tempRight] == target) {
                    tempRight++;
                }
                while (tempLeft >= 0 && nums[tempLeft] == target) {
                    tempLeft--;
                }
                return tempRight - tempLeft - 1;
            }
        }
        return 0;
    }
    //endregion

    //region    20230307    剑指 Offer 53 - II. 0～n-1中缺失的数字

    /**
     * https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/
     *
     * @param nums 0-n-1 数组
     * @return 数组中缺失的数字
     */
    public int missingNumber(int[] nums) {
        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] - mid == 0) {
                left = mid + 1;
            } else if (nums[mid] - mid == 1) {
                right = mid - 1;
            }
        }
        return nums[left] - left == 0 ? left + 1 : left;
    }
    //endregion

    //region    20230318    剑指 Offer 54. 二叉搜索树的第k大节点

    /**
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
     *
     * @param root 二叉搜索树根节点 root
     * @param k    第 k 大的节点
     * @return 第 k 大的节点的值
     */
    public int kthLargest(TreeNode root, int k) {
        count = k;
        preOrder(root);
        return res1;
    }

    public void preOrder(TreeNode root) {
        if (root == null || count <= 0) {
            return;
        }
        preOrder(root.right);
        if (--count == 0) {
            res1 = root.val;
        }
        preOrder(root.left);
    }

    int count, res1;
    //endregion

    //region    20230321    剑指 Offer 55 - I. 二叉树的深度

    /**
     * https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/
     *
     * @param root 二叉树的根节点 root
     * @return 该树的最长深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    //endregion

    //region    20230321    剑指 Offer 55 - II. 平衡二叉树

    /**
     * https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/description/
     *
     * @param root 二叉树的根节点 root
     * @return 判断它是否为平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }
    //endregion

    //region    20230325    剑指 Offer 56 - I. 数组中数字出现的次数

    /**
     * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
     *
     * @param nums 整型数组 nums
     * @return 找出这两个只出现一次的数字
     */
    public int[] singleNumbers(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        int div = 1;
        while ((div & res) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }
    //endregion

    //region    20230325    剑指 Offer 56 - II. 数组中数字出现的次数 II

    /**
     * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
     *
     * @param nums 数组 nums
     * @return 请找出那个只出现一次的数字
     */
    public int singleNumber(int[] nums) {
        int[] counts = new int[32];
        for (int num : nums) {
            for (int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0, m = 3;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % m;
        }
        return res;
    }
    //endregion

    //region    20230316    剑指 Offer 57. 和为s的两个数字

    /**
     * https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/
     *
     * @param nums   数组 nums
     * @param target 数字s
     * @return 数组中查找两个数，使得它们的和正好是s
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) {
                break;
            }
            if (findNum(nums, target - nums[i])) {
                return new int[]{nums[i], target - nums[i]};
            }
        }
        return new int[]{-1, -1};
    }

    public boolean findNum(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
    //endregion

    //region    20230327    剑指 Offer 57 - II. 和为s的连续正数序列

    /**
     * https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
     *
     * @param target 正整数 target
     * @return 输出所有和为 target 的连续正整数序列（至少含有两个数）
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> vec = new ArrayList<int[]>();
        for (int left = 1, right = 2; left < right; ) {
            int sum = (left + right) * (right - left + 1) / 2;
            if (sum == target) {
                int[] res = new int[right - left + 1];
                for (int i = left; i <= right; i++) {
                    res[i - left] = i;
                }
                vec.add(res);
                left++;
            } else if (sum < target) {
                right++;
            } else {
                left++;
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }
    //endregion

    //region    20230316    剑指 Offer 58 - I. 翻转单词顺序

    /**
     * https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/
     *
     * @param s 输入字符串s
     * @return 反转字符串
     */
    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
    //endregion

    //region    20230306    剑指 Offer 58 - II. 左旋转字符串

    /**
     * https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
     *
     * @param s 字符串 s
     * @param n 数字 n
     * @return 字符串左旋后的字符串
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
    //endregion

    //region    20230330    剑指 Offer 59 - I. 滑动窗口的最大值

    /**
     * https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
     *
     * @param nums 一个数组 nums
     * @param k    滑动窗口的大小 k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] res = new int[n - k + 1];
        res[0] = pq.peek()[0];
        for (int i = k; i < n; i++) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            res[i - k + 1] = pq.peek()[0];
        }
        return res;
    }
    //endregion

    //region    20230330    面试题59 - II. 队列的最大值

    /**
     * https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof
     */
    class MaxQueue {

        int[] q = new int[20000];
        int begin = 0, end = 0;

        public MaxQueue() {

        }

        public int max_value() {
            int ans = -1;
            for (int i = begin; i != end; ++i) {
                ans = Math.max(ans, q[i]);
            }
            return ans;
        }

        public void push_back(int value) {
            q[end++] = value;
        }

        public int pop_front() {
            if (begin == end) {
                return -1;
            }
            return q[begin++];
        }
    }
    //endregion

    //region    20230401    剑指 Offer 60. n个骰子的点数

    /**
     * https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/
     *
     * @param n 输入n
     * @return 打印出s的所有可能的值出现的概率
     */
    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += dp[j] / 6.0;
                }
            }
            dp = temp;
        }
        return dp;
    }
    //endregion

    //region    20230327    剑指 Offer 62. 圆圈中最后剩下的数字

    /**
     * https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
     *
     * @param n 0-n-1 n个数字
     * @param m 每轮删除第m个数字
     * @return 最后剩下的数字
     */
    public int lastRemaining(int n, int m) {
        return fn(n, m);
    }

    public int fn(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = fn(n - 1, m);
        return (m + x) % n;
    }
    //endregion

    //region    20230312    剑指 Offer 63. 股票的最大利润
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxPrice = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxPrice) {
                maxPrice = prices[i] - minPrice;
            }
        }
        return maxPrice;
    }
    //endregion

    //region    20230322    剑指 Offer 64. 求1+2+…+n

    /**
     * https://leetcode.cn/problems/qiu-12n-lcof/
     *
     * @param n 求1+2+…+n
     * @return
     */
    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }
    //endregion

    //region    20230324    剑指 Offer 65. 不用加减乘除做加法

    /**
     * https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
     *
     * @param a 整数 a
     * @param b 整数 b
     * @return 求两个整数之和
     */
    public int add(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
    //endregion

    //region    20230319    面试题61. 扑克牌中的顺子

    /**
     * https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
     *
     * @param nums 数组 nums 表示随机的 5 张扑克牌
     * @return 判断5张牌是不是连续的
     */
    public boolean isStraight(int[] nums) {
        Set<Integer> repeat = new HashSet<>();
        int max = 0, min = 14;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            max = Math.max(max, num);
            min = Math.min(min, num);
            if (repeat.contains(num)) {
                return false;
            }
            repeat.add(num);
        }
        return max - min < 5;
    }
    //endregion

    //region    20230326    剑指 Offer 66. 构建乘积数组

    /**
     * https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/
     *
     * @param a 数组 a
     * @return 建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]
     */
    public int[] constructArr(int[] a) {
        int length = a.length;
        //L和R分别表示左右两侧的乘积列表
        int[] L = new int[length];
        int[] R = new int[length];

        int[] answer = new int[length];

        if (length == 0) {
            return answer;
        }
        //L[i] 为索引i左侧所有元素的乘积
        //对于索引为0的元素，因为左侧没有元素，所以L[0]=1
        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = a[i - 1] * L[i - 1];
        }

        //R[i] 为索引i右侧所有元素的乘积
        //对于索引为 length-1 的元素，因为右侧没有元素，所以R[length-1]=1
        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            R[i] = a[i + 1] * R[i + 1];
        }
        //对于索引 i,除 a[i] 之外其余各元素的乘积
        for (int i = 0; i < length; i++) {
            answer[i] = R[i] * L[i];
        }
        return answer;
    }
    //endregion

    //region    20230329    面试题67. 把字符串转换成整数

    /**
     * https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof
     *
     * @param str 字符串
     * @return 一个函数 StrToInt，实现把字符串转换成整数这个功能
     */
    public int strToInt(String str) {
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 0, sign = 1, length = str.length();
        if (length == 0) {
            return 0;
        }
        while (str.charAt(i) == ' ') {
            if (++i == length) {
                return 0;
            }
        }
        if (str.charAt(i) == '-') {
            sign = -1;
        }
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            i++;
        }
        for (int j = i; j < length; j++) {
            if (str.charAt(j) < '0' || str.charAt(j) > '9') {
                break;
            }
            if (res > bndry || res == bndry && str.charAt(j) > '7') {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (str.charAt(j) - '0');
        }
        return sign * res;
    }
    //endregion

    //region    20230322    剑指 Offer 68 - I. 二叉搜索树的最近公共祖先

    /**
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof
     *
     * @param root 二叉树根结点 root
     * @param p    结点 p
     * @param q    结点 q
     * @return 该树中两个指定节点的最近公共祖先
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

    //region    20230322    剑指 Offer 68 - II. 二叉树的最近公共祖先

    /**
     * https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
     *
     * @param root 二叉树根结点 root
     * @param p    结点 p
     * @param q    结点 q
     * @return 该树中两个指定节点的最近公共祖先且 x 的深度尽可能大
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null; // 1.
        }
        if (left == null) {
            return right; // 3.
        }
        if (right == null) {
            return left; // 4.
        }
        return root; // 2. if(left != null and right != null)
    }
    //endregion

    public static void main(String[] args) {
        new TargetLcof().missingNumber(new int[]{0, 1, 3});
    }
}
