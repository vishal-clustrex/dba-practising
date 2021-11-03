package com.home.patterns;

public class PyramidPattern4 {

	public static void main(String[] args) {
		PyramidPattern4 pattern4 = new PyramidPattern4();
		int rows = Util.getRows();
		pattern4.printPyramid(rows);
		System.out.println("Print from Rows Pyramid");
		pattern4.printPyramidReversed(rows);
		System.out.println("Inverted Pyramid");
		pattern4.printPyramidInverted(rows);
	}
	
	private void printPyramid(int rows) {
		for (int i = 1; i <= rows; i++) {
			Util.printString("  ", rows - i);
			for(int j = 1; j<=i; j++) {
				System.out.print(j + " ");
			}
			for(int j = i-1; j>=1; j--) {
				System.out.print(j + " ");
			}
			//Util.printString(i + " ", i);
			System.out.println("");
		}
	}
	
	private void printPyramidReversed(int rows) {
		for (int i = rows; i >= 1; i--) {
			Util.printString("  ", i);
			for(int j = i; j<=rows; j++) {
				System.out.print(j + " ");
			}
			for(int j = rows-1; j>=i; j--) {
				System.out.print(j + " ");
			}
			//Util.printString(i + " ", i);
			System.out.println("");
		}
	}
	
	private void printPyramidInverted(int rows) {
		for (int i = rows; i >= 1; i--) {
			Util.printString("  ", rows - i);
			for(int j = 1; j<=i; j++) {
				System.out.print(j + " ");
			}
			for(int j = i-1; j>=1; j--) {
				System.out.print(j + " ");
			}
			//Util.printString(i + " ", i);
			System.out.println("");
		}
	}
	
}
