public class Solution {
    public void moveZeroes(int[] nums) {
        int num_of_zero=0;
        int temp=0;
        for(int i=nums.length-1;i>=0;i--)//�����һ��0��ʼ�������һ��0��λ�������һ��λ�ã�����ǰһ��0
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