package com.dsa.backtracking;

import java.util.Scanner;

public class CrossWord {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        in.nextBigInteger();

        char [][] cw = new char[10][10];
        for(int i=0;i<10;i++) {
            String line = in.nextLine();
            for(int j=0;j<10;j++) {
                cw[i][j] = line.charAt(j);
            }

        }
        String[] words = in.nextLine().split(";");
        fillCrossWord2(cw, words, 0,10);

    }

    static void printCrossword(char[][] cw) {
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                System.out.print(cw[i][j]);
            }
            System.out.println("");
        }

    }

    static boolean fillCrossWord2(char[][] cw, String[] words, int wIndex, int n) {
        if(wIndex==words.length) {
            printCrossword(cw);
            return true;
        }
        boolean isFilled = false;
        for(int i=0;i<n;i++) {
            if(isFilled) {
                break;
            }
            for(int j=0;j<n;j++) {
                if(cw[i][j]=='-'||cw[i][j]==words[wIndex].charAt(0)) {
                    if(canFillWordInRow(words[wIndex],cw,i,j)) {
                        boolean[] revertArrayRow = fillWordInRow(words[wIndex],cw,i,j);
                        if(fillCrossWord2(cw,words,wIndex+1,n)) {
                            isFilled = true;
                            break;
                        } else {
                            revertFillinginRow(cw,revertArrayRow,i,j);
                        }
                    }
                    if(!isFilled && canFillWordInCol(words[wIndex],cw,i,j)) {
                        boolean[] revertArrayCol = fillWordInCol(words[wIndex],cw,i,j);
                        if(fillCrossWord2(cw,words,wIndex+1,n)) {
                            isFilled = true;
                            break;
                        } else {
                            revertFillinginCol(cw,revertArrayCol,i,j);
                        }
                    }
                }
            }
        }
        return isFilled;
    }


    static boolean canFillWordInRow(String word, char[][]cw,int row, int col) {
        boolean canFill = true;
        if(col+word.length()>cw.length) {
            return false;
        }
        for(int i=0;i<word.length();i++) {
            if(i>0 && cw[row][col+i]!='-' && cw[row][col+i]!=word.charAt(i)) {
                canFill = false;
                break;
            }
        }
        return canFill;
    }

    static boolean[] fillWordInRow(String word, char[][]cw, int row, int col) {
        boolean [] revertStatus = new boolean[word.length()];
        for(int i=0;i<word.length();i++) {
            if(cw[row][col+i]!='-') {
                revertStatus[i] = false;
            } else {
                revertStatus[i] = true;
            }
            cw[row][col+i] = word.charAt(i);
        }
        return revertStatus;
    }

    static boolean[] fillWordInCol(String word, char[][]cw, int row, int col) {
        boolean [] revertStatus = new boolean[word.length()];
        for(int i=0;i<word.length();i++) {
            if(cw[row+i][col]!='-') {
                revertStatus[i] = false;
            } else {
                revertStatus[i] = true;
            }
            cw[row+i][col] = word.charAt(i);
        }
        return revertStatus;
    }


    static boolean canFillWordInCol(String word, char[][]cw,int row, int col) {
        boolean canFill = true;
        if(row+word.length()>cw.length) {
            return false;
        }
        for(int i=0;i<word.length();i++) {
            if(i>0 && cw[row+i][col]!='-' && cw[row+i][col]!=word.charAt(i)) {
                canFill = false;
                break;
            }
        }
        return canFill;
    }

    static void revertFillinginRow(char[][]cw, boolean[]revertStatus, int row, int col) {
        for(int i=0;i<revertStatus.length;i++) {
            if(revertStatus[i]) {
                cw[row][col+i] = '-';
            }
        }
    }

    static void revertFillinginCol(char[][]cw, boolean[]revertStatus, int row, int col) {
        for(int i=0;i<revertStatus.length;i++) {
            if(revertStatus[i]) {
                cw[row+i][col] = '-';
            }
        }
    }

}
