package StudyPlan;

import java.util.*;

/**
 * @author yyb
 * leetcode_studyplan_Lcof
 * leetcode 学习计划 剑指offer
 */
public class TargetLcof {

    //region    20230309    自定义类  树节点
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
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

    //region    20230313    剑指 Offer 46. 把数字翻译成字符串

    /**
     * https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
     *
     * @param num 一个数字 num
     * @return 按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法
     */
    public int translateNum(int num) {
        return 0;
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
     * @param s 字符串 s
     * @return 找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLength=0;
        HashSet<Character> hashSet=new HashSet<>();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if(i!=0){
                //左指令向右移动一格，一出一个字符
                hashSet.remove(s.charAt(i-1));
            }
            while (rk+1<s.length()&&!hashSet.contains(s.charAt(rk+1))){
                // 不断地移动右指针
                hashSet.add(s.charAt(rk + 1));
                ++rk;
            }
            //第i到rk个字符是一个极长无重复字符字串
            maxLength=Math.max(maxLength,rk-i+1);
        }
        return maxLength;
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

    public static void main(String[] args) {
        new TargetLcof().missingNumber(new int[]{0, 1, 3});
    }
}
