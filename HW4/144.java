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
    public List<Integer> preorderTraversal(TreeNode root) {
			ArrayList<Integer> lst= new ArrayList <Integer>();
			if(root== null)
				return lst;
			Stack<TreeNode> stack = new Stack<TreeNode>();
			TreeNode p=root;
			stack.push(p);
			while(!stack.empty()){
			    //stack.push(p);
			    TreeNode t=stack.pop();
			        lst.add(t.val);
			    if(t.right!=null){
			        stack.push(t.right);
			        //p=p.right;
			    }if(t.left!=null){
			        stack.push(t.left);
			    }
			}
		return lst; 
    }
}