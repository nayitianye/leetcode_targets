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

    //region    20230418    5. 最长回文子串

    /**
     * https://leetcode.cn/problems/longest-palindromic-substring
     *
     * @param s 一个字符串 s
     * @return 找到 s 中最长的回文子串
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        int maxLength = 1;
        int begin = 0;
        //d[i][j]表示s[i..j]是否是回文串
        boolean[][] dp = new boolean[length][length];
        //初始化：所有长度为1的字串都是回文串
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        //先枚举字串的长度
        for (int L = 2; L <= length; L++) {
            //枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < length; i++) {
                //由i和j可以确定右边界，即j-i+1=L得
                int j = L + i - 1;
                //如果右边界越界，就可以退出当前循环
                if (j >= length) {
                    break;
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                //只要dp[i][L]==true成立，就表示字串s[i..L]是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLength);
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

    //region    20230418    45. 跳跃游戏 II

    /**
     * https://leetcode.cn/problems/jump-game-ii/
     *
     * @param nums 长度为 n 的 0 索引整数数组 nums
     * @return 返回到达 nums[n - 1] 的最小跳跃次数
     */
    public int jump(int[] nums) {
        //下一次跳的位置
        int end = 0;
        //最大的跳跃距离
        int maxPosition = 0;
        //跳跃次数
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
    //endregion

    //region    20230418    47. 全排列 II

    /**
     * https://leetcode.cn/problems/permutations-ii/
     * @param nums  可包含重复数字的序列 nums
     * @return  按任意顺序 返回所有不重复的全排列
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> perm = new ArrayList<>();
        visPermuteUnique = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, res, 0, perm);
        return res;
    }

    public void backtrack(int[] nums, List<List<Integer>> res, int index, List<Integer> perm) {
        if (index == nums.length) {
            res.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visPermuteUnique[i] || (i > 0 && nums[i] == nums[i - 1] && !visPermuteUnique[i-1])) {
                continue;
            }
            perm.add(nums[i]);
            visPermuteUnique[i] = true;
            backtrack(nums, res, index + 1, perm);
            visPermuteUnique[i] = false;
            perm.remove(index);
        }
    }


    public boolean[] visPermuteUnique;
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

    //region    20230418    55. 跳跃游戏

    /**
     * https://leetcode.cn/problems/jump-game/
     *
     * @param nums 非负整数数组 nums
     * @return 判断你是否能够到达最后一个下标
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        //前n-1个元素能够跳到的最远的距离
        int mostLength = 0;
        for (int i = 0; i <= mostLength; i++) {
            //第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            //更新最远距离
            mostLength = Math.max(mostLength, temp);
            //如果最远距离已经大于或等于最后一个元素的下标，则说明能跳过去，退出，减少循环
            if (mostLength >= nums.length - 1) {
                return true;
            }
        }
        //最远距离k不再改变，且没有到末尾元素
        return false;
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

    //region    20230418    62. 不同路径

    /**
     * https://leetcode.cn/problems/unique-paths
     *
     * @param m m 行
     * @param n n 列
     * @return 从左上到右下的步骤数
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

    //region    20230418    78. 子集

    /**
     * https://leetcode.cn/problems/subsets/
     *
     * @param nums 一个整数数组 nums ，数组中的元素互不相同
     * @return 返回该数组所有可能的子集（幂集）
     */
    public List<List<Integer>> subsets(int[] nums) {
        dfsSubSets(0, nums);
        return resSubSets;
    }

    public void dfsSubSets(int cur, int[] nums) {
        if (cur == nums.length) {
            resSubSets.add(new ArrayList<>(tempList));
            return;
        }
        tempList.add(nums[cur]);
        dfsSubSets(cur + 1, nums);
        tempList.remove(tempList.size() - 1);
        dfsSubSets(cur + 1, nums);
    }

    List<List<Integer>> resSubSets = new ArrayList<>();
    List<Integer> tempList = new ArrayList<>();
    //endregion

    //region    20230418    90. 子集 II

    /**
     * https://leetcode.cn/problems/subsets-ii
     *
     * @param nums 一个整数数组 nums ，其中可能包含重复元素
     * @return 返回该数组所有可能的子集（幂集）
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfsSubsetsWithDup(false, 0, nums);
        return resSubsetWidthDup;
    }

    public void dfsSubsetsWithDup(boolean choosePre, int cur, int[] nums) {
        if (cur == nums.length) {
            resSubsetWidthDup.add(new ArrayList<>(tempSubsetsWithDup));
            return;
        }
        dfsSubsetsWithDup(false, cur + 1, nums);
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        tempSubsetsWithDup.add(nums[cur]);
        dfsSubsetsWithDup(true, cur + 1, nums);
        tempSubsetsWithDup.remove(tempSubsetsWithDup.size() - 1);
    }

    List<Integer> tempSubsetsWithDup = new ArrayList<>();
    List<List<Integer>> resSubsetWidthDup = new ArrayList<>();
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

    //region    20230418    213. 打家劫舍 II

    /**
     * https://leetcode.cn/problems/house-robber-ii/
     *
     * @param nums 给定一个代表每个房屋存放金额的非负整数数组 nums
     * @return 计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额
     */
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(maxRob(0, nums.length - 1, nums), maxRob(1, nums.length, nums));
    }

    public int maxRob(int start, int end, int[] nums) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
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

    //region    20230418    413. 等差数列划分

    /**
     * https://leetcode.cn/problems/arithmetic-slices/
     *
     * @param nums 一个整数数组 nums
     * @return 返回数组 nums 中所有为等差数组的 子数组 个数
     */
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }
        int res = 0;
        //存储增量
        // 1, 2, 3, 6, 8, 10 原数组
        // 0  0  1  0  0  1 增量
        int[] dp = new int[nums.length];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;//确定增量
                res += dp[i];//添加增量
            }
        }
        return res;
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

    public static void main(String[] args) {
        new TargetProgrammingSkillsBasic().addToArrayForm(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, 1);
    }
}
