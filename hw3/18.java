public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>(); 
    	Arrays.sort(nums);
        int last_num1=-10000;
    	int last_num2=-10000;
    	int last_num3=-10000;
    	int last_num4=-10000;
    	for(int i=0;i<nums.length;i++)
    	{   
    	    if(i>0&&nums[i]==nums[i-1])continue;
    	    for(int j=i+1;j<nums.length;j++)  
            {   
                if(j>i+1&&nums[j]==nums[j-1])continue;
    	        int begin=j+1;
    	        int end=nums.length-1;
    	        while(begin<end)
    	        {
    	            int sum=nums[i]+nums[j]+nums[begin]+nums[end]; 
    	            if(sum==target&&(last_num1!=nums[i]||last_num2!=nums[j]||last_num3!=nums[begin]||last_num4!=nums[end]))
    	            {
    	                List<Integer> temp = new ArrayList<Integer>();
    	                temp.add(nums[i]);
    	                temp.add(nums[j]);
    	                temp.add(nums[begin]);
    	                temp.add(nums[end]);
    	                list.add(temp);
    	                last_num1=nums[i];
    	                last_num2=nums[j];
    	                last_num3=nums[begin];
    	                last_num4=nums[end];
    	                //System.out.printf("%d %d %d %d\n",i,j,begin,end);
    	                begin++;
    	                end--;
    	                
    	            }
    	            else if(sum<target)begin++;
    	            else end--;
    	        }
    	        
            }
    	}
    	return list;
    }
}