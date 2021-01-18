package com.dsa.segmenttree;

/**
 * when the problems where you get updates for a particular range like 2, 5
 * then one option is you can update each node one by one which will have complexity nlog(n)
 * Lazy propagation helps to update and Qeuery in log(n) time itself.
 */
public class LazyPropagationTree {

    public static void main(String[] args) {
        int [] arr = new int[]{1,2,-3,4};
        int [] tree = new int[16];
        int[] lazy = new int[16];
        buildSegmentTree(arr,tree,0,3,1);
        for (int i=1;i<10;i++) {
            System.out.println(tree[i]);
        }
        updateSegmentTree(tree,lazy,0,3,1,0,3,4);
        System.out.println("---------");
        for (int i=1;i<10;i++) {
            System.out.println(tree[i]);
        }
        System.out.println("Lazy---------");
        for (int i=1;i<10;i++) {
            System.out.println(lazy[i]);
        }
        updateSegmentTree(tree,lazy,0,3,1,2,3,8);
        System.out.println("---------");
        for (int i=1;i<10;i++) {
            System.out.println(tree[i]);
        }
        System.out.println("Lazy---------");
        for (int i=1;i<10;i++) {
            System.out.println(lazy[i]);
        }
        System.out.println("minValue="+searchSegmentTree(tree,lazy,0,3,1,1,2));
    }

    public static void buildSegmentTree(int []arr, int []tree, int start, int end, int treeIndex) {
        if(start==end) {
            tree[treeIndex] = arr[start];
            return;
        }
        int mid = (start+end)/2;
        buildSegmentTree(arr, tree,start,mid,2*treeIndex);
        buildSegmentTree(arr, tree,mid+1,end,2*treeIndex+1);
        tree[treeIndex] = min(tree[2*treeIndex],tree[2*treeIndex+1]);
    }

    public static int searchSegmentTree(int []tree, int[] lazy, int low, int high, int treeIndex, int searchS, int searchE) {
        if(low>high) {
            System.out.println("low>high,low="+low+",high="+high);
            return Integer.MAX_VALUE;
        }
        // first check if lazy tree has anything already pending to be added
        if(lazy[treeIndex]!=0) {
            tree[treeIndex]+=lazy[treeIndex];
            if(low!=high) {
                lazy[2*treeIndex] +=lazy[treeIndex];
                lazy[2*treeIndex+1] +=lazy[treeIndex];
            }
            lazy[treeIndex] = 0;
        }

        // Match
        if(searchS==high && searchE==low) {
            return tree[treeIndex];
        }

        // if no match
        if(searchS>high||searchE<low) {
            return Integer.MAX_VALUE;
        }

        // partial overlap
        if((searchS>=low &&searchE<high)||(searchS>low &&searchE<=high)) {
            int mid = (low+high)/2;
            int min1 = searchSegmentTree(tree,lazy,low,mid,2*treeIndex,searchS,searchE>mid?mid:searchE);
            int min2 = searchSegmentTree(tree,lazy,mid+1,high,2*treeIndex+1,searchS<mid+1?mid+1:searchS,searchE);
//            tree[treeIndex] = min(tree[2*treeIndex],tree[2*treeIndex+1]);
            return min(min1,min2);
        }
        return tree[treeIndex];
    }


    public static void updateSegmentTree(int []tree, int[] lazy, int low, int high, int treeIndex, int startR, int endR,int inc) {
        if(low>high) {
            System.out.println("low>high,low="+low+",high="+high);
            return;
        }
        // first check if lazy tree has anything already pending to be added
        if(lazy[treeIndex]!=0) {
            tree[treeIndex]+=lazy[treeIndex];
            if(low!=high) {
                lazy[2*treeIndex] +=lazy[treeIndex];
                lazy[2*treeIndex+1] +=lazy[treeIndex];
            }
            lazy[treeIndex] = 0;
        }

        // no overlap
        if(startR>high || endR<low) {
            return;
        }

        // complete overlap
        if(startR<=low&& high<=endR) {
            tree[treeIndex] +=inc;
            if(low!=high) {
                lazy[2*treeIndex] +=inc;
                lazy[2*treeIndex+1] +=inc;
            }
            return;
        }

        // partial overlap
        int mid = (low+high)/2;
        updateSegmentTree(tree,lazy,low,mid,2*treeIndex,startR,endR,inc);
        updateSegmentTree(tree,lazy,mid+1,high,2*treeIndex+1,startR,endR,inc);
        tree[treeIndex] = min(tree[2*treeIndex],tree[2*treeIndex+1]);
    }

    private static int min(int a, int b) {
        return a>b?b:a;
    }
}
