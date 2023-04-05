package StudyPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yyb
 * leetcode_studyplan_data_structures_Basic
 * leetcode 学习计划 数据结构基础
 */
public class TargetDataStructuresBasic {

    //region    20230405    15. 三数之和

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

    //region    20230405    136. 只出现一次的数字

    /**
     * https://leetcode.cn/problems/single-number/
     *
     * @param nums 非空整数数组 nums
     * @return 找出那个只出现了一次的元素
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
    //endregion

    //region    20230405    169. 多数元素

    /**
     * https://leetcode.cn/problems/majority-element/
     *
     * @param nums 大小为 n 的数组 nums
     * @return 返回其中的多数元素
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
    //endregion

}
