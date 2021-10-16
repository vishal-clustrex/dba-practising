package com.home.dba.dp;

public class LongestIncSubsequence {

	public static void main(String[] args) {
		LongestIncSubsequence longestIncSubsequence = new LongestIncSubsequence();
		int n[] = new int[] {3,4,2,8,10,1};
		int output = longestIncSubsequence.lis(n, n.length);
		System.out.println("Output : " + output);
	}
	
	private int lis(int[] arr, int n) {
		int[] l = new int[n];
		l[0] = 1;
		
		for(int i=1; i<n; i++) {
			l[i] = 1;
			for(int j=0; j<i; j++) {
				if(arr[j] < arr[i]) {
					l[i] = Math.max(l[i], l[j]+1);
				}
			}
		}
		
		int res = l[0];
		for(int i = 0; i < n; i++) {
			res = Math.max(res, l[i]);
		}
		return res;
	}
	
}
