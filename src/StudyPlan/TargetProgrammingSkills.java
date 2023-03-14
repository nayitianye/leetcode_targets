package StudyPlan;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author yyb
 * leetcode_studyplan_ProgrammingSkills
 * leetcode 学习计划 编程能力
 */
public class TargetProgrammingSkills {

    //region    20230315    202. 快乐数

    /**
     * https://leetcode.cn/problems/happy-number/description/
     * @param n 正整数 n
     * @return  判断 n 是否是快乐数
     */
    public boolean isHappy(int n) {
        HashSet<Integer> hashSet=new HashSet<>();
        while (n!=1&&!hashSet.contains(n)){
            hashSet.add(n);
            n=getNext(n);
        }
        return n==1;
    }

    public int getNext(int n){
        int sum=0;
        while(n!=0){
            int d=n%10;
            sum+=d*d;
            n=n/10;
        }
        return sum;
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

    //region    20230312    1523. 在区间范围内统计奇数数目

    /**
     * https://leetcode.cn/problems/count-odd-numbers-in-an-interval-range/
     *
     * @param low  非负整数 low
     * @param high 非负整数 high
     * @return 返回 low 和 high 之间（包括二者）奇数的数目
     */
    public int countOdds(int low, int high) {
        if (low % 2 == 1) {
            low--;
        }
        if (high % 2 == 1) {
            high++;
        }
        return (high - low) / 2 + 1;
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
