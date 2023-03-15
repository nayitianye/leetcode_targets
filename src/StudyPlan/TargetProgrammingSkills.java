package StudyPlan;

import java.util.*;

/**
 * @author yyb
 * leetcode_studyplan_ProgrammingSkills
 * leetcode 学习计划 编程能力
 */
public class TargetProgrammingSkills {

    //region    20230315    191. 位1的个数

    /**
     * https://leetcode.cn/problems/number-of-1-bits/description/
     * @param n 输入是一个无符号整数（以二进制串的形式）
     * @return  返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）
     */
    public int hammingWeight(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }
    //endregion

    //region    20230315    202. 快乐数

    /**
     * https://leetcode.cn/problems/happy-number/description/
     *
     * @param n 正整数 n
     * @return 判断 n 是否是快乐数
     */
    public boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        while (n != 1 && !hashSet.contains(n)) {
            hashSet.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    public int getNext(int n) {
        int sum = 0;
        while (n != 0) {
            int d = n % 10;
            sum += d * d;
            n = n / 10;
        }
        return sum;
    }
    //endregion

    //region    20230315    1281. 整数的各位积和之差

    /**
     * https://leetcode.cn/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
     *
     * @param n 整数 n
     * @return 计算并返回该整数「各位数字之积」与「各位数字之和」的差
     */
    public int subtractProductAndSum(int n) {
        if (n == 0) {
            return n;
        }
        int sum = 0, multi = 1;
        while (n > 0) {
            sum+=n%10;
            multi*=n%10;
            n=n/10;
        }
        return multi-sum;
    }
    //endregion

    //region    20230312    1491. 去掉最低工资和最高工资后的工资平均值

    /**
     * https://leetcode.cn/problems/average-salary-excluding-the-minimum-and-maximum-salary/
     *
     * @param salary 整数数组 salary
     * @return 返回去掉最低工资和最高工资以后，剩下员工工资的平均值
     */
    public double average(int[] salary) {
        double min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < salary.length; i++) {
            min = Math.min(min, salary[i]);
            max = Math.max(max, salary[i]);
            sum += salary[i];
        }
        return (sum - min - max) / (salary.length - 2);
    }
    //endregion

    //region    20230315    1502. 判断能否形成等差数列

    /**
     * https://leetcode.cn/problems/can-make-arithmetic-progression-from-sequence/
     *
     * @param arr 数组arr
     * @return 判断数组中的值能否组成等差数列
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return true;
        }
        Arrays.sort(arr);
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != arr[i - 1] - arr[i - 2]) {
                return false;
            }
        }
        return true;
    }
    //endregion

    //region    20230315    1779. 找到最近的有相同 X 或 Y 坐标的点

    /**
     * https://leetcode.cn/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/solutions/
     *
     * @param x      整数 x
     * @param y      整数 y
     * @param points 一个数组 points ，其中 points[i] = [ai, bi] 表示在 (ai, bi) 处有一个点
     * @return 一个数组 points ，其中 points[i] = [ai, bi] 表示在 (ai, bi) 处有一个点
     */
    public int nearestValidPoint(int x, int y, int[][] points) {
        int n = points.length;
        int best = Integer.MAX_VALUE, bestid = -1;
        for (int i = 0; i < n; ++i) {
            int px = points[i][0], py = points[i][1];
            if (x == px) {
                int dist = Math.abs(y - py);
                if (dist < best) {
                    best = dist;
                    bestid = i;
                }
            } else if (y == py) {
                int dist = Math.abs(x - px);
                if (dist < best) {
                    best = dist;
                    bestid = i;
                }
            }
        }
        return bestid;
    }
    //endregion

    //region   20230315    1790. 仅执行一次字符串交换能否使两个字符串相等

    /**
     * https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/
     *
     * @param s1 字符串 s1
     * @param s2 字符串 s2
     * @return 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false
     */
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff.size() >= 2) {
                    return false;
                }
                diff.add(i);
            }
        }
        if (diff.isEmpty()) {
            return true;
        }
        if (diff.size() != 2) {
            return false;
        }
        return s1.charAt(diff.get(0)) == s2.charAt(diff.get(1)) && s1.charAt(diff.get(1)) == s2.charAt(diff.get(0));
    }
    //endregion

    //region    20230315    1822. 数组元素积的符号

    /**
     * https://leetcode.cn/problems/sign-of-the-product-of-an-array/
     *
     * @param nums 整数数组 nums
     * @return 返回数组乘积的符号
     */
    public int arraySign(int[] nums) {
        int sign = 1;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            if (num < 0) {
                sign = -sign;
            }
        }
        return sign;
    }
    //endregion
}
