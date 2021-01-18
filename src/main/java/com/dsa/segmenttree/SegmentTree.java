package com.dsa.segmenttree;

/**
 *
 */
public class SegmentTree {
    public static void main(String[] args) {
        int [] arr = new int[]{1,3,4,5,6,7};
        // tree size should always be 4*n
        int [] tree = new int[4*arr.length];
        buildSegmentTree(arr,tree,0,arr.length-1,1);
        for (int i=0;i<2*arr.length+1;i++) {
            System.out.println(tree[i]);
        }
        System.out.println("sum="+searchSegmentTree(arr,tree,0,arr.length-1,1,4,1));
        updateSegmentTree(arr,tree,0,arr.length-1,1,10,5);
        for (int i=0;i<2*arr.length+1;i++) {
            System.out.println(tree[i]);
        }
        System.out.println("sum="+searchSegmentTree(arr,tree,0,arr.length-1,2,5,1));
    }

    private static int searchSegmentTree(int[] arr, int[] tree, int start, int end, int searchS, int searchE, int treeIndex) {
        if(searchS==start&&searchE==end) {
            return tree[treeIndex-1];
        }
        if(searchS>end||searchE<start) {
            return 0;
        }
        if((searchS>=start &&searchE<end)||(searchS>start &&searchE<=end)) {
            int mid = (start+end)/2;
            return searchSegmentTree(arr,tree,start,mid,searchS,searchE>mid?mid:searchE,2*treeIndex)
                    +searchSegmentTree(arr,tree,mid+1,end,searchS<mid+1?mid+1:searchS,searchE,2*treeIndex+1);
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
        tree[treeNodeIndex-1] = tree[2*treeNodeIndex-1] + tree[2*treeNodeIndex];
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
        tree[treeNodeIndex-1] = tree[2*treeNodeIndex-1] + tree[2*treeNodeIndex];
    }
}
