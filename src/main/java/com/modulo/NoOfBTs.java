package com.modulo;

import java.util.Scanner;

public class NoOfBTs {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(balancedBTs(n));
    }

    public static long balancedBTs(long n){

        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        if(n==1 ||n==0) {
            return 1;
        }
        long m = (long)Math.pow(10,9) + 7;
        long x = balancedBTs(n-1);
        long y = balancedBTs(n-2);
        int res1 = (int)(x*x%m);
        int res2 = (int)(2*x*y%m);
        return (res1+ res2)%m;

    }
}
