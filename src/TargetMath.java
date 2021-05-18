import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yyb
 * leetcode_tag_math
 * leetcode 标签 数学
 */
public class TargetMath {

    //region 66. 加一 2021/10/21
    /**
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * 示例 1：
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * 解释：输入数组表示数字 123。
     * 示例 2：
     * 输入：digits = [4,3,2,1]
     * 输出：[4,3,2,2]
     * 解释：输入数组表示数字 4321。
     * 示例 3：
     * 输入：digits = [0]
     * 输出：[1]
     *
     * 提示：
     * 1 <= digits.length <= 100
     * 0 <= digits[i] <= 9
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }
        // digits 中所有的元素均为 9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }
    //endregion

    //region 171. Excel表列序号
    /**
     * 给定一个Excel表格中的列名称，返回其相应的列序号。
     * 例如，
     *     A -> 1
     *     B -> 2
     *     C -> 3
     *     ...
     *     Z -> 26
     *     AA -> 27
     *     AB -> 28
     *     ...
     *
     * 示例 1:
     * 输入: "A"
     * 输出: 1
     *
     * 示例 2:
     * 输入: "AB"
     * 输出: 28
     *
     * 示例 3:
     * 输入: "ZY"
     * 输出: 701
     * @param s
     * @return
     */
    private int titleToNumber(String s) {
        int res=0;
        for(int i=0;i<s.length();i++){
            int flag=s.charAt(i)-'A'+1;
            res=res*26+flag;
        }
        return res;
    }
    //endregion

    //region 258. 各位相加
    /**
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     * 示例:
     * 输入: 38
     * 输出: 2
     * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
     * @param num
     * @return
     */
    private int addDigits(int num) {
        if(num!=0&&num%9==0) return 9;//若括号内为9，原本应该返回9，但经过模运算变为0，故要考虑此情况
        else return num%9;
    }
    /**
     * 时间复杂度O(1)
     * 解题思路：
     * 除个位外，每一位上的值都是通过(9+1)进位的过程得到的，想一下拨算盘进位
     * 把整数n看成n样物品，原本是以10个1份打包的，现在从这些10个1份打包好的里面，拿出1个，让它们以9个为1份打包。
     *
     * 这样就出现了两部分的东西：
     * 原本10个现在9个1份的，打包好的物品，这些，我们不用管
     * 零散的物品，它们还可以分成：
     * 从原来打包的里面拿出来的物品，它们的总和 =》 原来打包好的份数 =》
     * 10进制进位的次数 =》 10进制下，除个位外其他位上的值的总和
     * 以10个为1份打包时，打不进去的零散物品 =》 10进制个位上的值
     * 如上零散物品的总数，就是第一次处理num后得到的累加值
     * 如果这个累加值>9，那么如题就还需要将各个位上的值再相加，直到结果为个位数为止。
     * 也就意味着还需要来一遍如上的过程。
     *
     * 那么按照如上的思路，似乎可以通过n % 9得到最后的值
     * 但是有1个关键的问题，如果num是9的倍数，那么就不适用上述逻辑。
     * 原本我是想得到n被打包成10个1份的份数+打不进10个1份的散落个数的和。
     * 通过与9取模，去获得那个不能整除的1，作为计算份数的方式，但是如果可以被9整除，
     * 我就无法得到那个1，也得不到个位上的数。
     *
     * 所以需要做一下特殊处理，(num - 1) % 9 + 1
     * 可以这么做的原因：原本可以被完美分成9个为一份的n样物品，我故意去掉一个，
     * 那么就又可以回到上述逻辑中去得到我要的n被打包成10个一份的份数+打不进10个一份的散落个数的和。
     * 而这个减去的1就相当于从，在10个1份打包的时候散落的个数中借走的，本来就不影响原来10个1份打包的份数，
     * 先拿走再放回来，都只影响散落的个数，所以没有关系。
     * @param num
     * @return
     */
    private int addDigits1(int num) {
        return (num - 1) % 9 + 1;
    }
    //endregion

    //region 453.最小操作次数使数组元素相等 2021/10/21
    /**
     * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
     *
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：3
     * 解释：
     * 只需要3次操作（注意每次操作会增加两个元素的值）：
     * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
     * 示例 2：
     * 输入：nums = [1,1,1]
     * 输出：0
     *
     * 提示：
     * n == nums.length
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * 答案保证符合 32-bit 整数
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int minNum = Arrays.stream(nums).min().getAsInt();
        int res = 0;
        for (int num : nums) {
            res += num - minNum;
        }
        return res;
    }
    //endregion

    //region 728. 自除数
    /**
     * 自除数 是指可以被它包含的每一位数除尽的数。
     * 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，
     * 128 % 8 == 0。
     * 还有，自除数不允许包含 0 。
     * 给定上边界和下边界数字，输出一个列表，
     * 列表的元素是边界（含边界）内所有的自除数。
     *
     * 示例 1：
     * 输入：
     * 上边界left = 1, 下边界right = 22
     * 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
     * 注意：
     * 每个输入参数的边界满足 1 <= left <= right <= 10000。
     * @param left
     * @param right
     * @return
     */
    private List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list=new ArrayList<>();
        for(int i=left;i<=right;i++){
            if(isSelfDividNumber(i)){
                list.add(i);
            }
        }
        return list;
    }
    private boolean isSelfDividNumber(int num){
        int div=num;
        while(div>0){
            int flag=div%10;
            if(flag==0){
                return false;
            }
            if(num%flag!=0){
                return false;
            }
            div=div/10;
        }
        return true;
    }
    //endregion

    //region 877. 石子游戏   2019/10/22  动态规划
    /**
     * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
     * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
     * 亚历克斯和李轮流进行，亚历克斯先开始。
     * 每回合，玩家从行的开始或结束处取走整堆石头。
     * 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
     *
     * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
      
     *
     * 示例：
     * 输入：[5,3,4,5]
     * 输出：true
     * 解释：
     * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
     * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
     * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
     * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
     * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。 
     *
     * 提示：
     * 2 <= piles.length <= 500
     * piles.length 是偶数。
     * 1 <= piles[i] <= 500
     * sum(piles) 是奇数。
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int N = piles.length;

        // dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
        int[][] dp = new int[N+2][N+2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1)
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                else
                    dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
            }

        return dp[1][N] > 0;
    }
    //endregion

    //region 883. 三维形体投影面积
    /**
     * 在 N * N 的网格中，我们放置了一些与 x，y，z 三轴对齐的 1 * 1 * 1 立方体。
     * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
     * 现在，我们查看这些立方体在 xy、yz 和 zx 平面上的投影。
     * 投影就像影子，将三维形体映射到一个二维平面上。
     * 在这里，从顶部、前面和侧面看立方体时，我们会看到“影子”。
     * 返回所有三个投影的总面积。
     *
     * 示例 1：
     * 输入：[[2]]
     * 输出：5
     *
     * 示例 2：
     * 输入：[[1,2],[3,4]]
     * 输出：17
     * 解释：
     * 这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
     *
     * 示例 3：
     * 输入：[[1,0],[0,2]]
     * 输出：8
     *
     * 示例 4：
     * 输入：[[1,1,1],[1,0,1],[1,1,1]]
     * 输出：14
     *
     * 示例 5：
     * 输入：[[2,2,2],[2,1,2],[2,2,2]]
     * 输出：21
     *
     * @param grid
     * @return
     */
    private int projectionArea(int[][] grid) {
        int N = grid.length;
        int ans = 0;
        for (int i = 0; i < N;  ++i) {
            int bestRow = 0;  // largest of grid[i][j]
            int bestCol = 0;  // largest of grid[j][i]
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] > 0) ans++;  // top shadow
                bestRow = Math.max(bestRow, grid[i][j]);
                bestCol = Math.max(bestCol, grid[j][i]);
            }
            ans += bestRow + bestCol;
        }
        return ans;
    }
    //endregion

    //region 908. 最小差值 I
    /**
     * 给定一个整数数组 A，对于每个整数 A[i]，我们可以选择任意 x 满足 -K <= x <= K，并将 x 加到 A[i] 中。
     * 在此过程之后，我们得到一些数组 B。
     * 返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
     *
     * 示例 1：
     * 输入：A = [1], K = 0
     * 输出：0
     * 解释：B = [1]
     *
     * 示例 2：
     * 输入：A = [0,10], K = 2
     * 输出：6
     * 解释：B = [2,8]
     *
     * 示例 3：
     * 输入：A = [1,3,6], K = 3
     * 输出：0
     * 解释：B = [3,3,3] 或 B = [4,4,4]
     *  
     * 提示：
     * 1 <= A.length <= 10000
     * 0 <= A[i] <= 10000
     * 0 <= K <= 10000
     *
     * @param A
     * @param K
     * @return
     */
    private int smallestRangeI(int[] A, int K) {
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<A.length;i++){
            if(A[i]>max){
                max=A[i];
            }
            if(A[i]<min){
                min=A[i];
            }
        }
        int sum=max-min;
        return sum>=2*K?sum-2*K:0;
    }
    //endregion

    //region 942. 增减字符串匹配
    /**
     * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，
     * 令 N = S.length。
     * 返回 [0, 1, ..., N] 的任意排列 A 
     * 使得对于所有 i = 0, ..., N-1，都有：
     * 如果 S[i] == "I"，那么 A[i] < A[i+1]
     * 如果 S[i] == "D"，那么 A[i] > A[i+1]
     *  
     * 示例 1：
     * 输出："IDID"
     * 输出：[0,4,1,3,2]
     *
     * 示例 2：
     * 输出："III"
     * 输出：[0,1,2,3]
     *
     * 示例 3：
     * 输出："DDI"
     * 输出：[3,2,0,1]
     *  
     * 提示：
     * 1 <= S.length <= 1000
     * S 只包含字符 "I" 或 "D"。
     *
     * @param S
     * @return
     */
    private int[] diStringMatch(String S) {
        int left=S.length();
        int right=0;
        int[] res=new int[left+1];
        for(int i=0;i<S.length();i++){
            if(S.charAt(i)=='I'){
                res[i]=right;
                right++;
            }else{
                res[i]=left;
                left--;
            }
        }
        res[S.length()]=right;
        return res;
    }
    //endregion

    //region 1025. 除数博弈
    /**
     * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
     * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
     * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
     * 用 N - x 替换黑板上的数字 N 。
     * 如果玩家无法执行这些操作，就会输掉游戏。
     *
     * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。
     * 假设两个玩家都以最佳状态参与游戏。
     *
     * 示例 1：
     * 输入：2
     * 输出：true
     * 解释：爱丽丝选择 1，鲍勃无法进行操作。
     * 示例 2：
     *
     * 输入：3
     * 输出：false
     * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
     *  
     * 提示：
     * 1 <= N <= 1000
     * @param N
     * @return
     */
    private boolean divisorGame(int N) {
        return N%2==0;
    }

    /**
     * 动态规划求解
     * @param N
     * @return
     */
    private boolean divisorGame2(int N) {
        boolean dp[]=new boolean[N+1];
        dp[1]=false;
        dp[0]=true;
        for(int i=2;i<=N;i++){
            dp[i]=false;
            for(int j=1;j<=i;j++){
                if(i%j==0 &&dp[i-j]==false){
                    dp[i]=true;
                    break;
                }
            }
        }
        return  dp[N];
    }
    //endregion

    //region 1134. 阿姆斯特朗数
    /**
     * 假设存在一个 k 位数 N，其每一位上的数字的 k 次幂的总和也是 N，
     * 那么这个数是阿姆斯特朗数。
     * 给你一个正整数 N，让你来判定他是否是阿姆斯特朗数，是则返回 true，
     * 不是则返回 false。
     *
     * 示例 1：
     * 输入：153
     * 输出：true
     * 示例：
     * 153 是一个 3 位数，且 153 = 1^3 + 5^3 + 3^3。
     *
     * 示例 2：
     * 输入：123
     * 输出：false
     * 解释：
     * 123 是一个 3 位数，且 123 != 1^3 + 2^3 + 3^3 = 36。
     * 提示：
     * 1 <= N <= 10^8
     * @param N
     * @return
     */
    private boolean isArmstrong(int N) {
        int len=(N+"").length();
        int m=N;
        int res=0;
        int flag=0;
        while(m>0){
            flag=m%10;
            res+=getPow(flag,len);
            m=m/10;
        }
        return res==N;
    }
    //endregion

    //region 1180. 统计只含单一字母的子串
    /**
     * 给你一个字符串 S，返回只含 单一字母 的子串个数。
     *
     * 示例 1：
     * 输入： "aaaba"
     * 输出： 8
     * 解释：
     * 只含单一字母的子串分别是 "aaa"， "aa"， "a"， "b"。
     * "aaa" 出现 1 次。
     * "aa" 出现 2 次。
     * "a" 出现 4 次。
     * "b" 出现 1 次。
     * 所以答案是 1 + 2 + 4 + 1 = 8。
     *
     * 示例 2:
     * 输入： "aaaaaaaaaa"
     * 输出： 55
     *  
     * 提示：
     * 1 <= S.length <= 1000
     * S[i] 仅由小写英文字母组成。
     *
     * @param S
     * @return
     */
    private static int countLetters(String S) {
        int sum=0;
        int left=0;
        for(int i=1;i<S.length();i++){
            if(S.charAt(i)==S.charAt(i-1)){
                continue;
            }else{
                int len=i-left;
                sum=sum+len*(len+1)/2;
                left=i;
            }
        }
        int last=S.length()-left;
        sum=sum+last*(last+1)/2;
        return sum;
    }
    //endregion

    private int getPow(int a,int b){
        int res=1;
        for(int i=0;i<b;i++){
            res=res*a;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countLetters("jjjjxttttn"));
    }

}
