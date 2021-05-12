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

    //region 1720 解码异或后的数组  2021/05/11
    /**
     * 未知 整数数组 arr 由 n 个非负整数组成。
     * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
     * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
     * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
     *
     * 示例 1：
     * 输入：encoded = [1,2,3], first = 1
     * 输出：[1,0,2,1]
     * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
     * 示例 2：
     * 输入：encoded = [6,2,7,3], first = 4
     * 输出：[4,2,0,7,4]
     *
     * 提示：
     * 2 <= n <= 10^4
     * encoded.length == n - 1
     * 0 <= encoded[i] <= 10^5
     * 0 <= first <= 10^5
     *
     * @param encoded
     * @param first
     * @return
     */
    public int[] decode(int[] encoded, int first) {
        int[] decode=new int[encoded.length+1];
        decode[0]=first;
        for(int i=0;i<encoded.length;i++){
            decode[i+1]=encoded[i]^decode[i];
        }
        return decode;
    }
    //endregion

    // region  1310. 子数组异或查询 2021/05/12

    /**
     * 有一个正整数数组arr，现给你一个对应的查询数组queries，其中queries[i] = [Li,Ri]。
     * 对于每个查询i，请你计算从Li到Ri的XOR值（即arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
     * 并返回一个包含给定查询queries所有结果的数组。
     *
     * 示例 1：
     * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
     * 输出：[2,7,14,8]
     * 解释：
     * 数组中元素的二进制表示形式是：
     * 1 = 0001
     * 3 = 0011
     * 4 = 0100
     * 8 = 1000
     * 查询的 XOR 值为：
     * [0,1] = 1 xor 3 = 2
     * [1,2] = 3 xor 4 = 7
     * [0,3] = 1 xor 3 xor 4 xor 8 = 14
     * [3,3] = 8
     * 示例 2：
     * 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
     * 输出：[8,0,4,4]
     *
     * 提示：
     * 1 <= arr.length <= 3 * 10^4
     * 1 <= arr[i] <= 10^9
     * 1 <= queries.length <= 3 * 10^4
     * queries[i].length == 2
     * 0 <= queries[i][0] <= queries[i][1] < arr.length
     *
     * @param arr
     * @param queries
     * @return
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] result =new int[arr.length+1];
        for(int i=0;i< arr.length;i++) {
            result[i+1] = result[i]^arr[i];
        }
        int[] xorQueries=new int[queries.length];
        for(int i=0;i< queries.length;i++){
            xorQueries[i]=result[queries[i][0]]^result[queries[i][1]+1];
        }
        return xorQueries;
    }
    // endregion

    public static void main(String[] args) {
        int[][] arrays=new int[1][2];
        int arr[] =new int[]{1,3,4,8};
        int queries[][] =new int[][] {{0,1},{1,2},{0,3},{3,3}};
        int num[]=(new TargetBitManipulation()).xorQueries(arr,queries);
    }
}
