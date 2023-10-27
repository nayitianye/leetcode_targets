package LeetBook;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yyb
 * leetcode_leetbook_preSum
 * leetbook 前缀和
 */
public class TargetBookPreSum {

    //region    20230331    303. 区域和检索 - 数组不可变
    static class NumArray {

        int[] preSum;

        public NumArray(int[] nums) {
            preSum = new int[nums.length + 1];
            for (int i = 1; i <= nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }
    //endregion

    //region    20230401    1177. 构建回文串检测
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int length = s.length();
        int[] preSum = new int[length + 1];
        for (int i = 0; i < length; i++) {
            preSum[i + 1] = preSum[i] ^ (1 << (s.charAt(i) - 'a'));
        }
        List<Boolean> list = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            int v = preSum[query[1] + 1] ^ preSum[query[0]];
            int c = 0;
            while (v != 0) {
                c++;
                v = v & (v - 1);
            }
            list.add(c <= (query[2] << 1) + 1);
        }
        return list;
    }
    //endregion

    public static void main(String[] args) {
    }
}
