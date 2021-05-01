package com.dsa.tries;

import javax.swing.tree.TreeNode;
import java.util.Scanner;

/**
 *  SUBXOR
 * Send Feedback
 * A straightforward question. Given an array of positive integers you have to print the number of subarrays whose XOR is less than K. Subarrays are defined as a sequence of continuous elements Ai, Ai+1, ..., Aj . XOR of a subarray is defined as Ai ^ Ai+1 ^ ... ^ Aj. Symbol ^ is Exclusive Or.
 * Input Format
 *
 * First line contains T, the number of test cases.
 * Each of the test case consists of N and K in one line, followed by N space separated integers in next line.
 *
 * Output Format
 *
 * For each test case, print the required answer.
 *
 * Constraints:
 *
 * 1 ≤ T ≤ 5
 * 1 ≤ N ≤ 10^5
 * 1 ≤ A[i] ≤ 10^5
 * 1 ≤ K ≤ 10^6
 *
 * Sample Input
 *
 * 1
 * 5 2
 * 4 1 3 2 7
 *
 * Sample Output
 *
 * 3
 */
public class SubarrayXor {

    static class TrieNode {
        public TrieNode left;
        public TrieNode right;
        int cnt = 0;
    }

    public static long query(TrieNode head, int maxXor, int k) {
        int count = 0;
        TrieNode curr = head;
        for(int j=31;j>=0;j--) {
            int kb = (k >> j)&1;
            int maxXorb = (maxXor>>j)&1;

            if(kb==1) {
                if(maxXorb==1) {
                    // take the count of right node and move to left
                    // as 1 (max Xor bit) Xor 1 (right node) is 0 which is less than kb bit 1
                    if(curr.right!=null) {
                        count+=curr.right.cnt;
                    }
                    curr = curr.left;
                } else {
                    // take the count of left node and move to right
                    // as maxXor bit = 0 Xor 0 (current node bit) is 0 which is less than kb bit 1
                    if(curr.left!=null) {
                        count+=curr.left.cnt;
                    }
                    curr = curr.right;
                }
            } else {
                if(maxXorb==1) {
                    // move right to make current bit (1) Xor maxXorb (1) to 0
                    curr = curr.right;
                } else {
                    // move left to make current bit (0) Xor maxXorb (0) to 0
                    curr = curr.left;
                }
            }
            // break as soon if you meet leaf node.
            if(curr==null) {
                break;
            }
        }
        return count;
    }

    private static void insertXor(TrieNode head, int n) {
        TrieNode curr = head;
        for(int i=31;i>=0;i--) {
            int b = (n>>i)&1;
            if(b==0) {
                if(curr.left==null) {
                    curr.left = new TrieNode();
                    curr.left.cnt = 1;
                } else {
                    curr.left.cnt++;
                }
                curr = curr.left;
            } else {
                if(curr.right==null) {
                    curr.right = new TrieNode();
                    curr.right.cnt = 1;
                } else {
                    curr.right.cnt++;
                }
                curr = curr.right;
            }
        }
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-->0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] arr = new int[n];
            TrieNode trieNode = new TrieNode();
            int maxXor = 0;
            int ans = 0;
            insertXor(trieNode, maxXor);
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                maxXor^=arr[i];
                ans+=query(trieNode,maxXor,k);
                insertXor(trieNode, maxXor);
            }
            System.out.println(ans);
        }
    }
}
