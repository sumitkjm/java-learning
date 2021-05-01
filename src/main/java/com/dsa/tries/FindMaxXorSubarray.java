package com.dsa.tries;

import java.util.Scanner;

/**
 *  Maximum XOR Subarray
 * Send Feedback
 * Given an array of n integers, find subarray whose xor is maximum.
 * Input Format
 *
 * First line contains single integer n (1<=n<=1000000).
 * Next line contains n space separated integers.
 *
 * Output Format
 *
 * Print xor of the subarray whose xor of all elements in subarray is maximum over all subarrays.
 *
 * Constraints
 *
 * 1 <= n <= 1000000
 * 1 <= A[i] <=100000
 *
 * Sample Input
 *
 * 4
 * 1 2 3 4
 *
 * Sample Output
 *
 * 7
 */
public class FindMaxXorSubarray {
    static class TrieNode {
        TrieNode left;
        TrieNode right;
    }

    private static int findMaximumXorSubarray(TrieNode head, int[] arr, int n) {
        int max_xor = Integer.MIN_VALUE;
        int preXor = 0;
        for (int i=0;i<n;i++) {
            TrieNode curr = head;
            int value = arr[i];
            preXor = preXor^value;
            insert(head,preXor);
            int curr_xor = getCurr_xor(curr, preXor);
            if(curr_xor>max_xor) {
                max_xor = curr_xor;
            }
        }
        return max_xor;
    }

    static int max(int a, int b) {
        return a>b?a:b;
    }

    private static int getCurr_xor(TrieNode curr, int value) {
        int curr_xor = 0;
        for(int j=31;j>=0;j--) {
            int b = (value >> j)&1;
            if(b==0) {
                if(curr.right!=null) {
                    curr_xor += (int)Math.pow(2,j);
                    curr = curr.right;
                } else {
                    curr = curr.left;
                }
            } else {
                if(curr.left!=null) {
                    curr_xor += (int)Math.pow(2,j);
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
        }
        return curr_xor;
    }


    private static void insert(TrieNode head, int n) {
        TrieNode curr = head;
        for(int i=31;i>=0;i--) {
            int b = (n>>i)&1;
            if(b==0) {
                if(curr.left==null) {
                    curr.left = new TrieNode();
                }
                curr = curr.left;
            } else {
                if(curr.right==null) {
                    curr.right = new TrieNode();
                }
                curr = curr.right;
            }
        }
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i=0;i<n;i++) {
            arr[i] = in.nextInt();
        }
        TrieNode trieNode = new TrieNode();
        insert(trieNode,arr[0]);
        System.out.println(findMaximumXorSubarray(trieNode,arr,n));
    }

}
