package com.dsa.segmenttree;

import java.util.Scanner;

/**
 *  Counting Even/Odd
 * Send Feedback
 * Ashu and Shanu are best buddies. One day Shanu gives Ashu a problem to test his intelligence.He gives him an array of N natural numbers and asks him to solve the following queries:-
 *
 * Query 0 :- modify the element present at index i to x.
 * Query 1:- count the number of even numbers in range l to r inclusive.
 * Query 2:- count the number of odd numbers in range l to r inclusive.
 *
 * Input:
 *
 * First line of the input contains the number N. Next line contains N natural numbers.
 * Next line contains an integer Q followed by Q queries.
 *
 * 0 x y - modify the number at index x to y.
 *
 * 1 x y - count the number of even numbers in range l to r inclusive.
 *
 * 2 x y - count the number of odd numbers in range l to r inclusive.
 *
 * Constraints:
 *
 * 1<=N,Q<=10^5
 *
 * 1<=l<=r<=N
 *
 * 0<=Ai<=10^9
 *
 * 1<=x<=N
 *
 * 0<=y<=10^9
 *
 * Note:-
 * indexing starts from 1.
 * Sample Input
 *
 * 6
 * 1 2 3 4 5 6
 * 4
 * 1 2 5
 * 2 1 4
 * 0 5 4
 * 1 1 6
 *
 * Sample Output
 *
 * 2
 * 2
 * 4
 */
public class CountOddEven {

    static class Node {
        int odds;
        int evens;
    }

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] arr = new int[n];
        for (int i=0;i<n;i++){
            arr[i] = in.nextInt();
        }

        int q = in.nextInt();
        // build segment tree
        Node []tree =  new Node[4*n];
        buildSegmentTree(arr, tree,0,n-1,1);
        for (int i=0;i<q;i++) {
            int type = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            if(type==0) {
                // modify the number at index x to y
                updateSegmentTree(arr,tree,0,n-1,1,y,x-1);
            }

            if(type ==1) {
                //count the number of even numbers in range l to r inclusive.
                System.out.println(searchSegmentTree(tree,0,n-1,x-1,y-1,1).evens);
            }

            if (type ==2) {
               // count the number of odd numbers in range l to r inclusive.
                System.out.println(searchSegmentTree(tree,0,n-1,x-1,y-1,1).odds);
            }
        }
    }

    private static Node searchSegmentTree(Node[] tree, int start, int end, int searchS, int searchE, int treeIndex) {
        if(searchS==start&&searchE==end) {
            return tree[treeIndex];
        }
        if(searchS>end||searchE<start) {
            return null;
        }
        if((searchS>=start &&searchE<end)||(searchS>start &&searchE<=end)) {
            int mid = (start+end)/2;
            Node node1 = searchSegmentTree(tree,start,mid,searchS,searchE>mid?mid:searchE,2*treeIndex);
            Node node2 = searchSegmentTree(tree,mid+1,end,searchS<mid+1?mid+1:searchS,searchE,2*treeIndex+1);
            Node node = null;
            if(node1==null) {
                return  node2;
            } else if(node2==null) {
                return node1;
            } else {
                node = new Node();
            }
            node.odds = node1.odds+ node2.odds;
            node.evens = node1.evens + node2.evens;
            return node;
        }
        return null;
    }


    private static void updateSegmentTree(int[] arr, Node[] tree, int start, int end, int treeNodeIndex, int updateValue, int index) {
        if(start==end) {
            arr[index] = updateValue;
            if(updateValue%2==0) {
                tree[treeNodeIndex].evens = 1;
                tree[treeNodeIndex].odds = 0;
            } else {
                tree[treeNodeIndex].evens = 0;
                tree[treeNodeIndex].odds = 1;
            }
            return;
        }
        int mid = (start+end)/2;
        if(index>mid) {
            updateSegmentTree(arr, tree,mid+1,end,2*treeNodeIndex+1,updateValue,index);
        } else {
            updateSegmentTree(arr, tree,start,mid,2*treeNodeIndex,updateValue,index);
        }
        Node node = tree[treeNodeIndex];
        node.odds = tree[2*treeNodeIndex].odds + tree[2*treeNodeIndex+1].odds;
        node.evens = tree[2*treeNodeIndex].evens + tree[2*treeNodeIndex+1].evens;
    }


    private static void buildSegmentTree(int[] arr, Node[]tree, int start, int end, int treeNodeIndex) {
        if(start==end) {
            Node node = new Node();
            if(arr[start]%2==0) {
                node.evens = 1;
            } else {
                node.odds = 1;
            }
            tree[treeNodeIndex] = node;
            return;
        }
        int mid = (start+end)/2;
        buildSegmentTree(arr,tree,start,mid,2*treeNodeIndex);
        buildSegmentTree(arr,tree,mid+1,end,2*treeNodeIndex+1);
        Node node = new Node();
        node.odds = tree[2*treeNodeIndex].odds + tree[2*treeNodeIndex+1].odds;
        node.evens = tree[2*treeNodeIndex].evens + tree[2*treeNodeIndex+1].evens;
        tree[treeNodeIndex] = node;
    }

}
