public class Solution {
    public int climbStairs(int n) {
       double t=Math.sqrt(5);
       double x1=(1+t)/2;
       double x2=(1-t)/2;
       double xn1=1;
       double xn2=1;
       int result=0;
       double result_d=1/t;
       for(int i=0;i<=n;i++)
       {
           xn1*=x1;
           xn2*=x2;
       }    
       result_d*=(xn1-xn2);
       result=(int)result_d;
       return result;
    }
}