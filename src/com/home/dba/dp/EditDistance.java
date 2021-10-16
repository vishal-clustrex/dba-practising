package com.home.dba.dp;

public class EditDistance {

	public static void main(String[] args) {
		EditDistance editDistance = new EditDistance();
		String s1 = "SATURDAY";
		String s2 = "SUNDAY";
		int output = editDistance.editDistance(s1, s2, s1.length(), s2.length());
		System.out.println("Output : " + output);
	}
	
	private int editDistance(String s1, String s2, int m, int n) {
		if(m==0) return n;
		if(n==0) return m;
		if(s1.charAt(m-1) == s1.charAt(n-1)) {
			return editDistance(s1, s2, m-1, n-1);
		} else {
			return 1 + Math.min(Math.min(editDistance(s1, s2, m-1, n), 
					editDistance(s1, s2, m, n-1)), 
					editDistance(s1, s2, m-1, n-1));
		}
	}
	
}
