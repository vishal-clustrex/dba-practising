package com.home.dba.dp;

public class MaxSum {

	public static void main(String[] args) {
		MaxSum maxSum = new MaxSum();
		int[] n = new int[] {10, 20, 10, 40, 30};
		int output = maxSum.maxSum(n, n.length);
		System.out.println("Output : " + output);
		int sOutput = maxSum.maxSumSpaceOpt(n, n.length);
		System.out.println("Output : " + sOutput);
	}
	
	private int maxSum(int arr[], int n) {
		int[] db = new int[n+1];
		if(n==1) return arr[0];
		db[1] = arr[0];
		db[2] = Math.max(arr[0], arr[1]);
		for(int i=3; i<=n; i++) {
			db[i] = Math.max(db[i-1], db[i-2] + arr[i-1]);
		}
		return db[n];
	}
	
	private int maxSumSpaceOpt(int arr[], int n) {
		if(n==1) return arr[0];
		int prevPrev = arr[0];
		int prev = Math.max(arr[0], arr[1]);
		int res = prev;
		for(int i=3; i<=n; i++) {
			res = Math.max(prev, prevPrev + arr[i-1]);
			prevPrev = prev;
			prev = res;
		}
		return res;
	}
}
