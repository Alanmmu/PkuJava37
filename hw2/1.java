public class Solution
{
    public  int[] twoSum(int[] nums, int target) 
    {
    	int []index=new int[2];//��¼index1��index2
    	
    	for(int i=0;i<nums.length;i++)//�������飬�ҵ�����������������
    	{
    	//if(nums[i]<=target)//��ͼ��֦δ������Ϊ��������и���
    		//{
    			for(int j=i+1;j<nums.length;j++)
    			{
    				if(nums[i]+nums[j]==target)
    				{
    					index[0]=i+1;
    					index[1]=j+1;
    					break;
    				}
    			}
    	//	}
    		
    	}
    	
        return index;
    }
}