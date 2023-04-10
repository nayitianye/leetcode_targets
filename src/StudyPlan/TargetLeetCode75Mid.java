package StudyPlan;

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

    //region    20230410    438. 找到字符串中所有字母异位词

    /**
     * https://leetcode.cn/problems/find-all-anagrams-in-a-string
     * @param s  字符串 s
     * @param p  字符串 p
     * @return  找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引
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
            ++count[s.charAt(i + plen )- 'a'];
            if (differ == 0) {
                res.add(i + 1);
            }
        }
        return res;
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
