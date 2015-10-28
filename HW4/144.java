/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {//非递归不知道错在哪里了-，-
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preList = new ArrayList<Integer>();
        Stack<TreeNode> tempList = new Stack<TreeNode>();
        TreeNode temp = root;
        while(temp!=null || !tempList.empty()){
            while(temp!=null){
                preList.add(temp.val);
                tempList.push(temp);
                temp = temp.left;
            }
            if(!tempList.empty()){
                temp = tempList.peek();
                tempList.pop();
                temp = temp.right;
            }
        }
        return preList;
    }
    
}