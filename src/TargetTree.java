import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author yyb
 * leetcode_tag_tree
 * leetcode 标签 树
 */
public class TargetTree {
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

    //region 108. 将有序数组转换为二叉搜索树  2019/10/7  递归+二分搜索
    /**
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
     *
     * 示例:
     * 给定有序数组: [-10,-3,0,5,9],
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode res=sortedArrayToBSTDsf(0,nums.length-1,nums);
        return res;
    }

    public TreeNode sortedArrayToBSTDsf(int left,int right,int[] nums){
        if(left==right){
            return new TreeNode(nums[left]);
        }
        if(left>right){
            return null;
        }
        int mid=(left+right+1)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=sortedArrayToBSTDsf(left,mid-1,nums);
        root.right=sortedArrayToBSTDsf(mid+1,right,nums);
        return root;
    }
    //endregion

    //region 156. 上下翻转二叉树  2019/10/7
    /**
     * 给定一个二叉树，其中所有的右节点要么是具有兄弟节点（拥有相同父节点的左节点）的叶节点，要么为空，将此二叉树上下翻转并将它变成一棵树， 原来的右节点将转换成左叶节点。返回新的根。
     *
     * 例子:
     * 输入: [1,2,3,4,5]
     *
     *     1
     *    / \
     *   2   3
     *  / \
     * 4   5
     *
     * 输出: 返回二叉树的根 [4,5,2,#,#,3,1]
     *
     *    4
     *   / \
     *  5   2
     *     / \
     *    3   1
     * 说明:
     * 对 [4,5,2,#,#,3,1] 感到困惑? 下面详细介绍请查看 二叉树是如何被序列化的。
     * 二叉树的序列化遵循层次遍历规则，当没有节点存在时，'#' 表示路径终止符。
     *
     * 这里有一个例子:
     *    1
     *   / \
     *  2   3
     *     /
     *    4
     *     \
     *      5
     * 上面的二叉树则被序列化为 [1,2,3,#,#,4,#,#,5].
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode parent = null, parent_right = null;
        while(root != null){
            TreeNode root_left = root.left;
            root.left = parent_right;
            parent_right = root.right;
            root.right = parent;
            parent = root;
            root = root_left;
        }
        return parent;
    }
    //endregion

    //region 226. 翻转二叉树  2017/10/7  递归
    /**
     * 翻转一棵二叉树。
     * 示例：
     * 输入：
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 输出：
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     * 备注:
     * 这个问题是受到 Max Howell 的 原问题 启发的 ：
     * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode right=invertTree(root.right);
        TreeNode left=invertTree(root.left);
        root.left=right;
        root.right=left;
        return root;
    }
    //endregion

    //region 617. 合并二叉树  2019/10/7  递归
    /**
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，
     * 两个二叉树的一些节点便会重叠。
     *
     * 你需要将他们合并为一个新的二叉树。
     * 合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     *
     * 示例 1:
     * 输入:
     * 	Tree 1                     Tree 2
     *           1                         2
     *          / \                       / \
     *         3   2                     1   3
     *        /                           \   \
     *       5                             4   7
     * 输出:
     * 合并后的树:
     * 	     3
     * 	    / \
     * 	   4   5
     * 	  / \   \
     * 	 5   4   7
     * 注意: 合并必须从两个树的根节点开始。
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null && t2==null)return null;
        if(t1==null && t2!=null)return t2;
        if(t1!=null && t2==null)return t1;
        t1.val+=t2.val;
        t1.left=mergeTrees( t1.left, t2.left);
        t1.right=mergeTrees( t1.right, t2.right);
        return t1;
    }

    /**
     * 自己写的 深入优先遍历
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        TreeNode res=mergeTreesDfs(t1,t2);
        return res;
    }

    public TreeNode mergeTreesDfs(TreeNode t1,TreeNode t2){
        int value=0;
        if(t1==null&&t2==null){
            return null;
        }
        else{
            if(t1!=null){
                value=value+t1.val;
            }
            if(t2!=null) {
                value = value + t2.val;
            }

            TreeNode root=new TreeNode(value);
            if(t1==null){
                root.left=mergeTreesDfs(null,t2.left);
                root.right=mergeTreesDfs(null,t2.right);
            }
            else if(t2==null){
                root.left=mergeTreesDfs(t1.left,null);
                root.right=mergeTreesDfs(t1.right,null);
            }else{
                root.left=mergeTreesDfs(t1.left,t2.left);
                root.right=mergeTreesDfs(t1.right,t2.right);
            }
            return root;
        }
    }
    //endregion

    //region  654. 最大二叉树  2019/10/7  递归
    /**
     * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
     * 二叉树的根是数组中的最大元素。
     * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
     * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
     * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
     *
     * 示例 ：
     * 输入：[3,2,1,6,0,5]
     * 输出：返回下面这棵树的根节点：
     *
     *       6
     *     /   \
     *    3     5
     *     \    /
     *      2  0
     *        \
     *         1
     *  
     * 提示：

     * 给定的数组的大小在 [1, 1000] 之间。
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode res=constructMaximumBinaryTreeDfs(0,nums.length,nums);
        return res;
    }
    public TreeNode constructMaximumBinaryTreeDfs(int left,int right,int[] nums){
        if(left==right){
            return null;
        }
        int max=Integer.MIN_VALUE;
        int flag=0;
        for(int i=left;i<right;i++){
            if(nums[i]>max){
                max=nums[i];
                flag=i;
            }
        }
        TreeNode root=new TreeNode(max);
        root.left=constructMaximumBinaryTreeDfs(left,flag,nums);
        root.right=constructMaximumBinaryTreeDfs(flag+1,right,nums);
        return root;
    }
    //endregion

    //region 700. 二叉搜索树中的搜索 2019/10/7  搜索
    /**
     * 给定二叉搜索树（BST）的根节点和一个值。
     * 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。
     * 如果节点不存在，则返回 NULL。
     *
     * 例如，
     * 给定二叉搜索树:
     *
     *         4
     *        / \
     *       2   7
     *      / \
     *     1   3
     *
     * 和值: 2
     * 你应该返回如下子树:
     *
     *       2
     *      / \
     *     1   3
     * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null){
            return null;
        }
        while(root!=null){
            if(root.val==val){
                return root;
            }
            if(root.val<val){
                root=root.right;
            }else{
                root=root.left;
            }
        }
        return null;
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
    public int rangeSumBST(TargetRecursion.TreeNode root, int L, int R) {
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
    public int rangeSumBST1(TargetRecursion.TreeNode root, int L, int R) {
        int ans = 0;
        Stack<TargetRecursion.TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TargetRecursion.TreeNode node = stack.pop();
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
    public int rangeSumBST2(TargetRecursion.TreeNode root, int L, int R) {
        ans = 0;
        dfs(root, L, R);
        return ans;
    }

    public void dfs(TargetRecursion.TreeNode node, int L, int R) {
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
    public int rangeSumBST3(TargetRecursion.TreeNode root, int L, int R) {
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
    public void getList(TargetRecursion.TreeNode root, List<Integer> list){
        if(root==null){
            return;
        }
        getList(root.left,list);
        list.add(root.val);
        getList(root.right,list);
    }
    //endregion

    public static void main(String[] args) {
    }

}
