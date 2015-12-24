public class Solution {
    public void moveZeroes(int[] nums) {
        int num_of_zero=0;
        int temp=0;
        for(int i=nums.length-1;i>=0;i--)//从最后一个0开始，将最后一个0按位换至最后一个位置，再找前一个0
        {
            if(nums[i]==0)
                for(int j=i;j<nums.length-1-num_of_zero;j++)
                {
                    temp=nums[j+1];
                    nums[j+1]=nums[j];
                    nums[j]=temp;
                }
        }
    }
}