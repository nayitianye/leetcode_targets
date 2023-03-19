package StudyPlan;

import java.util.PriorityQueue;

/**
 * @author yyb
 * leetcode_studyplan_algorithms_Beginners
 * leetcode 学习计划 算法入门
 */
public class TargetAlgorithmsBeginner {

    //region    20230319    35. 搜索插入位置

    /**
     * https://leetcode.cn/problems/search-insert-position/
     *
     * @param nums   一个排序数组 nums
     * @param target 一个目标值 target
     * @return 数组中找到目标值，并返回其索引
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        if (nums[0] > target) {
            return 0;
        }
        if (nums[nums.length - 1] < target) {
            return nums.length;
        }
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[right] < target ? right + 1 : right;
    }
    //endregion

    //region    20230320    189. 轮转数组

    /**
     * https://leetcode.cn/problems/rotate-array/
     *
     * @param nums 整数数组 nums
     * @param k    数组中的元素向右轮转 k 个位置，其中 k 是非负数
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    //endregion

    //region    20230319    278. 第一个错误的版本

    /**
     * https://leetcode.cn/problems/first-bad-version/
     *
     * @param n n 个版本 [1, 2, ..., n]
     * @return 导致之后所有版本出错的第一个错误的版本
     */
    public int firstBadVersion(int n) {
        int first = 0, last = n, mid = 0;
        while (first < last) {
            mid = first + (last - first) / 2;
            if (isBadVersion(mid)) {
                last = mid;
            } else {
                first = mid + 1;
            }
        }
        return isBadVersion(mid) ? mid : mid + 1;
    }

    public boolean isBadVersion(int n) {
        return n == 10;
    }
    //endregion

    //region    20230319    704. 二分查找

    /**
     * https://leetcode.cn/problems/binary-search/
     *
     * @param nums   一个 n 个元素有序的（升序）整型数组 nums
     * @param target 一个目标值 target
     * @return 一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    //endregion

    //region    20230320    977. 有序数组的平方

    /**
     * https://leetcode.cn/problems/squares-of-a-sorted-array/
     *
     * @param nums 整数数组 nums
     * @return 返回每个数字的平方组成的新数组，要求也按非递减顺序排序
     */
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                res[pos] = nums[i] * nums[i];
                i++;
            } else {
                res[pos] = nums[j] * nums[j];
                j--;
            }
            pos--;
        }
        return res;
    }
    //endregion

}
