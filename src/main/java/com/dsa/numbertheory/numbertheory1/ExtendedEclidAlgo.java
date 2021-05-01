package com.dsa.numbertheory.numbertheory1;

/**
 * if ax+by = gcd(a,b) then there will be surely 2 integers corresponding to x and y
 * where a,b are integers >0
 */
public class ExtendedEclidAlgo {
    public static class Tripplet {
        public long x;
        public long y;
        public long gcd;
    }
    public static Tripplet extendedEclid(long a, long b) {
        if(a<b) {
            return extendedEclid(b,a);
        }
        if(b==0) {
            Tripplet ans = new Tripplet();
            ans.x = 1;
            ans.y = 0;
            ans.gcd = a;
            return ans;
        }
        Tripplet smallAns = extendedEclid(b, a%b);
        Tripplet ans = new Tripplet();
        ans.x = smallAns.y;
        ans.y = smallAns.x - ((a/b)* smallAns.y);
        ans.gcd = smallAns.gcd;
        return ans;
    }

    public static long multiplicateInverse(long a, long m) {
        Tripplet ans = extendedEclid(a, m);
        return (ans.x%m+m)%m;
    }

    public static void main(String[] args) {
        Tripplet ans = extendedEclid(16,10);
        System.out.println("x="+ans.x+" y="+ans.y+" gcd="+ans.gcd);
        // (a * b) %m = 1
        // A = 1%m
        // x = -by/a
        System.out.println("Multiplicate modulo inverse="+multiplicateInverse(5,17));
    }
}
