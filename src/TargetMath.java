import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yyb
 * leetcode_tag_math
 * leetcode 标签 数学
 */
public class TargetMath {

    //region    20211021    66. 加一

    /**
     * https://leetcode.cn/problems/plus-one/
     *
     * @param digits 由整数组成的非空数组 digits
     * @return 一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一
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

    //region    20230226    96. 不同的二叉搜索树

    /**
     * https://leetcode.cn/problems/unique-binary-search-trees/
     *
     * @param n 整数 n
     * @return 返回满足题意的二叉搜索树的种数
     */
    public int numTrees(int n) {
        //卡塔兰数  C0=1 Cn+1=2*(2*n+1)/(n+2)*Cn
        // 提示：我们在这里需要用 long 类型防止计算过程中的溢出
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }
    //endregion

    //region    20190922    171. Excel表列序号

    /**
     * https://leetcode.cn/problems/excel-sheet-column-number/
     *
     * @param columnTitle 一个字符串 columnTitle ，表示 Excel 表格中的列名称
     * @return 返回该列名称对应的列序号
     */
    private int titleToNumber(String columnTitle) {
        int res = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            int flag = columnTitle.charAt(i) - 'A' + 1;
            res = res * 26 + flag;
        }
        return res;
    }
    //endregion

    //region    20190922    258. 各位相加

    /**
     * https://leetcode.cn/problems/add-digits/
     *
     * @param num 一个非负整数 num
     * @return 反复将各个位上的数字相加，直到结果为一位数。返回这个结果
     */
    private int addDigits(int num) {
        //若括号内为9，原本应该返回9，但经过模运算变为0，故要考虑此情况
        if (num != 0 && num % 9 == 0) {
            return 9;
        } else {
            return num % 9;
        }
    }

    /**
     * 时间复杂度O(1)
     * 解题思路：
     * 除个位外，每一位上的值都是通过(9+1)进位的过程得到的，想一下拨算盘进位
     * 把整数n看成n样物品，原本是以10个1份打包的，现在从这些10个1份打包好的里面，拿出1个，让它们以9个为1份打包。
     * <p>
     * 这样就出现了两部分的东西：
     * 原本10个现在9个1份的，打包好的物品，这些，我们不用管
     * 零散的物品，它们还可以分成：
     * 从原来打包的里面拿出来的物品，它们的总和 =》 原来打包好的份数 =》
     * 10进制进位的次数 =》 10进制下，除个位外其他位上的值的总和
     * 以10个为1份打包时，打不进去的零散物品 =》 10进制个位上的值
     * 如上零散物品的总数，就是第一次处理num后得到的累加值
     * 如果这个累加值>9，那么如题就还需要将各个位上的值再相加，直到结果为个位数为止。
     * 也就意味着还需要来一遍如上的过程。
     * <p>
     * 那么按照如上的思路，似乎可以通过n % 9得到最后的值
     * 但是有1个关键的问题，如果num是9的倍数，那么就不适用上述逻辑。
     * 原本我是想得到n被打包成10个1份的份数+打不进10个1份的散落个数的和。
     * 通过与9取模，去获得那个不能整除的1，作为计算份数的方式，但是如果可以被9整除，
     * 我就无法得到那个1，也得不到个位上的数。
     * <p>
     * 所以需要做一下特殊处理，(num - 1) % 9 + 1
     * 可以这么做的原因：原本可以被完美分成9个为一份的n样物品，我故意去掉一个，
     * 那么就又可以回到上述逻辑中去得到我要的n被打包成10个一份的份数+打不进10个一份的散落个数的和。
     * 而这个减去的1就相当于从，在10个1份打包的时候散落的个数中借走的，本来就不影响原来10个1份打包的份数，
     * 先拿走再放回来，都只影响散落的个数，所以没有关系。
     *
     * @param num
     * @return
     */
    private int addDigits1(int num) {
        return (num - 1) % 9 + 1;
    }
    //endregion

    //region    20230226    263. 丑数

    /**
     * https://leetcode.cn/problems/ugly-number/
     *
     * @param n 一个整数 n
     * @return 判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false
     */
    public boolean isUgly(int n) {
        if (n < 1) {
            return false;
        }
        while (n > 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else if (n % 3 == 0) {
                n = n / 3;
            } else if (n % 5 == 0) {
                n = n / 5;
            } else {
                return false;
            }
        }
        return true;
    }
    //endregion

    //region    20211020    453.最小操作次数使数组元素相等

    /**
     * https://leetcode.cn/problems/minimum-moves-to-equal-array-elements/
     *
     * @param nums 长度为 n 的整数数组 nums
     * @return 返回让数组所有元素相等的最小操作次数
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

    //region    20190922    728. 自除数

    /**
     * https://leetcode.cn/problems/self-dividing-numbers/
     *
     * @param left  整数 left
     * @param right 整数 right
     * @return 返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数
     */
    private List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividNumber(i)) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean isSelfDividNumber(int num) {
        int div = num;
        while (div > 0) {
            int flag = div % 10;
            if (flag == 0) {
                return false;
            }
            if (num % flag != 0) {
                return false;
            }
            div = div / 10;
        }
        return true;
    }
    //endregion

    //region    20191022    877. 石子游戏

    /**
     * https://leetcode.cn/problems/stone-game/
     *
     * @param piles 一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i]
     * @return 当 Alice 赢得比赛时返回 true ，当 Bob 赢得比赛时返回 false
     */
    public boolean stoneGame(int[] piles) {
        int N = piles.length;

        // dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
        int[][] dp = new int[N + 2][N + 2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1)
                    dp[i + 1][j + 1] = Math.max(piles[i] + dp[i + 2][j + 1], piles[j] + dp[i + 1][j]);
                else
                    dp[i + 1][j + 1] = Math.min(-piles[i] + dp[i + 2][j + 1], -piles[j] + dp[i + 1][j]);
            }

        return dp[1][N] > 0;
    }
    //endregion

    //region    20190922    883. 三维形体投影面积

    /**
     * https://leetcode.cn/problems/projection-area-of-3d-shapes/
     *
     * @param grid 网格 grid
     * @return 返回所有三个投影的总面积
     */
    private int projectionArea(int[][] grid) {
        int N = grid.length;
        int ans = 0;
        for (int i = 0; i < N; ++i) {
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

    //region    20190922    908. 最小差值 I

    /**
     * https://leetcode.cn/problems/smallest-range-i/
     *
     * @param nums 整数数组 nums
     * @param K    整数 k
     * @return 返回 nums 的最低 分数
     */
    private int smallestRangeI(int[] nums, int K) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        int sum = max - min;
        return sum >= 2 * K ? sum - 2 * K : 0;
    }
    //endregion

    //region    20190926    942. 增减字符串匹配

    /**
     * https://leetcode.cn/problems/di-string-match/
     *
     * @param S 范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s
     * @return 给定一个字符串 s ，重构排列 perm 并返回它
     */
    private int[] diStringMatch(String S) {
        int left = S.length();
        int right = 0;
        int[] res = new int[left + 1];
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'I') {
                res[i] = right;
                right++;
            } else {
                res[i] = left;
                left--;
            }
        }
        res[S.length()] = right;
        return res;
    }
    //endregion

    //region    20190922    1025. 除数博弈

    /**
     * https://leetcode.cn/problems/divisor-game/
     *
     * @param N 一个数字 n
     * @return 在爱丽丝在游戏中取得胜利时才返回 true
     */
    private boolean divisorGame(int N) {
        return N % 2 == 0;
    }

    /**
     * 动态规划求解
     *
     * @param N 一个数字 n
     * @return 在爱丽丝在游戏中取得胜利时才返回 true
     */
    private boolean divisorGame2(int N) {
        boolean dp[] = new boolean[N + 1];
        dp[1] = false;
        dp[0] = true;
        for (int i = 2; i <= N; i++) {
            dp[i] = false;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0 && dp[i - j] == false) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N];
    }
    //endregion

    //region    20230411    1041. 困于环中的机器人

    /**
     * https://leetcode.cn/problems/robot-bounded-in-circle/
     *
     * @param instructions 执行指令 instructions
     * @return 只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 fals
     */
    public boolean isRobotBounded(String instructions) {
        int k = 0;
        int[] dist = new int[4];
        for (int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);
            if (c == 'L') {
                k = (k + 1) % 4;
            } else if (c == 'R') {
                k = (k + 3) % 4;
            } else {
                ++dist[k];
            }
        }
        return (dist[0] == dist[2] && dist[1] == dist[3]) || (k != 0);
    }
    //endregion

    //region    20190922    1134. 阿姆斯特朗数

    /**
     * https://leetcode.cn/problems/armstrong-number/
     *
     * @param N 一个整数 n
     * @return 判定他是否是 阿姆斯特朗数
     */
    private boolean isArmstrong(int N) {
        int len = (N + "").length();
        int m = N;
        int res = 0;
        int flag = 0;
        while (m > 0) {
            flag = m % 10;
            res += getPow(flag, len);
            m = m / 10;
        }
        return res == N;
    }

    private int getPow(int a, int b) {
        int res = 1;
        for (int i = 0; i < b; i++) {
            res = res * a;
        }
        return res;
    }
    //endregion

    //region    20190922    1180. 统计只含单一字母的子串

    /**
     * https://leetcode.cn/problems/count-substrings-with-only-one-distinct-letter/
     *
     * @param S 一个字符串 s
     * @return 返回只含单一字母的子串个数
     */
    private static int countLetters(String S) {
        int sum = 0;
        int left = 0;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == S.charAt(i - 1)) {
                continue;
            } else {
                int len = i - left;
                sum = sum + len * (len + 1) / 2;
                left = i;
            }
        }
        int last = S.length() - left;
        sum = sum + last * (last + 1) / 2;
        return sum;
    }
    //endregion

    //region    20230421    2413. 最小偶倍数

    /**
     * https://leetcode.cn/problems/smallest-even-multiple
     * @param n  一个正整数 n
     * @return  返回 2 和 n 的最小公倍数（正整数）
     */
    public int smallestEvenMultiple(int n) {
        if (n % 2 == 0) {
            return n;
        } else {
            return 2 * n;
        }
    }
    //endregion

    //region    20230405    2427. 公因子的数目

    /**
     * https://leetcode.cn/problems/number-of-common-factors/
     *
     * @param a 正整数 a
     * @param b 正整数 b
     * @return 返回 a 和 b 的 公 因子的数目
     */
    public int commonFactors(int a, int b) {
        int res = 0;
        for (int i = 1; i <= Math.min(a, b); i++) {
            if (a % i == 0 && b % i == 0) {
                res++;
            }
        }
        return res;
    }
    //endregion

    //region    20230321    2469. 温度转换

    /**
     * https://leetcode.cn/problems/convert-the-temperature/
     *
     * @param celsius 摄氏度（Celsius）为单位
     * @return 将摄氏度转换为 开氏度（Kelvin）和 华氏度（Fahrenheit），并以数组 ans = [kelvin, fahrenheit] 的形式返回结果
     */
    public double[] convertTemperature(double celsius) {
        return new double[]{celsius + 273.15, celsius * 1.8 + 32.00};
    }
    //endregion


    public static void main(String[] args) {
        System.out.println(countLetters("jjjjxttttn"));
    }

}
