package com.home.dba.dp;

public class MinJumps {

	public static void main(String[] args) {
		MinJumps minJumps = new MinJumps();
		int[] arr = new int[] {3,4,1,2,1};
		int output = minJumps.minJumps(arr, arr.length);
		System.out.println("Output : " + output);
	}
	
	private int minJumps(int arr[], int n) {
		if(n==1) return 0;
		int res = Integer.MAX_VALUE;
		for(int i = 0; i <= n-2; i++) {
			if(i+arr[i] >= n-1) {
				int subRes = minJumps(arr, i+1);
				if(subRes != Integer.MAX_VALUE) {
					res = Math.min(res, subRes+1);
				}
			}
		}
		return res;
	}
	
}
