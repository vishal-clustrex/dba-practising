package com.home.dba.recursive;

public class PalindromePartitioning {

	public static void main(String[] args) {
		PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
		String s = "geek";
		int output = palindromePartitioning.palPartition(s, 0, s.length());
		System.out.println("Output : " + output);
	}
	
	private int palPartition(String s, int i, int j) {
		if(isPalidrome(s, i, j)) {
			return 0;
		}
		int res = Integer.MAX_VALUE;
		for(int k = i; k < j; k++) {
			res = Math.min(res, 1 + palPartition(s, i, k) + palPartition(s, k+1, j));
		}
		return res;
	}
	
	private boolean isPalidrome(String s, int i, int j) {
		StringBuilder subString = new StringBuilder(s.substring(i, j));
		return subString.toString().equals(subString.reverse().toString());
	}
	
}
