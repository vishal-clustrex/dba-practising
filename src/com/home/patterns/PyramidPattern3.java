package com.home.patterns;

public class PyramidPattern3 {

	public static void main(String[] args) {
		PyramidPattern3 pattern3 = new PyramidPattern3();
		int rows = Util.getRows();
		pattern3.printPyramid(rows);
	}
	
	private void printPyramid(int rows) {
		for (int i = 1; i <= rows; i++) {
			Util.printString(" ", rows - i);
			Util.printString("* ", i);
			System.out.println("");
		}
	}
	
}
