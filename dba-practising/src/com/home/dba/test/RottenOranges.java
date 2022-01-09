package com.home.dba.test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Rotten Oranges - 4 Direction Adjacent
 * i/p : [[2,1,1],[1,1,0],[0,1,1]]
 * o/p : 4
 * 
 * i/p : [[2,1,1],[0,1,1],[1,0,1]]
 * o/p : -1
 * @author vishal
 *
 */
public class RottenOranges {

	public static void main(String[] args) {
		RottenOranges rottenOranges = new RottenOranges();
		int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
		//int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};
		//int[][] grid = {{2,0}};
		//int[][] grid = {{1,2}};
		//int[][] grid = {{0,1}};
		//int[][] grid = {{2,0,1,1,1,1,1,1,1,1},
//				{1,0,1,0,0,0,0,0,0,1},
//				{1,0,1,0,1,1,1,1,0,1},
//				{1,0,1,0,1,0,0,1,0,1},
//				{1,0,1,0,1,0,0,1,0,1},
//				{1,0,1,0,1,1,0,1,0,1},
//				{1,0,1,0,0,0,0,1,0,1},
//				{1,0,1,1,1,1,1,1,0,1},
//				{1,0,0,0,0,0,0,0,0,1},
//				{1,1,1,1,1,1,1,1,1,1}};
		int output = rottenOranges.orangesRotting(grid);
		System.out.println("Output : " + output);
	}
	
	private boolean isSafe(int i, int j, int c, int r) {
		return (i >= 0 && i < r && j >= 0 && j < c);
	}
	
	public int orangesRotting(int[][] grid) {
        int c = grid[0].length , r = grid.length;
        int changedCount = 0;
        Queue<Coordinate> queue = new ArrayDeque<Coordinate>();
        
        for(int i=0; i<r; i++) {
        	for(int j=0; j<c; j++) {
        		if(grid[i][j] == 2) {
        			queue.add(new Coordinate(i, j));
        		}
        	}
        }
    	while(!queue.isEmpty()) {
    		int size = queue.size();
    		boolean isChanged = false;
    		for(int i = 0; i < size; i++) {
    			Coordinate current = queue.poll();
        		if(isSafe(current.x-1, current.y , c, r) && grid[current.x-1][current.y] == 1) {
        			queue.add(new Coordinate(current.x-1, current.y));
    				grid[current.x-1][current.y] = 2;
    				isChanged = true;
        		}
        		if(isSafe(current.x, current.y-1 , c, r) && grid[current.x][current.y-1] == 1) {
        			queue.add(new Coordinate(current.x, current.y-1));
    				grid[current.x][current.y-1] = 2;
    				isChanged = true;
        		}
        		if(isSafe(current.x+1, current.y , c, r) && grid[current.x+1][current.y] == 1) {
        			queue.add(new Coordinate(current.x+1, current.y));
    				grid[current.x+1][current.y] = 2;
    				isChanged = true;
        		}
        		if(isSafe(current.x, current.y+1 , c, r) && grid[current.x][current.y+1] == 1) {
        			queue.add(new Coordinate(current.x, current.y+1));
    				grid[current.x][current.y+1] = 2;
    				isChanged = true;
        		}
        	}
    		if(isChanged) changedCount++;
    		else break;
        }   
        
        for(int i=0; i<r; i++) {
        	for(int j=0; j<c; j++) {
        		if(grid[i][j] == 1) {
        			changedCount = -1;
        			break;
        		}
        	}
        }
		return changedCount;
    }
	
	static class Coordinate {
		private int x;
		private int y;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
}
