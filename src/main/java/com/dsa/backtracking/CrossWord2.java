package com.dsa.backtracking;

import java.util.Scanner;

public class CrossWord2 {

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
        fillCrossWord(cw, words, 10,0, 0);
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                System.out.print(cw[i][j]);
            }
            System.out.println("");
        }


    }

    static String getNextPossibleCell(char[][] cw, int n, int row, int col) {
        String cellStr = null;
        // get empty position
        for(int i=row;i<n;i++) {
            int j = 0;
            if(i==row) {
                j = col;
            }
            for(;j<n;j++) {
                if(cw[i][j]=='-') {
                    if(i>0 && cw[i-1][j]!='+' && cw[i-1][j]!='-') {
                        cellStr = (i-1)+";"+j;
                    } else if(j>0 && cw[i][j-1]!='+'  && cw[i][j-1]!='-') {
                        cellStr = i+";"+(j-1);
                    } else {
                        cellStr = i +";" +j;
                    }
                    return cellStr;
                }
            }
        }
        return cellStr;
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


    static boolean fillCrossWord(char[][] cw, String [] words, int n, int i, int j) {
        if (i == n-1 && j == n-1) {
            if (words.length == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if(words.length==0) {
                return true;
            }
        }
        String cell = getNextPossibleCell(cw, n, i, j);
        boolean filled = false;
        if (cell != null) {
            String[] celStrArr = cell.split(";");
            int row = Integer.parseInt(celStrArr[0]);
            int col = Integer.parseInt(celStrArr[1]);
            char[] word1 = null;
            char[] word2 = null;
            int r = row;
            int l = col;
            int w = 1;
            if(cw[row][col]!='-') {
                w = 2;
            }
            if(col<n-1) {
                r = row;
                l = col+w;
            } else if(row<n-1){
                r = row+w;
                l = col;
            }
            for (String word : words) {
                filled = false;
                // fill word at row, col
                if (canFillWordInRow(word, cw, row, col)) {
                    word1 = new char[word.length()];
                    for (int k = 0; k < word.length(); k++) {
                        word1[k] = cw[row][col + k];
                        cw[row][col + k] = word.charAt(k);
                    }
                    String[] remainingWords = getRemainingWords(words, word);
                    filled = fillCrossWord(cw, remainingWords, n, r, l);
                    if (!filled) {
                        for (int k = 0; k < word1.length; k++) {
                            cw[row][col + k] = word1[k];
                        }
                    } else {
                        continue;
                    }
                }

                if (canFillWordInCol(word, cw, row, col)) {
                    word2 = new char[word.length()];
                    for (int k = 0; k < word.length(); k++) {
                        word2[k] = cw[row + k][col];
                        cw[row + k][col] = word.charAt(k);
                    }
                    String[] remainingWords = getRemainingWords(words, word);
                    filled = fillCrossWord(cw, remainingWords, n, r, l);
                    if (!filled) {
                        for (int k = 0; k < word2.length; k++) {
                            cw[row+k][col] = word2[k];
                        }
                    } else {
                        return true;
                    }
                }
            }
                    if(!filled) {
                        return fillCrossWord(cw,words,n,r,l);
                    } else {
                        return true;
                    }
        } else {
            return false;
        }
    }

    private static String[] getRemainingWords(String[] words, String word) {
        String[] remainingWords = new String[words.length-1];
        for (int wI = 0, rI = 0; wI < words.length; wI++) {
            if (word.equals(words[wI])) {
                continue;
            }
            remainingWords[rI++] = words[wI];
        }
        return remainingWords;
    }

}
