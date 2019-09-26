/**
 * @author yyb
 * leetcode_tag_dynamic_programming
 * leetcode 标签 动态规划
 */
public class TargetDynamicProgramming {


    //region 256. 粉刷房子
    /**
     * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，
     * 你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
     * 当然，因为市场上不同颜色油漆的价格不同，
     * 所以房子粉刷成不同颜色的花费成本也是不同的。
     * 每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。
     * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；
     * costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
     * 请你计算出粉刷完所有房子最少的花费成本。
     *
     * 注意：
     * 所有花费均为正整数。
     *
     * 示例：
     * 输入: [[17,2,17],[16,16,5],[14,3,19]]
     * 输出: 10
     * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
     *       最少花费: 2 + 5 + 3 = 10。
     * @param costs
     * @return
     */
    private int minCost(int[][] costs) {
        int N = costs.length;
        if(N == 0) return 0;
        int dp[][] = new int[N][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for(int i=1; i<N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i][2];
        }
        return Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));
    }
    //endregion


    //region 303. 区域和检索 - 数组不可变
    /**
     * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
     *
     * 示例：
     * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
     * sumRange(0, 2) -> 1
     * sumRange(2, 5) -> -1
     * sumRange(0, 5) -> -3
     *
     * 说明:
     * 你可以假设数组不可变。
     * 会多次调用 sumRange 方法。
     *
     */
    private class NumArray {
        private int[] sum;
        public NumArray(int[] nums) {
            sum=new int[nums.length+1];
            for (int i = 0; i < nums.length; i++) {
               sum[i+1]=sum[i]+nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j+1]-sum[i];
        }
    }
    //endregion



    public static void main(String[] args) {

    }
}
