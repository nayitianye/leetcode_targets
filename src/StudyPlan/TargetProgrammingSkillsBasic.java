package StudyPlan;

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

    //region    20230406    459. 重复的子字符串

    /**
     * https://leetcode.cn/problems/repeated-substring-pattern/
     * @param s 非空的字符串 s
     * @return  检查是否可以通过由它的一个子串重复多次构成
     */
    public boolean repeatedSubstringPattern(String s) {
        String str=s+s;
        return str.substring(1,str.length()-1).contains(s);
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
}
