package StudyPlan;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import java.util.*;

/**
 * @author yyb
 * leetcode_studyplan_ProgrammingSkillsBasic
 * leetcode 学习计划 编程能力基础
 */
public class TargetProgrammingSkillsBasic {

    //region    自定义数据结构

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

    class Node {
        public int val;

        Node next;
        Node random;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
            this.next = null;
            this.random = null;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

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
    //endregion

    //region    20230419    2. 两数相加

    /**
     * https://leetcode.cn/problems/add-two-numbers/
     *
     * @param l1 链表 l1
     * @param l2 链表 l2
     * @return 两个数相加，并以相同形式返回一个表示和的链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
    //endregion

    //region    20230419    17. 电话号码的字母组合

    /**
     * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
     *
     * @param digits 一个仅包含数字 2-9 的字符串 digits
     * @return 返回所有它能表示的字母组合
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
    //endregion

    //region    20230419    22. 括号生成

    /**
     * https://leetcode.cn/problems/generate-parentheses/
     *
     * @param n 数字 n 代表生成括号的对数
     * @return 设计一个函数，用于能够生成所有可能的并且 有效的 括号组合
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        dfs("", 0, 0, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号已经用了几个
     * @param right  右括号已经用了几个
     * @param n      左括号、右括号一共得用几个
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }

        if (left < n) {
            dfs(curStr + "(", left + 1, right, n, res);
        }
        if (right < n) {
            dfs(curStr + ")", left, right + 1, n, res);
        }
    }
    //endregion

    //region    20230405    28. 找出字符串中第一个匹配项的下标

    /**
     * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string
     *
     * @param haystack 字符串 haystack
     * @param needle   字符串 needle
     * @return 在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）
     */
    public int strStr(String haystack, String needle) {
        for (int i = 0; i < haystack.length(); i++) {
            int start = i;
            for (int j = 0; j < needle.length(); j++) {
                if (start < haystack.length() && haystack.charAt(start) == needle.charAt(j)) {
                    start++;
                } else {
                    break;
                }
            }
            if (start - i == needle.length()) {
                return i;
            }
        }
        return -1;
    }
    //endregion

    //region    20230410    43. 字符串相乘

    /**
     * https://leetcode.cn/problems/multiply-strings/
     *
     * @param num1 非负整数 num1
     * @param num2 非负整数 num2
     * @return 返回 num1 和 num2 的乘积
     */
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

    //region    20230417    48. 旋转图像

    /**
     * https://leetcode.cn/problems/rotate-image
     *
     * @param matrix 一个 n × n 的二维矩阵 matrix 表示一个图像,将图像顺时针旋转 90 度
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }
    //endregion

    //region    20230417    49. 字母异位词分组

    /**
     * https://leetcode.cn/problems/group-anagrams
     *
     * @param strs 一个字符串数组 strs
     * @return 字母异位词 组合在一起
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            //将每个出现次数大于0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    stringBuffer.append((char) ('a' + i));
                    stringBuffer.append(counts[i]);
                }
            }
            String key = stringBuffer.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
    //endregion

    //region    20230417    54. 螺旋矩阵

    /**
     * https://leetcode.cn/problems/spiral-matrix/
     *
     * @param matrix 一个 m 行 n 列的矩阵 matrix
     * @return 按照 顺时针螺旋顺序 ，返回矩阵中的所有元素
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

    //region    20230410    58. 最后一个单词的长度

    /**
     * https://leetcode.cn/problems/length-of-last-word
     *
     * @param s 字符串 s
     * @return 返回字符串中最后一个单词的长度
     */
    public int lengthOfLastWord(String s) {
        String[] strings = s.trim().split(" ");
        return strings[strings.length - 1].length();
    }
    //endregion

    //region    20230420    61. 旋转链表

    /**
     * https://leetcode.cn/problems/rotate-list/
     *
     * @param head 链表的头节点 head
     * @param k    旋转链表，将链表每个节点向右移动 k 个位置
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }
    //endregion

    //region    20230409    66. 加一

    /**
     * https://leetcode.cn/problems/plus-one
     *
     * @param digits 由整数组成的非空数组 digits 所表示的非负整数
     * @return 该数的基础上加一
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            int res = digits[i] + 1;
            if (res == 10) {
                digits[i] = 0;
            } else {
                digits[i] = res;
                break;
            }
        }
        if (digits[0] == 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        }
        return digits;
    }
    //endregion

    //region    20230409    67. 二进制求和

    /**
     * https://leetcode.cn/problems/add-binary/
     *
     * @param a 二进制字符串 a
     * @param b 二进制字符串 b
     * @return 二进制字符串的形式返回它们的和
     */
    public String addBinary(String a, String b) {
        int indexa = a.length() - 1;
        int indexb = b.length() - 1;
        StringBuffer res = new StringBuffer();
        int flag = 0;
        while (indexa >= 0 && indexb >= 0) {
            if (a.charAt(indexa) == '1' && b.charAt(indexb) == '1') {
                if (flag == 0) {
                    res.append("0");
                } else {
                    res.append("1");
                }
                flag = 1;
            } else if ((a.charAt(indexa) == '1' && b.charAt(indexb) == '0') ||
                    a.charAt(indexa) == '0' && b.charAt(indexb) == '1') {
                if (flag == 0) {
                    res.append("1");
                } else {
                    res.append("0");
                }
            } else {
                if (flag == 0) {
                    res.append("0");
                } else {
                    res.append("1");
                }
                flag = 0;
            }
            indexa--;
            indexb--;
        }
        while (indexa >= 0) {
            if (a.charAt(indexa) == '1' && flag == 1) {
                res.append("0");
            } else if (a.charAt(indexa) == '0' && flag == 1) {
                res.append("1");
                flag = 0;
            } else {
                res.append(a.charAt(indexa));
            }
            indexa--;
        }

        while (indexb >= 0) {
            if (b.charAt(indexb) == '1' && flag == 1) {
                res.append("0");
            } else if (b.charAt(indexb) == '0' && flag == 1) {
                res.append("1");
                flag = 0;
            } else {
                res.append(b.charAt(indexb));
            }
            indexb--;
        }
        if (flag == 1) {
            res.append("1");
        }
        return res.reverse().toString();
    }
    //endregion

    //region    20230406    110. 平衡二叉树

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

    //region    20230420    138. 复制带随机指针的链表

    /**
     * https://leetcode.cn/problems/copy-list-with-random-pointer/
     *
     * @param head 链表头节点 head
     * @return 复制链表中的指针都不应指向原链表中的节点
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node p = head;
        //第一步，在每个原节点后面创建一个新节点
        //1->1'->2->2'->3->3'
        while (p != null) {
            Node newNode = new Node(p.val);
            newNode.next = p.next;
            p.next = newNode;
            p = newNode.next;
        }
        p = head;
        //第二步，设置新节点的随机节点
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }
        Node dummy = new Node(-1);
        p = head;
        Node cur = dummy;
        //第三步，将两个链表分离
        while (p != null) {
            cur.next = p.next;
            cur = cur.next;
            p.next = cur.next;
            p = p.next;
        }
        return dummy.next;
    }
    //endregion

    //region    20230418    143. 重排链表

    /**
     * https://leetcode.cn/problems/reorder-list/
     *
     * @param head 单链表 L 的头节点 head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
    //endregion

    //region    20230409    150. 逆波兰表达式求值

    /**
     * https://leetcode.cn/problems/evaluate-reverse-polish-notation
     *
     * @param tokens 字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式
     * @return 计算该表达式并返回一个表示表达式值的整数
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stacks = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (!isCalculateString(tokens[i])) {
                stacks.push(Integer.parseInt(tokens[i]));
            } else {
                int a = stacks.pop();
                int b = stacks.pop();
                int result = 0;
                if (tokens[i].equals("+")) {
                    result = b + a;
                } else if (tokens[i].equals("-")) {
                    result = b - a;
                } else if (tokens[i].equals("*")) {
                    result = b * a;
                } else {
                    result = a == 0 ? 0 : b / a;
                }
                stacks.push(result);
            }
        }
        return stacks.pop();
    }

    public boolean isCalculateString(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
    //endregion

    //region    20230420    173. 二叉搜索树迭代器

    /**
     * https://leetcode.cn/problems/binary-search-tree-iterator/
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

    //region    20230417    304. 二维区域和检索 - 矩阵不可变

    /**
     * https://leetcode.cn/problems/range-sum-query-2d-immutable/
     */
    class NumMatrix {

        int[][] sums;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            if (m > 0) {
                int n = matrix[0].length;
                sums = new int[m][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        sums[i][j + 1] = sums[i][j] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum += sums[i][col2 + 1] - sums[i][col1];
            }
            return sum;
        }
    }
    //endregion

    //region    20230417    429. N 叉树的层序遍历

    /**
     * https://leetcode.cn/problems/n-ary-tree-level-order-traversal/
     *
     * @param root 一个 N 叉树根节点 root
     * @return 返回其前序遍历
     */
    public List<List<Integer>> levelOrder(Node root) {
        helpLevelOrder(root, 0);
        return res;
    }

    public void helpLevelOrder(Node root, int level) {
        if (root == null) {
            return;
        }
        if (res.size() < level + 1) {
            List<Integer> levelList = new ArrayList<>();
            res.add(levelList);
        }
        res.get(level).add(root.val);
        for (Node node : root.children) {
            helpLevelOrder(node, level + 1);
        }
    }

    private List<List<Integer>> res = new ArrayList<>();
    //endregion

    //region    20230417    438. 找到字符串中所有字母异位词

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

    //region    20230419    445. 两数相加 II

    /**
     * https://leetcode.cn/problems/add-two-numbers-ii
     *
     * @param l1 链表 l1
     * @param l2 链表 l2
     * @return 两个数相加，并以相同形式返回一个表示和的链表
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<Integer>();
        Deque<Integer> stack2 = new ArrayDeque<Integer>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode ans = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            int cur = a + b + carry;
            carry = cur / 10;
            cur %= 10;
            ListNode curnode = new ListNode(cur);
            curnode.next = ans;
            ans = curnode;
        }
        return ans;
    }
    //endregion

    //region    20230406    459. 重复的子字符串

    /**
     * https://leetcode.cn/problems/repeated-substring-pattern/
     *
     * @param s 非空的字符串 s
     * @return 检查是否可以通过由它的一个子串重复多次构成
     */
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
    //endregion

    //region    20230420    503. 下一个更大元素 II

    /**
     * https://leetcode.cn/problems/next-greater-element-ii/
     *
     * @param nums 一个循环数组 nums
     * @return 返回 nums 中每个元素的 下一个更大元素
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return res;
    }
    //endregion

    //region    20230420    556. 下一个更大元素 III

    /**
     * https://leetcode.cn/problems/next-greater-element-iii/
     *
     * @param n 一个正整数 n
     * @return 找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n
     */
    public int nextGreaterElement(int n) {
        char[] s = String.valueOf(n).toCharArray();
        for (int i = s.length - 1; i > 0; i--) {
            Arrays.sort(s, i, s.length);
            for (int j = i; j < s.length; j++) {
                if (s[j] > s[i - 1]) {
                    char t = s[i - 1];
                    s[i - 1] = s[j];
                    s[j] = t;
                    long ans = Long.parseLong(String.valueOf(s));
                    if (ans > Integer.MAX_VALUE) {
                        return -1;
                    }
                    return (int) ans;
                }
            }
        }
        return -1;
    }
    //endregion

    //region    20230417    713. 乘积小于 K 的子数组

    /**
     * https://leetcode.cn/problems/subarray-product-less-than-k
     *
     * @param nums 一个整数数组 nums
     * @param k    一个整数 k
     * @return 返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        //同样排除k为1的情况比如  [1,1,1] k=1
        if (k <= 1) {
            return 0;
        }
        int left = 0;
        int right = 0;
        //创建一个变量记录路上的乘积
        int mul = 1;
        //记录连续数组的组合个数
        int ans = 0;

        //用右指针遍历整个数组，每次循环右指针右移一次
        while (right < nums.length) {
            //记录乘积
            mul *= nums[right];
            //当大于等于k，左指针右移并把之前左指针的数除掉
            while (mul >= k) {
                mul /= nums[left];
                left++;
            }

            //每次右指针位移到一个新位置，应该加上 x 种数组组合：
            //  nums[right]
            //  nums[right-1], nums[right]
            //  nums[right-2], nums[right-1], nums[right]
            //  nums[left], ......, nums[right-2], nums[right-1], nums[right]
            //共有 right - left + 1 种
            ans += right - left + 1;

            //右指针右移
            right++;
        }
        return ans;
    }
    //endregion

    //region    20230410    739. 每日温度

    /**
     * https://leetcode.cn/problems/daily-temperatures/
     *
     * @param temperatures 整数数组 temperatures
     * @return 一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                res[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return res;
    }
    //endregion

    //region    20230405    896. 单调数列

    /**
     * https://leetcode.cn/problems/monotonic-array
     *
     * @param nums 数组 nums
     * @return 单调数组时返回 true，否则返回 false
     */
    public boolean isMonotonic(int[] nums) {
        boolean isAdd = nums[nums.length - 1] - nums[0] > 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 0) {
                continue;
            }
            if (isAdd && nums[i] - nums[i - 1] < 0) {
                return false;
            }
            if (!isAdd && nums[i] - nums[i - 1] > 0) {
                return false;
            }
        }
        return true;
    }
    //endregion

    //region    20230420    910. 最小差值 II

    /**
     * https://leetcode.cn/problems/smallest-range-ii/
     *
     * @param nums 一个整数数组 nums
     * @param k    一个整数 k
     * @return 在更改每个下标对应的值之后，返回 nums 的最小 分数
     */
    public int smallestRangeII(int[] nums, int k) {
        int length = nums.length;
        Arrays.sort(nums);
        int res = nums[length - 1] - nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            int a = nums[i], b = nums[i + 1];
            int high = Math.max(nums[length - 1] - k, a + k);
            int low = Math.min(nums[0] + k, b - k);
            res = Math.min(res, high - low);
        }
        return res;
    }
    //endregion

    //region    20230420    973. 最接近原点的 K 个点

    /**
     * https://leetcode.cn/problems/k-closest-points-to-origin/
     *
     * @param points 一个数组 points
     * @param k      一个整数 k
     * @return 返回离原点 (0,0) 最近的 k 个点
     */
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] array1, int[] array2) {
                return array2[0] - array1[0];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        int n = points.length;
        for (int i = k; i < n; ++i) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (dist < pq.peek()[0]) {
                pq.poll();
                pq.offer(new int[]{dist, i});
            }
        }
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; ++i) {
            ans[i] = points[pq.poll()[1]];
        }
        return ans;
    }
    //endregion

    //region    20230409    989. 数组形式的整数加法

    /**
     * https://leetcode.cn/problems/add-to-array-form-of-integer/
     *
     * @param num 数组形式  num 是按照从左到右的顺序表示其数字的数组
     * @param k   整数 k
     * @return 返回 整数 num + k 的 数组形式
     */
    public List<Integer> addToArrayForm(int[] num, int k) {
        int n = num.length;
        List<Integer> res = new ArrayList<>();  // 可以用 LinkeList，或者 ArrayList 往后加，最后反转
        int i = n - 1, sum = 0, carry = 0;
        while (i >= 0 || k != 0) {  // 循环条件：两个数有一个没完
            int x = i >= 0 ? num[i] : 0;
            int y = k != 0 ? k % 10 : 0;

            sum = x + y + carry;
            carry = sum / 10;
            k = k / 10;

            i--;
            res.add(0, sum % 10);
        }
        if (carry != 0) {
            res.add(0, carry);
        }
        return res;
    }
    //endregion

    //region    20230420    1367. 二叉树中的链表

    /**
     * https://leetcode.cn/problems/linked-list-in-binary-tree/
     *
     * @param head 以 root 为根的二叉树
     * @param root 一个 head 为第一个节点的链表
     * @return 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        //先判断当前的节点，如果不对，再看左子树和右子树呗
        return isSub(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean isSub(ListNode head, TreeNode node) {
        //特判：链表走完了，返回true
        if (head == null) {
            return true;
        }
        //特判：链表没走完，树走完了，这肯定不行，返回false
        if (node == null) {
            return false;
        }
        //如果值不同，必定不是啊
        if (head.val != node.val) {
            return false;
        }
        //如果值相同，继续看，左边和右边有一个满足即可
        return isSub(head.next, node.left) || isSub(head.next, node.right);
    }
    //endregion

    //region    20230420    1376. 通知所有员工所需的时间

    /**
     * https://leetcode.cn/problems/time-needed-to-inform-all-employees/
     *
     * @param n          有 n 名员工
     * @param headID     总负责人通过 headID 进行标识
     * @param manager    manager 数组
     * @param informTime 第 i 名员工需要 informTime[i]
     * @return 通知所有员工这一紧急消息所需要的 分钟数
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int res = 0;
        for (int i = 0; i < manager.length; i++) {
            if (informTime[i] == 0) {
                int temp = 0;
                int index = i;
                while (index != -1) {
                    temp += informTime[index];
                    index = manager[index];
                }
                res = Math.max(res, temp);
            }
        }
        return res;
    }
    //endregion

    //region    20230417    1630. 等差子数组

    /**
     * https://leetcode.cn/problems/arithmetic-subarrays
     *
     * @param nums 由 n 个整数组成的数组 nums
     * @param l    由 m 个整数组成的数组 l
     * @param r    由 m 个整数组成的数组 r
     * @return 返回 boolean 元素构成的答案列表 answer
     */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length;
        List<Boolean> ans = new ArrayList<Boolean>();
        for (int i = 0; i < n; ++i) {
            int left = l[i], right = r[i];
            int minv = nums[left], maxv = nums[left];
            for (int j = left + 1; j <= right; ++j) {
                minv = Math.min(minv, nums[j]);
                maxv = Math.max(maxv, nums[j]);
            }

            if (minv == maxv) {
                ans.add(true);
                continue;
            }
            if ((maxv - minv) % (right - left) != 0) {
                ans.add(false);
                continue;
            }

            int d = (maxv - minv) / (right - left);
            boolean flag = true;
            boolean[] seen = new boolean[right - left + 1];
            for (int j = left; j <= right; ++j) {
                if ((nums[j] - minv) % d != 0) {
                    flag = false;
                    break;
                }
                int t = (nums[j] - minv) / d;
                if (seen[t]) {
                    flag = false;
                    break;
                }
                seen[t] = true;
            }
            ans.add(flag);
        }
        return ans;
    }
    //endregion

    //region    20230420    1886. 判断矩阵经轮转后是否一致

    /**
     * https://leetcode.cn/problems/determine-whether-matrix-can-be-obtained-by-rotation/
     *
     * @param mat    大小为 n x n 的二进制矩阵 mat
     * @param target 大小为 n x n 的二进制矩阵 target
     * @return 现 以 90 度顺时针轮转 矩阵 mat 中的元素 若干次 ，如果能够使 mat 与 target 一致，返回 true ；否则，返回 false
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        for (int count = 0; count < 4; ++count) {
            for (int i = 0; i < n / 2; ++i) {
                for (int j = 0; j < (n + 1) / 2; ++j) {
                    int temp = mat[i][j];
                    mat[i][j] = mat[n - 1 - j][i];
                    mat[n - 1 - j][i] = mat[n - 1 - i][n - 1 - j];
                    mat[n - 1 - i][n - 1 - j] = mat[j][n - 1 - i];
                    mat[j][n - 1 - i] = temp;
                }
            }
            if (isequals(mat, target)) {
                return true;
            }
        }
        return false;
    }

    public boolean isequals(int[][] mat, int[][] target) {
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[0].length; ++j) {
                if (mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    //endregion

    public static void main(String[] args) {
        new TargetProgrammingSkillsBasic().addToArrayForm(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, 1);
    }
}
