public class Solution 
{
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) 
    {
        int count=0;//����1�ĸ���
        //long n_long=n;//����ת��
        while(n!=0)
        {
            n=n&(n-1);
            count++;
        }
        return count;
    }
}
//��λ����ķ������Լ���Ľ�������ʱ�� = =!