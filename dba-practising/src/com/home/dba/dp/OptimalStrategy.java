package com.home.dba.dp;

public class OptimalStrategy {

	public static void main(String[] args) {
		OptimalStrategy optimalStrategy = new OptimalStrategy();
		int[] arr = new int[] {20, 5, 4, 6, 8, 3};
		int output = optimalStrategy.optimalStrategy(arr, arr.length);
		System.out.println("OS Output : " + output);
	}
	
	private int optimalStrategy(int[] arr, int n) {
		int[][] db = new int[n][n];
		
		for(int i = 0; i < n-1; i++) {
			db[i][i+1] = Math.max(arr[i], arr[i+1]);
		}
		
		for(int gap = 3; gap < n; gap = gap + 2) {
			for(int i = 0; i+gap < n; i++) {
				int j = i + gap;
				db[i][j] = Math.max(arr[i] + Math.min(db[i+2][j], db[i+1][j-1]), 
						arr[j] + Math.min(db[i+1][j-1], db[i][j-2]));
			}
		}
		
		return db[0][n-1];
	}
	
}
