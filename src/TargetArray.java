import java.util.*;

/**
 * @author yyb
 * leetcode_tag_array
 * leetcode 标签 数组
 */
public class TargetArray {

    //region    20190830    1. 两数之和

    /**
     * https://leetcode.cn/problems/two-sum/
     * @param nums  整数数组 nums
     * @param target  整数目标值 target
     * @return  在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标
     */
    private int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }
    //endregion

    //region    20190830    15. 三数之和

    /**
     * https://leetcode.cn/problems/3sum/
     *
     * @param nums  整数数组 nums
     * @return  所有和为 0 且不重复的三元组
     */
    private List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 3) {
            return ans;
        }
        Arrays.sort(nums);
        if (nums[len - 1] < 0) {
            return ans;
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++;
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--;
                    }
                    L++;
                    R--;
                } else if (sum < 0) {
                    L++;
                } else if (sum > 0) {
                    R--;
                }
            }
        }
        return ans;
    }
    //endregion

    //region    20191011    39. 组合总和

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，
     * 找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1:
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     * [7],
     * [2,2,3]
     * ]
     * <p>
     * 示例 2:
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     * [2,2,2,2],
     * [2,3,3],
     * [3,5]
     * ]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;
        Arrays.sort(candidates);
        findCombinationSum(target, 0, new Stack<>(), len, candidates, res);
        return res;
    }

    private void findCombinationSum(int residue, int start, Stack<Integer> pre, int len, int[] candidates, List<List<Integer>> res) {
        if (residue == 0) {
            res.add(new ArrayList<>(pre));
        }
        for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
            pre.add(candidates[i]);
            findCombinationSum(residue - candidates[i], i, pre, len, candidates, res);
            pre.pop();
        }
    }
    //endregion

    //region    20191011    40. 组合总和 II

    /**
     * 给定一个数组 candidates 和一个目标数 target ，
     * 找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 说明：
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1:
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     * [1, 7],
     * [1, 2, 5],
     * [2, 6],
     * [1, 1, 6]
     * ]
     * <p>
     * 示例 2:
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     * [1,2,2],
     * [5]
     * ]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        findConbinationSum2(target, 0, new Stack<>(), candidates, res);
        return res;
    }

    private void findConbinationSum2(int target, int index, Stack<Integer> stack, int[] candidates, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(stack));
        }
        for (int i = index; i < candidates.length && target - candidates[i] >= 0; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            stack.push(candidates[i]);
            findConbinationSum2(target - candidates[i], i + 1, stack, candidates, res);
            stack.pop();
        }
    }
    //endregion

    //region    20171007    48. 旋转图像

    /**
     * 给定一个 n × n 的二维矩阵表示一个图像。
     * 将图像顺时针旋转 90 度。
     * <p>
     * 说明：
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。
     * 请不要使用另一个矩阵来旋转图像。
     * <p>
     * 示例 1:
     * 给定 matrix =
     * [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * ],
     * 原地旋转输入矩阵，使其变为:
     * [
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]
     * ]
     * <p>
     * 示例 2:
     * 给定 matrix =
     * [
     * [ 5, 1, 9,11],
     * [ 2, 4, 8,10],
     * [13, 3, 6, 7],
     * [15,14,12,16]
     * ],
     * 原地旋转输入矩阵，使其变为:
     * [
     * [15,13, 2, 5],
     * [14, 3, 4, 1],
     * [12, 6, 8, 9],
     * [16, 7,10,11]
     * ]
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }
    //endregion

    //region    20190926    53. 最大子序和

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * @param nums
     * @return
     */
    private int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int flag = nums[i] + nums[i - 1];
            if (flag > nums[i]) {
                nums[i] = flag;
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
    //endregion

    //region    20191007    62. 不同路径

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

    //region    20191007    63. 不同路径 II

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

    //region    20191022    169. 求众数

    /**
     * 给定一个大小为 n 的数组，找到其中的众数。
     * 众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在众数。
     * <p>
     * 示例 1:
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
            } else {
                hashMap.put(nums[i], 1);
            }
        }
        Map.Entry<Integer, Integer> res = null;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (res == null || entry.getValue() > res.getValue()) {
                res = entry;
            }
        }
        return res.getKey();
    }
    //endregion

    //region    20190926    561. 数组拆分 I

    /**
     * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，
     * 使得从1 到 n 的 min(ai, bi) 总和最大。
     * <p>
     * 示例 1:
     * 输入: [1,4,3,2]
     * 输出: 4
     * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
     * <p>
     * 提示:
     * n 是正整数,范围在 [1, 10000].
     * 数组中的元素范围在 [-10000, 10000].
     *
     * @param nums
     * @return
     */
    private int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            i++;
        }
        return sum;
    }
    //endregion

    //region    20191017    621. 任务调度器

    /**
     * 给定一个用字符数组表示的 CPU 需要执行的任务列表。
     * 其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
     * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
     * CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
     * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，
     * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * 你需要计算完成所有任务所需要的最短时间。
     * <p>
     * 示例 1：
     * 输入: tasks = ["A","A","A","B","B","B"], n = 2
     * 输出: 8
     * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
     * <p>
     * 注：
     * 任务的总个数为 [1, 10000]。
     * n 的取值范围为 [0, 100]。
     *
     * @param tasks
     * @param n
     * @return
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

    //region    20210429    633.平方数之和

    /**
     * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a * a + b * b = c
     * <p>
     * 示例 1：
     * <p>
     * 输入：c = 5
     * 输出：true
     * 解释：1 * 1 + 2 * 2 = 5
     * 示例 2：
     * <p>
     * 输入：c = 3
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：c = 4
     * 输出：true
     * 示例 4：
     * <p>
     * 输入：c = 2
     * 输出：true
     * 示例 5：
     * <p>
     * 输入：c = 1
     * 输出：true
     */
    public boolean judgeSquareSum(int c) {
        boolean result = false;
        for (int i = 0; i <= Math.sqrt(c); i++) {
            int num1 = i * i;
            int num2 = (int) Math.sqrt(c - num1);
            if (num1 + num2 * num2 == c) {
                result = true;
                break;
            }
        }
        return result;
    }
    //endregion

    //region    20190926    832. 翻转图像

    /**
     * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
     * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
     * 反转图片的意思是图片中的0全部被1替换，1全部被0替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
     * <p>
     * 示例 1:
     * 输入: [[1,1,0],[1,0,1],[0,0,0]]
     * 输出: [[1,0,0],[0,1,0],[1,1,1]]
     * 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     * 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
     * <p>
     * 示例 2:
     * 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
     * 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     * 然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 说明:
     * 1 <= A.length = A[0].length <= 20
     * 0 <= A[i][j] <= 1
     *
     * @param A
     * @return
     */
    private static int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < (A[i].length + 1) / 2; j++) {
                if (A[i][j] != A[i][A[i].length - 1 - j]) {
                    continue;
                } else {
                    if (A[i][j] == 1) {
                        if (j == A[i].length - 1 - j) {
                            A[i][j] = 0;
                        } else {
                            A[i][j] = 0;
                            A[i][A[i].length - 1 - j] = 0;
                        }
                    } else {
                        if (j == A[i].length - 1 - j) {
                            A[i][j] = 1;
                        } else {
                            A[i][j] = 1;
                            A[i][A[i].length - 1 - j] = 1;
                        }
                    }
                }
            }
        }
        return A;
    }
    //endregion

    //region    20190926    867. 转置矩阵

    /**
     * 给定一个矩阵 A， 返回 A 的转置矩阵。
     * 矩阵的转置是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
     * <p>
     * 示例 1：
     * 输入：[[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[1,4,7],[2,5,8],[3,6,9]]
     * <p>
     * 示例 2：
     * 输入：[[1,2,3],[4,5,6]]
     * 输出：[[1,4],[2,5],[3,6]]
     * <p>
     * 提示：
     * 1 <= A.length <= 1000
     * 1 <= A[0].length <= 1000
     *
     * @param A
     * @return
     */
    private int[][] transpose(int[][] A) {
        int[][] res = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                res[j][i] = A[i][j];
            }
        }
        return res;
    }
    //endregion

    //region    20190926    905. 按奇偶排序数组

    /**
     * 给定一个非负整数数组 A，返回一个数组，在该数组中，A 的所有偶数元素之后跟着所有奇数元素。
     * 你可以返回满足此条件的任何数组作为答案。
     * <p>
     * 示例：
     * 输入：[3,1,2,4]
     * 输出：[2,4,3,1]
     * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
     * <p>
     * 提示：
     * 1 <= A.length <= 5000
     * 0 <= A[i] <= 5000
     *
     * @param A
     * @return
     */
    private int[] sortArrayByParity(int[] A) {
        int left = 0;
        int right = A.length - 1;
        while (left < right) {
            while (A[left] % 2 == 0 && left < right) {
                left++;
            }
            while (A[right] % 2 == 1 && left < right) {
                right--;
            }
            int flag = A[left];
            A[left] = A[right];
            A[right] = flag;
        }
        return A;
    }
    //endregion

    //region    20190926    977. 有序数组的平方

    /**
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     * <p>
     * 示例 1：
     * 输入：[-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * <p>
     * 示例 2：
     * 输入：[-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     * 提示：
     * <p>
     * 1 <= A.length <= 10000
     * -10000 <= A[i] <= 10000
     * A 已按非递减顺序排序。
     *
     * @param A
     * @return
     */
    private int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }
    //endregion

    //region    20190926    1051. 高度检查器

    /**
     * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
     * 请你返回至少有多少个学生没有站在正确位置数量。
     * 该人数指的是：能让所有学生以 非递减 高度排列的必要移动人数。
     * <p>
     * 示例：
     * 输入：[1,1,4,2,1,3]
     * 输出：3
     * 解释：
     * 高度为 4、3 和最后一个 1 的学生，没有站在正确的位置。
     * <p>
     * 提示：
     * 1 <= heights.length <= 100
     * 1 <= heights[i] <= 100
     *
     * @param heights
     * @return
     */
    private int heightChecker(int[] heights) {
        int[] newheights = Arrays.copyOf(heights, heights.length);
        Arrays.sort(newheights);
        int flag = 0;
        for (int i = 0; i < heights.length; i++) {
            if (newheights[i] != heights[i]) {
                flag++;
            }
        }
        return flag;
    }
    //endregion

    //region    20230403    1053. 交换一次的先前排列

    /**
     * https://leetcode.cn/problems/previous-permutation-with-one-swap
     *
     * @param arr 正整数数组 arr
     * @return 返回可在 一次交换（交换两数字 arr[i] 和 arr[j] 的位置）后得到的、按字典序排列小于 arr 的最大排列
     */
    public int[] prevPermOpt1(int[] arr) {
        int len = arr.length;
        int curMax = -1;
        int index = -1;
        boolean hasResult = false;
        for (int i = len - 2; i >= 0; i--) {
            //  此处逆序
            if (arr[i + 1] < arr[i]) {
                for (int j = i + 1; j < len; j++) {
                    if (arr[i] > arr[j]) {
                        hasResult = true;
                        if (arr[j] > curMax) {
                            curMax = arr[j];
                            index = j;
                        }
                    }
                }
            }
            if (hasResult) {
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
                return arr;
            }
        }
        return arr;
    }
    //endregion

    //region    20190926    1064. 不动点

    /**
     * 给定已经按升序排列、由不同整数组成的数组 A，返回满足 A[i] == i 的最小索引 i。
     * 如果不存在这样的 i，返回 -1。
     * <p>
     * 示例 1：
     * 输入：[-10,-5,0,3,7]
     * 输出：3
     * 解释：
     * 对于给定的数组，A[0] = -10，A[1] = -5，A[2] = 0，A[3] = 3，因此输出为 3 。
     * <p>
     * 示例 2：
     * 输入：[0,2,5,8,17]
     * 输出：0
     * 示例：
     * A[0] = 0，因此输出为 0 。
     * <p>
     * 示例 3：
     * 输入：[-10,-5,3,4,7,9]
     * 输出：-1
     * 解释：
     * 不存在这样的 i 满足 A[i] = i，因此输出为 -1 。
     * <p>
     * 提示：
     * 1 <= A.length < 10^4
     * -10^9 <= A[i] <= 10^9
     *
     * @param A
     * @return
     */
    private int fixedPoint(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == i) {
                return i;
            }
        }
        return -1;
    }
    //endregion

    //region    20190926    1085. 最小元素各数位之和

    /**
     * 给你一个正整数的数组 A。
     * 然后计算 S，使其等于数组 A 当中最小的那个元素各个数位上数字之和。
     * 最后，假如 S 所得计算结果是 奇数 的请你返回 0，否则请返回 1。
     * <p>
     * 示例 1:
     * <p>
     * 输入：[34,23,1,24,75,33,54,8]
     * 输出：0
     * 解释：
     * 最小元素为 1，该元素各个数位上的数字之和 S = 1，是奇数所以答案为 0。
     * <p>
     * 示例 2：
     * 输入：[99,77,33,66,55]
     * 输出：1
     * 解释：
     * 最小元素为 33，该元素各个数位上的数字之和 S = 3 + 3 = 6，是偶数所以答案为 1。
     * <p>
     * 提示：
     * 1 <= A.length <= 100
     * 1 <= A[i].length <= 100
     *
     * @param A
     * @return
     */
    private int sumOfDigits(int[] A) {
        int min = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] < min) {
                min = A[i];
            }
        }
        int sum = 0;
        while (min > 0) {
            sum += min % 10;
            min = min / 10;
        }
        return sum % 2 == 0 ? 1 : 0;
    }
    //endregion

    //region    20191022    1150. 检查一个数是否在数组中占绝大多数

    /**
     * 给出一个按 非递减 顺序排列的数组 nums，和一个目标数值 target。
     * 假如数组 nums 中绝大多数元素的数值都等于 target，则返回 True，
     * 否则请返回 False。
     * 所谓占绝大多数，是指在长度为 N 的数组中出现必须 超过 N/2 次。
     * 示例 1：
     * 输入：nums = [2,4,5,5,5,5,5,6,6], target = 5
     * 输出：true
     * <p>
     * 解释：
     * 数字 5 出现了 5 次，而数组的长度为 9。
     * 所以，5 在数组中占绝大多数，因为 5 次 > 9/2。
     * <p>
     * 示例 2：
     * 输入：nums = [10,100,101,101], target = 101
     * 输出：false
     * <p>
     * 解释：
     * 数字 101 出现了 2 次，而数组的长度是 4。
     * 所以，101 不是 数组占绝大多数的元素，因为 2 次 = 4/2。
     * <p>
     * 提示：
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^9
     * 1 <= target <= 10^9
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean isMajorityElement(int[] nums, int target) {
        int flag = 0;
        for (int num : nums) {
            if (num == target) {
                flag++;
            }
        }
        return flag > nums.length / 2;
    }

    /**
     * 双指针求解
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean isMajorityElement1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (nums.length == 1) {
            return nums[0] == target;
        }
        while (left < right) {
            if (nums[left] < target) {
                left++;
            }
            if (nums[left] > target) {
                return false;
            }
            if (nums[right] > target) {
                right--;
            }
            if (nums[right] < target) {
                return false;
            }
            if (nums[left] == nums[right] && nums[left] == target) {
                break;
            }
        }
        return right - left + 1 > nums.length / 2;
    }
    //endregion

    //region    20230330    1637. 两点之间不包含任何点的最宽垂直区域

    /**
     * https://leetcode.cn/problems/widest-vertical-area-between-two-points-containing-no-points/
     *
     * @param points n 个二维平面上的点 points ，其中 points[i] = [xi, yi]
     * @return 请你返回两点之间内部不包含任何点的 最宽垂直区域 的宽度
     */
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int max = 0;
        for (int i = 1; i < points.length; i++) {
            max = Math.max(points[i][0] - points[i - 1][0], max);
        }
        return max;
    }
    //endregion

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int sum = 0;
        for (int e : energy) {
            sum += e;
        }
        int trainingHours = initialEnergy > sum ? 0 : sum + 1 - initialEnergy;
        for (int e : experience) {
            if (initialExperience <= e) {
                trainingHours += 1 + (e - initialExperience);
                initialExperience = 2 * e + 1;
            } else {
                initialExperience += e;
            }
        }
        return trainingHours;
    }

    public static void main(String[] args) {
        int[][] A = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        System.out.println(flipAndInvertImage(A));
    }
}
