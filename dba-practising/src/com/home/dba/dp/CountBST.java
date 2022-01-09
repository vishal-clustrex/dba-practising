package com.home.dba.dp;

public class CountBST {

	public static void main(String[] args) {
		CountBST countBST = new CountBST();
		int output = countBST.countBST(5);
		System.out.println("Output : " + output);
	}

	private int countBST(int n) {
		int[] db = new int[n+1];
		db[0] = 1;
		for(int i=1; i<=n; i++) {
			db[i] = 0;
			for(int j=0; j<i; j++) {
				db[i] += db[j] * db[i-j-1];
			}
		}
		return db[n];
	}
	
}
