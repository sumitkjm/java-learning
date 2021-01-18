package com.dsa.segmenttree;

import java.util.Scanner;

/**
 *  Maximum Sum In Subarray
 * Send Feedback
 * You are given a sequence A[1], A[2], ..., A[N] . ( |A[i]| ≤ 15007 , 1 ≤ N ≤ 50000 ). A query is defined as follows:
 * Query(x,y) = Max { a[i]+a[i+1]+...+a[j] ; x ≤ i ≤ j ≤ y }.
 * Given M queries, your program must output the results of these queries.
 * Input
 *
 * The first line of the input file contains the integer N.
 * In the second line, N numbers follow.
 * The third line contains the integer M.
 * M lines follow, where line i contains 2 numbers xi and yi.
 *
 * Output
 *
 * Your program should output the results of the M queries, one
 * query per line.
 *
 * Sample Input:
 *
 * 3
 * -1 2 3
 * 1
 * 1 2
 *
 * Sample Output:
 *
 * 2
 */
public class MaximumSumInSubArray {

    static class Node {
        long sum;
        long maxSum;
        long bestPrefixSum;
        long bestSuffixSum;
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = in.nextInt();
        }
        Node[] tree = new Node[4*n];
        buildSegmentTree(arr,tree,0,n-1,1);
        int q = in.nextInt();
        for(int i=0;i<q;i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            System.out.println(searchSegmentTree(arr,tree,0,n-1,x-1,y-1,1).maxSum);
        }
    }

    private static Node searchSegmentTree(int[] arr, Node[] tree, int start, int end, int searchS, int searchE, int treeIndex) {
        if(searchS==start&&searchE==end) {
            return tree[treeIndex-1];
        }
        if(searchS>end||searchE<start) {
            return null;
        }
        if((searchS>=start &&searchE<end)||(searchS>start &&searchE<=end)) {
            int mid = (start+end)/2;
            return getMaximumSumArrayNode(searchSegmentTree(arr,tree,start,mid,searchS,searchE>mid?mid:searchE,2*treeIndex)
                    ,searchSegmentTree(arr,tree,mid+1,end,searchS<mid+1?mid+1:searchS,searchE,2*treeIndex+1));
        }
        return new Node();
    }

    private static void buildSegmentTree(int[] arr, Node[]tree, int start, int end, int treeNodeIndex) {
        if(start==end) {
            Node node = new Node();
            node.sum = arr[start];
            node.maxSum = arr[start];
            node.bestPrefixSum = arr[start];
            node.bestSuffixSum = arr[start];
            tree[treeNodeIndex-1] = node;
            return;
        }
        int mid = (start+end)/2;
        buildSegmentTree(arr,tree,start,mid,2*treeNodeIndex);
        buildSegmentTree(arr,tree,mid+1,end,2*treeNodeIndex+1);
        tree[treeNodeIndex-1] = getMaximumSumArrayNode(tree[2*treeNodeIndex-1] , tree[2*treeNodeIndex]);
    }

    private static Node getMaximumSumArrayNode(Node node1, Node node2) {
        if(node1==null) {
            return node2;
        }
        if(node2==null) {
            return node1;
        }
        Node node = new Node();
        node.maxSum = max(max(max(max(
                node1.maxSum,node2.maxSum),
                node1.sum + node2.bestPrefixSum),
                node2.sum + node1.bestSuffixSum),
                node1.bestSuffixSum + node2.bestPrefixSum);
        node.sum = node1.sum + node2.sum;
        node.bestPrefixSum = max((node1.sum + node2.bestPrefixSum),node1.bestPrefixSum);
        node.bestSuffixSum = max((node2.sum + node1.bestSuffixSum),node2.bestSuffixSum);
        return node;
    }

    private static long max(long a, long b) {
        return a>b?a:b;
    }


}
