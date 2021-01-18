package com.dsa.segmenttree;

import java.util.Scanner;

/**
 *  Minimum In SubArray
 * Send Feedback
 * Range Minimum Query
 * Given an array A of size N, there are two types of queries on this array.
 * 1) q l r: In this query you need to print the minimum in the sub-array A[l:r].
 * 2) u x y: In this query you need to update A[x]=y.
 * Input:
 *
 * First line of the test case contains two integers, N and Q, size of array A and number of queries.
 * Second line contains N space separated integers, elements of A.
 * Next Q lines contain one of the two queries.
 *
 * Output:
 *
 * For each type 1 query, print the minimum element in the sub-array A[l:r].
 *
 * Contraints:
 *
 * 1≤N,Q,y≤10^5
 * 1≤l,r,x≤N
 *
 * Sample Input :
 *
 * 5 5
 * 1 5 2 4 3
 * q 1 5
 * q 1 3
 * q 3 5
 * u 3 6
 * q 1 5
 *
 * Sample Output :
 *
 * 1
 * 1
 * 2
 * 1
 */
public class MinimumInArray {

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] arr = new int[n];
        for (int i=0;i<n;i++){
            arr[i] = in.nextInt();
        }
        // it's getting array out of bound exception (2*n+1) with bigger size of array
        // with below size and program worked fine with below commented one:
        //        int [] tree = new int[2*n+n/2+n/4];
        int [] tree = new int[2*n+1];

        buildSegmentTree(arr, tree,0,n-1,1);
        for (int i=0;i<q;i++) {
            String op = in.next();
            int l = in.nextInt();
            int r = in.nextInt();
            if(op.equals("q")) {
                System.out.println(searchSegmentTree(arr,tree,0,n-1,l-1,r-1,1));
            } else {
                updateSegmentTree(arr,tree,0,n-1,1,r,l-1);
            }
        }

    }

    private static int searchSegmentTree(int[] arr, int[] tree, int start, int end, int searchS, int searchE, int treeIndex) {
        if(searchS==start&&searchE==end) {
            return tree[treeIndex-1];
        }
        if(searchS>end||searchE<start) {
            return Integer.MAX_VALUE;
        }
        if((searchS>=start &&searchE<end)||(searchS>start &&searchE<=end)) {
            int mid = (start+end)/2;
            return min(searchSegmentTree(arr,tree,start,mid,searchS,searchE>mid?mid:searchE,2*treeIndex),
                    searchSegmentTree(arr,tree,mid+1,end,searchS<mid+1?mid+1:searchS,searchE,2*treeIndex+1));
        }
        return 0;
    }

    private static void buildSegmentTree(int[] arr, int[]tree, int start, int end, int treeNodeIndex) {
        if(start==end) {
            tree[treeNodeIndex-1] = arr[start];
            return;
        }
        int mid = (start+end)/2;
        buildSegmentTree(arr,tree,start,mid,2*treeNodeIndex);
        buildSegmentTree(arr,tree,mid+1,end,2*treeNodeIndex+1);
        tree[treeNodeIndex-1] = min(tree[2*treeNodeIndex-1] , tree[2*treeNodeIndex]);
    }

    private static void updateSegmentTree(int[] arr, int[] tree, int start, int end, int treeNodeIndex, int updateValue, int index) {
        if(start==end) {
            arr[index] = updateValue;
            tree[treeNodeIndex-1] = updateValue;
            return;
        }
        int mid = (start+end)/2;
        if(index>mid) {
            updateSegmentTree(arr, tree,mid+1,end,2*treeNodeIndex+1,updateValue,index);
        } else {
            updateSegmentTree(arr, tree,start,mid,2*treeNodeIndex,updateValue,index);
        }
        tree[treeNodeIndex-1] = min(tree[2*treeNodeIndex-1] , tree[2*treeNodeIndex]);
    }

    public static int min(int a , int b) {
        return a>b?b:a;
    }


}
