package com.dsa.segmenttree;

import java.util.Scanner;

/**
 *  Vasya vs Rhezo
 * Send Feedback
 * Queen Vasya is preparing for a war against Rhezo. She has N warriors in total arranged in a line. Let us number the warriors by numbers from 1 to N. She will fight Rhezo's army for Q days, and each day she can choose only one warrior.
 * For each warrior, we know 2 values associated with him, let us call these A and B. Each day Vasya can choose her warrior from a range Li to Ri, she must choose the warrior with maximum A value. If there is more than 1 warrior having the same maximum A value, she chooses the warrior with minimum B value. If still there is more than 1 warrior with same maximum A value and same minimum B value, she chooses the one with lower index in line.
 * You being the hand of Queen Vasya, need to help her in choosing the warrior for each day.
 * Input:
 *
 * First line contains a single integer N, denoting the number of warriors Queen Vasya has.
 * Second line contains N space separated integers Ai. Third line contains N space separated integers Bi.
 * Next line contains a single integer Q, denoting the number of days Queen Vasya chooses a warrior.
 * Each of the next Q lines contains 2 integers Li and Ri.
 *
 * Output:
 *
 * For each Li and Ri, print the index of the warrior that Queen Vasya should choose.
 *
 * Constraints:
 * 1≤ N,Q ≤10^6
 * 1≤ Ai,Bi ≤10^9
 * 1≤Li≤Ri
 * Sample Input
 *
 * 5
 * 1 8 4 6 8
 * 4 8 6 3 7
 * 4
 * 1 4
 * 2 4
 * 3 4
 * 1 5
 *
 * Sample Output
 *
 * 2
 * 2
 * 4
 * 5
 */
public class VasyaVsRhezo {

    static class Warrior {
        long propertyA;
        long propertyB;
        int index;
    }

    public static void main(String [] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Warrior[] warriors = new Warrior[n];

        for (int i=0;i<n;i++) {
            warriors[i] = new Warrior();
            warriors[i].propertyA = in.nextInt();
            warriors[i].index = i;
        }
        for (int i=0;i<n;i++) {
            warriors[i].propertyB = in.nextInt();
        }
        Warrior [] tree = new Warrior[4*n];
        buildSegmentTree(warriors,tree,0,n-1,1);
        int q = in.nextInt();
        for(int i=0;i<q;i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            System.out.println(searchSegmentTree(tree,0,n-1,x-1,y-1,1).index+1);
        }

    }

    private static void buildSegmentTree(Warrior[] warriors, Warrior[]tree, int start, int end, int treeNodeIndex) {
        if(start==end) {
            tree[treeNodeIndex] = warriors[start];
            return;
        }
        int mid = (start+end)/2;
        buildSegmentTree(warriors, tree,start,mid,2*treeNodeIndex);
        buildSegmentTree(warriors, tree,mid+1,end,2*treeNodeIndex+1);
        tree[treeNodeIndex] = getApplicableWarrior(tree[2*treeNodeIndex] , tree[2*treeNodeIndex+1]);
    }


    private static Warrior getApplicableWarrior(Warrior node1, Warrior node2) {
        Warrior warrior = null;
        if(node1==null) {
            return cloneWarrior(node2);
        }
        if(node2==null) {
            return cloneWarrior(node1);
        }
        if(node1.propertyA>node2.propertyA) {
            warrior = cloneWarrior(node1);
        } else if(node1.propertyA<node2.propertyA) {
            warrior = cloneWarrior(node2);
        } else {
            // both are equal then choose the one with minA value
            if(node1.propertyB<node2.propertyB) {
                warrior = cloneWarrior(node1);
            } else if(node1.propertyB>node2.propertyB) {
                warrior = cloneWarrior(node2);
            } else {
                // compare indexes and choose the one with min index
                warrior = node1.index<node2.index?cloneWarrior(node1):cloneWarrior(node2);
            }
        }
        return warrior;
    }

    private static Warrior searchSegmentTree(Warrior[] tree, int start, int end, int searchS, int searchE, int treeIndex) {
        if(searchS==start&&searchE==end) {
            return tree[treeIndex];
        }
        if(searchS>end||searchE<start) {
            return null;
        }
        if((searchS>=start &&searchE<end)||(searchS>start &&searchE<=end)) {
            int mid = (start+end)/2;
            return getApplicableWarrior(searchSegmentTree(tree,start,mid,searchS,searchE>mid?mid:searchE,2*treeIndex)
                    ,searchSegmentTree(tree,mid+1,end,searchS<mid+1?mid+1:searchS,searchE,2*treeIndex+1));
        }
        return null;
    }


    private static Warrior cloneWarrior(Warrior node) {
        Warrior warrior;
        warrior = new Warrior();
        warrior.index = node.index;
        warrior.propertyB = node.propertyB;
        warrior.propertyA = node.propertyA;
        return warrior;
    }

}
