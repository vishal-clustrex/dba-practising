package com.home.dba.dp;

public class LISBinarySearch {

	public static void main(String[] args) {
		LISBinarySearch lcsBinarySearch = new LISBinarySearch();
		int n[] = new int[] {3,4,2,8,10,1};
		int output = lcsBinarySearch.lis(n, n.length);
		System.out.println("Output : " + output);
	}
	
	private int lis(int[] arr, int n) {
		int[] tail = new int[n];
		tail[0] = arr[0];
		int len = 1;
		
		for(int i=1; i<n; i++) {
			if(arr[i] > tail[len - 1]) {
				tail[len] = arr[i] ;
				len++;
			} else {
				int c = ceilIndex(tail, 0, len-1, arr[i]);
				tail[c] = arr[i];
			}
		}
		return len;
	}
	
	private int ceilIndex(int[] tail, int start, int end, int x) {
		while(end > start) {
			int m = (start + (end - 1)) / 2;
			if(tail[m] >= x) {
				end = m;
			} else {
				end = m + 1;
			}
		}
		return end;
	}
	
}
