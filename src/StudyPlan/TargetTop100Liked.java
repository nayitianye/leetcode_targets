package StudyPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yyb
 * leetcode_studyplan_Top100Liked
 * leetcode 学习计划 LeetCode 热题 100
 */
public class TargetTop100Liked {

    //region    20230427    1. 两数之和

    /**
     * https://leetcode.cn/problems/two-sum/
     * @param nums  一个整数数组 nums
     * @param target  一个整数目标值 target
     * @return  找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标
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

    //region    20230428    49. 字母异位词分组

    /**
     * https://leetcode.cn/problems/group-anagrams
     * @param strs  字符串数组 strs
     * @return  按任意顺序返回结果列表
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res=new ArrayList<>();
        for (String str:strs) {

        }
        return res;
    }
    //endregion

}
