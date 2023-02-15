import java.util.HashMap;

/**
 * @author yyb
 * leetcode_tag_dynamic_programming
 * leetcode 标签 动态规划
 */
public class TargetDynamicProgramming {

    //region 62. 不同路径   2019/10/7  动态规划+数组

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。
     * 机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 问总共有多少条不同的路径？
     * <p>
     * 说明：m 和 n 的值均不超过 100。
     * 示例 1:
     * 输入: m = 3, n = 2
     * 输出: 3
     * 解释:
     * <p>
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     * 示例 2:
     * 输入: m = 7, n = 3
     * 输出: 28
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
    //endregion

    //region 63. 不同路径 II  2019/10/7 动态规划+数组

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * 说明：m 和 n 的值均不超过 100。
     * 示例 1:
     * 输入:
     * [
     * [0,0,0],
     * [0,1,0],
     * [0,0,0]
     * ]
     * 输出: 2
     * 解释:
     * 3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length < 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        int rowLen = obstacleGrid.length, colLen = obstacleGrid[0].length;
        int[][] sum = new int[rowLen][colLen]; // 存储坐标路径数
        // 初始化第一行，第一列路径数
        sum[0][0] = 1;
        for (int row = 1; row < sum[0].length; row++) {
            sum[0][row] = obstacleGrid[0][row] == 1 ? 0 : sum[0][row - 1];
        }
        for (int col = 1; col < sum.length; col++) {
            sum[col][0] = obstacleGrid[col][0] == 1 ? 0 : sum[col - 1][0];
        }
        // 推导每个坐标的路径数
        for (int row = 1; row < sum.length; row++) {
            for (int col = 1; col < sum[row].length; col++) {
                sum[row][col] = obstacleGrid[row][col] == 1 ? 0 :
                        sum[row - 1][col] + sum[row][col - 1];
            }
        }
        return sum[rowLen - 1][colLen - 1];
    }
    //endregion

    //region 70. 爬楼梯 20230215
    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 示例 1：
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     * 示例 2：
     * 输入：n = 3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶 + 1 阶
     * 2. 1 阶 + 2 阶
     * 3. 2 阶 + 1 阶
     * <p>
     * 提示：
     * 1 <= n <= 45
     *
     * @param n
     * @return
     */
    HashMap<Integer, Integer> hashMapcli = new HashMap<>();

    public int climbStairs(int n) {
        if (hashMapcli.containsKey(n)) {
            return hashMapcli.get(n);
        }
        if (n <= 2) {
            return n;
        } else {
            hashMapcli.put(n, climbStairs(n - 1) + climbStairs(n - 2));
            return hashMapcli.get(n);
        }
    }

    public int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        int curr = 1;
        int next = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = curr + next;
            curr = next;
            next = temp;
        }
        return next;
    }
    //endregion

    //region 256. 粉刷房子

    /**
     * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，
     * 你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
     * 当然，因为市场上不同颜色油漆的价格不同，
     * 所以房子粉刷成不同颜色的花费成本也是不同的。
     * 每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。
     * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；
     * costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
     * 请你计算出粉刷完所有房子最少的花费成本。
     * <p>
     * 注意：
     * 所有花费均为正整数。
     * <p>
     * 示例：
     * 输入: [[17,2,17],[16,16,5],[14,3,19]]
     * 输出: 10
     * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
     * 最少花费: 2 + 5 + 3 = 10。
     *
     * @param costs
     * @return
     */
    private int minCost(int[][] costs) {
        int N = costs.length;
        if (N == 0) return 0;
        int dp[][] = new int[N][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        return Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2]));
    }
    //endregion

    //region 303. 区域和检索 - 数组不可变

    /**
     * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
     * <p>
     * 示例：
     * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
     * sumRange(0, 2) -> 1
     * sumRange(2, 5) -> -1
     * sumRange(0, 5) -> -3
     * <p>
     * 说明:
     * 你可以假设数组不可变。
     * 会多次调用 sumRange 方法。
     */
    private class NumArray {
        private int[] sum;

        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }
    //endregion

    //region 392. 判断子序列   2019/10/22

    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * <p>
     * 你可以认为 s 和 t 中仅包含英文小写字母。
     * 字符串 t 可能会很长（长度 ~= 500,000），
     * 而 s 是个短字符串（长度 <=100）。
     * <p>
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）
     * 字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * <p>
     * 示例 1:
     * s = "abc", t = "ahbgdc"
     * 返回 true.
     * 示例 2:
     * s = "axc", t = "ahbgdc"
     * 返回 false.
     * 后续挑战 :
     * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
     * 在这种情况下，你会怎样改变代码？
     *
     * @param s
     * @param t
     * @return
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

    //region 403 青蛙过河

    /**
     * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
     * 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
     * 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
     * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
     * <p>
     * 示例 1：
     * 输入：stones = [0,1,3,5,6,8,12,17]
     * 输出：true
     * 解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
     * 示例 2：
     * 输入：stones = [0,1,2,3,4,8,9,11]
     * 输出：false
     * 解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
     * <p>
     * 提示：
     * 2 <= stones.length <= 2000
     * 0 <= stones[i] <= 231 - 1
     * stones[0] == 0
     */
    public boolean canCross(int[] stones) {
        int[] JumpLength = new int[stones.length];
        return false;
    }
    //endregion

    //region 509. 斐波拉契数列  20230215

    /**
     * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给定 n ，请计算 F(n) 。
     * <p>
     * 示例 1：
     * 输入：n = 2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     * 示例 2：
     * 输入：n = 3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
     * 示例 3：
     * 输入：n = 4
     * 输出：3
     * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
     * <p>
     * 提示：
     * 0 <= n <= 30
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    HashMap<Integer, Integer> hashMapfib = new HashMap<Integer, Integer>();

    public int fib1(int n) {
        if (hashMapfib.containsKey(n)) {
            return hashMapfib.get(n);
        }
        if (n == 0 || n == 1) {
            return n;
        } else {
            hashMapfib.put(n, fib1(n - 1) + fib1(n - 2));
            return fib1(n - 1) + fib1(n - 2);
        }
    }
    //endregion

    // region 746. 使用最小花费爬楼梯 20230215

    /**
     * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     * 请你计算并返回达到楼梯顶部的最低花费。
     * <p>
     * 示例 1：
     * 输入：cost = [10,15,20]
     * 输出：15
     * 解释：你将从下标为 1 的台阶开始。
     * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
     * 总花费为 15 。
     * 示例 2：
     * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
     * 输出：6
     * 解释：你将从下标为 0 的台阶开始。
     * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
     * 总花费为 6 。
     * <p>
     * 提示：
     * 2 <= cost.length <= 1000
     * 0 <= cost[i] <= 999
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        int[] minCost = new int[length];
        minCost[0] = 0;
        minCost[1] = Math.min(cost[0], cost[1]);
        for (int i = 0; i < length; i++) {
            minCost[i] = Math.min(minCost[i - 1] + cost[i], minCost[i - 2] + cost[i - 1]);
        }
        return minCost[length - 1];
    }
    // endregion

    //region 877. 石子游戏   2019/10/22  动态规划

    /**
     * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
     * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
     * 亚历克斯和李轮流进行，亚历克斯先开始。
     * 每回合，玩家从行的开始或结束处取走整堆石头。
     * 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
     * <p>
     * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false。
     * <p>
     * 示例：
     * 输入：[5,3,4,5]
     * 输出：true
     * 解释：
     * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
     * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
     * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
     * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
     * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
     * <p>
     * 提示：
     * 2 <= piles.length <= 500
     * piles.length 是偶数。
     * 1 <= piles[i] <= 500
     * sum(piles) 是奇数。
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int N = piles.length;
        int[][] dp = new int[N + 2][N + 2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1) {
                    dp[i + 1][j + 1] = Math.max(piles[i] + dp[i + 2][j + 1], piles[j] + dp[i + 1][j]);
                } else {
                    dp[i + 1][j + 1] = Math.min(-piles[i] + dp[i + 2][j + 1], -piles[j] + dp[i + 1][j]);
                }
            }

        return dp[1][N] > 0;
    }
    //endregion

    //region 1137. 第 N 个泰波那契数 20230215

    /**
     * 泰波那契序列 Tn 定义如下：
     * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
     * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
     * <p>
     * 示例 1：
     * 输入：n = 4
     * 输出：4
     * 解释：
     * T_3 = 0 + 1 + 1 = 2
     * T_4 = 1 + 1 + 2 = 4
     * 示例 2：
     * 输入：n = 25
     * 输出：1389537
     * <p>
     * 提示：
     * 0 <= n <= 37
     * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
     *
     * @param n
     * @return
     */
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int p = 0, q = 0, r = 1, s = 1;
        for (int i = 3; i <= n; i++) {
            p = q;
            q = r;
            r = s;
            s = p + q + r;
        }
        return s;
    }
    //endregion

    // region 1269. 停在原地的方案数 2021/05/13

    /**
     * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
     * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
     * 给你两个整数 steps 和arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
     * 由于答案可能会很大，请返回方案数 模10^9 + 7 后的结果。
     * <p>
     * 示例 1：
     * 输入：steps = 3, arrLen = 2
     * 输出：4
     * 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
     * 向右，向左，不动
     * 不动，向右，向左
     * 向右，不动，向左
     * 不动，不动，不动
     * 示例 2：
     * 输入：steps = 2, arrLen = 4
     * 输出：2
     * 解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
     * 向右，向左
     * 不动，不动
     * 示例 3：
     * <p>
     * 输入：steps = 4, arrLen = 2
     * 输出：8
     * <p>
     * 提示：
     * 1 <= steps <= 500
     * 1 <= arrLen <= 10^6
     *
     * @param steps
     * @param arrLen
     * @return
     */
    public int numWays(int steps, int arrLen) {
        final int MODULO = 1000000007;
        int maxColums = Math.min(steps, arrLen - 1);
        int[][] numWays = new int[steps + 1][maxColums + 1];
        numWays[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxColums; j++) {
                numWays[i][j] = numWays[i - 1][j];
                if (j - 1 >= 0) {
                    numWays[i][j] = (numWays[i][j] + numWays[i - 1][j - 1]) % MODULO;
                }
                if (j + 1 <= maxColums) {
                    numWays[i][j] = (numWays[i][j] + numWays[i - 1][j + 1]) % MODULO;
                }
            }
        }
        return numWays[steps][0];
    }
    // endregion

    public static void main(String[] args) {
//        int[][] arrays=new int[1][2];
//        arrays[0][0]=1;
//        arrays[0][1]=0;
//        int num=(new TargetDynamicProgramming()).uniquePathsWithObstacles(arrays);
//        int num=(new TargetDynamicProgramming()).numWays(4,3);
    }
}
