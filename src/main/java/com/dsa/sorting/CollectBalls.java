package com.dsa.sorting;

import java.util.Scanner;

public class CollectBalls {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        System.out.println(getMinK(n,1,n));
    }

    static long getMinK(long n, long start, long end) {
        if(start==end) {
            return start;
        }
        long k = (start + end )/2;
        if(check(n,k)) {
            return getMinK(n,start,k);
        } else {
            return getMinK(n,k+1,end);
        }
    }

    static boolean check(long n, long k) {
        long sum = 0;
        long curr = n;
        while (curr>0) {
            if(curr<k) {
                sum += curr;
                break;
            }
            sum  += k;
            curr -= k;
            curr -= (curr)/10;
        }
        return 2*sum>=n;
    }
}
