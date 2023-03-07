package StudyPlan;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author yyb
 * leetcode_studyplan_LeetCode 75
 * leetcode 学习计划 LeetCode 75
 */
public class TargetLeetCode75 {

    //region    20230307    21. 合并两个有序链表

    /**
     * https://leetcode.cn/problems/merge-two-sorted-lists/
     *
     * @param list1 有序链表 list1
     * @param list2 有序链表 list1
     * @return 返回合并后的有序链表
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

    public static class ListNode {
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
    //endregion

    //region    20230306    205. 同构字符串

    /**
     * https://leetcode.cn/problems/isomorphic-strings/
     *
     * @param s 字符串 s
     * @param t 字符串 t
     * @return 判断它们是否是同构的
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> hashMapS = new HashMap<>();
        HashMap<Character, Character> hashMapT = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char chs = s.charAt(i);
            char cht = t.charAt(i);
            if ((hashMapT.containsKey(chs) && hashMapT.get(chs) != cht) ||
                    (hashMapS.containsKey(cht) && hashMapS.get(cht) != chs)) {
                return false;
            }
            hashMapT.put(chs, cht);
            hashMapS.put(cht, chs);
        }
        return true;
    }
    //endregion

    //region    20230307    206. 反转链表

    /**
     * https://leetcode.cn/problems/reverse-linked-list/
     *
     * @param head 链表 head
     * @return 反转链表 heed 并返回
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

    //region    20230306    392. 判断子序列

    /**
     * https://leetcode.cn/problems/is-subsequence/
     *
     * @param s 字符串 s
     * @param t 字符串 t
     * @return 判断 s 是否为 t 的子序列
     */
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == m;
    }
    //endregion

    //region    20230305    724. 寻找数组的中心下标

    /**
     * https://leetcode.cn/problems/find-pivot-index/
     *
     * @param nums 整数数组 nums
     * @return 计算数组的 中心下标
     */
    public int pivotIndex(int[] nums) {
        int[] pivot = new int[nums.length];
        pivot[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pivot[i] = pivot[i - 1] + nums[i];
        }
        int left = 0;
        for (int i = 0; i < pivot.length; i++) {
            if (i != 0) {
                left = pivot[i - 1];
            }
            if (pivot[nums.length - 1] - pivot[i] == left) {
                return i;
            }
        }
        return -1;
    }
    //endregion

    //region    20230308    876. 链表的中间结点

    /**
     * https://leetcode.cn/problems/middle-of-the-linked-list/
     * @param head  链表 head
     * @return  返回链表 head 的中间结点
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

    //region    20230305    1480. 一维数组的动态和

    /**
     * https://leetcode.cn/problems/running-sum-of-1d-array/
     *
     * @param nums 数组 nums
     * @return 数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) . 请返回 nums 的动态和
     */
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 1) {
            return nums;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        return nums;
    }
    //endregion


    public static void main(String[] args) {

    }
}


