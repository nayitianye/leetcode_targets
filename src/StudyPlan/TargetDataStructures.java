package StudyPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author yyb
 * leetcode_studyplan_data_structures
 * leetcode 学习计划 数据结构
 */
public class TargetDataStructures {

    //region    20230305    1. 两数之和

    /**
     * https://leetcode.cn/problems/two-sum/
     *
     * @param nums   一个整数数组 nums
     * @param target 一个整数目标值 target
     * @return 返回它们的数组下标
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{i, hashMap.get(target - nums[i])};
            } else {
                hashMap.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }
    //endregion

    //region    20230305    53. 最大子数组和

    /**
     * https://leetcode.cn/problems/maximum-subarray/
     *
     * @param nums 整数数组 nums
     * @return 找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
     */
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        //dp 数组表示以nums[i]结尾的最大数组
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    //endregion

    //region    20230305    88. 合并两个有序数组

    /**
     * https://leetcode.cn/problems/merge-sorted-array/
     *
     * @param nums1 递减顺序排列的整数数组 nums1
     * @param m     表示 nums1 中的元素数目
     * @param nums2 递减顺序排列的整数数组 nums2
     * @param n     表示 nums1 中的元素数目
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len1 = m - 1;
        int len2 = n - 1;
        int len = m + n - 1;
        while (len1 >= 0 && len2 >= 0) {
            //注意--符号再后面，表示先进行计算再减1，这种缩写缩短了代码
            nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
        }
        // 表示将nums2数组从下标0位置开始，拷贝到nums1数组中，从下标0位置开始，长度为len2+1
        System.arraycopy(nums2, 0, nums1, 0, len2 + 1);
    }
    //endregion

    //region    20230307    118. 杨辉三角

    /**
     * https://leetcode.cn/problems/pascals-triangle/
     *
     * @param numRows 杨辉三角的行数
     * @return 返回n行的杨辉三角组成的list
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

    //region    20230306    121. 买卖股票的最佳时机

    /**
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
     *
     * @param prices 数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格
     * @return 返回你可以从这笔交易中获取的最大利润
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(prices[i] - min, max);
        }
        return max;
    }
    //endregion

    //region    20230305    217. 存在重复元素

    /**
     * https://leetcode.cn/problems/contains-duplicate/
     *
     * @param nums 整数数组 nums
     * @return 如果任一数值在数组中出现至少两次 ，返回 true ；如果数组中每个元素互不相同 ，返回 false 。
     */
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                return true;
            } else {
                hashMap.put(num, 1);
            }
        }
        return false;
    }
    //endregion

    //region    20230306    350. 两个数组的交集 II

    /**
     * https://leetcode.cn/problems/intersection-of-two-arrays-ii/
     *
     * @param nums1 整数数组 nums1
     * @param nums2 整数数组 nums2
     * @return 以数组形式返回两数组的交集
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums2[index2] < nums1[index1]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
    //endregion

    //region    20230307    566. 重塑矩阵

    /**
     * https://leetcode.cn/problems/reshape-the-matrix/
     *
     * @param mat 数组 mat
     * @param r   r 行
     * @param c   c 列
     * @return 将数组 mat 重塑成 r 行 c 列的数组
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }

        int[][] ans = new int[r][c];
        for (int x = 0; x < m * n; ++x) {
            ans[x / c][x % c] = mat[x / n][x % n];
        }
        return ans;
    }
    //endregion

    public static void main(String[] args) {
        //1. 两数之和
        System.out.println(Arrays.toString(new TargetDataStructures().twoSum(new int[]{1, 2, 7, 9, 11}, 11)));
        //217. 存在重复元素
        System.out.println(new TargetDataStructures().containsDuplicate(new int[]{1, 2, 3, 4, 4, 6, 6}));
        //53. 最大子数组和
        System.out.println(new TargetDataStructures().maxSubArray(new int[]{1, 3, 4, -10, 6, 18, -5, 12}));
        //88. 合并两个有序数组
        new TargetDataStructures().merge(new int[]{1, 2, 3, 4, 4, 5, 0, 0, 0, 0}, 6, new int[]{2, 3, 5, 7}, 4);
        //350. 两个数组的交集 II
        System.out.println(Arrays.toString(new TargetDataStructures().intersect(new int[]{1, 2, 3, 3}, new int[]{1, 2, 4})));
        //121. 买卖股票的最佳时机
        System.out.println(new TargetDataStructures().maxProfit(new int[]{7, 5, 4, 3, 2, 1}));
        //566. 重塑矩阵
        System.out.println(new TargetDataStructures().matrixReshape(new int[][]{{1, 2}, {3, 4}}, 1, 4));
    }
}
