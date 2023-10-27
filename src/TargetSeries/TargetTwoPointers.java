package TargetSeries;

import java.util.*;

/**
 * @author yyb
 * leetcode_tag_双指针
 * leetcode 标签 双指针
 */
public class TargetTwoPointers {

    //region    20230422    246. 中心对称数

    /**
     * https://leetcode.cn/problems/strobogrammatic-number/
     *
     * @param num 字符串数字 num
     * @return 判断该数字是否是中心对称数
     */
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        //满足要求的数字对
        int left = 0, right = num.length() - 1;
        //双指针初始化
        while (left <= right) {
            if (map.get(num.charAt(left)) == null || map.get(num.charAt(right)) == null) {
                //不满足要求的数字
                return false;
            }
            if (map.get(num.charAt(left)) != num.charAt(right)) {
                //check一下是否真的中心对称
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    //endregion

    //region    20230422    247. 中心对称数 II

    /**
     * https://leetcode.cn/problems/strobogrammatic-number-ii/
     *
     * @param n 一个整数 n
     * @return 返回所有长度为 n 的 中心对称数
     */
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    public List<String> helper(int n, int m) {
        // 第一步：判断输入或者状态是否合法
        if (n < 0 || m < 0 || n > m) {
            throw new IllegalArgumentException("invalid input");
        }
        // 第二步，判断递归是否应当结束
        if (n == 0) {
            return new ArrayList<>(Arrays.asList(""));
        }
        if (n == 1) {
            return new ArrayList<>(Arrays.asList("0", "1", "8"));
        }
        // 第三步：缩小问题规模
        List<String> list = helper(n - 2, m);
        // 第四步:整合结果
        List<String> res = new ArrayList<>();
        for (String s : list) {
            if (n != m) {
                // n=m时，表示最外层处理。
                // 例如：原始需求n=m=2, '00'不合法
                // 若原始需求n=m=4, 内层循环n=2,m=4,'00';最外层循环，n=m=4时，'1001'
                res.add("0" + s + "0");
            }
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        return res;
    }
    //endregion

    //region    20230422    248. 中心对称数 III
    public int strobogrammaticInRange(String low, String high) {
        int lo = low.length();
        int hi = high.length();
        char[][] mapping = {{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
        //获取长度 [lo,hi] 区间的中心对称数
        for (int i = lo; i <= hi; i++) {
            char[] chs = new char[i];
            getStrobogrammatic(chs, 0, chs.length - 1, low, high, mapping);
        }
        return count;
    }

    public void getStrobogrammatic(char[] chs, int lo, int hi, String low, String high, char[][] mapping) {
        //填充完毕，开始统计个数
        if (lo > hi) {
            if (chs.length == 1 || chs[0] != '0') {
                String str = String.valueOf(chs);
                if (compare(str, low) && compare(high, str)) {
                    count++;
                }
            }
            return;
        }
        //遍历 lo 和 hi 的可能取值
        for (char[] map : mapping) {
            if (lo == hi && map[0] != map[1]) {
                continue;
            }
            chs[lo] = map[0];
            chs[hi] = map[1];
            getStrobogrammatic(chs, lo + 1, hi - 1, low, high, mapping);
        }
    }

    public boolean compare(String str1, String str2) {
        if (str1.length() == str2.length()) {
            if (str1.compareTo(str2) >= 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public int count = 0;
    //endregion

    //region    20230422    1056. 易混淆数

    /**
     * https://leetcode.cn/problems/confusing-number/
     *
     * @param n 一个数字 N
     * @return 判断其是否易混淆数 (confusing number) 在旋转180°以后，可以得到和原来不同的数，且新数字的每一位都是有效的
     */
    public boolean confusingNumber(int n) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("0", "0");
        hashMap.put("1", "1");
        hashMap.put("6", "9");
        hashMap.put("8", "8");
        hashMap.put("9", "6");
        String str = String.valueOf(n);
        String RN = "";
        for (char ch : str.toCharArray()) {
            if (!hashMap.containsKey(String.valueOf(ch))) {
                return false;
            }
            RN = RN + hashMap.get(String.valueOf(ch));
        }
        return !new StringBuffer(str).reverse().toString().equalsIgnoreCase(RN);
    }
    //endregion

    //region    20230422    1088. 易混淆数 II

    /**
     * https://leetcode.cn/problems/confusing-number-ii/
     *
     * @param n 正整数 n
     * @return 返回  [1, n] 范围内的 易混淆数 的数量
     */
    public int confusingNumberII(int n) {
        int[][] mapping = new int[][]{{0, 0}, {1, 1}, {6, 9}, {8, 8}, {9, 6}};
        dfsConfusingNumber(0, 0, 0, n, mapping);
        return count;
    }

    public void dfsConfusingNumber(int curr, int rev, int digits, int n, int[][] mapping) {
        // 当前枚举的数字 >n 结束
        if (curr > n) {
            return;
        }
        if (curr != rev) {
            count++;
        }
        //枚举下一个要加上的数，比如9，假设当前curr是16
        //curr    16
        //nextCurr 16*10+9        =169
        //nextRev  9*100+rev(61)  =961
        for (int i = 0; i < 5; i++) {
            if (digits == 0 && i == 0) {
                continue;
                //前导0不符合要求
            }
            // 下一个数字nextCurr是 curr*10+[] 如果当前curr>n/10 就没有继续的必要了 nextCurr一定过大 剪枝
            if (curr > n / 10) {
                return;
            }
            int nextCurr = curr * 10 + mapping[i][0];
            int nextRecv = mapping[i][1] * (int) (Math.pow(10, digits)) + rev;
            dfsConfusingNumber(nextCurr, nextRecv, digits + 1, n, mapping);
        }
    }
    //endregion
}
