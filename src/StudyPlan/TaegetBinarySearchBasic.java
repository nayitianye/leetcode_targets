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

    //region    20230327    240. 搜索二维矩阵 II

    /**
     * https://leetcode.cn/problems/search-a-2d-matrix-ii/
     *
     * @param matrix m x n 矩阵 matrix
     * @param target 目标值 target
     * @return 判断目标值是否在数组中
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    //endregion

    //region    20230327    275. H 指数 II

    /**
     * https://leetcode.cn/problems/h-index-ii/
     *
     * @param citations 整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数
     * @return 返回该研究者的 h 指数
     */
    public int hIndex(int[] citations) {
        int letf = 0, right = citations.length - 1;
        while (letf <= right) {
            int mid = letf + (right - letf) / 2;
            if (citations[mid] >= citations.length - mid) {
                right = mid - 1;
            } else {
                letf = mid + 1;
            }
        }
        return citations.length - letf;
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

    //region    20230328    540. 有序数组中的单一元素

    /**
     * https://leetcode.cn/problems/single-element-in-a-sorted-array/
     *
     * @param nums 整数组成的有序数组 nums，其中每个元素都会出现两次，唯有一个数只会出现一次。
     * @return 找出并返回只出现一次的那个数
     */
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == nums[mid ^ 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
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

    //region    20230327    1482. 制作 m 束花所需的最少天数

    /**
     * https://leetcode.cn/problems/minimum-number-of-days-to-make-m-bouquets
     *
     * @param bloomDay 整数数组 bloomDay
     * @param m        整数 m
     * @param k        整数 k
     * @return 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1
     */
    public int minDays(int[] bloomDay, int m, int k) {
        if (m > bloomDay.length / k) {
            return -1;
        }
        int low = Integer.MAX_VALUE, high = 0;
        int length = bloomDay.length;
        for (int i = 0; i < length; i++) {
            low = Math.min(low, bloomDay[i]);
            high = Math.max(high, bloomDay[i]);
        }
        while (low < high) {
            int days = (high - low) / 2 + low;
            if (canMake(bloomDay, days, m, k)) {
                high = days;
            } else {
                low = days + 1;
            }
        }
        return low;
    }

    public boolean canMake(int[] bloomDay, int days, int m, int k) {
        int bouquets = 0;
        int flowers = 0;
        int length = bloomDay.length;
        for (int i = 0; i < length && bouquets < m; i++) {
            if (bloomDay[i] <= days) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        return bouquets >= m;
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

    //region    20230327    1818. 绝对差值和

    /**
     * https://leetcode.cn/problems/minimum-absolute-sum-difference/
     *
     * @param nums1 数组 nums1
     * @param nums2 数组 nums2
     * @return 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和
     */
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        final int MOD = 1000000007;
        int n = nums1.length;
        int[] rec = new int[n];
        System.arraycopy(nums1, 0, rec, 0, n);
        Arrays.sort(rec);
        int sum = 0, maxn = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % MOD;
            int j = binarySearch2(rec, nums2[i]);
            if (j < n) {
                maxn = Math.max(maxn, diff - (rec[j] - nums2[i]));
            }
            if (j > 0) {
                maxn = Math.max(maxn, diff - (nums2[i] - rec[j - 1]));
            }
        }
        return (sum - maxn + MOD) % MOD;
    }

    public int binarySearch2(int[] rec, int target) {
        int left = 0, right = rec.length - 1;
        if (rec[right] < target) {
            return right + 1;
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (rec[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    //endregion

    //region    20230328    1838. 最高频元素的频数

    /**
     * https://leetcode.cn/problems/frequency-of-the-most-frequent-element/
     * @param nums  整数数组 nums
     * @param k 一个整数 k
     * @return  返回数组中最高频元素的 最大可能频数
     */
    public int maxFrequency(int[] nums, int k) {
        /*
        排序+滑窗+前缀和+二分:
        1.因为要求的是频数,也就是要操作使得最接近某个数的数字优先变成该数字,因此用到排序
        2.滑窗是固定窗口右边界,寻找左边界的过程
        3.前缀和用于求解某段区间需要填充的操作次数,ss = nums[r] * len - sum[r - k + 1, r]
        4.二分用于寻找适合符合要求的窗口长度的最大值->最大频数
         */
        Arrays.sort(nums);
        int len = nums.length;
        int[] sum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int l = 0, r = len; // 窗口长度
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (check(mid, k, len, nums, sum)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;   // l==r
    }

    // nums内是否存在以curLen为长度的窗口,窗口内可以用k填充成完全相等的元素
    private boolean check(int curLen, int k, int len, int[] nums, int[] sum) {
        // 遍历所有长度为curLen的窗口
        for (int i = 0; i + curLen - 1 < len; i++) {
            int j = i + curLen - 1; // 窗口右端索引
            int ss = nums[j] * curLen - (sum[j + 1] - sum[i]);  // 要填充的部分
            if (k >= ss) return true;   // k可以满足要填充的部分
        }
        return false;   // 无法完成填充
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
            int mid = left + (right - left) / 2;
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
