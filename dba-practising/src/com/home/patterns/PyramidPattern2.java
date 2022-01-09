package com.home.patterns;

public class PyramidPattern2 {

	public static void main(String[] args) {
		PyramidPattern2 pattern2 = new PyramidPattern2();
		int rows = Util.getRows();
		pattern2.printPyramid(rows);
	}
	
	private void printPyramid(int rows) {
		for (int i = 1; i <= rows; i++) {
			Util.printString(" ", rows - i);
			for(int j=1; j<=i; j++) {
				System.out.print(j + " ");
			}
			System.out.println("");
		}
	}
	
}
