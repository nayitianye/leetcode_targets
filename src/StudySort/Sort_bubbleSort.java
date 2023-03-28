package StudySort;

/**
 * @author yyb
 * 排序算法_冒泡排序
 */
public class Sort_bubbleSort {

    /**
     * 冒泡排序写法一
     * 一边比较一边向后两两交换，将最大值/最小值冒泡到最后一位。
     *
     * @param arr 数组 arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    //如果左边的数大于右边的数,则交换,保证右边的数字最大
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 冒泡排序写法二
     * 使用一个变量记录当前轮次的比较是否发生过交换，如果没有发生交换表示已经有序，不再继续排序。
     *
     * @param arr 数组 arr
     */
    public static void bubbleSort1(int[] arr) {
        // 初始时 swapped 为 true,否则排序过程无法启动
        boolean swapped = true;
        for (int i = 0; i < arr.length - 1; i++) {
            // 如果没有发生过交换，说明剩余部分已经有序，排序完成
            if (!swapped) {
                break;
            }
            // 设置 swapped 为 false,如果发生交换,则将其置为 true
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    swap(arr, j, j + 1);
                    // 表示发生了交换
                    swapped = true;
                }
            }
        }
    }

    /**
     * 冒泡排序写法三
     * 除了使用变量记录当前轮次是否发生交换外，再使用一个变量记录上次发生交换的位置，下一轮排序时到达上次交换的位置就停止比较。
     *
     * @param arr
     */
    public static void bubbleSort2(int[] arr) {
        boolean swapped = true;
        // 最后一个没有经过排序的元素的下标
        int indexOfLastUnsortedElement = arr.length - 1;
        // 上次发生交换的位置
        int swappedIndex = -1;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < indexOfLastUnsortedElement; i++) {
                if (arr[i] > arr[i + 1]) {
                    // 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    swap(arr, i, i + 1);
                    // 表示发生了交换
                    swapped = true;
                    // 更新交换的位置
                    swappedIndex = i;
                }
            }
            // 最后一个没有经过排序的元素的下标就是最后一次发生交换的位置
            indexOfLastUnsortedElement = swappedIndex;
        }
    }

    /**
     * 交换元素_写法一:引入变量
     *
     * @param arr 数组 arr
     * @param i   下标 i
     * @param j   下标 j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 交换元素_写法二:不引入变量，两个值之间通过加法，但是会出现值溢出
     *
     * @param arr 数组 arr
     * @param i   下标 i
     * @param j   下标 j
     */
    private static void swap1(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] + arr[j];
            arr[j] = arr[i] - arr[j];
            arr[i] = arr[i] - arr[j];
        }
    }

    /**
     * 交换元素_写法三:不引入变量，两个值之间通过减法
     *
     * @param arr 数组 arr
     * @param i   下标 i
     * @param j   下标 j
     */
    private static void swap2(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] - arr[j];
            arr[j] = arr[i] + arr[j];
            arr[i] = arr[j] - arr[i];
        }
    }

    /**
     * 交换元素_写法四:不引入变量，两个值之间通过异或
     *
     * @param arr 数组 arr
     * @param i   下标 i
     * @param j   下标 j
     */
    private static void swap3(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[j] ^ arr[i];
        }
    }

}
