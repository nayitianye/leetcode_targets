package StudyPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yyb
 * leetcode_studyplan_binary_search_basic
 * leetcode 学习计划 二分查找基础
 */
public class TaegetBinarySearchBasic {

    //region    20230320    209. 长度最小的子数组

    /**
     * https://leetcode.cn/problems/minimum-size-subarray-sum/
     *
     * @param target 一个正整数 target
     * @param nums   含有 n 个正整数的数组 nums
     * @return 该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度
     */
    public int minSubArrayLen(int target, int[] nums) {
        int[] presum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        if (presum[nums.length] < target) {
            return 0;
        }
        int minLength = Integer.MAX_VALUE;
        for (int i = 1; i < presum.length; i++) {
            int temptarget = target + presum[i - 1];
            int bound = Arrays.binarySearch(presum, temptarget);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound < presum.length) {
                minLength = Math.min(minLength, bound - (i - 1));
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    //endregion

    //region    20230324    287. 寻找重复数

    /**
     * https://leetcode.cn/problems/find-the-duplicate-number/description/
     *
     * @param nums 包含 n + 1 个整数的数组 nums
     * @return 返回 这个重复的数
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }
    //endregion

    //region    20230322    300. 最长递增子序列

    /**
     * https://leetcode.cn/problems/longest-increasing-subsequence/
     *
     * @param nums 整数数组 nums
     * @return 找到其中最长严格递增子序列的长度
     */
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int left = 1, right = len, pos = 0;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
    //endregion

    //region    20230321    611. 有效三角形的个数

    /**
     * https://leetcode.cn/problems/valid-triangle-number/
     *
     * @param nums 含非负整数的数组 nums
     * @return 其中可以组成三角形三条边的三元组个数
     */
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int left = j + 1, right = n - 1, k = j;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (nums[mid] < nums[i] + nums[j]) {
                        k = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                ans += k - j;
            }
        }
        return ans;
    }
    //endregion

    //region    20230321    658. 找到 K 个最接近的元素

    /**
     * https://leetcode.cn/problems/find-k-closest-elements/
     *
     * @param arr 一个 排序好 的数组 arr
     * @param k   整数 k
     * @param x   整数 x
     * @return 数组中找到最靠近 x（两数之差最小）的 k 个数
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int right = binarySearch1(arr, x);
        int left = right - 1;
        while (k-- > 0) {
            if (left < 0) {
                right++;
            } else if (right >= arr.length) {
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                left--;
            } else {
                right++;
            }
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public int binarySearch1(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    //endregion

    //region    20230324    875. 爱吃香蕉的珂珂

    /**
     * https://leetcode.cn/problems/koko-eating-bananas/
     *
     * @param piles 有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉
     * @param h     h 小时
     * @return h 小时内吃掉所有香蕉的最小速度 k
     */
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        for (int i = 0; i < piles.length; i++) {
            high = Math.max(high, piles[i]);
        }
        int k = high;
        while (low < high) {
            int speed = low + (high - low) / 2;
            long time = getTime(piles, speed);
            if (time <= h) {
                k = speed;
                high = speed;
            } else {
                low = speed + 1;
            }
        }
        return k;
    }

    public long getTime(int[] piles, int speed) {
        long time = 0;
        for (int i = 0; i < piles.length; i++) {
            int curTime = (piles[i] + speed - 1) / speed;
            time += curTime;
        }
        return time;
    }
    //endregion

    //region    20230324    1283. 使结果不超过阈值的最小除数

    /**
     * https://leetcode.cn/problems/find-the-smallest-divisor-given-a-threshold/description/
     *
     * @param nums      整数数组 nums
     * @param threshold 正整数 threshold
     * @return 小于等于阈值 threshold 的除数中 最小 的那个
     */
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = 1000000;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (!possible(nums, mid, threshold)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public boolean possible(int[] nums, int divisor, int threshold) {
        int count = 0;
        for (int num : nums) {
            count += (num - 1) / divisor + 1;
        }
        return count <= threshold;
    }
    //endregion

    //region    20230324    1552. 两球之间的磁力

    /**
     * https://leetcode.cn/problems/magnetic-force-between-two-balls/
     *
     * @param position 整数数组 position, 有 n 个空的篮子，第 i 个篮子的位置在 position[i]
     * @param m        一个整数 m  m 个球
     * @return 最大化的最小磁力
     */
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 1, right = position[position.length - 1] - position[0], ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid, position, m)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public boolean check(int x, int[] position, int m) {
        int pre = position[0], cnt = 1;
        for (int i = 1; i < position.length; i++) {
            if (position[i] - pre >= x) {
                pre = position[i];
                cnt += 1;
            }
        }
        return cnt >= m;
    }
    //endregion

    //region    20230322    1760. 袋子里最少数目的球

    /**
     * https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/
     *
     * @param nums          一个整数数组 nums ，其中 nums[i] 表示第 i 个袋子里球的数目。
     * @param maxOperations 一个整数 maxOperations
     * @return 最小开销
     */
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1, right = Arrays.stream(nums).max().getAsInt();
        int ans = 0;
        while (left <= right) {
            int y = (left + right) / 2;
            long ops = 0;
            for (int x : nums) {
                ops += (x - 1) / y;
            }
            if (ops <= maxOperations) {
                ans = y;
                right = y - 1;
            } else {
                left = y + 1;
            }
        }
        return ans;
    }
    //endregion

    //region    20230325    1870. 准时到达的列车最小时速

    /**
     * @param dist 长度为 n 的整数数组 dist ，其中 dist[i] 表示第 i 趟列车的行驶距离（单位是千米）
     * @param hour 浮点数 hour ，表示你到达办公室可用的总通勤时间
     * @return 返回能满足你准时到达办公室所要求全部列车的 最小正整数 时速（单位：千米每小时），如果无法准时到达，则返回 -1。
     */
    public int minSpeedOnTime(int[] dist, double hour) {
        int i = 1;//最小速度
        int j = Arrays.stream(dist).max().getAsInt() * 100;//最大速度
        while (i <= j) {
            int m = i + (j - i) / 2;
            if (costtime(m, dist) > hour) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i > Arrays.stream(dist).max().getAsInt() * 100 ? -1 : i;
    }

    public double costtime(double speed, int[] dist) {
        int len = dist.length;
        double result = 0;
        for (int i = 0; i < len - 1; i++) {
            result += Math.ceil(dist[i] / speed);
        }
        return result + dist[len - 1] / speed;
    }
    //endregion

    //region    20230321    1894. 找到需要补充粉笔的学生编号

    /**
     * https://leetcode.cn/problems/find-the-student-that-will-replace-the-chalk/
     *
     * @param chalk 长度为 n 且下标从 0 开始的整数数组 chalk
     * @param k     一个整数 k
     * @return
     */
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        if (chalk[0] > k) {
            return 0;
        }
        for (int i = 1; i < n; i++) {
            chalk[i] += chalk[i - 1];
            if (chalk[i] > k) {
                return i;
            }
        }
        k %= chalk[n - 1];
        return binarySearch(chalk, k);
    }

    public int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    //endregion

    //region    20230325    1898. 可移除字符的最大数目

    /**
     * https://leetcode.cn/problems/maximum-number-of-removable-characters/
     *
     * @param s         字符串 s
     * @param p         字符串 p
     * @param removable 一个元素 互不相同 且下标 从 0 开始 计数的整数数组 removable ，该数组是 s 中下标的一个子集
     * @return 返回你可以找出的 最大 k
     */
    public int maximumRemovals(String s, String p, int[] removable) {
        int left = 0, right = removable.length - 1;
        while (left <= right) {
            int mid = left + (right -left) / 2;
            if (!isSubsequence(s, p, mid, removable)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right + 1;
    }

    public boolean isSubsequence(String s, String p, int k, int[] removable) {
        int m = s.length();
        int n = p.length();
        int i = 0;
        int j = 0;
        boolean[] state = new boolean[m];
        for (int x = 0; x <= k; x++) {
            state[removable[x]] = true;
        }
        while (i < m && j < n) {
            if (s.charAt(i) == p.charAt(j) && !state[i]) {
                j++;
            }
            i++;
        }
        return j == n;
    }
    //endregion

    public static void main(String[] args) {
        new TaegetBinarySearchBasic().chalkReplacer(new int[]{3, 4, 1, 2}, 25);
    }
}
