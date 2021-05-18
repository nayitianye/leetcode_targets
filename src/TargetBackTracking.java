import java.util.*;

/**
 * @author yyb
 * leetcode_tag_backtracking
 * leetcode 标签 回溯算法
 * 10 17 22 37 39 40 44 46 47 51 52 60 77 78 79 89 90 93 126 131
 * 140 211 212 216 254 267 291 294 306 320 351 357 404 411 425
 * 526 691 784 842 980 996 1066 1079 1087 1088
 */
public class TargetBackTracking {

    //region 17. 电话号码的字母组合  2019/10/11 回溯求解
    //    /**
    //     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
    //     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    //     * 2-abc 3-def 4-ghi 5-jkl 6-mno 7-pqrs 8-tuv 9-wxyz
    //     * 示例:
    //     * 输入："23"
    //     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    //     * @param digits
    //     * @return
    //     */
    //    public List<String> letterCombinations(String digits) {
    //        Map<String,String> map=new HashMap<>();
    //        map.put("2","abc");
    //        map.put("3","def");
    //        map.put("4","ghi");
    //        map.put("5","jkl");
    //        map.put("6","mno");
    //        map.put("7","pqrs");
    //        map.put("8","tuv");
    //        map.put("9","wxyz");
    //        List<String> res=new ArrayList<>();
    //        if(digits.length()!=0){
    //            backtrackletterCombinations("",digits,res,map);
    //        }
    //        return res;
    //    }
    //    private  void backtrackletterCombinations(String conbination,String next_digits,List<String> res,Map<String,String> map){
    //        if(next_digits.length()==0){
    //            res.add(conbination);
    //        }
    //        else{
    //            String digit=next_digits.substring(0,1);
    //            String letters=map.get(digit);
    //            for(int i=0;i<letters.length();i++){
    //                String letter=map.get(digit).substring(i,i+1);
    //                backtrackletterCombinations(conbination+letter,next_digits.substring(1),res,map);
    //            }
    //        }
    //    }
    //    //endregion

    //region 22. 括号生成  2019/10/6  回溯求解
    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，
     * 使其能够生成所有可能的并且有效的括号组合。
     * 例如，给出 n = 3，生成结果为：
     *
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     *
     * @param n
     * @return
     */
    private List<String> generateParenthesis(int n) {
        List<String> ans=new ArrayList<>();
        backtrack(ans,"",0,0,n);
        return ans;
    }

    private void backtrack(List<String> ans,String cur,int open,int close,int max){
        if(cur.length()==max*2){
            ans.add(cur);
            return;
        }
        if(open<max){
            backtrack(ans,cur+"(",open+1,close,max);
        }else{
            backtrack(ans,cur+")",open,close+1,max);
        }
    }
    //endregion

    //region 39. 组合总和  2019/10/11  回溯求解
    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，
     * 找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     *
     * 示例 2:
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        int len=candidates.length;
        Arrays.sort(candidates);
        findCombinationSum(target,0,new Stack<>(),len,candidates,res);
        return res;
    }
    private void findCombinationSum(int residue,int start,Stack<Integer>pre,int len,int[] candidates,List<List<Integer>> res) {
        if(residue==0){
            res.add(new ArrayList<>(pre));
        }
        for(int i=start;i<len&&residue-candidates[i]>=0;i++){
            pre.add(candidates[i]);
            findCombinationSum(residue-candidates[i],i,pre,len,candidates,res);
            pre.pop();
        }
    }
    //endregion

    //region 40. 组合总和 II  2019/10/11  回溯求解
    /**
     * 给定一个数组 candidates 和一个目标数 target ，
     * 找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 说明：
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     *
     * 示例 2:
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len=candidates.length;
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(candidates);
        findConbinationSum2(target,0,new Stack<>(),candidates,res);
        return res;
    }
    private void findConbinationSum2(int target,int index,Stack<Integer> stack,int[] candidates,List<List<Integer>>res){
        if(target==0){
            res.add(new ArrayList<>(stack));
        }
        for(int i=index;i<candidates.length&&target-candidates[i]>=0;i++){
            if(i>index&&candidates[i]==candidates[i-1]){
                continue;
            }
            stack.push(candidates[i]);
            findConbinationSum2(target-candidates[i],i+1,stack,candidates,res);
            stack.pop();
        }
    }
    //endregion

    //region 46. 全排列  2019/10/11 回溯求解
    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     *
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     *
     * @param nums
     * @return
     */
    private List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        if(nums.length==0){
            return res;
        }
        boolean[] isUse=new boolean[nums.length];
        Stack<Integer> stack=new Stack<>();
        gen_permute(nums,0,isUse,stack,res);
        return res;
    }
    private void gen_permute(int[] nums,int index,boolean[] isUse,Stack<Integer> stack,List<List<Integer>> res){
        if(index==nums.length){
            res.add(new ArrayList<>(stack));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(!isUse[i]){
                stack.add(nums[i]);
                isUse[i]=true;
                gen_permute(nums,index+1,isUse,stack,res);
                isUse[i]=false;
                stack.pop();
            }
        }
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

}
