package StudyPlan;

import java.util.Arrays;

/**
 * @author yyb
 * leetcode_studyplan_LeetCode 75
 * leetcode 学习计划 LeetCode 75
 */
public class TargetLeetCode75 {

    //region    20230305    724. 寻找数组的中心下标
    /**
     * 给你一个整数数组 nums ，请计算数组的 中心下标 。
     * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。
     * 这一点对于中心下标位于数组最右端同样适用。
     * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
     * <p>
     * 示例 1：
     * 输入：nums = [1, 7, 3, 6, 5, 6]
     * 输出：3
     * 解释：
     * 中心下标是 3 。
     * 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
     * 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
     * 示例 2：
     * 输入：nums = [1, 2, 3]
     * 输出：-1
     * 解释：
     * 数组中不存在满足此条件的中心下标。
     * 示例 3：
     * 输入：nums = [2, 1, -1]
     * 输出：0
     * 解释：
     * 中心下标是 0 。
     * 左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
     * 右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。
     * <p>
     * 提示：
     * 1 <= nums.length <= 104
     * -1000 <= nums[i] <= 1000
     * 注意：本题与主站 1991 题相同：<a href="https://leetcode-cn.com/problems/find-the-middle-index-in-array/">...</a>
     *
     * @param nums 整数数组 nums
     * @return 计算数组的 中心下标
     */
    public int pivotIndex(int[] nums) {
        int[] pivot = new int[nums.length];
        pivot[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pivot[i] = pivot[i - 1] + nums[i];
        }
        int left = 0;
        for (int i = 0; i <pivot.length; i++) {
            if (i != 0) {
                left = pivot[i - 1];
            }
            if (pivot[nums.length - 1] - pivot[i] ==left) {
                return i;
            }
        }
        return -1;
    }
    //endregion

    //region    20230305    1480. 一维数组的动态和
    /**
     * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
     * 请返回 nums 的动态和。
     * <p>
     * 示例 1：
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,6,10]
     * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
     * 示例 2：
     * 输入：nums = [1,1,1,1,1]
     * 输出：[1,2,3,4,5]
     * 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
     * 示例 3：
     * 输入：nums = [3,1,2,10,1]
     * 输出：[3,4,6,16,17]
     * <p>
     * 提示：
     * 1 <= nums.length <= 1000
     * -10^6 <= nums[i] <= 10^6
     *
     * @param nums 数组 nums
     * @return 数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) . 请返回 nums 的动态和
     */
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 1) {
            return nums;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        return nums;
    }
    //endregion

    public static void main(String[] args) {
        int[] res = new TargetLeetCode75().runningSum(new int[]{1, 2, 3, 4});
        System.out.println(Arrays.toString(res));
        int pivotIndex = new TargetLeetCode75().pivotIndex(new int[]{1, 7, 3, 6, 5, 6});
        System.out.println(pivotIndex);
    }
}


