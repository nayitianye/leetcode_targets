import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode_tag_queue
 * leetcode 标签 队列
 * 346、353、363、582、621、641、622、862、933
 */

public class TargetQueue {
    public static void main(String[] args) {

    }


    /**
     * 346. 数据流中的移动平均值
     * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，
     * 计算其所有整数的移动平均值。
     * 示例:
     * MovingAverage m = new MovingAverage(3);
     * m.next(1) = 1
     * m.next(10) = (1 + 10) / 2
     * m.next(3) = (1 + 10 + 3) / 3
     * m.next(5) = (10 + 3 + 5) / 3
     *
     */
    class MovingAverage {
        private int size=0;
        private double sum=0.0;
        private Queue<Integer> queue=new LinkedList<>();
        public MovingAverage(int size) {
            this.size=size;
        }

        public double next(int val) {
            sum+=val;
            queue.offer(val);
            if(queue.size()>this.size){
                sum-=queue.remove();
            }
            return sum/queue.size();
        }
    }



}
