package com.home.dba.dp;

public class MinimumPagesDP {

	public static void main(String[] args) {
		MinimumPagesDP minimumPagesDP = new MinimumPagesDP();
		int[] arr = new int[] {10, 20, 30, 40};
		int noOfStudents = 2;
		int output = minimumPagesDP.minimumPages(arr, arr.length, noOfStudents);
		System.out.println("Output : " + output);
	}
	
	private int minimumPages(int[] arr, int n, int k) {
		int[][] db = new int[k+1][n+1];
		for(int i=1; i<=n; i++) {
			db[1][i] = sum(arr, 0, i-1);
		}
		
		for(int i=1; i<=k; i++) {
			db[i][1] = arr[0];
		}
		
		for(int i=2; i<=k; i++) {
			for(int j=2; j<=n; j++) {
				int res = Integer.MAX_VALUE;
				for(int p = 1; p < j; p++) {
					res = Math.min(res, Math.max(db[i-1][p], sum(arr, p, j-1)));
				}
				db[i][j] = res;
			}
		}
		
		return db[k][n];		
	}
	
	private int sum(int[] arr, int i, int j) {
		int sum = 0;
		for(int k = i; k <= j; k++) {
			sum = sum + arr[k];
		}
		return sum;
	}
}
