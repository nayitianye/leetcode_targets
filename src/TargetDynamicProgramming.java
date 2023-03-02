import java.util.*;

/**
 * @author yyb
 * leetcode_tag_dynamic_programming
 * leetcode 标签 动态规划
 */
public class TargetDynamicProgramming {

    //region 相关的网址
    //代码随想录
    //https://www.bilibili.com/video/BV1pd4y147Rh/
    //endregion

    //region  5. 最长回文子串  20230228

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
     * <p>
     * 示例 1：
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出："bb"
     * <p>
     * 提示：
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母组成
     *
     * @param s 字符串 s
     * @return s 中最长的回文子串
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        int maxLength = 1;
        int begin = 0;
        //d[i][j]表示s[i..j]是否是回文串
        boolean[][] dp = new boolean[length][length];
        //初始化：所有长度为1的字串都是回文串
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        //先枚举字串的长度
        for (int L = 2; L <= length; L++) {
            //枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < length; i++) {
                //由i和j可以确定右边界，即j-i+1=L得
                int j = L + i - 1;
                //如果右边界越界，就可以退出当前循环
                if (j >= length) {
                    break;
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                //只要dp[i][L]==true成立，就表示字串s[i..L]是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLength);
    }
    //endregion

    //region 45. 跳跃游戏 II  20230217

    /**
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     * 0 <= j <= nums[i]
     * i + j < n
     * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
     * <p>
     * 示例 1:
     * 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步 到达数组的最后一个位置。
     * 示例 2:
     * 输入: nums = [2,3,0,1,4]
     * 输出: 2
     * <p>
     * 提示:
     * 1 <= nums.length <= 10^4
     * 0 <= nums[i] <= 1000
     * 题目保证可以到达 nums[n-1]
     *
     * @param nums 长度为 n 的 0 索引整数数组 nums
     * @return 最小跳跃数
     */
    public int jump(int[] nums) {
        //下一次跳的位置
        int end = 0;
        //最大的跳跃距离
        int maxPosition = 0;
        //跳跃次数
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
    //endregion

    //region 53. 最大子数组和  20230217

    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     * <p>
     * 示例 1：
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * 示例 2：
     * 输入：nums = [1]
     * 输出：1
     * 示例 3：
     * 输入：nums = [5,4,-1,7,8]
     * 输出：23
     * <p>
     * 提示：
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     *
     * @param nums 整数数组 nums
     * @return 具有最大和的连续子数组的最大和
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int maxNum = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxNum = Math.max(maxNum + nums[i], nums[i]);
            if (maxNum > res) {
                res = maxNum;
            }
            if (maxNum < 0) {
                maxNum = 0;
            }
        }
        return res;
    }
    //endregion

    //region 55. 跳跃游戏   20230217

    /**
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标。
     * <p>
     * 示例 1：
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步 到达最后一个下标。
     * 示例 2：
     * 输入：nums = [3,2,1,0,4]
     * 输出：false
     * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
     * <p>
     * 提示：
     * 1 <= nums.length <= 3 * 104
     * 0 <= nums[i] <= 105
     *
     * @param nums 非负整数数组 nums
     * @return 是否可达到最后一个下标
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }
        //数组表示第i个元素能够到的最远的距离
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + i);
            if (dp[i] == i) {
                return false;
            }
        }
        return true;
    }

    public boolean canJump1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        //前n-1个元素能够跳到的最远的距离
        int mostLength = 0;
        for (int i = 0; i <= mostLength; i++) {
            //第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            //更新最远距离
            mostLength = Math.max(mostLength, temp);
            //如果最远距离已经大于或等于最后一个元素的下标，则说明能跳过去，退出，减少循环
            if (mostLength >= nums.length - 1) {
                return true;
            }
        }
        //最远距离k不再改变，且没有到末尾元素
        return false;
    }
    //endregion

    //region 62. 不同路径   20191007  动态规划+数组

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
     * @param m 网格位置 m
     * @param n 网格位置 n
     * @return 到达路径数
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

    //region 63. 不同路径 II  20191007 动态规划+数组

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
     * @param obstacleGrid 一个 m x n 网格
     * @return 左上角到右下角将会有多少条不同的路径
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

    //region 64. 最小路径和  20230227

    /**
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * <p>
     * 示例 1：
     * <p>
     * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     * 输出：7
     * 解释：因为路径 1→3→1→1→1 的总和最小。
     * 示例 2：
     * 输入：grid = [[1,2,3],[4,5,6]]
     * 输出：12
     * <p>
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 200
     * 0 <= grid[i][j] <= 100
     *
     * @param grid 包含非负整数的 m x n 网格 grid
     * @return 找出一条从左上角到右下角的路径，使得路径上的数字总和为最小
     */
    public int minPathSum(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        if (row == 0 || col == 0) {
            return 0;
        }
        for (int i = 1; i < row; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < col; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[row - 1][col - 1];
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
     * @param n 爬楼梯。需要 n 阶你才能到达楼顶
     * @return 多少种不同的方法可以爬到楼顶
     */
    public int climbStairs(int n) {
        if (hashmap.containsKey(n)) {
            return hashmap.get(n);
        }
        if (n <= 2) {
            return n;
        } else {
            hashmap.put(n, climbStairs(n - 1) + climbStairs(n - 2));
            return hashmap.get(n);
        }
    }

    HashMap<Integer, Integer> hashmap = new HashMap<>();

    public int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        int curr = 1;
        int next = 2;
        int temp;
        for (int i = 3; i <= n; i++) {
            temp = curr + next;
            curr = next;
            next = temp;
        }
        return next;
    }
    //endregion

    //region 91. 解码方法 20230226

    /**
     * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
     * 'A' -> "1"
     * 'B' -> "2"
     * ...
     * 'Z' -> "26"
     * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
     * "AAJF" ，将消息分组为 (1 1 10 6)
     * "KJF" ，将消息分组为 (11 10 6)
     * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
     * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
     * 题目数据保证答案肯定是一个 32 位 的整数。
     * <p>
     * <p>
     * 示例 1：
     * 输入：s = "12"
     * 输出：2
     * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
     * 示例 2：
     * 输入：s = "226"
     * 输出：3
     * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     * 示例 3：
     * 输入：s = "06"
     * 输出：0
     * 解释："06" 无法映射到 "F" ，因为存在前导零（"6" 和 "06" 并不等价）。
     * <p>
     * 提示：
     * 1 <= s.length <= 100
     * s 只包含数字，并且可能包含前导零。
     *
     * @param s 只含数字的非空字符串 s
     * @return 计算并返回解码方法的总数
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0')) <= 26) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }

    //endregion

    //region 96. 不同的二叉搜索树  20230226

    /**
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
     * 返回满足题意的二叉搜索树的种数。
     * <p>
     * 示例 1：
     * 输入：n = 3
     * 输出：5
     * 示例 2：
     * 输入：n = 1
     * 输出：1
     * <p>
     * 提示：
     * 1 <= n <= 19
     *
     * @param n 整数 n
     * @return 二叉搜索树的种数
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
    //endregion

    //region  118. 杨辉三角  20230225

    /**
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     * <p>
     * 示例 1:
     * 输入: numRows = 5
     * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     * 示例 2:
     * 输入: numRows = 1
     * 输出: [[1]]
     * <p>
     * 提示:
     * 1 <= numRows <= 30
     *
     * @param numRows 非负整数 numRows
     * @return 生成「杨辉三角」的前 numRows 行
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(res.get(i - 1).get(j) + res.get(i - 1).get(j - 1));
                }
            }
            res.add(list);
        }
        return res;
    }
    //endregion

    //region 119. 杨辉三角 II  20230225

    /**
     * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     * <p>
     * 示例 1:
     * 输入: rowIndex = 3
     * 输出: [1,3,3,1]
     * 示例 2:
     * 输入: rowIndex = 0
     * 输出: [1]
     * 示例 3:
     * 输入: rowIndex = 1
     * 输出: [1,1]
     * <p>
     * 提示:
     * 0 <= rowIndex <= 33
     *
     * @param rowIndex 非负索引 rowIndex
     * @return 返回「杨辉三角」的第 rowIndex 行
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                cur.set(j, cur.get(j - 1) + cur.get(j));
            }
            cur.add(1);//部上每层的最后一个1
        }
        return cur;
    }
    //endregion

    //region 120. 三角形最小路径和  20230225

    /**
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     * <p>
     * 示例 1：
     * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
     * 输出：11
     * 解释：如下面简图所示：
     * 2
     * 3 4
     * 6 5 7
     * 4 1 8 3
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     * 示例 2：
     * 输入：triangle = [[-10]]
     * 输出：-10
     * <p>
     * 提示：
     * 1 <= triangle.length <= 200
     * triangle[0].length == 1
     * triangle[i].length == triangle[i - 1].length + 1
     * -10^4 <= triangle[i][j] <= 10^4
     *
     * @param triangle 三角形 triangle
     * @return 自顶向下的最小路径和
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            dp[i] = dp[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
            }
            dp[0] += triangle.get(i).get(0);
        }
        int minTotal = dp[0];
        for (int i = 1; i < triangle.size(); i++) {
            minTotal = Math.min(minTotal, dp[i]);
        }
        return minTotal;
    }
    //endregion

    //region 121. 买卖股票的最佳时机  20230219

    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * <p>
     * 示例 1：
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2：
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     * <p>
     * 提示：
     * 1 <= prices.length <= 10^5
     * 0 <= prices[i] <= 10^4
     *
     * @param prices 一个数组 prices
     * @return 这笔交易中获取的最大利润
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int min = prices[0], max = 0;
        for (int price : prices) {
            max = Math.max(max, price - min);
            min = Math.min(min, price);
        }
        return max;
    }
    //endregion

    //region 122. 买卖股票的最佳时机 II 20220220

    /**
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的最大利润 。
     * <p>
     * 示例 1：
     * 输入：prices = [7,1,5,3,6,4]
     * 输出：7
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     * 总利润为 4 + 3 = 7 。
     * 示例 2：
     * 输入：prices = [1,2,3,4,5]
     * 输出：4
     * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     * 总利润为 4 。
     * 示例 3：
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
     * <p>
     * 提示：
     * 1 <= prices.length <= 3 * 10^4
     * 0 <= prices[i] <= 10^4
     *
     * @param prices 整数数组 prices
     * @return 得的最大利润
     */
    public int maxProfit1(int[] prices) {
        //贪心算法
        int resProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                resProfit = resProfit + prices[i] - prices[i - 1];
            }
        }
        return resProfit;
    }

    public int maxProfit2(int[] prices) {
        //动态规划
        int dp0 = 0, dp1 = -prices[0];
        //dp0表示第i天交易完后手里没有股票的最大利润
        //dp1表示第i天交易完后手里持有一只股票的最大利润
        for (int i = 1; i < prices.length; i++) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }
    //endregion

    //region  139. 单词拆分  20230226

    /**
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     * <p>
     * 示例 1：
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
     * 示例 2：
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     * 注意，你可以重复使用字典中的单词。
     * 示例 3：
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     * <p>
     * 提示：
     * 1 <= s.length <= 300
     * 1 <= wordDict.length <= 1000
     * 1 <= wordDict[i].length <= 20
     * s 和 wordDict[i] 仅有小写英文字母组成
     * wordDict 中的所有字符串 互不相同
     *
     * @param s        字符串 s
     * @param wordDict 字符串列表 wordDict
     * @return 判断是否可以利用字典中出现的单词拼接出 s
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
    //endregion

    // region 152. 乘积最大子数组 20230225

    /**
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * 测试用例的答案是一个 32-位 整数。
     * 子数组 是数组的连续子序列。
     * <p>
     * <p>
     * 示例 1:
     * 输入: nums = [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 示例 2:
     * 输入: nums = [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     * <p>
     * 提示:
     * 1 <= nums.length <= 2 * 104
     * -10 <= nums[i] <= 10
     * nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
     *
     * @param nums 整数数组 nums
     * @return 找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积
     */
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int[] maxdp = new int[length];
        int[] mindp = new int[length];
        System.arraycopy(nums, 0, maxdp, 0, length);
        System.arraycopy(nums, 0, mindp, 0, length);
        for (int i = 1; i < length; i++) {
            maxdp[i] = Math.max(maxdp[i - 1] * nums[i], Math.max(nums[i], mindp[i - 1] * nums[i]));
            mindp[i] = Math.min(mindp[i - 1] * nums[i], Math.min(nums[i], maxdp[i - 1] * nums[i]));
        }
        int ans = maxdp[0];
        for (int i = 1; i < length; i++) {
            ans = Math.max(ans, maxdp[i]);
        }
        return ans;
    }

    public int maxProduct1(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }
    // endregion

    // region 198. 打家劫舍 20230216

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     * 示例 1：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2：
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     * <p>
     * 提示：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     *
     * @param nums 一个代表每个房屋存放金额的非负整数数
     * @return 计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额
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
    // endregion

    // region  213. 打家劫舍 II 20230216

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     * <p>
     * 示例 1：
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * 示例 2：
     * 输入：nums = [1,2,3,1]
     * 输出：4
     * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 3：
     * 输入：nums = [1,2,3]
     * 输出：3
     * <p>
     * 提示：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     *
     * @param nums 一个代表每个房屋存放金额的非负整数数组
     * @return 计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额
     */
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(maxRob(0, nums.length - 1, nums), maxRob(1, nums.length, nums));
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

    //region 221. 最大正方形  20230227

    /**
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     * 示例 1：
     * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
     * 输出：4
     * 示例 2：
     * 输入：matrix = [["0","1"],["1","0"]]
     * 输出：1
     * 示例 3：
     * 输入：matrix = [["0"]]
     * 输出：0
     * <p>
     * 提示：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 300
     * matrix[i][j] 为 '0' 或 '1'
     *
     * @param matrix 由 '0' 和 '1' 组成的二维矩阵
     * @return 只包含 '1' 的最大正方形的面积
     */
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
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
     * @param costs 粉刷不同房子不同颜色油漆的成本
     * @return 粉刷完所有房子最少的花费成本
     */
    private int minCost(int[][] costs) {
        int N = costs.length;
        if (N == 0) return 0;
        int[][] dp = new int[N][3];
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

    //region 264. 丑数 II  20230226

    /**
     * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
     * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
     * <p>
     * 示例 1：
     * 输入：n = 10
     * 输出：12
     * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
     * 示例 2：
     * 输入：n = 1
     * 输出：1
     * 解释：1 通常被视为丑数。
     * <p>
     * 提示：
     * 1 <= n <= 1690
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;//当前数和2乘已经放入丑数数组了，下次没意义了，要加一
            }
            if (dp[i] == num3) {
                p3++;//当前数和3乘已经放入丑数数组了，下次没意义了，要加一
            }
            if (dp[i] == num5) {
                p5++;//当前数和3乘已经放入丑数数组了，下次没意义了，要加一
            }
        }
        return dp[n];
    }
    //endregion

    //region  300. 最长递增子序列  20230301

    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <p>
     * 示例 1：
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * 示例 2：
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     * 示例 3：
     * 输入：nums = [7,7,7,7,7,7,7]
     * 输出：1
     * <p>
     * <p>
     * 提示：
     * 1 <= nums.length <= 2500
     * -10^4 <= nums[i] <= 10^4
     * <p>
     * 进阶：
     * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
     *
     * @param nums 整数数组 nums
     * @return 最长严格递增子序列的长度
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
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
    private static class NumArray {
        private final int[] sum;

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

    //region 304. 二维区域和检索 - 矩阵不可变  20230226

    /**
     * 给定一个二维矩阵 matrix，以下类型的多个请求：
     * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
     * 实现 NumMatrix 类：
     * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
     * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
     * 示例 1：
     * <p>
     * 输入:
     * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
     * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
     * 输出:
     * [null, 8, 11, 12]
     * 解释:
     * NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
     * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
     * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
     * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
     */
    private static class NumMatrix {
        int[][] sums;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            if (m > 0) {
                int n = matrix[0].length;
                sums = new int[m][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        sums[i][j + 1] = sums[i][j] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i < row2; i++) {
                sum += sums[i][col2 + 1] - sums[i][col1];
            }
            return sum;
        }
    }
    //endregion

    //region 309. 最佳买卖股票时机含冷冻期 20230220

    /**
     * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1:
     * 输入: prices = [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     * 示例 2:
     * 输入: prices = [1]
     * 输出: 0
     * <p>
     * 提示：
     * 1 <= prices.length <= 5000
     * 0 <= prices[i] <= 1000[
     *
     * @param prices 整数数组prices
     * @return 一个算法计算出最大利润
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int[][] f = new int[prices.length][3];
        //f[i][0]:手上持有股票的最大收益
        //f[i][1]:手上不持有股票，并且处于冷冻期的累计最大收益
        //f[i][2]:手上不持有股票，并且不在冷冻期的累计最大收益
        f[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[prices.length - 1][1], f[prices.length - 1][2]);
    }
    //endregion

    //region 376. 摆动序列  20230301

    /**
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。
     * 第一个差（如果存在的话）可能是正数或负数。
     * 仅有一个元素或者含两个不等元素的序列也视作摆动序列。
     * 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
     * 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
     * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
     * <p>
     * 示例 1：
     * 输入：nums = [1,7,4,9,2,5]
     * 输出：6
     * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
     * 示例 2：
     * 输入：nums = [1,17,5,10,13,15,10,5,16,8]
     * 输出：7
     * 解释：这个序列包含几个长度为 7 摆动序列。
     * 其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
     * 示例 3：
     * 输入：nums = [1,2,3,4,5,6,7,8,9]
     * 输出：2
     * <p>
     * 提示：
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     *
     * @param nums 整数数组 nums
     * @return 返回 nums 中作为 摆动序列 的 最长子序列的长度
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
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
     * @param s 字符串 s
     * @param t 字符串 t
     * @return 判断 s 是否为 t 的子序列
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
        return false;
    }
    //endregion

    //region 413. 等差数列划分 20230225

    /**
     * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
     * <p>
     * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
     * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
     * 子数组 是数组中的一个连续序列。
     * <p>
     * 示例 1：
     * 输入：nums = [1,2,3,4]
     * 输出：3
     * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
     * 示例 2：
     * 输入：nums = [1]
     * 输出：0
     * 提示：
     * <p>
     * 1 <= nums.length <= 5000
     * -1000 <= nums[i] <= 1000
     *
     * @param nums 整数数组 nums
     * @return 返回数组 nums 中所有为等差数组的子数组个数
     */
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }
        int begin = 0, end = 0, distance = Integer.MIN_VALUE, res = 0;
        for (int i = 1; i < nums.length; i++) {
            if (distance == Integer.MIN_VALUE) {
                distance = nums[i] - nums[i - 1];
            }
            if (nums[i] - nums[i - 1] == distance) {
                end = i;
            } else {
                if (end - begin >= 2) {
                    res += calculateNumber(end - begin + 1);
                }
                i--;
                begin = i;
                end = i;
                distance = Integer.MIN_VALUE;
            }
        }
        if (end == nums.length - 1 && end - begin >= 2) {
            res = res + calculateNumber(end - begin + 1);
        }
        return res;
    }

    public int calculateNumber(int n) {
        int res = 0;
        for (int i = 1; i <= n - 3 + 1; i++) {
            res = res + i;
        }
        return res;
    }

    //动态规划的解法
    public int numberOfArithmeticSlices1(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }
        int res = 0;
        //存储增量
        // 1, 2, 3, 6, 8, 10 原数组
        // 0  0  1  0  0  1 增量
        int[] dp = new int[nums.length];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;//确定增量
                res += dp[i];//添加增量
            }
        }
        return res;
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
     * @param n 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列
     * @return 计算 F(n)
     */
    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    HashMap<Integer, Integer> hashMapfib = new HashMap<>();

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

    //region 516. 最长回文子序列

    /**
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     * <p>
     * 示例 1：
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出：2
     * 解释：一个可能的最长回文子序列为 "bb" 。
     * <p>
     * 提示：
     * 1 <= s.length <= 1000
     * s 仅由小写英文字母组成
     *
     * @param s 字符串 s
     * @return 最长的回文子序列，并返回该序列的长度
     */
    public int longestPalindromeSubseq(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        for (int i = length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i + 1; j < length; j++) {
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][length - 1];
    }
    //endregion

    //region 714. 买卖股票的最佳时机含手续费 20230220

    /**
     * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     * <p>
     * 示例 1：
     * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
     * 输出：8
     * 解释：能够达到的最大利润:
     * 在此处买入 prices[0] = 1
     * 在此处卖出 prices[3] = 8
     * 在此处买入 prices[4] = 4
     * 在此处卖出 prices[5] = 9
     * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
     * 示例 2：
     * 输入：prices = [1,3,7,5,10,3], fee = 3
     * 输出：6
     * <p>
     * 提示：
     * 1 <= prices.length <= 5 * 10^4
     * 1 <= prices[i] < 5 * 10^4
     * 0 <= fee < 5 * 10^4
     *
     * @param prices 整数数组 prices
     * @param fee    整数 fee 代表了交易股票的手续费用
     * @return 利润的最大值
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int dp0 = 0, dp1 = -prices[0] - fee;
        for (int i = 1; i < prices.length; i++) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i] - fee);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }
    //endregion

    //region 740. 删除并获得点数 20230216

    /**
     * 给你一个整数数组 nums ，你可以对它进行一些操作。
     * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
     * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     * <p>
     * 示例 1：
     * 输入：nums = [3,4,2]
     * 输出：6
     * 解释：
     * 删除 4 获得 4 个点数，因此 3 也被删除。
     * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
     * 示例 2：
     * 输入：nums = [2,2,3,3,3,4]
     * 输出：9
     * 解释：
     * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
     * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
     * 总共获得 9 个点数。
     * <p>
     * 提示：
     * 1 <= nums.length <= 2 * 104
     * 1 <= nums[i] <= 104
     *
     * @param nums 整数数组 nums
     * @return 操作获得的最大点数
     */
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                hashMap.put(num, hashMap.get(num) + num);
            } else {
                hashMap.put(num, num);
            }
            if (max < num) {
                max = num;
            }
        }
        int[] list = new int[max + 1];
        for (Integer key : hashMap.keySet()) {
            list[key] = hashMap.get(key);
        }
        if (list.length == 1) {
            return list[0];
        }
        int first = list[0], second = Math.max(list[0], list[1]);
        for (int i = 2; i < list.length; i++) {
            int temp = second;
            second = Math.max(first + list[i], second);
            first = temp;
        }
        return second;
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
     * @param cost 整数数组 cost
     * @return 计算并返回达到楼梯顶部的最低花费
     */
    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        int[] minCost = new int[length];
        minCost[0] = 0;
        minCost[1] = Math.min(cost[0], cost[1]);
        for (int i = 2; i < length; i++) {
            minCost[i] = Math.min(minCost[i - 1] + cost[i], minCost[i - 2] + cost[i - 1]);
        }
        return minCost[length - 1];
    }
    // endregion

    //region 877. 石子游戏   20191022  动态规划

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
     * @param piles 正整数颗石子piles
     * @return 返回输赢
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

    //region  918. 环形子数组的最大和 20230217

    /**
     * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
     * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
     * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
     * <p>
     * 示例 1：
     * 输入：nums = [1,-2,3,-2]
     * 输出：3
     * 解释：从子数组 [3] 得到最大和 3
     * 示例 2：
     * 输入：nums = [5,-3,5]
     * 输出：10
     * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
     * 示例 3：
     * 输入：nums = [3,-2,2,-3]
     * 输出：3
     * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
     * <p>
     * 提示：
     * n == nums.length
     * 1 <= n <= 3 * 10^4
     * -3 * 10^4 <= nums[i] <= 3 * 10^4
     *
     * @param nums 长度为 n 的环形整数数组 nums
     * @return 返回 nums 的非空子数组的最大可能和
     */
    public int maxSubarraySumCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int curMax, max, curMin, min, sum;
        curMax = max = curMin = min = sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            curMax = curMax > 0 ? curMax + nums[i] : nums[i];
            max = Math.max(curMax, max);
            curMin = curMin < 0 ? curMin + nums[i] : nums[i];
            min = Math.min(curMin, min);
        }
        if (max < 0) {
            return max;
        }
        return Math.max(max, sum - min);
    }
    //endregion

    //region  931. 下降路径最小和  20230225

    /**
     * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
     * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
     * 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
     * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
     * <p>
     * 示例 1：
     * 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
     * 输出：13
     * 解释：如图所示，为和最小的两条下降路径
     * 示例 2：
     * <p>
     * 输入：matrix = [[-19,57],[-40,-5]]
     * 输出：-59
     * 解释：如图所示，为和最小的下降路径
     * <p>
     * 提示：
     * n == matrix.length == matrix[i].length
     * 1 <= n <= 100
     * -100 <= matrix[i][j] <= 100
     *
     * @param matrix n x n 的 方形 整数数组 matrix
     * @return 和最小的下降路径
     */
    public int minFallingPathSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0) {
                    dp[i][j] = matrix[i][j];
                } else {
                    if (j == 0) {
                        dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], dp[i - 1][j + 1]);
                    } else if (j == matrix.length - 1) {
                        dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                    } else {
                        dp[i][j] = matrix[i][j] + Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i - 1][j + 1]);
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < dp[dp.length - 1].length; j++) {
            if (dp[dp.length - 1][j] < res) {
                res = dp[dp.length - 1][j];
            }
        }
        return res;
    }
    //endregion

    //region 1014. 最佳观光组合  20230226

    /**
     * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
     * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
     * 返回一对观光景点能取得的最高分。
     * <p>
     * 示例 1：
     * 输入：values = [8,1,5,2,6]
     * 输出：11
     * 解释：i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
     * 示例 2：
     * 输入：values = [1,2]
     * 输出：2
     *
     * @param values 正整数数组 values
     * @return 观光景点能取得的最高分
     */
    public int maxScoreSightseeingPair(int[] values) {
        int maxScore = 0, mx = values[0];
        for (int i = 1; i < values.length; i++) {
            maxScore = Math.max(maxScore, mx + values[i] - i);
            mx = Math.max(mx, values[i] + i);
        }
        return maxScore;
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
     * @param n 第 N 个泰波那契数
     * @return 第 N 个泰波那契数的值
     */
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int p, q = 0, r = 1, s = 1;
        for (int i = 3; i <= n; i++) {
            p = q;
            q = r;
            r = s;
            s = p + q + r;
        }
        return s;
    }
    //endregion

    //region  1143. 最长公共子序列 20230302

    /**
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     * <p>
     * 示例 1：
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     * 示例 2：
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
     * 示例 3：
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0 。
     * <p>
     * 提示：
     * 1 <= text1.length, text2.length <= 1000
     * text1 和 text2 仅由小写英文字符组成。
     *
     * @param text1 字符串 text1
     * @param text2 字符串 text2
     * @return 两个字符串的最长公共子序列的长度
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
    //endregion

    //region 1277. 统计全为 1 的正方形子矩阵  20230227

    /**
     * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
     * <p>
     * 示例 1：
     * 输入：matrix =
     * [
     * [0,1,1,1],
     * [1,1,1,1],
     * [0,1,1,1]
     * ]
     * 输出：15
     * 解释：
     * 边长为 1 的正方形有 10 个。
     * 边长为 2 的正方形有 4 个。
     * 边长为 3 的正方形有 1 个。
     * 正方形的总数 = 10 + 4 + 1 = 15.
     * 示例 2：
     * 输入：matrix =
     * [
     * [1,0,1],
     * [1,1,0],
     * [1,1,0]
     * ]
     * 输出：7
     * 解释：
     * 边长为 1 的正方形有 6 个。
     * 边长为 2 的正方形有 1 个。
     * 正方形的总数 = 6 + 1 = 7.
     * <p>
     * 提示：
     * 1 <= arr.length <= 300
     * 1 <= arr[0].length <= 300
     * 0 <= arr[i][j] <= 1
     *
     * @param matrix m * n 的矩阵
     * @return 正方形的总数
     */
    public int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = 0;
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                res += dp[i][j];
            }
        }
        return res;
    }
    //endregion

    // region 1269. 停在原地的方案数 20210513

    /**
     * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
     * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
     * 给你两个整数 steps 和arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
     * 由于答案可能会很大，请返回方案数 模10^9 + 7 后的结果。
     * <p>
     * 示例 1：
     * 输入：steps = 3, arrLen = 2
     * 输出：4
     * 解释：3 步 后，总共有 4 种不同的方法可以停在索引 0 处。
     * 向右，向左，不动
     * 不动，向右，向左
     * 向右，不动，向左
     * 不动，不动，不动
     * 示例 2：
     * 输入：steps = 2, arrLen = 4
     * 输出：2
     * 解释：2 步 后，总共有 2 种不同的方法可以停在索引 0 处。
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
     * @param steps  整数 steps
     * @param arrLen 整数 arrLen
     * @return 返回方案数 模10^9 + 7 后的结果
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

    //region 1314. 矩阵区域和  20230226

    /**
     * 给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：
     * i - k <= r <= i + k,
     * j - k <= c <= j + k 且
     * (r, c) 在矩阵内。
     * <p>
     * 示例 1：
     * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
     * 输出：[[12,21,16],[27,45,33],[24,39,28]]
     * 示例 2：
     * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
     * 输出：[[45,45,45],[45,45,45],[45,45,45]]
     * <p>
     * 提示：
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n, k <= 100
     * 1 <= mat[i][j] <= 100
     *
     * @param mat m x n 的矩阵
     * @param k   整数 k
     * @return 返回一个矩阵 answer
     */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int n = mat.length, m = mat[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + mat[i - 1][j - 1] - dp[i - 1][j - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int x1 = i - k, y1 = j - k, x2 = i + k, y2 = j + k;
                if (x1 < 1) {
                    x1 = 1;
                }
                if (y1 < 1) {
                    y1 = 1;
                }
                if (x2 > n) {
                    x2 = n;
                }
                if (y2 > m) {
                    y2 = m;
                }
                mat[i - 1][j - 1] = dp[x2][y2] + dp[x1 - 1][y1 - 1] - dp[x1 - 1][y2] - dp[x2][y1 - 1];
            }
        }
        return mat;
    }
    //endregion

    //region 1567. 乘积为正数的最长子数组长度 20230226

    /**
     * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
     * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
     * 请你返回乘积为正数的最长子数组长度。
     * <p>
     * <p>
     * 示例  1：
     * 输入：nums = [1,-2,-3,4]
     * 输出：4
     * 解释：数组本身乘积就是正数，值为 24 。
     * 示例 2：
     * 输入：nums = [0,1,-2,-3,-4]
     * 输出：3
     * 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
     * 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。
     * 示例 3：
     * 输入：nums = [-1,-2,-3,0,1]
     * 输出：2
     * 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
     * <p>
     * 提示：
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     *
     * @param nums 整数数组 nums
     * @return 返回乘积为正数的最长子数组长度
     */
    public int getMaxLen(int[] nums) {
        int length = nums.length;
        int[] positive = new int[length];
        int[] negative = new int[length];
        if (nums[0] > 0) {
            positive[0] = 1;
        } else if (nums[0] < 0) {
            negative[0] = 1;
        }
        int maxLength = positive[0];
        for (int i = 1; i < length; i++) {
            if (nums[i] > 0) {
                positive[i] = positive[i - 1] + 1;
                negative[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
            } else if (nums[i] < 0) {
                positive[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
                negative[i] = positive[i - 1] + 1;
            } else {
                positive[i] = 0;
                negative[i] = 0;
            }
            maxLength = Math.max(maxLength, positive[i]);
        }
        return maxLength;
    }
    //endregion

    public static void main(String[] args) {
//        int[][] arrays=new int[1][2];
//        arrays[0][0]=1;
//        arrays[0][1]=0;
//        int num=(new TargetDynamicProgramming()).uniquePathsWithObstacles(arrays);
//        int num=(new TargetDynamicProgramming()).numWays(4,3);
//        int num = new TargetDynamicProgramming().numberOfArithmeticSlices(new int[]{1, 2, 3, 8, 9, 10});
        new TargetDynamicProgramming().minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}});
    }
}
