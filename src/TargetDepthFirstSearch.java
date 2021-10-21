import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author yyb
 * leetcode_tag_depth_first_search
 * leetcode 标签 深度优先遍历
 */
public class TargetDepthFirstSearch {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //region 100. 相同的树  2019/10/7  递归+深度优先遍历+tree
    /**
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * 示例 1:
     *
     * 输入:       1         1
     *           / \       / \
     *          2   3     2   3
     *
     *         [1,2,3],   [1,2,3]
     *
     * 输出: true
     * 示例 2:
     *
     * 输入:      1          1
     *           /           \
     *          2             2
     *
     *         [1,2],     [1,null,2]
     *
     * 输出: false
     * 示例 3:
     *
     * 输入:       1         1
     *           / \       / \
     *          2   1     1   2
     *
     *         [1,2,1],   [1,1,2]
     *
     * 输出: false
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        boolean res=isSameTreeDfs(p,q);
        return res;
    }
    public boolean isSameTreeDfs(TreeNode p,TreeNode q){
        if (p == null && q == null) {
            return true;
        }
        if(p!=null&&q!=null){
            return p.val==q.val&&isSameTree(p.left,q.left)&&isSameTree(q.right,p.right);
        }
        return false;
    }
    //endregion

    //region 897. 递增顺序查找树 2019/10/7  中序遍历（深度优先遍历）+tree
    /**
     * 给定一个树，按中序遍历重新排列树，使树中最左边的结点现在是树的根，
     * 并且每个结点没有左子结点，只有一个右子结点。
     *
     * 示例 ：
     * 输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]
     *
     *        5
     *       / \
     *     3    6
     *    / \    \
     *   2   4    8
     *  /        / \
     * 1        7   9
     *
     * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     *  1
     *   \
     *    2
     *     \
     *      3
     *       \
     *        4
     *         \
     *          5
     *           \
     *            6
     *             \
     *              7
     *               \
     *                8
     *                 \
     *                   9
     *  
     * 提示：
     * 给定树中的结点数介于 1 和 100 之间。
     * 每个结点都有一个从 0 到 1000 范围内的唯一整数值。
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> vals = new ArrayList();
        inorder(root, vals);
        TreeNode ans = new TreeNode(0), cur = ans;
        for (int v: vals) {
            cur.right = new TreeNode(v);
            cur = cur.right;
        }
        return ans.right;
    }
    public void inorder(TreeNode node, List<Integer> vals) {
        if (node == null) return;
        inorder(node.left, vals);
        vals.add(node.val);
        inorder(node.right, vals);
    }
    //endregion
}
