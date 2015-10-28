/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {//same as BS preorder Traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderList = new ArrayList<Integer>();
        Stack<TreeNode> tempList = new Stack<TreeNode>();
        TreeNode temp = root;
        while(temp!=null || !tempList.empty()){
            while(temp!=null){
                tempList.push(temp);
                temp = temp.left;
            }
            if(!tempList.empty()){
                temp = tempList.peek();
                inorderList.add(temp.val);
                tempList.pop();
                temp = temp.right;
            }
        }
        return inorderList;
    }
}