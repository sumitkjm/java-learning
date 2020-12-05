package com.dsa.backtracking;

import java.util.Scanner;

public class CrossWord {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char [][] cw = new char[10][10];
        for(int i=0;i<10;i++) {
            String line = in.nextLine();
//            System.out.println("");
            for(int j=0;j<10;j++) {
                cw[i][j] = line.charAt(j);
//                System.out.print(cw[i][j]);
            }

        }
        String[] words = in.nextLine().split(";");
            fillCrossWord(cw, words, 0,10, 0, 0);

        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                System.out.print(cw[i][j]);
            }
            System.out.println("");
        }

    }

    static String getNextEmptyCell(char[][] cw, String[] words, int wIndex, int n, int row, int col) {
        String cellStr = null;
        // get empty position
        for(int i=row;i<n;i++) {
            for(int j=col;j<n;j++) {
                if(cw[i][j]=='-'||cw[i][j]==words[wIndex].charAt(0)) {
                    cellStr = i+";"+j;
                }
            }
        }
        return cellStr;
    }

    static void fillCrossWord(char[][] cw, String[] words, int wIndex, int n, int row, int col) {
        if (wIndex == words.length) {
            return;

        }
            String cell = getNextEmptyCell(cw, words, wIndex, n, row, col);
            if(cell!=null) {
                String [] celStrArr = cell.split(";");
                int i = Integer.parseInt(celStrArr[0]);
                int j = Integer.parseInt(celStrArr[1]);
                row = i;
                col = j;
                if(fillWordInRow(cw,words[wIndex],i,j) || fillWordInCol(cw,words[wIndex],i,j)) {
                    fillCrossWord(cw,words,wIndex+1, n, row,col);
                } else {
                    fillCrossWord(cw,words,wIndex, n, row,col);
                }
            }
        }

    static boolean fillWordInCol(char[][] cw, String word, int i, int j) {
        int lastPos = i+word.length();
        int firstPos = i;
        boolean unableToFill = false;
        int k = 0;
        while (i<lastPos&& i<cw.length) {
            if(cw[i][j]=='-'||cw[i][j]==word.charAt(k)) {
                cw[i][j] = word.charAt(k++);
                i++;
            } else {
                unableToFill = true;
                break;
            }
        }
        if(i>=word.length()) {
            unableToFill = true;
        }
        while (unableToFill && firstPos<i) {
            cw[firstPos++][j] = '+';
        }
        System.out.println("filling word in col"+word+unableToFill);
        return !unableToFill;
    }

    static boolean fillWordInRow(char[][] cw, String word, int i, int j) {
        int lastPos = j+word.length();
        int firstPos = j;
        boolean unableToFill = false;
        int k = 0;
        while (j<lastPos&& j<cw.length) {
            if(cw[i][j]=='-'||cw[i][j]==word.charAt(k)) {
                cw[i][j] = word.charAt(k++);
                j++;
            } else {
                unableToFill = true;
                break;
            }
        }
        if(j>=word.length()) {
            unableToFill = true;
        }

        while (unableToFill && firstPos<j) {
            cw[i][firstPos++] = '+';
        }
        System.out.println("filling word in row"+word+unableToFill);
        return !unableToFill;
    }

}
