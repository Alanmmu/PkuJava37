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
    /*public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postList = new ArrayList<Integer>();
        if(root!=null){
            postList.addAll( postorderTraversal(root.left) );
            postList.addAll( postorderTraversal(root.right) );
            postList.add(root.val);
        }
        return postList;
    }*/
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postList = new ArrayList<Integer>();
        Stack<TreeNode> tempList = new Stack<TreeNode>();
        TreeNode cur = root;
        TreeNode pre = null;
        TreeNode l = null;
        TreeNode r = null;
        tempList.push(root);
        while(!tempList.empty()){
            cur = tempList.peek();
            if(cur == null) break;
            l = cur.left;
            r = cur.right;
            if( (l==null && r==null) ||
            (pre!=null &&(l == pre || r == pre)) ){
                postList.add(cur.val);
                tempList.pop();
                pre = cur;
            }else{
                if(cur.right != null){//一定要先Push right，再push left
                    tempList.push(cur.right);
                }
                if(cur.left != null){
                    tempList.push(cur.left);
                }
                
            }
        }
        
        return postList;
    }
}