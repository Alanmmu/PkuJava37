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
    public boolean isSymmetric(TreeNode root) {
        if(root==null)return true;
        else return isSymmetric(root.left,root.right);//ʹ�õݹ�ķ���
    }
    public boolean isSymmetric(TreeNode left,TreeNode right){
        if(left==null&&right==null)return true;
        if(left!=null&&right==null||left==null&&right!=null)return false;
        if(left.val==right.val)
            return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);//����ӵ���������Ҷ��ӵ��Ҷ�����ͬ ����ӵ��Ҷ������Ҷ��ӵ��������ͬ
        return false;
    }
}