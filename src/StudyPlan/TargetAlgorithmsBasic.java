package StudyPlan;

import java.util.*;

/**
 * @author yyb
 * leetcode_studyplan_algorithms_Basic
 * leetcode 学习计划 算法基础
 */
public class TargetAlgorithmsBasic {

    //region    自定义数据结构
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


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

    //region    20230412    11. 盛最多水的容器

    /**
     * https://leetcode.cn/problems/container-with-most-water/
     *
     * @param height 给定一个长度为 n 的整数数组 height
     * @return 出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。返回容器可以储存的最大水量
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ? Math.max(res, (j - i) * height[i++]) : Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }
    //endregion

    //region    20230408    15. 三数之和

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

    //region    20230405    33. 搜索旋转排序数组

    /**
     * https://leetcode.cn/problems/search-in-rotated-sorted-array/
     *
     * @param nums   整数数组 nums
     * @param target 整数 target
     * @return nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //先根据 nums[0] 与 target 的关系判断目标值是在左半段还是右半段
            if (target >= nums[0]) {
                //目标值在左半段时，若mid在右半段，则讲mid索引的值该成inf
                if (nums[mid] < nums[0]) {
                    nums[mid] = Integer.MAX_VALUE;
                }
            } else {
                // 目标值在右半段时，若 mid 在左半段，则将 mid 索引的值改成 -inf
                if (nums[mid] >= nums[0]) {
                    nums[mid] = Integer.MIN_VALUE;
                }
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    //endregion

    //region    20230405    34. 在排序数组中查找元素的第一个和最后一个位置

    /**
     * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
     *
     * @param nums   非递减顺序排列的整数数组 nums
     * @param target 目标值 target
     * @return 目标值在数组中的开始位置和结束位置
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        if (nums[0] > target || nums[nums.length - 1] < target) {
            return res;
        }
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                int start = mid, end = mid;
                while (start >= 0 && nums[start] == target) {
                    start--;
                }
                while (end <= nums.length - 1 && nums[end] == target) {
                    end++;
                }
                res[0] = ++start;
                res[1] = --end;
                break;
            }
        }
        return res;
    }
    //endregion

    //region    20230405    74. 搜索二维矩阵

    /**
     * https://leetcode.cn/problems/search-a-2d-matrix/
     *
     * @param matrix m x n 的 matrix 矩阵
     * @param target 目标值 target
     * @return 判断目标值是否在数组中
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int bottom = -1, top = matrix.length - 1, mid = 0;
        while (bottom < top) {
            mid = bottom + (top - bottom + 1) / 2;
            if (matrix[mid][0] <= target) {
                bottom = mid;
            } else {
                top = mid - 1;
            }
        }
        if (bottom == -1) {
            return false;
        }
        int left = 0, right = matrix[0].length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (matrix[bottom][mid] == target) {
                return true;
            } else if (matrix[bottom][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
    //endregion

    //region    20230411    82. 删除排序链表中的重复元素 II

    /**
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii
     *
     * @param head 已排序的链表的头 head
     * @return 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表
     */
    public ListNode deleteDuplicates(ListNode head) {
        //没有节点或者只有一个节点，必然没有重复元素
        if (head == null || head.next == null) {
            return head;
        }
        //当前节点和下一个节点，值不同，则head的值是需要保留的，对head.next继续递归
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        } else {
            // 当前节点与下一个节点的值重复了，重复的值都不能要
            // 一直往下找，找到不重复的节点。返回堆不重复节点的递归结果
            ListNode notDup = head.next.next;
            while (notDup != null && notDup.val == head.val) {
                notDup = notDup.next;
            }
            return deleteDuplicates(notDup);
        }
    }
    //endregion

    //region    20230408    153. 寻找旋转排序数组中的最小值

    /**
     * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array
     *
     * @param nums 元素值 互不相同 的数组 nums
     * @return 找出并返回数组中的 最小元素
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
    //endregion

    //region    20230411    162. 寻找峰值

    /**
     * https://leetcode.cn/problems/find-peak-element/
     *
     * @param nums 整数数组 nums
     * @return 找到峰值元素并返回其索引
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

    //region    20230410    200. 岛屿数量

    /**
     * https://leetcode.cn/problems/number-of-islands/
     *
     * @param grid 一个由 '1'（陆地）和 '0'（水）组成的的二维网格 grid
     * @return 计算网格中岛屿的数量
     */
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfsGrid(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfsGrid(char[][] grid, int row, int col) {
        if (row >= grid.length || col >= grid[0].length || row < 0 || col < 0) {
            return;
        }

        if (grid[row][col] != '1') {
            return;
        }

        grid[row][col] = '2';
        dfsGrid(grid, row - 1, col);
        dfsGrid(grid, row + 1, col);
        dfsGrid(grid, row, col - 1);
        dfsGrid(grid, row, col + 1);
    }
    //endregion

    //region    20230410    209. 长度最小的子数组

    /**
     * https://leetcode.cn/problems/minimum-size-subarray-sum
     *
     * @param target 一个正整数 target
     * @param nums   含有 n 个正整数的数组 nums
     * @return 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0
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

    //region    20230413    572. 另一棵树的子树

    /**
     * https://leetcode.cn/problems/subtree-of-another-tree/
     *
     * @param root    二叉树 root
     * @param subRoot 二叉树 subRoot
     * @return 检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root, subRoot);
    }

    public boolean dfs(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        return check(root, subRoot) || dfs(root.right, subRoot) || dfs(root.left, subRoot);
    }

    public boolean check(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null || root.val != subRoot.val) {
            return false;
        }
        return check(root.left, subRoot.left) && check(root.right, subRoot.right);
    }
    //endregion

    //region    20230412    713. 乘积小于 K 的子数组

    /**
     * https://leetcode.cn/problems/subarray-product-less-than-k/
     *
     * @param nums 整数数组 nums
     * @param k    一个整数 k
     * @return 返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        //同样排除k为1的情况比如  [1,1,1] k=1
        if (k <= 1) {
            return 0;
        }
        int left = 0;
        int right = 0;
        //创建一个变量记录路上的乘积
        int mul = 1;
        //记录连续数组的组合个数
        int ans = 0;

        //用右指针遍历整个数组，每次循环右指针右移一次
        while (right < nums.length) {
            //记录乘积
            mul *= nums[right];
            //当大于等于k，左指针右移并把之前左指针的数除掉
            while (mul >= k) {
                mul /= nums[left];
                left++;
            }

            //每次右指针位移到一个新位置，应该加上 x 种数组组合：
            //  nums[right]
            //  nums[right-1], nums[right]
            //  nums[right-2], nums[right-1], nums[right]
            //  nums[left], ......, nums[right-2], nums[right-1], nums[right]
            //共有 right - left + 1 种
            ans += right - left + 1;

            //右指针右移
            right++;
        }
        return ans;
    }
    //endregion

    //region    20230412    844. 比较含退格的字符串

    /**
     * https://leetcode.cn/problems/backspace-string-compare
     *
     * @param s 字符串 s
     * @param t 字符串 t
     * @return 它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true
     */
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stackT = getStack(t);
        Stack<Character> stackS = getStack(s);
        while (!stackS.isEmpty() || !stackT.isEmpty()) {
            if (!stackS.isEmpty() && stackT.isEmpty()) {
                return false;
            }
            if (stackS.isEmpty() && !stackT.isEmpty()) {
                return false;
            }
            if (stackS.peek() != stackT.peek()) {
                return false;
            }
            stackS.pop();
            stackT.pop();
        }
        return true;
    }

    private Stack<Character> getStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                stack.push(s.charAt(i));
            }
            if (!stack.isEmpty() && s.charAt(i) == '#') {
                stack.pop();
            }
        }
        return stack;
    }
    //endregion

    //region    20230412    986. 区间列表的交集

    /**
     * https://leetcode.cn/problems/interval-list-intersections/
     *
     * @param firstList  由一些 闭区间 组成的列表，firstList
     * @param secondList 由一些 闭区间 组成的列表，secondList
     * @return 两个区间列表的交
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList == null || firstList.length == 0) {
            return firstList;
        }
        if (secondList == null || secondList.length == 0) {
            return secondList;
        }
        List<int[]> res = new ArrayList<>();
        int firstIndex = 0;
        int secondIndex = 0;
        while (firstIndex < firstList.length && secondIndex < secondList.length) {
            int lo = Math.max(firstList[firstIndex][0], secondList[secondIndex][0]);
            int hi = Math.min(firstList[firstIndex][1], secondList[secondIndex][1]);
            if (lo <= hi) {
                res.add(new int[]{lo, hi});
            }
            if (firstList[firstIndex][1] < secondList[secondIndex][1]) {
                firstIndex++;
            } else {
                secondIndex++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
    //endregion
}
