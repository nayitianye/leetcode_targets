import java.util.*;

/**
 * @author yyb
 * leetcode_tag_binary_search
 * leetcode 标签 二分查找
 */
public class TargetBinarySearch {

    //region 二分查找 通用算法流程

    /**
     * 范围查询规律
     * 初始化:
     * int left = 0;
     * int right = nums.length - 1;
     * 循环条件
     * left <= right
     * 右边取值
     * right = mid - 1
     * 左边取值
     * left = mid + 1
     * 查询条件
     * >= target值, 则 nums[mid] >= target时, 都减right = mid - 1
     * >  target值, 则 nums[mid] >  target时, 都减right = mid - 1
     * <= target值, 则 nums[mid] <= target时, 都加left = mid + 1
     * <  target值, 则 nums[mid] <  target时, 都加left = mid + 1
     * 结果
     * 求大于(含等于), 返回left
     * 求小于(含等于), 返回right
     * 核心思想: 要找某个值, 则查找时遇到该值时, 当前指针(例如right指针)要错过它, 让另外一个指针(left指针)跨过他(体现在left <= right中的=号), 则找到了
     */
    //endregion
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // region 33. 搜索旋转排序数组 20230223

    /**
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * <p>
     * 示例 1：
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * 示例 2：
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     * 示例 3：
     * 输入：nums = [1], target = 0
     * 输出：-1
     * <p>
     * 提示：
     * 1 <= nums.length <= 5000
     * -104 <= nums[i] <= 104
     * nums 中的每个值都 独一无二
     * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
     * -104 <= target <= 104
     *
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //先根据 nums[0] 与 target 的关系判断目标值是在左半段还是右半段
            if (target >= nums[0]) {
                //目标值在左半段时，若mid在右半段，则讲mid索引的值该成inf
                if (nums[mid] < nums[0]) {
                    nums[mid] = Integer.MAX_VALUE;
                }
            } else {
                // 目标值在右半段时，若 mid 在左半段，则将 mid 索引的值改成 -inf
                if (nums[mid] >= nums[0]) {
                    nums[mid] = Integer.MIN_VALUE;
                }
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    //endregion

    // region 34. 在排序数组中查找元素的第一个和最后一个位置 20230219

    /**
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     * <p>
     * 示例 1：
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     * <p>
     * 提示：
     * 0 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * nums 是一个非递减数组
     * -10^9 <= target <= 10^9
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        if (nums[0] > target || nums[nums.length - 1] < target) {
            return res;
        }
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                int start = mid, end = mid;
                while (start >= 0 && nums[start] == target) {
                    start--;
                }
                while (end <= nums.length - 1 && nums[end] == target) {
                    end++;
                }
                res[0] = ++start;
                res[1] = --end;
                break;
            }
        }
        return res;
    }
    //endregion

    //region 35. 搜索插入位置 20230218

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * <p>
     * 示例 1:
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * 示例 2:
     * 输入: nums = [1,3,5,6], target = 2
     * 输出: 1
     * 示例 3:
     * 输入: nums = [1,3,5,6], target = 7
     * 输出: 4
     * <p>
     * 提示:
     * 1 <= nums.length <= 10^4
     * -10^4 <= nums[i] <= 10^4
     * nums 为 无重复元素 的 升序 排列数组
     * -10^4 <= target <= 10^4
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums[0] > target) {
            return 0;
        }
        if (nums[nums.length - 1] < target) {
            return nums.length;
        }
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return nums[mid] > target ? mid : mid + 1;
    }
    //endregion

    //region 69. x 的平方根 20230219

    /**
     * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
     * <p>
     * 示例 1：
     * 输入：x = 4
     * 输出：2
     * 示例 2：
     * 输入：x = 8
     * 输出：2
     * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
     * <p>
     * 提示：
     * 0 <= x <= 2^31 - 1
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 0, right = x, mid;
        while (right - left > 1) {
            mid = left + (right - left) / 2;
            if (x / mid < mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }
    //endregion

    //region 74. 搜索二维矩阵 20230223

    /**
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * <p>
     * 示例 1：
     * <p>
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
     * 输出：false
     * <p>
     * 提示：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 100
     * -104 <= matrix[i][j], target <= 104
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int bottom = -1, top = matrix.length - 1, mid = 0;
        while (bottom < top) {
            mid = bottom + (top - bottom + 1) / 2;
            if (matrix[mid][0] <= target) {
                bottom = mid;
            } else {
                top = mid - 1;
            }
        }
        if (bottom == -1) {
            return false;
        }
        int left = 0, right = matrix[0].length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (matrix[bottom][mid] == target) {
                return true;
            } else if (matrix[bottom][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
    //endregion

    //region 153. 寻找旋转排序数组中的最小值  20230223

    /**
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * <p>
     * 示例 1：
     * 输入：nums = [3,4,5,1,2]
     * 输出：1
     * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
     * 示例 2：
     * 输入：nums = [4,5,6,7,0,1,2]
     * 输出：0
     * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
     * 示例 3：
     * 输入：nums = [11,13,15,17]
     * 输出：11
     * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
     * <p>
     * 提示：
     * n == nums.length
     * 1 <= n <= 5000
     * -5000 <= nums[i] <= 5000
     * nums 中的所有整数 互不相同
     * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
    //endregion

    //region 167. 两数之和 II - 输入有序数组  20230221

    /**
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，
     * 请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，
     * 则 1 <= index1 < index2 <= numbers.length 。
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * 你所设计的解决方案必须只使用常量级的额外空间。
     * <p>
     * <p>
     * 示例 1：
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     * 示例 2：
     * 输入：numbers = [2,3,4], target = 6
     * 输出：[1,3]
     * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
     * 示例 3：
     * 输入：numbers = [-1,0], target = -1
     * 输出：[1,2]
     * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     * <p>
     * 提示：
     * 2 <= numbers.length <= 3 * 10^4
     * -1000 <= numbers[i] <= 1000
     * numbers 按 非递减顺序 排列
     * -1000 <= target <= 1000
     * 仅存在一个有效答案
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        //二分法
        for (int i = 0; i < numbers.length; i++) {
            int left = i + 1, right = numbers.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }
    //endregion

    //region 278. 第一个错误版本 20211020

    /**
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     * <p>
     * 示例 1：
     * 输入：n = 5, bad = 4
     * 输出：4
     * 解释：
     * 调用 isBadVersion(3) -> false
     * 调用 isBadVersion(5) -> true
     * 调用 isBadVersion(4) -> true
     * 所以，4 是第一个错误的版本。
     * <p>
     * 示例 2：
     * 输入：n = 1, bad = 1
     * 输出：1
     * <p>
     * 提示：
     * 1 <= bad <= n <= 2^31 - 1
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
        if (n < 100) {
            return true;
        } else {
            return false;
        }
    }
    //endregion

    //region 367.有效的完全平方数 20230218

    /**
     * 给你一个正整数 num 。如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     * 完全平方数 是一个可以写成某个整数的平方的整数。换句话说，它可以写成某个整数和自身的乘积。
     * 不能使用任何内置的库函数，如  sqrt 。
     * <p>
     * 示例 1：
     * 输入：num = 16
     * 输出：true
     * 解释：返回 true ，因为 4 * 4 = 16 且 4 是一个整数。
     * 示例 2：
     * 输入：num = 14
     * 输出：false
     * 解释：返回 false ，因为 3.742 * 3.742 = 14 但 3.742 不是一个整数。
     * <p>
     * 提示：
     * 1 <= num <= 2^31 - 1
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1) {
            return true;
        }
        int left = 0, right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;
            if (square < num) {
                left = mid + 1;
            } else if (square > num) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
    //endregion

    //region 392. 判断子序列   20191022

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

    // region 441. 排列硬币 20230219

    /**
     * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
     * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
     * <p>
     * 示例 1：
     * 输入：n = 5
     * 输出：2
     * 解释：因为第三行不完整，所以返回 2 。
     * 示例 2：
     * 输入：n = 8
     * 输出：3
     * 解释：因为第四行不完整，所以返回 3 。
     *
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if ((long) mid * (mid + 1) <= (long) 2 * n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    // endregion

    //region 633. 平方数之和 20230222

    /**
     * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
     * <p>
     * 示例 1：
     * 输入：c = 5
     * 输出：true
     * 解释：1 * 1 + 2 * 2 = 5
     * 示例 2：
     * 输入：c = 3
     * 输出：false
     * <p>
     * 提示：
     * 0 <= c <= 2^31 - 1
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        for (long i = 0; i * i <= c; ++i) {
            long b = c - i * i;
            if (binarySearchJudgeSquareSum(0, b, b)) {
                return true;
            }
        }
        return false;
    }

    public boolean binarySearchJudgeSquareSum(long left, long right, long target) {
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (mid * mid == target) {
                return true;
            } else if (mid * mid < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
    //endregion

    //region 704. 二分查找 20211020

    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * <p>
     * 示例 1:
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * 示例 2:
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     * <p>
     * 提示：
     * 你可以假设 nums 中的所有元素是不重复的。
     * n 将在 [1, 10000]之间。
     * nums 的每个元素都将在 [-9999, 9999]之间。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums[0] > target || nums[nums.length - 1] < target) {
            return -1;
        }
        return BinarySearch(nums, target, 0, nums.length - 1);
    }

    public int BinarySearch(int[] nums, int target, int start, int end) {
        int index = (start + end) / 2;
        if (start > end) {
            return -1;
        }
        if (nums[index] == target) {
            return index;
        } else if (nums[index] < target) {
            return BinarySearch(nums, target, index + 1, end);
        } else {
            return BinarySearch(nums, target, start, index - 1);
        }
    }
    //endregion

    //region 744. 寻找比目标字母大的最小字母 20230219

    /**
     * 给你一个字符数组 letters，该数组按非递减顺序排序，以及一个字符 target。letters 里至少有两个不同的字符。
     * 返回 letters 中大于 target 的最小的字符。如果不存在这样的字符，则返回 letters 的第一个字符。
     * <p>
     * 示例 1：
     * 输入: letters = ["c", "f", "j"]，target = "a"
     * 输出: "c"
     * 解释：letters 中字典上比 'a' 大的最小字符是 'c'。
     * 示例 2:
     * 输入: letters = ["c","f","j"], target = "c"
     * 输出: "f"
     * 解释：letters 中字典顺序上大于 'c' 的最小字符是 'f'。
     * 示例 3:
     * 输入: letters = ["x","x","y","y"], target = "z"
     * 输出: "x"
     * 解释：letters 中没有一个字符在字典上大于 'z'，所以我们返回 letters[0]。
     * <p>
     * 提示：
     * 2 <= letters.length <= 10^4
     * letters[i] 是一个小写字母
     * letters 按非递减顺序排序
     * letters 最少包含两个不同的字母
     * target 是一个小写字母
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        //如果所有的字符都比target大或者小，直接返回首字母
        if (target < letters[0] || letters[letters.length - 1] < target) {
            return letters[0];
        }
        int left = 0, right = letters.length - 1, mid;
        while (right > left) {
            mid = left + (right - left) / 2;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return letters[left] > target ? letters[left] : letters[0];
    }
    //endregion

    //region 852. 山脉数组的峰顶索引   20191022

    /**
     * 我们把符合下列属性的数组 A 称作山脉：
     * A.length >= 3
     * 存在 0 < i < A.length - 1
     * 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
     * 给定一个确定为山脉的数组，
     * 返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
     * <p>
     * 示例 1：
     * 输入：[0,1,0]
     * 输出：1
     * <p>
     * 示例 2：
     * 输入：[0,2,1,0]
     * 输出：1
     * 提示：
     * 3 <= A.length <= 10000
     * 0 <= A[i] <= 10^6
     * A 是如上定义的山脉
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (arr[left] < arr[left + 1]) {
            left++;
        }
        while (arr[right] < arr[right - 1]) {
            right--;
        }
        return arr[left] >= arr[right] ? left : right;
    }
    //endregion

    //region 1150. 检查一个数是否在数组中占绝大多数  20191022   暴力+双指针

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

    //region 1337. 矩阵中战斗力最弱的 K 行 20230223

    /**
     * 给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
     * 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
     * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
     * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
     * <p>
     * 示例 1：
     * 输入：mat =
     * [[1,1,0,0,0],
     * [1,1,1,1,0],
     * [1,0,0,0,0],
     * [1,1,0,0,0],
     * [1,1,1,1,1]],
     * k = 3
     * 输出：[2,0,3]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 2
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 2
     * 行 4 -> 5
     * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
     * 示例 2：
     * 输入：mat =
     * [[1,0,0,0],
     * [1,1,1,1],
     * [1,0,0,0],
     * [1,0,0,0]],
     * k = 2
     * 输出：[0,2]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 1
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 1
     * 从最弱到最强对这些行排序后得到 [0,2,3,1]
     * <p>
     * 提示：
     * m == mat.length
     * n == mat[i].length
     * 2 <= n, m <= 100
     * 1 <= k <= m
     * matrix[i][j] 不是 0 就是 1
     *
     * @param mat
     * @param k
     * @return
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return new int[]{0};
        }
        List<int[]> power = new ArrayList<int[]>();
        for (int i = 0; i < mat.length; i++) {
            int left = 0, right = mat[i].length - 1, pos = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (mat[i][mid] == 0) {
                    right = mid - 1;
                } else {
                    pos = mid;
                    left = mid + 1;
                }
            }
            power.add(new int[]{pos + 1, i});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                if (pair1[0] != pair2[0]) {
                    return pair1[0] - pair2[0];
                } else {
                    return pair1[1] - pair2[1];
                }
            }
        });
        for (int[] pair : power) {
            pq.offer(pair);
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = pq.poll()[1];
        }
        return ans;
    }

    //endregion

    //region  1346. 检查整数及其两倍数是否存在 20230222

    /**
     * 给你一个整数数组 arr，请你检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。
     * 更正式地，检查是否存在两个下标 i 和 j 满足：
     * i != j
     * 0 <= i, j < arr.length
     * arr[i] == 2 * arr[j]
     * <p>
     * <p>
     * 示例 1：
     * 输入：arr = [10,2,5,3]
     * 输出：true
     * 解释：N = 10 是 M = 5 的两倍，即 10 = 2 * 5 。
     * 示例 2：
     * 输入：arr = [7,1,14,11]
     * 输出：true
     * 解释：N = 14 是 M = 7 的两倍，即 14 = 2 * 7 。
     * 示例 3：
     * 输入：arr = [3,1,7,11]
     * 输出：false
     * 解释：在该情况下不存在 N 和 M 满足 N = 2 * M 。
     * <p>
     * 提示：
     * 2 <= arr.length <= 500
     * -10^3 <= arr[i] <= 10^3
     *
     * @param arr
     * @return
     */
    public boolean checkIfExist(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return false;
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (binarySearchcheckIfExist(arr, arr[i] * 2, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean binarySearchcheckIfExist(int[] arr, int target, int index) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target && mid != index) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
    //endregion

    //region 1351. 统计有序矩阵中的负数 20230222

    /**
     * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。 请你统计并返回 grid 中 负数 的数目。
     * <p>
     * 示例 1：
     * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
     * 输出：8
     * 解释：矩阵中共有 8 个负数。
     * 示例 2：
     * 输入：grid = [[3,2],[1,0]]
     * 输出：0
     * <p>
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 100
     * -100 <= grid[i][j] <= 100
     * <p>
     * 进阶：你可以设计一个时间复杂度为 O(n + m) 的解决方案吗？
     *
     * @param grid
     * @return
     */
    public int countNegatives(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][grid[i].length - 1] >= 0) {
                continue;
            }
            int left = 0, right = grid[i].length - 1, mid = 0;
            while (left < right) {
                mid = left + (right - left) / 2;
                if (grid[i][mid] >= 0) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            int index = grid[i][mid] < 0 ? mid : mid + 1;
            res = res + grid[i].length - index;
        }
        return res;
    }
    //endregion

    //region 1385. 两个数组间的距离值  20230219

    /**
     * 给你两个整数数组 arr1 ， arr2 和一个整数 d ，请你返回两个数组之间的 距离值 。
     * 「距离值」 定义为符合此距离要求的元素数目：对于元素 arr1[i] ，不存在任何元素 arr2[j] 满足 |arr1[i]-arr2[j]| <= d 。
     * <p>
     * 示例 1：
     * 输入：arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
     * 输出：2
     * 解释：
     * 对于 arr1[0]=4 我们有：
     * |4-10|=6 > d=2
     * |4-9|=5 > d=2
     * |4-1|=3 > d=2
     * |4-8|=4 > d=2
     * 所以 arr1[0]=4 符合距离要求
     * 对于 arr1[1]=5 我们有：
     * |5-10|=5 > d=2
     * |5-9|=4 > d=2
     * |5-1|=4 > d=2
     * |5-8|=3 > d=2
     * 所以 arr1[1]=5 也符合距离要求
     * 对于 arr1[2]=8 我们有：
     * |8-10|=2 <= d=2
     * |8-9|=1 <= d=2
     * |8-1|=7 > d=2
     * |8-8|=0 <= d=2
     * 存在距离小于等于 2 的情况，不符合距离要求
     * 故而只有 arr1[0]=4 和 arr1[1]=5 两个符合距离要求，距离值为 2
     * 示例 2：
     * 输入：arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
     * 输出：2
     * 示例 3：
     * 输入：arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
     * 输出：1
     * <p>
     * 提示：
     * 1 <= arr1.length, arr2.length <= 500
     * -10^3 <= arr1[i], arr2[j] <= 10^3
     * 0 <= d <= 100
     *
     * @param arr1
     * @param arr2
     * @param d
     * @return
     */
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int result = arr1.length;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    result -= 1;
                    break;
                }
            }
        }
        return result;
    }

    //endregion

    //region 1539. 第 k 个缺失的正整数  20230221

    /**
     * 给你一个 严格升序排列 的正整数数组 arr 和一个整数 k 。
     * 请你找到这个数组里第 k 个缺失的正整数。
     * <p>
     * 示例 1：
     * 输入：arr = [2,3,4,7,11], k = 5
     * 输出：9
     * 解释：缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9 。
     * 示例 2：
     * 输入：arr = [1,2,3,4], k = 2
     * 输出：6
     * 解释：缺失的正整数包括 [5,6,7,...] 。第 2 个缺失的正整数为 6 。
     * <p>
     * 提示：
     * 1 <= arr.length <= 1000
     * 1 <= arr[i] <= 1000
     * 1 <= k <= 1000
     * 对于所有 1 <= i < j <= arr.length 的 i 和 j 满足 arr[i] < arr[j]
     *
     * @param arr
     * @param k
     * @return
     */

    public int findKthPositive(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return k;
        }
        int left = 0, right = arr.length - 1, mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] - mid - 1 >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return k + right + 1;
    }
    //endregion

    //region 1608. 特殊数组的特征值 20230221

    /**
     * 给你一个非负整数数组 nums 。如果存在一个数 x ，使得 nums 中恰好有 x 个元素 大于或者等于 x ，
     * 那么就称 nums 是一个 特殊数组 ，而 x 是该数组的 特征值 。
     * 注意： x 不必 是 nums 的中的元素。
     * 如果数组 nums 是一个 特殊数组 ，请返回它的特征值 x 。否则，返回 -1 。
     * 可以证明的是，如果 nums 是特殊数组，那么其特征值 x 是 唯一的 。
     * <p>
     * 示例 1：
     * 输入：nums = [3,5]
     * 输出：2
     * 解释：有 2 个元素（3 和 5）大于或等于 2 。
     * 示例 2：
     * 输入：nums = [0,0]
     * 输出：-1
     * 解释：没有满足题目要求的特殊数组，故而也不存在特征值 x 。
     * 如果 x = 0，应该有 0 个元素 >= x，但实际有 2 个。
     * 如果 x = 1，应该有 1 个元素 >= x，但实际有 0 个。
     * 如果 x = 2，应该有 2 个元素 >= x，但实际有 0 个。
     * x 不能取更大的值，因为 nums 中只有两个元素。
     * 示例 3：
     * 输入：nums = [0,4,3,0,4]
     * 输出：3
     * 解释：有 3 个元素大于或等于 3 。
     * 示例 4：
     * 输入：nums = [3,6,7,7,0]
     * 输出：-1
     * <p>
     * 提示：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     *
     * @param nums
     * @return
     */
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[0] >= n) {
            return n;
        }
        int l = 1, r = n - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[n - mid] >= mid && nums[n - mid - 1] < mid) {
                return mid;
            } else if (nums[n - mid] < mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
            if (l >= r - 1) {
                if (nums[n - l] >= l && nums[n - l - 1] < l) {
                    return l;
                }
                if (nums[n - r] >= r && nums[n - r - 1] < r) {
                    return r;
                }
                break;
            }
        }
        return -1;
    }

    public int specialArray1(int[] nums) {
        //暴力求解
        for (int i = 1; i <= nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] >= i) {
                    count++;
                }
            }
            if (count == i) {
                return i;
            }
        }
        return -1;
    }

    public int specialArray2(int[] nums) {
        //排序后暴力
        //降序排序
        Arrays.sort(nums);
        if (nums[0] >= nums.length) {
            return nums.length;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[nums.length - i] >= i && nums[nums.length - i - 1] < i) {
                return i;
            }
        }
        return -1;
    }

    public int specialArray3(int[] nums) {
        //降序排序 + 一次遍历
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        for (int i = 1; i <= n; ++i) {
            if (nums[i - 1] >= i && (i == n || nums[i] < i)) {
                return i;
            }
        }
        return -1;
    }
    //endregion

    //region 1855. 下标对中的最大距离 20230223

    /**
     * 给你两个 非递增 的整数数组 nums1 和 nums2 ，数组下标均 从 0 开始 计数。
     * 下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。
     * 如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，
     * 该下标对的 距离 为 j - i 。
     * 返回所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0 。
     * 一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，
     * 那么该数组是一个 非递增 数组。
     * <p>
     * 示例 1：
     * 输入：nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
     * 输出：2
     * 解释：有效下标对是 (0,0), (2,2), (2,3), (2,4), (3,3), (3,4) 和 (4,4) 。
     * 最大距离是 2 ，对应下标对 (2,4) 。
     * 示例 2：
     * 输入：nums1 = [2,2,2], nums2 = [10,10,1]
     * 输出：1
     * 解释：有效下标对是 (0,0), (0,1) 和 (1,1) 。
     * 最大距离是 1 ，对应下标对 (0,1) 。
     * 示例 3：
     * 输入：nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
     * 输出：2
     * 解释：有效下标对是 (2,2), (2,3), (2,4), (3,3) 和 (3,4) 。
     * 最大距离是 2 ，对应下标对 (2,4) 。
     * <p>
     * <p>
     * 提示：
     * 1 <= nums1.length <= 10^5
     * 1 <= nums2.length <= 10^5
     * 1 <= nums1[i], nums2[j] <= 10^5
     * nums1 和 nums2 都是 非递增 数组
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxDistance(int[] nums1, int[] nums2) {
        int res = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (i > 0 && nums1[i] == nums1[i - 1]) {
                continue;
            }
            int left = i, right = nums2.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums2[mid] < nums1[i]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                    res = Math.max(res, mid - i);
                }
            }
        }
        return res;
    }
    //endregion

    public static void main(String[] args) {
        //int arr1[] = new int[]{4, 5, 8};
        //int arr2[] = new int[]{10, 9, 1, 8};
        //int res = (new TargetBinarySearch()).findTheDistanceValue(arr1, arr2, 2);
        //int res = (new TargetBinarySearch().mySqrt(2147395599));
        //new TargetBinarySearch().searchRange(new int[]{1}, 1);
        //new TargetBinarySearch().arrangeCoins(5);
        int[][] res = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        new TargetBinarySearch().searchMatrix(new int[][]{{1}}, 1);
    }
}
