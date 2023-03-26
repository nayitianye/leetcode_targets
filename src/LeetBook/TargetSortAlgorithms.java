package LeetBook;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author yyb
 * leetcode_leetbook_sort_algorithms
 * leetbook 排序算法图文学
 * @url https://leetcode.cn/leetbook/detail/sort-algorithms/
 */
public class TargetSortAlgorithms {

    //region 冒泡排序

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

    //endregion

    //region    选择排序

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

    //endregion

    //region    插入排序

    //region    20230326    912. 排序数组
    public int[] sortArray1(int[] nums) {
        insertSort(nums);
        return nums;
    }

    public static void insertSort(int[] arr) {
        // 从第二个数开始，往前插入数字
        for (int i = 1; i < arr.length; i++) {
            int currentNumber = arr[i];
            int j = i - 1;
            while (j >= 0 && currentNumber < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            // 两种情况会跳出循环：1. 遇到一个小于或等于 currentNumber 的数字，跳出循环，currentNumber 就坐到它后面。
            // 2. 已经走到数列头部，仍然没有遇到小于或等于 currentNumber 的数字，也会跳出循环，此时 j 等于 -1，currentNumber 就坐到数列头部。
            arr[j] = currentNumber;
        }
    }
    //endregion

    //region    20230326    147. 对链表进行插入排序
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        // 创建哑结点，用于将在 head 前插入结点转换为在哑结点后插入，统一处理，更方便
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 记录已排序完成的结点末尾
        ListNode lastSorted = head;
        // 当前需要新插入的结点
        ListNode current = head.next;
        while (current != null) {
            if (lastSorted.val <= current.val) {
                // 新插入的值正好时最大值，直接插入链表末尾
                lastSorted = lastSorted.next;
            } else {
                // 从头开始寻找插入位置
                ListNode previous = dummyHead;
                while (previous.next.val <= current.val) {
                    previous = previous.next;
                }
                // 将新结点插入链表
                lastSorted.next = current.next;
                current.next = previous.next;
                previous.next = current;
            }
            //更新新结点
            current = lastSorted.next;
        }
        return dummyHead.next;
    }
    //endregion

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
    //endregion

    //region    希尔排序

    //region    20230326    912. 排序数组
    public int[] sortArray2(int[] nums) {
        shellSort(nums);
        return nums;
    }

    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int currentNumber = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && currentNumber < arr[preIndex]) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = currentNumber;
            }
        }
    }

    //endregion\

    //region    20230326    506. 相对名次
    public String[] findRelativeRanks(int[] score) {
        int[] arr = score.clone();
        shellSortSorces(arr);
        //建立每位运动员和名次的映射关系
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i + 1);
        }
        String[] res = Arrays.stream(score).mapToObj(String::valueOf).toArray(String[]::new);
        //Arrays.stream(nums) :把 nums整型数组变成流
        //.mapToObj(String::valueOf) 流中的为一个整型元素变成String类型
        //.toArray(String[]::new) 将流中元素重新搜集为一个整型数组。
        for (int i = 0; i < score.length; i++) {
            if (score[i] == arr[0]) {
                res[i] = "Gold Medal";
            } else if (score[i] == arr[1]) {
                res[i] = "Silver Medal";
            } else if (score[i] == arr[2]) {
                res[i] = "Bronze Medal";
            } else {
                res[i] = String.valueOf(map.get(score[i]));
            }
        }
        return res;
    }

    public static void shellSortSorces(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int currentNumber = arr[i];
                int prdIndex = i - gap;
                while (prdIndex >= 0 && currentNumber > arr[prdIndex]) {
                    arr[prdIndex + gap] = arr[prdIndex];
                    prdIndex -= gap;
                }
                arr[prdIndex + gap] = currentNumber;
            }
        }
    }
    //endregion

    //endregion

    public static void main(String[] args) {
        TargetSortAlgorithms.sortArray(new int[]{5, 2, 3, 1});
    }
}
