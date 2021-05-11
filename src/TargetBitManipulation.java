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

    // region  136. 只出现一次的数字  2021/05/11
    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     * 输入: [4,1,2,1,2]
     * 输出: 4
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res=0;
        for(int i=0;i<nums.length;i++){
            res^=nums[i];
        }
        return res;
    }
    //endregion

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

    //region 260. 只出现一次的数字 III  2021/05/11
    /**
     * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
     * 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
     * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
     *
     * 示例 1：
     * 输入：nums = [1,2,1,3,2,5]
     * 输出：[3,5]
     * 解释：[5, 3] 也是有效的答案。
     * 示例 2：
     * 输入：nums = [-1,0]
     * 输出：[-1,0]
     * 示例 3：
     * 输入：nums = [0,1]
     * 输出：[1,0]
     *
     * 提示：
     * 2 <= nums.length <= 3 * 10^4
     * -2^31 <= nums[i] <= 2^31 - 1
     * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
     * @param nums
     * @return
     */
    public int[] singleNumber3(int[] nums) {
        int ret=0;
        for(int i=0;i<nums.length;i++){
            ret^=nums[i];
        }
        int div=1;
        while ((div & ret)==0){
            div <<= 1;
        }
        int a=0,b=0;
        for(int i=0;i<nums.length;i++){
            if((div &nums[i])!=0){
                a^=nums[i];
            }else{
                b^=nums[i];
            }
        }
        return new int[]{a,b};
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

    //region  1734. 解码异或后的排列  2021/05/11

    /**
     * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
     * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
     * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
     *
     * 示例 1：
     * 输入：encoded = [3,1]
     * 输出：[1,2,3]
     * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
     * 示例 2：
     * 输入：encoded = [6,5,4,6]
     * 输出：[2,4,1,5,3]
     *
     * 提示：
     * 3 <= n < 10^5
     * n 是奇数。
     * encoded.length == n - 1
     *
     * @param encoded
     * @return
     */
    public int[] decode(int[] encoded) {
        int[] decode=new int[encoded.length+1];
        int abcde=0;
        for(int i=1;i<=encoded.length+1;i++){
            abcde^=i;
        }
        int bcde=0;
        for(int i=1;i<encoded.length;i+=2){
            bcde^=encoded[i];
        }
        decode[0]=abcde^bcde;
        for(int i=1;i<=encoded.length;i++){
            decode[i]=decode[i-1]^encoded[i-1];
        }
        return  decode;
    }
    //endregion
}
