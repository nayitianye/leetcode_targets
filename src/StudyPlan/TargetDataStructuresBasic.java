package StudyPlan;

import java.util.*;

/**
 * @author yyb
 * leetcode_studyplan_data_structures_Basic
 * leetcode 学习计划 数据结构基础
 */
public class TargetDataStructuresBasic {

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

    //region    20230417    2. 两数相加

    /**
     * https://leetcode.cn/problems/add-two-numbers/
     * @param l1  链表 l1
     * @param l2  链表 l2
     * @return  两个数相加，并以相同形式返回一个表示和的链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
    //endregion

    //region    20230413    5. 最长回文子串

    /**
     * https://leetcode.cn/problems/longest-palindromic-substring/
     *
     * @param s 一个字符串 s
     * @return 找到 s 中最长的回文子串
     */
    public String longestPalindrome1(String s) {
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

    //region    20230405    15. 三数之和

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

    //region    20230417    24. 两两交换链表中的节点

    /**
     * https://leetcode.cn/problems/swap-nodes-in-pairs
     * @param head  你一个链表头节点 head
     * @return  两两交换其中相邻的节点，并返回交换后链表的头节点
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
    //endregion

    //region    20230412    43. 字符串相乘

    /**
     * https://leetcode.cn/problems/multiply-strings/
     *
     * @param num1 非负整数 num1
     * @param num2 非负整数 num2
     * @return 返回 num1 和 num2 的乘积
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        if (num1.equals("1")) {
            return num2;
        }
        if (num2.equals("1")) {
            return num1;
        }
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addStrings1(ans, curr.reverse().toString());
        }
        return ans;
    }

    public String addStrings1(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }
    //endregion

    //region    20230417    48. 旋转图像

    /**
     * https://leetcode.cn/problems/rotate-image
     * @param matrix  一个 n × n 的二维矩阵 matrix 表示一个图像,将图像顺时针旋转 90 度
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }
    //endregion

    //region    20230412    49. 字母异位词分组

    /**
     * https://leetcode.cn/problems/group-anagrams/description/
     *
     * @param strs 字符串数组 strs
     * @return 将字母异位词组合在一起 , 可以按任意顺序返回结果列表
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            //将每个出现次数大于0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    stringBuffer.append((char) ('a' + i));
                    stringBuffer.append(counts[i]);
                }
            }
            String key = stringBuffer.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
    //endregion

    //region    20230408    75. 颜色分类

    /**
     * https://leetcode.cn/problems/sort-colors/
     *
     * @param nums 含红色、白色和蓝色、共 n 个元素的数组 nums
     */
    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        // all in [0, zero) = 0
        // all in [zero, i) = 1
        // all in [two, len - 1] = 2

        // 循环终止条件是 i == two，那么循环可以继续的条件是 i < two
        // 为了保证初始化的时候 [0, zero) 为空，设置 zero = 0，
        // 所以下面遍历到 0 的时候，先交换，再加
        int zero = 0;
        // 为了保证初始化的时候[two,len-1]为空，设置 two=len
        // 所以下面遍历到 2 的时候，先减，再交换
        int two = len;
        int i = 0;
        // 当 i==two 上面的三个子区间正好覆盖了全部数组
        // 因此，循环可以继续的条件时 i < two
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, i, two);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
    //endregion

    //region    20230417    82. 删除排序链表中的重复元素 II

    /**
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii
     * @param head  一个已排序的链表的头 head
     * @return  删除原始链表中所有重复数字的节点，只留下不同的数字
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

    //region    20230408    119. 杨辉三角 II

    /**
     * https://leetcode.cn/problems/pascals-triangle-ii/
     *
     * @param rowIndex 非负索引 rowIndex
     * @return 返回「杨辉三角」的第 rowIndex 行
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                cur.set(j, cur.get(j - 1) + cur.get(j));
            }
            cur.add(1);//部上每层的最后一个1
        }
        return cur;
    }
    //endregion

    //region    20230405    136. 只出现一次的数字

    /**
     * https://leetcode.cn/problems/single-number/
     *
     * @param nums 非空整数数组 nums
     * @return 找出那个只出现了一次的元素
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
    //endregion

    //region    20230417    142. 环形链表 II

    /**
     * https://leetcode.cn/problems/linked-list-cycle-ii
     * @param head  链表的头节点  head
     * @return  返回链表开始入环的第一个节点。 如果链表无环，则返回 null
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
    //endregion

    //region    20230417    160. 相交链表

    /**
     * https://leetcode.cn/problems/intersection-of-two-linked-lists
     * @param headA  单链表的头节点 headA
     * @param headB  单链表的头节点 headB
     * @return  返回两个单链表相交的起始节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }
    //endregion

    //region    20230405    169. 多数元素

    /**
     * https://leetcode.cn/problems/majority-element/
     *
     * @param nums 大小为 n 的数组 nums
     * @return 返回其中的多数元素
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
    //endregion

    //region    20230410    238. 除自身以外数组的乘积

    /**
     * https://leetcode.cn/problems/product-of-array-except-self/
     *
     * @param nums 整数数组 nums
     * @return 返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积
     */
    public int[] productExceptSelf(int[] nums) {
        int CountZero = 0;
        int Mul = 1;
        int FirstZero = 0;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                CountZero++;
                if (CountZero == 1) {
                    FirstZero = i;
                }
                if (CountZero > 1) {
                    return res;
                }
            } else {
                Mul *= nums[i];
            }
        }
        if (CountZero == 1) {
            res[FirstZero] = Mul;
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            res[i] = Mul / nums[i];
        }
        return res;
    }
    //endregion

    //region    20230411    290. 单词规律

    /**
     * https://leetcode.cn/problems/word-pattern/
     *
     * @param pattern 一种规律 pattern
     * @param s       一个字符串 s
     * @return 判断 s 是否遵循相同的规律
     */
    public boolean wordPattern(String pattern, String s) {
        String[] strs = s.trim().split(" ");
        if (pattern.length() != strs.length) {
            return false;
        }
        Map<Object, Integer> map = new HashMap<>();
        for (Integer i = 0; i < strs.length; i++) {
            if (map.put(pattern.charAt(i), i) != map.put(strs[i], i)) {
                return false;
            }
        }
        return true;
    }
    //endregion

    //region    20230410    334. 递增的三元子序列

    /**
     * https://leetcode.cn/problems/increasing-triplet-subsequence/
     *
     * @param nums 整数数组 nums
     * @return 存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }
    //endregion

    //region    20230410    409. 最长回文串

    /**
     * https://leetcode.cn/problems/longest-palindrome/
     *
     * @param s 包含大写字母和小写字母的字符串 s
     * @return 通过这些字母构造成的 最长的回文串
     */
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            } else {
                hashMap.put(s.charAt(i), 1);
            }
        }
        int res = 0, maxSinge = 0;
        for (Integer value : hashMap.values()) {
            if (value % 2 == 0) {
                res += value;
            } else {
                res += value / 2 * 2;
                maxSinge = 1;
            }
        }
        return res + maxSinge;
    }
    //endregion

    //region    20230410    415. 字符串相加

    /**
     * https://leetcode.cn/problems/add-strings/
     *
     * @param num1 非负整数 num1
     * @param num2 非负整数 num2
     * @return 它们的和并同样以字符串形式返回
     */
    public String addStrings(String num1, String num2) {
        StringBuffer res = new StringBuffer();
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int buffer = 0;
        while (index1 >= 0 || index2 >= 0) {
            int sum = 0;
            if (index1 >= 0 && index2 >= 0) {
                int a = num1.charAt(index1) - '0';
                int b = num2.charAt(index2) - '0';
                sum = a + b + buffer;

            } else if (index1 >= 0) {
                int a = num1.charAt(index1) - '0';
                sum = a + buffer;

            } else {
                int b = num2.charAt(index2) - '0';
                sum = b + buffer;
            }
            res.append(String.valueOf(sum % 10));
            buffer = sum / 10;
            index1--;
            index2--;
        }
        if (buffer >= 1) {
            res.append(String.valueOf(buffer));
        }
        return res.reverse().toString();
    }
    //endregion

    //region    20230410    560. 和为 K 的子数组

    /**
     * https://leetcode.cn/problems/subarray-sum-equals-k
     *
     * @param nums 整数数组 nums
     * @param k    一个整数 k
     * @return 统计并返回 该数组中和为 k 的连续子数组的个数
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (hashMap.containsKey(pre - k)) {
                count += hashMap.get(pre - k);
            }
            hashMap.put(pre, hashMap.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
    //endregion

    //region    20230417    707. 设计链表

    /**
     * https://leetcode.cn/problems/design-linked-list/
     */
    class MyLinkedList {

        private  class Node{
            int val;
            Node next;
            Node(int val){
                this.val = val;
                this.next = null;
            }
        }
        private Node head,tail;
        private int size;

        /**
         * 链表构造函数
         */
        public MyLinkedList() {
            Node p= new Node(0);
            this.head = p;
            this.tail = p;
            this.size = 0;
        }

        /**
         *获取链表中第 index 个节点的值。
         * @param index 索引
         * @return 索引为index 对应的值 无效返回-1
         */
        public int get(int index) {
            //如果超出范围直接返回-1
            if(index<0||index>=this.size) return -1;
            else{
                Node cur = this.head;
                for(int i =0 ;i<index;i++){
                    cur= cur.next;
                }
                return cur.val;
            }
        }

        /**
         * 在链表的第一个元素之前添加一个值为 val 的节点。
         * @param val
         */
        public void addAtHead(int val) {
            if(this.size==0){
                this.head.val = val;
            }else{
                Node tmp = new Node(val);
                tmp.next = this.head;
                this.head = tmp;//加了之后要更新head 的值
            }
            this.size ++;
        }

        /**
         * 将值为 val 的节点追加到链表的最后一个元素。
         * @param val
         */
        public void addAtTail(int val) {
            if(this.size==0){
                this.tail.val = val;
            }else {
                Node tmp = new Node(val);
                this.tail.next = tmp;
                this.tail = tmp;//加了之后要更新tail的值
            }
            this.size++;
        }

        /**
         * 在链表中的第 index 个节点之前添加值为 val  的节点。
         * 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
         * 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
         * @param index
         * @param val
         */
        public void addAtIndex(int index, int val) {
            if(index>this.size){//超出范围结束
                return;
            }else if(index<=0){//比零小，加在head
                addAtHead(val);
            }else if(index==this.size){//index为size说明实在tail之后加
                addAtTail(val);
            }else {
                Node tmp = new Node(val);
                Node cur = this.head;
                for(int i=0;i<index-1;i++){
                    cur = cur.next;
                }//加的位置是cur的下一个
                tmp.next = cur.next;
                cur.next = tmp;
                this.size ++;//别忘记加size
            }
        }

        /**
         * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
         * @param index
         */
        public void deleteAtIndex(int index) {
            if(index>=0&&index<this.size)//索引有效才能删
            {
                if(index==0)//删head
                {
                    this.head = this.head.next;
                }else
                {
                    Node cur = this.head;
                    for(int i=0;i<index-1;i++)
                    {
                        cur = cur.next;
                    }
                    cur.next = cur.next.next;
                    if(index==this.size-1){
                        tail=cur;
                    }//删tail 要更新tail
                }
                this.size--;//别忘记了减size
            }
        }
    }
    //endregion

    public static void main(String[] args) {
        new TargetDataStructuresBasic().addStrings("123", "11");
    }
}
