import java.util.*;
/**
 * @author yyb
 * leetcode_tag_array
 * leetcode 标签 数组
 */
public class TargetArray {

    //region 1. 两数之和
    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，
     * 并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。
     * 但是，你不能重复利用这个数组中同样的元素。
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     * @param nums
     * @param target
     * @return
     */
    private int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            int complement=target-nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement),i};
            }
            map.put(nums[i],i);
        }
        return new int[2];
    }
    //endregion

    //region 15. 三数之和
    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     * @param nums
     * @return
     */
    private List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        int len=nums.length;
        if(nums==null||len<3){
            return ans;
        }
        Arrays.sort(nums);
        if(nums[len-1]<0){
            return ans;
        }
        for(int i=0;i<len;i++){
            if(nums[i]>0){
                break;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int L=i+1;
            int R=len-1;
            while(L<R){
                int sum=nums[i]+nums[L]+nums[R];
                if(sum==0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while(L<R&&nums[L]==nums[L+1]){
                        L++;
                    }
                    while(L<R&&nums[R]==nums[R-1]){
                        R--;
                    }
                    L++;
                    R--;
                }else if(sum<0){
                    L++;
                }else if(sum>0){
                    R--;
                }
            }
        }
        return ans;
    }
    //endregion

    //region 53. 最大子序和
    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * @param nums
     * @return
     */
    private int maxSubArray(int[] nums) {
        int max=nums[0];
        for(int i=1;i<nums.length;i++){
            int flag=nums[i]+nums[i-1];
            if(flag>nums[i]){
                nums[i]=flag;
            }
            if(nums[i]>max){
                max=nums[i];
            }
        }
        return max;
    }
    //endregion

    //region 561. 数组拆分 I
    /**
     * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，
     * 使得从1 到 n 的 min(ai, bi) 总和最大。
     *
     * 示例 1:
     * 输入: [1,4,3,2]
     * 输出: 4
     * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
     *
     * 提示:
     * n 是正整数,范围在 [1, 10000].
     * 数组中的元素范围在 [-10000, 10000].
     * @param nums
     * @return
     */
    private int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            i++;
        }
        return sum;
    }
    //endregion

    //region 832. 翻转图像
    /**
     * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
     * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
     * 反转图片的意思是图片中的0全部被1替换，1全部被0替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
     *
     * 示例 1:
     * 输入: [[1,1,0],[1,0,1],[0,0,0]]
     * 输出: [[1,0,0],[0,1,0],[1,1,1]]
     * 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
     *
     * 示例 2:
     * 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
     * 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 说明:
     * 1 <= A.length = A[0].length <= 20
     * 0 <= A[i][j] <= 1
     *
     * @param A
     * @return
     */
    private static int[][] flipAndInvertImage(int[][] A) {
        for(int i=0;i<A.length;i++){
            for(int j=0;j<(A[i].length+1)/2;j++){
                if(A[i][j]!=A[i][A[i].length-1-j]){
                    continue;
                }else{
                    if(A[i][j]==1){
                        if(j==A[i].length-1-j){
                            A[i][j]=0;
                        }else{
                            A[i][j]=0;
                            A[i][A[i].length-1-j]=0;
                        }
                    }else{
                        if(j==A[i].length-1-j){
                            A[i][j]=1;
                        }else{
                            A[i][j]=1;
                            A[i][A[i].length-1-j]=1;
                        }
                    }
                }
            }
        }
        return A;
    }
    //endregion

    //region 867. 转置矩阵
    /**
     * 给定一个矩阵 A， 返回 A 的转置矩阵。
     * 矩阵的转置是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
     *
     * 示例 1：
     * 输入：[[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[1,4,7],[2,5,8],[3,6,9]]
     *
     * 示例 2：
     * 输入：[[1,2,3],[4,5,6]]
     * 输出：[[1,4],[2,5],[3,6]]
     *  
     * 提示：
     * 1 <= A.length <= 1000
     * 1 <= A[0].length <= 1000
     *
     * @param A
     * @return
     */
    private int[][] transpose(int[][] A) {
        int[][] res=new int[A[0].length][A.length];
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                res[j][i]=A[i][j];
            }
        }
        return res;
    }
    //endregion

    //region 905. 按奇偶排序数组
    /**
     * 给定一个非负整数数组 A，返回一个数组，在该数组中，A 的所有偶数元素之后跟着所有奇数元素。
     * 你可以返回满足此条件的任何数组作为答案。
     *
     * 示例：
     * 输入：[3,1,2,4]
     * 输出：[2,4,3,1]
     * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
     *  
     * 提示：
     * 1 <= A.length <= 5000
     * 0 <= A[i] <= 5000
     * @param A
     * @return
     */
    private int[] sortArrayByParity(int[] A) {
        int left=0;
        int right=A.length-1;
        while(left<right){
            while(A[left]%2==0 && left<right){
                left++;
            }
            while(A[right]%2==1&& left<right){
                right--;
            }
            int flag=A[left];
            A[left]=A[right];
            A[right]=flag;
        }
        return A;
    }
    //endregion

    //region 977. 有序数组的平方
    /**
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     *
     * 示例 1：
     * 输入：[-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     *
     * 示例 2：
     * 输入：[-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     * 提示：
     *
     * 1 <= A.length <= 10000
     * -10000 <= A[i] <= 10000
     * A 已按非递减顺序排序。
     *
     * @param A
     * @return
     */
    private int[] sortedSquares(int[] A) {
        for(int i=0;i<A.length;i++){
            A[i]=A[i]*A[i];
        }
        Arrays.sort(A);
        return A;
    }
    //endregion

    //region 1051. 高度检查器
    /**
     * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
     * 请你返回至少有多少个学生没有站在正确位置数量。
     * 该人数指的是：能让所有学生以 非递减 高度排列的必要移动人数。
     *
     * 示例：
     * 输入：[1,1,4,2,1,3]
     * 输出：3
     * 解释：
     * 高度为 4、3 和最后一个 1 的学生，没有站在正确的位置。
     *
     * 提示：
     * 1 <= heights.length <= 100
     * 1 <= heights[i] <= 100
     *
     * @param heights
     * @return
     */
    private int heightChecker(int[] heights) {
        int[] newheights=Arrays.copyOf(heights,heights.length);
        Arrays.sort(newheights);
        int flag=0;
        for(int i=0;i<heights.length;i++){
            if(newheights[i]!=heights[i]){
                flag++;
            }
        }
        return flag;
    }
    //endregion

    //region 1064. 不动点
    /**
     * 给定已经按升序排列、由不同整数组成的数组 A，返回满足 A[i] == i 的最小索引 i。
     * 如果不存在这样的 i，返回 -1。
     *
     * 示例 1：
     * 输入：[-10,-5,0,3,7]
     * 输出：3
     * 解释：
     * 对于给定的数组，A[0] = -10，A[1] = -5，A[2] = 0，A[3] = 3，因此输出为 3 。
     *
     * 示例 2：
     * 输入：[0,2,5,8,17]
     * 输出：0
     * 示例：
     * A[0] = 0，因此输出为 0 。
     *
     * 示例 3：
     * 输入：[-10,-5,3,4,7,9]
     * 输出：-1
     * 解释：
     * 不存在这样的 i 满足 A[i] = i，因此输出为 -1 。
     *  
     * 提示：
     * 1 <= A.length < 10^4
     * -10^9 <= A[i] <= 10^9
     * @param A
     * @return
     */
    private int fixedPoint(int[] A) {
        for(int i=0;i<A.length;i++){
            if(A[i]==i){
                return i;
            }
        }
        return -1;
    }
    //endregion

    //region 1085. 最小元素各数位之和
    /**
     * 给你一个正整数的数组 A。
     * 然后计算 S，使其等于数组 A 当中最小的那个元素各个数位上数字之和。
     * 最后，假如 S 所得计算结果是 奇数 的请你返回 0，否则请返回 1。
     *
     * 示例 1:
     *
     * 输入：[34,23,1,24,75,33,54,8]
     * 输出：0
     * 解释：
     * 最小元素为 1，该元素各个数位上的数字之和 S = 1，是奇数所以答案为 0。
     *
     * 示例 2：
     * 输入：[99,77,33,66,55]
     * 输出：1
     * 解释：
     * 最小元素为 33，该元素各个数位上的数字之和 S = 3 + 3 = 6，是偶数所以答案为 1。
     *  
     * 提示：
     * 1 <= A.length <= 100
     * 1 <= A[i].length <= 100
     *
     * @param A
     * @return
     */
    private int sumOfDigits(int[] A) {
        int min=A[0];
        for(int i=1;i<A.length;i++){
            if(A[i]<min){
                min=A[i];
            }
        }
        int sum=0;
        while(min>0){
            sum+=min%10;
            min=min/10;
        }
        return sum%2==0?1:0;
    }
    //endregion


    public static void main(String[] args) {
        int[][] A={{1,1,0},{1,0,1},{0,0,0}};
        System.out.println(flipAndInvertImage(A));
    }
}
