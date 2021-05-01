package com.dsa.tries;

import java.util.Scanner;

/**
 *  Search Engine
 * Send Feedback
 * Let us see how search engines work. Consider the following simple auto complete feature. When you type some characters in the text bar, the engine automatically gives best matching options among it's database. Your job is simple. Given an incomplete search text, output the best search result.
 * Each entry in engine's database has a priority factor attached to it. We consider a result / search suggestion best if it has maximum weight and completes the given incomplete search query. For each query in the input, print the maximum weight of the string in the database, that completes the given incomplete search string. In case no such string exists, print -1.
 * INPUT
 *
 * First line contains two integers n and q, which represent number of database entries and number of search queries need to be completed. Next n lines contain a string s and an integer weight, which are the database entry and it's corresponding priority.
 *
 * Next q lines follow, each line having a string t, which needs to be completed.
 *
 * OUTPUT
 *
 * Output q lines, each line containing the maximum possible weight of the match for given query, else -1, in case no valid result is obtained.
 *
 * CONSTRAINTS
 *
 * 1 ≤ n, weight, len(s), len(t) ≤ 10^6
 * 1 ≤ q ≤ 10^5
 * total length of all strings in database entries ≤ 10^6
 * total length of all query strings ≤ 10^6
 *
 * SAMPLE INPUT
 *
 * 2 1
 * hackerearth 10
 * hackerrank 9
 * hacker
 *
 * SAMPLE OUTPUT
 *
 * 10
 */
public class SearchEngine {

    static  class TrieNode {
        TrieNode [] children = new TrieNode[26];
        int w;
    }

    private static void insert(TrieNode head, String s, int w) {
        TrieNode curr = head;
        for(int i=0;i<s.length();i++) {
            int index = s.charAt(i) - 97;
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
                curr.children[index].w = w;
            } else {
                if (curr.children[index].w < w) {
                    curr.children[index].w = w;
                }
            }
            curr = curr.children[index];
        }
    }

    private static int query(TrieNode head, String s) {
        int ans = -1;
        TrieNode curr = head;
        for (int i=0;i<s.length();i++) {
            int index = s.charAt(i) - 97;
            if(curr.children[index]!=null) {
                if(i==s.length()-1) {
                    ans = curr.children[index].w;
                }
                curr = curr.children[index];
            } else {
                break;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        TrieNode head = new TrieNode();
        for (int i=0;i<n;i++) {
            String s = in.next();
            int w = in.nextInt();
            insert(head,s,w);
        }
        for (int i=0;i<q;i++) {
            String queryStr = in.next();
            System.out.println(query(head,queryStr));
        }
    }
}
