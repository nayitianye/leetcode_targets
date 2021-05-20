import java.util.Stack;

/**
 * leetcode_tag_stack
 * leetcode 标签 栈
 *
 */
public class TargetStack {

    //region 20. 有效的括号
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * 示例 1:
     * 输入: "()"
     * 输出: true
     *
     * 示例2:
     * 输入: "()[]{}"
     * 输出: true
     *
     * 示例3:
     * 输入: "(]"
     * 输出: false
     *
     * 示例4:
     * 输入: "([)]"
     * 输出: false
     *
     * 示例5:
     * 输入: "{[]}"
     * 输出: true
     * @param s
     * @return
     */
    private boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            Character ch=s.charAt(i);
            if(ch=='('||ch=='{'||ch=='['){
                stack.push(ch);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                if(ch==')'&&stack.peek()=='('){
                    stack.pop();
                }
                else if(ch==']'&&stack.peek()=='['){
                    stack.pop();
                }
                else if(ch=='}'&&stack.peek()=='{'){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
    //endregion

    public static void main(String[] args) {
        System.out.println(new TargetStack().isValid("{}[]()"));
    }
}
