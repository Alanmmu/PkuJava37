public class Solution 
{
    public int addDigits(int num) 
    {
        int sum=num;//��¼���
        while(num>9)//num����9ѭ��
        {
            sum=0;
            while(num>=1)//�����λ���ֵĺ�
            {
                sum+=num%10;
                num/=10;
            }
            num=sum;//��sum����num����ѭ��
        }
        return sum;//numС��10���
    }
}