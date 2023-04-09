package StudyPlan;

import java.util.HashMap;

/**
 * @author yyb
 * leetcode_tag_dynamic_programmingBasic
 * leetcode 标签 动态规划_基础
 */
public class TargetDynamicProgrammingBasic {

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
