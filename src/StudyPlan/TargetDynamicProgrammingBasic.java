package StudyPlan;

import java.util.HashMap;

/**
 * @author yyb
 * leetcode_tag_dynamic_programmingBasic
 * leetcode 标签 动态规划_基础
 */
public class TargetDynamicProgrammingBasic {

    //region    20230411    53. 最大子数组和

    /**
     * https://leetcode.cn/problems/maximum-subarray/
     * @param nums  整数数组 nums
     * @return  找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
     */
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        //dp 数组表示以nums[i]结尾的最大数组
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        int res=nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(dp[i-1]>0){
                dp[i]=dp[i-1]+nums[i];
            }else{
                dp[i]=nums[i];
            }
            res=Math.max(res,dp[i]);
        }
        return res;
    }
    //endregion

    //region    20230409    70. 爬楼梯

    /**
     * https://leetcode.cn/problems/climbing-stairs/
     * @param n  需要 n 阶你才能到达楼顶
     * @return  有多少种不同的方法可以爬到楼顶
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            if (hashMapClimbStairs.containsKey(n)) {
                return hashMapClimbStairs.get(n);
            } else {
                hashMapClimbStairs.put(n, climbStairs(n - 1) + climbStairs(n - 2));
                return hashMapClimbStairs.get(n);
            }
        }
    }
    HashMap<Integer, Integer> hashMapClimbStairs = new HashMap<>();
    //endregion

    //region    20230411    198. 打家劫舍

    /**
     * https://leetcode.cn/problems/house-robber/
     * @param nums  一个代表每个房屋存放金额的非负整数数组 nums
     * @return  计算你不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
    //endregion

    //region    20230411    213. 打家劫舍 II

    /**
     * https://leetcode.cn/problems/house-robber-ii/
     * @param nums  给定一个代表每个房屋存放金额的非负整数数组 nums
     * @return  计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额
     */
    public int rob1(int[] nums) {
        if(nums==null ||nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        if(nums.length==2){
            return Math.max(nums[0],nums[1]);
        }
        return Math.max(maxRob(0,nums.length-1,nums),maxRob(1,nums.length,nums));
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

    //region    20230409    509. 斐波那契数

    /**
     * https://leetcode.cn/problems/fibonacci-number/
     * @param n  给定 n
     * @return  计算 F(n)
     */
    public int fib(int n) {
        if(hashMapfib.containsKey(n)){
            return hashMapfib.get(n);
        }
        if (n == 0 || n == 1) {
            return n;
        }
        else {
            hashMapfib.put(n,fib(n - 1) + fib(n - 2));
            return fib(n - 1) + fib(n - 2);
        }
    }
    HashMap<Integer,Integer> hashMapfib=new HashMap<Integer,Integer>();
    //endregion

    //region    20230409    746. 使用最小花费爬楼梯

    /**
     * https://leetcode.cn/problems/min-cost-climbing-stairs
     * @param cost  一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用
     * @return  计算并返回达到楼梯顶部的最低花费
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = 0;
        dp[1] = Math.min(cost[0], cost[1]);
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i], dp[i - 2] + cost[i - 1]);
        }
        return dp[cost.length - 1];
    }
    //endregion
}
