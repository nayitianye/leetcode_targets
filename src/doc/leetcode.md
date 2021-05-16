#### 一、链表

与数组相似，链表也是一种`线性`数据结构。这里有一个例子：

![1566298444850](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566298444850.png)

​		正如你所看到的，链表中的每个元素实际上是一个单独的对象，而所有对象都通过每个元素中的引用字段链接在一起。
​		链表有两种类型：单链表和双链表。上面给出的例子是一个单链表，这里有一个双链表的例子： 

![1566298530547](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566298530547.png)

##### 1.单链表

链表中的每个结点不仅包含值，还包含链接到下一个结点的`引用字段`。通过这种方式，单链表将所有结点按顺序组织起来。、

下面是一个单链表的例子：

![1566298444850](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566298444850.png)

蓝色箭头显示单个链接列表中的结点是如何组合在一起的。

###### **添加操作**

如果我们想在给定的结点 `prev` 之后添加新值，我们应该：
1. 使用给定值初始化新结点 `cur；`

![1566298863230](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566298863230.png)

2. 将 `cur `的“next”字段链接到 prev 的下一个结点 `next`；

![1566298880886](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566298880886.png)

3. 将 `prev` 中的“next”字段链接到 `cur` 。

![1566298926945](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566298926945.png)

与数组不同，我们不需要将所有元素移动到插入元素之后。因此，您可以在 `O(1)` 时间复杂度中将新结点插入到链表中，这非常高效。

**在开头添加节点**
众所周知，我们使用头结点来代表整个列表。
因此，在列表开头添加新节点时更新头结点 `head` 至关重要。
1. 初始化一个新结点 `cur；`
2. 将新结点链接到我们的原始头结点 `head`。
3. 将 `cur` 指定为 `head`。

例如，让我们在列表的开头添加一个新结点 9。
1. 我们初始化一个新结点 9 并将其链接到当前头结点 23。
![1566299090510](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566299090510.png)
3. 指定结点 9 为新的头结点。 
![1566299132127](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566299132127.png)

###### 删除操作

如果我们想从单链表中删除现有结点 `cur`，可以分两步完成：
1. 找到 cur 的上一个结点 `prev` 及其下一个结点 `next；`
![1566299243585](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566299243585.png)
3. 接下来链接 `prev` 到 cur 的下一个节点 `next。`
![1566299285345](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566299285345.png)

在我们的第一步中，我们需要找出 `prev` 和 `next`。使用 `cur` 的参考字段很容易找出 `next`，但是，我们必须从头结点遍历链表，以找出 `prev`，它的平均时间是 `O(N)`，其中 N 是链表的长度。因此，删除结点的时间复杂度将是 `O(N)`。 

空间复杂度为 `O(1)`，因为我们只需要常量空间来存储指针。

**删除第一个结点**
如果我们想删除第一个结点，策略会有所不同。
正如之前所提到的，我们使用头结点 `head` 来表示链表。我们的头是下面示例中的黑色结点 23。

![1566299357080](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566299357080.png)

如果想要删除第一个结点，我们可以简单地`将下一个结点分配给 head`。也就是说，删除之后我们的头将会是结点 6。

![1566299385585](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566299385585.png)

链表从头结点开始，因此结点 23 不再在我们的链表中。

###### 设计链表

​		设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：`val` 和 `next`。`val` 是当前节点的值，`next` 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 `prev` 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
在链表类中实现这些功能：
- get(index)：获取链表中第 `index` 个节点的值。如果索引无效，则返回`-1`。
- addAtHead(val)：在链表的第一个元素之前添加一个值为 `val` 的节点。插入后，新节点将成为链表的第一个节点。
- addAtTail(val)：将值为 `val` 的节点追加到链表的最后一个元素。
- addAtIndex(index,val)：在链表中的第 `index` 个节点之前添加值为 `val`  的节点。如果 `index` 等于链表的长度，则该节点将附加到链表的末尾。如果 `index` 大于链表长度，则不会插入节点。如果`index`小于0，则在头部插入节点。
- deleteAtIndex(index)：如果索引 `index` 有效，则删除链表中的第 `index` 个节点。
示例：
```java
MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-> 3
linkedList.get(1);            //返回3
```
**提示：**

- 所有`val`值都在 `[1, 1000]` 之内。
- 操作次数将在  `[1, 1000]` 之内。
- 请不要使用内置的 LinkedList 库。

```java
/**
 * A singly linked list with a left sentinel node.
 */
class MyLinkedList1 {
    /** A very simple node class. */
    private static class Node {
        int val;
        Node next;
    }
    // Predecessor of the first element
    private Node headPred;
    // Predecessor of the tail
    private Node tailPred;
    private int length;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        headPred = new Node();
        tailPred = headPred;
        length = 0;
    }
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if ((index < 0) || (index >= length)) {
            return -1;
        }
        return findPred(index).next.val;
    }
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        if (length == 0) {
            addAtTail(val);
        } else {
            addAfter(headPred, val);
        }
    }
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAfter(tailPred, val);
        tailPred = tailPred.next;
    }
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0) {
            addAtHead(val);
        } else if (index == length) {
            addAtTail(val);
        } else if ((index >= 0) && (index < length)) {
            addAfter(findPred(index), val);
        }
    }
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if ((index >= 0) && (index < length)) {
            Node pred = findPred(index);
            if (index == length - 1) { // Remove last element
                // Move tail to the left
                tailPred = pred;
            }
            pred.next = pred.next.next;
            --length;
        }
    }
    /** Return the predecessor of the index-th node. */
    private Node findPred(int index) {
        Node pred = headPred;
        for (int i = 0; i < index; ++i) {
            pred = pred.next;
        }
        return pred;
    }
    /** Add an element after the given node. */
    private void addAfter(Node pred, int val) {
        Node node = new Node();
        node.val = val;
        node.next = pred.next;
        pred.next = node;
        ++length;
    }
}
```

##### 2. 链表中的双指针

让我们从一个经典问题开始：
		给定一个链表，判断链表中是否有环。

你可能已经使用**哈希表**提出了解决方案。但是，使用**双指针技巧**有一个更有效的解决方案。在阅读接下来的内容之前，试着自己仔细考虑一下。

想象一下，有两个速度不同的跑步者。如果他们在直路上行驶，快跑者将首先到达目的地。但是，如果它们在圆形跑道上跑步，那么快跑者如果继续跑步就会追上慢跑者。

这正是我们在链表中使用两个速度不同的指针时会遇到的情况： 
		**1. 如果没有环，快指针将停在链表的末尾。** 
		**2. 如果有环，快指针最终将与慢指针相遇。** 

所以剩下的问题是： 
		这两个指针的适当速度应该是多少？  

一个安全的选择是每次移动慢指针**一步**，而移动快指针**两步**。每一次迭代，快速指针将额外移动一步。如果环的长度为 M，经过 M 次迭代后，快指针肯定会多绕环一周，并赶上慢指针。 
		那其他选择呢？它们有用吗？它们会更高效吗？

###### 环形链表

给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 `pos` 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 `pos` 是 `-1`，则在该链表中没有环。

 **示例 1：**

```java
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

![1567657229081](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567657229081.png)

**示例 2：**

```java
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
```

![1567657269891](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567657269891.png)

**示例 3：**

```java
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
```

![1567657307140](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567657307140.png)

**算法一：使用双指针**

```java
public boolean hasCycle(ListNode head) {
    ListNode fast=head;
    ListNode slow=head;
    while(fast!=null&&fast.next!=null){
        fast=fast.next.next;
        slow=slow.next;
        if(fast==slow){
            return true;
        }
    }
    return false;
}
```

**算法二：使用HashSet哈希表解决**

```java
public boolean hasCycle2(ListNode head) {
    HashSet<ListNode> hashSet=new HashSet<>();
    while(head!=null){
        if (hashSet.contains(head)) {
            return true;
        }
        hashSet.add(head);
        head=head.next;
    }
    return false;
}
```

###### 环形链表Ⅱ

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 `null`。

为了表示给定链表中的环，我们使用整数 `pos` 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 `pos` 是 `-1`，则在该链表中没有环。

**说明：**不允许修改给定的链表。

**示例 1：**

```java
输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。
```

![1567661883008](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567661883008.png)

示例 2：**

```java
输入：head = [1,2], pos = 0
输出：tail connects to node index 0
解释：链表中有一个环，其尾部连接到第一个节点。
```

![1567661902646](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567661902646.png)

**示例 3：**

```java
输入：head = [1], pos = -1
输出：no cycle
解释：链表中没有环。
```

![1567661922104](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567661922104.png)

**算法一：双指针**

```java
public ListNode detectCycle1(ListNode head) {
    if (head == null)
        return head;
    ListNode slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }
    return null;
}
```

**算法二：hashset哈希表**

```java
public ListNode detectCycle(ListNode head) {
    Set<ListNode> set = new HashSet<>();
    while(head != null){
        if(set.contains(head))
            return head;
        else
            set.add(head);
        head = head.next;
    }
    return null;
}
```

###### 相交链表

编写一个程序，找到两个单链表相交的起始节点。

如下面的两个链表**：**

![1567666255187](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567666255187.png)

在节点 c1 开始相交。

**示例 1：**

![1567666288771](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567666288771.png)

```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```

**示例 2：**

![1567666359096](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567666359096.png)

```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```

**示例 3：**

![1567666389072](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567666389072.png)

```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```

**注意：**

- 如果两个链表没有交点，返回 `null`.
- 在返回结果后，两个链表仍须保持原有的结构。
- 可假定整个链表结构中没有循环。
- 程序尽量满足 O(*n*) 时间复杂度，且仅用 O(*1*) 内存。

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null)
            return null;
        ListNode a=headA;
        ListNode b=headB;
        //计算两个链表的长度差 长的先走差值
        //长度相等时若有相交则肯定是同时走到
        int lenA=0,lenB=0;
        while(a!=null){
            a=a.next;
            lenA++;
        }
        while(b!=null){
            b=b.next;
            lenB++;
        }
        //重新指向
        a=headA;
        b=headB;
        //B链长，B先走差值个
        if(lenB>lenA){
            int c=lenB-lenA;
            while(c>0){
                b=b.next;
                c--;
            }
        }else if(lenB<lenA){
            int c=lenA-lenB;
            while(c>0){
                a=a.next;
                c--;
            }   
        }
        //此时AB链相等
        while(a!=null&&b!=null){
            if(a==b)
                return a;
            a=a.next;
            b=b.next;
        }
        return null;
}
```

###### 删除链表的倒数第N个节点
给定一个链表，删除链表的倒数第 *n* 个节点，并且返回链表的头结点。
**示例：**
```java
给定一个链表: 1->2->3->4->5, 和 n = 2.
当删除了倒数第二个节点后，链表变为 1->2->3->5.
```
**说明：**
给定的 *n* 保证是有效的。
**进阶：**
你能尝试使用一趟扫描实现吗？
```java
public ListNode removeNthFromEnd(ListNode head, int n) {
     ListNode fast = head;
     ListNode slow = head;
     while (n-->0){
        fast = fast.next;
    }
    if (fast == null) {
        return head.next;
    }   
    while (fast.next!=null){
        fast = fast.next;
        slow = slow.next;
    }
    slow.next = slow.next.next;
	return head;
}
```

##### 3. 反转链表
让我们从一个经典问题开始：
​		反转一个单链表。
一种解决方案是`按原始顺序迭代结点`，并将它们`逐个移动到列表的头部`。似乎很难理解。我们先用一个例子来说明我们的算法。

**算法概述**

![1567666605752](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567666605752.png)

**更多**

​		在该算法中，每个结点`只移动一次`。
​		因此，时间复杂度为 `O(N)`，其中 N 是链表的长度。我们只使用常量级的额外空间，所以空间复杂度为 `O(1)。`
​		这个问题是你在面试中可能遇到的许多链表问题的基础。如果你仍然遇到困难，我们的下一篇文章将更多地讨论实现细节。
​		还有许多其他的解决方案。您应该熟悉至少一个解决方案并能够实现它。

###### 反转链表

反转一个单链表。

**示例:**

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

**进阶:**
你可以迭代或递归地反转链表。

**算法一：迭代的方式**

```java
public ListNode reverseList(ListNode head) {
    if(head == null){
        return head;
    }
    ListNode node = head;
    ListNode next = null;
    ListNode prev = null;
    while(node!=null){
        next = node.next;
        node.next = prev;
        prev = node;
        node = next;
    }
    return prev;
}
```

**复杂度分析**

- 时间复杂度：O(n)*O*(*n*)，假设 n*n* 是列表的长度，时间复杂度是 O(n)*O*(*n*)。
- 空间复杂度：O(1)*O*(1)。

**算法二：递归的方式**

```java
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode p = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return p;
}
```

复杂度分析

时间复杂度：O(n)，假设 n 是列表的长度，那么时间复杂度为 O(n)。
空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间。递归深度可能会达到 n 层。

###### 移除链表元素

删除链表中等于给定值 **val** 的所有节点。

**示例:**

```
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
```

**算法一：迭代的方式**

```java
 public ListNode removeElements(ListNode head, int val) {
     ListNode pre=new ListNode(0);
    pre.next=head;
    ListNode cur=pre;
    while(cur.next!=null){
        if(cur.next.val==val){
            if(cur.next.next!=null){
                cur.next=cur.next.next;
            }else{
                cur.next=null;
            }
        }else{
            cur=cur.next;
        }
    }
    return pre.next;
}
```

**算法二：递归的方式**

```java
public ListNode removeElements(ListNode head, int val) {
	//使用链表和递归的方式来实现
    if(head==null) {
    	return null;
    }
    head.next=removeElements(head.next, val);
    return head.val==val ? head.next:head;
}
```

######  奇偶链表

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

**示例 1:**

```
输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
```

**示例 2:**

```
输入: 2->1->3->5->6->4->7->NULL 
输出: 2->3->6->7->1->5->4->NULL
```

**说明:**

- 应当保持奇数节点和偶数节点的相对顺序。
- 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

**算法思路**

​		一个 LinkedList 需要一个头指针和一个尾指针来支持双端操作。我们用变量 head 和 odd 保存奇链表的头和尾指针。 evenHead 和 even 保存偶链表的头和尾指针。算法会遍历原链表一次并把奇节点放到奇链表里去、偶节点放到偶链表里去。遍历整个链表我们至少需要一个指针作为迭代器。这里 odd 指针和 even 指针不仅仅是尾指针，也可以扮演原链表迭代器的角色。

![1567670525943](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567670525943.png)

**算法**

```java
public ListNode oddEvenList(ListNode head) {
    if(head==null){
        return null;
    }
    ListNode odd=head;
    ListNode even=head.next;
    ListNode evenHead=even;
    while(even!=null&&even.next!=null){
        odd.next=even.next;
        odd=odd.next;
        even.next=odd.next;
        even=even.next;
    }
    odd.next=evenHead;
    return head;
}
```

###### **回文链表**

请判断一个链表是否为回文链表。

**示例 1:**

```
输入: 1->2
输出: false
```

**示例 2:**

```
输入: 1->2->2->1
输出: true
```

**进阶：**
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

```java
public boolean isPalindrome(ListNode head) {
    if(head == null || head.next == null) return true;
    ListNode slow = head, fast = head.next, pre = null, prepre=null;
    while(fast != null && fast.next != null) {
    	//反转前半段链表
    	pre = slow;
    	slow = slow.next;
    	fast = fast.next.next;
    	//先移动指针再来反转
    	pre.next = prepre;
    	prepre = pre;
    }
    ListNode p2 = slow.next;
    slow.next = pre;
    ListNode p1 = fast == null? slow.next : slow;
    while(p1 != null) {
    	if(p1.val != p2.val)
    		return false;
    	p1 = p1.next;
    	p2 = p2.next;
    }
    return true;
}
```

##### 4.  双链表
​		双链表以类似的方式工作，但`还有一个引用字段`，称为`“prev”`字段。有了这个额外的字段，您就能够知道当前结点的前一个结点。
​		让我们看一个例子：

![1567672296664](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567672296664.png)

​		绿色箭头表示我们的“prev”字段是如何工作的。
**结点结构**

```java
// Definition for doubly-linked list.
class DoublyListNode {
    int val;
    DoublyListNode next, prev;
    DoublyListNode(int x) {val = x;}
}
```
​		与单链接列表类似，我们将使用**头结点**来表示整个列表。
**操作**
​		我们可以与单链表相同的方式访问数据：

1. 我们不能在常量级的时间内`访问随机位置`。
2. 我们必须从头部遍历才能得到我们想要的第一个结点。
3. 在最坏的情况下，时间复杂度将是 `O(N)`，其中 `N` 是链表的长度。

​        对于添加和删除，会稍微复杂一些，因为我们还需要处理“prev”字段。在接下来的两篇文章中，我们将介绍这两个操作
之后，我们提供练习，让你使用双链表重新设计链表。

###### 添加操作 - 双链表

![1567672471129](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567672471129.png)

**示例**

![1567672524918](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567672524918.png)

######  删除操作 - 双链表

![1567672564951](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567672564951.png)

**示例**

![1567672600567](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567672600567.png)

###### 设计链表
​		设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：`val` 和 `next`。`val` 是当前节点的值，`next` 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 `prev` 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：

- get(index)：获取链表中第 `index` 个节点的值。如果索引无效，则返回`-1`。
- addAtHead(val)：在链表的第一个元素之前添加一个值为 `val` 的节点。插入后，新节点将成为链表的第一个节点。
- addAtTail(val)：将值为 `val` 的节点追加到链表的最后一个元素。
- addAtIndex(index,val)：在链表中的第 `index` 个节点之前添加值为 `val`  的节点。如果 `index` 等于链表的长度，则该节点将附加到链表的末尾。如果 `index` 大于链表长度，则不会插入节点。如果`index`小于0，则在头部插入节点。
- deleteAtIndex(index)：如果索引 `index` 有效，则删除链表中的第 `index` 个节点。
**示例：**
```java
MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-> 3
linkedList.get(1);            //返回3
```

**提示：**

- 所有`val`值都在 `[1, 1000]` 之内。
- 操作次数将在  `[1, 1000]` 之内。
- 请不要使用内置的 LinkedList 库。

##### **5. 小结**
###### 复习

![1567672812924](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567672812924.png)

###### 对照
这里我们提供链表和其他数据结构（包括[数组](http://leetcode-cn.com/explore/learn/card/array-and-string/)，队列和栈）之间`时间复杂度`的比较：

![1567672851562](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567672851562.png)

经过这次比较，我们不难得出结论：
​		如果你需要经常添加或删除结点，链表可能是一个不错的选择。
​		如果你需要经常按索引访问元素，数组可能是比链表更好的选择。

###### 合并两个有序链表

将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

**示例：**

```java
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

```java
 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	if (l1 == null) return l2;
	if (l2 == null) return l1;
	ListNode head = null;
	ListNode node = null;
	if (l1.val < l2.val) {
		head = l1;
		node = l1;
		l1 = l1.next;
	} else {
		head = l2;
		node = l2;
		l2 = l2.next;
	}
	while (l1 != null && l2 != null) {
		if (l1.val < l2.val) {
			node.next = l1;
			l1 = l1.next;
		} else {
			node.next = l2;
			l2 = l2.next;
		}
		node = node.next;
	}
	if (l1 == null) {
		node.next = l2;
	}
	if (l2 == null) {
		node.next = l1;
	}
	return head;
}
```
###### 两数相加
​		给出两个 **非空** 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 **逆序** 的方式存储的，并且它们的每个节点只能存储 **一位** 数字。
​		如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
**示例：**

```java
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
```

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if(l1==null){
        return l2;
    }
    if(l2==null){
        return l1;
    }
    ListNode head=l1;
    ListNode node=l2;
    ListNode res=new ListNode(0);
    ListNode flag=res;
    int x,y,z=0;
    while(head!=null||node!=null||z!=0){
        if(head==null){
            x=0;
        }else{
            x=head.val;
            head=head.next;
        }
        if(node==null){
            y=0;
        }else{
            y=node.val;
            node=node.next;
        }
        int temp=(x+y+z)%10;
        z=(x+y+z)/10;
        flag.next=new ListNode(temp);
        flag=flag.next;
    }
    return res.next;
}
```

######  扁平化多级双向链表

​		您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

​		扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。

**示例:**

```java
输入:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
输出:
1-2-3-7-8-11-12-9-10-4-5-6-NULL
```

**以上示例的说明:**

给出以下多级双向链表:

![1567750752521](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567750752521.png)

我们应该返回如下所示的扁平双向链表:

![1567750797660](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567750797660.png)

```java
public class Node {
     public int val;
     public Node prev;
     public Node next;
     public Node child;
     public Node() {}
     public Node(int _val,Node _prev,Node _next,Node _child) {
         val = _val;
         prev = _prev;
         next = _next;
		child = _child;
	}
}
public Node flatten(Node head) {
	if(head==null){
        return head;
    }
    search(head);
    return head;
}   
private Node search(Node head){
    Node n=head;
    while(true){
		if(n.child!=null){
			if(n.next==null){
				Node m=search(n.child);
				n.next=n.child;
				n.child.prev=n;
				n.child=null;
				return m;
			}else{
				Node m=search(n.child);
				n.next.prev=m;
				m.next=n.next;
				n.next=n.child;
				n.child.prev=n;
				n.child=null;
				n=m.next;
			}
		}else{
			if(n.next==null){
				return n;
			}else{
				n=n.next;
			}
		}
	}                
}
```

###### 复制带随机指针的链表

给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的**深拷贝**。 

**示例：**

![1567755479618](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567755479618.png)

```JAVA
输入：
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
解释：
节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
```

**提示：**

1. 你必须返回**给定头的拷贝**作为对克隆列表的引用。

首先，我们来看一下有向链表：

![1567758829640](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567758829640.png)

在上图中，对于一个节点，它的 next 指针指向链表中的下一个节点。 next 指针是通常有向链表中有的部分且将所有节点 链接 起来。图中有趣的一点，也是这题有趣的一点在于 random 指针，正如名字所示，它可以指向链表中的任一节点也可以为空。

**方法 1：回溯**

想法

回溯算法的第一想法是将链表想象成一张图。链表中每个节点都有 2 个指针（图中的边）。因为随机指针给图结构添加了随机性，所以我们可能会访问相同的节点多次，这样就形成了环。

上图中，我们可以看到随机指针指向了前一个节点，因此成环。我们需要考虑这种环的实现。

此方法中，我们只需要遍历整个图并拷贝它。拷贝的意思是每当遇到一个新的未访问过的节点，你都需要创造一个新的节点。遍历按照深度优先进行。我们需要在回溯的过程中记录已经访问过的节点，否则因为随机指针的存在我们可能会产生死循环。

**算法**

1. 从 `头` 指针开始遍历整个图。

   我们将链表看做一张图。下图对应的是上面的有向链表的例子，`Head` 是图的出发节点。

![1567758963448](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567758963448.png)

2.当我们遍历到某个点时，如果我们已经有了当前节点的一个拷贝，我们不需要重复进行拷贝。
3.如果我们还没拷贝过当前节点，我们创造一个新的节点，并把该节点放到已访问字典中，即：

```java
visited_dictionary[current_node] = cloned_node_for_current_node.
```

4.我们针对两种情况进行回溯调用：一个顺着 random 指针调用，另一个沿着 next 指针调用。步骤 1 中将 random 和 next 指针分别红红色和蓝色标注。然后我们分别对两个指针进行函数递归调用：

```java
cloned_node_for_current_node.next = copyRandomList(current_node.next);
cloned_node_for_current_node.random = copyRandomList(current_node.random);
```

```java
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
public class Solution {
	// HashMap which holds old nodes as keys and new nodes as its values.
	HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();
	public Node copyRandomList(Node head) {
    	if (head == null) {
      		return null;
    	}
    // If we have already processed the current node, then we simply return the cloned version of
    // it.
    if (this.visitedHash.containsKey(head)) {
      return this.visitedHash.get(head);
    }
    // Create a new node with the value same as old node. (i.e. copy the node)
    Node node = new Node(head.val, null, null);
    // Save this value in the hash map. This is needed since there might be
    // loops during traversal due to randomness of random pointers and this would help us avoid
    // them.
    this.visitedHash.put(head, node);
    // Recursively copy the remaining linked list starting once from the next pointer and then from
    // the random pointer.
    // Thus we have two independent recursive calls.
    // Finally we update the next and random pointers for the new node created.
    node.next = this.copyRandomList(head.next);
    node.random = this.copyRandomList(head.random);
    return node;
  }
}
```

**复杂度分析**

​		时间复杂度：O(N)，其中 N 是链表中节点的数目。
​		空间复杂度：O(N) 。如果我们仔细分析，我们需要维护一个回溯的栈，同时也需要记录已经被深拷贝过的节点，也就是维护一个已访问字典。渐进时间复杂度为 O(N)。

**方法 2： O(N)*O*(*N*) 空间的迭代**
**想法**
		迭代算法不需要将链表视为一个图。当我们在迭代链表时，我们只需要为 random 指针和 next 指针指向的未访问过节点创造新的节点并赋值即可。
**算法**

1. 从 head 节点开始遍历链表。下图中，我们首先创造新的 head 拷贝节点。拷贝的节点如下图虚线所示。实现中，我们将该新建节点的引用也放入已访问字典中。

![1567759309764](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567759309764.png)

2. random 指针
- 如果当前节点 ii 的 random 指针只想一个节点 j 且节点 j 已经被拷贝过，我们将直接使用已访问字典中该节点的引用而不会新建节点。
- 如果当前节点 ii 的 random 指针只想的节点 j 还没有被拷贝过，我们就对 j 节点创建对应的新节点，并把它放入已访问节点字典中。

下图中， A 的 random 指针指向的节点 C 。前图中可以看出，节点 C 还没有被访问过，所以我们创造一个拷贝的 C'节点与之对应，并将它添加到已访问字典中。

![1567759385373](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567759385373.png)

3. next 指针
- 如果当前节点 ii 的 next 指针指向的节点 jj 在已访问字典中已有拷贝，我们直接使用它的拷贝节点。
- 如果当前节点 ii 的next 指针指向的节点 jj 还没有被访问过，我们创建一个对应节点的拷贝，并放入已访问字典。

​       下图中，A 节点的 next 指针指向节点 B 。节点 B 在前面的图中还没有被访问过，因此我们创造一个新的拷贝 B' 节点，并放入已访问字典中。

![1567759445067](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567759445067.png)

4.  我们重复步骤 2 和步骤 3 ，直到我们到达链表的结尾。

​       下图中， 节点 B 的 random 指针指向的节点 A 已经被访问过了，因此在步骤 2 中，我们不会创建新的拷贝，只将节点 B' 的 random 指针指向克隆节点 A' 。
​       同样的， 节点 BB 的 next 指针指向的节点 C 已经访问过，因此在步骤 3 中，我们不会创建新的拷贝，而直接将 B'  的 next 指针指向已经存在的拷贝节点 C' 。

![1567759525555](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567759525555.png)

```java
public class Solution {
	// Visited dictionary to hold old node reference as "key" and new node reference as the "value"
  	HashMap<Node, Node> visited = new HashMap<Node, Node>();
  	public Node getClonedNode(Node node) {
    	// If the node exists then
    	if (node != null) {
			// Check if the node is in the visited dictionary
      		if (this.visited.containsKey(node)) {
        	// If its in the visited dictionary then return the new node reference from the dictionary
        		return this.visited.get(node);
		} else {
        // Otherwise create a new node, add to the dictionary and return it
        		this.visited.put(node, new Node(node.val, null, null));
        		return this.visited.get(node);
			}
		}
		return null;
	}

	public Node copyRandomList(Node head) {
    	if (head == null) {
		return null;
    	}
		Node oldNode = head;
		// Creating the new head node.
    	Node newNode = new Node(oldNode.val);
    	this.visited.put(oldNode, newNode);
    	// Iterate on the linked list until all nodes are cloned.
    	while (oldNode != null) {
      		// Get the clones of the nodes referenced by random and next pointers.
      		newNode.random = this.getClonedNode(oldNode.random);
      		newNode.next = this.getClonedNode(oldNode.next);
      		// Move one step ahead in the linked list.
      		oldNode = oldNode.next;
      		newNode = newNode.next;
    	}
    	return this.visited.get(head);
  	}
}
```
**复杂度分析**
时间复杂度：O(N) 。因为我们需要将原链表逐一遍历。
空间复杂度：O(N) 。我们需要维护一个字典，保存旧的节点和新的节点的对应。因此总共需要 N 个节点，需要 O(N) 的空间复杂度。
**方法 3: O(1) 空间的迭代**
**想法**
		与上面提到的维护一个旧节点和新节点对应的字典不同，我们通过扭曲原来的链表，并将每个拷贝节点都放在原来对应节点的旁边。这种旧节点和新节点交错的方法让我们可以在不需要额外空间的情况下解决这个问题。让我们看看这个算法如何工作
**算法**

1. 遍历原来的链表并拷贝每一个节点，将拷贝节点放在原来节点的旁边，创造出一个旧节点和新节点交错的链表。

![1567759923275](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567759923275.png)

![1567759867682](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567759867682.png)

如你所见，我们只是用了原来节点的值拷贝出新的节点。原节点 `next` 指向的都是新创造出来的节点。
```java
cloned_node.next = original_node.next
original_node.next = cloned_node
```
2. 迭代这个新旧节点交错的链表，并用旧节点的 random 指针去更新对应新节点的 random 指针。比方说， B 的 random 指针指向 A ，意味着 B' 的 random 指针指向 A' 。

![1567760004782](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567760004782.png)

3. 现在 `random` 指针已经被赋值给正确的节点， `next` 指针也需要被正确赋值，以便将新的节点正确链接同时将旧节点重新正确链接。

![1567760054766](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567760054766.png)

```java
public class Solution {
	public Node copyRandomList(Node head) {
		if (head == null) {
			return null;
    	}
    	// Creating a new weaved list of original and copied nodes.
    	Node ptr = head;
    	while (ptr != null) {
      		// Cloned node
			Node newNode = new Node(ptr.val);
      		// Inserting the cloned node just next to the original node.
      		// If A->B->C is the original linked list,
      		// Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
      		newNode.next = ptr.next;
      		ptr.next = newNode;
      		ptr = newNode.next;
    	}
    	ptr = head;
    	// Now link the random pointers of the new nodes created.
    	// Iterate the newly created list and use the original nodes' random pointers,
    	// to assign references to random pointers for cloned nodes.
    	while (ptr != null) {
      		ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
      		ptr = ptr.next.next;
		}
    	// Unweave the linked list to get back the original linked list and the cloned list.
    	// i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
    	Node ptr_old_list = head; // A->B->C
    	Node ptr_new_list = head.next; // A'->B'->C'
    	Node head_old = head.next;
    	while (ptr_old_list != null) {
			ptr_old_list.next = ptr_old_list.next.next;
			ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
      		ptr_old_list = ptr_old_list.next;
      		ptr_new_list = ptr_new_list.next;
    	}
    	return head_old;
	}
}
```
**复杂度分析**
时间复杂度: O(N)
空间复杂度: O(1)

###### 旋转链表

**示例 1:**

```
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL
```

**示例 2:**

```
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL
```

```java
public ListNode rotateRight(ListNode head, int k) {
	if(head==null||k==0){
		return head;
	}
	ListNode flag=head;
	int  num=0;
	while(flag!=null){
		flag=flag.next;
		num++;
	}
	k=k%num;
	if(k==0){
		return head;
	}
	k=num-k;
	flag=head;
	ListNode pre=null;
	for(int i=1;i<k;i++){
		flag=flag.next;
		pre=flag.next;
	}
	if(pre==null){
		pre=flag.next;
	}
	flag.next=null;
	ListNode cur=pre;
	while(cur!=null&&cur.next!=null){
		cur=cur.next;
	}
	cur.next=head;
	return pre;
}
```

#### 二、栈和队列

##### 3.栈（stack）

![img](file://C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1566192077795.png?lastModify=1569839967)

​		在 LIFO 数据结构中，将`首先处理添加到队列`中的`最新元素`。 		与队列不同，栈是一个 LIFO 数据结构。通常，插入操作在栈中被称作入栈 `push` 。与队列类似，总是`在堆栈的末尾添加一个新元素`。但是，删除操作，退栈 `pop` ，将始终`删除`队列中相对于它的`最后一个元素`。

入栈：

![img](file://C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1566192841910.png?lastModify=1569839967)

出栈：

![img](file://C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1566192984721.png?lastModify=1569839967)

###### 栈的实现

栈的实现比队列容易。**动态数组**足以实现堆栈结构。

实现的代码：

```java
 // "static void main" must be defined in a public class. 
class MyStack {     
    private List<Integer> data;              
    // store elements     
    public MyStack() {         
        data = new ArrayList<>();     
    }    
    /** Insert an element into the stack. */     
    public void push(int x) 
    {        
        data.add(x);    
    }    
    /** Checks whether the queue is empty or not. */     
    public boolean isEmpty() 
    {        
        return data.isEmpty();     
    }     
    /** Get the top item from the queue. */     
    public int top() {         
        return data.get(data.size() - 1);     
    }     
    /** Delete an element from the queue. Return true if the operation is successful. */     
    public boolean pop() 
    {         
        if (isEmpty()) 
        {             
            return false;         
        }         
        data.remove(data.size() - 1);         
        return true;     
    } 
};
```

###### 栈的用法

​	大多数流行的语言都提供了内置的栈库，因此你不必重新发明轮子。除了**初始化**，我们还需要知道如何使用两个最重要的操作：**入栈**和**退栈**。除此之外，你应该能够从栈中**获得顶部元素**。

```java
 // "static void main" must be defined in a public class. 
public class Main {     
    public static void main(String[] args) {         
        // 1. Initialize a stack.         
        Stack<Integer> s = new Stack<>();         
        // 2. Push new element.         
        s.push(5);         
        s.push(13);         
        s.push(8);         
        s.push(6);         
        // 3. Check if stack is empty.         
        if (s.empty() == true) {             
            System.out.println("Stack is empty!");             
            return;         
        }         
        // 4. Pop an element.         
        s.pop();         
        // 5. Get the top element.         
        System.out.println("The top element is: " + s.peek());         
        // 6. Get the size of the stack.         
        System.out.println("The size is: " + s.size());     
    } 
}
```

##### 4.  栈和深度优先搜索

###### 栈和DFS

`深度优先搜索`（DFS）也可用于查找从根结点到目标结点的路径。

**1. 结点的处理顺序是什么？** 		在上面的例子中，我们从根结点 `A` 开始。首先，我们选择结点 `B` 的路径，并进行回溯，直到我们到达结点 `E`，我们无法更进一步深入。然后我们回溯到 `A` 并选择第二条路径到结点 `C` 。从 `C` 开始，我们尝试第一条路径到 `E` 但是 `E` 已被访问过。所以我们回到 `C` 并尝试从另一条路径到 `F`。最后，我们找到了 `G`。 总的来说，在我们到达`最深的`结点之后，我们`只`会回溯并尝试另一条路径。 **2. 栈的入栈和退栈顺序是什么？** 		如上面的动画所示，我们首先将根结点推入到栈中；然后我们尝试第一个邻居 `B` 并将结点 `B` 推入到栈中；等等等等。当我们到达最深的结点 `E` 时，我们需要回溯。当我们回溯时，我们将从栈中`弹出最深的结点`，这实际上是推入到栈中的`最后一个结点`。 结点的处理顺序是`完全相反的顺序`，就像它们被`添加`到栈中一样，它是后进先出（LIFO）。这就是我们在 DFS 中使用栈的原因

###### 深度优先搜索--模板

​		在大多数情况下，我们在能使用 BFS 时也可以使用 DFS。但是有一个重要的区别：`遍历顺序`。 		与 BFS 不同，`更早访问的结点可能不是更靠近根结点的结点`。因此，你在 DFS 中找到的第一条路径`可能不是最短路径`。

**模板Ⅰ**

```java
 /*  * Return true if there is a path from cur to target.  */ 
boolean DFS(Node cur, Node target, Set<Node> visited) 
{     
    return true if cur is target;     
    for (next : each neighbor of cur) 
    {         
        if (next is not in visited) 
        {             
            add next to visted;             
            return true if DFS(next, target, visited) == true;         
        }     
    }     
    return false; 
}
```

当我们递归地实现 DFS 时，似乎不需要使用任何栈。但实际上，我们使用的是由系统提供的隐式栈，也称为调用栈（[Call Stack](https://en.wikipedia.org/wiki/Call_stack)）。

**示例：**

让我们看一个例子。我们希望在下图中找到结点 0 和结点 3 之间的路径。我们还会在每次调用期间显示栈的状态。

![img](file://C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1566205123270.png?lastModify=1569839967) 		在每个堆栈元素中，都有一个整数 `cur`，一个整数 `target`，一个对`访问过的`数组的引用和一个对数组`边界`的引用，这些正是我们在 DFS 函数中的参数。我们只在上面的栈中显示 `cur`。 		每个元素都需要固定的空间。栈的大小正好是 DFS 的深度。因此，在最坏的情况下，维护系统栈需要 O(h)，其中 h 是 DFS 的最大深度。在计算空间复杂度时，永远不要忘记考虑系统栈。

```
 在上面的模板中，我们在找到`第一条`路径时停止。  如果你想找到`最短`路径呢？ 提示：再添加一个参数来指示你已经找到的最短路径。
```

**模板Ⅱ**

​	递归解决方案的优点是它更容易实现。 但是，存在一个很大的缺点：如果递归的深度太高，你将遭受堆栈溢出。 在这种情况下，您可能会希望使用 BFS，或使用显式栈实现 DFS。

```java
/*
 * Return true if there is a path from cur to target.
 */
boolean DFS(int root, int target) {
    Set<Node> visited;
    Stack<Node> s;
    add root to s;
    while (s is not empty) {
        Node cur = the top element in s;
        return true if cur is target;
        for (Node next : the neighbors of cur) {
            if (next is not in visited) {
                add next to s;
                add next to visited;
            }
        }
        remove cur from s;
    }
    return false;
}
```

##### 1.队列(queue)

​		队列是一种先进先出（First In First Out）的线性表，简称FIFO。允许插入的一端称为队尾，允许删除的一端称为队头。

![1566105030315](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566105030315.png)

​		如上图所示，队列是典型的FIFO数据结构。插入（insert）操作也称作入队（enqueue)，新元素始终被添加在队列的末尾。删除（delete）操作也被称作（dequeue）。你只能移除第一个元素。

1）入队操作：在队列尾部插入新的元素。

![1566105264134](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566105264134.png)

![1566105296267](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566105296267.png)

2）出队操作：删除队列的首元素。

![1566105370590](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566105370590.png)

![1566105394259](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566105394259.png)

###### 队列的实现

为了实现队列，我们可以使用动态数组和指向队列头部的索引。

自定义队列代码实现：

```java
// "static void main" must be defined in a public class.
class MyQueue {
    // store elements
    private List<Integer> data;         
    // a pointer to indicate the start position
    private int p_start;            
    public MyQueue() {
        data = new ArrayList<Integer>();
        p_start = 0;
    }
    /** Insert an element into the queue. Return true if the operation is successful. */
    public boolean enQueue(int x) {
        data.add(x);
        return true;
    };    
    /** Delete an element from the queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty() == true) {
            return false;
        }
        p_start++;
        return true;
    }
    /** Get the front item from the queue. */
    public int Front() {
        return data.get(p_start);
    }
    /** Checks whether the queue is empty or not. */
    public boolean isEmpty() {
        return p_start >= data.size();
    }     
};

public class Main {
    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.enQueue(5);
        q.enQueue(3);
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
    }
}
```

**缺点**

​		上面的实现很简单，但在某些情况下效率很低。随着起始指针的移动，浪费了越来越多的空间。

![1566110314275](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566110314275.png)

假设我们只分配一个最大长度为5的数组。当我们只添加少于5个元素时，我珉娥的解决方案是很有效。例如，如果我们只调用入队函数四次后还想将元素入队，那么我们可以成功。但是就不能接受更多的入队请求，这是合理的，因为现在队列已经满了。但是如果我们将一个元素出队呢？

![1566110507007](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566110507007.png)

实际上，在这种情况下，我们应该能够再接受一个元素。

###### 循环队列

我们可以使用**固定大小的数组**和**两个指针**来指示起始位置和结束位置。目的是**重用**前面提到的**被浪费的存储**。

自定义循环队列代码实现：

```java
class MyCircularQueue {
	private int[] q;
  	private int head = 0, tail = -1, len = 0;
  	public MyCircularQueue(int k) {
    	this.q = new int[k];
  	}
  	public boolean enQueue(int value) {
    	if (isFull()) return false;
    	tail = (tail + 1) % q.length;
    	q[tail] = value;
    	len++;
    	return true;
  	}
  	public boolean deQueue() {
   		if (isEmpty()) return false;
    	head = (head + 1) % q.length;
    	len--;
    	return true;
  	}

  	public int Front() {
   		return isEmpty() ? -1: q[head];
  	}

  	public int Rear() {
    	return isEmpty() ? -1: q[tail];
  	}
	
  	public boolean isEmpty() {
    	return len == 0;
  	}
	
  	public boolean isFull() {
    	return len == q.length;
  	}
}
```

###### **队列的用法**

如前所述，队列有两个重要的操作，`入队 enqueue` 和`出队 dequeue`。 此外，我们应该能够`获得队列中的第一个元素`，因为应该首先处理它。

下面是使用内置队列库及其常见操作的一些示例：

```java
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // 1. Initialize a queue.
        Queue<Integer> q = new LinkedList();
        // 2. Get the first element - return null if queue is empty.
        System.out.println("The first element is: " + q.peek());
        // 3. Push new element.
        q.offer(5);
        q.offer(13);
        q.offer(8);
        q.offer(6);
        // 4. Pop an element.
        q.poll();
        // 5. Get the first element.
        System.out.println("The first element is: " + q.peek());
        // 7. Get the size of the queue.
        System.out.println("The size is: " + q.size());
    }
}
```

###### **数据流中的移动平均值**

给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。

示例：

```java
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
```

代码：

```java
public static class MovingAverage{
    private int[] data;
    private int size;
    private int head;
    private int tail;
    private int sum;
    public  MovingAverage(int x){
        data=new int[x];
        size=x;
        head=-1;
        tail=-1;
        sum=0;
    }

    public double next(int val){
        boolean isfull=((tail+1)%size==head);
        boolean isempty=(head==-1);
        if(isempty){
            head=0;
        }
        if(isfull){
            sum=sum-data[head];
            head=(head+1)%size;
        }
        tail=(tail+1)%size;
        data[tail]=val;
        int num=(tail+size-head)%size+1;
        sum=sum+val;
        return (double)sum/num;
    }
}
```

##### 2.  队列和广度优先搜索

###### 队列和BFS

广度优先搜索（BFS）的一个常见应用是找出从根结点到目标结点的最短路径。

**1. 结点的处理顺序是什么？**
在第一轮中，我们处理根结点。在第二轮中，我们处理根结点旁边的结点；在第三轮中，我们处理距根结点两步的结点；等等等等。
与树的层序遍历类似，`越是接近根结点的结点将越早地遍历`。
如果在第 k 轮中将结点 `X` 添加到队列中，则根结点与 `X` 之间的最短路径的长度恰好是 `k`。也就是说，第一次找到目标结点时，你已经处于最短路径中。
**2. 队列的入队和出队顺序是什么？**
如上面的动画所示，我们首先将根结点排入队列。然后在每一轮中，我们逐个处理已经在队列中的结点，并将所有邻居添加到队列中。值得注意的是，新添加的节点`不会`立即遍历，而是在下一轮中处理。
结点的处理顺序与它们`添加`到队列的顺序是`完全相同的顺序`，即先进先出（FIFO）。这就是我们在 BFS 中使用队列的原因

###### 广度优先搜索--模板

使用 BFS 的两个主要方案：`遍历`或`找出最短路径`。通常，这发生在树或图中。BFS 也可以用于更抽象的场景中。

**模板Ⅰ**

```java
/**
 * Return the length of the shortest path between root and target node.
 */
int BFS(Node root, Node target) {
    Queue<Node> queue;  // store all nodes which are waiting to be processed
    int step = 0;       // number of steps neeeded from root to current node
    // initialize
    add root to queue;
    // BFS
    while (queue is not empty) {
        step = step + 1;
        // iterate the nodes which are already in the queue
        int size = queue.size();
        for (int i = 0; i < size; ++i) {
            Node cur = the first node in queue;
            return step if cur is target;
            for (Node next : the neighbors of cur) {
                add next to queue;
            }
            remove the first node from queue;
        }
    }
    return -1;          // there is no path from root to target
}
```

1. 如代码所示，在每一轮中，队列中的结点是**等待处理的结点**。
2. 在每个更外一层的 `while` 循环之后，我们**距离根结点更远一步**。变量 `step` 指示从根结点到我们正在访问的当前结点的距离。

**模板Ⅱ**

有时，确保我们永远**不会访问一个结点两次**很重要。否则，我们可能陷入无限循环。如果是这样，我们可以在上面的代码中添加一个哈希集来解决这个问题。这是修改后的伪代码：

```java
/**
 * Return the length of the shortest path between root and target node.
 */
int BFS(Node root, Node target) {
    Queue<Node> queue;  // store all nodes which are waiting to be processed
    Set<Node> used;     // store all the used nodes
    int step = 0;       // number of steps neeeded from root to current node
    // initialize
    add root to queue;
    add root to used;
    // BFS
    while (queue is not empty) {
        step = step + 1;
        // iterate the nodes which are already in the queue
        int size = queue.size();
        for (int i = 0; i < size; ++i) {
            Node cur = the first node in queue;
            return step if cur is target;
            for (Node next : the neighbors of cur) {
                if (next is not in used) {
                    add next to queue;
                    add next to used;
                }
            }
            remove the first node from queue;
        }
    }
    return -1;          // there is no path from root to target
}
```

有两种情况你不需要使用哈希集：

1. 完全确定没有循环，例如，在树遍历中；
2. 你确实希望多次将结点添加到队列中。

###### **墙与门**

你被给定一个 *m × n* 的二维网格，网格中有以下三种可能的初始化值：

1. `-1` 表示墙或是障碍物
2. `0` 表示一扇门
3. `INF` 无限表示一个空的房间。然后，我们用 `231 - 1 = 2147483647` 代表 `INF`。你可以认为通往门的距离总是小于 `2147483647` 的。
你要给每个空房间位上填上该房间到 *最近* 门的距离，如果无法到达门，则填 `INF` 即可。
 示例：给定二维网格：
```java
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
```
运行完你的函数后，该网格应该变成：
```javascript
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
```
代码：
```java
private static final int EMPTY=Integer.MAX_VALUE;
private static final int GATE=0;
private static final int WALL=-1;
private static final List<int[]> DIRECTIONS= Arrays.asList(
        new int[]{1,0},
        new int[]{-1,0},
        new int[]{0,1},
        new int[]{0,-1}
);
private static int distanceToNearestGate(int[][]rooms,int startRow,
										int startCol){
    int m=rooms.length;
    int n=rooms[0].length;
    int[][] distance=new int[m][n];
    Queue<int[]> queue=new LinkedList<>();
    queue.add(new int[]{startRow,startCol});
    while(!queue.isEmpty()){
        int[] point=queue.poll();
        int row=point[0];
            int col=point[1];
            for(int[] direction:DIRECTIONS){
            int r=row+direction[0];
            int c=col+direction[1];
            if(r<0||c<0||r>=m||c>=n||rooms[r][c]==WALL||distance[r]				[c]!=0){
                continue;
             }
             distance[r][c]=distance[row][col]+1;
             if(rooms[r][c]==GATE){
                return distance[r][c];
             }
             queue.add(new int[]{r,c});
        }
    }
    return Integer.MAX_VALUE;
}
public static void wallsAndGates(int[][] rooms){
    int m=rooms.length;
    if(m==0){
         return;
    }
    int n=rooms[0].length;
    Queue<int[]>queue=new LinkedList<>();
    for(int row=0;row<m;row++){
        for(int col=0;col<n;col++){
            if(rooms[row][col]==GATE){
                 queue.add(new int[]{row,col});
            }
        }
    }
    while(!queue.isEmpty()){
        int[] point=queue.poll();
        int row=point[0];
        int col=point[1];
        for(int[] direction:DIRECTIONS){
            int r=row+direction[0];
            int c=col+direction[1];
            if(r<0||c<0||r>=m||c>=n||rooms[r][c]!=EMPTY){
                continue;
            }
            rooms[r][c]=rooms[row][col]+1;
            queue.add(new int[]{r,c});
        }
    }
}
```
###### **岛屿数量**

​		给定一个由 `'1'`（陆地）和 `'0'`（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
示例 1:

```java
输入:
11110
11010
11000
00000
输出: 1
```
示例 2:
```java
输入:
11000
11000
00100
00011
输出: 3
```
代码：

```java
public static int numIslands(char[][] grid) {
    if(grid==null||grid.length==0){
        return 0;
    }
    int nr=grid.length;
    int nc=grid[0].length;
    int num_islands=0;
    for(int r=0;r<nr;r++){
        for(int c=0;c<nc;c++){
            if(grid[r][c]=='1'){
                num_islands++;
            }
            dfs(grid,r,c);
        }
    }
    return  num_islands;
}
public static void dfs(char[][]grid,int r,int c){
    int nr=grid.length;
    int nc=grid[0].length;
    if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
        return;
    }
    grid[r][c]='0';
    dfs(grid,r-1,c);
    dfs(grid,r+1,c);
    dfs(grid,r,c+1);
    dfs(grid,r,c-1);
}
```

###### **打开转盘锁**

​		你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： `'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'` 。每个拨轮可以自由旋转：例如把 `'9'` 变为  `'0'`，`'0'` 变为 `'9'` 。每次旋转都只能旋转一个拨轮的一位数字。
锁的初始数字为 `'0000'` ，一个代表四个拨轮的数字的字符串。
​		列表 `deadends` 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
​		字符串 `target` 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
示例 1:

```java
输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
输出：6
解释：
可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
因为当拨动到 "0102" 时这个锁就会被锁定。
```
示例 2:
```java
输入: deadends = ["8888"], target = "0009"
输出：1
解释：
把最后一位反向旋转一次即可 "0000" -> "0009"。
```
示例 3:
```java
输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
输出：-1
解释：
无法旋转到目标数字且不被锁定。
```
示例 4:
```java
输入: deadends = ["0000"], target = "8888"
输出：-1
```
提示：
1. 死亡列表 `deadends` 的长度范围为 `[1, 500]`。

2. 目标数字 `target` 不会在 `deadends` 之中。

3. 每个 `deadends` 和 `target` 中的字符串的数字会在 10,000 个可能的情况 `'0000'` 到 `'9999'` 中产生。

代码：

```java
public static int openLock(String[] deadends, String target) {
    int rotateNums=0;
    char[] pos=new char[]{'0','1','2','3','4','5','6','7','8','9'};
    Set<String> set=new HashSet<>(Arrays.asList(deadends));
    LinkedList<String> list=new LinkedList<>();
    if(target==null||target.length()==0||set.contains("0000")){
        return -1;
    }
    list.add("0000");
    int depth=0;
    //广度优先遍历
    while(!list.isEmpty()){
        int size=list.size();
        while(size-- >0){
            String str=list.removeFirst();
            if(str.equals(target)){
                return depth;
            }
            if(!set.contains(str)){
                set.add(str);
                list.addAll(getNextList(str,pos));
            }
        }
        //每一层遍历完，深度加一
        depth++;
    }
    return -1;
}
private static List<String> getNextList(String str,char[] pos){
    List<String> res=new ArrayList<>();
    char[] chars=str.toCharArray();
    for(int i=0;i<chars.length;i++){
        int num=chars[i]-'0';
        char[] newChars=chars.clone();
        newChars[i]=pos[(num+11)%10];    //数字加一
        res.add(new String(newChars));
        newChars[i]=pos[(num+9)%10];
        res.add(new String(newChars));
    }
    return res;
}
```

###### **完全平方数**

给定正整数 *n*，找到若干个完全平方数（比如 `1, 4, 9, 16, ...`）使得它们的和等于 *n*。你需要让组成和的完全平方数的个数最少。
示例 1:

```java
输入: n = 12
输出: 3 
解释: 12 = 4 + 4 + 4.
```
示例 2:
```java
输入: n = 13
输出: 2
解释: 13 = 4 + 9
```

代码：

```java
public static class Node{
    private int val;
    private int step;
    public Node(int val,int step){
        this.val=val;
        this.step=step;
    }
}
public static int numSquares(int n) {
    Queue<Node> queue=new LinkedList<>();
    queue.add(new Node(n,1));
    boolean[] record=new boolean[n];
    while(!queue.isEmpty()){
        int val=queue.peek().val;
        int step=queue.peek().step;
        queue.remove();
        //每一层的广度遍历
        for(int i=1;;i++){
            int nextVal=val-i*i;
            //说明已经最大平分数
            if(nextVal<0){
                break;
            }
            //由于是广度遍历；所以当遍历到0时，肯定是最短路径
            if(nextVal==0){
                return step;
            }
            //当再次出现时没有必要加入，因为在该节点的路径长度肯定不小于第一次出现的路径长度
            if(!record[nextVal]){
                queue.add(new Node(nextVal,step+1));
                record[nextVal]=true;
            }
        }
    }
    return -1;
}
```

#### 三、数组和字符串

##### 1.数组

###### 数组简介

​		**数组**是一种基本的数据结构，用于**按顺序存储元素的集合**。但是元素可以随机存取，因为数组中的每个元素都可以通过数组**索引**来识别。
​		数组可以有一个或多个维度。这里我们从**一维数组**开始，它也被称为线性数组。

![1566274824756](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566274824756.png)

​		在上面的例子中，数组 A 中有 6 个元素。也就是说，A 的长度是 6 。我们可以使用 A[0] 来表示数组中的第一个元素。因此，A[0] = 6 。类似地，A[1] = 3，A[2] = 8，依此类推。

**数组中的操作**

```JAVA
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // 1. Initialize
        int[] a0 = new int[5];
        int[] a1 = {1, 2, 3};
        // 2. Get Length
        System.out.println("The size of a1 is: " + a1.length);
        // 3. Access Element
        System.out.println("The first element is: " + a1[0]);
        // 4. Iterate all Elements
        System.out.print("[Version 1] The contents of a1 are:");
        for (int i = 0; i < a1.length; ++i) {
            System.out.print(" " + a1[i]);
        }
        System.out.println();
        System.out.print("[Version 2] The contents of a1 are:");
        for (int item: a1) {
            System.out.print(" " + item);
        }
        System.out.println();
        // 5. Modify Element
        a1[0] = 4;
        // 6. Sort
        Arrays.sort(a1);
    }
}
```

**动态数组**

数组具有**固定的容量**，我们需要在初始化时指定数组的大小。有时它会非常不方便并可能造成浪费。
因此，大多数编程语言都提供内置的**动态数组**，它仍然是一个随机存取的列表数据结构，但**大小是可变的**。例如，在 C++ 中的 `vector`，以及在 Java 中的 `ArrayList`。

**动态数组中的操作**

```java
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // 1. initialize
        List<Integer> v0 = new ArrayList<>();
        List<Integer> v1;                           
        // v1 == null
        // 2. cast an array to a vector
        Integer[] a = {0, 1, 2, 3, 4};
        v1 = new ArrayList<>(Arrays.asList(a));
        // 3. make a copy
        List<Integer> v2 = v1;                      
        // another reference to v1
        List<Integer> v3 = new ArrayList<>(v1);     
        // make an actual copy of v1
        // 3. get length
        System.out.println("The size of v1 is: " + v1.size());;
        // 4. access element
        System.out.println("The first element in v1 is: " + v1.get(0));
        // 5. iterate the vector
        System.out.print("[Version 1] The contents of v1 are:");
        for (int i = 0; i < v1.size(); ++i) {
            System.out.print(" " + v1.get(i));
        }
        System.out.println();
        System.out.print("[Version 2] The contents of v1 are:");
        for (int item : v1) {
            System.out.print(" " + item);
        }
        System.out.println();
        // 6. modify element
        v2.set(0, 5);       
        // modify v2 will actually modify v1
        System.out.println("The first element in v1 is: " + v1.get(0));
        v3.set(0, -1);
        System.out.println("The first element in v1 is: " + v1.get(0));
        // 7. sort
        Collections.sort(v1);
        // 8. add new element at the end of the vector
        v1.add(-1);
        v1.add(1, 6);
        // 9. delete the last element
        v1.remove(v1.size() - 1);
    }
}
```

###### 二维数组

二维数组的例子：

```java
// "static void main" must be defined in a public class.
public class Main {
    private static void printArray(int[][] a) {
        for (int i = 0; i < a.length; ++i) {
            System.out.println(a[i]);
        }
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; a[i] != null && j < a[i].length; ++j) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        System.out.println("Example I:");
        int[][] a = new int[2][5];
        printArray(a);
        System.out.println("Example II:");
        int[][] b = new int[2][];
        printArray(b);
        System.out.println("Example III:");
        b[0] = new int[3];
        b[1] = new int[5];
        printArray(b);
    }
}
```

**原理**

​		在一些语言中，多维数组实际上是在**内部**作为一维数组实现的，而在其他一些语言中，**实际上**根本没有**多维数组**。

1. C++ 将二维数组存储为一维数组。
下图显示了大小为 M * N 的数组 A 的实际结构：

![1566286249156](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566286249156.png)

因此，如果我们将 A 定义为也包含 *M \* N* 个元素的一维数组，那么实际上 A[i][j] 就等于 A[i * N + j]。

2. 在Java中，二维数组实际上是包含着 M 个元素的一维数组，每个元素都是包含有N 个整数的数组。

  	下图显示了 Java 中二维数组 A 的实际结构：	

![1566286302016](C:\Users\yyb\AppData\Roaming\Typora\typora-user-images\1566286302016.png)

**动态二维数组**

与一维动态数组类似，我们也可以定义动态二维数组。 实际上，它可以只是一个嵌套的动态数组。 你可以自己尝试一下。

##### 2.字符串

字符串实际上是一个 `unicode 字符`数组。

###### **比较函数**

字符串有它自己的`比较函数`（我们将在下面的代码中向你展示比较函数的用法）。

然而，存在这样一个问题：

> 我们可以用 “==” 来比较两个字符串吗？

这取决于下面这个问题的答案：

> 我们使用的语言是否支持`运算符重载`？

1. 如果答案是 `yes` （例如 C++）。我们`可以使用` “==” 来比较两个字符串。

2. 如果答案是 `no` （例如 Java），我们`可能无法使用` “==” 来比较两个字符串。当我们使用  “==” 时，它实际上会比较这两个对象是否是同一个对象。

```java
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // initialize
        String s1 = "Hello World";
        System.out.println("s1 is \"" + s1 + "\"");
        String s2 = s1;
        System.out.println("s2 is another reference to s1.");
        String s3 = new String(s1);
        System.out.println("s3 is a copy of s1.");
        // compare using '=='
        System.out.println("Compared by '==':");
        // true since string is immutable and s1 is binded to "Hello World"
        System.out.println("s1 and \"Hello World\": " + (s1 == "Hello World"));
        // true since s1 and s2 is the reference of the same object
        System.out.println("s1 and s2: " + (s1 == s2));
        // false since s3 is refered to another new object
        System.out.println("s1 and s3: " + (s1 == s3));
        // compare using 'equals'
        System.out.println("Compared by 'equals':");
        System.out.println("s1 and \"Hello World\": " + s1.equals("Hello World"));
        System.out.println("s1 and s2: " + s1.equals(s2));
        System.out.println("s1 and s3: " + s1.equals(s3));
        // compare using 'compareTo'
        System.out.println("Compared by 'compareTo':");
        System.out.println("s1 and \"Hello World\": " + (s1.compareTo("Hello World") == 0));
        System.out.println("s1 and s2: " + (s1.compareTo(s2) == 0));
        System.out.println("s1 and s3: " + (s1.compareTo(s3) == 0));
    }
}
```

###### **是否可变**

不可变意味着一旦字符串被初始化，你就无法改变它的内容。

1. 在某些语言（如 C ++）中，字符串是`可变的`。 也就是说，你可以像在数组中那样修改字符串。

2. 在其他一些语言（如  Java）中，字符串是不可变的。 此特性将带来一些问题。 我们将在下一篇文章中阐明问题和解决方案。

`测试修改操作`来确定你喜欢的语言中的字符串是否可变:

```java
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s1 = "Hello World";
        s1[5] = ',';
        System.out.println(s1);
    }
}
```

**额外操作**

```java
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s1 = "Hello World";
        // 1. concatenate
        s1 += "!";
        System.out.println(s1);
        // 2. find
        System.out.println("The position of first 'o' is: " + s1.indexOf('o'));
        System.out.println("The position of last 'o' is: " + s1.lastIndexOf('o'));
        // 3. get substring
        System.out.println(s1.substring(6, 11));
    }
}
```

###### 不可变字符串 —— 问题和解决方案

**修改操作**

显然，不可变字符串无法被修改。哪怕你只是想修改其中的一个字符，也必须创建一个新的字符串。

**小心 Java 中的字符串**

```java
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s = "";
        int n = 10000;
        for (int i = 0; i < n; i++) {
            s += "hello";
        }
    }
}
```

请注意对于 Java 来说字符串连接有多慢？ 另一方面，在 C++ 中没有明显的性能影响。

在 Java 中，由于字符串是`不可变的`，因此在连接时首先为新字符串分配足够的空间，复制旧字符串中的内容并附加到新字符串。

因此，总时间复杂度将是：

   5 + 5 × 2 + 5 × 3 + … + 5 × n
 = 5 × (1 + 2 + 3 + … + n)
 = 5 × n × (n + 1) / 2,

也就是 `O(n2)`。

**解决方案**

如果你希望你的字符串是可变的，这里有一些替代方案：

**1. 如果你确实希望你的字符串是可变的，则可以将其转换为字符数组。**

```java
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s = "Hello World";
        char[] str = s.toCharArray();
        str[5] = ',';
        System.out.println(str);
    }
}
```

**2. 如果你经常必须连接字符串，最好使用一些其他的数据结构，如 StringBuilder 。 以下代码以 O(n)** **的复杂度运行。**

```java
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        int n = 10000;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append("hello");
        }
        String s = str.toString();
    }
}
```

#### 四、哈希表

`哈希表`是一种使用`哈希函数`组织数据，以支持快速插入和搜索的数据结构。
有两种不同类型的哈希表：哈希集合和哈希映射。

- `哈希集合`是`集合`数据结构的实现之一，用于存储`非重复值`。
- `哈希映射`是`映射` 数据结构的实现之一，用于存储`(key, value)`键值对。
在`标准模板库`的帮助下，哈希表是`易于使用的`。大多数常见语言（如Java，C ++ 和 Python）都支持哈希集合和哈希映射。
通过选择合适的哈希函数，哈希表可以在插入和搜索方面实现`出色的性能`。
在这张卡片中，我们将回答以下问题：
1. 哈希表的`原理`是什么？
2. 如何`设计`哈希表？
3. 如何使用`哈希集`来解决与重复相关的问题？
4. 如何使用`哈希映射`按键聚合信息？
5. 如何在使用哈希表时`设计正确的键？`

##### 1.设计哈希表

###### 哈希表的原理
哈希表的关键思想是使用哈希函数`将键映射到存储桶`。更确切地说，
1. 当我们插入一个新的键时，哈希函数将决定该键应该分配到哪个桶中，并将该键存储在相应的桶中；
2. 当我们想要搜索一个键时，哈希表将使用相同的哈希函数来查找对应的桶，并只在特定的桶中进行搜索。
**示例**
![1566384936414](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1566384936414.png)
在示例中，我们使用y=x%5作为哈希函数。让我们使用这个例子来完成插入和搜索策略：
1. 插入：我们通过哈希函数解析键，将它们映射到相应的桶中。 	
   - 例如，1987 分配给桶 2，而 24 分配给桶 4。
2. 搜索：我们通过相同的哈希函数解析键，并仅在特定存储桶中搜索。 	
   - 如果我们搜索 1987，我们将使用相同的哈希函数将1987 映射到 2。因此我们在桶 2 中搜索，我们在那个桶中成功找到了 1987。
   - 例如，如果我们搜索 23，将映射 23 到 3，并在桶 3 中搜索。我们发现 23 不在桶 3 中，这意味着 23 不在哈希表中。

###### 设计哈希表的关键
在设计哈希表时，你应该注意两个基本因素。
**1.哈希函数**
哈希函数是哈希表中最重要的组件，该哈希表用于将键映射到特定的桶。在上一篇文章中的示例中，我们使用 `y = x % 5` 作为散列函数，其中 `x` 是键值，`y` 是分配的桶的索引。
散列函数将取决于`键值的范围`和`桶的数量。` 
下面是一些哈希函数的示例：
![1566385165957](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1566385165957.png)
​		哈希函数的设计是一个开放的问题。其思想是尽可能将键分配到桶中，理想情况下，完美的哈希函数将是键和桶之间的一对一映射。然而，在大多数情况下，哈希函数并不完美，它需要在桶的数量和桶的容量之间进行权衡。

**2.冲突解决**
		理想情况下，如果我们的哈希函数是完美的一对一映射，我们将不需要处理冲突。不幸的是，在大多数情况下，冲突几乎是不可避免的。例如，在我们之前的哈希函数（*y  =  x ％ 5*）中，1987 和 2 都分配给了桶 2，这是一个`冲突`。

冲突解决算法应该解决以下几个问题：
1. 如何组织在同一个桶中的值？
2. 如果为同一个桶分配了太多的值，该怎么办？
3. 如何在特定的桶中搜索目标值？  

​        根据我们的哈希函数，这些问题与`桶的容量`和可能映射到`同一个桶`的`键的数目`有关。
​        让我们假设存储最大键数的桶有 `N` 个键。
​        通常，如果 *N* 是常数且很小，我们可以简单地使用一个数组将键存储在同一个桶中。如果 *N* 是可变的或很大，我们可能需要使用`高度平衡的二叉树`来代替.

3. **训练**
      `插入`和`搜索`是哈希表中的两个基本操作。
此外，还有基于这两个操作的操作。例如，当我们`删除元素`时，我们将首先搜索元素，然后在元素存在的情况下从相应位置移除元素。

#### 五、二叉树

​		树是一种经常用到的数据结构，用来模拟具有树状结构性质的数据集合。
​		树里的每一个节点有一个根植和一个包含所有子节点的列表。从图的观点来看，树也可视为一个拥有N 个节点和N-1 条边的一个有向无环图。
​		二叉树是一种更为典型的树树状结构。如它名字所描述的那样，二叉树是每个节点最多有两个子树的树结构，通常子树被称作“左子树”和“右子树”。

##### **1.树的遍历**

###### 前序遍历
前序遍历首先访问根节点，然后遍历左子树，最后遍历右子树。

**迭代实现**

```java
/**
 * 二叉树的前序遍历   --迭代的方式
 * @param root
 * @return
 */
public List<Integer> preorderTraversal1(TreeNode root) {
    List<Integer> treeList=new ArrayList<>();
    Stack<TreeNode> stack=new Stack<>();
    if(root==null){
        return  treeList;
    }
    stack.add(root);
    while(!stack.isEmpty()){
        TreeNode treeNode=stack.pop();
        treeList.add(treeNode.val);
        if(treeNode.right!=null){
            stack.push(treeNode.right);
        }
        if(treeNode.left!=null){
            stack.push(treeNode.left);
        }
    }
    return treeList;
}
```

###### 中序遍历
中序遍历是先遍历左子树，然后访问根节点，然后遍历右子树。对于二叉搜索树，我们可以通过中序遍历得到一个递增的有序序列。

**方法一：递归**

```java
class Solution {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List < Integer > res) {
        if (root != null) {
            if (root.left != null) {
                helper(root.left, res);
            }
            res.add(root.val);
            if (root.right != null) {
                helper(root.right, res);
            }
        }
    }
}
```
**复杂度分析**：
时间复杂度：O(n)。递归函数 T(n)=2⋅T(n/2)+1T(n) = 2 \cdot T(n/2)+1T(n)=2⋅T(n/2)+1。
空间复杂度：最坏情况下需要空间O(n)O(n)O(n)，平均情况为O(log⁡n)O(\log n)O(logn)。

**方法二：基于栈的遍历**

```java
public class Solution {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}
```
**算法复杂度：**
时间复杂度：O(n)。
空间复杂度：O(n)。

**方法三：莫里斯遍历**

```java
Javaclass Solution {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;          
                // move to next right node
            } else {                        
                // has a left subtree
                pre = curr.left;
                while (pre.right != null) { 
                    // find rightmost
                    pre = pre.right;
                }
                pre.right = curr;           
                // put cur after the pre node
                TreeNode temp = curr;       
                // store cur node
                curr = curr.left;    
                // move cur to the top of the new tree
                temp.left = null; 
                // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
}
```
**算法复杂度：**
时间复杂度：O(n)。
​		 想要证明时间复杂度是O(n)，最大的问题是找到每个节点的前驱节点的时间复杂度。乍一想，找到每个节点的前驱节点的时间复杂度应该是 O(nlog⁡n)，因为找到一个节点的前驱节点和树的高度有关。
​		但事实上，找到所有节点的前驱节点只需要O(n) 时间。一棵 n个节点的二叉树只有 n−1条边，每条边只可能使用2次，一次是定位节点，一次是找前驱节点。
故复杂度为 O(n)。
空间复杂度：O(n)。使用了长度为 n的数组。

**自己写的算法**

```java
/**
 * 二叉树的中序遍历   --迭代的方式
 * @param root
 * @return
 */
 public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> output=new LinkedList<>();
    Stack<TreeNode> treeNodes=new Stack<>();
    if(root==null){
        return output;
    }
    treeNodes.add(root);
    while (!treeNodes.isEmpty()){
        while(root.left!=null){
            treeNodes.add(root.left);
            root=root.left;
        }
        while(root.right==null){
            TreeNode treeNode=treeNodes.pop();
            output.add(treeNode.val);
            if(treeNodes.isEmpty()){
                break;
            }
            root=treeNodes.peek();
        }
        if(root.right!=null){
            TreeNode treeNode=treeNodes.pop();
            output.add(treeNode.val);
            treeNodes.add(root.right);
            root=treeNodes.peek();
        }
    }
    return output;
}
```

###### 后序遍历
后序遍历是先遍历左子树，然后遍历右子树，最后访问树的根节点。
​		值得注意的是，当你删除树中的节点时，删除过程将按照后序遍历的顺序进行。 也就是说，当你删除一个节点时，你将首先删除它的左节点和它的右边的节点，然后再删除节点本身。
​		另外，后序在数学表达中被广泛使用。 编写程序来解析后缀表示法更为容易。 这里是一个例子：
![1566439996952](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1566439996952.png)
​		您可以使用中序遍历轻松找出原始表达式。 但是程序处理这个表达式时并不容易，因为你必须检查操作的优先级。 
​		如果你想对这棵树进行后序遍历，使用栈来处理表达式会变得更加容易。 每遇到一个操作符，就可以从栈中弹出栈顶的两个元素，计算并将结果返回到栈中。

```java
/**
 * 二叉树的后序遍历   --迭代的方式
 * @param root
 * @return
 */
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> output=new LinkedList<>();
    Stack<TreeNode> treeNodes=new Stack<>();
    if(root==null){
        return output;
    }
    treeNodes.add(root);
    TreeNode pre=null;
    while(!treeNodes.isEmpty()){
        TreeNode cur=treeNodes.peek();
        if((cur.left==null&&cur.right==null)||(pre!=null&&(cur.left==pre||cur.right==pre))){
            output.add(cur.val);
            pre=cur;
            treeNodes.pop();
        }
        else{
            if(cur.right!=null){
                treeNodes.push(cur.right);
            }
            if(cur.left!=null){
                treeNodes.push(cur.left);
            }
        }
    }
    return output;
}
```

###### 层次遍历
层序遍历就是逐层遍历树结构。
`广度优先搜索`是一种广泛运用在树或图这类数据结构中，遍历或搜索的算法。 该算法从一个根节点开始，首先访问节点本身。 然后遍历它的相邻节点，其次遍历它的二级邻节点、三级邻节点，以此类推。
当我们在树中进行广度优先搜索时，我们访问的节点的顺序是按照层序遍历顺序的。

通常，我们使用一个叫做队列的数据结构来帮助我们做广度优先搜索。

```java
/**
 * 二叉树的层次遍历   --迭代的方式
 * @param root
 * @return
 */
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>>output=new ArrayList<>();
    Queue<TreeNode> queue=new LinkedList<>();
    if(root==null){
        return output;
    }
    queue.add(root);
    int level=0;
    while(!queue.isEmpty()){
        output.add(new ArrayList<>());
        int level_length=queue.size();
        for(int i=0;i<level_length;i++){
            TreeNode treeNode=queue.remove();
            output.get(level).add(treeNode.val);
            if(treeNode.left!=null){
                queue.add(treeNode.left);
            }
            if(treeNode.right!=null){
                queue.add(treeNode.right);
            }
        }
        level++;
    }
    return  output;
}
```

##### 2.运用递归解决问题
​		递归是解决树的相关问题最有效和最常用的方法之一。
​		我们知道，树可以以递归的方式定义为一个节点（根节点），它包括一个值和一个指向其他节点指针的列表。 递归是树的特性之一。 因此，许多树问题可以通过递归的方式来解决。 对于每个递归层级，我们只能关注单个节点内的问题，并通过递归调用函数来解决其子节点问题。
​		通常，我们可以通过 “自顶向下” 或 “自底向上” 的递归来解决树问题。

###### **“自顶向下” 的解决方案**

“自顶向下” 意味着在每个递归层级，我们将首先访问节点来计算一些值，并在递归调用函数时将这些值传递到子节点。 所以 “自顶向下” 的解决方案可以被认为是一种**前序遍历**。 具体来说，递归函数 `top_down(root, params)` 的原理是这样的：

```java
return specific value for null node
update the answer if needed                    //anwer <-- params
left_ans = top_down(root.left, left_params)    //left_params<-- root.val, params
right_ans = top_down(root.right, right_params) //right_params<-- root.val, params
return the answer if needed                    //answer<-- left_ans,right_ans
```

例如，思考这样一个问题：给定一个二叉树，请寻找它的最大深度。

​		我们知道根节点的深度是`1`。 对于每个节点，如果我们知道某节点的深度，那我们将知道它子节点的深度。 因此，在调用递归函数的时候，将节点的深度传递为一个参数，那么所有的节点都知道它们自身的深度。 而对于叶节点，我们可以通过更新深度从而获取最终答案。 这里是递归函数 `maximum_depth(root, depth)` 的伪代码：

```java
return if root is null
if root is a leaf node:
	answer = max(answer, depth)     
    // update the answer if needed
	maximum_depth(root.left, depth + 1) 
	// call the function recursively for left child
	maximum_depth(root.right, depth + 1)
	// call the function recursively for right child
```

```java
private int answer;		
// don't forget to initialize answer before call maximum_depth
private void maximum_depth(TreeNode root, int depth) {
    if (root == null) {
        return;
    }
    if (root.left == null && root.right == null) {
        answer = Math.max(answer, depth);
    }
    maximum_depth(root.left, depth + 1);
    maximum_depth(root.right, depth + 1);
}
```

###### “自底向上” 的解决方案

​		“自底向上” 是另一种递归方法。 在每个递归层次上，我们首先对所有子节点递归地调用函数，然后根据返回值和根节点本身的值得到答案。 这个过程可以看作是**后序遍历**的一种。 通常， “自底向上” 的递归函数 `bottom_up(root)` 为如下所示：

```java
return specific value for null node
left_ans = bottom_up(root.left)          
// call function recursively for left child
right_ans = bottom_up(root.right)        
// call function recursively for right child
return answers                           
// answer <-- left_ans, right_ans, root.val
```
​		让我们继续讨论前面关于树的最大深度的问题，但是使用不同的思维方式：对于树的单个节点，以节点自身为根的子树的最大深度`x`是多少？
​		如果我们知道一个根节点，以其**左**子节点为根的最大深度为`l`和以其**右**子节点为根的最大深度为`r`，我们是否可以回答前面的问题？ 当然可以，我们可以选择它们之间的最大值，再加上1来获得根节点所在的子树的最大深度。 那就是 `x = max（l，r）+ 1`
这意味着对于每一个节点来说，我们都可以在解决它子节点的问题之后得到答案。 因此，我们可以使用“自底向上“的方法。下面是递归函数 `maximum_depth(root)` 的伪代码：

```java
return 0 if root is null                 
// return 0 for null node
left_depth = maximum_depth(root.left)
right_depth = maximum_depth(root.right)
return max(left_depth, right_depth) + 1  
// return depth of the subtree rooted at root
```

```java
public int maximum_depth(TreeNode root) {
	if (root == null) {
		return 0;                                   
		// return 0 for null node
	}
	int left_depth = maximum_depth(root.left);
	int right_depth = maximum_depth(root.right);
	return Math.max(left_depth, right_depth) + 1;	
	// return depth of the subtree rooted at root
}
```

###### 总结
了解递归并利用递归解决问题并不容易。
当遇到树问题时，请先思考一下两个问题：
1. 你能确定一些参数，从该节点自身解决出发寻找答案吗？
2. 你可以使用这些参数和节点本身的值来决定什么应该是传递给它子节点的参数吗？

如果答案都是肯定的，那么请尝试使用 “`自顶向下`” 的递归来解决此问题。
或者你可以这样思考：对于树中的任意一个节点，如果你知道它子节点的答案，你能计算出该节点的答案吗？ 如果答案是肯定的，那么 “`自底向上`” 的递归可能是一个不错的解决方法。

#### 六、二叉搜索树

##### 1.二叉搜索树的定义

<Font color='blue'>二叉搜索树（BST）</font>是二叉树的一种特殊表示形式，它满足如下特性：

1. 每个节点中的值必须<Font color='blue'>大于（或等于）</font>存储在其左侧子树中的任何值。
2. 每个节点中的值必须<Font color='blue'>小于（或等于）</font>存储在其右子树中的任何值。

下面是一个二叉搜索树的例子：

![1566559284871](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1566559284871.png)

##### 2.二叉搜索树中的基本操作

###### 在二叉搜索树中实现搜索操作
根据BST的特性，对于每个节点：
1. 如果目标值等于节点的值，则返回节点;
2. 如果目标值小于节点的值，则继续在左子树中搜索;
3. 如果目标值大于节点的值，则继续在右子树中搜索。

我们一起来看一个例子：我们在上面的二叉搜索树中搜索目标值为 4 的节点。
![1566563072706](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1566563072706.png)

#### 七、二分查找

##### 什么是二分查找？

二分查找中使用的术语：
- 标 Target —— 你要查找的值
- 索引 Index —— 你要查找的当前位置
- 左、右指示符 Left，Right —— 我们用来维持查找空间的指标
- 中间指示符 Mid —— 我们用来应用条件来确定我们应该向左查找还是向右查找的索引

##### 它是如何工作的？

​	在最简单的形式中，二分查找对具有指定左索引和右索引的连续序列进行操作。这就是所谓的查找空间。二分查找维护查找空间的左、右和中间指示符，并比较查找目标或将查找条件应用于集合的中间值；如果条件不满足或值不相等，则清除目标不可能存在的那一半，并在剩下的一半上继续查找，直到成功为止。如果查以空的一半结束，则无法满足条件，并且无法找到目标。

##### **如何识别二分查找？**

如前所述，二分查找是一种在每次比较之后*将查找空间一分为二*的算法。每次需要查找集合中的索引或元素时，都应该考虑二分查找。如果集合是无序的，我们可以总是在应用二分查找之前先对其进行排序。

##### **成功的二分查找的 3 个部分**

二分查找一般由三个主要部分组成：

1. ***预处理*** —— 如果集合未排序，则进行排序。
2. **二分查找** —— 使用循环或递归在每次比较后将查找空间划分为两半。
3. **后处理** —— 在剩余空间中确定可行的候选者。

##### 二分查找模板 I

```java
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left <= right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target)
    { 
        eturn mid; 
    }
    else if(nums[mid] < target) {
        left = mid + 1; 
    }
    else {
        right = mid - 1; 
    }
  }
  // End Condition: left > right
  return -1;
}
```

#### 八、N叉树

##### 1. N叉树的遍历

​		一棵二叉树可以按照前序、中序、后序或者层序来进行遍历。在这些遍历方法中，前序遍历、后序遍历和层序遍历同样可以运用到N叉树中。
回顾 - 二叉树的遍历
1. 前序遍历 - 首先访问根节点，然后遍历左子树，最后遍历右子树；
2. 中序遍历 - 首先遍历左子树，然后访问根节点，最后遍历右子树；
3. 后序遍历 - 首先遍历左子树，然后遍历右子树，最后访问根节点；
4. 层序遍历 - 按照从左到右的顺序，逐层遍历各个节点。

​		请注意，N叉树的中序遍历没有标准定义，中序遍历只有在二叉树中有明确的定义。尽管我们可以通过几种不同的方法来定义N叉树的中序遍历，但是这些描述都不是特别贴切，并且在实践中也不常用到，所以我们暂且跳过N叉树中序遍历的部分。

把上述关于二叉树遍历转换为N叉树遍历，我们只需把如下表述:

```java
遍历左子树... 遍历右子树... 
```
变为:
```JAVA
对于每个子节点:
      通过递归地调用遍历函数来遍历以该子节点为根的子树
```
​		我们假设for循环将会按照各个节点在数据结构中的顺序进行遍历：通常按照从左到右的顺序，如下所示。

我们用如图所示的三叉树来举例说明:

![1567149809833](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567149809833.png)

1.前序遍历
​		在N叉树中，前序遍历指先访问根节点，然后逐个遍历以其子节点为根的子树。
​		例如，上述三叉树的前序遍历是: A->B->C->E->F->D->G.

2.后序遍历
​		在N叉树中，后序遍历指前先逐个遍历以根节点的子节点为根的子树，最后访问根节点。
​		例如，上述三叉树的后序遍历是: B->E->F->C->G->D->A.

3.层序遍历
​		N叉树的层序遍历与二叉树的一致。通常，当我们在树中进行广度优先搜索时，我们将按层序的顺序进行遍历。
​		例如，上述三叉树的层序遍历是: A->B->C->D->E->F->G.

**N-ary Tree Preorder Traversal**

给定一个 N 叉树，返回其节点值的*前序遍历*。
例如，给定一个 `3叉树` :

![1567152321104](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567152321104.png)

```java
/**
 * N叉树  前序遍历--递归的方式
 * @param root
 * @return
 */
public List<Integer> preorder(Node root) {
     List<Integer> res=new ArrayList<>();
     helperPreorder(root,res);
     return res;
}
public void helperPreorder(Node root,List<Integer> res){
    if(root!=null){
        res.add(root.val);
        List<Node> nodes=root.children;
        for(Node node:nodes){
            helperPreorder(node,res);
        }
    }
}
```

```java
/**
 * N叉树  前序遍历--迭代的方式
 * @param root
 * @return
 */
public List<Integer> preorder1(Node root) {
    List<Integer> res=new ArrayList<>();
    if(root==null){
        return res;
    }
    Stack<Node> stack=new Stack<>();
    stack.add(root);
    while(!stack.isEmpty()){
        Node node=stack.pop();
        res.add(node.val);
        List<Node> listNodes=node.children;
        for(int i=listNodes.size()-1;i>=0;i--){
            stack.push(listNodes.get(i));
        }
    }
    return res;
}
```

**N-ary Tree Postorder Traversal**

给定一个 N 叉树，返回其节点值的*后序遍历*。
例如，给定一个 `3叉树` :

![1567152631218](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567152631218.png)

返回其后序遍历: `[5,6,3,2,4,1]`.

```java
/**
  * N叉树  后序遍历--递归的方式
  * @param root
  * @return
  */
public List<Integer> postorder(Node root) {
    List<Integer> res=new ArrayList<>();
    if(root==null){
        return res;
    }
    List<Node> nodes=root.children;
    for(Node node:nodes){
        res.addAll(postorder(node));
    }
    res.add(root.val);
    return  res;
}
```

```java
/**
 * N叉树  后序遍历--迭代+栈的方式
 * @param root
 * @return
 */
public List<Integer> postorder1(Node root) {
    List<Integer> res=new ArrayList<>();
    if(root==null){
        return res;
    }
    Stack<Node> stack=new Stack<>();
    stack.add(root);
    Node top;
    int i,len;
    while(!stack.isEmpty()){
        top=stack.pop();
        len=top.children.size();
        res.add(0,top.val);
        for(i=0;i<len;i++){
            stack.push(top.children.get(i));
        }
    }
    return  res;
} 
```

**N叉树的层序遍历**

给定一个 N 叉树，返回其节点值的*层序遍历*。 (即从左到右，逐层遍历)。
例如，给定一个 `3叉树` :

![1567165640519](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567165640519.png)

返回其层序遍历:
```
[
     [1],
     [3,2,4],
     [5,6]
]
```
**说明:**
1. 树的深度不会超过 `1000`。

2. 树的节点总数不会超过 `5000`。

```java
/**
 * N叉树  层次遍历--递归的方式
 */
private List<List<Integer>> res=new ArrayList<>();
public List<List<Integer>> levelOrder1(Node root) {
	helpLevelOrder(root,0);
   	return res;
}
public void helpLevelOrder(Node root,int level){
    if(root==null){
        return;
    }
    if(res.size()<level+1){
        List<Integer> levelList=new ArrayList<>();
        res.add(levelList);
    }
    res.get(level).add(root.val);
    for(Node node:root.children){
        helpLevelOrder(node,level+1);
    }
}
```

##### 2. N叉树的经典递归解法

1. "自顶向下"的解决方案

​	    "自顶向下"意味着在每个递归层次上，我们首先访问节点以获得一些值，然后在调用递归函数时，将这些值传给其子节点。
​        一个典型的 "自顶向下" 函数 `top_down(root, params)` 的工作原理如下：

```java
对于 null 节点返回一个特定值 
如果有需要，对当前答案 answer 进行更新                         
// answer <-- params 
for each child node root.children[k]: 
	ans[k] = top_down(root.children[k], new_params[k])  
	// new_params <-- root.val, params 
如果有需要，返回答案 answer                                 
// answer <-- all ans[k]
```
2. "自底向上"的解决方案

​        "自底向上" 意味着在每个递归层次上，我们首先为每个子节点递归地调用函数，然后根据返回值和根节点本身的值给出相应结果。
一个典型的 "自底向上" 函数 bottom_up(root) 的工作原理如下：
```java
对于 null 节点返回一个特定值 
for each child node root.children[k]: 
    ans[k] = bottom_up(root.children[k]) 
    // 为每个子节点递归地调用函数 
返回答案 answer                          
// answer <- root.val, all ans[k]
```
 **Maximum Depth of N-ary Tree**
给定一个 N 叉树，找到其最大深度。
最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
例如，给定一个 `3叉树` :

![1567168334785](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567168334785.png)

我们应返回其最大深度，3。
说明:
1. 树的深度不会超过 `1000`。
2. 树的节点总不会超过 `5000`。
```java
/**
 * 求N叉树的深度--递归
 * @param root
 * @return
 */
 public int maxDepth(Node root) {
    if (root==null){
        return 0;
    }
    if(root.children==null){
        return 1;
    }
    int len=root.children.size();
    int[] depth=new int[len];
    int max=0;
    for(int i=0;i<len;i++){
        depth[i]=maxDepth(root.children.get(i));
        if(depth[i]>max){
            max=depth[i];
        }
    }
    return max+1;
}
```
**序列化和反序列化 N 叉树**
		序列化是指将一个数据结构转化为位序列的过程，因此可以将其存储在文件中或内存缓冲区中，以便稍后在相同或不同的计算机环境中恢复结构。  
		设计一个序列化和反序列化 N 叉树的算法。一个 N 叉树是指每个节点都有不超过 N 个孩子节点的有根树。序列化 / 反序列化算法的算法实现没有限制。
		你只需要保证 N 叉树可以被序列化为一个字符串并且该字符串可以被反序列化成原树结构即可。
例如，你需要序列化下面的 3-叉 树。

![1567169674377](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567169674377.png)

​		为 [1 [3[5 6] 2 4]]。你不需要以这种形式完成，你可以自己创造和实现不同的方法。
**注意：**

1. `N` 的范围在 `[1, 1000]`
2. 不要使用类成员 / 全局变量 / 静态变量来存储状态。你的序列化和反序列化算法应是无状态的。

#### 九、前缀树

##### 1. 什么是前缀树
​		**前缀树**是**N叉树**的一种特殊形式。通常来说，一个前缀树是用来**存储字符串**的。前缀树的每一个节点代表一个字符串（前缀）。每一个节点会有多个子节点，通往不同子节点的路径上有着不同的字符。子节点代表的字符串是由节点本身的**原始字符串**，以及**通往该子节点路径上所有的字符**组成的。
​		下面是前缀树的一个例子：

![1567171495905](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567171495905.png)

​		在上图示例中，我们在节点中标记的值是该节点对应表示的字符串。例如，我们从根节点开始，选择第二条路径 'b'，然后选择它的第一个子节点 'a'，接下来继续选择子节点 'd'，我们最终会到达叶节点 "bad"。节点的值是由从根节点开始，与其经过的路径中的字符按顺序形成的。 		
值得注意的是，根节点表示**空字符串**。
​		前缀树的一个重要的特性是，节点所有的后代都与该节点相关的字符串有着共同的前缀。这就是**前缀树**名称的由来。
​		我们再来看这个例子。例如，以节点 "b" 为根的子树中的节点表示的字符串，都具有共同的前缀 "b"。反之亦然，具有公共前缀 "b" 的字符串，全部位于以 "b" 为根的子树中，并且具有不同前缀的字符串来自不同的分支。 
​		前缀树有着广泛的应用，例如自动补全，拼写检查等等。

##### 2. 如何表示一个前缀树？
​		前缀树的特别之处在于字符和子节点之间的对应关系。有许多不同的表示前缀树节点的方法，这里我们只介绍其中的两种方法。

###### 方法一 - 数组
​		第一种方法是用数组存储子节点。
​		例如，如果我们只存储含有字母 a 到 z 的字符串，我们可以在每个节点中声明一个大小为26的数组来存储其子节点。对于特定字符 c，我们可以使用 c - 'a' 作为索引来查找数组中相应的子节点。

```JAVA
class TrieNode {
    // change this value to adapt to different cases
    public static final N = 26;
    public TrieNode[] children = new TrieNode[N];
    // you might need some extra values according to different cases
};

/** Usage:
 *  Initialization: TrieNode root = new TrieNode();
 *  Return a specific child node with char c: root.children[c - 'a']
 */
```
​		访问子节点十分**快捷**。访问一个特定的子节点比较**容易**，因为在大多数情况下，我们很容易将一个字符转换为索引。但并非所有的子节点都需要这样的操作，所以这可能会导致**空间的浪费**。

###### 方法二 - Map
​		第二种方法是使用 **Hashmap** 来存储子节点。
​		我们可以在每个节点中声明一个Hashmap。Hashmap的键是字符，值是相对应的子节点。

```JAVA
class TrieNode {
    public Map<Character, TrieNode) children = new HashMap<>();
    
    // you might need some extra values according to different cases
};
/** Usage:
 *  Initialization: TrieNode root = new TrieNode();
 *  Return a specific child node with char c: root.children.get(c)
 */
```
​		通过相应的字符来访问特定的子节点**更为容易**。但它可能比使用数组**稍慢一些**。但是，由于我们只存储我们需要的子节点，因此**节省了空间**。这个方法也更加**灵活**，因为我们不受到固定长度和固定范围的限制。
**补充**
​		我们已经提到过如何表示前缀树中的子节点。除此之外，我们也需要用到一些其他的值。 
​		例如，我们知道，前缀树的每个节点表示一个字符串，但并不是所有由前缀树表示的字符串都是有意义的。如果我们只想在前缀树中存储单词，那么我们可能需要在每个节点中声明一个布尔值（Boolean）作为标志，来表明该节点所表示的字符串是否为一个单词。

#####  3. Insertion in Trie

​		当我们在二叉搜索树中插入目标值时，在每个节点中，我们都需要根据 节点值 和 目标值 之间的关系，来确定目标值需要去往哪个子节点。同样地，当我们向前缀树中插入一个目标值时，我们也需要根据插入的 目标值 来决定我们的路径。  

​		更具体地说，如果我们在前缀树中插入一个字符串 S，我们要从根节点开始。 我们将根据 S[0]（S中的第一个字符），选择一个子节点或添加一个新的子节点。然后到达第二个节点，并根据 S[1] 做出选择。 再到第三个节点，以此类推。 最后，我们依次遍历 S 中的所有字符并到达末尾。 末端节点将是表示字符串 S 的节点。

我们来用伪代码总结一下以上策略：

```java
Initialize: cur = root 
for each char c in target string S: 
	if cur does not have a child c: 
		cur.children[c] = new Trie node 
	cur = cur.children[c] 
cur is the node which represents the string S
```

​		通常情况情况下，你需要自己构建前缀树。构建前缀树实际上就是多次调用插入函数。但请记住在插入字符串之前要 **初始化根节点**。

##### 4. Search in Trie
**搜索前缀** 
		正如我们在前缀树的简介中提到的，所有节点的后代都与该节点相对应字符串的有着共同前缀。因此，很容易搜索以特定前缀开头的任何单词。
		同样地，我们可以根据给定的前缀沿着树形结构搜索下去。一旦我们找不到我们想要的子节点，搜索就以失败终止。否则，搜索成功。
我们来用伪代码总结一下以上策略：

```java
Initialize: cur = root 
for each char c in target string S: 
	if cur does not have a child c: 
		search fails 
	cur = cur.children[c] 
search successes
```
**搜索单词**
		你可能还想知道如何搜索特定的单词，而不是前缀。我们可以将这个词作为前缀，并同样按照上述同样的方法进行搜索。

1. 如果搜索失败，那么意味着没有单词以目标单词开头，那么目标单词绝对不会存在于前缀树中。
2. 如果搜索成功，我们需要检查目标单词是否是前缀树中单词的前缀，或者它本身就是一个单词。为了进一步解决这个问题，你可能需要稍对节点的结构做出修改。

提示：往每个节点中加入布尔值可能会有效地帮助你解决这个问题。

##### 5. 实现 Trie (前缀树)
实现一个 Trie (前缀树)，包含 `insert`, `search`, 和 `startsWith` 这三个操作。
**示例:**
```
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");   
trie.search("app");     // 返回 true
```
**说明:**
- 你可以假设所有的输入都是由小写字母 `a-z` 构成的。
- 保证所有输入均为非空字符串。实现一个 Trie (前缀树)，包含 `insert`, `search`, 和 `startsWith` 这三个操作。

```java
class Trie {
	private class Node{
		public boolean isWord;
		public TreeMap<Character,Node> next;
		public Node(boolean isWord){
			this.isWord=isWord;
			next=new TreeMap<>();
		}
         public Node(){
             this(false);
         }
	}
    
    /** Initialize your data structure here. */
    private Node root;
    private int size;
    public Trie() {
		root=new Node();
		size=0;
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		Node cur=root;
		for(int i=0;i<word.length();i++){
			char c=word.charAt(i);
			if(cur.next.get(c)==null){
				cur.next.put(c,new Node()) ;
			}
			cur=cur.next.get(c);
		}
		if(!cur.isWord){
			cur.isWord=true;
			size++;
		}
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		Node cur=root;
		for(int i=0;i<word.length();i++){
			char c=word.charAt(i);
			if(cur.next.get(c)==null){
				return false;
			}
			cur=cur.next.get(c);
		}
		return cur.isWord;
	}

	/** Returns if there is any word in the trie that starts with the given prefix. */
	public boolean startsWith(String prefix) {
		Node cur=root;
		for(int i=0;i<prefix.length(); i++){
			char ch=prefix.charAt(i);
			if(cur.next.get(ch)==null){
				return false;
			}
			cur=cur.next.get(ch);
		}
		return true;
	}
}
```



#### **十、红黑树**

来源：http://www.tianxiaobo.com/2018/01/11/%E7%BA%A2%E9%BB%91%E6%A0%91%E8%AF%A6%E7%BB%86%E5%88%86%E6%9E%90/

##### 1.红黑树简介

​		红黑树是一种自平衡的二叉查找树，是一种高效的查找树。它是由 Rudolf Bayer 于1978年发明，在当时被称为**对称二叉 B 树(symmetric binary B-trees)**。后来，在1978年被 Leo J. Guibas 和 Robert Sedgewick 修改为如今的**红黑树**。红黑树具有良好的效率，它可在 **O(logN)** 时间内完成查找、增加、删除等操作。因此，红黑树在业界应用很广泛，比如 Java 中的 TreeMap，JDK 1.8 中的 HashMap、C++ STL 中的 map 均是基于红黑树结构实现的。考虑到红黑树是一种被广泛应用的数据结构，所以我们很有必要去弄懂它。

​		![1567249828066](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567249828066.png)

#####  2.红黑树的性质
​		学过二叉查找树的同学都知道，普通的二叉查找树在极端情况下可退化成链表，此时的增删查效率都会比较低下。为了避免这种情况，就出现了一些自平衡的查找树，比如 AVL，红黑树等。这些自平衡的查找树通过定义一些性质，将任意节点的左右子树高度差控制在规定范围内，以达到平衡状态。以红黑树为例，红黑树通过如下的性质定义实现自平衡：

1. 节点是红色或黑色。
2. 根是黑色。
3. 所有叶子都是黑色（叶子是NIL节点）。
4. 每个红色节点必须有两个黑色的子节点。（从每个叶子到根的所有路径上不能有两个连续的红色节点。）
5. 从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点（简称黑高）。

​		有了上面的几个性质作为限制，即可避免二叉查找树退化成单链表的情况。但是，仅仅避免这种情况还不够，这里还要考虑某个节点到其每个叶子节点路径长度的问题。如果某些路径长度过长，那么，在对这些路径上的及诶单进行增删查操作时，效率也会大大降低。这个时候性质4和性质5用途就凸显了，有了这两个性质作为约束，即可保证任意节点到其每个叶子节点路径最长不会超过最短路径的2倍。原因如下：  

​		当某条路径最短时，这条路径必然都是由黑色节点构成。当某条路径长度最长时，这条路径必然是由红色和黑色节点相间构成（性质4限定了不能出现两个连续的红色节点）。而性质5又限定了从任一节点到其每个叶子节点的所有路径必须包含相同数量的黑色节点。此时，在路径最长的情况下，路径上红色节点数量 = 黑色节点数量。该路径长度为两倍黑色节点数量，也就是最短路径长度的2倍。举例说明一下，请看下图：

![1567250064675](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567250064675.png)

上图画出了从根节点 M 出发的到其叶子节点的最长和最短路径。这里偷懒只画出了两条最长路径，实际上最长路径有4条，分别为：

```
M -> Q -> O -> N
M -> Q -> O -> p
M -> Q -> Y -> X
M -> Q -> Y -> Z
```

长度为4，最短路径为 `M -> E`，长度为2。最长路径的长度正好为最短路径长度的2倍。

​		前面说了关于红黑树的一些性质，这里还需要补充一些其他方面的东西。在红黑树简介一节中说到红黑树被发明出来的时候并不叫**红黑树**，而是叫做对**称二叉 B 树**，从名字中可发现红黑树和 B 树（这里指的是2-3树）或许有一定的关联，事实也正是如此。如果对红黑树的性质稍加修改，就能让红黑树和B树形成一一对应的关系。关于红黑树和 B 树关系的细节这里不展开说明了，有兴趣的同学可以参考[《算法》](https://book.douban.com/subject/19952400/)第4版，那本书上讲的很透彻。

#####  3.红黑树操作

​		红黑树的基本操作和其他树形结构一样，一般都包括查找、插入、删除等操作。前面说到，红黑树是一种自平衡的二叉查找树，既然是二叉查找树的一种，那么查找过程和二叉查找树一样，比较简单，这里不再赘述。相对于查找操作，红黑树的插入和删除操作就要复杂的多。尤其是删除操作，要处理的情况比较多，不过大家如果静下心来去看，会发现其实也没想的那么难。好了，废话就说到这，接下来步入正题吧。

###### 3.1 旋转操作

​		在分析插入和删除操作前，这里需要插个队，先说明一下旋转操作，这个操作在后续操作中都会用得到。旋转操作分为左旋和右旋，左旋是将某个节点旋转为其右孩子的左孩子，而右旋是节点旋转为其左孩子的右孩子。这话听起来有点绕，所以还是请看下图：

![1567250225256](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567250225256.png)

上图包含了左旋和右旋的示意图，这里以右旋为例进行说明，右旋节点 M 的步骤如下：

1. 将节点 M 的左孩子引用指向节点 E 的右孩子
2. 将节点 E 的右孩子引用指向节点 M，完成旋转

![1567250259758](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567250259758.png)

###### 3.2 插入

​		红黑树的插入过程和二叉查找树插入过程基本类似，不同的地方在于，红黑树插入新节点后，需要进行调整，以满足红黑树的性质。性质1规定红黑树节点的颜色要么是红色要么是黑色，那么在插入新节点时，这个节点应该是红色还是黑色呢？答案是红色，原因也不难理解。如果插入的节点是黑色，那么这个节点所在路径比其他路径多出一个黑色节点，这个调整起来会比较麻烦（参考红黑树的删除操作，就知道为啥多一个或少一个黑色节点时，调整起来这么麻烦了）。如果插入的节点是红色，此时所有路径上的黑色节点数量不变，仅可能会出现两个连续的红色节点的情况。这种情况下，通过变色和旋转进行调整即可，比之前的简单多了。

​		接下来，将分析插入红色节点后红黑树的情况。这里假设要插入的节点为 N，N 的父节点为 P，祖父节点为 G，叔叔节点为 U。插入红色节点后，会出现5种情况，分别如下：

**3.2.1 情况一**

​		插入的新节点 N 是红黑树的根节点，这种情况下，我们把节点 N 的颜色由红色变为黑色，性质2（根是黑色）被满足。同时 N 被染成黑色后，红黑树所有路径上的黑色节点数量增加一个，性质5（从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点）仍然被满足。

![1567250406319](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567250406319.png)

**3.2.2 情况二**

​		N 的父节点是黑色，这种情况下，性质4（每个红色节点必须有两个黑色的子节点）和性质5没有受到影响，不需要调整。

![1567250446129](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567250446129.png)

**3.2.3 情况三**

​		N 的父节点是红色（节点 P 为红色，其父节点必然为黑色），叔叔节点 U 也是红色。由于 P 和 N 均为红色，所有性质4被打破，此时需要进行调整。这种情况下，先将 P 和 U 的颜色染成黑色，再将 G 的颜色染成红色。此时经过 G 的路径上的黑色节点数量不变，性质5仍然满足。但需要注意的是 G 被染成红色后，可能会和它的父节点形成连续的红色节点，此时需要递归向上调整。

![1567250505607](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567250505607.png)

**3.2.4 情况四**

​		N 的父节点为红色，叔叔节点为黑色。节点 N 是 P 的右孩子，且节点 P 是 G 的左孩子。此时先对节点 P 进行左旋，调整 N 与 P 的位置。接下来按照情况五进行处理，以恢复性质4。

![1567250557878](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567250557878.png)

**3.2.5 情况五**

​		N 的父节点为红色，叔叔节点为黑色。N 是 P 的左孩子，且节点 P 是 G 的左孩子。此时对 G 进行右旋，调整 P 和 G 的位置，并互换颜色。经过这样的调整后，性质4被恢复，同时也未破坏性质5。

![1567250613813](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567250613813.png)

 **3.2.6 插入总结**

​		上面五种情况中，情况一和情况二比较简单，情况三、四、五稍复杂。但如果细心观察，会发现这三种情况的区别在于叔叔节点的颜色，如果叔叔节点为红色，直接变色即可。如果叔叔节点为黑色，则需要选选择，再交换颜色。当把这三种情况的图画在一起就区别就比较容易观察了，如下图：

![1567250664468](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567250664468.png)

###### **3.3 删除**

​		相较于插入操作，红黑树的删除操作则要更为复杂一些。删除操作首先要确定待删除节点有几个孩子，如果有两个孩子，不能直接删除该节点。而是要先找到该节点的前驱（该节点左子树中最大的节点）或者后继（该节点右子树中最小的节点），然后将前驱或者后继的值复制到要删除的节点中，最后再将前驱或后继删除。由于前驱和后继至多只有一个孩子节点，这样我们就把原来要删除的节点有两个孩子的问题转化为只有一个孩子节点的问题，问题被简化了一些。我们并不关心最终被删除的节点是否是我们开始想要删除的那个节点，只要节点里的值最终被删除就行了，至于树结构如何变化，这个并不重要。

​		红黑树删除操作的复杂度在于删除节点的颜色，当删除的节点是红色时，直接拿其孩子节点补空位即可。因为删除红色节点，性质5（从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点）仍能够被满足。当删除的节点是黑色时，那么所有经过该节点的路径上的黑节点数量少了一个，破坏了性质5。如果该节点的孩子为红色，直接拿孩子节点替换被删除的节点，并将孩子节点染成黑色，即可恢复性质5。但如果孩子节点为黑色，处理起来就要复杂的多。分为6种情况，下面会展开说明。

​		在展开说明之前，我们先做一些假设，方便说明。这里假设最终被删除的节点为`X`（至多只有一个孩子节点），其孩子节点为`N`，`X`的兄弟节点为`S`，`S`的左节点为 SL，右节点为 SR。接下来讨论是建立在节点 `X` 被删除，节点 `N` 替换`X`的基础上进行的。这里说明把被删除的节点`X`特地拎出来说一下的原因是防止大家误以为节点`N`会被删除，不然后面就会看不明白。

![1567265892589](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567265892589.png)

在上面的基础上，接下来就可以展开讨论了。红黑树删除有6种情况，分别是：

 **3.3.1 情况一**

**N 是新的根。在这种情形下，我们就做完了。我们从所有路径去除了一个黑色节点，而新根是黑色的，所以性质都保持着。**

上面是维基百科中关于红黑树删除的情况一说明，由于没有配图，看的有点晕。经过思考，我觉得可能会是下面这种情形：

要删除的节点 X 是根节点，且左右孩子节点均为空节点，此时将节点 X 用空节点替换完成删除操作。

可能还有其他情形，大家如果知道，烦请告知。

**3.3.2 情况二**

​		S 为红色，其他节点为黑色。这种情况下可以对 N 的父节点进行左旋操作，然后互换 P 与 S 颜色。但这并未结束，经过节点 P 和 N 的路径删除前有3个黑色节点（P -> X -> N），现在只剩两个了（P -> N）。比未经过 N 的路径少一个黑色节点，性质5仍不满足，还需要继续调整。不过此时可以按照情况四、五、六进行调整。

![1567266013914](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567266013914.png)

 **3.3.2 情况三**

​		N 的父节点，兄弟节点 S 和 S 的孩子节点均为黑色。这种情况下可以简单的把 S 染成红色，所有经过 S 的路径比之前少了一个黑色节点，这样经过 N 的路径和经过 S 的路径黑色节点数量一致了。但经过 P 的路径比不经过 P 的路径少一个黑色节点，此时需要从情况一开始对 P 进行平衡处理。

![1567266074059](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567266074059.png)

 **3.3.4 情况四**

​		N 的父节点为红色，叔叔节点为黑色。节点 N 是 P 的右孩子，且节点 P 是 G 的左孩子。此时先对节点 P 进行左旋，调整 N 与 P 的位置。接下来按照情况五进行处理，以恢复性质4。

![1567266107673](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567266107673.png)

​		这里需要特别说明一下，上图中的节点 N 并非是新插入的节点。当 P 为红色时，P 有两个孩子节点，且孩子节点均为黑色，这样从 G 出发到各叶子节点路径上的黑色节点数量才能保持一致。既然 P 已经有两个孩子了，所以 N 不是新插入的节点。情况四是由以 N 为根节点的子树中插入了新节点，经过调整后，导致 N 被变为红色，进而导致了情况四的出现。考虑下面这种情况（PR 节点就是上图的 N 节点）：

![1567266133375](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567266133375.png)

如上图，插入节点 N 并按情况三处理。此时 PR 被染成了红色，与 P 节点形成了连续的红色节点，这个时候就需按情况四再次进行调整。

 **3.3.5 情况五**

​		S 为黑色，S 的左孩子为红色，右孩子为黑色。N 的父节点颜色可红可黑，且 N 是 P 左孩子。这种情况下对 S 进行右旋操作，并互换 S 和 SL 的颜色。此时，所有路径上的黑色数量仍然相等，N 兄弟节点的由 S 变为了 SL，而 SL 的右孩子变为红色。接下来我们到情况六继续分析。

![1567266176871](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567266176871.png)

 **3.3.6 情况六**

​		S 为黑色，S 的右孩子为红色。N 的父节点颜色可红可黑，且 N 是其父节点左孩子。这种情况下，我们对 P 进行左旋操作，并互换 P 和 S 的颜色，并将 SR 变为黑色。因为 P 变为黑色，所以经过 N 的路径多了一个黑色节点，经过 N 的路径上的黑色节点与删除前的数量一致。对于不经过 N 的路径，则有以下两种情况：

1. 该路径经过 N 新的兄弟节点 SL ，那它之前必然经过 S 和 P。而 S 和 P 现在只是交换颜色，对于经过 SL 的路径不影响。
2. 该路径经过 N 新的叔叔节点 SR，那它之前必然经过 P、 S 和 SR，而现在它只经过 S 和 SR。在对 P 进行左旋，并与 S 换色后，经过 SR 的路径少了一个黑色节点，性质5被打破。另外，由于 S 的颜色可红可黑，如果 S 是红色的话，会与 SR 形成连续的红色节点，打破性质4（每个红色节点必须有两个黑色的子节点）。此时仅需将 SR 由红色变为黑色即可同时恢复性质4和性质5（从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点。）。

![1567266275332](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567266275332.png)

#####  4.总结

​		红黑树是一种重要的二叉树，应用广泛，但在很多数据结构相关的书本中出现的次数并不多。很多书中要么不说，要么就一笔带过，并不会进行详细的分析，这可能是因为红黑树比较复杂的缘故。我在学习红黑树的时候也找了很多资料，但总体感觉讲的都不太好。尤其是在我学习删除操作的时候，很多资料是实在人看不下去，看的我很痛苦。直到我看到维基百科上关于[红黑树](https://en.wikipedia.org/wiki/Red–black_tree)的分析时，很是欣喜。这篇文章分析的很有条理，言简意赅，比很多资料好了太多。本文对红黑树的分析也主要参考了维基百科中的红黑树分析，并对维基百科中容易让人产生疑问和误解的地方进行了说明。同时维基百科中文版红黑树文中的图片较为模糊，这里我重新进行了绘制。需要说明的是，维基百科中文版无法打开了，文中关于维基百科的链接都是英文版的。另外在给大家推荐一个数据结构可视化的网站，里面包含常见数据结构可视化过程，地址为：[t.cn/RZFgryr](https://www.cs.usfca.edu/~galles/visualization/Algorithms.html)。

​		另外，由于红黑树本身比较复杂，实现也较为复杂。在写这篇文章之前，我曾尝试过用 Java 语言实现红黑树的增删操作，最终只写出了新增节点操作，删除没做出来。而且自己写的新增逻辑实在太繁琐，写的不好看，没法拿出来 show。所以最后把 Java 中的 TreeMap 增删相关源码拷出来，按照自己的需求把源码修改了一下，也勉强算是实现了红黑树吧。代码放到了 github 上，传送门 -> [RBTree.java](https://github.com/code4wt/basic_algorithm/blob/master/src/main/java/search/RBTree.java)。

##### 5.Java代码实现

##### 6.红黑树

#### 十一、堆（Heap）大根堆、小根堆

##### 1. 堆的简介

Heap是一种数据结构：
​		1）完全二叉树；
​		2）heap中存储的值是偏序；

**最大堆**：最大堆的任何一个父节点的值，都大于或等于它左、右孩子节点的值。

![1568292541351](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568292541351.png)

**最小堆**：最小堆的任何一个父节点的值，都小于或等于它左、右孩子节点的值。

![1568292698596](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568292698596.png)

堆的根节点叫**堆顶**。
最大堆的堆顶是整个堆中**最大元素**；最小堆的堆顶是整个堆中**最小元素**。

##### 2. 堆的操作

###### 2.1  堆的数据结构

存储结构：一般是由数组来表示堆，`i `节点的父节点下标就为`(i-1)/2`。它的左右节点下标分别为 `2*i+1` 和 `2*i+2`。该性质有对的逻辑结构为完全二叉树得来。

![1568355710947](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568355710947.png)

###### 2.2  插入节点（Insert）

当二叉树插入节点时，插入的位置是完全二叉树的最后一个位置。例如插入一个新的节点2。

![1568295059889](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568295059889.png)

这时，新的节点的父节点10比5大，显然不符合最小堆的性质。于是让新节点"上浮"，和父节点交换位置。

![1568295225933](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568295225933.png)

继续用节点2和父节点7比较，因为2小于7，则让新节点继续"上浮"。

![1568295379853](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568295379853.png)

继续比较，最终新节点2"上浮"到堆顶位置。

![1568295501816](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568295501816.png)

插入操作在数据存储结构形式变化：

![1568354258933](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568354258933.png)

"**上浮（swim）**"操作的实现：

```java
 /**
  * “上浮”调整
  * 时间复杂度 O(logn)
  * @param array  待调整的堆
  */
public void upAdjust(int[] array){
    int childIndex=array.length-1;
    int parentIndex=(childIndex-1)/2;
    //temp保存插入的叶子节点值，用于最后的赋值
    int temp=array[childIndex];
    while(childIndex>0&&temp<array[parentIndex]){
        //无须真正交换，单向赋值即可
        array[childIndex]=array[parentIndex];
        childIndex=parentIndex;
        parentIndex=(parentIndex-1)/2;
    }
    array[childIndex]=temp;
}
```

###### **2.3  删除节点（Delete）**

二叉树删除节点的过程和插入节点的过程正好相反，所删除的是处于堆顶的节点。例如删除最小堆的堆顶节点2。

![1568349366845](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568349366845.png)

这时，为了继续维持完全二叉树的结构，我们把堆的最后一个节点10临时补到堆顶的位置。

![1568349578459](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568349578459.png)

接下来，让暂处堆顶位置的节点10和它的左、右孩子进行比较，如果左、右孩子中最小的一个（显然是节点5）比节点10小，那么让节点10"下沉"。

![1568349843946](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568349843946.png)

接下来继续让节点10和它的左、右孩子做比较，左、右孩子中最小的是节点7，由于10大于7，让节点10继续"下沉"。

![1568350031480](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568350031480.png)

这样一来，二叉树重新得到了调整。

插入操作在数据存储结构形式变化：

![1568354101909](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568354101909.png)

"**下沉（link）**"操作的实现：

```java
/**
 * “下沉”调整
 * 时间复杂度 O(logn)
 * @param array            待调整的堆
 * @param parentIndex      要“下沉”的父节点
 * @param length           堆的有效大小
 */
public void downAdjust(int[] array,int parentIndex,int length){
    //temp保存父节点值，用于最后的赋值
    int temp=array[parentIndex];
    int childIndex=2*parentIndex+1;
    while(childIndex<length){
        //如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
        if(childIndex+1<length&&array[childIndex+1]<array[childIndex]){
            childIndex++;
        }
        //如果父节点小于任何一个孩子的值，则直接跳出
        if(temp<=array[childIndex]){
            break;
        }
        array[parentIndex]=array[childIndex];
        parentIndex=childIndex;
        childIndex=2*childIndex+1;
    }
    array[parentIndex]=temp;
}
```

###### 2.4  构建二叉堆（Build）

构建二叉堆，也就是把一个无序的完全二叉树调整为二叉堆，本质上就是让所有非叶子节点依次"下沉"。

下面举一个无序完全二叉树的例子，如下图所示。

![1568355637226](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568355637226.png)

​		首先，从最后一个非叶子节点开始，也就是从节点21开始。如果节点21大于它左、右孩子中最小的一个，则节点21"下沉"。

![1568355845513](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568355845513.png)

​		接下来，轮到节点9，如果节点9大于它左、右孩子节点中最小的一个，则节点9"下沉"。

![1568355971396](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568355971396.png)

​		然后轮到节点5，如果节点5大于它左、右孩子节点中最小的一个，则节点5"下沉"。事实上节点5小于它的左、右孩子，所以不用改变。

​		接下来轮到节点15，如果节点15大于它的左、右孩子节点中最小的一个，则节点"下沉"。

![1568356295695](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568356295695.png)

​		节点15继续比较，继续"下沉"。

![1568356418617](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568356418617.png)

​		经过上述几轮比较和"下沉"操作，最终每一节点都小于它的左、右孩子节点，一个无序的完全二叉树就被构建成了一个最小堆。

"构建堆"的操作实现：

```java
/**
 * 构建堆
 * 时间复杂度 O(n)
 * @param array  待调整的堆
 */
public void buildHeap(int[] array){
    for(int i=(array.length-2)/2;i>=0;i--){
        downAdjust(array,i,array.length);
    }
}
```

##### 3  优先队列-堆的应用一

###### **3.1  定义**

​		最大优先队列，无论入队顺序如何，都是当前最大的元素优先出队。

​		最小优先队列，无论入队顺序如何，都是当前最小的元素优先出队。

###### **3.2 实现**

通过最大堆的定义可知，最大堆的堆顶是整个堆中的最大元素。因此可以用最大堆来实现最大优先队列。

![1568358222833](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568358222833.png)

**入队操作：**

1.插入新的节点20；

![1568358700587](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568358700587.png)

2.新节点"上浮"到合适的位置。

![1568358746648](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568358746648.png)

**出队操作：**

1.让原堆顶节点21出队。

![1568358816293](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568358816293.png)

2.把最后一个节点10替换到堆顶位置。

![1568358911388](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568358911388.png)

3.节点10"下沉"，节点20成为新堆顶。

![1568359051644](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568359051644.png)

###### 3.3代码实现

```java
import java.util.Arrays;
public class PriorityQueue {
    private int[] array;
    private int size;
    public PriorityQueue(){
        //队列初始化为32
        array=new int[32];
    }

    /**
     * 入队
     * 时间复杂度 O(logn)
     * @param key  入队元素
     */
    public void enQueue(int key){
        //队列长度超出范围，扩容
        if(size >= array.length){
            resize();
        }
        array[size++]=key;
        upAdjust();
    }

    /**
     * 出队
     * 时间复杂度 O(logn)
     * @return
     * @throws Exception
     */
    public int deQueue() throws Exception{
        if(size<=0){
            throw new Exception("the queue is empty");
        }
        //获取堆顶元素
        int head=array[0];
        //让最后一个元素移动到堆顶
        array[0]=array[--size];
        downAdjust();
        return head;
    }

    private void resize(){
        //队列容量翻倍
        int newSize=this.size*2;
        this.array= Arrays.copyOf(this.array,newSize);
    }
    
    /**
     * “上浮”调整
     */
    public void upAdjust(){
        int childIndex=size-1;
        int parentIndex=childIndex/2;
        //temp保存插入的叶子节点值，用于最后的赋值
        int temp=array[childIndex];
        while(childIndex > 0 && temp > array[parentIndex]){
            //无须真正交换，单向赋值即可
            array[childIndex]=array[parentIndex];
            childIndex=parentIndex;
            parentIndex=(parentIndex-1)/2;
        }
        array[childIndex]=temp;
    }

    /**
     * “下沉”调整
     */
    public void downAdjust(){
        //temp保存父节点值，用于最后的赋值
        int parentIndex=0;
        int temp=array[parentIndex];
        int childIndex=1;
        while(childIndex<size){
            //如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if(childIndex+1 < size && array[childIndex+1] > array[childIndex]){
                childIndex++;
            }
            //如果父节点小于任何一个孩子的值，则直接跳出
            if(temp >= array[childIndex]){
                break;
            }
            //无序真正的交换单向赋值即可
            array[parentIndex]=array[childIndex];
            parentIndex=childIndex;
            childIndex=2*childIndex+1;
        }
        array[parentIndex]=temp;
    }
}
```

##### 4.堆排序-堆的应用二

###### 4.1堆排序的思想

​		将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了

###### 4.2堆排序的算法步骤

1.把无序数组构建成二叉堆。需要从小到大排序，则构建成最大堆；需要从大到小排序，则构建成最小堆。

2.循环删除堆顶元素，替换到二叉堆的末尾，调整产生新的堆顶。

![1568361462931](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568361462931.png)

删除节点20，节点19称为新堆顶。

![1568361649067](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568361649067.png)

删除节点19，节点17称为新堆顶

![1568361740980](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568361740980.png)

删除节点17，节点15成为新堆顶

![1568361824832](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568361824832.png)

删除节点15，节点13成为新堆顶

![1568361890482](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568361890482.png)

删除节点13，节点10成为新堆顶

![1568361965941](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568361965941.png)

删除节点10，节点9称为新堆顶

![1568362046383](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568362046383.png)

删除节点9，节点7成为新堆顶

![1568362104142](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568362104142.png)

删除节点7，节点5成为新堆顶。

![1568362180585](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568362180585.png)

到此为止，原本的最大二叉堆已经变成了一个从小到大的有序集合。其数据存储形式变化为：

![1568362738110](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568362738110.png)

###### 4.3堆排序的代码实现

```java
/**
 * “下沉”调整
 * @param array            待调整的堆
 * @param parentIndex      要“下沉”的父节点
 * @param length           堆的有效大小
 */
public void downAdjust(int[] array,int parentIndex,int length){
     //temp保存父节点值，用于最后的赋值
     int temp=array[parentIndex];
     int childIndex=2*parentIndex+1;
     while(childIndex<length){
        //如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
        if(childIndex+1 < length && array[childIndex+1] > array[childIndex]){
            childIndex++;
        }
        //如果父节点小于任何一个孩子的值，则直接跳出
        if(temp >= array[childIndex]){
            break;
        }
        array[parentIndex]=array[childIndex];
        parentIndex=childIndex;
        childIndex=2*childIndex+1;
    }
    array[parentIndex]=temp;
}

/**
 * 构建堆  无序数组构建成堆
 * @param array  待调整的堆
 */
public void buildHeap(int[] array){
    for(int i=(array.length-2)/2;i>=0;i--){
        downAdjust(array,i,array.length);
    }
}

/**
 * 堆排序 （升序）
 * 时间复杂度 O(nlogn)
 * @param array  待调整的堆
 */
public void heapSort(int[] array){
    //1. 把无序数组构建成最大堆
    buildHeap(array);
    //2. 循环删除堆顶元素，移到集合尾部，调整堆产生新的堆顶
    for(int i=array.length-1;i>0;i--){
        //最后1个元素和第1个元素进行交换
        int temp=array[i];
        array[i]=array[0];
        array[0]=temp;
        //"下沉"调整最大堆
        downAdjust(array,0,i);
    }
}
```

##### 5.算法的使用

**5.1.优先队列的使用**

#### 十二、线段树（区间树 Segment Tree）

##### 1 定义

​		线段树（Segment Tree）是一种二叉树形数据结构，1977 年由 Jon Louis Bentley 发明，用以存储区间或线段，并且允许快速查询结构内包含某一点的所有区间。

![1569736000948](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569736000948.png)

​		一个包含 n 个区间的线段树，空间复杂度为 O(n)，查询的时间复杂度则为 O(log n+k)，其中 k 是符合条件的区间数量。

此数据结构亦可推广到高维度。

##### 2 使用场景

##### 3 数据结构表示

#### 十三、多路查找树

<Font color='blue'>多路查找树（muitl-way search tree）</Font>，其每一个节点的孩子数可以多于两个，且每一个结点处可以存储多个元素。

##### 1 2-3树

###### 定义

2-3树是这样一颗多路查找树：其中的每一个结点都具有两个孩子（我们称它为2节点）或三个孩子（我们称它为3结点）。

###### 插入节点

###### 删除节点

##### 2 2-3-4树

###### 定义

###### 插入节点

###### 删除节点

##### 3 B树

**定义**

​		**B树（B-tree）是一种平衡的多路查找树**，2-3树和2-3-4树都是B树的特例。**结点最大的孩子数目称为B树的阶（order）**，因此，2-3树是3阶B树，2-3-4树是4阶B树。



#### 十四、并查集



#### 十五、图

##### 1 图的定义



#### 排序

![1568443295330](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1568443295330.png)

插入排序，冒泡排序和快速排序的排序趟数与序列的初始状态有关

堆排序和选择排序的排序次数与初始状态无关，即最好情况和最坏情况都一样

![img](https://uploadfiles.nowcoder.com/images/20160805/5966933_1470356996533_BA048E772791DFD1C2655A677B1FD1AB)

##### 1.基位排序

###### 1.基位排序（LSD）

###### 2.基位排序（MSD）

##### 2.快速排序

###### 1.快速排序（Dual-pivot）

###### 2.快速排序（Ternary.LR）

###### 3.快速排序（LL）

###### 4.快速排序（LR）

###### 5.快速排序（Ternary.LL）

##### 1.冒泡排序

**思想**：两两比较相邻记录的关键字。如果反序则交换，直到没有反序的记录为止。

**算法实现：**

```java
/**
* 标准的冒泡排序算法
* @param array
*/
public static void BubbleSort(int[] array) {
	for(int i=0;i<array.length;i++) {
		for(int j=array.length-1;j>=i;j--) {
			if(array[j]<array[i]) {
				int flag=array[i];
				array[i]=array[j];
				array[j]=flag;
			}
		}
	}
}
```

```java
/**
* 优化的冒泡排序算法
* @param array
*/
public static void BubbleSort1(int[] array) {
	boolean status=true;
	for(int i=0;i<array.length&&status;i++) {
		status=false;
		for(int j=array.length-1;j>=i;j--) {
			if(array[j]<array[i]) {
				int flag=array[i];
				array[i]=array[j];
				array[j]=flag;
				status=true;
			}
		}
	}
}
```

**时间复杂度：**

​		最好的情况：待排序数组是顺序的  O(n)

​		最坏的情况：待排序数组是逆序的 O(n^2)

**空间复杂度：**

​		额外空间复杂度: O(1)

##### 2.鸡尾酒排序算法

**思想：**又称为双向的冒泡排序、鸡尾酒搅拌排序、搅拌排序、涟漪排序、来回排序或快乐小时排序，是冒泡排序的一种变形。

**算法实现：**





##### 3.简单选择排序

**思想：**就是通过n-1次关键字间的比较，从n-i+1个记录中选出关键字最小的记录，并和第i(1<i<n）个记录交换之。

**算法实现：**

```java
/**
 * 简单选择排序算法
 * @param array
 */
public static void SelectSort(int[] array) {
	int min;
	for(int i=0;i<array.length;i++) {
		min=i;
		for(int j=i+1;j<array.length;j++) {
			if(array[min]>array[j]) {
				min=j;
			}
		}
		if(i!=min) {
			int flag=array[i];
			array[i]=array[min];
			array[min]=flag;
		}
	}
}
```

**时间复杂度：**

​		最好的情况：O(n^2)    交换次数：0  

​		最坏的情况：O(n^2）  交换次数：n-1

##### 4.直接插入排序

**思想：**基本操作是将一个记录插入到已经排好序的有序表中，从而得到一个新的、记录数增1的有序表。

**算法实现：** 

##### 5. 计数排序

**思想：**计数排序（Counting sort）是一种稳定的线性时间排序算法。计数排序使用一个额外的数组[![img](http://pic04.sogoucdn.com/s3/a/100520084/baike/formula/4fc55753007cd3c18576f7933f6f089196732029.svg)](javascript:)，其中第i个元素是待排序数组[![img](http://pic02.sogoucdn.com/s3/a/100520084/baike/formula/7daff47fa58cdfd29dc333def748ff5fa4c923e3.svg)](javascript:)中值等于[![img](http://pic01.sogoucdn.com/s3/a/100520084/baike/formula/add78d8608ad86e54951b8c8bd6c8d8416533d20.svg)](javascript:)的元素的个数。然后根据数组[![img](http://pic04.sogoucdn.com/s3/a/100520084/baike/formula/4fc55753007cd3c18576f7933f6f089196732029.svg)](javascript:)来将[![img](http://pic03.sogoucdn.com/s3/a/100520084/baike/formula/7daff47fa58cdfd29dc333def748ff5fa4c923e3.svg)](javascript:)中的元素排到正确的位置。



#### 数组类算法

​		面试中的算法问题，有很多并不需要复杂的数据结构支撑。就是用数组，就能考察出很多东西了。其实，经典的排序问题，二分搜索等等问题，就是在数组这种最基础的结构中处理问题的，今天主要介绍 LeetCode 中典型的数组类问题，主要介绍这类问题的一些常用解法：做好初始定、基础算法思想应用、对撞指针、滑动窗口法等。

##### 1. 做好初始定义

​		做数组类算法问题的时候，我们常常需要定义一个变量，明确该变量的定义，并且在书写整个逻辑的时候，要不停的维护住这个变量的意义。也特别需要注意初始值和边界的问题。



#### 递归
`递归`是计算机科学中的一个重要概念。它是许多其他算法和数据结构的基础。然而，对于许多初学者来说，掌握它可能是一件非常棘手的事情。
##### 1.递归原理
递归是一种解决问题的有效方法，在递归过程中，函数将自身作为子例程调用
为了确保递归函数不会导致无限循环，它应具有以下属性：
1. 一个简单的`基本案例（basic case）`（或一些案例） —— 能够不使用递归来产生答案的终止方案。
2. 一组规则，也称作`递推关系（recurrence relation）`，可将所有其他情况拆分到基本案例。
注意，函数可能会有多个位置进行自我调用。
###### 示例
让我们从一个简单的编程问题开始：
​		以相反的顺序打印字符串。
首先，我们可以将所需的函数定义为 `printReverse(str[0...n-1])`，其中 `str[0]` 表示字符串中的第一个字符。然后我们可以分两步完成给定的任务：
1. `printReverse(str[1...n-1])`：以相反的顺序打印子字符串 `str[1...n-1]` 。
2. `print(str[0])`：打印字符串中的第一个字符。

请注意，我们在第一步中调用函数本身，根据定义，它使函数递归。
```java
private static void printReverse(char [] str) {
  helper(0, str);
}

private static void helper(int index, char [] str) {
  if (str == null || index >= str.length) {
    return;
  }
  helper(index + 1, str);
  System.out.print(str[index]);
}
```

###### 翻转字符串
编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
**示例 1：**
```
输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
```
**示例 2：**
```
输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
```



#### 回溯法：算法框架与问题求解

##### **1 回溯法思想描述**

```java
在包含问题的所有解的解空间树中，按照深度优先搜索的策略，从根结点出发深度探索解空间树。当探索到某一结点时，要先判断该结点是否包含问题的解，如果包含，就从该结点出发继续探索下去，如果该结点不包含问题的解，则逐层向其祖先结点回溯。（其实回溯法就是对隐式图的深度优先搜索算法）。 若用回溯法求问题的所有解时，要回溯到根，且根结点的所有可行的子树都要已被搜索遍才结束。 而若使用回溯法求任一个解时，只要搜索到问题的一个解就可以结束。
```

##### **2 概念**

​		回溯算法实际上一个类似枚举的搜索尝试过程，主要是在搜索尝试过程中寻找问题的解，当发现已不满足求解条件时，就“回溯”返回，尝试别的路径。
​		回溯法是一种选优搜索法，按选优条件向前搜索，以达到目标。但当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择，这种走不通就退回再走的技术为回溯法，而满足回溯条件的某个状态的点称为“回溯点”。
​		许多复杂的，规模较大的问题都可以使用回溯法，有“通用解题方法”的美称。

##### **3 用回溯法解题的一般步骤：**

1）针对所给问题，确定问题的解空间：
首先应明确定义问题的解空间，问题的解空间应至少包含问题的一个（最优）解。
2）确定结点的扩展搜索规则。
3）以深度优先方式搜索解空间，并在搜索过程中用剪枝函数避免无效搜索。4.

##### 4 回溯法的一般框架

```java
bool finished = FALSE; /* 是否获得全部解? */
backtrack(int a[], int k, data input)
{
    int c[MAXCANDIDATES]; /*这次搜索的候选 */
    int ncandidates; /* 候选数目 */
    int i; /* counter */
    if (is_a_solution(a,k,input))
    process_solution(a,k,input);
    else {
        k = k+1;
        construct_candidates(a,k,input,c,&ncandidates);
        for (i=0; i<ncandidates; i++) {
            a[k] = c[i];
            make_move(a,k,input);
            backtrack(a,k,input);
            unmake_move(a,k,input);
            if (finished) return; /* 如果符合终止条件就提前退出 */
        }
    }
}
```

对于其中的函数和变量，解释如下：
　　**a[]**表示当前获得的部分解；
　　**k**表示搜索深度；
　　**input**表示用于传递的更多的参数；
　　**is_a_solution(a,k,input)**判断当前的部分解向量a[1...k]是否是一个符合条件的解
　　**construct_candidates(a,k,input,c,ncandidates)**根据目前状态，构造这一步可能的选择，存入c[]数组，其长度存入ncandidates
　　**process_solution(a,k,input)**对于符合条件的解进行处理，通常是输出、计数等
　　**make_move(a,k,input)**和**unmake_move(a,k,input)**前者将采取的选择更新到原始数据结构上，后者把这一行为撤销。

##### 5 算法框架

（1）问题框架
​		 设问题的解是一个n维向量(a1,a2,………,an),约束条件是ai(i=1,2,3,…..,n)之间满足某种条件，记为f(ai)。

（2）非递归回溯框架

```java
int a[n],i;
初始化数组a[];
i = 1;
while (i>0(有路可走)   and  (未达到目标))       // 还未回溯到头
{
     if(i > n)                                // 搜索到叶结点
     {   
           搜索到一个解，输出；
     }
     else                                     // 处理第i个元素
     { 
     	a[i]第一个可能的值；
        while(a[i]在不满足约束条件且在搜索空间内)
        {
             a[i]下一个可能的值；
        }
        if(a[i]在搜索空间内)
        {
             标识占用的资源；
             i = i+1;                        // 扩展下一个结点
        }
        else 
        {
              清理所占的状态空间；             // 回溯
              i = i –1; 
        }
}
```

（3）递归的算法框架
​         回溯法是对解空间的深度优先搜索，在一般情况下使用递归函数来实现回溯法比较简单，其中i为搜索的深度，框架如下：

```java
int a[n];
try(int i)
{
	if(i>n)
		输出结果;
    else
    {
    	for(j = 下界; j <= 上界; j=j+1)  
        // 枚举i所有可能的路径
        {
        	 if(fun(j))                 
             // 满足限界函数和约束条件
             {
				a[i] = j;
                 ...                    
                 // 其他操作
                 try(i+1);
                 回溯前的清理工作（如a[i]置空值等）;
             }
        }
    }
}
```

6 回溯算法的经典题解（仅回溯求解）

###### 6.1 全排列（leetcode 46）

https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/（原文作者）

 问题：给定一个**没有重复**数字的序列，返回其所有可能的全排列。

示例:

```java
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

**求解思路**：
以示例输入：[1，2，3]为例，如果让我们手写，要做到不重不漏，我们的一般书写策略为"一位一位确定"，具体是这样的：

1、先写以 1 开始的两个排列：[1, 2, 3]、[1, 3, 2]；
2、再写以 2 开始的两个排列：[2, 1, 3]、[2, 3, 1]；
3、最后写以 3 开始的两个排列：[3, 1, 2]、[3, 2, 1]。

如果数组元素多一点的话，也不怕，我们写的时候遵循下面的原则即可：

1、按数组的顺序来（不要求排序，但我们选取元素的顺序是从左到右的），每次排定 1 个元素；
说明：只有按照顺序才能做到不重漏。
2、新排定的元素一定不能在之前排定的元素中出现。
说明：如果违反了这一条，就不符合"全排列"的定义。

这道题可以作为理解“回溯算法”的入门题。这是一个非常典型的使用 回溯算法 解决的问题。解决回溯问题，我的经验是 一定不要偷懒，拿起纸和笔，把这个问题的递归结构画出来，一般而言，是一个树形结构，这样思路和代码就会比较清晰了。而写代码即是将画出的图用代码表现出来。

**思路分析**：

<font color="red">方法："回溯搜索"算法即"深度优先遍历 + 状态重置 + 剪枝"</font >（这道题没有剪枝）
以示例输入: [1, 2, 3] 为例，<font color='green'>因为是排列问题，只要我们按照顺序选取数字，保证上一层选过的数字不在下一层出现，就能够得到不重不漏的所有排列。</font>

说明：这里"保证上一层选过的数字不在下一层出现"的意思是我们手写的时候，后面选的数字一定不能是前面已经出现过的。为了做到这一点，我们得使用一个数组长度这么长的额外空间，记为数组 used ，只要"上一层"选了一个元素，我们就得"标记一下"，"表示占位"。

画出树形结构如下图

![1569040903940](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569040903940.png)

这里我们介绍什么是"**状态**"。

在递归树里，辅助数组 used 记录的情况和当前已经选出数组成的一个排序，我们统称为当前的"状态"。

注意：

1、这里特别说明一点：虽然我的图是一下子展示出来的，但是我想你画出的图应该是一层一层画出来的；
2、在每一层，我们都有若干条分支供我们选择。下一层的分支数比上一层少 1 ，因为每一层都会排定 1 个数，从这个角度，再来理解一下为什么要使用额外空间记录那些元素使用过；
3、全部的"排列"正是在这棵递归树的所有叶子结点。

我们把上面这件事情给一个形式化的描述：问题的解空间是一棵递归树，求解的过程正是在这棵递归树上搜索答案，而搜索的路径是“深度优先遍历”，它的特点是"不南墙不回头"。

下面解释"**状态重置**"。
​		在程序执行到上面这棵树的叶子结点的时候，此时递归到底，当前根结点到叶子结点走过的路径就构成一个全排列，把它加入结果集，我把这一步称之为"结算"。此时递归方法要返回了，对于方法返回以后，要做两件事情：

（1）释放对最后一个数的占用；
（2）将最后一个数从当前选取的排列中弹出。

​		事实上在每一层的方法执行完毕，即将要返回的时候都需要这么做。<font color='green'>这棵树上的每一个结点都会被访问 2 次，绕一圈回到第 1 次来到的那个结点，第 2 次回到结点的"状态"要和第 1 次来到这个结点时候的"状态"相同，这种程序员赋予程序的操作叫做"状态重置"。</font>

​		"状态重置"是"回溯"的重要操作，"回溯搜索"是有方向的搜索，否则我们要写多重循环，代码量不可控。

说明：
1、数组 used 记录了索引 i 在递归过程中是否被使用过，还可以用哈希表、位图来代替，在下面的代码 1 和代码 2 分别提供了 Java 的代码
2、当程序第 1 次走到一个结点的时候，表示考虑一个数，要把它加入列表，经过更深层的递归又回到这个结点的时候，需要"状态重置"、"恢复现场"，需要把之前考虑的那个数从末尾弹出，这都是在一个列表的末尾操作，最合适的数据结构是栈（Stack）。

请大家在脑子里想一想程序在这棵递归树上"深度优先遍历"执行的路径，理解了"状态重置"这个概念，是不是觉得“回溯搜索”这个名字很形象。

**参考代码 1：使用used数组**

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    // curSize 表示当前的路径 path 里面有多少个元素
    private void generatePermution(int[] nums, boolean[] visited, int curSize, int len, Stack<Integer> path, List<List<Integer>> res) {
        if (curSize == len) {
            // 此时 path 已经保存了 nums 中的所有数字，已经成为了一个排列
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                path.push(nums[i]);
                visited[i] = true;
                generatePermution(nums, visited, curSize + 1, len, path, res);
                // 刚开始接触回溯算法的时候常常会忽略状态重置
                // 回溯的时候，一定要记得状态重置
                path.pop();
                visited[i] = false;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[len];
        if (len == 0) {
            return res;
        }
        generatePermution(nums, used, 0, len, new Stack<>(), res);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        Solution solution = new Solution();
        List<List<Integer>> permute = solution.permute(nums);
        for (int i = 0; i < permute.size(); i++) {
            System.out.println(permute.get(i));
        }
    }
}
```

**参考代码2:   使用哈希表代替 `used` 数组**

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // 使用哈希表检测一个数字是否使用过
        Set<Integer> used = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        backtrack(nums, 0, len, used, stack, res);
        return res;
    }

    private void backtrack(int[] nums, int depth, int len, Set<Integer> used, Stack<Integer> stack, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used.contains(i)) {
                used.add(i);
                stack.push(nums[i]);
                backtrack(nums, depth + 1, len, used, stack, res);
                stack.pop();
                used.remove(i);
            }
        }
    }
}
```

**参考代码3：使用位掩码代替 `used` 数组。这个技巧对于数组 `nums` 不超过 3232 位时有效。**

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;

        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 使用位图，适用于数组 nums 的长度不超过 32 位的情况
        int used = 0;
        Stack<Integer> stack = new Stack<>();

        backtrack(nums, 0, len, used, stack, res);
        return res;
    }

    private void backtrack(int[] nums, int depth, int len, int used, Stack<Integer> stack, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (((used >> i) & 1) == 0) {
                used ^= (1 << i);
                stack.push(nums[i]);

                backtrack(nums, depth + 1, len, used, stack, res);

                stack.pop();
                used ^= (1 << i);
            }
        }
    }
}
```

###### 6.2 全排列Ⅱ （leetcode 47）

https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/

给定一个可包含重复数字的序列，返回所有不重复的全排列。

**示例:**

```java
输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

**思路分析：**

​		这一题是在 ：[全排列](#6.1 全排列（leetcode 46）) 的基础上增加了"序列中的元素可重复"这一条件。因此我们还使用"回溯算法"，只不过在构建递归树的过程中需要"剪枝"，以去除重复元素。

**"去重复"的思想来源**
<Font color='green'>**1 发现困难**</Font>：
		这道题我们完全可以按照全排列，在结果集中去重，不过在实际编码的时候，我们会发现并不好做，因为可能产生重复的是一个列表对象，如果是简单的数字，直接放在哈希表中去重就好了。
<Font color='green'>**2 在以前做过的问题中寻找经验**</Font>：
		我们可以想象一下，在一个数组中去掉重复元素，除了使用哈希表，更容易想到的是将原始数组排序（升序、降序均可）。

​		可以确定的是：重复的元素一定不会是数组第 0 号索引位置的元素。因为要相同元素只保留 1 个，为了方便编码，相同元素我们保留第 1 个或者最后 1 个。

[「力扣」第 15 题："三数之和"](https://leetcode-cn.com/problems/3sum/)就利用这样的思路，在遍历到相同元素的第 2 个的时候，将当前循环 continue 掉，这一步也可以认为是“剪枝操作”。放在这一题也是一样的，同样的思路还可以应用于第 39 题、第 40 题、第 78 题、第 90 题。

下面我具体解释一下这个思想应用于本题的过程。在第 46 题中，如果没有重复元素画出的树形图是这样的。

![1569051774425](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569051774425.png)

<Font color='red'>**方法：回溯 + 剪枝（剪枝的效果是"去重复"）**</Font>
下面这段话是解决有重复元素的序列的排列问题的关键：

​		当数组中有重复元素的时候，可以先将数组排序，排序以后在递归的过程中可以很容易发现重复的元素。当发现重复元素的时候，让这一个分支跳过，以达到“剪枝”的效果，重复的排列就不会出现在结果集中。

​		请看下图，我们把排序以后的数组，就当做它没有重复元素的话，还按照之前的回溯方法，也很容易看出重复的分支，把它剪去即可。

![1569051871499](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569051871499.png)

![1569051888526](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569051888526.png)

![1569051899422](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569051899422.png)

![1569051910414](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569051910414.png)

**说明：**
下面这个代码片段：

```java
if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
    continue;
}
```
中的 used[i - 1] 加或者不加 ! ，提交到力扣的代码测评系统中都是可以通过的，下面我解释一下原因。
		以 [2, 3, 3, 3] 中的 3 个 3 为例，相同的 3 个 3 有 6 个排列。我们只要保留 1 个就好了。
		它们的索引分列是：
		[1, 2, 3] （数组中的数组表示 3 这个元素在 [2, 3, 3, 3] 这个数组中的索引，在全排列中可能的“排列”，下同）
		[1, 3, 2]
		[2, 1, 3]
		[2, 3, 1]
		[3, 1, 2]
		[3, 2, 1]

​		发现其实又是一个全排列问题。首先联系数组 used[i - 1] 的语义：used[i - 1] == true 表示索引 i 位置的前一个位置元素已经使用过。在 used[i - 1] == true 的时候全部 continue 掉，则只剩下了 used[i - 1] == false 的情况，即当前遍历的元素的之前的元素均未使用过，因此保留了 [3, 2, 1] 这种排列。
​		反之理解另一种情况。
​		因此，used[i - 1] 前面加不加感叹号的区别仅在于保留的是相同元素的顺序索引，还是倒序索引；应用于本题，则是相同分支保留的是第 1 个分支还是最后一个分支，它们在结果集中是“等价的”，具体加感叹号对应哪种情况，不加感叹号，对应哪种情况，我个人觉得并不太重要。

​		以下代码根据我在「力扣」第 46 题：全排列 II 中的题解（上文有给出链接）中的示例代码修改而来，具体修改的地方，在下面代码的注释中有说明。

基于第 46 题，做 2 处修改即可：
1、在开始回溯算法之前，对数组进行一次排序操作，这是上面多次提到的；
2、在进入一个新的分支之前，看一看这个数是不是和之前的数一样，<Font color='green'>如果这个数和之前的数一样，并且之前的数还未使用过，那接下来如果走这个分支，就会使用到之前那个和当前一样的数，就会发生重复，此时分支和之前的分支一模一样。（这句话特别关键，可以停下来多看两遍，再看一看上面画的那张图）。</Font>
**参考代码：**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] used;
    private void findPermuteUnique(int[] nums, int depth, Stack<Integer> stack) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // 修改 2：因为排序以后重复的数一定不会出现在开始，故 i > 0
                // 和之前的数相等，并且之前的数还未使用过，
                // 只有出现这种情况，才会出现相同分支
                // 这种情况跳过即可
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                used[i] = true;
                stack.add(nums[i]);
                findPermuteUnique(nums, depth + 1, stack);
                stack.pop();
                used[i] = false;
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        // 修改 1：首先排序，之后才有可能发现重复分支
        Arrays.sort(nums);
        // 如果是降序，需要把 nums 变为包装数组类型，
        //输入 Arrays.sort() 方法才生效，并且还要传入一个比较器，
        //搜索之前，再转为基本类型数组，因此不建议降序排序
// Integer[] numsBoxed = IntStream.of(nums).boxed().
//collect(Collectors.toList()).toArray(new Integer[0]);
// Arrays.sort(numsBoxed, Collections.reverseOrder());
// nums = Arrays.stream(numsBoxed).mapToInt(Integer::valueOf).toArray();
        used = new boolean[len];
        findPermuteUnique(nums, 0, new Stack<>());
        return res;
    }
}
```
###### 6.3 组合总和（leetcode 39）
https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/

**问题**：给定一个无重复元素的数组 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。
`candidates` 中的数字可以无限制重复被选取。

**说明**：
所有数字（包括 target）都是正整数。
解集不能包含重复的组合。

**示例 1**:
```java
输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]
```
**示例 2**:
```java
输入: candidates = [2,3,5], target = 8,
所求解集为:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```

**解题思路**
做搜索、回溯问题的套路是画图，代码其实就是根据画出的树形图写出来的。

那么如何画图呢？
		根据题目中的用例，画一个图，因为是搜索，因此呈现的是一个树形结构图，并且在这个树形结构中会体现出递归结构。
		根据题目中的用例，比对自己画图的结果和题目的结果的差异，如果一样，说明我们的分析没有错；如果不一样，说明我们的分析有误，一定有哪一个环节漏掉了或者分析错误，根据找到的问题调整算法。

针对示例 1：
```java
输入: candidates = [2, 3, 6, 7]，target = 7，所求解集为: [[7], [2, 2, 3]]
```
一开始我画的图是这样的：
思路：以` target = 7 `为根结点，每一个分支做减法。减到 `00 `或者负数的时候，剪枝。其中，减到 00 的时候结算，这里 “结算” 的意思是添加到结果集。

![1569056220592](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569056220592.png)

我把其中文字的部分去掉了，这样大家看得清楚一点：

![1569056253764](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569056253764.png)

**说明：**
		1、一个蓝色正方形表示的是 “尝试将这个数到数组 candidates 中找组合”，那么怎么找呢？挨个减掉那些数就可以了。
		2、在减的过程中，会得到` 0`和负数，也就是被我标红色和粉色的结点：
				得到` 0 `是我们喜欢的，从` 0 `这一点向根结点走的路径（很可能只走过一条边，也算一个路径），就是一个组合，在这一点要做一次结算（把根结点到` 0 `所经过的路径，加入结果集）。
				得到负数就说明这条路走不通，没有必要再走下去了。

总结一下：在减的过程中，得到` 0 `或者负数，就没有必要再走下去，所以这两种情况就分别表示成为叶子结点。此时递归结束，然后要发生回溯。

​		画出图以后，我看了一下，我这张图画出的结果有 4 个 0，对应的路径是` [[2, 2, 3], [2, 3, 2], [3, 2, 2], [7]]`，而示例中的解集只有` [[7], [2, 2, 3]]`，很显然，我的分析出现了问题。问题是很显然的，我的结果集出现了重复。重复的原因是
​		<Font color='green'>后面分支的更深层的边出现了前面分支低层的边的值。</Font>

​		限于我的表达能力有限，大伙意会这句话就可以了，看一看重复的叶子结点 0的路径，想一想重复的原因，或许你会比我说得更清楚更好。

​		但是这个问题也不难解决，把候选数组排个序就好了（想一下，结果数组排个序是不是也可以去重），后面选取的数不能比前面选的数还要小，即 “更深层的边上的数值不能比它上层的边上的数值小”，按照这种策略，剪枝就可以去掉重复的组合。

![1569056300311](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569056300311.png)

**参考代码：**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private int[] candidates;
    private int len;

    private void findCombinationSum(int residue, int start, Stack<Integer> pre) {
        if (residue == 0) {
            // Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
            res.add(new ArrayList<>(pre));
            return;
        }
        // 优化添加的代码2：在循环的时候做判断，尽量避免系统栈的深度
        // residue - candidates[i] 表示下一轮的剩余，如果下一轮的剩余都小于 0 ，         // 就没有必要进行后面的循环了
        // 这一点基于原始数组是排序数组的前提，因为如果计算后面的剩余，只会越来越小
        for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
            pre.add(candidates[i]);
            // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
            findCombinationSum(residue - candidates[i], i, pre);
            pre.pop();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        // 优化添加的代码1：先对数组排序，可以提前终止判断
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombinationSum(target, 0, new Stack<>());
        return res;
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        Solution solution = new Solution();
        List<List<Integer>> combinationSum = solution.combinationSum(candidates, target);
        System.out.println(combinationSum);
    }
}
```

###### 6.4 组合总和Ⅱ（leetcode 40）

**问题**：给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
示例 1:

```java
输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```


示例 2:

```java
输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
```

解题思路：

这道题与上一问的区别在于：

第 39 题：candidates 中的数字可以无限制重复被选取。
第 40 题：candidates 中的每个数字在每个组合中只能使用一次。
编码的不同，就在于，下一层递归的起始索引不一样。

第 39 题：还从候选数组的当前索引值开始。
第 40 题：从候选数组的当前索引值的下一位开始。

相同之处：解集不能包含重复的组合。

为了使得解集不包含重复的组合。我们想一想，如何去掉一个数组中重复的元素，除了使用哈希表以外，我们还可以先对数组升序排序，重复的元素一定不是排好序以后的第 1 个元素和相同元素的第 1 个元素。根据这个思想，我们先对数组升序排序是有必要的。候选数组有序，对于在递归树中发现重复分支，进而“剪枝”是十分有效的。

思路分析：

这道题其实比上一问更简单，思路是：

以 target 为根结点，依次减去数组中的数字，直到小于 00 或者等于 00，把等于 00 的结果记录到结果集中。

当然你也可以以 00 为根结点，依次加上数组中的数字，直到大于 target 或者等于 target，把等于 target 的结果记录到结果集中。

“解集不能包含重复的组合”，就暗示我们得对数组先排个序（“升序”或者“降序”均可，下面示例中均使用“升序”）。
“candidates 中的每个数字在每个组合中只能使用一次”，那就按照顺序依次减去数组中的元素，递归求解即可：遇到 00 就结算且回溯，遇到负数也回溯。
candidates 中的数字可以重复，可以借助「力扣」第 47 题：“全排列 II” 的思想，在搜索的过程中，找到可能发生重复结果的分支，把它剪去。
![1569062987903](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569062987903.png)

![1569063004311](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569063004311.png)

**参考代码 1**：以 target 为根结点，依次减去数组中的数字，直到小于 00 或者等于 00，把等于 00 的结果记录到结果集中。

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {


    // residue 表示剩余，这个值一开始等于 target，基于题目中说明的"所有数字（包括目标数）都是正整数"这个条件
    // residue 在递归遍历中，只会越来越小
    private void findCombinationSum2(int[] candidates, int begin, int len, int residue, Stack<Integer> stack, List<List<Integer>> res) {
        if (residue == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = begin; i < len && residue - candidates[i] >= 0; i++) {
            // 这一步之所以能够生效，其前提是数组一定是排好序的，这样才能保证：
            // 在递归调用的统一深度（层）中，一个元素只使用一次。
            // 这一步剪枝操作基于 candidates 数组是排序数组的前提下
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            stack.add(candidates[i]);
            // 【关键】因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            findCombinationSum2(candidates, i + 1, len, residue - candidates[i], stack, res);
            stack.pop();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // 先将数组排序，这一步很关键
        Arrays.sort(candidates);
        findCombinationSum2(candidates, 0, len, target, new Stack<>(), res);
        return res;
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        Solution solution = new Solution();
        List<List<Integer>> combinationSum2 = solution.combinationSum2(candidates, target);
        System.out.println(combinationSum2);
    }
}
```

这里按照用户 @Aspire 提供的思路，给出从 00 开始，一个使用加法，搜索加到目标数的写法，“前提是排序（升序降序均可）”，然后“剪枝”的操作和上面一样。

![1569063132698](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569063132698.png)

**参考代码 2**：以 00 为根结点，依次加上数组中的数字，直到大于 target 或者等于 target，把等于 target 的结果记录到结果集中。

```C++
#include <iostream>
#include <vector>
#include <map>

using namespace std;

class Solution {
public:

    vector<int> input;
    int target;
    vector<vector<int>> result;
    vector<int> vc;

    void dfs(int index, int sum) {
        // index >= input.size() ，写成 index == input.size() 即可
        // 因为每次都 + 1，在 index == input.size() 剪枝就可以了
        if (sum >= target || index == input.size()) {
            if (sum == target) {
                result.push_back(vc);
            }
            return;
        }
        for (int i = index; i < input.size(); i++) {
            if (input[i] > target) {
                continue;
            }

            // 【我添加的代码在这里】：
            // 1、i > index 表明剪枝的分支一定不是当前层的第 1 个分支
            // 2、input[i - 1] == input[i] 表明当前选出来的数等于当前层前一个分支选出来的数
            // 因为前一个分支的候选集合一定大于后一个分支的候选集合
            // 故后面出现的分支中一定包含了前面分支出现的结果，因此剪枝
            // “剪枝”的前提是排序，升序或者降序均可
            if (i > index && input[i - 1] == input[i]) {
                continue;
            }

            vc.push_back(input[i]);
            sum += input[i];
            dfs(i + 1, sum);
            vc.pop_back();
            sum -= input[i];
        }
    }

    vector<vector<int>> combinationSum2(vector<int> &candidates, int target) {
        // “剪枝”的前提是排序，升序或者降序均可
        sort(candidates.begin(), candidates.end());
        this->input = candidates;
        this->target = target;
        dfs(0, 0);
        return result;
    }
};


int main() {
    cout << "LeetCode 第 40 题：组合问题 II" << endl;
    Solution solution = Solution();

    vector<int> candidates;
    candidates.push_back(10);
    candidates.push_back(1);
    candidates.push_back(2);
    candidates.push_back(7);
    candidates.push_back(6);
    candidates.push_back(1);
    candidates.push_back(5);

    int target = 8;
    vector<vector<int>> res = solution.combinationSum2(candidates, target);
    for (int i = 0; i < res.size(); ++i) {
        for (int j = 0; j < res[i].size(); ++j) {
            cout << res[i][j] << ",";
        }
        cout << "" << endl;
    }
    return 0;
}
```

###### 6.5 N皇后（leetcode 51）

**问题：**n皇后问题研究的是如何将 *n* 个皇后放置在 *n*×*n* 的棋盘上，并且使皇后彼此之间不能相互攻击。

![1569064362810](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1569064362810.png)

上图为 8 皇后问题的一种解法。

给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

示例:

```java
输入: 4
输出: [
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
解释: 4 皇后问题存在两个不同的解法。
```

**思路分析**：

以 4 皇后问题为例，它的“搜索”过程如下，大家完全可以在纸上模拟下面这个过程：



#### **经典算法--操作系统**

##### 1.  LRU缓存
​		运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
​		获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
​		写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
进阶:
你是否可以在 O(1) 时间复杂度内完成这两种操作？
示例:

```JAVA
LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得密钥 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得密钥 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
```

###### 方法 1：有序字典
题目要求实现 LRU 缓存机制，需要在 O(1)时间内完成如下操作：
​		获取键 / 检查键是否存在
​		设置键
​		删除最先插入的键
前两个操作可以用标准的哈希表在 O(1)时间内完成。

```java
class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity; 
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
    public void put(int key, int value) {
        super.put(key, value);
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }
}
```

**复杂度分析**

​		时间复杂度：对于 put 和 get 操作复杂度是 O(1)，因为有序字典中的所有操作：get/in/set/move_to_end/popitem（get/containsKey/put/remove）都可以在常数时间内完成。
​		空间复杂度：O(capacity)，因为空间只用于有序字典存储最多 capacity + 1 个元素。

###### 方法 2：哈希表 + 双向链表

​		这个问题可以用哈希表，辅以双向链表记录键值对的信息。所以可以在 O(1)O(1) 时间内完成 put 和 get 操作，同时也支持 O(1)O(1) 删除第一个添加的节点。

![1567674472729](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567674472729.png)

使用双向链表的一个好处是不需要额外信息删除一个节点，同时可以在常数时间内从头部或尾部插入删除节点。

一个需要注意的是，在双向链表实现中，这里使用一个伪头部和伪尾部标记界限，这样在更新的时候就不需要检查是否是 null 节点。

![1567674503225](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567674503225.png)

```java
import java.util.Hashtable;
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }
    private void addNode(DLinkedNode node) {
        /**
         * Always add the new node right after head.
         */
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    private void removeNode(DLinkedNode node){
        /**
         * Remove an existing node from the linked list.
         */
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }
    private void moveToHead(DLinkedNode node){
        /**
         * Move certain node in between to the head.
         */
        removeNode(node);
        addNode(node);
    }
    private DLinkedNode popTail() {
        /**
         * Pop the current tail.
         */
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
    private Hashtable<Integer, DLinkedNode> cache = new Hashtable<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;
    
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) 
        {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if(node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;
            cache.put(key, newNode);
            addNode(newNode);
            ++size;
            if(size > capacity) {
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
}
```

**复杂度分析**

​		时间复杂度：对于 put 和 get 都是 O(1)。
​		空间复杂度：O(capacity)，因为哈希表和双向链表最多存储 capacity + 1 个元素。

##### 2. LFU缓存

​		设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
​		get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
​		put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
进阶：
你是否可以在 O(1) 时间复杂度内执行两项操作？
示例：

```java
LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) */ );
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回 1
cache.put(3, 3);    // 去除 key 2
cache.get(2);       // 返回 -1 (未找到key 2)
cache.get(3);       // 返回 3
cache.put(4, 4);    // 去除 key 1
cache.get(1);       // 返回 -1 (未找到 key 1)
cache.get(3);       // 返回 3
cache.get(4);       // 返回 4
```

LFU表示最不经常使用算法。注意与LRU最近最少使用算法，相区别。

这种只有put和get两个操作的数据结构一般都会用到哈希表。往往再配合其它数据结构，达到要求的复杂度。

![1567673721950](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567673721950.png)

LFU数据结构

index哈希表：key -> 节点node。
cnt2key链表：每个节点，有频率值和同一频率的key链表。且key链表将最近最少用的值排在末尾。节点中的频率值递增排序，但不一定是连续递增。
节点node：value，cnt——频率，cnt_pos——指向cnt2key链表中，值为cnt的节点。key_pos——指向cnt的节点中key的位置。
难点，key的cnt加1后，要将key从cnt的链表中移动到cnt+1的链表。cnt+1节点可能不存在。

**cnt更新的思路**：

```java
if(cnt2key没有cnt+1的节点){
    在cnt节点后面，插入cnt+1的节点。
}
从cnt节点链表中删除key;
if(cnt节点链表为空) 将cnt节点从cnt2key中删除;
将key加入cnt+1节点链表的头部;
node nd=index[key];
nd.cnt++;
nd.cnt_pos = cnt+1的节点;
nd.key_pos = cnt+1节点链表的头部;
```

get函数取一个存在的key以及put函数更新一个存在的key的值的操作逻辑就如上面了。

put函数在插入新值时，先要判断是否有空间。

当空间不够时，要删除一个频率值最小且最近最少使用的key。

**删除key的思路：**

```java
cnt2key第一个节点A是频率值最小的节点;
从节点A链表的尾部删除一个key;
if(节点A链表为空){
    从cnt2key中删除节点A;
}
从index哈希表中删除key;
```

空间有了，将新key/value添加到index哈希表。将key添加到频率值为1的链表中。

**添加新key的思路**:

```java
if(cnt2key没有频率值为1的节点){
    在cnt2key首部添加频率值为1的节点;
}
将key添加到频率值为1的链表的首部;
新节点node{key,value};
node.cnt_pos = 频率值为1的节点;
node.key_pos = 频率值为1的链表的首部;
index[key] = node;
```

重要逻辑就是前面三个。补充上其它部分后，整体逻辑。

```java
get(key){
   if(如果key不再index中) 返回-1;
   执行上述{cnt更新的思路};
}

put(key,value){
    if(如果key已在index中){
        更新key的值为value;
        执行上述{cnt更新的思路};
    }else{
        if(容量不够) 执行上述{删除key的思路};
        执行上述{添加新key的思路};
    }
}
```

get和put的函数复杂度都为O(1)。

代码：

```java
class LFUCache {
public:  
    list< pair<int, list<int> > > cnt2key;
    typedef list< pair<int, list<int> > >::iterator llit;
    typedef list<int>::iterator lit;
    
    struct node{
        int value;
        int cnt;
        lit key_pos;
        llit cnt_pos;
        node(int _cnt=0):cnt(_cnt){}
    };    
    unordered_map<int,node> index;
    int cap;
    LFUCache(int capacity) :cap(capacity) {
        
    }
    
    int get(int key) {
        if(index.count(key)){
            node & n=index[key];
            update(key,n);
            return n.value;
        }else return -1;
    }
    
    void put(int key, int value) {
        if(index.count(key)){
            node & n=index[key];
            n.value = value;
            update(key,n);
        }else if(cap > 0){
            if(index.size()>=cap){
                llit it = cnt2key.begin();
                int noneed_key = it->second.back();
                it->second.pop_back();
                if(it->second.empty()){
                    cnt2key.erase(it);
                }
                index.erase(noneed_key);
            }
            llit it = cnt2key.begin();
            if(it == cnt2key.end() || it->first != 1){
                cnt2key.push_front(make_pair(1,list<int>()));
                it = cnt2key.begin();
            }
            node nd;
            nd.value = value;
            nd.cnt = 1;
            nd.cnt_pos = it;
            it->second.push_front(key);
            nd.key_pos = it->second.begin();
            index[key]=nd;
        }
    }
    void update(int key,node& n){
        n.cnt++;
        llit next_cnt_pos = next(n.cnt_pos);
        if(next_cnt_pos == cnt2key.end() || next_cnt_pos->first != n.cnt){
            next_cnt_pos = cnt2key.insert(next_cnt_pos,make_pair(n.cnt,list<int>()));
        }
        n.cnt_pos->second.erase(n.key_pos);
        if(n.cnt_pos->second.empty()){
            cnt2key.erase(n.cnt_pos);
        }
        
        n.cnt_pos = next_cnt_pos;
        n.cnt_pos->second.push_front(key);
        n.key_pos = n.cnt_pos->second.begin();
    }
};
```

#### 索引所用到的数据结构

##### 1. 二叉查找树

 

##### 2. B Tree

![1567923909384](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567923909384.png)

###### 2.1 定义
​		根节点至少包括两个孩子；
​		树中每个节点最多含有m个孩子（m>=2）；
​		除根节点和叶节点外，其他每个节点至少有ceil(m/2)个孩子；
​		所有叶子节点都位于同一层；
​		除非每个非终端节点中包含有n个关键字信息，其中
​				a）Ki(i=1...n)为关键字，且关键字按顺序升序排序K(i-1) < Ki；
​				b）关键字的个数n必须满足：[ceil(m/2)-1] <= n <= m-1；
​				c）非叶子结点的指针：P[0]，P[1]，... ，P[M]；其中P[1]指向关键字小于K[1]的子树，P[M]指向关键字大于K[M-1]的子树，其他P[i]指向关键字属于（K[i-1]，K[i]）的子树。

##### 3. B+ Thee

![1567924934137](C:/Users/yyb/AppData/Roaming/Typora/typora-user-images/1567924934137.png)

###### 3.1定义
**B+树是B树的变体，其定义基本与B树相同，除了：**
​		非叶子节点的子树指针与关键字个数相同；
​		非叶子节点的子树指针P[i]，指向关键字值[ K[i]，K[i+1] )的子树；
​		非叶子节点仅用来索引，数据都保存在叶子节点中
​		所有叶子节点均有一个链指针指向下一个叶子结点

#### 初级算法

##### 数组

##### 字符串

##### 链表

##### 树

##### 排序和搜索

##### 动态规划

###### 爬楼梯
假设你正在爬楼梯。需要 *n* 阶你才能到达楼顶。
每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
**注意：**给定 *n* 是一个正整数。
**示例 1：**
```java
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
```
**示例 2：**
```java
输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶
```



##### 设计问题

##### 数学

##### 其他

#### 中级算法

##### 数组和字符串

##### 链表

##### 树和图

##### 回溯算法

##### 排序和搜索

##### 动态规划

##### 设计问题

##### 数学

##### 其他

#### 高级算法

##### 数组和字符串

##### 链表

##### 树和图

##### 回溯算法

##### 排序和搜索

##### 动态规划

##### 设计问题

##### 数学

##### 其他

#### 算法高级

RMQ

树状数组





























































