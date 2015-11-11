public class Solution {
    public int romanToInt(String s) {
        int len = s.length();
        int num[] = new int [len];
        for(int i = 0; i < len; i++){
            num[i] = convert(s.charAt(i));
        }
        int temp, sum = 0;
        for(int i = 0; i < len; i++){
            if(i != len-1)
            if(num[i] == 100 || num[i] == 10 ||num[i] == 1)//可有可无
            if(num[i+1]>num[i])
                num[i] = -num[i];
        }
        for(int i = 0; i < len; i++){
            sum += num[i];
        }
        return sum;
    }
    private int convert(char x){
        switch(x){
            case 'I': 
                return 1;
            case 'V': 
                return 5;
            case 'X': 
                return 10;
            case 'L': 
                return 50;
            case 'C': 
                return 100;
            case 'D': 
                return 500;
            case 'M': 
                return 1000;
        }
        return 0;
    }
}
//I II III IV V X L（50）、C（100）、D（500）、 M（1000