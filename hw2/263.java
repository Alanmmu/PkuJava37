public class Solution 
{
    public boolean isUgly(int num)
    {
        if(num<=0)return false;//�ų�����
        while(num%2==0)//����������2����
            num/=2;
        while(num%3==0)//����������3����
            num/=3;
        while(num%5==0)//����������5����
            num/=5;
        if(num==1)return true;//��û���������Ӽ�Ϊugly number
        else return false;
    }
}