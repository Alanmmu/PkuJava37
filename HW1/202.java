public class Solution {
    public boolean isHappy(int n) {
        if(n <= 0) return false;
		Set<Integer> set = new HashSet<Integer>();
		
		int cnt = n;//������õ��м�ֵ
		while(cnt!=1){
			if(set.contains(cnt)==true) return false;
			set.add(cnt);
			cnt = calc(cnt);
		}
		return true;
	}
	
	private static int calc(int n){
		int mid = 0;  //�м�ֵ
		int l;
		while(n>0){
			l = n%10;
			mid += l*l;
			n /= 10;
		}
		return mid;
	}
}