package StudySort;

/**
 * @author yyb
 * 排序算法_堆排序
 */
public class Sort_heapSort {


    /**
     * 堆排序
     * @param arr   未排序的数组 arr
     */
    public static void heapSort(int[] arr) {
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            //  将最大值交换到数组最后
            swap(arr, 0, i);
            //  调整剩余数组，使其满足大顶堆
            maxHeapify(arr, 0, i);
        }
    }

    /**
     * 构建初始大顶堆
     * @param arr 数组 arr
     */
    public static void buildMaxHeap(int[] arr) {
        // 从最后一个非叶子结点开始调整大顶堆，最后一个非叶子结点的下标就是 arr.length/2-1
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, 0, i);
        }
    }

    /**
     * 调整大顶堆
     *
     * @param arr      完全二叉树
     * @param i        根结点的位置
     * @param heapSize 剩余未排序的数字的数量，剩余堆的大小
     */
    public static void maxHeapify(int[] arr, int i, int heapSize) {
        // 左子结点下标
        int left = 2 * i + 1;
        // 右子结点下标
        int right = left + 1;
        // 记录根结点、左子树结点、右子树结点三者中的最大值下标
        int largest = i;
        // 与左子树结点比较
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        // 与右子树结点比较
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            // 将最大值交换为根结点
            swap(arr, i, largest);
            maxHeapify(arr, largest, heapSize);
        }
    }

    /**
     * 交换数组 arr 中 i 和 j 下标的值
     * @param arr 数组 arr
     * @param i 下标 i
     * @param j 下标 j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
