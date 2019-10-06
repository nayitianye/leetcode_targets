import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author yyb
 * leetcode_tag_backtracking
 * leetcode 标签 回溯算法
 * 10 17 22 37 39 40 44 46 47 51 52 60 77 78 79 89 90 93 126 131
 * 140 211 212 216 254 267 291 294 306 320 351 357 404 411 425
 * 526 691 784 842 980 996 1066 1079 1087 1088
 */
public class TargetBackTracking {

    /**
     * 10. 正则表达式匹配
     * @param text
     * @param pattern
     * @return
     */
    private boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    //region 22. 括号生成  2019/10/6  回溯求解
    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，
     * 使其能够生成所有可能的并且有效的括号组合。
     * 例如，给出 n = 3，生成结果为：
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

    /**
     * 46. 全排列
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
