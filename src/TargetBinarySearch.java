/**
 * @author yyb
 * leetcode_tag_binary_search
 * leetcode 标签 二分查找
 */
public class TargetBinarySearch {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //region 278.第一个错误版本 2021.10.20
    /**
     *你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     *
     * 示例 1：
     * 输入：n = 5, bad = 4
     * 输出：4
     * 解释：
     * 调用 isBadVersion(3) -> false
     * 调用 isBadVersion(5) -> true
     * 调用 isBadVersion(4) -> true
     * 所以，4 是第一个错误的版本。
     *
     * 示例 2：
     * 输入：n = 1, bad = 1
     * 输出：1
     *
     * 提示：
     * 1 <= bad <= n <= 2^31 - 1
     *
     */
    /*public class Solution extends VersionControl {
        public int firstBadVersion(int n) {

        }
    }*/
    //endregion

    //region 392. 判断子序列   2019/10/22
    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     *
     * 你可以认为 s 和 t 中仅包含英文小写字母。
     * 字符串 t 可能会很长（长度 ~= 500,000），
     * 而 s 是个短字符串（长度 <=100）。
     *
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）
     * 字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     *
     * 示例 1:
     * s = "abc", t = "ahbgdc"
     * 返回 true.
     * 示例 2:
     * s = "axc", t = "ahbgdc"
     * 返回 false.
     * 后续挑战 :
     * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
     * 在这种情况下，你会怎样改变代码？
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int index = 0,i = 0;
        while(index < s.length() && t.indexOf(s.charAt(index),i) >= i){
            i = t.indexOf(s.charAt(index),i) + 1;
            index++;
        }
        return index == s.length();
    }
    //endregion

    //region 704.二分查找 2021/10/20

    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     * 示例 1:
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * 示例 2:
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     *
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
        if(nums[0]>target ||nums[nums.length-1]<target){
            return -1;
        }
        return BinarySearch(nums,target,0,nums.length-1);
    }

    public int BinarySearch(int[] nums,int target,int start,int end){
        int index=(start+end)/2;
        if(start>end){
            return -1;
        }
        if(nums[index]==target){
            return index;
        }
        else if(nums[index]<target)
        {
            return BinarySearch(nums,target,index+1,end);
        }
        else
        {
            return BinarySearch(nums,target,start,index-1);
        }
    }
    //endregion

    //region 852. 山脉数组的峰顶索引   2019/10/22
    /**
     * 我们把符合下列属性的数组 A 称作山脉：
     * A.length >= 3
     * 存在 0 < i < A.length - 1
     * 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
     * 给定一个确定为山脉的数组，
     * 返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
     *
     * 示例 1：
     * 输入：[0,1,0]
     * 输出：1
     *
     * 示例 2：
     * 输入：[0,2,1,0]
     * 输出：1
     * 提示：
     * 3 <= A.length <= 10000
     * 0 <= A[i] <= 10^6
     * A 是如上定义的山脉
     * @param A
     * @return
     */
    public int peakIndexInMountainArray(int[] A) {
        int left=0;
        int right=A.length-1;
        while (A[left]<A[left+1])
        {
            left++;
        }
        while(A[right]<A[right-1]){
            right--;
        }
        return A[left]>=A[right]?left:right;
    }
    //endregion

    //region 1150. 检查一个数是否在数组中占绝大多数  2019/10/22   暴力+双指针
    /**
     * 给出一个按 非递减 顺序排列的数组 nums，和一个目标数值 target。
     * 假如数组 nums 中绝大多数元素的数值都等于 target，则返回 True，
     * 否则请返回 False。
     * 所谓占绝大多数，是指在长度为 N 的数组中出现必须 超过 N/2 次。
     * 示例 1：
     * 输入：nums = [2,4,5,5,5,5,5,6,6], target = 5
     * 输出：true
     *
     * 解释：
     * 数字 5 出现了 5 次，而数组的长度为 9。
     * 所以，5 在数组中占绝大多数，因为 5 次 > 9/2。
     *
     * 示例 2：
     * 输入：nums = [10,100,101,101], target = 101
     * 输出：false
     *
     * 解释：
     * 数字 101 出现了 2 次，而数组的长度是 4。
     * 所以，101 不是 数组占绝大多数的元素，因为 2 次 = 4/2。
     *  
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
        int flag=0;
        for(int num:nums){
            if (num==target){
                flag++;
            }
        }
        return flag>nums.length/2;
    }

    /**
     * 双指针求解
     * @param nums
     * @param target
     * @return
     */
    public boolean isMajorityElement1(int[] nums, int target) {
        int left=0,right=nums.length-1;
        if(nums.length==1){
            return nums[0]==target;
        }
        while(left<right){
            if(nums[left]<target){
                left++;
            }
            if(nums[left]>target){
                return false;
            }
            if(nums[right]>target){
                right--;
            }
            if(nums[right]<target){
                return false;
            }
            if(nums[left]==nums[right]&&nums[left]==target){
                break;
            }
        }
        return right-left+1>nums.length/2;
    }
    //endregion
}
