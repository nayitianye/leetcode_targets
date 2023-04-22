package LeetBook;

import java.util.Map;

/**
 * @author yyb
 * leetcode_leetbook_preSum
 * leetbook 前缀和
 */
public class TargetBookDynamicProgrammingPlusOne {

    //region    20230422    300. 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        // 数组的长度小于2，则最长上升子序列为数组的长度
        if (nums.length < 2) {
            return nums.length;
        }
        // dp[i]表示：以 num[i] 结尾的上升子序列的长度。
        // 注意：这个定义中 nums[i] 必须被选取，且必须是这个子序列的最后一个元素
        int[] dp = new int[nums.length];
        // 1个字符的长度为1的上升子序列
        dp[0] = 1;
        // 用于记录最长的上升子序列的长度
        int maxLength = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //与每一个小于当前的值的序列+1
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //交换取最长的上升子序列的长度
            maxLength=Math.max(dp[i],maxLength);
        }
        return maxLength;
    }
    //endregion
}
