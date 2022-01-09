package com.home.dba.dp;

public class KnapsackProblem {

	public static void main(String[] args) {
		KnapsackProblem knapsackProblem = new KnapsackProblem();
		int[] wt = new int[] {5, 4, 6, 3};
		int[] v = new int[] {10, 40, 30, 50};
		int w = 10;
		int output = knapsackProblem.knapsackRecurive(w, wt, v, wt.length);
		System.out.println("Output : " + output);
		int dpOutput = knapsackProblem.knapsackDp(w, wt, v, wt.length);
		System.out.println("DP Output : " + dpOutput);
	}
	
	private int knapsackDp(int w, int[] wt, int[] v, int n) {
		int[][] db = new int[n+1][w+1];
		
		for(int i=0;i<=w;i++) {
			db[0][i] = 0;
		}
		
		for(int i=0;i<=n;i++) {
			db[i][0] = 0;
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=w; j++) {
				if(wt[i-1] > j) {
					db[i][j] = db[i-1][j];
				} else {
					db[i][j] = Math.max(v[i-1] + db[i-1][j-wt[i-1]], db[i-1][j]);
				}
			}
		}
		
		return db[n][w];
	}
	
	private int knapsackRecurive(int w, int[] wt, int[] v, int n) {
		if(n == 0 || w == 0) {
			return 0;
		}
		
		if(wt[n-1] > w) {
			return knapsackRecurive(w, wt, v, n-1);
		} else {
			return Math.max(knapsackRecurive(w, wt, v, n-1), v[n-1] + knapsackRecurive(w-wt[n-1], wt, v, n-1));
		}
	}
	
}
