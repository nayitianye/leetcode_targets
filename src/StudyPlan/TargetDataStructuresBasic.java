package StudyPlan;

import java.util.*;

/**
 * @author yyb
 * leetcode_studyplan_data_structures_Basic
 * leetcode 学习计划 数据结构基础
 */
public class TargetDataStructuresBasic {

    //region    20230413    5. 最长回文子串

    /**
     * https://leetcode.cn/problems/longest-palindromic-substring/
     * @param s  一个字符串 s
     * @return  找到 s 中最长的回文子串
     */
    public String longestPalindrome1(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        int maxLength = 1;
        int begin = 0;
        //d[i][j]表示s[i..j]是否是回文串
        boolean[][] dp = new boolean[length][length];
        //初始化：所有长度为1的字串都是回文串
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        //先枚举字串的长度
        for (int L = 2; L <= length; L++) {
            //枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < length; i++) {
                //由i和j可以确定右边界，即j-i+1=L得
                int j = L + i - 1;
                //如果右边界越界，就可以退出当前循环
                if (j >= length) {
                    break;
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                //只要dp[i][L]==true成立，就表示字串s[i..L]是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLength);
    }
    //endregion

    //region    20230405    15. 三数之和

    /**
     * https://leetcode.cn/problems/3sum/
     *
     * @param nums 整数数组 nums
     * @return 返回所有和为 0 且不重复的三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        if (nums[nums.length - 1] < 0) {
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
    //endregion

    //region    20230412    43. 字符串相乘

    /**
     * https://leetcode.cn/problems/multiply-strings/
     * @param num1  非负整数 num1
     * @param num2  非负整数 num2
     * @return  返回 num1 和 num2 的乘积
     */
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
            ans = addStrings1(ans, curr.reverse().toString());
        }
        return ans;
    }

    public String addStrings1(String num1, String num2) {
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

    //region    20230412    49. 字母异位词分组

    /**
     * https://leetcode.cn/problems/group-anagrams/description/
     * @param strs  字符串数组 strs
     * @return  将字母异位词组合在一起 , 可以按任意顺序返回结果列表
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            //将每个出现次数大于0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    stringBuffer.append((char) ('a' + i));
                    stringBuffer.append(counts[i]);
                }
            }
            String key = stringBuffer.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
    //endregion

    //region    20230408    75. 颜色分类

    /**
     * https://leetcode.cn/problems/sort-colors/
     *
     * @param nums 含红色、白色和蓝色、共 n 个元素的数组 nums
     */
    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        // all in [0, zero) = 0
        // all in [zero, i) = 1
        // all in [two, len - 1] = 2

        // 循环终止条件是 i == two，那么循环可以继续的条件是 i < two
        // 为了保证初始化的时候 [0, zero) 为空，设置 zero = 0，
        // 所以下面遍历到 0 的时候，先交换，再加
        int zero = 0;
        // 为了保证初始化的时候[two,len-1]为空，设置 two=len
        // 所以下面遍历到 2 的时候，先减，再交换
        int two = len;
        int i = 0;
        // 当 i==two 上面的三个子区间正好覆盖了全部数组
        // 因此，循环可以继续的条件时 i < two
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, i, two);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
    //endregion

    //region    20230408    119. 杨辉三角 II

    /**
     * https://leetcode.cn/problems/pascals-triangle-ii/
     *
     * @param rowIndex 非负索引 rowIndex
     * @return 返回「杨辉三角」的第 rowIndex 行
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                cur.set(j, cur.get(j - 1) + cur.get(j));
            }
            cur.add(1);//部上每层的最后一个1
        }
        return cur;
    }
    //endregion

    //region    20230405    136. 只出现一次的数字

    /**
     * https://leetcode.cn/problems/single-number/
     *
     * @param nums 非空整数数组 nums
     * @return 找出那个只出现了一次的元素
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
    //endregion

    //region    20230405    169. 多数元素

    /**
     * https://leetcode.cn/problems/majority-element/
     *
     * @param nums 大小为 n 的数组 nums
     * @return 返回其中的多数元素
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
    //endregion

    //region    20230410    238. 除自身以外数组的乘积

    /**
     * https://leetcode.cn/problems/product-of-array-except-self/
     *
     * @param nums 整数数组 nums
     * @return 返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积
     */
    public int[] productExceptSelf(int[] nums) {
        int CountZero = 0;
        int Mul = 1;
        int FirstZero = 0;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                CountZero++;
                if (CountZero == 1) {
                    FirstZero = i;
                }
                if (CountZero > 1) {
                    return res;
                }
            } else {
                Mul *= nums[i];
            }
        }
        if (CountZero == 1) {
            res[FirstZero] = Mul;
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            res[i] = Mul / nums[i];
        }
        return res;
    }
    //endregion

    //region    20230411    290. 单词规律

    /**
     * https://leetcode.cn/problems/word-pattern/
     *
     * @param pattern 一种规律 pattern
     * @param s       一个字符串 s
     * @return 判断 s 是否遵循相同的规律
     */
    public boolean wordPattern(String pattern, String s) {
        String[] strs = s.trim().split(" ");
        if (pattern.length() != strs.length) {
            return false;
        }
        Map<Object, Integer> map = new HashMap<>();
        for (Integer i = 0; i < strs.length; i++) {
            if (map.put(pattern.charAt(i), i) != map.put(strs[i], i)) {
                return false;
            }
        }
        return true;
    }
    //endregion

    //region    20230410    334. 递增的三元子序列

    /**
     * https://leetcode.cn/problems/increasing-triplet-subsequence/
     *
     * @param nums 整数数组 nums
     * @return 存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }
    //endregion

    //region    20230410    409. 最长回文串

    /**
     * https://leetcode.cn/problems/longest-palindrome/
     *
     * @param s 包含大写字母和小写字母的字符串 s
     * @return 通过这些字母构造成的 最长的回文串
     */
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            } else {
                hashMap.put(s.charAt(i), 1);
            }
        }
        int res = 0, maxSinge = 0;
        for (Integer value : hashMap.values()) {
            if (value % 2 == 0) {
                res += value;
            } else {
                res += value / 2 * 2;
                maxSinge = 1;
            }
        }
        return res + maxSinge;
    }
    //endregion

    //region    20230410    415. 字符串相加

    /**
     * https://leetcode.cn/problems/add-strings/
     *
     * @param num1 非负整数 num1
     * @param num2 非负整数 num2
     * @return 它们的和并同样以字符串形式返回
     */
    public String addStrings(String num1, String num2) {
        StringBuffer res = new StringBuffer();
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int buffer = 0;
        while (index1 >= 0 || index2 >= 0) {
            int sum = 0;
            if (index1 >= 0 && index2 >= 0) {
                int a = num1.charAt(index1) - '0';
                int b = num2.charAt(index2) - '0';
                sum = a + b + buffer;

            } else if (index1 >= 0) {
                int a = num1.charAt(index1) - '0';
                sum = a + buffer;

            } else {
                int b = num2.charAt(index2) - '0';
                sum = b + buffer;
            }
            res.append(String.valueOf(sum % 10));
            buffer = sum / 10;
            index1--;
            index2--;
        }
        if (buffer >= 1) {
            res.append(String.valueOf(buffer));
        }
        return res.reverse().toString();
    }
    //endregion

    //region    20230410    560. 和为 K 的子数组

    /**
     * https://leetcode.cn/problems/subarray-sum-equals-k
     *
     * @param nums 整数数组 nums
     * @param k    一个整数 k
     * @return 统计并返回 该数组中和为 k 的连续子数组的个数
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (hashMap.containsKey(pre - k)) {
                count += hashMap.get(pre - k);
            }
            hashMap.put(pre, hashMap.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
    //endregion

    public static void main(String[] args) {
        new TargetDataStructuresBasic().addStrings("123", "11");
    }
}
