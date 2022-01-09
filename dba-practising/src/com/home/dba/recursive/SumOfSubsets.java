package com.home.dba.recursive;

public class SumOfSubsets {

	public static void main(String[] args) {
		int[] set = new int[] {1, 2, 3};
		int sum = 3;
		SumOfSubsets sos = new SumOfSubsets();
		System.out.println("sos o/p : " + sos.sumOfSubsets(set, set.length, sum));
	}
	
	private int sumOfSubsets(int[] set, int n, int sum) {
		if(n == 0) { 
			return (sum == 0) ? 1 : 0;
		}
		return sumOfSubsets(set, n-1, sum) + sumOfSubsets(set, n-1, sum - set[n-1]);
	}
}
