package design;
/**
 * leetcode_tag_design
 * leetcode 标签 设计数据结构
 * 循坏双端队列的实现——链表实现
 */
public class MyCircularDeque {
    class ListNode {
        private ListNode pre;
        private ListNode next;
        private int val;

        public ListNode(int value) {
            this.val = value;
            pre = null;
            next = null;
        }
    }

    private ListNode head = new ListNode(-1);
    private ListNode tail = new ListNode(-1);
    private int size = 0;
    private int capcity = 0;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        head.next = tail;
        tail.pre = head;
        size = 0;
        this.capcity = k;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (capcity <= size) {
            return false;
        }
        ListNode list = new ListNode(value);
        list.next = head.next;
        list.next.pre = list;
        list.pre = head;
        head.next = list;
        size++;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (capcity <= size) {
            return false;
        }
        ListNode node = new ListNode(value);
        node.pre = tail.pre;
        node.next = tail;
        node.pre.next = node;
        tail.pre = node;
        size++;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (size == 0) {
            return false;
        }
        head.next = head.next.next;
        head.next.pre = head;
        size--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (size == 0) {
            return false;
        }
        tail.pre = tail.pre.pre;
        tail.pre.next = tail;
        size--;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        return head.next.val;
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        return tail.pre.val;
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return size == capcity;
    }
}
