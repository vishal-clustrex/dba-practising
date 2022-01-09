package com.home.dba.dp;

import java.util.Scanner;

public class LCSTabulation {

	int memo[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Longest Common Subsequence");
		System.out.println("Enter first string : ");
		String s1 = sc.next();
		System.out.println("Enter second string : ");
		String s2 = sc.next();
		sc.close();
		LCSTabulation lcsTabulation = new LCSTabulation();
		lcsTabulation.init(s1.length(), s2.length());
		System.out.println(lcsTabulation.lcs(s1, s2));
	}
	
	private void init(int m, int n) {
		memo = new int[m+1][n+1];
	}
	
	private int lcs(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		for(int i=0; i<m; i++) {
			memo[i][0] = 0;
		}
		for(int j=0; j<n; j++) {
			memo[0][j] = 0;
		}
		for(int i=1; i<=m; i++) {
			for(int j=1; j<=n; j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					memo[i][j] = 1 + memo[i-1][j-1];
				} else {
					memo[i][j] = Math.max(memo[i-1][j], memo[i][j-1]);
				}
			}
		}
		return memo[m][n];
	}
}
