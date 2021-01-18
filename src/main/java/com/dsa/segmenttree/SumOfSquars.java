package com.dsa.segmenttree;

import java.util.Scanner;

/**
 *  Sum Of Squares
 * Send Feedback
 * Segment trees are extremely useful. In particular "Lazy Propagation" (i.e. see here, for example) allows one to compute sums over a range in O(lg(n)), and update ranges in O(lg(n)) as well. In this problem you will compute something much harder:
 * The sum of squares over a range with range updates of 2 types:
 * 1) increment in a range
 * 2) set all numbers the same in a range.
 * Input
 *
 * There will be T (T <= 25) test cases in the input file. First line of the input contains two positive integers, N (N <= 100,000) and Q (Q <= 100,000).
 * The next line contains N integers, each at most 1000. Each of the next Q lines starts with a number, which indicates the type of operation:
 *
 * 2 st nd -- return the sum of the squares of the numbers with indices in [st, nd] {i.e., from st to nd inclusive} (1 <= st <= nd <= N).
 *
 * 1 st nd x -- add "x" to all numbers with indices in [st, nd] (1 <= st <= nd <= N, and -1,000 <= x <= 1,000).
 *
 * 0 st nd x -- set all numbers with indices in [st, nd] to "x" (1 <= st <= nd <= N, and -1,000 <= x <= 1,000).
 *
 * Output
 *
 * For each test case output the “Case <caseno>:” in the first line and from the second line output the sum of squares for each operation of type 2. Intermediate overflow will not occur with proper use of 64-bit signed integer.
 *
 * Input:
 *
 * 2
 * 4 5
 * 1 2 3 4
 * 2 1 4
 * 0 3 4 1
 * 2 1 4
 * 1 3 4 1
 * 2 1 4
 * 1 1
 * 1
 * 2 1 1
 *
 * Output:
 *
 * Case 1:
 * 30
 * 7
 * 13
 * Case 2:
 * 1
 */
public class SumOfSquars {

    static class Node {
        long sum;
        long sumOfSquares;
        int type = 2;
        int value;
        public String toString() {
            return "sum="+sum+",sumOfSquares="+sumOfSquares+",type="+type+",value="+value;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i=1;i<=t;i++) {
            int n = in.nextInt();
            int q = in.nextInt();
            int [] arr = new int[n];
            for (int j=0;j<n;j++) {
                arr[j] = in.nextInt();
            }
            Node[] tree = new Node[4*n];
            buildSegmentTree(arr,tree,0,n-1,1);
            Node[] lazy = new Node[4*n];
            System.out.println("Case "+i+":");
            for (int j=1;j<=q;j++){
                int type = in.nextInt();
                int x = in.nextInt();
                int y = in.nextInt();
                int v=0;
                if(type==0) {
                    // set all numbers to v in the range x and y
                    v = in.nextInt();
                    updateSegmentTreeWithValue(tree,lazy,0,n-1,1,x-1,y-1,v);
                } else if(type==1) {
                    // increment v to all numbers in the range x and y
                    v = in.nextInt();
                    updateSegmentTree(tree,lazy,0,n-1,1,x-1,y-1,v);
                } else if(type==2) {
                    // return the sum of squares of the numbers of given range
                    System.out.println(searchSegmentTree(tree,lazy,0,n-1,1,x-1,y-1).sumOfSquares);
                }
//                System.out.println("operation="+type+" "+x+" "+y+" "+v);
//                System.out.println("--tree--");
//                for (int k=1;k<4*n;k++) {
//                    System.out.println(" "+tree[k]);
//                }
//                System.out.println("--Lazy tree--");
//                for (int k=1;k<4*n;k++) {
//                    System.out.println(" "+lazy[k]);
//                }
            }
        }
    }

    public static void buildSegmentTree(int []arr, Node []tree, int start, int end, int treeIndex) {
        if(start==end) {
            Node node = new Node();
            node.sum = arr[start];
            node.sumOfSquares = arr[start] * arr[start];
            tree[treeIndex] = node;
            return;
        }
        int mid = (start+end)/2;
        buildSegmentTree(arr, tree,start,mid,2*treeIndex);
        buildSegmentTree(arr, tree,mid+1,end,2*treeIndex+1);
        Node node = new Node();
        node.sum = tree[2*treeIndex].sum + tree[2*treeIndex+1].sum;
        node.sumOfSquares = tree[2*treeIndex].sumOfSquares + tree[2*treeIndex+1].sumOfSquares;
        tree[treeIndex] = node;
    }

    // update segment tree with increment value by inc
    public static void updateSegmentTree(Node []tree, Node[] lazy, int low, int high, int treeIndex, int startR, int endR,int inc) {
        if(low>high) {
//            System.out.println("low>high,low="+low+",high="+high);
            return;
        }
        // first check if lazy tree has anything already pending to be added
        checkLazyNode(tree, lazy, low, high, treeIndex);
        // no overlap
        if(startR>high || endR<low) {
            return;
        }

        // complete overlap
        if(startR<=low&& high<=endR) {
            updateNodeWithInc(low,high,inc,tree[treeIndex],1);
            if(low!=high) {
                if(lazy[2*treeIndex]==null) {
                    lazy[2*treeIndex] = new Node();
                }
                if(lazy[2*treeIndex+1]==null) {
                    lazy[2*treeIndex+1] = new Node();
                }
                int mid = (low+high)/2;
                updateNodeWithInc(low,mid,inc,lazy[2*treeIndex],1);
                updateNodeWithInc(mid+1,high,inc,lazy[2*treeIndex+1],1);
            }
            return;
        }

        // partial overlap
        int mid = (low+high)/2;
        updateSegmentTree(tree,lazy,low,mid,2*treeIndex,startR,endR,inc);
        updateSegmentTree(tree,lazy,mid+1,high,2*treeIndex+1,startR,endR,inc);
        Node node = tree[treeIndex];
        node.sum = tree[2*treeIndex].sum + tree[2*treeIndex+1].sum;
        node.sumOfSquares = tree[2*treeIndex].sumOfSquares + tree[2*treeIndex+1].sumOfSquares;
    }

    // update segment tree with increment value with value
    public static void updateSegmentTreeWithValue(Node []tree, Node[] lazy, int low, int high, int treeIndex, int startR, int endR,int v) {
        if(low>high) {
            System.out.println("low>high,low="+low+",high="+high);
            return;
        }
        // first check if lazy tree has anything already pending to be added
        if(lazy[treeIndex]!=null) {
            lazy[treeIndex] = null;
        }

        // no overlap
        if(startR>high || endR<low) {
            return;
        }

        // complete overlap
        if(startR<=low&& high<=endR) {
            updateNodeWithValue(low,high,v,tree[treeIndex],0);
            if(low!=high) {
                if(lazy[2*treeIndex]==null) {
                    lazy[2*treeIndex] = new Node();
                }
                if(lazy[2*treeIndex+1]==null) {
                    lazy[2*treeIndex+1] = new Node();
                }
                int mid = (low+high)/2;
                updateNodeWithValue(low,mid,v,lazy[2*treeIndex],0);
                updateNodeWithValue(mid+1,high,v,lazy[2*treeIndex+1],0);
            }
            return;
        }

        // partial overlap
        int mid = (low+high)/2;
        updateSegmentTreeWithValue(tree,lazy,low,mid,2*treeIndex,startR,endR,v);
        updateSegmentTreeWithValue(tree,lazy,mid+1,high,2*treeIndex+1,startR,endR,v);
        Node node = tree[treeIndex];
        node.sum = tree[2*treeIndex].sum + tree[2*treeIndex+1].sum;
        node.sumOfSquares = tree[2*treeIndex].sumOfSquares + tree[2*treeIndex+1].sumOfSquares;
    }

    public static Node searchSegmentTree(Node []tree, Node[] lazy, int low, int high, int treeIndex, int searchS, int searchE) {
        // first check if lazy tree has anything already pending to be added
        checkLazyNode(tree, lazy, low, high, treeIndex);

        // Match
        if(searchE==high && searchS==low) {
            return tree[treeIndex];
        }

        // if no match
        if(searchS>high||searchE<low) {
            return null;
        }

        // partial overlap
        if((searchS>=low &&searchE<high)||(searchS>low &&searchE<=high)) {
            int mid = (low+high)/2;
            Node node1 = searchSegmentTree(tree,lazy,low,mid,2*treeIndex,searchS,searchE>mid?mid:searchE);
            Node node2 = searchSegmentTree(tree,lazy,mid+1,high,2*treeIndex+1,searchS<mid+1?mid+1:searchS,searchE);
            return addNode(node1,node2);
        }
        return tree[treeIndex];
    }

    private static void checkLazyNode(Node[] tree, Node[] lazy, int low, int high, int treeIndex) {
        if(lazy[treeIndex]!=null) {
            Node lNode = lazy[treeIndex];
            if(lNode.type==0) {
                // then update tree node with lazy node
                updateNodeWithLazyNode(tree[treeIndex],lNode);
                if(low != high) {
                    if(lazy[2* treeIndex]==null) {
                        Node childLazyNode = getChildLazyNode(lNode);
                        childLazyNode.sum = tree[2* treeIndex].sum;
                        lazy[2* treeIndex] = childLazyNode;
                    }
                    if(lazy[2* treeIndex +1]==null) {
                        Node childLazyNode = getChildLazyNode(lNode);
                        childLazyNode.sum = tree[2* treeIndex+1].sum;
                        lazy[2* treeIndex +1] = childLazyNode;
                    }
                    int mid = (low+high)/2;
                    updateNodeWithValue(low,mid,lNode.value,lazy[2* treeIndex],lNode.type);
                    updateNodeWithValue(mid+1,high,lNode.value,lazy[2* treeIndex+1],lNode.type);
                }
            }

            if(lNode.type==1) {
                // then increment
                incrementNodeWithLazyNode(tree[treeIndex], lazy[treeIndex]);
                if(low != high) {
                    if(lazy[2* treeIndex]==null) {
                        Node childLazyNode = getChildLazyNode(lNode);
                        childLazyNode.sum = tree[2* treeIndex].sum;
                        lazy[2* treeIndex] = childLazyNode;
                    }
                    if(lazy[2* treeIndex +1]==null) {
                        Node childLazyNode = getChildLazyNode(lNode);
                        childLazyNode.sum = tree[2* treeIndex+1].sum;
                        lazy[2* treeIndex +1] = childLazyNode;
                    }
                    int mid = (low+high)/2;
                    updateNodeWithInc(low, mid,lNode.value,lazy[2*treeIndex],lNode.type);
                    updateNodeWithInc(low, mid,lNode.value,lazy[2*treeIndex+1],lNode.type);
                }
            }
        }
        lazy[treeIndex] = null;
    }

    private static Node getChildLazyNode(Node lNode) {
        Node childLazyNode = new Node();
        childLazyNode.type = lNode.type;
        childLazyNode.value = lNode.value;
        return childLazyNode;
    }

    private static Node addNode(Node node1, Node node2) {
        if(node1==null) {
            return node2;
        }
        if(node2==null) {
            return node1;
        }
        Node node = new Node();
        node.sumOfSquares = node1.sumOfSquares + node2.sumOfSquares;
        node.sum = node1.sum + node2.sum;
        return node;
    }

    private static void updateNodeWithLazyNode(Node node, Node lazyNode) {
        node.sumOfSquares = lazyNode.sumOfSquares;
        node.sum =lazyNode.sum;
        node.type = lazyNode.type;
    }

    private static void incrementNodeWithLazyNode(Node node, Node lazyNode) {
        node.sumOfSquares +=lazyNode.sumOfSquares;
        node.sum +=lazyNode.sum;
        node.type = lazyNode.type;
    }

    private static void updateNodeWithValue(int x, int y, int v, Node node, int type) {
        node.sumOfSquares = (y-x+1)*v*v;
        node.sum = (y-x+1) * v;
        node.type = type;
        node.value = v;
    }

    private static void updateNodeWithInc(int x, int y, int inc, Node node, int type) {
        node.sumOfSquares += getSSIncValue(x,y,inc,node.sum);
        node.sum +=getSumIncValue(x,y,inc);
        node.type = type;
        node.value = inc;
    }


    private static long getSSIncValue(int x, int y, int inc, long sum) {
        return (y-x+1) * inc * inc + 2 * sum * inc;
    }

    private static long getSumIncValue(int x, int y, int inc) {
        return (y-x+1) * inc;
    }


}
