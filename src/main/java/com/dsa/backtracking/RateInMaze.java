package com.dsa.backtracking;

public class RateInMaze {

    public static void main(String [] args) {
        int n = 3;
        int maze[][] = new int[][]{{1,1,1},{1,1,1},{1,0,1}};
        int sol[][] = new int [n][n];
        rateInAMaze(maze, sol,n, 0, 0);

    }

    static void rateInAMaze(int maze[][], int sol[][],int n,int r, int c) {
        if(r==n-1 && c==n-1) {
            if(maze[r][c]==1) {
                sol[r][c] = 1;
                for(int i=0;i<n;i++) {
                    for(int j=0;j<n;j++) {
                        System.out.print(sol[i][j]+" ");
                    }
                }
                sol[r][c] = 0;
                System.out.println("");
            }
        } else {
            if(maze[r][c]==0) {
                // backtrack
                return;
            } else {
                sol[r][c] = 1;
                // traverse right
                if(c<(n-1) && sol[r][c+1]==0) {
                    rateInAMaze(maze, sol,n,r,c+1);
                }

                // traverse left
                if(c>0 && sol[r][c-1]==0) {
                    rateInAMaze(maze, sol, n, r, c-1);
                }

                // traverse down
                if(r<(n-1) && sol[r+1][c]==0) {
                    rateInAMaze(maze, sol, n, r+1, c);
                }
                sol[r][c] = 0;
            }
        }
    }


}
