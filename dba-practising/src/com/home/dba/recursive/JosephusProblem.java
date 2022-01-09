package com.home.dba.recursive;

public class JosephusProblem {

	public static void main(String[] args) {
		int n = 5;
		int k = 3;
		JosephusProblem jp = new JosephusProblem();
		int survived = jp.jos(n, k);
		System.out.println("survived : " + survived);
	}
	
	private int jos(int n, int k) {
		if(n == 1) {
			return 0;
		}
		return (jos(n-1, k) + k) % n;
	}
	
}
