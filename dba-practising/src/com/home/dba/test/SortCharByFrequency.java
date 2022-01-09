package com.home.dba.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * i/p : tree
 * o/p : eert
 * 
 * i/p : aabbbbccc
 * o/p : bbbbcccaa
 * 
 * @author vishal
 *
 */
public class SortCharByFrequency {

	public static void main(String[] args) {
		String s = "trreeee";
		SortCharByFrequency sortCharByFrequency = new SortCharByFrequency();
		String output = sortCharByFrequency.solution(s);
		System.out.println("Output : " + output);
	}
	
	private String solution(String s) {
		String sorted = "";
		Map<Character, Integer> charCount = new HashMap<Character, Integer>();
		for (char d : s.toCharArray()) {
			charCount.put(d, charCount.getOrDefault(d, 0) + 1);
		}
		Map<Character, Integer> charCountSorted = charCount.entrySet().stream()
				.sorted((i1, i2) -> (i2.getValue().compareTo(i1.getValue())))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		for (Map.Entry<Character, Integer> entry : charCountSorted.entrySet()) {
			sorted += new String(new char[entry.getValue()]).replace("\0", entry.getKey()+"");
		}
		System.out.println("Sorted String : " + sorted);
		return sorted;
	}
	
}
