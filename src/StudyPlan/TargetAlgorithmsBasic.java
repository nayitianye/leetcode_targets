package StudyPlan;

/**
 * @author yyb
 * leetcode_studyplan_algorithms_Basic
 * leetcode 学习计划 算法基础
 */
public class TargetAlgorithmsBasic {

    //region    20230405    33. 搜索旋转排序数组

    /**
     * https://leetcode.cn/problems/search-in-rotated-sorted-array/
     * @param nums  整数数组 nums
     * @param target  整数 target
     * @return  nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1
     */
    public int search(int[] nums, int target) {
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

    //region    20230405    34. 在排序数组中查找元素的第一个和最后一个位置

    /**
     * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
     * @param nums  非递减顺序排列的整数数组 nums
     * @param target  目标值 target
     * @return  目标值在数组中的开始位置和结束位置
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

    //region    20230405    74. 搜索二维矩阵

    /**
     * https://leetcode.cn/problems/search-a-2d-matrix/
     * @param matrix  m x n 的 matrix 矩阵
     * @param target 目标值 target
     * @return  判断目标值是否在数组中
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int bottom = -1, top = matrix.length - 1, mid = 0;
        while (bottom < top) {
            mid = bottom + (top - bottom+1) / 2;
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
}
