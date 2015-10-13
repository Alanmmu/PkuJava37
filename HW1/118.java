public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> Rows = new ArrayList<List<Integer>>();
		if(numRows == 0) return Rows;
		List<Integer> firstRow = new ArrayList<Integer>();  //��һ�е���ֵ
		//List<Integer> firstRow = new ArrayList<Integer>();  //��һ�е���ֵ
		firstRow.add(1);
		Rows.add(firstRow);
		if(numRows == 1) return Rows; //���������
		List<Integer> previousRow = new ArrayList<Integer>(); //���ڼ�¼ǰһ����Ϣ������������һ�е���
		previousRow = firstRow;
		for(int i = 2; i <= numRows; i++){
			List<Integer> row = new ArrayList<Integer>();   //���ڵ�ǰ�еĴ���
			//List<Integer> row = new ArrayList<Integer>();   //���ڵ�ǰ�еĴ���
			row.add(1);   //��һ��ֵΪ1
			//List<Integer> previousRow = new ArrayList<Integer>();
			//previousRow = Rows.get(i-2);
			for(int j = 1; j < i-1; j++){//���ڵ�ǰ��ÿһ��ֵ����һ�еó�
				//row.add( previousRow.get(i-1) + previousRow.get(i) );
				row.add( previousRow.get(j-1) + previousRow.get(j) );
			}
			row.add(1);  //���һ��Ԫ����1
			previousRow = row;
			Rows.add(row);
		}
		return Rows;
    }
}