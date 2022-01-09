package com.home.patterns;

public class PyramidPattern1 {

	public static void main(String[] args) {
		PyramidPattern1 pattern1 = new PyramidPattern1();
		int rows = Util.getRows();
		pattern1.printPyramid(rows);
	}
	
	private void printPyramid(int rows) {
		for (int i = 1; i <= rows; i++) {
			Util.printString(" ", rows - i);
			Util.printString(i + Util.getSpacingByValue(i, rows), i);
			System.out.println("");
		}
	}

}
