package com.dsa.backtracking;

public class PlaceNQueens {
    public static void main(String [] args) {
        int [][] board = new int [4][4];
        placeNQueensInRow(4,0,board);
    }


    static void placeNQueensInRow(int n, int row, int[][]board) {
        if(row>(n-1)) {
            //print
            for(int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    System.out.print(board[i][j]+" ");
//                    board[i][j] = 0;
                }
            }
            System.out.println("");
            // placed all queens im rows so backtracking to find more configurations
            return;
        } else {
            boolean canPlace = true;
            for(int i=0;i<n;i++) {
                canPlace = true;
                if(row==0) {
                    board[row][i] = 1;
                    placeNQueensInRow(n, row+1, board);
                    board[row][i] = 0;
                } else {
                    for(int j=row,k=1;j>0;j--,k++) {
                        if(board[j-1][i]==1) {
                            canPlace = false;
                            break;
                        }
                        // upper left diagonal
                        if((i-k)>=0 && board[j-1][i-k]==1) {
                            canPlace = false;
                            break;
                        }
                        // upper right diagonal
                        if((i+k)<n && board[j-1][i+k]==1) {
                            canPlace = false;
                            break;
                        }
                    }
                    if(canPlace) {
                        board[row][i] = 1;
                        placeNQueensInRow(n,row+1,board);
                        board[row][i] = 0;
                    }
                }
            }
            // backtrack if Queeen is not been able to be placed
            if(!canPlace) {
                return;
            }
        }
        return;
    }

}
