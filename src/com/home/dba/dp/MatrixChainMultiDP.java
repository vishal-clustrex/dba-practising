package com.home.dba.dp;

public class MatrixChainMultiDP {

	public static void main(String[] args) {
		MatrixChainMultiDP matrixChainMultiDP = new MatrixChainMultiDP();
		int[] arr = new int[] {20, 10, 30, 40};
		int output = matrixChainMultiDP.mcm(arr, arr.length);
		System.out.println("Output : " + output);
	}
	
	private int mcm(int[] arr, int n) {
		int[][] db = new int[n][n];
		
		for(int i=0; i<n-1; i++) {
			db[i][i+1] = 0;
		}
		for(int gap = 2; gap < n; gap++) {
			for(int i=0; i+gap < n; i++) {
				int j = i + gap;
				db[i][j] = Integer.MAX_VALUE;
				for(int k = i+1; k < j; k++) {
					db[i][j] = Math.min(db[i][j], db[i][k] + db[k][j] + (arr[i] * arr[k] * arr[j]));
				}
			}
		}
		
		return db[0][n-1];
	}
	
	
}
