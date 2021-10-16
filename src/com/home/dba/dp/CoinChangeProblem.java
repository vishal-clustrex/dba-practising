package com.home.dba.dp;

public class CoinChangeProblem {

	public static void main(String[] args) {
		CoinChangeProblem coinChangeProblem = new CoinChangeProblem();
		int[] coins = new int [] {1,2,3};
		int output = coinChangeProblem.getCount(coins, coins.length, 4);
		System.out.println("Output : " + output);
	}
	
	private int getCount(int[] coins, int n, int sum) {
		int[][] db = new int[sum+1][n+1];
		for(int i = 0; i <= n; i++) {
			db[0][i] = 1;
		}
		for(int i = 1; i <= sum; i++) {
			db[i][0] = 0;
		}
		
		for(int i = 1; i <= sum; i++) {
			for(int j = 1; j <= n; j++) {
				db[i][j] = db[i][j-1];
				if(coins[j-1] <= i) {
					db[i][j] += db[i-coins[j-1]][j];
				}
			}
		}
		return db[sum][n];
	}
	
}
