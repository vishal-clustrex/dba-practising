package com.home.dba.dp;

public class MinCoinValue {

	public static void main(String[] args) {
		MinCoinValue minCoinValue = new MinCoinValue();
		int[] coins = new int[] {3, 2, 1};
		int output = minCoinValue.getMinCut(coins, coins.length, 5);
		System.out.println("Output : " + output);
	}
	
	private int getMinCut(int arr[], int n, int val) {
		int[] db = new int[val+1];
		db[0] = 0;
		for(int i = 1; i <= val;i++) {
			db[i] = Integer.MAX_VALUE;
		}
		for(int i=1; i<=val; i++) {
			for(int j=0;j<n;j++) {
				if(arr[j] <= i) {
					int subRes = db[i - arr[j]];
					if(subRes != Integer.MAX_VALUE) {
						db[i] = Math.min(db[i], subRes + 1);
					}
				}
			}
		}
		
		return db[val];
	}
	
}
