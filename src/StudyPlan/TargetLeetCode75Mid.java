package StudyPlan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author yyb
 * leetcode_studyplan_LeetCode_75_中等
 * leetcode 学习计划 LeetCode 75 Level2
 */
public class TargetLeetCode75Mid {

    //region    20230401    14. 最长公共前缀

    /**
     * https://leetcode.cn/problems/longest-common-prefix/
     *
     * @param strs 字符串数组 strs
     * @return 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < Math.min(res.length(), strs[i].length()); j++) {
                if (res.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            res = res.substring(0, j);
        }
        return res;
    }
    //endregion

    //region    20230401    43. 字符串相乘
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        if (num1.equals("1")) {
            return num2;
        }
        if (num2.equals("1")) {
            return num1;
        }
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }
    //endregion

    //region    20230401    54. 螺旋矩阵

    /**
     * https://leetcode.cn/problems/spiral-matrix/
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int rows = matrix.length, colums = matrix[0].length;
        int left = 0, right = colums - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                res.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                res.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    res.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    res.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }
    //endregion

    //region    20230401    202. 快乐数

    /**
     * https://leetcode.cn/problems/happy-number/
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        while (n != 1) {
            if (hashSet.contains(n)) {
                return false;
            }
            hashSet.add(n);
            n = getNextN(n);
        }
        return true;
    }

    public int getNextN(int n) {
        int res = 0;
        while (n != 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }
    //endregion

    //region    20230401    1706. 球会落何处
    public int[] findBall(int[][] grid) {
        int n = grid[0].length;
        int[] res = new int[n];
        for (int i = 0; i < grid[0].length; i++) {
            int level = 0;
            int index = i;
            while (level < grid.length) {
                if (grid[level][index] == 1) {
                    int right = index + 1;
                    if (right < n && grid[level][right] == 1) {
                        index = right;
                    } else {
                        res[i] = -1;
                        break;
                    }
                } else {
                    int left = index - 1;
                    if (left >= 0 && grid[level][left] == -1) {
                        index = left;
                    } else {
                        res[i] = -1;
                        break;
                    }
                }
                level++;
            }
            if (level == grid.length) {
                res[i] = index;
            }
        }
        return res;
    }
    //endregion

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}, {1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}};
        new TargetLeetCode75Mid().findBall(matrix);
    }
}
