import com.sun.scenario.effect.impl.prism.PrFilterContext;

import java.util.*;

public class TargetHeap {

    public static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
    }
    /**
     * 自定义的优先队列
     */
//    public static class PriorityQueue {
//        private  ListNode[] array;
//        private  int size;
//        public  PriorityQueue(){
//            //队列初始化为32
//            array=new ListNode[32];
//        }
//        public PriorityQueue(int k){
//            //队列初始化为32
//            array=new ListNode[k];
//        }
//
//        /**
//         * 入队
//         * 时间复杂度 O(logn)
//         * @param node  入队元素
//         */
//        public void enQueue(ListNode node){
//            //队列长度超出范围，扩容
//            if(size >= array.length){
//                resize();
//            }
//            array[size++]=node;
//            upAdjust();
//        }
//
//        /**
//         * 出队
//         * 时间复杂度 O(logn)
//         * @return
//         * @throws Exception
//         */
//        public ListNode deQueue(){
//            if(size<=0){
//                return null;
//            }
//            //获取堆顶元素
//            ListNode head=array[0];
//            //让最后一个元素移动到堆顶
//            array[0]=array[--size];
//            downAdjust();
//            return head;
//        }
//
//        private void resize(){
//            //队列容量翻倍
//            int newSize=this.size*2;
//            this.array= Arrays.copyOf(this.array,newSize);
//        }
//
//        private boolean isEmpty(){
//            return size==0;
//        }
//        /**
//         * “上浮”调整
//         */
//        public void upAdjust(){
//            int childIndex=size-2;
//            int parentIndex=childIndex/2;
//            //temp保存插入的叶子节点值，用于最后的赋值
//            ListNode temp=array[childIndex];
//            while(childIndex > 0 && temp.val < array[parentIndex].val){
//                //无须真正交换，单向赋值即可
//                array[childIndex]=array[parentIndex];
//                childIndex=parentIndex;
//                parentIndex=(parentIndex-1)/2;
//            }
//            array[childIndex]=temp;
//        }
//
//        /**
//         * “下沉”调整
//         */
//        public void downAdjust(){
//            //temp保存父节点值，用于最后的赋值
//            int parentIndex=0;
//            ListNode temp=array[parentIndex];
//            int childIndex=1;
//            while(childIndex<size){
//                //如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
//                if(childIndex+1 < size && array[childIndex+1].val <= array[childIndex].val){
//                    childIndex++;
//                }
//                //如果父节点小于任何一个孩子的值，则直接跳出
//                if(temp.val < array[childIndex].val){
//                    break;
//                }
//                //无序真正的交换单向赋值即可
//                array[parentIndex]=array[childIndex];
//                parentIndex=childIndex;
//                childIndex=2*childIndex+1;
//            }
//            array[parentIndex]=temp;
//        }
//    }
//
//    public static ListNode mergeKLists(ListNode[] lists) {
//        int num=0;
//        for(int i=0;i<lists.length;i++){
//            ListNode list=lists[i];
//            while(list!=null){
//                num++;
//                list=list.next;
//            }
//        }
//        PriorityQueue queue=new PriorityQueue(num);
//        for(int i=0;i<lists.length;i++){
//            ListNode list=lists[i];
//            while(list!=null){
//                queue.enQueue(new ListNode(list.val));
//                list=list.next;
//            }
//        }
//        ListNode head=new ListNode(0);
//        ListNode flag=head;
//        while(!queue.isEmpty()){
//            flag.next=queue.deQueue();
//            flag=flag.next;
//        }
//        return head.next;
//    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        for(int i=0;i<nums.length;i++){
            queue.add(nums[i]);
        }
        for(int i=0;i<nums.length;i++){
            nums[i]=queue.poll();
        }
        return nums[nums.length-k-1];
    }

    //region 347. 前 K 个高频元素  2019/10/3  优先队列
    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     *
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     *
     * 示例 2:
     * 输入: nums = [1], k = 1
     * 输出: [1]
     *
     * 说明：
     * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
     *
     * @param nums
     * @param k
     * @return
     */
    private List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer,Integer> map=new TreeMap<>();
        for(int num:nums){
            if(map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else{
                map.put(num,1);
            }
        }

        PriorityQueue<Freq> pq=new PriorityQueue<>();

        //比较器的使用
        //PriorityQueue<Freq> pq=new PriorityQueue<>(new FreqComparator());

        //匿名类
       /* PriorityQueue<Freq> pq=new PriorityQueue<>(new Comparator<Freq>() {
            @Override
            public int compare(Freq o1, Freq o2) {
                return o1.freq-o2.freq;
            }
        });*/

       //避免构造自定义类
        /*PriorityQueue<Integer> pq=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1)-map.get(o2);
            }
        });*/

        //lambda表达式
        /*PriorityQueue<Integer> pq=new PriorityQueue<>(
                (a,b)->map.get(a)-map.get(b)
        );*/
        for(int key:map.keySet()){
            if(pq.size()<k){
                pq.add(new Freq(key,map.get(key)));
            }else if(map.get(key)>pq.peek().freq){
                pq.poll();
                pq.add(new Freq(key,map.get(key)));
            }
        }
        LinkedList<Integer> res=new LinkedList<>();
        while(!pq.isEmpty()){
            res.add(pq.poll().e);
        }
        return res;
    }

    private class Freq implements Comparable<Freq>{
        public int e,freq;
        public Freq(int e,int freq){
            this.e=e;
            this.freq=freq;
        }
        @Override
        public int compareTo(Freq o) {
            if(this.freq<o.freq){
                return -1;
            }else if(this.freq>o.freq){
                return 1;
            }else{
                return 0;
            }
        }
    }

    private class FreqComparator implements Comparator<Freq>{
        @Override
        public int compare(Freq o1, Freq o2) {
            return o1.freq-o2.freq;
        }
    }
    //endregion

    public static void main(String[] args) {
        ListNode node=new ListNode(-9);
        node.next=new ListNode(-7);
        node.next.next=new ListNode(-7);
        ListNode node1=new ListNode(-6);
        node1.next=new ListNode(-4);
        node1.next.next=new ListNode(-1);
        node1.next.next.next=new ListNode(-1);
        ListNode node2=new ListNode(-6);
        node2.next=new ListNode(-5);
        node2.next.next=new ListNode(-2);
        node2.next.next.next=new ListNode(0);
        node2.next.next.next.next=new ListNode(0);
        node2.next.next.next.next.next=new ListNode(1);
        node2.next.next.next.next.next.next=new ListNode(2);
        ListNode node3=new ListNode(-9);
        node3.next=new ListNode(-8);
        node3.next.next=new ListNode(-6);
        node3.next.next.next=new ListNode(-5);
        node3.next.next.next.next=new ListNode(-4);
        node3.next.next.next.next.next=new ListNode(1);
        node3.next.next.next.next.next.next=new ListNode(2);
        node3.next.next.next.next.next.next.next=new ListNode(4);
        ListNode node4=new ListNode(-10);
        ListNode node5=new ListNode(-5);
        node5.next=new ListNode(2);
        node5.next.next=new ListNode(-3);
        ListNode[] lists=new ListNode[6];
        lists[0]=node;
        lists[1]=node1;
        lists[2]=node2;
        lists[3]=node3;
        lists[4]=node4;
        lists[5]=node5;

    }
}


