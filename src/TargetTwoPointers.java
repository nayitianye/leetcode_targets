import java.util.*;

/**
 * @author yyb
 * leetcode_tag_two_pointers
 * leetcode 标签 双指针
 */
public class TargetTwoPointers {
    //region 167. 两数之和 II - 输入有序数组

    /**
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     * <p>
     * 说明:
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     * <p>
     * 示例:
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        int[] res = new int[2];
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                res[0] = low + 1;
                res[1] = high + 1;
                return res;
            } else if (sum > target) {
                high--;
            } else {
                low++;
            }
        }
        return res;
    }
    //endregion

    //region 345. 反转字符串中的元音字母

    /**
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     * <p>
     * 示例 1:
     * 输入: "hello"
     * 输出: "holle"
     * <p>
     * 示例 2:
     * 输入: "leetcode"
     * 输出: "leotcede"
     * 说明:
     * 元音字母不包含字母"y"。
     *
     * @param s
     * @return
     */
    private static String reverseVowels(String s) {
        List vowels = new ArrayList();
        StringBuilder strBuilder = new StringBuilder(s);
        int length = s.length();
        int head = 0;
        int tail = length - 1;
        char temp;
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        while (head < tail) {
            if (vowels.contains(s.charAt(head)) == true && vowels.contains(s.charAt(tail)) == true) {
                temp = s.charAt(head);
                strBuilder.setCharAt(head, s.charAt(tail));
                strBuilder.setCharAt(tail, temp);
                head++;
                tail--;
            } else if (vowels.contains(s.charAt(head)) == true && vowels.contains(s.charAt(tail)) != true) {
                tail--;
            } else if (vowels.contains(s.charAt(head)) != true && vowels.contains(s.charAt(tail)) == true) {
                head++;
            } else {
                head++;
                tail--;
            }
        }
        return strBuilder.toString();
    }
    //endregion

    //region 349. 两个数组的交集

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 示例 1:
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2]
     * <p>
     * 示例 2:
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [9,4]
     * <p>
     * 说明:
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) {
            set1.add(n);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) {
            set2.add(n);
        }
        return set1.size() < set2.size() ? set_intersection(set1, set2) : set_intersection(set2, set1);
    }

    private int[] set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int[] output = new int[set1.size()];
        int idx = 0;
        for (Integer e : set1) {
            if (set2.contains(e)) {
                output[idx++] = e;
            }
        }
        return Arrays.copyOf(output, idx);
    }
    //endregion

    //region 350. 两个数组的交集 II  20230223

    /**
     * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。
     * 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。
     * 可以不考虑输出结果的顺序。
     * <p>
     * 示例 1：
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     * <p>
     * 提示：
     * 1 <= nums1.length, nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 1000
     * <p>
     * 进阶：
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？
     * 如果 nums1 的大小比 nums2 小，哪种方法更优？
     * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums2[index2] < nums1[index1]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
    //endregion

    //region 475. 供暖器 20211222

    /**
     * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
     * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
     * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
     * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
     * <p>
     * 示例 1:
     * 输入: houses = [1,2,3], heaters = [2]
     * 输出: 1
     * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
     * <p>
     * 示例 2:
     * 输入: houses = [1,2,3,4], heaters = [1,4]
     * 输出: 1
     * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
     * <p>
     * 示例 3：
     * 输入：houses = [1,5], heaters = [2]
     * 输出：3
     * <p>
     * 提示：
     * 1 <= houses.length, heaters.length <= 3 * 10^4
     * 1 <= houses[i], heaters[i] <= 10^9
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        // 先进行升序排列
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int radius = 0;
        int i = 0;
        for (int house : houses) {
            while (i < heaters.length && heaters[i] < house) {
                // 一直找到处于房屋右侧的热水器
                i++;
            }
            if (i == 0) {
                radius = Math.max(radius, heaters[i] - house);
            } else if (i == heaters.length) {
                // 最后一个热水器
                return Math.max(radius, houses[houses.length - 1] - heaters[heaters.length - 1]);
            } else {
                // 房屋右侧的热水器和房屋左侧的热水器，取小的那个
                radius = Math.max(radius, Math.min(heaters[i] - house, house - heaters[i - 1]));
            }
        }
        return radius;
    }
    //endregion

    //region 633. 平方数之和 20230222

    /**
     * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
     * <p>
     * 示例 1：
     * 输入：c = 5
     * 输出：true
     * 解释：1 * 1 + 2 * 2 = 5
     * 示例 2：
     * 输入：c = 3
     * 输出：false
     * <p>
     * 提示：
     * 0 <= c <= 2^31 - 1
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);
        while (left <= right) {
            long sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }
    //endregion

    //region 844. 比较含退格的字符串

    /**
     * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，
     * 判断二者是否相等，并返回结果。 # 代表退格字符。
     * <p>
     * 示例 1：
     * 输入：S = "ab#c", T = "ad#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “ac”。
     * <p>
     * 示例 2：
     * 输入：S = "ab##", T = "c#d#"
     * 输出：true
     * 解释：S 和 T 都会变成 “”。
     * <p>
     * 示例 3：
     * 输入：S = "a##c", T = "#a#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “c”。
     * <p>
     * 示例 4：
     * 输入：S = "a#c", T = "b"
     * 输出：false
     * 解释：S 会变成 “c”，但 T 仍然是 “b”。
     * <p>
     * 提示：
     * 1 <= S.length <= 200
     * 1 <= T.length <= 200
     * S 和 T 只含有小写字母以及字符 '#'。
     *
     * @param S
     * @param T
     * @return
     */
    private boolean backspaceCompare(String S, String T) {
        String s = getNewString(S);
        String t = getNewString(T);
        return s.equals(t);
    }

    private String getNewString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                stack.push(s.charAt(i));
            }
            if (s.charAt(i) == '#' && !stack.isEmpty()) {
                stack.pop();
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.toString();
    }
    //endregion

    //region 1855. 下标对中的最大距离 20230223

    /**
     * 给你两个 非递增 的整数数组 nums1 和 nums2 ，数组下标均 从 0 开始 计数。
     * 下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。
     * 如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，
     * 该下标对的 距离 为 j - i 。
     * 返回所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0 。
     * 一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，
     * 那么该数组是一个 非递增 数组。
     * <p>
     * 示例 1：
     * 输入：nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
     * 输出：2
     * 解释：有效下标对是 (0,0), (2,2), (2,3), (2,4), (3,3), (3,4) 和 (4,4) 。
     * 最大距离是 2 ，对应下标对 (2,4) 。
     * 示例 2：
     * 输入：nums1 = [2,2,2], nums2 = [10,10,1]
     * 输出：1
     * 解释：有效下标对是 (0,0), (0,1) 和 (1,1) 。
     * 最大距离是 1 ，对应下标对 (0,1) 。
     * 示例 3：
     * 输入：nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
     * 输出：2
     * 解释：有效下标对是 (2,2), (2,3), (2,4), (3,3) 和 (3,4) 。
     * 最大距离是 2 ，对应下标对 (2,4) 。
     * <p>
     * <p>
     * 提示：
     * 1 <= nums1.length <= 10^5
     * 1 <= nums2.length <= 10^5
     * 1 <= nums1[i], nums2[j] <= 10^5
     * nums1 和 nums2 都是 非递增 数组
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxDistance(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0, maxDistance = 0;
        while (index1 < length1 && index2 < length2) {
            if (index2 < index1) {
                index2++;
            } else if (nums1[index1] > nums2[index2]) {
                index1++;
            } else {
                maxDistance = Math.max(maxDistance, index2 - index1);
                index2++;
            }
        }
        return maxDistance;
    }
    //endregion

    public static void main(String[] args) {
        System.out.println(reverseVowels("hello"));
    }
}

