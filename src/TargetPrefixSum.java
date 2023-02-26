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
     * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
     * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
     * <p>
     * 示例 1：
     * 输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
     * 输出：2
     * 解释：总和小于或等于 4 的正方形的最大边长为 2，如图所示。
     * 示例 2：
     * 输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
     * 输出：0
     * <p>
     * 提示：
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 300
     * 0 <= mat[i][j] <= 10^4
     * 0 <= threshold <= 10^5
     *
     * @param mat
     * @param threshold
     * @return
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

}
