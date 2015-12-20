public class Solution {
    public int countPrimes(int n) {
        boolean[] notprimes = new boolean[n];
		if(n <= 2)
			return 0;
		for(int i = 2; i*i < n;i++){
			if(!notprimes[i]){
				for(int j = i;i*j < n;j++){
					notprimes[i*j] = true;
				}
			}
		}
		int c = 0;
		for(int i=2;i<n;i++){
			if(notprimes[i] == false)
				++c;
		}
		return c;
    }
}