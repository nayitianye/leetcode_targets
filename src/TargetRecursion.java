import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author yyb
 * leetcode_tag_recursion
 * leetcode 标签 递归
 */
public class TargetRecursion {

    //region 544. 输出比赛匹配对  2019/10/6 递归
    /**
     * 在 NBA 季后赛中，我们总是安排较强的队伍对战较弱的队伍，
     * 例如用排名第 1 的队伍和第 n 的队伍对决，这是一个可以让比赛更加有趣的好策略。
     * 现在，给你 n 支队伍，你需要以字符串格式输出它们的 最终比赛配对。
     *
     * n 支队伍按从 1 到 n 的正整数格式给出，
     * 分别代表它们的初始排名（排名 1 最强，排名 n 最弱）。
     * 我们用括号（'(', ')'）和逗号（','）来表示匹配对——括号（'(', ')'）表示匹配，
     * 逗号（','）来用于分割。 在每一轮的匹配过程中，
     * 你都需要遵循将强队与弱队配对的原则。
     *
     * 示例 1：
     * 输入: 2
     * 输出: (1,2)
     * 解析:
     * 初始地，我们有队1和队2两支队伍，按照1，2排列。
     * 因此 用 '(', ')' 和 ','来将队1和队2进行配对，得到最终答案。
     *
     * 示例 2：
     * 输入: 4
     * 输出: ((1,4),(2,3))
     * 解析:
     * 在第一轮，我们将队伍1和4配对，2和3配对，以满足将强队和弱队搭配的效果。
     * 得到(1,4),(2,3).
     * 在第二轮，(1,4) 和 (2,3) 的赢家需要进行比赛以确定最终赢家，
     * 因此需要再在外面加一层括号。
     * 于是最终答案是((1,4),(2,3))。
     *
     * 示例 3：
     * 输入: 8
     * 输出: (((1,8),(4,5)),((2,7),(3,6)))
     * 解析:
     * 第一轮: (1,8),(2,7),(3,6),(4,5)
     * 第二轮: ((1,8),(4,5)),((2,7),(3,6))
     * 第三轮 (((1,8),(4,5)),((2,7),(3,6)))
     * 由于第三轮会决出最终胜者，故输出答案为(((1,8),(4,5)),((2,7),(3,6)))。
     *  
     * 注意:
     * n 的范围是 [2, 212].
     * 保证 n 可以写成 2k 的形式，其中 k 是正整数。
     *
     * @param n
     * @return
     */
    public String findContestMatch(int n) {
        List<String> lists=new ArrayList<>();
        for(int i=1;i<=n;i++){
            lists.add(i+"");
        }
        return getString(lists).get(0);
    }

    public List<String> getString(List<String> lists){
        if(lists.size()==1){
            return lists;
        }
        else{
            List<String> res=new ArrayList<>();
            for(int i=0;i<lists.size()/2;i++){
                res.add("("+lists.get(i)+","+lists.get(lists.size()-i-1)+")");
            }
            return getString(res);
        }
    }
    //endregion

    //region 938. 二叉搜索树的范围和  2019/10/7  递归+二叉树中序遍历
    /**
     * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
     * 二叉搜索树保证具有唯一的值。
     *
     * 示例 1：
     * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
     * 输出：32
     *
     * 示例 2：
     * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
     * 输出：23
     *
     * 提示：
     * 树中的结点数量最多为 10000 个。
     * 最终的答案保证小于 2^31。
     * @descript  递归实现
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root==null)
            return 0;
        else if(root.val<L)
            return rangeSumBST(root.right,L,R);
        else if(root.val>R)
            return rangeSumBST(root.left,L,R);
        else
            return root.val+rangeSumBST(root.right,L,R)+rangeSumBST(root.left,L,R);
    }

    /**
     * 迭代方式深度优先搜索实现
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST1(TreeNode root, int L, int R) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (L <= node.val && node.val <= R)
                    ans += node.val;
                if (L < node.val)
                    stack.push(node.left);
                if (node.val < R)
                    stack.push(node.right);
            }
        }
        return ans;
    }

    int ans;
    /**
     * 递归方式深度优先搜索实现
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST2(TreeNode root, int L, int R) {
        ans = 0;
        dfs(root, L, R);
        return ans;
    }

    public void dfs(TreeNode node, int L, int R) {
        if (node != null) {
            if (L <= node.val && node.val <= R)
                ans += node.val;
            if (L < node.val)
                dfs(node.left, L, R);
            if (node.val < R)
                dfs(node.right, L, R);
        }
    }

    /**
     * 自己递归深度优先搜索实现
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST3(TreeNode root, int L, int R) {
        List<Integer> list=new ArrayList<>();
        getList(root,list);
        int res=0;
        boolean flag=false;
        for(int i=0;i<list.size();i++){
            if(list.get(i)==L){
                flag=true;
            }
            if(flag){
                res+=list.get(i);
            }
            if(list.get(i)==R){
                break;
            }
        }
        return res;
    }
    public void getList(TreeNode root,List<Integer> list){
        if(root==null){
            return;
        }
        getList(root.left,list);
        list.add(root.val);
        getList(root.right,list);
    }
    //endregion

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }



}
