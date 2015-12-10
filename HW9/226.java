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
	//使用递归的办法解决
    public TreeNode invertTree(TreeNode root) {
         if(root == null)
    		  return null;
    	  
    	  TreeNode temp = root.left;
    	  root.left = root.right;
    	  root.right = temp;
    	  
    	  invertTree(root.left);
    	  invertTree(root.right);
    	  
    	  return root;
    }
}