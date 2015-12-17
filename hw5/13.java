public class Solution {
    public int romanToInt(String s) {
        int num=0;
        for(int i=0;i<s.length()-1;i++)
        {
            if(s.charAt(i)=='M')num+=1000;
            if(s.charAt(i)=='D')num+=500;
            if(s.charAt(i)=='L')num+=50;
            if(s.charAt(i)=='V')num+=5;
            if(s.charAt(i)=='C')
                if(s.charAt(i+1)=='M'||s.charAt(i+1)=='D')num-=100;
                else num+=100;
            if(s.charAt(i)=='X')
                if(s.charAt(i+1)=='C'||s.charAt(i+1)=='L')num-=10;
                else num+=10;
             if(s.charAt(i)=='I')
                if(s.charAt(i+1)=='X'||s.charAt(i+1)=='V')num-=1;
                else num+=1;
        }
        if(s.charAt(s.length()-1)=='M')num+=1000;
        if(s.charAt(s.length()-1)=='D')num+=500;
        if(s.charAt(s.length()-1)=='L')num+=50;
        if(s.charAt(s.length()-1)=='V')num+=5;
        if(s.charAt(s.length()-1)=='C')num+=100;
        if(s.charAt(s.length()-1)=='X')num+=10;
        if(s.charAt(s.length()-1)=='I')num+=1;
        return num;
    }
}