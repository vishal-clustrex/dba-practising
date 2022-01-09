package com.home.dba.dp;

import java.util.Scanner;

public class Tabulation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter n : ");
		int n = sc.nextInt();
		sc.close();
		Tabulation tab = new Tabulation();
		tab.fib(n);
	}
	
	private void fib(int n) {
		int f[] = new int[n+1];
		f[0] = 0;
		f[1] = 1;
		System.out.println(f[0] + "\n" + f[1]);
		for(int i=2; i< n; i++) {
			f[i] = f[i-1] + f[i-2];
			System.out.println(f[i]);
		}
		
	}
	
}
