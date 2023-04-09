import java.util.*;

/**
 * @author yyb
 * leetcode_tag_hash_table
 * leetcode 标签 哈希表
 */
public class TargetHashTable {

    //region    20230312    217. 存在重复元素

    /**
     * https://leetcode.cn/problems/contains-duplicate/
     *
     * @param nums 整数数组 nums
     * @return 如果任一数值在数组中出现至少两次 ，返回 true ；如果数组中每个元素互不相同 ，返回 false 。
     */
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                return true;
            } else {
                hashMap.put(num, 1);
            }
        }
        return false;
    }
    //endregion

    //region    20230312    219. 存在重复元素 II

    /**
     * https://leetcode.cn/problems/contains-duplicate-ii/
     *
     * @param nums 整数数组 nums
     * @param k    整数 k
     * @return 判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                if (i - hashMap.get(nums[i]) <= k) {
                    return true;
                } else {
                    hashMap.put(nums[i], i);
                }
            } else {
                hashMap.put(nums[i], i);
            }
        }
        return false;
    }
    //endregion

    //region    20230312    220. 存在重复元素 III

    /**
     * https://leetcode.cn/problems/contains-duplicate-iii/
     *
     * @param nums      整数数组 nums
     * @param indexDiff 下标间距
     * @param valueDiff 值间距
     * @return 存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= valueDiff ，同时又满足 abs(i - j) <= indexDiff ; 如果存在则返回 true，不存在返回 false。
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (j - i > indexDiff) {
                    break;
                }
                if (Math.abs(nums[j] - nums[i]) <= valueDiff) {
                    return true;
                }
            }
        }
        return false;
    }
    //endregion

    //region    20191003    349. 两个数组的交集

    /**
     * https://leetcode.cn/problems/intersection-of-two-arrays/
     *
     * @param nums1 数组 nums1
     * @param nums2 数组 nums2
     * @return 找出数组 nums1 和数组 nums2 的交集
     */
    private int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                list.add(num);
                set.remove(num);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    //endregion

    //region    20191003    350. 两个数组的交集 II

    /**
     * https://leetcode.cn/problems/intersection-of-two-arrays-ii/
     *
     * @param nums1 数组 nums1
     * @param nums2 数组 nums2
     * @return 找出数组 nums1 和数组 nums2 的交集
     */
    private int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                list.add(num);
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    //endregion

    //region    20191022    771. 宝石与石头

    /**
     * https://leetcode.cn/problems/jewels-and-stones/
     *
     * @param jewels 字符串 jewels 代表石头中宝石的类型
     * @param stones 字符串 stones 代表你拥有的石头
     * @return 你拥有的石头中有多少是宝石
     */
    public int numJewelsInStones(String jewels, String stones) {
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            hashSet.add(jewels.charAt(i));
        }
        int res = 0;
        for (int j = 0; j < stones.length(); j++) {
            if (hashSet.contains(stones.charAt(j))) {
                res++;
            }
        }
        return res;
    }
    //endregion

    //region    20230308    2215. 找出两数组的不同

    /**
     * https://leetcode.cn/problems/find-the-difference-of-two-arrays/
     *
     * @param nums1 数组 nums1
     * @param nums2 数组 nums2
     * @return 找出不同并返回
     */
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hashMap1 = new HashMap<>();
        HashMap<Integer, Integer> hashMap2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (!hashMap1.containsKey(nums1[i])) {
                hashMap1.put(nums1[i], 1);
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            if (!hashMap2.containsKey(nums2[i])) {
                hashMap2.put(nums2[i], 1);
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (!hashMap2.containsKey(nums1[i])) {
                list1.add(nums1[i]);
                hashMap2.put(nums1[i], 1);
            }
        }
        res.add(list1);
        for (int i = 0; i < nums2.length; i++) {
            if (!hashMap1.containsKey(nums2[i])) {
                list2.add(nums2[i]);
                hashMap1.put(nums2[i], 1);
            }
        }
        res.add(list2);
        return res;
    }
    //endregion

    //region    20230331    2367. 算术三元组的数目

    /**
     * https://leetcode.cn/problems/number-of-arithmetic-triplets/
     *
     * @param nums 严格递增 的整数数组 num
     * @param diff 一个正整数 diff
     * @return 不同 算术三元组 的数目
     */
    public int arithmeticTriplets(int[] nums, int diff) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            hashSet.add(nums[i]);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i] + diff) && hashSet.contains(nums[i] + diff * 2)) {
                res++;
            }
        }
        return res;
    }
    //endregion

    //region    20230326    2395. 和相等的子数组

    /**
     * https://leetcode.cn/problems/find-subarrays-with-equal-sum/
     *
     * @param nums 下标从 0 开始的整数数组 nums
     * @return 判断是否存在 两个 长度为 2 的子数组且它们的 和 相等。注意，这两个子数组起始位置的下标必须 不相同
     */
    public boolean findSubarrays(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 1; i < nums.length; i++) {
            if (hashSet.contains(nums[i] + nums[i - 1])) {
                return true;
            } else {
                hashSet.add(nums[i] + nums[i - 1]);
            }
        }
        return false;
    }
    //endregion

    //region    20230409    2399. 检查相同字母间的距离

    /**
     * https://leetcode.cn/problems/check-distances-between-same-letters/
     *
     * @param s  下标从 0 开始的字符串 s
     * @param distance  下标从 0 开始、长度为 26 的的整数数组 distance
     * @return   s 是一个 匀整 字符串，返回 true ；否则，返回 false
     */
    public boolean checkDistances(String s, int[] distance) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (last[c] != 0 && i - last[c] != distance[c]) {
                return false;
            }
            last[c] = i + 1;
        }
        return true;
    }
    //endregion

    public static void main(String[] args) {
        new TargetHashTable().containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3);
    }
}
