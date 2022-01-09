package com.home.dba.dp;

import java.util.Scanner;

public class LCSMemoization {

	int memo[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Longest Common Subsequence");
		System.out.println("Enter first string : ");
		String s1 = sc.next();
		System.out.println("Enter second string : ");
		String s2 = sc.next();
		sc.close();
		LCSMemoization lcsMemoization = new LCSMemoization();
		lcsMemoization.init(s1.length(), s2.length());
		System.out.println(lcsMemoization.lcs(s1, s2, s1.length(), s2.length()));
	}
	
	private void init(int m, int n) {
		memo = new int[m+1][n+1];
	}
	
	private int lcs(String s1, String s2, int m, int n) {
		if(memo[m][n] == 0) {
			int res;
			if(m == 0 || n == 0) {
				res = 0;
			} else if(s1.charAt(m-1) == s2.charAt(n-1)) {
				res = 1 + lcs(s1, s2, m-1, n-1);
			} else {
				res = Math.max(lcs(s1, s2, m-1, n), lcs(s1, s2, m, n-1));
			}
			memo[m][n] = res;
		}
		return memo[m][n];
	}
}
