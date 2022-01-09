package com.home.dba.recursive;

public class MatrixChainMultiplication {

	public static void main(String[] args) {
		MatrixChainMultiplication matrixChainMultiplication = new MatrixChainMultiplication();
		int[] arr = new int[] {20, 10, 30, 40};
		int output = matrixChainMultiplication.mcm(arr, 0, arr.length - 1);
		System.out.println("Output : " + output);
	}
	
	private int mcm(int[] arr, int i, int j) {
		if(i+1 == j) {
			return 0;
		}
		int res = Integer.MAX_VALUE;
		
		for(int k = i+1; k < j; k++) {
			res = Math.min(res, mcm(arr, i, k) + mcm(arr, k, j) + (arr[i] * arr[k] * arr[j]));
		}
		return res;
	}
	
}
