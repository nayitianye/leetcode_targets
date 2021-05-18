import java.util.*;

/**
 * @author yyb
 * leetcode_tag_hash_table
 * leetcode 标签 哈希表
 */
public class TargetHashTable {

    //region 349. 两个数组的交集 2019/10/3  TreeSet
    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * 示例 1:
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2]
     *
     * 示例 2:
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [9,4]
     *
     * 说明:
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set=new TreeSet<>();
        for(int num:nums1){
            set.add(num);
        }
        ArrayList<Integer> list=new ArrayList<>();
        for(int num:nums2){
            if(set.contains(num)) {
                list.add(num);
                set.remove(num);
            }
        }
        int[] res=new int[list.size()];
        for(int i=0;i<list.size();i++){
            res[i]=list.get(i);
        }
        return res;
    }
    //endregion

    //region 350. 两个数组的交集 II 2019/10/3 TreeMap
    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * 示例 1:
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     * 示例 2:
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [4,9]
     * 说明：
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     * 我们可以不考虑输出结果的顺序。
     *
     * 进阶:
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？
     * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
     * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     * @param nums1
     * @param nums2
     * @return
     */
    private int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer,Integer> map=new TreeMap<>();
        for(int num:nums1){
            if(!map.containsKey(num)){
                map.put(num,1);
            }else{
                map.put(num,map.get(num)+1);
            }
        }
        ArrayList<Integer> list=new ArrayList<>();
        for(int num:nums2){
            if(map.containsKey(num)){
                list.add(num);
                map.put(num,map.get(num)-1);
                if(map.get(num)==0){
                    map.remove(num);
                }
            }
        }
        int[] res=new int[list.size()];
        for(int i=0;i<list.size();i++){
            res[i]=list.get(i);
        }
        return res;
    }
    //endregion

    //region 771. 宝石与石头   2019/10/22  HashSet
    /**
     *  给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。
     *  S 中每个字符代表了一种你拥有的石头的类型，
     * 你想知道你拥有的石头中有多少是宝石。
     *
     * J 中的字母不重复，J 和 S中的所有字符都是字母。
     * 字母区分大小写，因此"a"和"A"是不同类型的石头。
     *
     * 示例 1:
     * 输入: J = "aA", S = "aAAbbbb"
     * 输出: 3
     * 示例 2:
     * 输入: J = "z", S = "ZZ"
     * 输出: 0
     * 注意:
     * S 和 J 最多含有50个字母。
     *  J 中的字符不重复。
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> hashSet=new HashSet<>();
        for(int i=0;i<J.length();i++){
            hashSet.add(J.charAt(i));
        }
        int res=0;
        for (int j=0;j<S.length();j++){
            if(hashSet.contains(S.charAt(j))){
                res++;
            }
        }
        return res;
    }
    //endregion


}
