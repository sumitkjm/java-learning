package com.bitops;

import java.util.Scanner;

public class TurnOffIthBit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int i = scanner.nextInt();
        System.out.println(TurnOffIthBit.turnOffIthBit(n,i));
    }

    public static int turnOffIthBit(int n, int i) {
        //Your code goes here
        return n & ~(1<<i);
    }

}
