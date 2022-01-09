package com.home.dba.dp;

import java.util.Arrays;
import java.util.Scanner;

public class Memoization {

	int[] memo;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter n : ");
		int n = sc.nextInt();
		sc.close();
		Memoization memoization = new Memoization();
		memoization.init(n);
		memoization.fib(n);
		memoization.display(n);
	}
	
	private void init(int n) {
		memo = new int[n+1];
		Arrays.fill(memo, -1);
	}
	
	private int fib(int n) {
		if(memo[n] == -1) {
			int res;
			if( n == 0 || n == 1) {
				res = n;
			} else {
				res = fib(n-1) + fib(n-2);
			}
			memo[n] = res;
		}
		return memo[n];
	}
	
	private void display(int n) {
		for(int i = 0; i < n; i++) {
			System.out.println(memo[i]);
		}
	}
}
