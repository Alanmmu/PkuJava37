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
        if((p.val-root.val)*(q.val-root.val)==0)return root;//p��q����һ������root
        if((p.val-root.val)*(q.val-root.val)<0)return root;//p��qλ��root�ڵ�����
        if(p.val<root.val&&q.val<root.val)return lowestCommonAncestor(root.left,p,q);//p��q����root����ߣ����õݹ麯��
        else return lowestCommonAncestor(root.right,p,q);//p��q����root���ұߣ����õݹ麯��
    }
}