package StudyPlan;

/**
 * @author yyb
 * leetcode_studyplan_ProgrammingSkills
 * leetcode 学习计划 编程能力
 */
public class TargetProgrammingSkills {

    //region    20230312    1491. 去掉最低工资和最高工资后的工资平均值

    /**
     * https://leetcode.cn/problems/average-salary-excluding-the-minimum-and-maximum-salary/
     * @param salary  整数数组 salary
     * @return  返回去掉最低工资和最高工资以后，剩下员工工资的平均值
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
}
