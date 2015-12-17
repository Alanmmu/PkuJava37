public class Solution {
    public String intToRoman(int num) {
        int t=0,h=0,s=0,g=0;//分别代表千百十个位
        t=num/1000;
        num%=1000;
        h=num/100;
        num%=100;
        s=num/10;
        g=num%10;
        String strt=null,strh=null,strs=null,strg=null;
        String str1=null,str2=null,str=null;
        if(t==3)strt="MMM";
        if(t==2)strt="MM";
        if(t==1)strt="M";
        if(t==0)strt="";
        if(h==9)strh="CM";
        if(h==8)strh="DCCC";
        if(h==7)strh="DCC";
        if(h==6)strh="DC";
        if(h==5)strh="D";
        if(h==4)strh="CD";
        if(h==3)strh="CCC";
        if(h==2)strh="CC";
        if(h==1)strh="C";
        if(h==0)strh="";
        if(s==9)strs="XC";
        if(s==8)strs="LXXX";
        if(s==7)strs="LXX";
        if(s==6)strs="LX";
        if(s==5)strs="L";
        if(s==4)strs="XL";
        if(s==3)strs="XXX";
        if(s==2)strs="XX";
        if(s==1)strs="X";
        if(s==0)strs="";
        if(g==9)strg="IX";
        if(g==8)strg="VIII";
        if(g==7)strg="VII";
        if(g==6)strg="VI";
        if(g==5)strg="V";
        if(g==4)strg="IV";
        if(g==3)strg="III";
        if(g==2)strg="II";
        if(g==1)strg="I";
        if(g==0)strg="";
        str1=strt+strh;
        str2=strs+strg;
        str=str1+str2;
        return str;
    }
}