package StudyPlan;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author yyb
 * leetcode_studyplan_algorithms_Beginners
 * leetcode 学习计划 算法入门
 */
public class TargetAlgorithmsBeginner {

    //region    自定义数据结构
    public class Node {
        public int val;
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
     * @param s1    字符串 s1
     * @param s2    字符串 s2
     * @return  判断 s2 是否包含 s1 的排列
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

}
