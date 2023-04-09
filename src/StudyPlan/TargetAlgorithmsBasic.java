package StudyPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yyb
 * leetcode_studyplan_algorithms_Basic
 * leetcode 学习计划 算法基础
 */
public class TargetAlgorithmsBasic {

    //region    20230408    15. 三数之和

    /**
     * https://leetcode.cn/problems/3sum/
     *
     * @param nums 整数数组 nums
     * @return 返回所有和为 0 且不重复的三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        if (nums[nums.length - 1] < 0) {
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
    //endregion

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

    //region    20230408    153. 寻找旋转排序数组中的最小值

    /**
     * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array
     * @param nums  元素值 互不相同 的数组 nums
     * @return  找出并返回数组中的 最小元素
     */
    public int findMin(int[] nums) {
        if(nums==null||nums.length<1){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int left=0,right=nums.length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]<nums[right]){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return nums[left];
    }
    //endregion

    //region    20230410    200. 岛屿数量

    /**
     * https://leetcode.cn/problems/number-of-islands/
     * @param grid 一个由 '1'（陆地）和 '0'（水）组成的的二维网格 grid
     * @return  计算网格中岛屿的数量
     */
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j ++) {
                if (grid[i][j] == '1') {
                    dfsGrid(grid, i, j);
                    res ++;
                }
            }
        }
        return res;
    }

    private void dfsGrid(char[][] grid, int row, int col) {
        if (row >= grid.length || col >= grid[0].length || row < 0 || col < 0) {
            return;
        }

        if (grid[row][col] != '1') {
            return;
        }

        grid[row][col] = '2';
        dfsGrid(grid, row - 1, col);
        dfsGrid(grid, row + 1, col);
        dfsGrid(grid, row, col - 1);
        dfsGrid(grid, row, col + 1);
    }
    //endregion

    //region    20230410    209. 长度最小的子数组

    /**
     * https://leetcode.cn/problems/minimum-size-subarray-sum
     * @param target 一个正整数 target
     * @param nums  含有 n 个正整数的数组 nums
     * @return  找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0
     */
    public int minSubArrayLen(int target, int[] nums) {
        int[] presum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        if (presum[nums.length] < target) {
            return 0;
        }
        int minLength = Integer.MAX_VALUE;
        for (int i = 1; i < presum.length; i++) {
            int temptarget = target + presum[i-1];
            int bound = Arrays.binarySearch(presum, temptarget);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound < presum.length) {
                minLength = Math.min(minLength, bound - (i - 1));
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    //endregion
}
