package StudyPlan;

import java.util.*;

/**
 * @author yyb
 * leetcode_studyplan_data_structures_Beginners
 * leetcode 学习计划 数据结构入门
 */
public class TargetDataStructuresBeginner {

    //region    自定义数据接口

    /**
     * ListNode 链表类
     */
    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
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

    //region    20230305    1. 两数之和

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

    //region    20230312    20. 有效的括号

    /**
     * https://leetcode.cn/problems/valid-parentheses/
     *
     * @param s 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s
     * @return 判断字符串是否有效
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (s.charAt(i) == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else if (s.charAt(i) == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
    //endregion

    //region    20230312    21. 合并两个有序链表

    /**
     * https://leetcode.cn/problems/merge-two-sorted-lists/
     *
     * @param list1 链表 list1
     * @param list2 链表 list2
     * @return 返回 list1 和 list2 合并后的链表
     */
    public ListNode MergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = MergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = MergeTwoLists(list1, list2.next);
            return list2;
        }
    }
    //endregion

    //region    20230308    36. 有效的数独

    /**
     * https://leetcode.cn/problems/valid-sudoku/
     *
     * @param board 数组 board
     * @return 判断数组是否符合数独的形式
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //endregion

    //region    20230305    53. 最大子数组和

    /**
     * https://leetcode.cn/problems/maximum-subarray/
     *
     * @param nums 整数数组 nums
     * @return 找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
     */
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        //dp 数组表示以nums[i]结尾的最大数组
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    //endregion

    //region    20230308    73. 矩阵置零

    /**
     * https://leetcode.cn/problems/set-matrix-zeroes/
     *
     * @param board 数组 board
     *              将数组中值为 0 的行和列的值置为 0 并返回
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    //endregion

    //region    20230312    83. 删除排序链表中的重复元素

    /**
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
     *
     * @param head 已排序的链表的头 head
     * @return 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        ListNode curr = head;
        hashMap.put(curr.val, 1);
        while (curr.next != null) {
            if (hashMap.containsKey(curr.next.val)) {
                curr.next = curr.next.next;
            } else {
                hashMap.put(curr.next.val, 1);
                curr = curr.next;
            }
        }
        return head;
    }
    //endregion

    //region    20230305    88. 合并两个有序数组

    /**
     * https://leetcode.cn/problems/merge-sorted-array/
     *
     * @param nums1 递减顺序排列的整数数组 nums1
     * @param m     表示 nums1 中的元素数目
     * @param nums2 递减顺序排列的整数数组 nums2
     * @param n     表示 nums1 中的元素数目
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len1 = m - 1;
        int len2 = n - 1;
        int len = m + n - 1;
        while (len1 >= 0 && len2 >= 0) {
            //注意--符号再后面，表示先进行计算再减1，这种缩写缩短了代码
            nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
        }
        // 表示将nums2数组从下标0位置开始，拷贝到nums1数组中，从下标0位置开始，长度为len2+1
        System.arraycopy(nums2, 0, nums1, 0, len2 + 1);
    }
    //endregion

    //region    20230315    94. 二叉树的中序遍历

    /**
     * https://leetcode.cn/problems/binary-tree-inorder-traversal/
     *
     * @param root 二叉树的根节点 root
     * @return 二叉树的根节点 root
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
    //endregion

    //region    20230317    98. 验证二叉搜索树

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

    //region    20230315    101. 对称二叉树

    /**
     * https://leetcode.cn/problems/symmetric-tree/description/
     *
     * @param root 二叉树的根节点 root
     * @return 判断他是否轴对称
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

    //region    20230315    102. 二叉树的层序遍历

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

    //region    20230315    104. 二叉树的最大深度

    /**
     * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
     *
     * @param root 二叉树 root
     * @return 返回其最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }
    //endregion

    //region    20230315    112. 路径总和

    /**
     * https://leetcode.cn/problems/path-sum/
     *
     * @param root      二叉树根节点 root
     * @param targetSum 目标值 targetSum
     * @return 判断二叉树根节点 root 到叶子节点的所有节点值是否等于目标值
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        } else {
            return hasPathSum(root.right, targetSum - root.val) || hasPathSum(root.left, targetSum - root.val);
        }
    }
    //endregion

    //region    20230307    118. 杨辉三角

    /**
     * https://leetcode.cn/problems/pascals-triangle/
     *
     * @param numRows 杨辉三角的行数
     * @return 返回n行的杨辉三角组成的list
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(res.get(i - 1).get(j) + res.get(i - 1).get(j - 1));
                }
            }
            res.add(list);
        }
        return res;
    }
    //endregion

    //region    20230306    121. 买卖股票的最佳时机

    /**
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
     *
     * @param prices 数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格
     * @return 返回你可以从这笔交易中获取的最大利润
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

    //region    20230312    141. 环形链表

    /**
     * https://leetcode.cn/problems/linked-list-cycle/
     *
     * @param head 链表的头节点 head
     * @return 判断链表中是否有环
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || slow == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    //endregion

    //region    20230315    144. 二叉树的前序遍历

    /**
     * https://leetcode.cn/problems/binary-tree-preorder-traversal/
     *
     * @param root 二叉树的根节点 root
     * @return 返回它节点值的 前序 遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }
    //endregion

    //region    20230315    145. 二叉树的后序遍历

    /**
     * https://leetcode.cn/problems/binary-tree-postorder-traversal/description/
     *
     * @param root 二叉树的根节点 root
     * @return 返回其节点值的 后序遍历
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    public void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }
    //endregion

    //region    20230312    203. 移除链表元素

    /**
     * https://leetcode.cn/problems/remove-linked-list-elements/
     *
     * @param head 链表的头节点 head
     * @param val  一个整数 val
     * @return 删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     */
    public ListNode removeElements(ListNode head, int val) {
        //创建一个虚拟头结点
        ListNode dummyNode = new ListNode(val - 1);
        dummyNode.next = head;
        ListNode prev = dummyNode;
        //确保当前结点后还有结点
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummyNode.next;
    }
    //endregion

    //region    20230312    206. 反转链表

    /**
     * https://leetcode.cn/problems/reverse-linked-list/
     *
     * @param head 单链表的头节点 head
     * @return 反转链表，并返回反转后的链表
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

    //region    20230305    217. 存在重复元素

    /**
     * https://leetcode.cn/problems/contains-duplicate/
     *
     * @param nums 整数数组 nums
     * @return 如果任一数值在数组中出现至少两次 ，返回 true ；如果数组中每个元素互不相同 ，返回 false 。
     */
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                return true;
            } else {
                hashMap.put(num, 1);
            }
        }
        return false;
    }
    //endregion

    //region    20230315    226. 翻转二叉树

    /**
     * https://leetcode.cn/problems/invert-binary-tree/
     *
     * @param root 二叉树根节点 root
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

    //region    20230312    232. 用栈实现队列

    /**
     * https://leetcode.cn/problems/implement-queue-using-stacks/
     * 用栈实现队列
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

    //region    20230317    235. 二叉搜索树的最近公共祖先

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

    //region    20230309    242. 有效的字母异位词

    /**
     * https://leetcode.cn/problems/valid-anagram/
     *
     * @param s 字符串 s
     * @param t 字符串 t
     * @return 判断 t 是否是 s 的字母异位词
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            } else {
                hashMap.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (hashMap.containsKey(t.charAt(i))) {
                if (hashMap.get(t.charAt(i)) == 1) {
                    hashMap.remove(t.charAt(i));
                } else {
                    hashMap.put(t.charAt(i), hashMap.get(t.charAt(i)) - 1);
                }
            } else {
                return false;
            }
        }
        return hashMap.isEmpty();
    }
    //endregion

    //region    20230306    350. 两个数组的交集 II

    /**
     * https://leetcode.cn/problems/intersection-of-two-arrays-ii/
     *
     * @param nums1 整数数组 nums1
     * @param nums2 整数数组 nums2
     * @return 以数组形式返回两数组的交集
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums2[index2] < nums1[index1]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
    //endregion

    //region    20230309    383. 赎金信

    /**
     * https://leetcode.cn/problems/ransom-note/
     *
     * @param ransomNote 字符串：ransomNote
     * @param magazine   字符串：magazine
     * @return 判断 ransomNote 能不能由 magazine 里面的字符构成
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            if (hashMap.containsKey(magazine.charAt(i))) {
                hashMap.put(magazine.charAt(i), hashMap.get(magazine.charAt(i)) + 1);
            } else {
                hashMap.put(magazine.charAt(i), 1);
            }
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (hashMap.containsKey(ransomNote.charAt(i))) {
                if (hashMap.get(ransomNote.charAt(i)) == 1) {
                    hashMap.remove(ransomNote.charAt(i));
                } else {
                    hashMap.put(ransomNote.charAt(i), hashMap.get(ransomNote.charAt(i)) - 1);
                }
            } else {
                return false;
            }
        }
        return true;
    }
    //endregion

    //region    20230309    387. 字符串中的第一个唯一字符

    /**
     * https://leetcode.cn/problems/first-unique-character-in-a-string/
     *
     * @param s 给定一个字符串 s
     * @return 找到 它的第一个不重复的字符，并返回它的索引
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            } else {
                hashMap.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
    //endregion

    //region    20230307    566. 重塑矩阵

    /**
     * https://leetcode.cn/problems/reshape-the-matrix/
     *
     * @param mat 数组 mat
     * @param r   r 行
     * @param c   c 列
     * @return 将数组 mat 重塑成 r 行 c 列的数组
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }

        int[][] ans = new int[r][c];
        for (int x = 0; x < m * n; ++x) {
            ans[x / c][x % c] = mat[x / n][x % n];
        }
        return ans;
    }
    //endregion

    //region    20230317    653. 两数之和 IV - 输入二叉搜索树

    /**
     * https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/
     *
     * @param root 二叉搜索树 root
     * @param k    一个目标结果 k
     * @return 如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 true
     */
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (integerHashSet.contains(k - root.val)) {
            return true;
        }
        integerHashSet.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    HashSet<Integer> integerHashSet = new HashSet<>();
    //endregion

    //region    20230316    700. 二叉搜索树中的搜索

    /**
     * https://leetcode.cn/problems/search-in-a-binary-search-tree/
     *
     * @param root 二叉搜索树（BST）的根节点 root
     * @param val  一个整数值 val
     * @return 一个整数值 val
     */
    public TreeNode searchBST(TreeNode root, int val) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            if (treeNode == null) {
                continue;
            }
            if (treeNode.val == val) {
                return treeNode;
            }
            stack.push(treeNode.left);
            stack.push(treeNode.right);
        }
        return null;
    }
    //endregion

    //region    20230316    701. 二叉搜索树中的插入操作

    /**
     * https://leetcode.cn/problems/insert-into-a-binary-search-tree/description/
     *
     * @param root 二叉搜索树（BST）的根节点 root
     * @param val  插入树中的值 value
     * @return 将值插入二叉搜索树。 返回插入后二叉搜索树的根节点
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode pos = root;
        while (pos != null) {
            if (val < pos.val) {
                if (pos.left == null) {
                    pos.left = new TreeNode(val);
                    break;
                } else {
                    pos = pos.left;
                }
            } else {
                if (pos.right == null) {
                    pos.right = new TreeNode(val);
                    break;
                } else {
                    pos = pos.right;
                }
            }
        }
        return root;
    }
    //endregion

    //region    20230320    709. 转换成小写字母

    /**
     * https://leetcode.cn/problems/to-lower-case/
     *
     * @param s 字符串 s
     * @return 字符串中的大写字母转换成相同的小写字母，返回新的字符串
     */
    public String toLowerCase(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= 'Z' && s.charAt(i) >= 'A') {
                char ch = s.charAt(i);
                ch |= 32;
                res.append(ch);
            } else {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
    //endregion

    //region    20230320    953. 验证外星语词典

    /**
     * https://leetcode.cn/problems/verifying-an-alien-dictionary/
     *
     * @param words 外星语书写的单词 words
     * @param order 其字母表的顺序 order
     * @return 当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < order.length(); i++) {
            index[order.charAt(i) - 'a'] = i;
        }
        for (int i = 1; i < words.length; i++) {
            boolean valid=false;
            for (int j = 0; j < words[i-1].length() && j<words[i].length(); j++) {
                int prev=index[words[i-1].charAt(j)-'a'];
                int curr=index[words[i].charAt(j)-'a'];
                if(prev<curr){
                    valid=true;
                    break;
                } else if (prev>curr) {
                    return false;
                }
            }
            if(!valid){
                if(words[i-1].length()>words[i].length()){
                    return false;
                }
            }
        }
        return true;
    }
    //endregion

    //region    20230320    1309. 解码字母到整数映射

    /**
     * https://leetcode.cn/problems/decrypt-string-from-alphabet-to-integer-mapping/
     *
     * @param s 字符串 s
     * @return 字符串 s
     */
    public String freqAlphabets(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
                res.append((char) ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '1') + 'a'));
                i += 2;
            } else {
                res.append((char) (s.charAt(i) - '1' + 'a'));
            }
        }
        return res.toString();
    }
    //endregion

    public static void main(String[] args) {
    }
}
