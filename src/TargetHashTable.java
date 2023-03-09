import java.util.*;

/**
 * @author yyb
 * leetcode_tag_hash_table
 * leetcode 标签 哈希表
 */
public class TargetHashTable {

    //region    20191003    349. 两个数组的交集

    /**
     * https://leetcode.cn/problems/intersection-of-two-arrays/
     * @param nums1 数组 nums1
     * @param nums2 数组 nums2
     * @return  找出数组 nums1 和数组 nums2 的交集
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
     * @param nums1 数组 nums1
     * @param nums2 数组 nums2
     * @return  找出数组 nums1 和数组 nums2 的交集
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
     * @param jewels 字符串 jewels 代表石头中宝石的类型
     * @param stones 字符串 stones 代表你拥有的石头
     * @return  你拥有的石头中有多少是宝石
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
     * @param nums1 数组 nums1
     * @param nums2 数组 nums2
     * @return  找出不同并返回
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

    public static void main(String[] args) {

    }
}
