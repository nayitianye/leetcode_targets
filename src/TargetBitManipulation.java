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
     * S 的长度不超过12。
     * S 仅由数字和字母组成。
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

    //region 1442. 形成两个异或相等数组的三元组数目 2021/5/18
    /**
     * 给你一个整数数组 arr 。
     * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
     * a 和 b 定义如下：
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
     * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     * 注意：^ 表示 按位异或 操作。
     * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     *
     * 示例 1：
     * 输入：arr = [2,3,1,6,7]
     * 输出：4
     * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
     * 示例 2：
     * 输入：arr = [1,1,1,1,1]
     * 输出：10
     * 示例 3：
     * 输入：arr = [2,3]
     * 输出：0
     * 示例 4：
     * 输入：arr = [1,3,5,7,9]
     * 输出：3
     * 示例 5：
     * 输入：arr = [7,11,12,9,5,2,7,17,22]
     * 输出：8
     * 提示：
     * 1 <= arr.length <= 300
     * 1 <= arr[i] <= 10^8
     * @param arr
     * @return
     */
    public int countTriplets(int[] arr) {
        int n=arr.length;
        Map<Integer,Integer> cnt=new HashMap<>();
        Map<Integer,Integer> total=new HashMap<>();
        int ans=0,s=0;
        for(int i=0;i<n;i++){
            int temp=arr[i];
            if(cnt.containsKey(s^temp)){
                ans+=cnt.get(s^temp)*i-total.get(s^temp);
            }
            cnt.put(s,cnt.getOrDefault(s,0)+1);
            total.put(s,total.getOrDefault(s,0)+i);
            s^=temp;
        }
        return ans;
    }
    //endregion

    // region  1486. 数组异或操作 2021/05/12
    /**
     * 给你两个整数，n 和 start 。
     * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
     * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
     *
     * 示例 1：
     * 输入：n = 5, start = 0
     * 输出：8
     * 解释：数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。
     *      "^" 为按位异或 XOR 运算符。
     * 示例 2：
     * 输入：n = 4, start = 3
     * 输出：8
     * 解释：数组 nums 为 [3, 5, 7, 9]，其中 (3 ^ 5 ^ 7 ^ 9) = 8.
     * 示例 3：
     * 输入：n = 1, start = 7
     * 输出：7
     * 示例 4：
     * 输入：n = 10, start = 5
     * 输出：2
     *
     * 提示：
     * 1 <= n <= 1000
     * 0 <= start <= 1000
     * n == nums.length
     *
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {
        int res=0;
        for(int i=0;i<n;i++){
            res=res^(start+i*2);
        }
        return res;
    }
    // endregion

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

    //region  1734. 解码异或后的排列  2021/05/11

    /**
     * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
     * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
     * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
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

    public static void main(String[] args) {
        int[][] arrays=new int[1][2];
        int arr[] =new int[]{1,3,4,8};
        int queries[][] =new int[][] {{0,1},{1,2},{0,3},{3,3}};
        int num[]=(new TargetBitManipulation()).xorQueries(arr,queries);
    }
}
