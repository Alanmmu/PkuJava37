public class Solution 
{
    public int reverse(int x) 
    {
        long long_r_num=0;//��һ��long������¼��ʼ�ķ�ת��
        int temp;
        while(x!=0)//�����ת��
        {
        	long_r_num*=10;
        	temp=x%10;
        	long_r_num+=temp;
        	x/=10;
        }
        if(long_r_num<-2147483648||long_r_num>2147483647)long_r_num=0;//�����int��Χ����0
        int r_num=(int) long_r_num;//ǿ������ת��
        return r_num;
    }
}