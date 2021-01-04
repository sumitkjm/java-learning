package com.bitops;

import java.util.Scanner;

public class TurnOffFirstSetBit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = n;
        int i=0;
        while ((k&1)==0) {
            k = k>>1;
            i++;
        }
        int x = 1<<i;
        System.out.println("first set bit number="+x);
//        System.out.println("unset first set bit="+(~x));
        System.out.println("unset first set bit="+(n^x));
    }

}
