package com.dsa.segmenttree;

import java.util.Scanner;

/**
 *  2 vs 3
 * Send Feedback
 * The fight for the best number in the globe is going to finally come to an end.The top two contenders for the best number are number 2 and number 3.It's the final the entire world was waiting for. Expectorates from all across the globe came to witness the breath taking finals.
 * The finals began in an astonishing way.A common problem was set for both of them which included both these number.The problem goes like this.
 * Given a binary string (that is a string consisting of only 0 and 1). They were supposed to perform two types of query on the string.
 *
 * Type 0: Given two indices l and r.Print the value of the binary string from l to r modulo 3.
 *
 * Type 1: Given an index l flip the value of that index if and only if the value at that index is 0.
 *
 * The problem proved to be a really tough one for both of them.Hours passed by but neither of them could solve the problem.So both of them wants you to solve this problem and then you get the right to choose the best number in the globe.
 * Input:
 *
 * The first line contains N denoting the length of the binary string .
 * The second line contains the N length binary string.Third line contains the integer Q indicating the number of queries to perform.
 * This is followed up by Q lines where each line contains a query.
 *
 * Output:
 *
 * For each query of Type 0 print the value modulo 3.
 *
 * Constraints:
 *
 * 1<= N <=10^5
 *
 * 1<= Q <= 10^5
 *
 * 0 <= l <= r < N
 *
 * Sample Input
 *
 * 5
 * 10010
 * 6
 * 0 2 4
 * 0 2 3
 * 1 1
 * 0 0 4
 * 1 1
 * 0 0 3
 *
 * Sample Output
 *
 * 2
 * 1
 * 2
 * 1
 */
public class TwoVsThree {

    private static int[] twoPowerResult = null;
    private static int m = (int)Math.pow(10,9)+7;

    public static void main(String []args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] arr = new int[n];
        String str = in.next();
        for (int i=0;i<n;i++){
            arr[i] = Integer.valueOf(String.valueOf(str.charAt(i)));
        }
        twoPowerResult = new int[n];
        twoPowerResult[0] = 1%3;
        for (int i=1;i<n;i++) {
            twoPowerResult[i] = (twoPowerResult[i-1]%3 * 2%3)%3;
        }
        int q = in.nextInt();
        int []tree = new int[4*n];
        buildSegmentTree(arr,tree,0,n-1,1);
        for (int i=0;i<q;i++) {
            int type = in.nextInt();
            if(type==0) {
                int x = in.nextInt();
                int y = in.nextInt();
                System.out.println(searchSegmentTree(tree,0,n-1,x,y,1));
            }
            if(type==1) {
                int index = in.nextInt();
                if(arr[index]==0) {
                    // flip to 1
                    updateSegmentTree(arr,tree,0,n-1,1,1,index);
                }
            }
        }

    }

    private static int searchSegmentTree(int[] tree, int start, int end, int searchS, int searchE, int treeIndex) {
        if(searchS==start&&searchE==end) {
            return tree[treeIndex];
        }
        if(searchS>end||searchE<start) {
            return -1;
        }
        if((searchS>=start &&searchE<end)||(searchS>start &&searchE<=end)) {
            int mid = (start+end)/2;
            return resultAtI(searchSegmentTree(tree,start,mid,searchS,searchE>mid?mid:searchE,2*treeIndex)
                    ,searchSegmentTree(tree,mid+1,end,searchS<mid+1?mid+1:searchS,searchE,2*treeIndex+1),searchE,searchE>mid?mid:searchE);
        }
        return 0;
    }

    private static void updateSegmentTree(int[] arr, int[] tree, int start, int end, int treeNodeIndex, int updateValue, int index) {
        if(start==end) {
            arr[index] = updateValue;
            tree[treeNodeIndex] = updateValue%3;
            return;
        }
        int mid = (start+end)/2;
        if(index>mid) {
            updateSegmentTree(arr, tree,mid+1,end,2*treeNodeIndex+1,updateValue,index);
        } else {
            updateSegmentTree(arr, tree,start,mid,2*treeNodeIndex,updateValue,index);
        }
        tree[treeNodeIndex] = resultAtI(tree[2*treeNodeIndex] , tree[2*treeNodeIndex+1],end,mid);
    }



    private static void buildSegmentTree(int[] arr, int[]tree, int start, int end, int treeNodeIndex) {
        if(start==end) {
            tree[treeNodeIndex] = arr[start]%3;
            return;
        }
        int mid = (start+end)/2;
        buildSegmentTree(arr,tree,start,mid,2*treeNodeIndex);
        buildSegmentTree(arr,tree,mid+1,end,2*treeNodeIndex+1);
        tree[treeNodeIndex] = resultAtI(tree[2*treeNodeIndex] , tree[2*treeNodeIndex+1],end,mid);
    }

    private static int resultAtI(int node1Mode, int node2Mode, int r, int mid) {
        if(node1Mode==-1) {
            return node2Mode;
        }
        if(node2Mode==-1) {
            return node1Mode;
        }
        return (node2Mode + (twoPowerResult[r-mid]*node1Mode)%3)%3;

    }


}
