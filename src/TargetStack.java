import javax.swing.plaf.nimbus.State;
import java.util.Stack;

/**
 * leetcode_tag_stack
 * leetcode 标签 栈
 *
 */
public class TargetStack {

    //region    20190919    20. 有效的括号

    /**
     * https://leetcode.cn/problems/valid-parentheses
     * @param s  给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s
     * @return  判断字符串是否有效
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

    //region    20230503    1003. 检查替换后的词是否有效

    /**
     * https://leetcode.cn/problems/check-if-word-is-valid-after-substitutions/
     * @param s  字符串 s
     * @return  判断它是否有效
     */
    public boolean isValid1(String s) {
        Stack<Character> stack=new Stack<>();
        for (int i = 0; i <s.length() ; i++) {
            stack.push(s.charAt(i));
            while (stack.size()>=3 && stack.peek()=='c'){
                stack.pop();
                if(stack.peek()=='b'){
                    stack.pop();
                    if(stack.peek()=='a'){
                        stack.pop();
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    //endregion

    public static void main(String[] args) {
        //System.out.println(new TargetStack().isValid1("aabcbc"));
    }
}
