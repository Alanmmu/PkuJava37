public class Solution {
    public int removeDuplicates(int[] nums) {
         if(nums.length == 0)
    	   return 0;
       int pre = nums[0];
       int cur = 1;
       int index = 1;
       while (index < nums.length){
    	   if(nums[index] != pre){
    		 nums[cur++] = nums[index];
    		   
    	   }
           pre = nums[index];
    	   index++;
       }
		//for(int i = 0; i < nums.length; i++){
			//for(int j = i+1; j < nums.length; j++ ){
				//if(nums[i]==nums[j])
		return cur;
    }
}