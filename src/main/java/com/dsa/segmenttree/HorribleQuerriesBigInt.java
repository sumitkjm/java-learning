package com.dsa.segmenttree;

import java.math.BigInteger;
import java.util.Scanner;

public class HorribleQuerriesBigInt {

    static int m = (int)Math.pow(10,9)+7;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int j=1;j<=t;j++) {
            int n = in.nextInt();
            // all element initially set to zero as per question to so need to read
            int c = in.nextInt();
            BigInteger [] tree = new BigInteger[4*n];
            BigInteger[] lazy = new BigInteger[4*n];
            for (int i=0;i<c;i++) {
                int type = in.nextInt();
                if(type == 0) {
                    // 0 p q v - you have to add v to all numbers in the range of p to q (inclusive), where p and q are two indexes of the array.
                    int p = in.nextInt();
                    int q = in.nextInt();
                    int v = in.nextInt();
                    updateSegmentTree(tree,lazy,0,n-1,1,p-1,q-1,v);
                }
                // 1 p q - output a line containing a single integer which is the sum of all the array elements between p and q (inclusive)
                if(type ==1) {
                    int p = in.nextInt();
                    int q = in.nextInt();
                    System.out.println(searchSegmentTree(tree,lazy,0,n-1,1,p-1,q-1).longValue());
                }
            }
        }

    }

    // update segment tree with increment value by inc
    public static void updateSegmentTree(BigInteger[]tree, BigInteger[] lazy, int low, int high, int treeIndex, int startR, int endR, int inc) {
        if(low>high) {
//            System.out.println("low>high,low="+low+",high="+high);
            return;
        }
        // first check if lazy tree has anything already pending to be added
        if(lazy[treeIndex]!=null) {
            // then increment
            tree[treeIndex].add(lazy[treeIndex]);
            if (low != high) {
                int mid = (low + high) / 2;
                if(lazy[2 * treeIndex]==null) {
                    lazy[2*treeIndex] = BigInteger.valueOf(0);
                }
                if(lazy[2 * treeIndex+1]==null) {
                    lazy[2*treeIndex+1] = BigInteger.valueOf(0);
                }
                lazy[2*treeIndex].add(lazy[treeIndex].multiply(BigInteger.valueOf((mid - low + 1))).divide(BigInteger.valueOf (high - low + 1)));
                lazy[2*treeIndex+1].add(lazy[treeIndex].multiply(BigInteger.valueOf((high - mid))).divide(BigInteger.valueOf (high - low + 1)));
            }
            lazy[treeIndex] = null;
        }
        // no overlap
        if(startR>high || endR<low) {
            return;
        }

        // complete overlap
        if(startR<=low&& high<=endR) {
            tree[treeIndex] = BigInteger.valueOf(0);
            tree[treeIndex].add(BigInteger.valueOf((high-low+1)*inc));
            if(low != high) {
                int mid = (low+high)/2;
                if(lazy[2 * treeIndex]==null) {
                    lazy[2*treeIndex] = BigInteger.valueOf(0);
                }
                if(lazy[2 * treeIndex+1]==null) {
                    lazy[2*treeIndex+1] = BigInteger.valueOf(0);
                }

                lazy[2*treeIndex].add(BigInteger.valueOf((mid-low+1)*inc));
                lazy[2*treeIndex+1].add(BigInteger.valueOf((high-mid)*inc));
            }
            return;
        }
        // partial overlap
        int mid = (low+high)/2;
        updateSegmentTree(tree,lazy,low,mid,2*treeIndex,startR,endR,inc);
        updateSegmentTree(tree,lazy,mid+1,high,2*treeIndex+1,startR,endR,inc);
        BigInteger bigInteger = BigInteger.valueOf(0);
        bigInteger.add(tree[2*treeIndex]);
        bigInteger.add(tree[2*treeIndex+1]);
        tree[treeIndex] =  bigInteger;
    }

    public static BigInteger searchSegmentTree(BigInteger[]tree, BigInteger[] lazy, int low, int high, int treeIndex, int searchS, int searchE) {
        // first check if lazy tree has anything already pending to be added
        // first check if lazy tree has anything already pending to be added
        if(lazy[treeIndex]!=null) {
            // then increment
            tree[treeIndex].add(lazy[treeIndex]);
            if (low != high) {
                int mid = (low + high) / 2;
                if(lazy[2 * treeIndex]==null) {
                    lazy[2*treeIndex] = BigInteger.valueOf(0);
                }
                if(lazy[2 * treeIndex+1]==null) {
                    lazy[2*treeIndex+1] = BigInteger.valueOf(0);
                }
                lazy[2*treeIndex].add(lazy[treeIndex].multiply(BigInteger.valueOf((mid - low + 1))).divide(BigInteger.valueOf (high - low + 1)));
                lazy[2*treeIndex+1].add(lazy[treeIndex].multiply(BigInteger.valueOf((high - mid))).divide(BigInteger.valueOf (high - low + 1)));
            }
            lazy[treeIndex] = null;
        }

        // Match
        if(searchE==high && searchS==low) {
            return tree[treeIndex];
        }

        // if no match
        if(searchS>high||searchE<low) {
            return null;
        }

        // partial overlap
        if((searchS>=low &&searchE<high)||(searchS>low &&searchE<=high)) {
            int mid = (low+high)/2;
            BigInteger sum1 = searchSegmentTree(tree,lazy,low,mid,2*treeIndex,searchS,searchE>mid?mid:searchE);
            BigInteger sum2 = searchSegmentTree(tree,lazy,mid+1,high,2*treeIndex+1,searchS<mid+1?mid+1:searchS,searchE);
            if(sum1==null) {
                return sum2;
            }
            if(sum2==null) {
                return sum1;
            }
            BigInteger bigInteger = BigInteger.valueOf(0);
            return bigInteger.add(sum1).add(sum2);
        }
        return tree[treeIndex];
    }

}
