import java.util.HashMap;
import java.util.Map;

/**
 * @author yyb
 * leetcode_tag_prefix_sum
 * leetcode 标签 前缀和
 */
public class TargetPrefixSum {
    // 前缀和、差分、树状数组、块状数组
    // https://leetcode.cn/circle/article/P92uug/
    // 前缀和思想的理解与运用
    // https://leetcode.cn/circle/discuss/sv2auZ/
    // 树状数组从入门到下车
    // https://leetcode.cn/circle/discuss/qGREiN/
    // 一文讲懂一维前缀和、二维前缀和、二维子矩阵和...
    // https://leetcode.cn/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/solutions/1277018/yi-wen-jiang-dong-yi-wei-qian-zhui-he-er-hwm0/

    //region 1292. 元素和小于等于阈值的正方形的最大边长  20230226

    /**
     * https://leetcode.cn/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/
     *
     * @param mat       大小为 m x n 的矩阵 mat
     * @param threshold 一个整数阈值 threshold
     * @return 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0
     */
    public int maxSideLength(int[][] mat, int threshold) {
        // 前缀和+类似DP的思路
        // presum 可以理解为矩形的前缀和 presum[i + 1][j + 1] 表示的是 mat[0][0]-mat[i][j] 这个矩形的和
        int ans = 0, m = mat.length, n = mat[0].length;
        int[][] presum = new int[m + 1][n + 1];
        // 遍历每一个下标
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // 统计矩形的前缀和 减去 presum[i][j] 是避免重复统计
                presum[i + 1][j + 1] = presum[i][j + 1] + presum[i + 1][j] - presum[i][j] + mat[i][j];
                // 1.判断下标是否合法 2.若存在边长为 ans + 1 的正方形总和小于等于阈值 ans++ 继续找下一个更大的正方形
                if (i - ans >= 0 && j - ans >= 0 && presum[i + 1][j + 1] - presum[i - ans][j + 1] - presum[i + 1][j - ans] + presum[i - ans][j - ans] <= threshold) {
                    ans++;
                }
            }
        }
        return ans;
    }
    //endregion

    //region    20230311    1590. 使数组和能被 P 整除

    /**
     * https://leetcode.cn/problems/make-sum-divisible-by-p/
     *
     * @param nums 正整数数组 nums
     * @param p    整数 p
     * @return 移除最短子数组（可以为空），使得剩余元素的和能被 p 整除
     */
    public int minSubarray(int[] nums, int p) {
        int x = 0;
        //所有元素的和，与目标取模
        for (int num : nums) {
            x = (x + num) % p;
        }
        //如果所有元素的和与p整除，不需要删除子数组
        if (x == 0) {
            return 0;
        }
        //哈希表 key保存每个前缀的和与p的取模值，value表示是第几个元素的前缀
        //例如：value = 1 ， 表示nums[1]之前的所有元素之和 ， 子数组的长度就是 i - 1 + 1
        Map<Integer, Integer> index = new HashMap<Integer, Integer>();
        int y = 0, res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            index.put(y, i); // 存放 第i个元素的前缀和与p的取模值
            y = (y + nums[i]) % p; //第i个元素的前缀之和，与p取模
            // 如果第i个元素的前缀和与p的取模值 减去 所有元素之和与p的取模值 的差（+p是保持为正数，不影响操作数） % p 的值
            if (index.containsKey((y - x + p) % p)) {
                //根据 y % p = x 那么 (y - z) % p = 0 等价于 z % p = x 定理
                //(y - z) % p = x  等价于  z % p = (y - x) % p 定理
                //(y - x + p) 相当于 剩余数组的和

                //也就是说，剩余数组和%p = 某个前缀和%p ，根据定理可知
                //取出剩余数组 与 前缀数组之间的子数组，所得到的数组和就能够被p整除（注意：子数组是连续的）
                res = Math.min(res, i - index.get((y - x + p) % p) + 1); //获取最小值
            }
        }
        return res == nums.length ? -1 : res;
    }
    //endregion
}
