package StudyPlan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author yyb
 * leetcode_studyplan_Lcof
 * leetcode 学习计划 剑指offer
 */
public class TargetLcof {

    //region    20230308    剑指 Offer 50. 第一个只出现一次的字符

    /**
     * https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
     *
     * @param s 字符串 s
     * @return 返回字符串第一个只出现一次的字符
     */
    public char firstUniqChar(String s) {
        HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (frequency.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }
    //endregion

    //region    20230307    剑指 Offer 03. 数组中重复的数字

    /**
     * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
     *
     * @param nums 数组 nums
     * @return 找出数组中的重复数字
     */
    public int findRepeatNumber(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                return num;
            } else {
                hashMap.put(num, 1);
            }
        }
        return -1;
    }
    //endregion

    //region    20230306    剑指 Offer 05. 替换空格

    /**
     * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
     *
     * @param s 字符串 s
     * @return 字符串 s 中的每个空格替换成"%20"
     */
    public String replaceSpace(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                stringBuilder.append(s.charAt(i));
            } else {
                stringBuilder.append("%20");
            }
        }
        return stringBuilder.toString();
    }
    //endregion

    //region    20230305    剑指 Offer 06. 从尾到头打印链表
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
     *
     * @param head 链表的头节点
     * @return 尾到头反过来返回每个节点的值（用数组返回）
     */
    public int[] reversePrint(ListNode head) {
        LinkedList<Integer> stack = new LinkedList<>();
        while (head != null) {
            stack.addLast(head.val);
            head = head.next;
        }
        int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = stack.removeLast();
        }
        return res;
    }
    //endregion

    //region    20230305    剑指 Offer 09. 用两个栈实现队列

    /**
     * https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
     */
    static class CQueue {
        //两个栈，一个出栈，一个入栈
        private final Stack<Integer> stack1;
        private final Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            } else {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.isEmpty() ? -1 : stack2.pop();
            }
        }
    }

    //endregion

    //region    20230308    剑指 Offer 11. 旋转数组的最小数字

    /**
     * https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
     *
     * @param numbers 旋转数组 nums
     * @return 旋转数组 nums 中的最小数字
     */
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                right -= 1;
            }
        }
        return numbers[left];
    }
    //endregion

    //region    20230305    剑指 Offer 24. 反转链表

    /**
     * https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/
     *
     * @param head 一个链表的头节点
     * @return 反转该链表并输出反转后链表的头节点
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

    //region    20230305    剑指 Offer 30. 包含min函数的栈

    /**
     * https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/
     */
    static class MinStack {

        private final Stack<Integer> xStack;
        private final Stack<Integer> minStack;

        public MinStack() {
            xStack = new Stack<>();
            minStack = new Stack<>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            xStack.push(x);
            minStack.push(Math.min(minStack.peek(), x));
        }

        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        public int top() {
            return xStack.peek();
        }

        public int min() {
            return minStack.peek();
        }
    }
    //endregion

    //region    20230305    剑指 Offer 35. 复杂链表的复制
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    HashMap<Node, Node> cachedNode = new HashMap<>();

    /**
     * https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/
     *
     * @param head 复杂链表
     * @return 复制一个复杂链表
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!cachedNode.containsKey(head)) {
            Node headNew = new Node(head.val);
            cachedNode.put(head, headNew);
            headNew.next = copyRandomList(head.next);
            headNew.random = copyRandomList(head.random);
        }
        return cachedNode.get(head);
    }
    //endregion

    //region    20230306    剑指 Offer 58 - II. 左旋转字符串

    /**
     * https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
     *
     * @param s 字符串 s
     * @param n 数字 n
     * @return 字符串左旋后的字符串
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
    //endregion

    //region    20230307    剑指 Offer 53 - I. 在排序数组中查找数字 I

    /**
     * https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
     *
     * @param nums   排序数组 nums
     * @param target 目标值
     * @return 目标值的个数
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                int tempLeft = mid - 1;
                int tempRight = mid + 1;
                while (tempRight <= nums.length - 1 && nums[tempRight] == target) {
                    tempRight++;
                }
                while (tempLeft >= 0 && nums[tempLeft] == target) {
                    tempLeft--;
                }
                return tempRight - tempLeft - 1;
            }
        }
        return 0;
    }
    //endregion

    //region    20230307    剑指 Offer 53 - II. 0～n-1中缺失的数字

    /**
     * https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/
     *
     * @param nums 0-n-1 数组
     * @return 数组中缺失的数字
     */
    public int missingNumber(int[] nums) {
        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] - mid == 0) {
                left = mid + 1;
            } else if (nums[mid] - mid == 1) {
                right = mid - 1;
            }
        }
        return nums[left] - left == 0 ? left + 1 : left;
    }
    //endregion

    public static void main(String[] args) {
        new TargetLcof().missingNumber(new int[]{0, 1, 3});
    }
}
