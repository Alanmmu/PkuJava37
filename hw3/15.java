public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>(); 
    	Arrays.sort(nums);
    	int last_num1=-1000;
    	int last_num2=-1000;
    	int last_num3=-1000;
    	for(int i=0;i<nums.length;i++)
    	{
    	    if(i>0&&nums[i]==nums[i-1])continue;
    		for(int j=i+1;j<nums.length;j++)
    			for(int k=j+1;k<nums.length;k++)
    			 if(nums[i]+nums[j]+nums[k]==0)
    			 {
    			     if(last_num1!=nums[i]||last_num2!=nums[j]||last_num3!=nums[k])
    			     {      
    			            List<Integer> temp = new ArrayList<Integer>();
    					    temp.add(nums[i]);
    					    temp.add(nums[j]);
    					    temp.add(nums[k]);
    					    last_num1=nums[i];
    					    last_num2=nums[j];
    					    last_num3=nums[k];
    					    list.add(temp);
    					    
    			     }
    			 }
    	}
    	return list;
        
    }
}