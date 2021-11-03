package com.home.patterns;

import java.util.Scanner;

public class Util {

	public static int getRows() {
		try(Scanner s = new Scanner(System.in)) {
			System.out.print("Enter number of rows : ");
			int rows = s.nextInt();
			s.close();
			return rows;
		} catch (Exception e) {
			throw new IllegalArgumentException("Problem in getting the input : " + e.getMessage());
		}
	}
	
	public static void printString(String s, int numberOfRows) {
		for(int i=0; i<numberOfRows; i++) {
			System.out.print(s);
		}
	}
	
	public static String getSpacingByValue(int i, int rows) {
		if(rows < 10) {
			return " ";
		} else if(rows >= 10 && i < 10) {
			return "  ";
		}
		return " ";
	}
	
}
