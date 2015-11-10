public class Solution {
    public int romanToInt(String s) {
        int result=roman(s.charAt(0));
        for(int i=1;i<s.length();i++){
            /*if(i==s.length()-1){
               result=roman(s[i]);
               continue;
            }*/
            if(roman(s.charAt(i-1))<roman(s.charAt(i))){
            result+=roman(s.charAt(i))-2*roman(s.charAt(i-1));
            }else{
            result+=roman(s.charAt(i));
            }
        }
        return result;
    }
        
        int roman(char c){
            switch(c){
                case'I':return 1;
                case'V':return 5;
                case'X':return 10;
                case'L':return 50;
                case'C':return 100;
                case'D':return 500;
                case'M':return 1000;
            }
            return 0;
        }
        
}
