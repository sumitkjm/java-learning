package com.dsa.numbertheory.numbertheory2;

/**
 * phi(a*b) = phi(a)*phi(b) where a and b are distinct co-primes of n
 * a*b = n
 */
public class EulerTotientFun {



    public static void main(String[] args) {
        int n = 10;
        int arr[] = new int[n+1];
        for (int i=1;i<=n;i++) {
            arr[i] = i;
        }
        for (int i=2;i<=n;i++) {
            if(arr[i]==i) {
                arr[i] = i - 1;
                for (int j = 2 * i; j <= n; j += i) {
                    arr[j] = (arr[j] * (i - 1)) / i;
                }
            }
        }
        for (int i=1;i<=n;i++) {
            System.out.println(i+"="+arr[i]);
        }
    }
}
