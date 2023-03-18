import java.util.*;

/**
 * @author yyb
 * leetcode_tag_two_pointers
 * leetcode 标签 双指针
 */
public class TargetTwoPointers {

    //region    20230306    125. 验证回文串

    /**
     * https://leetcode.cn/problems/valid-palindrome/
     *
     * @param s 给你一个字符串 s
     * @return 判断其是否是回文串
     */
    public boolean isPalindrome(String s) {
        char[] c = s.toLowerCase().toCharArray();
        int i = 0, j = c.length - 1;
        while (i < j) {
            while (!isValid(c[i]) && i < j) {
                ++i;
            }
            while (!isValid(c[j]) && i < j) {
                --j;
            }
            if (c[i] != c[j]) {
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }

    private boolean isValid(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
    //endregion

    //region    20230306    167. 两数之和 II - 输入有序数组

    /**
     * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
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

    //region    20230306    345. 反转字符串中的元音字母

    /**
     * https://leetcode.cn/problems/reverse-vowels-of-a-string/
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

    //region    20230306    349. 两个数组的交集

    /**
     * https://leetcode.cn/problems/intersection-of-two-arrays/
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

    //region    20230223    350. 两个数组的交集 II

    /**
     * https://leetcode.cn/problems/intersection-of-two-arrays-ii/
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

    //region    20211222    475. 供暖器

    /**
     * https://leetcode.cn/problems/heaters/
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

    //region    20230222    633. 平方数之和

    /**
     * https://leetcode.cn/problems/sum-of-square-numbers/
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

    //region    20230306    844. 比较含退格的字符串

    /**
     * https://leetcode.cn/problems/backspace-string-compare/
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

    //region    20230318    1616. 分割两个字符串得到回文串

    /**
     * https://leetcode.cn/problems/split-two-strings-to-make-palindrome/
     *
     * @param a 字符串 a
     * @param b 字符串 b
     * @return 请你选择一个下标，将两个字符串都在相同的下标分割开。由 a 可以得到两个字符串： aprefix 和 asuffix ，满足 a = aprefix + asuffix ，同理，由 b 可以得到两个字符串 bprefix 和 bsuffix ，满足 b = bprefix + bsuffix 。请你判断 aprefix + bsuffix 或者 bprefix + asuffix 能否构成回文串。
     */
    public boolean checkPalindromeFormation(String a, String b) {
        return checkConcatenation(a, b) || checkConcatenation(b, a);
    }

    public boolean checkConcatenation(String a, String b) {
        int n = a.length();
        int left = 0, right = n - 1;
        while (left < right && a.charAt(left) == b.charAt(right)) {
            left++;
            right--;
        }
        if (left >= right) {
            return true;
        }
        return checkSelfPalindrome(a, left, right) || checkSelfPalindrome(b, left, right);
    }

    public boolean checkSelfPalindrome(String a, int left, int right) {
        while (left < right && a.charAt(left) == a.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }
    //endregion

    //region    20230223    1855. 下标对中的最大距离

    /**
     * https://leetcode.cn/problems/maximum-distance-between-a-pair-of-values/
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
        new TargetTwoPointers().checkPalindromeFormation("pvhmupgqeltozftlmfjjde", "yjgpzbezspnnpszebzmhvp");
    }
}

