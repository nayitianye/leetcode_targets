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

    //region    20230418    5. 最长回文子串

    /**
     * https://leetcode.cn/problems/longest-palindromic-substring
     *
     * @param s 一个字符串 s
     * @return 找到 s 中最长的回文子串
     */
    public String longestPalindrome(String s) {
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

    //region    20230419    40. 组合总和 II

    /**
     * https://leetcode.cn/problems/combination-sum-ii
     *
     * @param candidates 一个候选人编号的集合 candidates
     * @param target     一个目标数 target
     * @return 找出 candidates 中所有可以使数字和为 target 的组合
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        findConbinationSum2(target, 0, new Stack<>(), candidates, res);
        return res;
    }

    private void findConbinationSum2(int target, int index, Stack<Integer> stack, int[] candidates, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(stack));
        }
        for (int i = index; i < candidates.length && target - candidates[i] >= 0; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            stack.push(candidates[i]);
            findConbinationSum2(target - candidates[i], i + 1, stack, candidates, res);
            stack.pop();
        }
    }
    //endregion

    //region    20230418    45. 跳跃游戏 II

    /**
     * https://leetcode.cn/problems/jump-game-ii/
     *
     * @param nums 长度为 n 的 0 索引整数数组 nums
     * @return 返回到达 nums[n - 1] 的最小跳跃次数
     */
    public int jump(int[] nums) {
        //下一次跳的位置
        int end = 0;
        //最大的跳跃距离
        int maxPosition = 0;
        //跳跃次数
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
    //endregion

    //region    20230418    47. 全排列 II

    /**
     * https://leetcode.cn/problems/permutations-ii/
     *
     * @param nums 可包含重复数字的序列 nums
     * @return 按任意顺序 返回所有不重复的全排列
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> perm = new ArrayList<>();
        visPermuteUnique = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, res, 0, perm);
        return res;
    }

    public void backtrack(int[] nums, List<List<Integer>> res, int index, List<Integer> perm) {
        if (index == nums.length) {
            res.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visPermuteUnique[i] || (i > 0 && nums[i] == nums[i - 1] && !visPermuteUnique[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            visPermuteUnique[i] = true;
            backtrack(nums, res, index + 1, perm);
            visPermuteUnique[i] = false;
            perm.remove(index);
        }
    }


    public boolean[] visPermuteUnique;
    //endregion

    //region    20230418    55. 跳跃游戏

    /**
     * https://leetcode.cn/problems/jump-game/
     *
     * @param nums 非负整数数组 nums
     * @return 判断你是否能够到达最后一个下标
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        //前n-1个元素能够跳到的最远的距离
        int mostLength = 0;
        for (int i = 0; i <= mostLength; i++) {
            //第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            //更新最远距离
            mostLength = Math.max(mostLength, temp);
            //如果最远距离已经大于或等于最后一个元素的下标，则说明能跳过去，退出，减少循环
            if (mostLength >= nums.length - 1) {
                return true;
            }
        }
        //最远距离k不再改变，且没有到末尾元素
        return false;
    }
    //endregion

    //region    20230418    62. 不同路径

    /**
     * https://leetcode.cn/problems/unique-paths
     *
     * @param m m 行
     * @param n n 列
     * @return 从左上到右下的步骤数
     */
    public int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                    continue;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
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

    //region    20230418    78. 子集

    /**
     * https://leetcode.cn/problems/subsets/
     *
     * @param nums 一个整数数组 nums ，数组中的元素互不相同
     * @return 返回该数组所有可能的子集（幂集）
     */
    public List<List<Integer>> subsets(int[] nums) {
        dfsSubSets(0, nums);
        return resSubSets;
    }

    public void dfsSubSets(int cur, int[] nums) {
        if (cur == nums.length) {
            resSubSets.add(new ArrayList<>(tempList));
            return;
        }
        tempList.add(nums[cur]);
        dfsSubSets(cur + 1, nums);
        tempList.remove(tempList.size() - 1);
        dfsSubSets(cur + 1, nums);
    }

    List<List<Integer>> resSubSets = new ArrayList<>();
    List<Integer> tempList = new ArrayList<>();
    //endregion

    //region    20230419    79. 单词搜索

    /**
     * https://leetcode.cn/problems/word-search/
     *
     * @param board 给定一个 m x n 二维字符网格 board
     * @param word  一个字符串单词 word
     * @return 如果 word 存在于网格中，返回 true ；否则，返回 false
     */
    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
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

    //region    20230418    90. 子集 II

    /**
     * https://leetcode.cn/problems/subsets-ii
     *
     * @param nums 一个整数数组 nums ，其中可能包含重复元素
     * @return 返回该数组所有可能的子集（幂集）
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfsSubsetsWithDup(false, 0, nums);
        return resSubsetWidthDup;
    }

    public void dfsSubsetsWithDup(boolean choosePre, int cur, int[] nums) {
        if (cur == nums.length) {
            resSubsetWidthDup.add(new ArrayList<>(tempSubsetsWithDup));
            return;
        }
        dfsSubsetsWithDup(false, cur + 1, nums);
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        tempSubsetsWithDup.add(nums[cur]);
        dfsSubsetsWithDup(true, cur + 1, nums);
        tempSubsetsWithDup.remove(tempSubsetsWithDup.size() - 1);
    }

    List<Integer> tempSubsetsWithDup = new ArrayList<>();
    List<List<Integer>> resSubsetWidthDup = new ArrayList<>();
    //endregion

    //region    20230419    91. 解码方法

    /**
     * https://leetcode.cn/problems/decode-ways/
     *
     * @param s 一个只含数字的 非空 字符串 s
     * @return 计算并返回 解码 方法的 总数
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0')) <= 26) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
    //endregion

    //region    20230419    130. 被围绕的区域

    /**
     * https://leetcode.cn/problems/surrounded-regions/
     *
     * @param board 一个 m x n 的矩阵 board
     */
    public void solve(char[][] board) {
        int n = board.length;
        if (n == 0) {
            return;
        }
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0, n, m);
            dfs(board, i, m - 1, n, m);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i, n, m);
            dfs(board, n - 1, i, n, m);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y, n, m);
        dfs(board, x - 1, y, n, m);
        dfs(board, x, y + 1, n, m);
        dfs(board, x, y - 1, n, m);
    }
    //endregion

    //region    20230419    139. 单词拆分

    /**
     * https://leetcode.cn/problems/word-break
     *
     * @param s        一个字符串 s
     * @param wordDict 一个字符串列表 wordDict 作为字典
     * @return 判断是否可以利用字典中出现的单词拼接出 s
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
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

    //region    20230418    213. 打家劫舍 II

    /**
     * https://leetcode.cn/problems/house-robber-ii/
     *
     * @param nums 给定一个代表每个房屋存放金额的非负整数数组 nums
     * @return 计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额
     */
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(maxRob(0, nums.length - 1, nums), maxRob(1, nums.length, nums));
    }

    public int maxRob(int start, int end, int[] nums) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
    //endregion

    //region    20230418    413. 等差数列划分

    /**
     * https://leetcode.cn/problems/arithmetic-slices/
     *
     * @param nums 一个整数数组 nums
     * @return 返回数组 nums 中所有为等差数组的 子数组 个数
     */
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }
        int res = 0;
        //存储增量
        // 1, 2, 3, 6, 8, 10 原数组
        // 0  0  1  0  0  1 增量
        int[] dp = new int[nums.length];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;//确定增量
                res += dp[i];//添加增量
            }
        }
        return res;
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

    //region    20230419    797. 所有可能的路径

    /**
     * https://leetcode.cn/problems/all-paths-from-source-to-target
     * @param graph  有 n 个节点的 有向无环图 graph
     * @return  找出所有从节点 0 到节点 n-1 的路径并输出
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        stack.offerLast(0);
        dfs(graph, 0, graph.length - 1);
        return ans;
    }

    public void dfs(int[][] graph, int x, int n) {
        if (x == n) {
            ans.add(new ArrayList<Integer>(stack));
            return;
        }
        for (int y : graph[x]) {
            stack.offerLast(y);
            dfs(graph, y, n);
            stack.pollLast();
        }
    }

    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    Deque<Integer> stack = new ArrayDeque<Integer>();
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

    //region    20230419    1091. 二进制矩阵中的最短路径

    /**
     * https://leetcode.cn/problems/shortest-path-in-binary-matrix/
     *
     * @param grid 一个 n x n 的二进制矩阵 grid
     * @return 矩阵中最短 畅通路径 的长度
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] directions = {{0, 1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {-1, 0}, {-1, -1}, {-1, 1}};
        int row = grid.length, col = grid[0].length;
        if (grid[0][0] == 1 || grid[row - 1][col - 1] == 1) {
            return -1;
        }
        Queue<int[]> pos = new LinkedList<>();
        grid[0][0] = 1;
        pos.add(new int[]{0, 0});
        while (!pos.isEmpty() && grid[row - 1][col - 1] == 0) {
            int[] xy = pos.remove();
            int preLength = grid[xy[0]][xy[1]];
            for (int i = 0; i < 8; i++) {
                int newx = xy[0] + directions[i][0];
                int newy = xy[1] + directions[i][1];
                if (inGrid(newx, newy, row, col) && grid[newx][newy] == 0) {
                    pos.add(new int[]{newx, newy});
                    grid[newx][newy] = preLength + 1;
                }
            }
        }
        return grid[row - 1][col - 1] == 0 ? -1 : grid[row - 1][col - 1];
    }

    private boolean inGrid(int x, int y, int row, int col) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
    //endregion
}
