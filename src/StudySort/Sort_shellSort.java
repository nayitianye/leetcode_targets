package StudySort;

/**
 * @author yyb
 * 排序算法_希尔排序
 */
public class Sort_shellSort {

    /**
     * 希尔排序_方法一：针对同一个gap值，将同一组的从前到后全遍历一遍；
     * @param arr 未排序的数组 arr
     */
    public static void shellSort(int[] arr) {
        // 间隔序列，在希尔排序中我们称之为增量序列
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 分组
            for (int groupStartIndex = 0; groupStartIndex < gap; groupStartIndex++) {
                //插入排序
                for (int currentIndex = groupStartIndex + gap; currentIndex < arr.length; currentIndex += gap) {
                    // currentNumber 占起来，开始找位置
                    int currentNumber = arr[currentIndex];
                    int preIndex = currentIndex - gap;
                    while (preIndex >= groupStartIndex && currentNumber < arr[preIndex]) {
                        //向后挪位置
                        arr[preIndex + gap] = arr[preIndex];
                        preIndex -= gap;
                    }
                    // currentNumber 找到了自己的位置，坐下
                    arr[preIndex + gap] = currentNumber;
                }
            }
        }
    }

    /**
     * 希尔排序_方法二：是针对同一个gap值，从第gap 位置开始向后逐个遍历，每次只完成当前位置数据的位置判断，同组的在gap之后的数据，
     * 会在gap+ i 且i==gap 时再处理，这期间先处理了 (gap,gap+gap)这几个数据
     * @param arr  未排序的数组 arr
     */
    public static void shellSort1(int[] arr) {
        // 间隔序列，在希尔排序中我们称之为增量序列
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从 gap 开始，按照顺序将每个元素依次向前插入自己所在的组
            for (int i = gap; i < arr.length; i++) {
                // currentNumber 占起来，开始找位置
                int currentNumber = arr[i];
                // 该组前一个数字的索引
                int preIndex = i - gap;
                while (preIndex >= 0 && currentNumber < arr[preIndex]) {
                    // 向后挪位置
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                // currentNumber 找到了自己的位置，坐下
                arr[preIndex + gap] = currentNumber;
            }
        }
    }

    /**
     * 希尔排序：Knuth 增量序列
     * @param arr   未排序的数组 arr
     */
    public static void shellSortByKnuth(int[] arr) {
        // 找到当前数组需要用到的 knuth 序列中的最大值
        int maxKnuthNumber = 1;
        while (maxKnuthNumber <= arr.length / 3) {
            maxKnuthNumber = maxKnuthNumber * 3 + 1;
        }
        // 增量按照 knuth 序列规则依次递减
        for (int gap = maxKnuthNumber; gap > 0; gap = (gap - 1) / 3) {
            // 从 gap 开始，按照顺序将每个元素依次向前插入自己所在的组
            for (int i = gap; i < arr.length; i++) {
                // currentNumber 占起来，开始找位置
                int currentNumber = arr[i];
                // 该组前一个数字的索引
                int preIndex = i - gap;
                while (preIndex >= 0 && currentNumber < arr[preIndex]) {
                    // 向后挪位置
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                // currentNumber 找到了自己的位置，坐下
                arr[preIndex + gap] = currentNumber;
            }
        }
    }
}
