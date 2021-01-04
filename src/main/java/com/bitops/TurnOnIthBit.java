package com.bitops;

import java.util.Scanner;

public class TurnOnIthBit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int i = scanner.nextInt();
        System.out.println(TurnOnIthBit.turnOnIthBit(n,i));
    }

    public static int turnOnIthBit(int n, int i) {
        //Your code goes here
        return n | 1<<i;
    }

}
