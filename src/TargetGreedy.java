import java.util.Arrays;

/**
 * @author yyb
 * leetcode_tag_greedy
 * leetcode 标签 贪心算法
 */
public class TargetGreedy {

    //region    20191022    392. 判断子序列

    /**
     * https://leetcode.cn/problems/is-subsequence/
     *
     * @param s  字符串 s
     * @param t  字符串 t
     * @return  判断 s 是否为 t 的子序列
     */
    public boolean isSubsequence(String s, String t) {
        int index = 0, i = 0;
        while (index < s.length() && t.indexOf(s.charAt(index), i) >= i) {
            i = t.indexOf(s.charAt(index), i) + 1;
            index++;
        }
        return index == s.length();
    }
    //endregion

    //region    20191017    621. 任务调度器

    /**
     * https://leetcode.cn/problems/task-scheduler/
     *
     * @param tasks  字符数组 tasks 表示的 CPU 需要执行的任务列表
     * @param n  两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间
     * @return  计算完成所有任务所需要的 最短时间
     */
    public int leastInterval(char[] tasks, int n) {
        /**
         * 解题思路
         *  1、将任务按类型分组，正好A-Z用一个int[26]保存任务类型个数
         *  2、对数组进行排序，优先排列个数（count）最大的任务，
         *        如题得到的时间至少为 retCount =（count-1）* (n+1) + 1 ==> A->X->X->A->X->X->A(X为其他任务或者待命)
         *  3、再排序下一个任务，如果下一个任务B个数和最大任务数一致，
         *        则retCount++ ==> A->B->X->A->B->X->A->B
         *  4、如果空位都插满之后还有任务，那就随便在这些间隔里面插入就可以，因为间隔长度肯定会大于n，在这种情况下就是任务的总数是最小所需时间
         */
        if (tasks.length <= 1 || n < 1) return tasks.length;
        //步骤1
        int[] counts = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            counts[tasks[i] - 'A']++;
        }
        //步骤2
        Arrays.sort(counts);
        int maxCount = counts[25];
        int retCount = (maxCount - 1) * (n + 1) + 1;
        int i = 24;
        //步骤3
        while (i >= 0 && counts[i] == maxCount) {
            retCount++;
            i--;
        }
        //步骤4
        return Math.max(retCount, tasks.length);
    }
    //endregion

    //region    20230412    1147. 段式回文

    /**
     * https://leetcode.cn/problems/longest-chunked-palindrome-decomposition/
     *
     * @param text 一个字符串 text
     * @return 返回k可能最大值
     */
    public int longestDecomposition(String text) {
        if (text.isEmpty()) {
            return 0;
        }
        for (int i = 1, n =text.length(); i <= n / 2; i++) {
            if (text.substring(0, i).equals(text.substring(n - i))) {
                return 2 + longestDecomposition(text.substring(i, n - i));
            }
        }
        return 1;
    }
    //endregion
}
