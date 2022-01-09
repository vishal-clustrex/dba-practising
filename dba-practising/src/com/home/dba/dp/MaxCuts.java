package com.home.dba.dp;

public class MaxCuts {

	public static void main(String[] args) {
		MaxCuts maxCuts = new MaxCuts();
		int output = maxCuts.maxCut(5, 5, 2, 3);
		System.out.println("Output : " + output);
	}
	
	private int maxCut(int n, int a, int b, int c) {
		int[] db = new int[n+1];
		db[0] = 0;
		for(int i = 1; i <= n ; i++) {
			db[i] = -1;
			if(i-a >= 0) db[i] = Math.max(db[i], db[i-a]);
			if(i-b >= 0) db[i] = Math.max(db[i], db[i-b]);
			if(i-c >= 0) db[i] = Math.max(db[i], db[i-c]);
			if(db[i] != -1) {
				db[i]++;
			}
		}
		return db[n];
	}
	
}
