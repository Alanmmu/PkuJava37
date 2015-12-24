public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int a=m-1;
    	int b=n-1;
    	int newnum=m+n-1;
		while (newnum>=0) {//从后向前，将两数列中最后一个数字中的较大的一个放在最后
			 if (b<0||(a>=0&&nums1[a]>nums2[b]))
				nums1[newnum--]=nums1[a--];
			 else
				nums1[newnum--]=nums2[b--];
		}
    }
}