package TargetInterview;

import java.util.Arrays;

/**
 * @author yyb
 * leetcode_interview
 * leetcode 标签 面试题
 */
public class TargetInterview {
    //region    20230311    面试题 17.05. 字母与数字

    /**
     * https://leetcode.cn/problems/find-longest-subarray-lcci/
     * @param array 放有字母和数字的数组
     * @return  放有字母和数字的数组
     */
    public String[] findLongestSubarray(String[] array) {
        int n = array.length, begin = 0, end = 0, s = n;
        int[] first = new int[n * 2 + 1];
        Arrays.fill(first, -1); // 注：去掉可以再快 1ms（需要 s 下标改从 1 开始）
        first[s] = 0; // s[0] = 0
        for (int i = 1; i <= n; ++i) {
            s += (array[i - 1].charAt(0) >> 6 & 1) * 2 - 1;
            int j = first[s];
            if (j < 0)
                first[s] = i;
            else if (i - j > end - begin) {
                begin = j;
                end = i;
            }
        }
        String[] sub = new String[end - begin];
        System.arraycopy(array, begin, sub, 0, sub.length);
        return sub;
    }
    //endregion
}
