package com.home.dba.recursive;

public class Permutations {

	public static void main(String[] args) {
		Permutations permutations = new Permutations();
		permutations.permute("ABCD", 0);
	}
	
	private String swap(String s, int i, int j) {
		StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(i, s.charAt(j));
        sb.setCharAt(j, s.charAt(i));
        return sb.toString();
	}
	
	private void permute(String s, int i) {
		if(i == s.length() - 1) {
			System.out.println(s + " ");
			return;
		}
		for(int j=i; j<s.length(); j++) {
			s = swap(s, i, j);
			permute(s, i+1);
			s = swap(s, i, j);
		}
	}
	
}
