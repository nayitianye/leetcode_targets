package TargetCompany;

import java.util.List;

/**
 * @author yyb
 * leetcode_tag_Netease
 * leetcode 标签 网易
 */
public class TargetNetease {

    //region    20230507    38. 外观数列
    public String countAndSay(int n) {
        String str = "1";
        for (int i = 1; i < n; i++) {
            str = getCountAndSay(str);
        }
        return str;
    }

    public String getCountAndSay(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        char ch = '1';
        for (int i = 0; i < str.length(); i++) {
            if (count == 0) {
                ch = str.charAt(i);
                count++;
            } else {
                if (ch == str.charAt(i)) {
                    count++;
                } else {
                    stringBuilder.append(count);
                    stringBuilder.append(ch);
                    count = 0;
                    i--;
                }
            }
        }
        if(count!=0){
            stringBuilder.append(count);
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
    //endregion

    //region    20230507    1262. 可被三整除的最大和

    /**
     * https://leetcode.cn/problems/greatest-sum-divisible-by-three
     *
     * @param nums 一个整数数组 nums
     * @return 找出并返回能被三整除的元素最大和
     */
    public int maxSumDivThree(int[] nums) {
        int[][] dp = new int[nums.length + 1][3];
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] % 3 == 0) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][2] + nums[i - 1]);
            }
            if (nums[i - 1] % 3 == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + nums[i - 1]);
            }
            if (nums[i - 1] % 3 == 2) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + nums[i - 1]);
            }
        }
        return dp[nums.length][0];
    }
    //endregion
}
