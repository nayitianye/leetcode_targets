import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yyb
 * leetcode_tag_bit_manipulation
 * leetcode 标签 位运算
 */
public class TargetBitManipulation {

    //region 169. 求众数   2019/10/22  hashMap解决
    /**
     * 给定一个大小为 n 的数组，找到其中的众数。
     * 众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在众数。
     *
     * 示例 1:
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
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

    //region 784. 字母大小写全排列
    /**
     * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。
     * 返回所有可能得到的字符串集合。
     *
     * 示例:
     * 输入: S = "a1b2"
     * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
     * 输入: S = "3z4"
     * 输出: ["3z4", "3Z4"]
     * 输入: S = "12345"
     * 输出: ["12345"]
     *
     * 注意：
     * S 的长度不超过12。
     * S 仅由数字和字母组成。
     * @param S
     * @return
     */
    private List<String> letterCasePermutation(String S) {
        List<String> res=new ArrayList<>();
        char[] str=S.toCharArray();
        int dis='A'-'a';
        dfs_letterCasePermutation(str,0,res,dis);
        return res;
    }
    private void dfs_letterCasePermutation(char[] str,int index,List<String> res,int dis){
        if(index==str.length){
            res.add(new String(str));
            return;
        }
        if(str[index]>='a'&&str[index]<='z'){
            dfs_letterCasePermutation(str,index+1,res,dis);
            str[index]+=dis;
            dfs_letterCasePermutation(str,index+1,res,dis);
            str[index]-=dis;
        }else if(str[index]>='A'&&str[index]<='Z'){
            dfs_letterCasePermutation(str,index+1,res,dis);
            str[index]-=dis;
            dfs_letterCasePermutation(str,index+1,res,dis);
            str[index]+=dis;
        }else{
            dfs_letterCasePermutation(str,index+1,res,dis);
        }
    }
    //endregion

}
