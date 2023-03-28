package StudySort;

/**
 * @author yyb
 * 排序算法_插入排序
 */
public class Sort_insertSort {

    /**
     * 插入排序_交换法：在新数字插入过程中，不断与前面的数字交换，直到找到自己合适的位置。
     *
     * @param arr   未排序的数组 arr
     */
    public static void insertSort(int[] arr) {
        // 从第二个数开始，往前插入数字
        for (int i = 1; i < arr.length; i++) {
            // j 记录当前数字下标
            int j = i;
            // 当前数字比前一个数字小，则将当前数字与前一个数字交换
            while (j >= 1 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                //更新当前数字下标
                j--;
            }
        }
    }

    /**
     * 插入排序_移动法：在新数字插入过程中，与前面的数字不断比较，前面的数字不断向后挪出位置，当新数字找到自己的位置后，插入一次即可。
     * @param arr   未排序的数组 arr
     */
    public static void insertSort1(int[] arr) {
        // 从第二个数开始，往前插入数字
        for (int i = 1; i < arr.length; i++) {
            int currentNumber = arr[i];
            int j = i - 1;
            //寻找插入位置的过程中，不断地将比currentNumber大的数字向后挪
            while(j>=0 && currentNumber<arr[j]){
                arr[j+1]=arr[j];
                j--;
            }
            // 两种情况会跳出循环：
            // 1.遇到一个小于或等于 currentNumber 的数字，跳出循环，currentNumber 就坐到它后面
            // 2.已经走到数列头部，仍然没有遇到小于或等于 currentNumber 的数字，也会跳出循环，此时 j 等于 -1，currentNumber 就坐到数列头部。
            arr[j+1]=currentNumber;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
