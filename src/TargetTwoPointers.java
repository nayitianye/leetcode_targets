import java.util.*;

/**
 * @author yyb
 * leetcode_tag_two_pointers
 * leetcode 标签 双指针
 */
public class TargetTwoPointers {
    //region 167. 两数之和 II - 输入有序数组
    /**
     * 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数。
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1必须小于index2。
     *
     * 说明:
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     *
     * 示例:
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int low=0,high=numbers.length-1;
        int[] res=new int[2];
        while(low<high){
            int sum=numbers[low]+numbers[high];
            if(sum==target){
                res[0]=low+1;
                res[1]=high+1;
                return res;
            }else if(sum>target){
                high--;
            }else {
                low++;
            }
        }
        return res;
    }
    //endregion

    //region 345. 反转字符串中的元音字母
    /**
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     *
     * 示例 1:
     * 输入: "hello"
     * 输出: "holle"
     *
     * 示例 2:
     * 输入: "leetcode"
     * 输出: "leotcede"
     * 说明:
     * 元音字母不包含字母"y"。
     *
     * @param s
     * @return
     */
    private static String reverseVowels(String s) {
        List vowels = new ArrayList();
        StringBuilder strBuilder = new StringBuilder(s);
        int length = s.length();
        int head = 0;
        int tail = length-1;
        char temp;
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        while(head < tail)
        {
            if(vowels.contains(s.charAt(head)) == true && vowels.contains(s.charAt(tail)) == true)
            {
                temp = s.charAt(head);
                strBuilder.setCharAt(head, s.charAt(tail));
                strBuilder.setCharAt(tail, temp);
                head++;
                tail--;
            }
            else if(vowels.contains(s.charAt(head)) == true && vowels.contains(s.charAt(tail)) != true)
            { tail--;}
            else if(vowels.contains(s.charAt(head)) != true && vowels.contains(s.charAt(tail)) == true)
            { head++;}
            else
            {
                head++;
                tail--;
            }
        }
        return strBuilder.toString();
    }
    //endregion

    //region 349. 两个数组的交集
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
        HashSet<Integer> set1=new HashSet<>();
        for(Integer n:nums1){
            set1.add(n);
        }
        HashSet<Integer> set2=new HashSet<>();
        for(Integer n:nums2){
            set2.add(n);
        }
        return set1.size()<set2.size()?set_intersection(set1,set2):set_intersection(set2,set1);
    }
    private int[] set_intersection(HashSet<Integer> set1,HashSet<Integer>set2){
        int[] output=new int[set1.size()];
        int idx=0;
        for(Integer e:set1){
            if(set2.contains(e)){
                output[idx++]=e;
            }
        }
        return Arrays.copyOf(output,idx);
    }
    //endregion

    //region 844. 比较含退格的字符串
    /**
     * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，
     * 判断二者是否相等，并返回结果。 # 代表退格字符。
     *
     * 示例 1：
     * 输入：S = "ab#c", T = "ad#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “ac”。
     *
     * 示例 2：
     * 输入：S = "ab##", T = "c#d#"
     * 输出：true
     * 解释：S 和 T 都会变成 “”。
     *
     * 示例 3：
     * 输入：S = "a##c", T = "#a#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “c”。
     *
     * 示例 4：
     * 输入：S = "a#c", T = "b"
     * 输出：false
     * 解释：S 会变成 “c”，但 T 仍然是 “b”。
     * 
     * 提示：
     * 1 <= S.length <= 200
     * 1 <= T.length <= 200
     * S 和 T 只含有小写字母以及字符 '#'。
     * @param S
     * @param T
     * @return
     */
    private boolean backspaceCompare(String S, String T) {
        String s=getNewString(S);
        String t=getNewString(T);
        return s.equals(t);
    }
    private String getNewString(String s){
        Stack<Character>stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='#'){
                stack.push(s.charAt(i));
            }
            if(s.charAt(i)=='#'&&!stack.isEmpty()){
                stack.pop();
            }
        }
        StringBuilder res=new StringBuilder();
        while(!stack.isEmpty()){
            res.append(stack.pop());
        }
        return res.toString();
    }
    //endregion

    public static void main(String[] args) {
        System.out.println(reverseVowels("hello"));
    }
}

