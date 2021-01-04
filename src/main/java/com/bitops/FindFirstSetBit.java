package com.bitops;

import java.util.Scanner;

public class FindFirstSetBit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(returnFirstSetBit(n));
    }

    public static int returnFirstSetBit(int n) {
        int i=0;
        while ((n&1)==0) {
            n = n>>1;
            i++;
        }
        return 1<<i;
    }



}
