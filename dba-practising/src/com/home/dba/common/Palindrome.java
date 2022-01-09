package com.home.dba.common;

public class Palindrome {

	public static void main(String[] args) {
		Palindrome palindrome = new Palindrome();
		boolean output = palindrome.isPalindrome("abba");
		System.out.println("Output : " + output);
	}
	
	private boolean isPalindrome(String s) {
		String reversedString = reverse(s);
		return reversedString.equals(s);
	}
	
	private String reverse(String s) {
		char[] c = s.toCharArray();
		int low = 0, high = c.length - 1;
		while(low < high) {
			char temp = c[low];
			c[low] = c[high];
			c[high] = temp;
			low++;
			high--;
		}
		return new String(c);
	}
}
