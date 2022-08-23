import java.util.HashMap;
import java.util.Map;

/**
 * @author yyb
 * leetcode_tag_divide_and_conquer
 * leetcode 标签 分治算法
 */
public class TargetDivideAndConquer {

    //region 169. 求众数   2019/10/22  hashMap解决
    /**
     * 给定一个大小为 n 的数组，找到其中的众数。
     * 众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在众数。
     *
     * 示例 1:
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(hashMap.containsKey(nums[i])){
                hashMap.put(nums[i],hashMap.get(nums[i])+1);
            }else {
                hashMap.put(nums[i],1);
            }
        }
        Map.Entry<Integer,Integer> res=null;
        for(Map.Entry<Integer,Integer> entry:hashMap.entrySet()){
            if(res==null||entry.getValue()>res.getValue()){
                res=entry;
            }
        }
        return res.getKey();
    }
    //endregion
}
