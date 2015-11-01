public class Solution {
	//根据在纸上写的规律可以知道符合斐波那契数列算法
    public int climbStairs(int n) {
        int current=1;//当前台阶
        int pre=1;//前一个台阶
        if(n==0||n==1)
        return 1;//在没有台阶或只有一个台阶时到达顶部的方式只有一种
        for(int i=2;i<=n;i++){
            int temp=current+pre;
            pre=current;
            current=temp;
        }
        return current;
    }
}