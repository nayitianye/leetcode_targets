package StudyPlan;

import javax.print.DocFlavor;
import javax.swing.plaf.nimbus.State;
import java.util.*;

/**
 * @author yyb
 * leetcode_studyplan_LeetCode_75_中等
 * leetcode 学习计划 LeetCode 75 Level2
 */
public class TargetLeetCode75Mid {

    //region    自定义数据结构
    public class Node {
        public int val;

        public Node left;
        public Node right;
        public Node next;
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

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 单链表
     */
    public class ListNode {
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

    /**
     * TreeNode 二叉树
     */
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
    //endregion

    //region    20230413    3. 无重复字符的最长子串

    /**
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters
     *
     * @param s 字符串 s
     * @return 找出其中不含有重复字符的最长子串的长度
     */
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        HashSet<Character> hashSet = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为-1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                //左指针向右移动一格，移除一个字符
                hashSet.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !hashSet.contains(s.charAt(rk + 1))) {
                hashSet.add(s.charAt(rk + 1));
                ++rk;
            }
            //第 i 到 rk 个字符是一个极长的无重复字符字串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
    //endregion

    //region    20230401    14. 最长公共前缀

    /**
     * https://leetcode.cn/problems/longest-common-prefix/
     *
     * @param strs 字符串数组 strs
     * @return 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < Math.min(res.length(), strs[i].length()); j++) {
                if (res.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            res = res.substring(0, j);
        }
        return res;
    }
    //endregion

    //region    20230413    16. 最接近的三数之和

    /**
     * https://leetcode.cn/problems/3sum-closest/
     *
     * @param nums   一个长度为 n 的整数数组 nums
     * @param target 一个目标值 target
     * @return 从 nums 中选出三个整数，使它们的和与 target 最接近
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if (sum > target)
                    end--;
                else if (sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }
    //endregion

    //region    20230402    19. 删除链表的倒数第 N 个结点

    /**
     * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
     *
     * @param head 头结点 head
     * @param n    删除倒数第 n 个结点
     * @return 返回删除倒数第 n 个结点的头结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
    //endregion

    //region    20230401    43. 字符串相乘
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        if (num1.equals("1")) {
            return num2;
        }
        if (num2.equals("1")) {
            return num1;
        }
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }
    //endregion

    //region    20230401    54. 螺旋矩阵

    /**
     * https://leetcode.cn/problems/spiral-matrix/
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int rows = matrix.length, colums = matrix[0].length;
        int left = 0, right = colums - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                res.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                res.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    res.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    res.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }
    //endregion

    //region    20230416    56. 合并区间

    /**
     * https://leetcode.cn/problems/merge-intervals/
     *
     * @param intervals 数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]
     * @return 合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间
     */
    public int[][] merge(int[][] intervals) {
        //1、先根据区间开始位置的大小，将 intervals 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] nums1, int[] nums2) {
                return nums1[0] - nums2[0];
            }
        });

        //这一段使用 lambda 表达式会更加高效
        //Arrays.sort(intervals , (nums1 , nums2)->nums1[0]-nums2[0]);

        //2、创建一个新的二维数组，用于保存合并后的区间
        int[][] res = new int[intervals.length][2];
        int index = 0;

        //3、开始合并区间
        for (int i = 0; i < intervals.length; i++) {
            //若是第一个区间，或者当前区间的起始位置 > 结果数组res中最后区间的终止位置（注意，结果数组res中最后区间的下标是 index-1）
            //则不需要将当前区间合并到上一个区间，那么当前区间可以赋值给ret，作为一个新的“合并基底”，将后面满足条件的区间向这个基底合并
            if (i == 0 || intervals[i][0] > res[index - 1][1])
                res[index++] = intervals[i];//同时将index+1
            else
                //否则，需要将当前区间合并到结果数组res的最后区间（即res中下标为index-1的区间），此时index不需要变化
                //此时需要改变 ret[index][1]，即ret[index] 数组的右边界可能扩展
                res[index - 1][1] = Math.max(res[index - 1][1], intervals[i][1]);
        }
        //res数组中实际上只有 index 个数组有效，其他都是 (0 , 0)，我们原先设置res长度为 intervals.length，需要将有效部分复制返回！
        return Arrays.copyOf(res, index);
    }
    //endregion

    //region    20230416    57. 插入区间

    /**
     * https://leetcode.cn/problems/insert-interval/
     *
     * @param intervals   一个无重叠的 ，按照区间起始端点排序的区间列表  intervals
     * @param newInterval 插入一个新的区间 newInterval
     * @return 确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        int len = intervals.length;
        while (i < len && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }
        while (i < len && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);
        while (i < len) {
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int[res.size()][]);
    }
    //endregion

    //region    20230416    76. 最小覆盖子串

    /**
     * https://leetcode.cn/problems/minimum-window-substring/
     *
     * @param s 一个字符串 s
     * @param t 一个字符串 t
     * @return 返回 s 中涵盖 t 所有字符的最小子串
     */
    public String minWindow(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();
    //endregion

    //region    20230414    100. 相同的树

    /**
     * https://leetcode.cn/problems/same-tree/
     *
     * @param p 二叉树的根节点 p
     * @param q 二叉树的根节点 q
     * @return 编写一个函数来检验这两棵树是否相同
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    //endregion

    //region    20230414    101. 对称二叉树

    /**
     * https://leetcode.cn/problems/symmetric-tree
     *
     * @param root 二叉树的根节点 root
     * @return 检查它是否轴对称
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        //调用递归函数，比较左节点，右节点
        return dfs(root.left, root.right);
    }

    boolean dfs(TreeNode left, TreeNode right) {
        //递归的终止条件是两个节点都为空
        //或者两个节点中有一个为空
        //或者两个节点的值不相等
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        //再递归的比较 左节点的左孩子 和 右节点的右孩子
        //以及比较  左节点的右孩子 和 右节点的左孩子
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
    //endregion

    //region    20230410    108. 将有序数组转换为二叉搜索树

    /**
     * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
     *
     * @param nums 整数数组 nums
     * @return 将其转换为一棵 高度平衡 二叉搜索树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }
    //endregion

    //region    20230405    110. 平衡二叉树

    /**
     * https://leetcode.cn/problems/balanced-binary-tree/
     *
     * @param root 一个二叉树根节点 root
     * @return 判断是否是平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(treeDepth(root.right) - treeDepth(root.left)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(treeDepth(root.left), treeDepth(root.right));
    }
    //endregion

    //region    20230403    148. 排序链表

    /**
     * https://leetcode.cn/problems/sort-list/
     *
     * @param head 链表的头结点 head
     * @return 将其按 升序 排列并返回 排序后的链表
     */
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
    //endregion

    //region    20230413    152. 乘积最大子数组

    /**
     * https://leetcode.cn/problems/maximum-product-subarray
     *
     * @param nums 一个整数数组 nums
     * @return 找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积
     */
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int[] maxdp = new int[length];
        int[] mindp = new int[length];
        System.arraycopy(nums, 0, maxdp, 0, length);
        System.arraycopy(nums, 0, mindp, 0, length);
        for (int i = 1; i < length; i++) {
            maxdp[i] = Math.max(maxdp[i - 1] * nums[i], Math.max(nums[i], mindp[i - 1] * nums[i]));
            mindp[i] = Math.min(mindp[i - 1] * nums[i], Math.min(nums[i], maxdp[i - 1] * nums[i]));
        }
        int ans = maxdp[0];
        for (int i = 1; i < length; i++) {
            ans = Math.max(ans, maxdp[i]);
        }
        return ans;
    }
    //endregion

    //region    20230416    155. 最小栈

    /**
     * https://leetcode.cn/problems/min-stack
     */
    class MinStack {

        private Stack<Integer> stack;

        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (!minStack.isEmpty()) {
                int top = minStack.peek();
                //小于的时候才入栈
                if (val <= top) {
                    minStack.push(val);
                }
            } else {
                minStack.push(val);
            }
        }

        public void pop() {
            int pop = stack.pop();
            int top = minStack.peek();
            if (pop == top) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
    //endregion

    //region    20230412    173. 二叉搜索树迭代器

    /**
     * https://leetcode.cn/problems/binary-search-tree-iterator
     */
    class BSTIterator {

        Deque<TreeNode> stack = new LinkedList<>();

        public BSTIterator(TreeNode root) {
            TreeNode node = root;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public int next() {
            TreeNode node = stack.pop();
            if (node.right != null) {
                TreeNode p = node.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
            }
            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
    //endregion

    //region    20230411    198. 打家劫舍

    /**
     * https://leetcode.cn/problems/house-robber/
     *
     * @param nums 一个代表每个房屋存放金额的非负整数数组 nums
     * @return 计算你不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
    //endregion

    //region    20230414    199. 二叉树的右视图

    /**
     * https://leetcode.cn/problems/binary-tree-right-side-view
     *
     * @param root 二叉树的 根节点 root
     * @return 返回从右侧所能看到的节点值
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }
    //endregion

    //region    20230401    202. 快乐数

    /**
     * https://leetcode.cn/problems/happy-number/
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        while (n != 1) {
            if (hashSet.contains(n)) {
                return false;
            }
            hashSet.add(n);
            n = getNextN(n);
        }
        return true;
    }

    public int getNextN(int n) {
        int res = 0;
        while (n != 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }
    //endregion

    //region    20230416    208. 实现 Trie (前缀树)

    /**
     * https://leetcode.cn/problems/implement-trie-prefix-tree
     */
    class Trie {

        private class Node {
            public boolean isWord;
            public TreeMap<Character, Node> next;

            public Node(boolean isWord) {
                this.isWord = isWord;
                next = new TreeMap<>();
            }

            public Node() {
                this(false);
            }
        }

        /**
         * Initialize your data structure here.
         */
        private Node root;
        private int size;

        public Trie() {
            root = new Node();
            size = 0;
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null) {
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            if (!cur.isWord) {
                cur.isWord = true;
                size++;
            }
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null) {
                    return false;
                }
                cur = cur.next.get(c);
            }
            return cur.isWord;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                if (cur.next.get(ch) == null) {
                    return false;
                }
                cur = cur.next.get(ch);
            }
            return true;
        }
    }
    //endregion

    //region    20230416    210. 课程表 II

    /**
     * https://leetcode.cn/problems/course-schedule-ii/
     *
     * @param numCourses    总共有 numCourses 门课需要选，记为 0 到 numCourses - 1
     * @param prerequisites 一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前必须先选修 bi
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        result = new int[numCourses];
        index = numCourses - 1;
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        //每次挑选一个【未搜索】 的节点，开始进行深度优先搜索
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0) {
                dfsfindOrder(i);
            }
        }
        if (!valid) {
            return new int[0];
        }
        //如果没有环，那么就有拓扑排序
        return result;
    }

    public void dfsfindOrder(int u) {
        //将节点标记为 搜索中
        visited[u] = 1;
        //搜索其相邻节点
        //只要发现有环，立即停止搜索
        for (int v : edges.get(u)) {
            //如果 未搜索 那么搜索相邻节点
            if (visited[v] == 0) {
                dfsfindOrder(v);
                if (!valid) {
                    return;
                }
            }
            //如果 搜索中 说明找到了环
            else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        // 将节点标记为 已完成
        visited[u] = 2;
        //将节点入栈
        result[index--] = u;
    }

    List<List<Integer>> edges;
    //标记每个节点的状态：0-未搜索，1=搜索中，2=已完成
    int[] visited;
    //用数组来模拟栈，下标n-1为栈底,0-为栈顶
    int[] result;
    //判断有向图中是否有环
    boolean valid = true;
    //栈下标
    int index;
    //endregion

    //region    20230405    226. 翻转二叉树

    /**
     * https://leetcode.cn/problems/invert-binary-tree/
     *
     * @param root 二叉树的根节点 root
     * @return 翻转这棵二叉树，并返回其根节点
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = root.left, right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }
    //endregion

    //region    20230418    227. 基本计算器 II

    /**
     * https://leetcode.cn/problems/basic-calculator-ii
     *
     * @param s 字符串表达式 s
     * @return 实现一个基本计算器来计算并返回它的值
     */
    public int calculate(String s) {
        // 将所有的空格去掉
        s = s.replaceAll(" ", "");
        char[] cs = s.toCharArray();
        int n = s.length();
        // 存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);
        // 存放所有「非数字以外」的操作
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNumber(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < n && isNumber(cs[j])) u = u * 10 + (cs[j++] - '0');
                    nums.addLast(u);
                    i = j - 1;
                } else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char prev = ops.peekLast();
                        if (map.get(prev) >= map.get(c)) {
                            calc(nums, ops);
                        } else {
                            break;
                        }
                    }
                    ops.addLast(c);
                }
            }
        }
        // 将剩余的计算完
        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peekLast();
    }

    void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        int ans = 0;
        if (op == '+') ans = a + b;
        else if (op == '-') ans = a - b;
        else if (op == '*') ans = a * b;
        else if (op == '/') ans = a / b;
        else if (op == '^') ans = (int) Math.pow(a, b);
        else if (op == '%') ans = a % b;
        nums.addLast(ans);
    }

    boolean isNumber(char c) {
        return Character.isDigit(c);
    }

    // 使用 map 维护一个运算符优先级
    // 这里的优先级划分按照「数学」进行划分即可
    Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
        put('%', 2);
        put('^', 3);
    }};
    //endregion

    //region    20230412    230. 二叉搜索树中第K小的元素

    /**
     * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
     *
     * @param root 二叉搜索树的根节点 root
     * @param k    个整数 k
     * @return 设计一个算法查找其中第 k 个最小元素
     */
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollFirst();
            if (priorityQueue.size() < k) {
                priorityQueue.add(node.val);
            } else if (priorityQueue.peek() > node.val) {
                priorityQueue.poll();
                priorityQueue.add(node.val);
            }
            if (node.left != null) {
                deque.addLast(node.left);
            }
            if (node.right != null) {
                deque.addLast(node.right);
            }
        }
        return priorityQueue.peek();
    }
    //endregion

    //region    20230416    232. 用栈实现队列

    /**
     * https://leetcode.cn/problems/implement-queue-using-stacks/
     */
    class MyQueue {

        Stack<Integer> inStack;
        Stack<Integer> outStack;

        public MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }

        public int peek() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }
    }
    //endregion

    //region    20230402    234. 回文链表

    /**
     * https://leetcode.cn/problems/palindrome-linked-list/
     *
     * @param head 单链表的头节点 head
     * @return 判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> res = new ArrayList<>();
        while (head != null) {
            res.add(head.val);
            head = head.next;
        }
        int front = 0, back = res.size() - 1;
        while (front < back) {
            if (res.get(front) != res.get(back)) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
    //endregion

    //region    20230403    328. 奇偶链表

    /**
     * https://leetcode.cn/problems/odd-even-linked-list/
     *
     * @param head 单链表的头节点 head
     * @return 将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
    //endregion

    //region    20230413    322. 零钱兑换

    /**
     * https://leetcode.cn/problems/coin-change/
     *
     * @param coins  一个整数数组 coins ，表示不同面额的硬币
     * @param amount 一个整数 amount ，表示总金额
     * @return 计算并返回可以凑成总金额所需的 最少的硬币个数
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i < max; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
    //endregion

    //region    20230416    416. 分割等和子集

    /**
     * https://leetcode.cn/problems/partition-equal-subset-sum/
     *
     * @param nums 一个 只包含正整数 的 非空 数组 nums
     * @return 判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        // 题目已经说非空数组，可以不做非空判断
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        // 创建二维状态数组，行：物品索引，列：容量（包括 0）
        boolean[][] dp = new boolean[len][target + 1];

        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        // 再填表格后面几行
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];

                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][target];
    }
    //endregion

    //region    20230416    417. 太平洋大西洋水流问题

    /**
     * https://leetcode.cn/problems/pacific-atlantic-water-flow
     *
     * @param heights 给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度
     * @return 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        g = heights;
        m = g.length;
        n = g[0].length;
        boolean[][] res1 = new boolean[m][n], res2 = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    if (!res1[i][j]) dfs(i, j, res1);
                }
                if (i == m - 1 || j == n - 1) {
                    if (!res2[i][j]) dfs(i, j, res2);
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (res1[i][j] && res2[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    void dfs(int x, int y, boolean[][] res) {
        res[x][y] = true;
        for (int[] di : dirs) {
            int nx = x + di[0], ny = y + di[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
            if (res[nx][ny] || g[nx][ny] < g[x][y]) continue;
            dfs(nx, ny, res);
        }
    }

    int n, m;
    int[][] g;
    //endregion

    //region    20230413    437. 路径总和 III

    /**
     * https://leetcode.cn/problems/path-sum-iii
     *
     * @param root 二叉树的根节点 root
     * @param sum  一个整数 targetSum
     * @return 求该二叉树里节点值之和等于 targetSum 的 路径 的数目
     */
    public int pathSum(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0L, 1);
        // 前缀和的递归回溯思路
        return recursionPathSum(root, prefixSumCount, sum, 0L);
    }

    /**
     * 前缀和的递归回溯思路
     * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
     * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
     * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
     *
     * @param node           树节点
     * @param prefixSumCount 前缀和Map
     * @param target         目标值
     * @param currSum        当前路径和
     * @return 满足题意的解
     */
    private int recursionPathSum(TreeNode node, Map<Long, Integer> prefixSumCount, int target, long currSum) {
        // 1.递归终止条件
        if (node == null) {
            return 0;
        }
        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;

        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
    //endregion

    //region    20230410    438. 找到字符串中所有字母异位词

    /**
     * https://leetcode.cn/problems/find-all-anagrams-in-a-string
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
            count[s.charAt(i) - 'a']++;
            count[p.charAt(i) - 'a']--;
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

    //region    20230413    543. 二叉树的直径

    /**
     * https://leetcode.cn/problems/diameter-of-binary-tree/
     *
     * @param root 根节点 root
     * @return 计算它的直径长度
     */
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans, L + R + 1);
        return Math.max(L, R) + 1;
    }

    int ans;
    //endregion

    //region    20230418    547. 省份数量

    /**
     * https://leetcode.cn/problems/number-of-provinces/
     * @param isConnected  给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
     * @return  返回矩阵中省份的数量。
     */
    public int findCircleNum(int[][] isConnected) {
        int cicties = isConnected.length;
        boolean[] visited = new boolean[cicties];
        int provinces = 0;
        for (int i = 0; i < cicties; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, cicties, i);
                provinces++;
            }
        }
        return provinces;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int cities, int i) {
        for (int j = 0; j < cities; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, cities, j);
            }
        }
    }
    //endregion

    //region    20230404    621. 任务调度器

    /**
     * https://leetcode.cn/problems/task-scheduler/
     *
     * @param tasks 字符数组 tasks 表示的 CPU 需要执行的任务列表
     * @param n     相同种类 的任务之间必须有长度为整数 n 的冷却时间
     * @return 计算完成所有任务所需要的 最短时间
     */
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        //最多的执行次数
        int maxExec = 0;
        for (char ch : tasks) {
            int exec = hashMap.getOrDefault(ch, 0) + 1;
            hashMap.put(ch, exec);
            maxExec = Math.max(maxExec, exec);
        }
        //具有最多执行次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = hashMap.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }
        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }
    //endregion

    //region    20230418    735. 行星碰撞

    /**
     * https://leetcode.cn/problems/asteroid-collision/
     *
     * @param asteroids 一个整数数组 asteroids，表示在同一行的行星
     * @return 找出碰撞后剩下的所有行星
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            boolean ok = true;
            while (ok && !deque.isEmpty() && deque.peekLast() > 0 && asteroid < 0) {
                int a = deque.peekLast();
                int b = -asteroid;
                if (a <= b) {
                    deque.pollLast();
                }
                if (a >= b) {
                    ok = false;
                }
            }
            if (ok) {
                deque.addLast(asteroid);
            }
        }
        int length = deque.size();
        int[] res = new int[length];
        while (!deque.isEmpty()) {
            res[--length] = deque.pollLast();
        }
        return res;
    }
    //endregion

    //region    20230416    815. 公交路线

    /**
     * https://leetcode.cn/problems/bus-routes/
     *
     * @param routes 一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶
     * @param source source 车站出发
     * @param target 前往 target 车站
     * @return 最少乘坐的公交车数量
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        int n = routes.length;
        boolean[][] edge = new boolean[n][n];
        Map<Integer, List<Integer>> rec = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> list = rec.getOrDefault(site, new ArrayList<Integer>());
                for (int j : list) {
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                rec.put(site, list);
            }
        }

        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Queue<Integer> que = new LinkedList<Integer>();
        for (int bus : rec.getOrDefault(source, new ArrayList<Integer>())) {
            dis[bus] = 1;
            que.offer(bus);
        }
        while (!que.isEmpty()) {
            int x = que.poll();
            for (int y = 0; y < n; y++) {
                if (edge[x][y] && dis[y] == -1) {
                    dis[y] = dis[x] + 1;
                    que.offer(y);
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int bus : rec.getOrDefault(target, new ArrayList<Integer>())) {
            if (dis[bus] != -1) {
                ret = Math.min(ret, dis[bus]);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
    //endregion

    //region    20230418    947. 移除最多的同行或同列石头

    /**
     * https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column
     * @param stones  一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置
     * @return  可以移除的石子 的最大数量
     */
    public int removeStones(int[][] stones) {
        int n = stones.length;
        List<List<Integer>> edge = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<Integer>());
            for (int j = 0; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    edge.get(i).add(j);
                }
            }
        }
        boolean[] vis = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                num++;
                dfs(i, edge, vis);
            }
        }
        return n - num;
    }

    public void dfs(int x, List<List<Integer>> edge, boolean[] vis) {
        vis[x] = true;
        for (int y : edge.get(x)) {
            if (!vis[y]) {
                dfs(y, edge, vis);
            }
        }
    }
    //endregion

    //region    20230413    994. 腐烂的橘子

    /**
     * https://leetcode.cn/problems/rotting-oranges
     *
     * @param grid 给定的 m x n 网格 grid 中
     * @return 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数
     */
    public int orangesRotting(int[][] grid) {
        int[] dr = new int[]{-1, 0, 1, 0};
        int[] dc = new int[]{0, -1, 0, 1};
        int row = grid.length, col = grid[0].length;
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    int code = i * col + j;
                    queue.add(code);
                    map.put(code, 0);
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int code = queue.remove();
            int r = code / col, c = code % col;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (0 <= nr && nr < row && 0 <= nc && nc < col && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    int ncode = nr * col + nc;
                    queue.add(ncode);
                    map.put(ncode, map.get(code) + 1);
                    ans = map.get(ncode);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return ans;
    }
    //endregion

    //region    20230401    1706. 球会落何处
    public int[] findBall(int[][] grid) {
        int n = grid[0].length;
        int[] res = new int[n];
        for (int i = 0; i < grid[0].length; i++) {
            int level = 0;
            int index = i;
            while (level < grid.length) {
                if (grid[level][index] == 1) {
                    int right = index + 1;
                    if (right < n && grid[level][right] == 1) {
                        index = right;
                    } else {
                        res[i] = -1;
                        break;
                    }
                } else {
                    int left = index - 1;
                    if (left >= 0 && grid[level][left] == -1) {
                        index = left;
                    } else {
                        res[i] = -1;
                        break;
                    }
                }
                level++;
            }
            if (level == grid.length) {
                res[i] = index;
            }
        }
        return res;
    }
    //endregion

    //region    20230404    2131. 连接两字母单词得到的最长回文串

    /**
     * https://leetcode.cn/problems/longest-palindrome-by-concatenating-two-letter-words/
     *
     * @param words 字符串数组 words
     * @return 返回你能得到的最长回文串的长度
     */
    public int longestPalindrome(String[] words) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            hashMap.put(words[i], hashMap.getOrDefault(words[i], 0) + 1);
        }
        int add = 0;
        int ans = 0;
        for (String s : hashMap.keySet()) {
            if (s.charAt(0) == s.charAt(1)) {
                ans += ((hashMap.get(s) >> 1) << 2);
                if (((hashMap.get(s) & 1) == 1)) {
                    add = 2;
                }
            } else {
                String t = pal(s);
                if (hashMap.containsKey(t)) {
                    ans += Math.min(hashMap.get(s), hashMap.get(t)) * 2;
                }
            }
        }
        return ans + add;
    }

    public String pal(String s) {
        return new StringBuilder(s).reverse().toString();
    }
    //endregion

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}, {1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}};
        new TargetLeetCode75Mid().findBall(matrix);
    }
}
