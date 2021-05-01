package com.dsa.tries;

import java.util.Scanner;

public class XorMaximumPair {

    static class TrieNode {
        TrieNode left;
        TrieNode right;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] arr = new int[n];
        TrieNode trieNode = new TrieNode();
        for (int i=0;i<n;i++) {
            arr[i] = in.nextInt();
            insert(trieNode,arr[i]);
        }
        System.out.println(findMaximumXorPair(trieNode,arr,arr.length));
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

    private static int findMaximumXorPair(TrieNode head, int[] arr, int n) {
        int max_xor = Integer.MIN_VALUE;
        for (int i=0;i<n;i++) {
            TrieNode curr = head;
            int value = arr[i];
            int curr_xor = 0;
            for(int j=31;j>=0;j--) {
                int b = (value>>i)&1;
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
            if(curr_xor>max_xor) {
                max_xor = curr_xor;
            }
        }
        return max_xor;
    }
}
