package com.dsa.numbertheory.numbertheory3;

/**
 * Complexity O(log(n))
 */
public class OptimizedPowerFun {

    static int m = pow(10,9)+7;

    public static int pow(int x, int n) {
        if(x==0) {
            return 0;
        }
        if(n==0) {
            return 1;
        }
        if(n%2==0) {
            int subResult = pow(x,n/2);
            return subResult * subResult;
        } else {
            int subResult = pow(x,n-1);
            return x * subResult;
        }
    }

    static long power(long x, long y, long p)
    {
        long res = 1; // Initialize result
        x = x % p;   // Update x if it is more
        // than or equal to p
        while (y > 0)
        {
            // If y is odd, multiply
            // x with result
            if ((y & 1) > 0)
                res = (res * x) % p;

            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
        return res;
    }


    public static int powWithModulo(int x, int n) {
        if(x==0) {
            return 0;
        }
        if(n==0) {
            return 1;
        }
        long ans;
        if(n%2==0) {
            long subResult = pow(x,n/2);
            ans = (subResult * subResult)%m;
        } else {
            long subResult = pow(x,n-1);
            ans = x%m;
            ans = (ans * subResult)%m;
        }
        return (int)((ans+m)%m);
    }


    public static void main(String[] args) {
        System.out.println(pow(2,30));
        System.out.println(powWithModulo(2,61));
    }
}
