package StudySort;

/**
 * @author yyb
 * 排序算法_冒泡排序
 */
public class Sort_selectionSort {

    /**
     * 选择排序思想：双重循环遍历数组，每经过一轮比较，找到最小元素的下标，将其交换至首位。
     *
     * @param arr 数组 arr
     */
    public static void selectionSort(int[] arr) {
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    // 记录最小值的下标
                    minIndex = j;
                }
            }
            // 将最小元素交换到首位
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    /**
     * 二元选择排序：每轮选择时记录最小值和最大值，可以把数组需要遍历的范围缩小一倍。
     *
     * @param arr 数组 arr
     */
    public static void selectionSort2(int[] arr) {
        int minIndex, maxIndex;
        // i 只需要遍历一半
        for (int i = 0; i < arr.length / 2; i++) {
            minIndex = i;
            maxIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    // 记录最小值的下标
                    minIndex = j;
                }
                if (arr[maxIndex] < arr[j]) {
                    // 记录最大值的下标
                    maxIndex = j;
                }
            }
            // 如果 minIndex 和 maxIndex 都相等，那么他们必定都等于 i，且后面的所有数字都与 arr[i] 相等，此时已经排序完成
            if (minIndex == maxIndex) {
                break;
            }
            //将最小元素交换至首位
            swap(arr, i, minIndex);
            //如果最大值的下标刚好时i,由于arr[i] 和 arr[minIndex] 已经交换了，所以这里要更新 minIndex的值
            if (maxIndex == i) {
                maxIndex = minIndex;
            }
            //将最大元素交换至末尾
            int lastIndex = arr.length - 1 - i;
            swap(arr, lastIndex, maxIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
