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
     *
     * @param n 输入是一个无符号整数（以二进制串的形式）
     * @return 返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）
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

    //region    20230317    283. 移动零

    /**
     * https://leetcode.cn/problems/move-zeroes/
     *
     * @param nums 一个数组 nums,编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
    //endregion

    //region    20230317    496. 下一个更大元素 I

    /**
     * https://leetcode.cn/problems/next-greater-element-i/description/
     *
     * @param nums1 没有重复元素 的数组 nums1
     * @param nums2 没有重复元素 的数组 nums2
     * @return 每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1。
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            hashMap.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = hashMap.get(nums1[i]);
        }
        return res;
    }
    //endregion

    //region    20230316    589. N 叉树的前序遍历

    /**
     * https://leetcode.cn/problems/n-ary-tree-preorder-traversal/
     *
     * @param root n 叉树的根节点  root
     * @return n 叉树的根节点  root
     */
    public List<Integer> preorder(TargetLeetCode75.Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TargetLeetCode75.Node> queue = new ArrayDeque<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            TargetLeetCode75.Node node = queue.poll();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; --i) {
                queue.push(node.children.get(i));
            }
        }
        return res;
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
            sum += n % 10;
            multi *= n % 10;
            n = n / 10;
        }
        return multi - sum;
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

    //region    202303018   1572. 矩阵对角线元素的和
    public int diagonalSum(int[][] mat) {
        int res = 0;
        for (int i = 0; i < mat.length; i++) {
            res = res + mat[i][i] + mat[i][mat.length - i - 1];
        }
        // 若矩阵阶数为偶数，由于主副对角线无交点因此不需要剔除重复的交点位置元素
        // 但若矩阵阶数为奇数，则需要去除多加一次交点处的元素
        // 5&1 => 0101 & 0001 = 0001 -> sum - mat[mid][mid] * 1
        // &1 => 0010 & 0001 = 0000 -> sum
        return res - mat[mat.length / 2][mat.length / 2] * (mat.length & 1);
    }
    //endregion

    //region    20230317    1588. 所有奇数长度子数组的和

    /**
     * https://leetcode.cn/problems/sum-of-all-odd-length-subarrays/description/
     *
     * @param arr 正整数数组 arr
     * @return 计算所有可能的奇数长度子数组的和
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int[] prefixSum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }
        int sum = 0;
        for (int start = 0; start < arr.length; start++) {
            for (int length = 1; length + start <= arr.length; length = length + 2) {
                int end = start + length;
                sum += prefixSum[end] - prefixSum[start];
            }
        }
        return sum;
    }
    //endregion

    //region    20230317    1672. 最富有客户的资产总量

    /**
     * https://leetcode.cn/problems/richest-customer-wealth/description/
     *
     * @param accounts m x n 的整数网格 accounts
     * @return 其中 accounts[i][j] 是第 i 位客户在第 j 家银行托管的资产数量。返回最富有客户所拥有的 资产总量
     */
    public int maximumWealth(int[][] accounts) {
        int maxWeath = 0;
        for (int i = 0; i < accounts.length; i++) {
            int sum = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                sum += accounts[i][j];
            }
            maxWeath = Math.max(maxWeath, sum);
        }
        return maxWeath;
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
