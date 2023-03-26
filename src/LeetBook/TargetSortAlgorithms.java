package LeetBook;

import java.util.Arrays;

/**
 * @author yyb
 * leetcode_leetbook_sort_algorithms
 * leetbook 排序算法图文学
 * @url https://leetcode.cn/leetbook/detail/sort-algorithms/
 */
public class TargetSortAlgorithms {

    //region    20230326    剑指 Offer 45. 把数组排成最小的数
    public String minNumber(int[] nums) {
        bubbleSortMinNumber(nums);
        return Arrays.toString(nums).replaceAll("\\[|]|,|\\s", "");
    }

    public static void bubbleSortMinNumber(int[] arr) {
        boolean swapped = true;
        // 最后一个没有经过排序的元素下标
        int lastIndexOfUnsortedElement = arr.length - 1;
        // 上次发生交换的位置
        int swappedIndex = -1;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < lastIndexOfUnsortedElement; i++) {
                if (("" + arr[i] + arr[i + 1]).compareTo("" + arr[i + 1] + arr[i]) > 0) {
                    //如果“”+arr[i]+arr[i+1]组成的字符串大于“”+arr[i+1]+arr[i]组成的字符串，则交换
                    swap(arr, i, i + 1);
                    //表示发生了交换
                    swapped = true;
                    //更新交换的位置
                    swappedIndex = i;
                }
            }
            //最后一个没有经过排序的元素的下标就是最后一次发生交换的位置
            lastIndexOfUnsortedElement = swappedIndex;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    //endregion

    //region    20230326    283. 移动零
    public void moveZeroes(int[] nums) {
        //记录末尾 0 的数量
        int zerosCount = 0;
        for (int i = 0; i < nums.length - zerosCount; i++) {
            if (nums[i] == 0) {
                for (int j = i; j < nums.length - zerosCount - 1; j++) {
                    exchange(nums, j, j + 1);
                }
                // 未尾多一个0，记录下来，以缩小遍历范围
                zerosCount++;
                // 下一轮遍历时 i 会增加 1，但此时 nums[i] 已经和 nums[i+1]交换了，nums[i+1]还没有判断是否为0，所以这里先减 1，以使下一轮继续判断 i 位置。
                i--;
            }
        }
    }

    public static void exchange(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    //endregion

    //region    20230326    215. 数组中的第 K 个最大元素
    public int findKthLargest(int[] nums, int k) {
        int maxIndex = 0;
        // 执行 k 次选择
        for (int i = 0; i < k; i++) {
            maxIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[maxIndex] < nums[j]) {
                    // 记录最大值
                    maxIndex = j;
                }
            }
            // 将最大元素交换至首位
            swap(nums, i, maxIndex);
        }
        return nums[k - 1];
    }
    //endregion

    //region    20230326    912. 排序数组
    public static int[] sortArray(int[] nums) {
        int minIndex, maxIndex;
        for (int i = 0; i < nums.length / 2; i++) {
            minIndex = i;
            maxIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
                if (nums[maxIndex] < nums[j]) {
                    maxIndex = j;
                }
            }
            if (maxIndex == minIndex) {
                break;
            }
            swap(nums, i, minIndex);
            if (maxIndex == i) {
                maxIndex = minIndex;
            }
            int lastIndex = nums.length - i - 1;
            swap(nums, lastIndex, maxIndex);
        }
        return nums;
    }
    //endregion

    public static void main(String[] args) {
        TargetSortAlgorithms.sortArray(new int[]{5, 2, 3, 1});
    }
}
