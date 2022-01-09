package com.home.dba.dp;

public class EggDroppingProblem {

	public static void main(String[] args) {
		EggDroppingProblem eggDroppingProblem = new EggDroppingProblem();
		int output = eggDroppingProblem.res(2, 3);
		System.out.println("Output : " + output);
	}

	private int res(int e, int f) {
		int[][] db = new int[f+1][e+1];
		
		for(int i=1; i<=e; i++) {
			db[1][i] = 1;
			db[0][i] = 0;
		}
		
		for(int j=1; j<=f; j++) {
			db[j][1] = j;
		}
		
		for(int i=2; i<=f; i++) {
			for(int j=2; j<=e; j++) {
				db[i][j] = Integer.MAX_VALUE;
				for(int x = 1; x <= i; x++) {
					db[i][j] = Math.min(db[i][j], 1 + Math.max(db[x-1][j-1], db[i-x][j]));
				}
			}
		}
		
		return db[f][e];
	}
	
}
