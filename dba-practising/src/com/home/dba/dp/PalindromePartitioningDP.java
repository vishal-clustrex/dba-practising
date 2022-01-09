package com.home.dba.dp;

public class PalindromePartitioningDP {

	public static void main(String[] args) {
		PalindromePartitioningDP palindromePartitioning = new PalindromePartitioningDP();
		String s = "abbac";
		int output = palindromePartitioning.palPartition(s);
		System.out.println("Output : " + output);
	}
	
	private int palPartition(String s) {
		int n = s.length();
		int[][] db = new int[n][n];
		boolean[][] isPal = new boolean[n][n];
		for(int i=0; i<n; i++) {
			db[i][i] = 0;
			isPal[i][i] = true;
		}
		
		for(int gap = 1; gap < n; gap++) {
			for(int i = 0; i + gap < n; i++) {
				int j = i + gap;
				if(s.charAt(i) == s.charAt(j) && (isPal[i+1][j-1] || gap == 1)) {
					db[i][j] = 0;
					isPal[i][j] = true;
				} else {
					db[i][j] = Integer.MAX_VALUE;
					isPal[i][j] = false;
					for(int k = i; k < j; k++) {
						db[i][j] = Math.min(db[i][j], 1 + db[i][k] + db[k+1][j]);
					}
				}
			}
		}
		return db[0][n-1];
	}
	
}
