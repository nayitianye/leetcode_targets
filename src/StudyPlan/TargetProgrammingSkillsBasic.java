package StudyPlan;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
     * @param num1  非负整数 num1
     * @param num2  非负整数 num2
     * @return  返回 num1 和 num2 的乘积
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

    //region    20230410    58. 最后一个单词的长度

    /**
     * https://leetcode.cn/problems/length-of-last-word
     * @param s  字符串 s
     * @return  返回字符串中最后一个单词的长度
     */
    public int lengthOfLastWord(String s) {
        String[] strings = s.trim().split(" ");
        return strings[strings.length - 1].length();
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

    public static void main(String[] args) {
        new TargetProgrammingSkillsBasic().addToArrayForm(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, 1);
    }
}
