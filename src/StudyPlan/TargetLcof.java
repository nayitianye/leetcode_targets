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

    //region    20230306    剑指 Offer 05. 替换空格

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * 示例 1：
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     * <p>
     * 限制：
     * 0 <= s 的长度 <= 10000
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
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * <p>
     * 示例 1：
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     * <p>
     * 限制：
     * 0 <= 链表长度 <= 10000
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
     * 用两个栈实现一个队列。队列的声明如下，
     * 请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。
     * (若队列中没有元素，deleteHead 操作返回 -1 )
     * 示例 1：
     * 输入：
     * ["CQueue","appendTail","deleteHead","deleteHead","deleteHead"]
     * [[],[3],[],[],[]]
     * 输出：[null,null,3,-1,-1]
     * 示例 2：
     * 输入：
     * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
     * [[],[],[5],[2],[],[]]
     * 输出：[null,-1,null,null,5,2]
     * 提示：
     * 1 <= values <= 10000
     * 最多会对 appendTail、deleteHead 进行 10000 次调用
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

    //region    20230305    剑指 Offer 24. 反转链表

    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * <p>
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * <p>
     * 限制：
     * 0 <= 节点个数 <= 5000
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
     * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
     * 还有一个 random 指针指向链表中的任意节点或者 null。
     * <p>
     * 示例 1：
     * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
     * 示例 2：
     * 输入：head = [[1,1],[2,1]]
     * 输出：[[1,1],[2,1]]
     * 示例 3：
     * 输入：head = [[3,null],[3,0],[3,null]]
     * 输出：[[3,null],[3,0],[3,null]]
     * 示例 4：
     * 输入：head = []
     * 输出：[]
     * 解释：给定的链表为空（空指针），因此返回 null。
     * <p>
     * 提示：
     * -10000 <= Node.val <= 10000
     * Node.random 为空（null）或指向链表中的节点。
     * 节点数目不超过 1000 。
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
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"bcdedit"和数字2，该函数将返回左旋转两位得到的结果"definable"。
     * 示例 1：
     * 输入: s = "bcdedit", k = 2
     * 输出: "definable"
     * 示例 2：
     * 输入: s = "closemouthed", k = 6
     * 输出: "closemouthed"
     * <p>
     * 限制：
     * 1 <= k < s.length <= 10000
     *
     * @param s 字符串 s
     * @param n 数字 n
     * @return 字符串左旋后的字符串
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
    //endregion

    public static void main(String[] args) {
        //09. 用两个栈实现队列
        CQueue queue = new CQueue();
        queue.appendTail(1);
        queue.appendTail(2);
        System.out.println(queue.deleteHead());
        //30. 包含min函数的栈
        MinStack minStack = new MinStack();
        minStack.push(10);
        minStack.push(2);
        minStack.push(43);
        System.out.println(minStack.min());
        System.out.println(minStack.top());
        minStack.pop();
        //06. 从尾到头打印链表
        ListNode listNode = new ListNode(11);
        listNode.next = new ListNode(8);
        listNode.next.next = new ListNode(22);
        System.out.println(Arrays.toString(new TargetLcof().reversePrint(listNode)));
        //24. 反转链表
        ListNode reverseList = new TargetLcof().reverseList(listNode);
        System.out.println(reverseList.toString());
        //35. 复杂链表的复制
        Node head = new Node(11);
        head.next = new Node(223);
        head.random = new Node(22);
        Node res = new TargetLcof().copyRandomList(head);
        System.out.println(res.toString());
        //05. 替换空格
        System.out.println(new TargetLcof().replaceSpace("aaa a a   a   aaa"));
        //58 - II. 左旋转字符串
        System.out.println(new TargetLcof().reverseLeftWords("abducens",2));
    }
}
