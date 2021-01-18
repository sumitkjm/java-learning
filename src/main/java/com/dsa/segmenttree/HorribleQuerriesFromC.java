package com.dsa.segmenttree;

import java.util.Scanner;

/**
 *  Horrible Queries
 * Send Feedback
 * World is getting more evil and it's getting tougher to get into the Evil League of Evil. Since the legendary Bad Horse has retired, now you have to correctly answer the evil questions of Dr. Horrible, who has a PhD in horribleness (but not in Computer Science). You are given an array of N elements, which are initially all 0. After that you will be given C commands. They are -
 *
 * 0 p q v - you have to add v to all numbers in the range of p to q (inclusive), where p and q are two indexes of the array.
 *
 * 1 p q - output a line containing a single integer which is the sum of all the array elements between p and q (inclusive)
 *
 * Input
 *
 * In the first line you'll be given T, number of test cases.
 *
 * Each test case will start with N (N <= 100 000) and C (C <= 100 000). After that you'll be given C commands in the format as mentioned above. 1 <= p, q <= N and 1 <= v <= 10^7.
 *
 * Output
 *
 * Print the answers of the queries.
 *
 * Input:
 *
 * 1
 * 8 6
 * 0 2 4 26
 * 0 4 8 80
 * 0 4 5 20
 * 1 8 8
 * 0 5 7 14
 * 1 4 8
 *
 * Output:
 *
 * 80
 * 508
 */
public class HorribleQuerriesFromC {

    static int m = (int)Math.pow(10,9)+7;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int j=1;j<=t;j++) {
            int n = in.nextInt();
            // all element initially set to zero as per question to so need to read
            int c = in.nextInt();
            long [] tree = new long[4*n];
            long[] lazy = new long[4*n];
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
                    System.out.println(searchSegmentTree(tree,lazy,0,n-1,1,p-1,q-1));
                }
            }
        }

    }

    // update segment tree with increment value by inc
    public static void updateSegmentTree(long[]tree, long[] lazy, int low, int high, int treeIndex, int startR, int endR, int inc) {
        if(low>high) {
//            System.out.println("low>high,low="+low+",high="+high);
            return;
        }
        // first check if lazy tree has anything already pending to be added
        if(lazy[treeIndex]!=0) {
            // then increment
            tree[treeIndex] += (high-low+1)*lazy[treeIndex];
            if (low != high) {
                lazy[2 * treeIndex] += lazy[treeIndex];
                lazy[2 * treeIndex + 1] +=lazy[treeIndex];
            }
            lazy[treeIndex] = 0;
        }
        // no overlap
        if(startR>high || endR<low) {
            return;
        }

        // complete overlap
            if(startR<=low&& high<=endR) {
                tree[treeIndex] +=(high-low+1) * inc;
                if(low != high) {
                    lazy[2*treeIndex] +=inc;
                    lazy[2*treeIndex+1] +=inc;
                }
                return;
            }
        // partial overlap
        int mid = (low+high)/2;
        updateSegmentTree(tree,lazy,low,mid,2*treeIndex,startR,endR,inc);
        updateSegmentTree(tree,lazy,mid+1,high,2*treeIndex+1,startR,endR,inc);
        tree[treeIndex] = tree[2*treeIndex] + tree[2*treeIndex+1];
    }

    public static long searchSegmentTree(long[]tree, long[] lazy, int low, int high, int treeIndex, int searchS, int searchE) {
        // first check if lazy tree has anything already pending to be added
        // first check if lazy tree has anything already pending to be added
        if(lazy[treeIndex]!=0) {
            // then increment
            tree[treeIndex] += (high-low+1)*lazy[treeIndex];
            if (low != high) {
                lazy[2 * treeIndex] += lazy[treeIndex];
                lazy[2 * treeIndex + 1] +=lazy[treeIndex];
            }
            lazy[treeIndex] = 0;
        }

        // Match
        if(searchE==high && searchS==low) {
            return tree[treeIndex];
        }

        // if no match
        if(searchS>high||searchE<low) {
            return 0;
        }

        // partial overlap
        if((searchS>=low &&searchE<high)||(searchS>low &&searchE<=high)) {
            int mid = (low+high)/2;
            long sum1 = searchSegmentTree(tree,lazy,low,mid,2*treeIndex,searchS,searchE>mid?mid:searchE);
            long sum2 = searchSegmentTree(tree,lazy,mid+1,high,2*treeIndex+1,searchS<mid+1?mid+1:searchS,searchE);
            return sum1+sum2;
        }
        return tree[treeIndex];
    }

}
