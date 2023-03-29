package StudyPlan;

import java.util.*;

/**
 * @author yyb
 * leetcode_studyplan_algorithms_Beginners
 * leetcode 学习计划 算法入门
 */
public class TargetAlgorithmsBeginner {

    //region    自定义数据结构
    public class Node {
        public int val;

        public Node left;
        public Node right;
        public Node next;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 单链表
     */
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

    /**
     * TreeNode 二叉树
     */
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

    //region    20230324    3. 无重复字符的最长子串

    /**
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     *
     * @param s 字符串 s
     * @return 找出其中不含有重复字符的 最长子串 的长度
     */
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        HashSet<Character> hashSet = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为-1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                //左指针向右移动一格，移除一个字符
                hashSet.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !hashSet.contains(s.charAt(rk + 1))) {
                hashSet.add(s.charAt(rk + 1));
                ++rk;
            }
            //第 i 到 rk 个字符是一个极长的无重复字符字串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
    //endregion

    //region    20230322    19. 删除链表的倒数第 N 个结点

    /**
     * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
     *
     * @param head 头结点 head
     * @param n    删除倒数第 n 个结点
     * @return 返回删除倒数第 n 个结点的头结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
    //endregion

    //region    20230228    21. 合并两个有序链表

    /**
     * https://leetcode.cn/problems/merge-two-sorted-lists
     *
     * @param list1 链表 list1
     * @param list2 链表 list2
     * @return 返回两个链表的合并链表
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
    //endregion

    //region    20230319    35. 搜索插入位置

    /**
     * https://leetcode.cn/problems/search-insert-position/
     *
     * @param nums   一个排序数组 nums
     * @param target 一个目标值 target
     * @return 数组中找到目标值，并返回其索引
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        if (nums[0] > target) {
            return 0;
        }
        if (nums[nums.length - 1] < target) {
            return nums.length;
        }
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[right] < target ? right + 1 : right;
    }
    //endregion

    //region    20230329    46. 全排列

    /**
     * https://leetcode.cn/problems/permutations/
     *
     * @param nums 不含重复数字的数组 num
     * @return 返回其 所有可能的全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        //所有数都填完了
        if (first == n) {
            res.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            //动态维护数组
            Collections.swap(output, first, i);
            //继续递归填下一个数
            backtrack(n, output, res, first + 1);
            //撤销操作
            Collections.swap(output, first, i);
        }
    }
    //endregion

    //region    20230330    70. 爬楼梯
    HashMap<Integer, Integer> hashMapClimbStairs = new HashMap<>();

    /**
     * https://leetcode.cn/problems/climbing-stairs/
     *
     * @param n n 阶楼梯
     * @return 多少种方法
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            if (hashMapClimbStairs.containsKey(n)) {
                return hashMapClimbStairs.get(n);
            } else {
                hashMapClimbStairs.put(n, climbStairs(n - 1) + climbStairs(n - 2));
                return hashMapClimbStairs.get(n);
            }
        }
    }
    //endregion

    //region    20230329    77. 组合

    /**
     * https://leetcode.cn/problems/combinations/
     *
     * @param n 整数 n
     * @param k 整数 k
     * @return 回范围 [1, n] 中所有可能的 k 个数的组合
     */
    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k);
        return ans;
    }

    public void dfs(int cur, int n, int k) {
        //剪枝；temp 长度加上区间【cur,n】的长度小于 k, 不可能构造出长度为 k 的temp;
        if (temp.size() + (n - cur + 1) < k) {
            return;
        }
        // 记录合法的答案
        if (temp.size() == k) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        //考虑选择当前位置
        temp.add(cur);
        dfs(cur + 1, n, k);
        temp.remove(temp.size() - 1);
        dfs(cur + 1, n, k);
    }

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    //endregion

    //region    20230328    116. 填充每个节点的下一个右侧节点指针

    /**
     * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/description/
     *
     * @param root 完美二叉树 root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {
            // 记录当前队列大小
            int size = queue.size();
            // 遍历这一层的所有节点
            for (int i = 0; i < size; i++) {

                // 从队首取出元素
                Node node = queue.poll();

                // 连接
                if (i < size - 1) {
                    node.next = queue.peek();
                }

                // 拓展下一层节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        // 返回根节点
        return root;
    }
    //endregion

    //region    20230330    120. 三角形最小路径和

    /**
     * https://leetcode.cn/problems/triangle/
     *
     * @param triangle 三角形 triangle
     * @return 自顶向下的最小路径和
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            dp[i] = dp[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
            }
            dp[0] += triangle.get(i).get(0);
        }
        int minTotal = dp[0];
        for (int i = 1; i < triangle.size(); i++) {
            minTotal = Math.min(minTotal, dp[i]);
        }
        return minTotal;
    }
    //endregion

    //region    20230321    167. 两数之和 II - 输入有序数组

    /**
     * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/
     *
     * @param numbers 整数数组 numbers
     * @param target  目标数 target
     * @return 长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2
     */
    public int[] twoSum(int[] numbers, int target) {
        //二分法
        for (int i = 0; i < numbers.length; i++) {
            int left = i + 1, right = numbers.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }
    //endregion

    //region    20230320    189. 轮转数组

    /**
     * https://leetcode.cn/problems/rotate-array/
     *
     * @param nums 整数数组 nums
     * @param k    数组中的元素向右轮转 k 个位置，其中 k 是非负数
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    //endregion

    //region    20230330    198. 打家劫舍

    /**
     * https://leetcode.cn/problems/house-robber/
     *
     * @param nums 一个代表每个房屋存放金额的非负整数数
     * @return 计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
    // endregion

    //region    20230328    206. 反转链表

    /**
     * https://leetcode.cn/problems/reverse-linked-list
     *
     * @param head 单链表的头节点 head
     * @return 反转链表，并返回反转后的链表
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    //endregion

    //region    20230322    217. 存在重复元素

    /**
     * https://leetcode.cn/problems/contains-duplicate/
     *
     * @param nums 整数数组 nums
     * @return 如果任一数值在数组中出现至少两次 ，返回 true ；如果数组中每个元素互不相同 ，返回 false 。
     */
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                return true;
            } else {
                hashMap.put(num, 1);
            }
        }
        return false;
    }
    //endregion

    //region    20230322    242. 有效的字母异位词

    /**
     * https://leetcode.cn/problems/valid-anagram/
     *
     * @param s 字符串 s
     * @param t 字符串 t
     * @return 判断 t 是否是 s 的字母异位词
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            } else {
                hashMap.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (hashMap.containsKey(t.charAt(i))) {
                if (hashMap.get(t.charAt(i)) == 1) {
                    hashMap.remove(t.charAt(i));
                } else {
                    hashMap.put(t.charAt(i), hashMap.get(t.charAt(i)) - 1);
                }
            } else {
                return false;
            }
        }
        return hashMap.isEmpty();
    }
    //endregion

    //region    20230319    278. 第一个错误的版本

    /**
     * https://leetcode.cn/problems/first-bad-version/
     *
     * @param n n 个版本 [1, 2, ..., n]
     * @return 导致之后所有版本出错的第一个错误的版本
     */
    public int firstBadVersion(int n) {
        int first = 0, last = n, mid = 0;
        while (first < last) {
            mid = first + (last - first) / 2;
            if (isBadVersion(mid)) {
                last = mid;
            } else {
                first = mid + 1;
            }
        }
        return isBadVersion(mid) ? mid : mid + 1;
    }

    public boolean isBadVersion(int n) {
        return n == 10;
    }
    //endregion

    //region    20230321    283. 移动零

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

    //region    20230322    344. 反转字符串

    /**
     * https://leetcode.cn/problems/reverse-string/
     *
     * @param s 字符数组 s
     */
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char ch = s[left];
            s[left] = s[right];
            s[right] = ch;
            left++;
            right--;
        }
    }
    //endregion

    //region    20230328    542. 01 矩阵

    /**
     * https://leetcode.cn/problems/01-matrix/
     *
     * @param mat 由 0 和 1 组成的矩阵 mat
     * @return 请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
     */
    public int[][] updateMatrix(int[][] mat) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = mat.length, n = mat[0].length;
        int[][] dist = new int[m][n];
        boolean[][] seen = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        // 将所有的 0 添加进初始队列中
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    seen[i][j] = true;
                }
            }
        }
        // 广度优先搜索
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int i = cell[0], j = cell[1];
            for (int k = 0; k < 4; k++) {
                int ni = i + dirs[k][0];
                int nj = j + dirs[k][1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && !seen[ni][nj]) {
                    dist[ni][nj] = dist[i][j] + 1;
                    queue.offer(new int[]{ni, nj});
                    seen[ni][nj] = true;
                }
            }
        }
        return dist;
    }
    //endregion

    //region    20230322    557. 反转字符串中的单词 III

    /**
     * https://leetcode.cn/problems/reverse-words-in-a-string-iii/
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            res.append(reverseWord(strs[i]));
            if (i != strs.length - 1) {
                res.append(" ");
            }
        }
        return res.toString();
    }

    public String reverseWord(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            res.append(s.charAt(i));
        }
        return res.toString();
    }
    //endregion

    //region    20230324    567. 字符串的排列

    /**
     * https://leetcode.cn/problems/permutation-in-string/
     *
     * @param s1 字符串 s1
     * @param s2 字符串 s2
     * @return 判断 s2 是否包含 s1 的排列
     */
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            --cnt[s1.charAt(i) - 'a'];
        }
        int left = 0;
        for (int right = 0; right < m; right++) {
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            while (cnt[x] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }
    //endregion

    //region    20230327    617. 合并二叉树

    /**
     * https://leetcode.cn/problems/merge-two-binary-trees/
     *
     * @param root1 二叉树： root1
     * @param root2 二叉树： root1
     * @return 返回合并后的二叉树
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);
        return merged;
    }
    //endregion

    //region    20230325    695. 岛屿的最大面积

    /**
     * https://leetcode.cn/problems/max-area-of-island/
     *
     * @param grid 大小为 m x n 的二进制矩阵 grid
     * @return 计算并返回 grid 中最大的岛屿面积
     */
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int tempArea = 0;
                if (grid[i][j] == 1) {
                    tempArea = AreaOfIsLand(grid, i, j);
                }
                maxArea = Math.max(tempArea, maxArea);
            }
        }
        return maxArea;
    }

    public int AreaOfIsLand(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return 0;
        }
        if (grid[row][col] != 1) {
            return 0;
        }
        grid[row][col] = 2;
        return 1 + AreaOfIsLand(grid, row - 1, col)
                + AreaOfIsLand(grid, row + 1, col)
                + AreaOfIsLand(grid, row, col + 1)
                + AreaOfIsLand(grid, row, col - 1);
    }
    //endregion

    //region    20230319    704. 二分查找

    /**
     * https://leetcode.cn/problems/binary-search/
     *
     * @param nums   一个 n 个元素有序的（升序）整型数组 nums
     * @param target 一个目标值 target
     * @return 一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    //endregion

    //region    20230325    733. 图像渲染

    /**
     * https://leetcode.cn/problems/flood-fill/
     *
     * @param image m x n 的二维整数数组表示的图画 image
     * @param sr    整数 sr
     * @param sc    整数 sc
     * @param color 颜色 color
     * @return 像素 image[sr][sc] 开始对图像进行 上色填充
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        floodFill(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    public void floodFill(int[][] image, int sr, int sc, int newColor, int oldColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != oldColor || newColor == oldColor) {
            return;
        }
        image[sr][sc] = newColor;
        floodFill(image, sr + 1, sc, newColor, oldColor);
        floodFill(image, sr - 1, sc, newColor, oldColor);
        floodFill(image, sr, sc + 1, newColor, oldColor);
        floodFill(image, sr, sc - 1, newColor, oldColor);
    }
    //endregion

    //region    20230329    784. 字母大小写全排列

    /**
     * https://leetcode.cn/problems/letter-case-permutation
     *
     * @param s 字符串 s
     * @return 通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串,返回 所有可能得到的字符串集合
     */
    public List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        dfsPermutation(s.toCharArray(), 0, ans);
        return ans;
    }

    public void dfsPermutation(char[] arr, int pos, List<String> res) {
        while (pos < arr.length && Character.isDigit(arr[pos])) {
            pos++;
        }
        if (pos == arr.length) {
            res.add(new String(arr));
            return;
        }
        arr[pos] ^= 32;
        dfsPermutation(arr, pos + 1, res);
        arr[pos] ^= 32;
        dfsPermutation(arr, pos + 1, res);
    }
    //endregion

    //region    20230323    876. 链表的中间结点

    /**
     * https://leetcode.cn/problems/middle-of-the-linked-list/
     *
     * @param head 链表 head
     * @return 返回链表 head 的中间结点
     */
    public ListNode middleNode(ListNode head) {
        int nums = 0;
        ListNode listNode = head;
        while (listNode != null) {
            listNode = listNode.next;
            nums++;
        }
        int mid = nums / 2;
        while (mid != 0) {
            head = head.next;
            mid--;
        }
        return head;
    }
    //endregion

    //region    20230320    977. 有序数组的平方

    /**
     * https://leetcode.cn/problems/squares-of-a-sorted-array/
     *
     * @param nums 整数数组 nums
     * @return 返回每个数字的平方组成的新数组，要求也按非递减顺序排序
     */
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                res[pos] = nums[i] * nums[i];
                i++;
            } else {
                res[pos] = nums[j] * nums[j];
                j--;
            }
            pos--;
        }
        return res;
    }
    //endregion

    //region    20230328    994. 腐烂的橘子

    /**
     * https://leetcode.cn/problems/rotting-oranges/
     *
     * @param grid 定的 m x n 网格 grid
     * @return 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数.
     */
    public int orangesRotting(int[][] grid) {
        int[] dr = new int[]{-1, 0, 1, 0};
        int[] dc = new int[]{0, -1, 0, 1};
        int row = grid.length, col = grid[0].length;
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    int code = i * col + j;
                    queue.add(code);
                    map.put(code, 0);
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int code = queue.remove();
            int r = code / col, c = code % col;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (0 <= nr && nr < row && 0 <= nc && nc < col && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    int ncode = nr * col + nc;
                    queue.add(ncode);
                    map.put(ncode, map.get(code) + 1);
                    ans = map.get(ncode);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return ans;
    }
    //endregion

}
