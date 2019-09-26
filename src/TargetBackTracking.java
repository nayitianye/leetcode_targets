import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
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


}
