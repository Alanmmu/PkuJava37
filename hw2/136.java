public class Solution 
{
    public int singleNumber(int[] nums) 
    {   
        int single=nums[0];
        for(int i=0;i<nums.length-1;i++)
        {
            single=single^nums[i+1];
        }
        return single;
    }
}
//���ý����������а�λ���^�ķ���
//ԭ����^�������㽻���� �� 0^a=a
//��� a1^a2^a3.....^an�ɵ�Ψһ��singlenumber