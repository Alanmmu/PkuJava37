/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if((p.val-root.val)*(q.val-root.val)==0)return root;//p、q其中一个就是root
        if((p.val-root.val)*(q.val-root.val)<0)return root;//p、q位于root节点两边
        if(p.val<root.val&&q.val<root.val)return lowestCommonAncestor(root.left,p,q);//p、q都在root的左边，调用递归函数
        else return lowestCommonAncestor(root.right,p,q);//p、q都在root的右边，调用递归函数
    }
}