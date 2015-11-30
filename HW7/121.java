public class Solution {
    public int maxProfit(int[] prices) {
        int sum = 0, max = 0, delta = 0;
        for(int i = 1; i < prices.length; i++){
            delta = prices[i] - prices[i-1];
            sum += delta;
            if(sum<0) sum = 0;//此时找到比之前最初值更小的值
            if(max<sum) max = sum;
        }
        return max;
    }
}