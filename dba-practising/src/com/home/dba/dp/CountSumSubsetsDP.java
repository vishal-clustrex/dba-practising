package com.home.dba.dp;

public class CountSumSubsetsDP {

	public static void main(String[] args) {
		CountSumSubsetsDP countSumSubsetsDP = new CountSumSubsetsDP();
		int[] arr = new int[] {2, 5, 3};
		int sum = 5;
		int output = countSumSubsetsDP.sumOfSubsets(arr, arr.length, sum);
		System.out.println("Output : " + output);
	}
	
	private int sumOfSubsets(int[] arr, int n, int sum) {
		int[][] db = new int[n+1][sum+1];
		
		for(int i = 0; i <= n; i++) {
			db[i][0] = 1;
		}
		
		for(int i = 1; i <= sum; i++) {
			db[0][i] = 0;
		}
		
		for(int i=1; i<=n ; i++) {
			for(int j=1;j<=sum;j++) {
				if(j < arr[i-1]) {
					db[i][j] = db[i-1][j];
				} else {
					db[i][j] = db[i-1][j] + db[i][j - arr[i-1]];
				}
			}
		}
		return db[n][sum];
	}
	
}
