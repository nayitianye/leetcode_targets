package StudyPlan;

import java.util.HashMap;
import java.util.List;

/**
 * @author yyb
 * leetcode_studyplan_premiumAlgo
 * leetcode 学习计划 尊享面试100题
 */
public class TargetPremiumAlgo100 {

    //region    20230427    161. 相隔为 1 的编辑距离

    /**
     * https://leetcode.cn/problems/one-edit-distance/
     * @param s  字符串 s
     * @param t  字符串 t
     * @return  如果它们的编辑距离为 1 ，则返回 true ，否则返回 false .
     */
    public boolean isOneEditDistance(String s, String t) {
        if(Math.abs(s.length()-t.length())>1){
            return false;
        }
        int index1=0,index2=0;
        boolean flag=true;
        while (index1<s.length() ||index2<t.length()){
            if(s.charAt(index1)==t.charAt(index2)){
                continue;
            }

        }
        return false;
    }
    //endregion

    //region    20230426    624. 数组列表中的最大距离

    /**
     * https://leetcode.cn/problems/maximum-distance-in-arrays/
     *
     * @param arrays 给定 m 个数组 arrays
     * @return
     */
    public int maxDistance(List<List<Integer>> arrays) {
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;
        int maxDistance=Integer.MIN_VALUE;
        for (List<Integer> list : arrays) {
            minNum = Math.min(list.get(0), minNum);
            maxNum = Math.max(list.get(list.size() - 1), maxNum);
        }
        return maxNum - minNum;
    }
    //endregion

    //region    20230427    1056. 易混淆数

    /**
     * https://leetcode.cn/problems/confusing-number
     * @param n  一个数字 N
     * @return  判断其是否是易混淆的数
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

    //region    20230426    1427. 字符串的左右移

    /**
     * https://leetcode.cn/problems/perform-string-shifts/
     *
     * @param s     字符串 s
     * @param shift 一个矩阵 shift
     * @return 对这个字符串进行所有操作后，返回最终结果
     */
    public String stringShift(String s, int[][] shift) {
        for (int i = 0; i < shift.length; i++) {
            if (shift[i][1] > s.length()) {
                shift[i][1] = shift[i][1] % s.length();
            }
            if (shift[i][0] == 1) {
                s = s.substring(s.length() - shift[i][1]) + s.substring(0, s.length() - shift[i][1]);
            } else {
                s = s.substring(shift[i][1]) + s.substring(0, shift[i][1]);
            }
        }
        return s;
    }
    //endregion
}
