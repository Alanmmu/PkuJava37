public class Solution {
    public boolean isAnagram(String s, String t) {
        //public boolean isAnagram(String s,String t){
    	   /*int size1 = s.length();
    	   int size2 = t.length();
    	   if(size1 == size2)
    		   for(int i = 0;i < s.length(); i++){
    			   for(int j = 0; j < t.length(); j++){
    				   if(s.charAt(i)==t.charAt(j))
    					 	  return true;
    				   }   
    		   }
          else
    	  return false;*/
		char[] tArr = s.toCharArray();
		char[] sArr = t.toCharArray();
		Arrays.sort(sArr);//数组排序
		Arrays.sort(tArr);
		return String.valueOf(sArr).equals(String.valueOf(tArr));
		//数组排序后要是相等就是相同的变形词否则不是，转换成字符串比较
    }
}