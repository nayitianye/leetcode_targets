package LeetBook;

/**
 * @author yyb
 * leetcode_leetbook_binarySearch
 * leetbook 二分查找
 * @url https://leetcode.cn/leetbook/detail/binary-search/
 */
public class TargetBookBinarySearch {

    //region    20230328    二分查找
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
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

    //region    20230328    数的平方根
    public int mySqrt(int x) {
        int left = 0, right = x / 2;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid * mid <= x) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    //endregion
}
