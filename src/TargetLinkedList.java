/**
 * @author yyb
 * leetcode_tag_linked_list
 * leetcode 标签 链表
 */
public class TargetLinkedList {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val=x;
        }

        /**
         * 链表节点的构造函数
         * 使用arr为参数，创建一个链表，当前的ListNode为链表头结点
         * @param arr
         */
        public ListNode(int[] arr){
            if(arr==null||arr.length==0){
                throw new IllegalArgumentException("arr can not be empty");
            }
            this.val=arr[0];
            ListNode cur=this;
            for(int i=1;i<arr.length;i++){
                cur.next=new ListNode(arr[i]);
                cur=cur.next;
            }
        }

        /**
         * 以当前节点为头结点的链表信息字符串
         * @return
         */
        @Override
        public String toString() {
            StringBuilder res=new StringBuilder();
            ListNode cur=this;
            while(cur!=null){
                res.append(cur.val+"->");
                cur=cur.next;
            }
            res.append("NULL");
            return res.toString();
        }
    }

    //region 203. 移除链表元素  2019/10/2
    /**
     * 删除链表中等于给定值 val 的所有节点。
     * 示例:
     * 输入: 1->2->6->3->4->5->6, val = 6
     * 输出: 1->2->3->4->5
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        while(head!=null&&head.val==val){
//            ListNode delNode=head;
//            head=head.next;
//            delNode.next=null;
            head=head.next;
        }
        if(head==null){
            return null;
        }
        ListNode prev=head;
        while(prev.next!=null){
            if(prev.next.val==val){
//                ListNode delNode=prev.next;
//                prev.next=delNode.next;
//                delNode.next=null;
                prev.next=prev.next.next;
            }else{
                prev=prev.next;
            }
        }
        return head;
    }

    /**
     * 使用了虚拟头结点的是情景
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements1(ListNode head, int val) {
        ListNode dummyHead=new ListNode(-1);
        dummyHead.next=head;
        ListNode prev=dummyHead;
        while(prev.next!=null){
            if(prev.next.val==val){
                prev.next=prev.next.next;
            }else{
                prev=prev.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 递归的求解方式
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        if(head==null){
            return null;
        }
        head.next=removeElements2(head.next,val);
        return head.next.val==val?head.next:head;
    }
    //endregion

    public static void main(String[] args) {
        int[] nums={1,2,6,3,4,6};
        ListNode head=new ListNode(nums);
        System.out.println(head);
        ListNode res=(new TargetLinkedList()).removeElements2(head,6);
        System.out.println(res);
    }
}
