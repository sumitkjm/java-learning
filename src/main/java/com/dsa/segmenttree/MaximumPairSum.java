package com.dsa.segmenttree;

import jdk.internal.util.xml.impl.Pair;

import java.util.Scanner;

/**
 *  Maximum Pair Sum
 * Send Feedback
 * You are given a sequence A[1], A[2], ..., A[N], ( 0 ≤ A[i] ≤ 10^8 , 2 ≤ N ≤ 10^5 ). There are two types of operations and they are defined as follows:
 * Update:
 * This will be indicated in the input by a 'U' followed by space and then two integers i and x.
 *
 * U i x, 1 ≤ i ≤ N, and x, 0 ≤ x ≤ 10^8.
 *
 * This operation sets the value of A[i] to x.
 * Query:
 * This will be indicated in the input by a 'Q' followed by a single space and then two integers i and j.
 *
 * Q x y, 1 ≤ x < y ≤ N.
 *
 * You must find i and j such that x ≤ i, j ≤ y and i != j, such that the sum A[i]+A[j] is maximized. Print the sum A[i]+A[j].
 * Input
 *
 * The first line of input consists of an integer N representing the length of the sequence.
 * Next line consists of N space separated integers A[i]. Next line contains an integer Q, Q ≤ 10^5, representing the number of operations. Next Q lines contain the operations.
 *
 * Output
 *
 *  Output the maximum sum mentioned above, in a separate line, for each Query.
 *
 * Input:
 *
 * 5
 * 1 2 3 4 5
 * 6
 * Q 2 4
 * Q 2 5
 * U 1 6
 * Q 1 5
 * U 1 7
 * Q 1 5
 *
 * Output:
 *
 * 7
 * 9
 * 11
 * 12
 */
public class MaximumPairSum {

    static MaxPairs leafMaxPairs = new MaxPairs();

    static  class MaxPairs {
        int max = 0;
        int nMax = 0;
        public int sumOfPairs() {
            return max+nMax;
        }
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] arr = new int[n];
        for (int i=0;i<n;i++) {
            arr[i] = in.nextInt();
        }

        MaxPairs [] tree = new MaxPairs[4*n];

        buildSegmentTree(arr, tree,0,n-1,1);
        int q = in.nextInt();
        for (int j=0;j<q;j++) {
            String op = in.next();
            if(op.equals("U")) {
                int i = in.nextInt();
                int x = in.nextInt();
                updateSegmentTree(arr,tree,0,n-1,1,x,i-1);
            } else {
                int x = in.nextInt();
                int y = in.nextInt();
                MaxPairs maxPairs = searchSegmentTree(arr,tree,0,n-1,x-1,y-1,1);
                System.out.println(maxPairs.sumOfPairs());
            }
        }
    }

    private static MaxPairs searchSegmentTree(int[] arr, MaxPairs[] tree, int start, int end, int searchS, int searchE, int treeIndex) {
        if(searchS==start&&searchE==end) {
            return tree[treeIndex-1];
        }
        if(searchS>end||searchE<start) {
            return null;
        }
        if((searchS>=start &&searchE<end)||(searchS>start &&searchE<=end)) {
            int mid = (start+end)/2;
            return getMaxPairs(searchSegmentTree(arr,tree,start,mid,searchS,searchE>mid?mid:searchE,2*treeIndex)
                    ,searchSegmentTree(arr,tree,mid+1,end,searchS<mid+1?mid+1:searchS,searchE,2*treeIndex+1));
        }
        return new MaxPairs();
    }

    private static void buildSegmentTree(int[] arr, MaxPairs[]tree, int start, int end, int treeNodeIndex) {
        if(start==end) {
            MaxPairs maxPairs = new MaxPairs();
            maxPairs.max = arr[start];
            tree[treeNodeIndex-1] = maxPairs;
            return;
        }
        int mid = (start+end)/2;
        buildSegmentTree(arr,tree,start,mid,2*treeNodeIndex);
        buildSegmentTree(arr,tree,mid+1,end,2*treeNodeIndex+1);
        tree[treeNodeIndex-1] = getMaxPairs(tree[2*treeNodeIndex-1] , tree[2*treeNodeIndex]);
    }

    private static void updateSegmentTree(int[] arr, MaxPairs[] tree, int start, int end, int treeNodeIndex, int updateValue, int index) {
        if(start==end) {
            arr[index] = updateValue;
            MaxPairs maxPairs = new MaxPairs();
            maxPairs.max = updateValue;
            tree[treeNodeIndex-1] = maxPairs;
            return;
        }
        int mid = (start+end)/2;
        if(index>mid) {
            updateSegmentTree(arr, tree,mid+1,end,2*treeNodeIndex+1,updateValue,index);
        } else {
            updateSegmentTree(arr, tree,start,mid,2*treeNodeIndex,updateValue,index);
        }
        tree[treeNodeIndex-1] = getMaxPairs(tree[2*treeNodeIndex-1] , tree[2*treeNodeIndex]);
    }

    public static MaxPairs getMaxPairs(MaxPairs pair1, MaxPairs pair2) {
        if(pair1==null) {
            return pair2;
        }
        if(pair2==null) {
            return pair1;
        }
        MaxPairs maxPairs = new MaxPairs();
        maxPairs.max = getMax(pair1.max,pair2.max);
        if(pair1.max>pair2.max) {
            maxPairs.nMax = getMax(pair2.max,getMax(pair1.nMax,pair2.nMax));
        } else {
            maxPairs.nMax = getMax(pair1.max,getMax(pair1.nMax,pair2.nMax));
        }
        return maxPairs;
    }

    public static int getMax(int a, int b) {
        return a>b?a:b;
    }


}
