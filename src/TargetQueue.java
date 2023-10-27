import java.util.*;

/**
 * leetcode_tag_queue
 * leetcode 标签 队列
 * 346、353、363、582、621、641、622、862、933
 */

public class TargetQueue {

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
    //endregion


    //region    20191017    346. 数据流中的移动平均值

    /**
     * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，
     * 计算其所有整数的移动平均值。
     * 示例:
     * MovingAverage m = new MovingAverage(3);
     * m.next(1) = 1
     * m.next(10) = (1 + 10) / 2
     * m.next(3) = (1 + 10 + 3) / 3
     * m.next(5) = (10 + 3 + 5) / 3
     */
    class MovingAverage {
        private int size = 0;
        private double sum = 0.0;
        private Queue<Integer> queue = new LinkedList<>();

        public MovingAverage(int size) {
            this.size = size;
        }

        public double next(int val) {
            sum += val;
            queue.offer(val);
            if (queue.size() > this.size) {
                sum -= queue.remove();
            }
            return sum / queue.size();
        }
    }
    //endregion

    //region    20191017    582. 杀死进程

    /**
     * 给 n 个进程，每个进程都有一个独一无二的 PID （进程编号）和它的 PPID （父进程编号）。
     * 每一个进程只有一个父进程，但是每个进程可能会有一个或者多个孩子进程。
     * 它们形成的关系就像一个树状结构。只有一个进程的 PPID 是 0 ，
     * 意味着这个进程没有父进程。
     * 所有的 PID 都会是唯一的正整数。
     * 我们用两个序列来表示这些进程，第一个序列包含所有进程的 PID ，
     * 第二个序列包含所有进程对应的 PPID。
     * 现在给定这两个序列和一个 PID 表示你要杀死的进程，函数返回一个 PID 序列，
     * 表示因为杀这个进程而导致的所有被杀掉的进程的编号。
     * 当一个进程被杀掉的时候，它所有的孩子进程和后代进程都要被杀掉。
     * 你可以以任意顺序排列返回的 PID 序列。
     * <p>
     * 示例 1:
     * 输入:
     * pid =  [1, 3, 10, 5]
     * ppid = [3, 0, 5, 3]
     * kill = 5
     * 输出: [5,10]
     * 解释:
     * 3
     * /   \
     * 1     5
     * /
     * 10
     * 杀掉进程 5 ，同时它的后代进程 10 也被杀掉。
     * <p>
     * 注意:
     * 被杀掉的进程编号一定在 PID 序列中。
     * n >= 1.
     *
     * @param pid
     * @param ppid
     * @param kill
     * @return
     */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                List<Integer> list = map.getOrDefault(ppid.get(i), new ArrayList<Integer>());
                list.add(pid.get(i));
                map.put(ppid.get(i), list);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        queue.add(kill);
        while (!queue.isEmpty()) {
            int r = queue.remove();
            res.add(r);
            if (map.containsKey(r)) {
                for (int id : map.get(r)) {
                    queue.add(id);
                }
            }
        }
        return res;
    }
    //endregion  2019

    //region    20191017    621. 任务调度器

    /**
     * 给定一个用字符数组表示的 CPU 需要执行的任务列表。
     * 其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
     * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
     * CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
     * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，
     * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * 你需要计算完成所有任务所需要的最短时间。
     * <p>
     * 示例 1：
     * 输入: tasks = ["A","A","A","B","B","B"], n = 2
     * 输出: 8
     * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
     * <p>
     * 注：
     * 任务的总个数为 [1, 10000]。
     * n 的取值范围为 [0, 100]。
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        /**
         * 解题思路
         *  1、将任务按类型分组，正好A-Z用一个int[26]保存任务类型个数
         *  2、对数组进行排序，优先排列个数（count）最大的任务，
         *        如题得到的时间至少为 retCount =（count-1）* (n+1) + 1 ==> A->X->X->A->X->X->A(X为其他任务或者待命)
         *  3、再排序下一个任务，如果下一个任务B个数和最大任务数一致，
         *        则retCount++ ==> A->B->X->A->B->X->A->B
         *  4、如果空位都插满之后还有任务，那就随便在这些间隔里面插入就可以，因为间隔长度肯定会大于n，在这种情况下就是任务的总数是最小所需时间
         */
        if (tasks.length <= 1 || n < 1) return tasks.length;
        //步骤1
        int[] counts = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            counts[tasks[i] - 'A']++;
        }
        //步骤2
        Arrays.sort(counts);
        int maxCount = counts[25];
        int retCount = (maxCount - 1) * (n + 1) + 1;
        int i = 24;
        //步骤3
        while (i >= 0 && counts[i] == maxCount) {
            retCount++;
            i--;
        }
        //步骤4
        return Math.max(retCount, tasks.length);
    }
    //endregion

    //region    20191017    641. 设计循环双端队列

    /**
     * 设计实现双端队列。
     * 你的实现需要支持以下操作：
     * <p>
     * MyCircularDeque(k)：构造函数,双端队列的大小为k。
     * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
     * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
     * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
     * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
     * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
     * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
     * isEmpty()：检查双端队列是否为空。
     * isFull()：检查双端队列是否满了。
     * <p>
     * 示例：
     * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
     * circularDeque.insertLast(1);			        // 返回 true
     * circularDeque.insertLast(2);			        // 返回 true
     * circularDeque.insertFront(3);			        // 返回 true
     * circularDeque.insertFront(4);			        // 已经满了，返回 false
     * circularDeque.getRear();  				// 返回 2
     * circularDeque.isFull();				        // 返回 true
     * circularDeque.deleteLast();			        // 返回 true
     * circularDeque.insertFront(4);			        // 返回 true
     * circularDeque.getFront();				// 返回 4
     * <p>
     * 提示：
     * 所有值的范围为 [1, 1000]
     * 操作次数的范围为 [1, 1000]
     * 请不要使用内置的双端队列库。
     */
    class MyCircularDeque {
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
    //endregion

    //region    20191017    933. 最近的请求次数

    /**
     * 写一个 RecentCounter 类来计算最近的请求。
     * 它只有一个方法：ping(int t)，其中 t 代表以毫秒为单位的某个时间。
     * 返回从 3000 毫秒前到现在的 ping 数。
     * 任何处于 [t - 3000, t] 时间范围之内的 ping 都将会被计算在内，包括当前（指 t 时刻）的 ping。
     * 保证每次对 ping 的调用都使用比之前更大的 t 值。
     * 示例：
     * 输入：inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
     * 输出：[null,1,2,3,3]
     * <p>
     * 提示：
     * 每个测试用例最多调用 10000 次 ping。
     * 每个测试用例会使用严格递增的 t 值来调用 ping。
     * 每次调用 ping 都有 1 <= t <= 10^9。
     */
    class RecentCounter {
        Queue<Integer> q;

        public RecentCounter() {
            q = new LinkedList<>();
        }

        public int ping(int t) {
            q.add(t);
            while (q.peek() < t - 3000) {
                q.poll();
            }
            return q.size();
        }
    }
    //endregion

    //region    20230410    1019. 链表中的下一个更大节点

    /**
     * https://leetcode.cn/problems/next-greater-node-in-linked-list/
     * @param head  长度为 n 的链表 head
     * @return  一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0
     */
    public int[] nextLargerNodes(ListNode head) {
        df(head, 0);
        return ans;
    }

    public void df(ListNode node, int i) {
        if (node == null) {
            ans = new int[i];
            return;
        }
        df(node.next, i + 1);
        while (!stack.isEmpty() && stack.peek() <= node.val) {
            stack.pop();
        }
        if (!stack.isEmpty()) {
            ans[i] = stack.peek();
        }
        stack.push(node.val);
    }

    private int[] ans;
    private final Stack<Integer> stack = new Stack<>();
    //endregion

    public static void main(String[] args) {

    }

}