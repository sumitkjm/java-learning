package com.dsa.tree;

import java.util.*;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class LowestCommonAncestorTree {

    /*
    class Node
        int data;
        Node left;
        Node right;
    */
    public static Node lca(Node root, int v1, int v2) {
        // Write your code here.

        Node lcaNode = new Node(-1);
        if(root==null) {
            return null;
        }

        int h = 0;
        int [] hp = {0};
        int v1h = 0;
        int v2h = 1;
        Node parentNodeV1 = getNodeMatched(root, v1, h, hp);
        //  System.out.println("parent of V1:"+parentNodeV1.data);
        //   System.out.println("Height of parent of V1:"+hp[0]);
        v1h = hp[0];
        hp[0] = 0;
        Node parentNodeV2 = getNodeMatched(root, v2, h, hp);
        //   System.out.println("parent of V2:"+parentNodeV2.data);
        //   System.out.println("Height of parent of V2:"+hp[0]);
        v2h = hp[0];
        hp[0] = 0;

        if(parentNodeV1.data==parentNodeV2.data) {
            return parentNodeV1;
        } else if(v1h==v2h) {
            //   System.out.println("v1h==v2h - 1");
            return getParentNode(root, parentNodeV1,parentNodeV2,v1h,v2h);
        } else if(v1h>v2h) {
            // System.out.println("v1h>v2h - 1");

            while(v1h!=v2h) {
                parentNodeV1 = getNodeMatched(root, parentNodeV1.data, h, hp);
                v1h = hp[0];
                hp[0] = 0;
            }
            // System.out.println("v1h==v2h - 2");

            if(parentNodeV1.data==parentNodeV2.data) {
                return parentNodeV1;
            }
            return getParentNode(root, parentNodeV1,parentNodeV2,v1h,v2h);
        } else if(v2h>v1h) {
            // System.out.println("v2h>v1h - 2");
            while(v1h!=v2h) {
                parentNodeV2 = getNodeMatched(root, parentNodeV2.data, h, hp);
                v2h = hp[0];
                hp[0] = 0;
            }
            //  System.out.println("v1h==v2h - 3"+ v2h+"parentNodeV2:"+parentNodeV2.data+"parentNodeV1:"+parentNodeV1.data);

            if(parentNodeV1.data==parentNodeV2.data) {
                return parentNodeV1;
            }

            return getParentNode(root, parentNodeV1,parentNodeV2,v1h,v2h);
        } else {
            return null;
        }
        //arr = traverse(root, v1, v2, lcaNode);
    }

    public static Node getParentNode(Node root, Node parentNodeV1, Node parentNodeV2, int v1h, int v2h) {
        int h =0;
        int[] hp = {0};
        while(parentNodeV1.data!=parentNodeV2.data) {
            parentNodeV1 = getNodeMatched(root, parentNodeV1.data, h, hp);
            v1h = hp[0];
            hp[0] = 0;
            parentNodeV2 = getNodeMatched(root, parentNodeV2.data, h, hp);
            v2h = hp[0];
            hp[0] = 0;
        }
        return parentNodeV1;
    }

    public static Node getNodeMatched(Node node, int v1, int h, int [] hp) {
        if(node==null) {
            return null;
        }
        if(node.data==v1) {
            return node;
        }
        Node matchedNodeLeft = getNodeMatched(node.left, v1, h+1, hp);
        Node parentNode = getParentNode(matchedNodeLeft, node, v1, h,hp);
        if(parentNode!=null) {
            return parentNode;
        }
        Node matchedNodeRight = getNodeMatched(node.right, v1, h+1, hp);
        return getParentNode(matchedNodeRight, node, v1, h, hp);
    }

    public static Node getParentNode(Node matchedNode, Node currentNode, int value, int h, int [] hp) {
        if(matchedNode!=null && matchedNode.data==value) {
            hp[0] = h;
            return currentNode;
        }
        if(matchedNode!=null && matchedNode.data!=value) {
            return matchedNode;
        }
        return null;
    }

    /**    public static int[] traverse(Node node, int v1, int v2, Node lcaNode) {
     int arr[] = {0,0};
     if(node==null) {
     return arr;
     }
     int[] arrLeft = traverse(node.left, v1, v2, arr, lcaNode);
     if(arr[0]==1 && arr[1]==1) {
     System.out.print("Matched 1:"+node.data+" Data");
     lcaNode.data = node.data;
     arr[0] = -1;
     arr[1] = -1;
     return;
     }
     if(arrLeft == null) {
     // it's a leaf node for left child
     arr[0] = traverse();
     }

     int[] arrRight = traverse(node.right, v1, v2, arr, lcaNode);
     if(arrRight == null) {
     // it's leaf node for right child
     }
     if(node.data == v1) {
     arr[0] = 1;
     }
     if(node.data == v2) {
     arr[1] = 1;
     }

     if(arr[0]==1 && arr[1]==1) {
     System.out.print("Matched 2:"+node.data+ " Data");
     lcaNode.data = node.data;
     arr[0] = -1;
     arr[1] = -1;
     return;
     }
     }
     */
    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        Node ans = lca(root,v1,v2);
        System.out.println(ans.data);
    }
}