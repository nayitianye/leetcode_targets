package StudyPlan;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yyb
 * leetcode_studyplan_binary_search_basic
 * leetcode 学习计划 二分查找基础
 */
public class TaegetBinarySearchBasic {

    //region    自定义数据结构
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    //endregion

    //region    20230401    81. 搜索旋转排序数组 II

    /**
     * https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/
     *
     * @param nums   按非降序排列的整数数组 nums ，数组中的值不必互不相同
     * @param target 一个整数 target
     * @return 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false
     */
    public boolean search1(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] == target;
        }
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
    //endregion

    //region    20230401    154. 寻找旋转排序数组中的最小值 II

    /**
     * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/
     *
     * @param nums 可能存在 重复 元素值的数组 nums
     * @return 请你找出并返回数组中的 最小元素
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right -= 1;
            }
        }
        return nums[left];
    }
    //endregion

    //region    20230401    162. 寻找峰值

    /**
     * https://leetcode.cn/problems/find-peak-element/
     *
     * @param nums 数组 nums
     * @return 返回 任何一个峰值 所在位置即可
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (compare(nums, mid - 1, mid) < 0 && compare(nums, mid, mid + 1) > 0) {
                ans = mid;
                break;
            }
            if (compare(nums, mid, mid + 1) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // 辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况
    public int[] get(int[] nums, int index) {
        if (index == -1 || index == nums.length) {
            return new int[]{0, 0};
        }
        return new int[]{1, nums[index]};
    }

    public int compare(int[] nums, int index1, int index2) {
        int[] num1 = get(nums, index1);
        int[] num2 = get(nums, index2);
        if (num1[0] != num2[0]) {
            return num1[0] > num2[0] ? 1 : -1;
        }
        if (num1[1] == num2[1]) {
            return 0;
        }
        return num1[1] > num2[1] ? 1 : -1;
    }
    //endregion

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

    //region    20230330    222. 完全二叉树的节点个数

    /**
     * https://leetcode.cn/problems/count-complete-tree-nodes
     *
     * @param root 完全二叉树 的根节点 root
     * @return 求出该树的节点个数
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
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

    //region    20230402    436. 寻找右区间

    /**
     * https://leetcode.cn/problems/find-right-interval/
     *
     * @param intervals 区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同
     * @return 返回一个由每个区间 i 的 右侧区间 在 intervals 中对应下标组成的数组
     */
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] startIntervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            startIntervals[i][0] = intervals[i][0];
            startIntervals[i][1] = i;
        }
        Arrays.sort(startIntervals, (o1, o2) -> o1[0] - o2[0]);

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            int target = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (startIntervals[mid][0] >= intervals[i][1]) {
                    target = startIntervals[mid][1];
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = target;
        }
        return ans;
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

    //region    20230401    528. 按权重随机选择

    /**
     * https://leetcode.cn/problems/random-pick-with-weight/
     */
    class Solution {
        int[] pre;
        int total;

        public Solution(int[] w) {
            pre = new int[w.length];
            pre[0] = w[0];
            for (int i = 1; i < w.length; ++i) {
                pre[i] = pre[i - 1] + w[i];
            }
            total = Arrays.stream(w).sum();
        }

        public int pickIndex() {
            int x = (int) (Math.random() * total) + 1;
            return binarySearch(x);
        }

        private int binarySearch(int x) {
            int low = 0, high = pre.length - 1;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (pre[mid] < x) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
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

    //region    20230402    826. 安排工作以达到最大收益

    /**
     * https://leetcode.cn/problems/most-profit-assigning-work/
     *
     * @param difficulty 数组：difficulty difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益
     * @param profit     数组：profit
     * @param worker     数组：worker  worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作
     * @return
     */
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        Integer[] aux = new Integer[n];
        for (int i = 0; i < n; i++) aux[i] = i;
        Arrays.sort(aux, (a, b) -> difficulty[a] - difficulty[b]);
        Arrays.sort(worker);
        int idx = 0, best = 0, ans = 0;
        for (int x : worker) {
            while (idx < n && x >= difficulty[aux[idx]]) {
                best = Math.max(best, profit[aux[idx++]]);
            }
            ans += best;
        }
        return ans;
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

    //region    20230403    1292. 元素和小于等于阈值的正方形的最大边长

    /**
     * https://leetcode.cn/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold
     *
     * @param mat       大小为 m x n 的矩阵 mat
     * @param threshold 整数阈值 threshold
     * @return 返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0
     */
    public int maxSideLength(int[][] mat, int threshold) {
        //二分查找
        int row = mat.length, col = mat[0].length;
        int[][] prefix = new int[row + 1][col + 1];
        for (int i = 1; i <= row; ++i) {
            for (int j = 1; j <= col; ++j) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int left = 0, right = Math.min(row, col), mid = 0, res = 0;
        while (left < right) {
            mid = left + right + 1 >> 1;
            boolean flag = false;
            for (int i = 1; i + mid <= row + 1; ++i) {
                for (int j = 1; j + mid <= col + 1; ++j) {
                    int temp = prefix[i + mid - 1][j + mid - 1] - prefix[i + mid - 1][j - 1] - prefix[i - 1][j + mid - 1] + prefix[i - 1][j - 1];
                    if (temp <= threshold) {
                        flag = true;
                    }
                }
            }
            if (flag) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
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

    //region    20230403    1498. 满足条件的子序列数目

    /**
     * https://leetcode.cn/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
     *
     * @param nums   整数数组 nums
     * @param target 一个整数 target
     * @return 统计并返回 nums 中能满足其最小元素与最大元素的 和 小于或等于 target 的 非空 子序列的数目
     */
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        long res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] * 2 > target) {
                break;
            }
            int left = i, right = nums.length - 1;
            while (left < right) {
                int mid = left + right + 1 >> 1;
                if (nums[mid] + nums[i] > target) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            if (nums[i] + nums[left] > target) {
                break;
            }
            res += fastPow(2, (left - i));
            res %= MOD;
        }
        return (int) res;
    }

    public long fastPow(int x, int n) {
        long res = 1, y = x;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = (res * y) % MOD;
            }
            y = (y * y) % MOD;
            n >>= 1;
        }
        return res % MOD;
    }

    final static int MOD = (int) 1e9 + 7;
    //endregion

    //region    20230402    1508. 子数组和排序后的区间和

    /**
     * https://leetcode.cn/problems/range-sum-of-sorted-subarray-sums/
     *
     * @param nums  数组 nums
     * @param n     包含 n 个正整数
     * @param left  下标为 left
     * @param right 下标为 right
     * @return 下标为 left 到 right （下标从 1 开始）的所有数字和（包括左右端点
     */
    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] prefixSums = new int[n + 1];
        prefixSums[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
        int[] prefixPrefixSums = new int[n + 1];
        prefixPrefixSums[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixPrefixSums[i + 1] = prefixPrefixSums[i] + prefixSums[i + 1];
        }
        return (getSum(prefixSums, prefixPrefixSums, n, right) - getSum(prefixSums, prefixPrefixSums, n, left - 1)) % MODULO;
    }

    public int getSum(int[] prefixSums, int[] prefixPrefixSums, int n, int k) {
        int num = getKth(prefixSums, n, k);
        int sum = 0;
        int count = 0;
        for (int i = 0, j = 1; i < n; i++) {
            while (j <= n && prefixSums[j] - prefixSums[i] < num) {
                j++;
            }
            j--;
            sum = (sum + prefixPrefixSums[j] - prefixPrefixSums[i] - prefixSums[i] * (j - i)) % MODULO;
            count += j - i;
        }
        sum = (sum + num * (k - count)) % MODULO;
        return sum;
    }

    public int getKth(int[] prefixSums, int n, int k) {
        int low = 0, high = prefixSums[n];
        while (low < high) {
            int mid = (high - low) / 2 + low;
            int count = getCount(prefixSums, n, mid);
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int getCount(int[] prefixSums, int n, int x) {
        int count = 0;
        for (int i = 0, j = 1; i < n; i++) {
            while (j <= n && prefixSums[j] - prefixSums[i] <= x) {
                j++;
            }
            j--;
            count += j - i;
        }
        return count;
    }

    static final int MODULO = 1000000007;
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

    //region    20230401    1574. 删除最短的子数组使剩余数组有序

    /**
     * https://leetcode.cn/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
     *
     * @param arr 整数数组 arr
     * @return 返回满足题目要求的最短子数组的长度
     */
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int i = 0, j = n - 1;
        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            ++i;
        }
        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            --j;
        }
        if (i >= j) {
            return 0;
        }
        int ans = Math.min(n - i - 1, j);
        for (int l = 0; l <= i; ++l) {
            int r = search(arr, arr[l], j);
            ans = Math.min(ans, r - l - 1);
        }
        return ans;
    }

    private int search(int[] arr, int x, int left) {
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    //endregion

    //region    20230330    1712. 将数组分成三个子数组的方案数

    /**
     * https://leetcode.cn/problems/ways-to-split-array-into-three-subarrays
     *
     * @param nums 非负 整数数组 nums
     * @return 返回好的分割 nums 方案数目。由于答案可能会很大，请你将结果对 109 + 7 取余后返回
     */
    public int waysToSplit(int[] nums) {
        int n = nums.length;
        // 计算前缀和
        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        final int MOD = 1000000000 + 7;
        long ans = 0;
        // 第一刀的最大值：sum(nums) / 3
        int t = sums[n - 1] / 3;
        for (int i = 0; i < n && sums[i] <= t; i++) {
            // 二分查找第二刀的最小值：sum(mid) == sum(left)
            // 在 [i+1, n] 中二分查找 sums[i] * 2，sums[i] 为到 i 为止元素和，因为是前缀数组，因而应该查找 sum(left) + sum(mid)
            int left = lowerBound(i + 1, n - 1, sums, sums[i] * 2);
            // 二分查找第二刀的最大值：sum(mid) == sum(mid + right) / 2
            // 在 [i+1, n] 中二分查找 sums[i] + (sums[n - 1] - sums[i]) / 2)，因为是前缀数组，因而应该查找 sum(left) + sum(mid + right) / 2
            int right = upperBound(i + 1, n - 1, sums, sums[i] + (sums[n - 1] - sums[i]) / 2);
            if (right >= left) {
                ans += right - left + 1;
            }
        }
        return (int) (ans % MOD);
    }

    public int lowerBound(int left, int right, int[] nums, int target) {
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int upperBound(int left, int right, int[] nums, int target) {
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
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
     *
     * @param nums 整数数组 nums
     * @param k    一个整数 k
     * @return 返回数组中最高频元素的 最大可能频数
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
