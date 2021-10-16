package com.home.dba.dp;

public class MaxLCS {

	public static void main(String[] args) {
		MaxLCS maxLcs = new MaxLCS();
		int n[] = new int[] {3,4,100,8,10,1};
		int output = maxLcs.maxLcs(n, n.length);
		System.out.println("Output : " + output);
	}
	
	private int maxLcs(int[] arr, int n) {
		int[] msis = new int[n];
		msis[0] = arr[0];
		
		for(int i=1; i<n; i++) {
			msis[i] = arr[i];
			for(int j=0; j<i; j++) {
				if(arr[j] < arr[i]) {
					msis[i] = Math.max(msis[i], msis[j]+arr[i]);
				}
			}
		}
		
		int res = msis[0];
		for(int i = 0; i < n; i++) {
			res = Math.max(res, msis[i]);
		}
		return res;
	}
	
}
