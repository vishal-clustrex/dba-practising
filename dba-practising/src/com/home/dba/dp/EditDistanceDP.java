package com.home.dba.dp;

public class EditDistanceDP {

	public static void main(String[] args) {
		EditDistanceDP editDistance = new EditDistanceDP();
		String s1 = "SATURDAY";
		String s2 = "SUNDAY";
		int output = editDistance.editDistance(s1, s2, s1.length(), s2.length());
		System.out.println("Output : " + output);
	}
	
	private int editDistance(String s1, String s2, int m, int n) {
		int[][] db = new int[m+1][n+1];
		for(int i=0; i<=m;i++) {
			db[i][0] = i;
		}
		for(int j=0; j<=n;j++) {
			db[0][j] = j;
		}
		for(int i=1; i<=m;i++) {
			for(int j=1; j<=n;j++) {
				if(s1.charAt(i-1) == s1.charAt(j-1)) {
					 db[i][j] = db[i-1][j-1];
				} else {
					db[i][j] = 1 + Math.min(Math.min(db[i-1][j], 
							db[i][j-1]), 
							db[i-1][j-1]);
				}
			}
		}
		return db[m][n];
	}
}
