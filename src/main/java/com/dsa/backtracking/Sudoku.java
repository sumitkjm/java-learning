package com.dsa.backtracking;

import java.util.Scanner;

public class Sudoku {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int board[][] = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = s.nextInt();
            }
        }
        System.out.println(Sudoku.sudokuSolver(board));
//        printSudoku(board);
    }

    static void printSudoku(int[][] board) {
        for(int i=0;i<board.length;i++) {
            for (int j=0;j<board.length;j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println("\n");
        }
    }

    static int[] findNextEmtpyLoc(int[][] board) {
        int[] loc = new int[2];
        loc[0] = -1;
        loc[1] = -1;
        for(int i=0;i<board.length;i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    loc[0] = i;
                    loc[1] = j;
                    return loc;
                }
            }
        }
        return loc;
    }

    public static boolean sudokuSolver(int board[][]) {
        boolean isSudoku = false;
        /* Your class should be named Solution.
         * Don't write main() function.
         * Don't read input, it is passed as function argument.
         * Don't print output and return output as specified in the question.
         */
        int[] loc = findNextEmtpyLoc(board);
        if (loc[0] == -1) {
            return true;
        }
        int i = loc[0];
        int j = loc[1];
        //try to fill it
        for (int k = 1; k <= 9; k++) {
            if (notInCol(board, j, k) && notInRow(board, i, k) && notInCube(board, i, j, k)) {
                board[i][j] = k;
                if (sudokuSolver(board)) {
                    return true;
                } else {
                    board[i][j] = 0;
                }
            }
        }
        return isSudoku;
    }

    static boolean notInRow(int[][]board, int row, int num) {
        for(int j=0;j<9;j++) {
            if(board[row][j]==num) {
                return false;
            }
        }
        return true;
    }

    static boolean notInCol(int[][]board, int col, int num) {
        for(int i=0;i<9;i++) {
            if(board[i][col]==num) {
                return false;
            }
        }
        return true;
    }

    static boolean notInCube(int[][]board, int row, int col, int num) {
        int wi = row/3;
        int wj = col/3;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(board[i+3*wi][j+3*wj]==num){
                    return false;
                }
            }
        }
        return true;
    }


}
