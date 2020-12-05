package com.dsa.tree.opt;

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

class TreeTraversal {

    /*
    class Node
        int data;
        Node left;
        Node right;
    */
    public static Node lca(Node root, int v1, int v2) {
        // Write your code here.

        if(root==null) {
            return null;
        }

        Node[] nodesMatched = getParentNodeMatched(root,v1,v2);
        return nodesMatched[2]!=null?nodesMatched[2]:root;
    }

    public static Node[] getParentNodeMatched(Node node, int v1, int v2) {
        if(node==null) {
            return null;
        }
        Node[] nodesMatched = {null,null,null};
        if(node.data==15) {
            System.out.println("Matched 15");
        }
        if(node.data==11) {
            System.out.println("Matched 11");
        }
        if(node.data == v1) {
            nodesMatched[0] = node;
        }
        if(node.data == v2) {
            nodesMatched[1] = node;
        }
        Node[] nodesMatchedLeft = getParentNodeMatched(node.left,v1,v2);

        if(nodesMatchedLeft==null) {
            // it does not have left child node
        } else {
            if(nodesMatchedLeft[2]!=null) {
                return nodesMatchedLeft;
            } else if(nodesMatchedLeft[0]!=null && nodesMatchedLeft[1]!=null) {
                nodesMatchedLeft[2] = node;
                return nodesMatchedLeft;
            } else if(nodesMatched[0]!=null && nodesMatchedLeft[1]!=null) {
                nodesMatched[1] = nodesMatchedLeft[1];
                return nodesMatched;
            } else if(nodesMatched[1]!=null && nodesMatchedLeft[0]!=null) {
                nodesMatched[0] = nodesMatchedLeft[0];
                return nodesMatched;
            } else {
                // do not return thing as both elements are not matched
            }
        }

        Node[] nodesMatchedRight = getParentNodeMatched(node.right,v1,v2);

        if(nodesMatchedLeft==null&& nodesMatchedRight==null) {
            return nodesMatched;
        } else if(nodesMatchedLeft!=null && nodesMatchedRight==null) {
            if(nodesMatchedLeft[0]!=null) {
                nodesMatched[0] = nodesMatchedLeft[0];
            }
            if(nodesMatchedLeft[1]!=null) {
                nodesMatched[1] = nodesMatchedLeft[1];
            }
            return nodesMatched;
        } else if(nodesMatchedLeft==null && nodesMatchedRight!=null) {
            if(nodesMatchedRight[0]!=null) {
                nodesMatched[0] = nodesMatchedRight[0];
            }
            if(nodesMatchedRight[1]!=null) {
                nodesMatched[1] = nodesMatchedRight[1];
            }
            return nodesMatched;

        } else {

            if(nodesMatchedRight[2]!=null) {
                return nodesMatchedRight;
            } else if(nodesMatchedRight[0]!=null && nodesMatchedRight[1]!=null) {
                nodesMatchedRight[2] = node;
                return nodesMatchedRight;
            } else if(nodesMatched[0]!=null && nodesMatchedRight[1]!=null) {
                nodesMatched[1] = nodesMatchedRight[1];
                return nodesMatched;
            } else if(nodesMatched[1]!=null && nodesMatchedRight[0]!=null) {
                nodesMatched[0] = nodesMatchedRight[0];
                return nodesMatched;
            } else if(nodesMatchedLeft[0]!=null && nodesMatchedRight[1]!=null) {
                nodesMatchedLeft[1] = nodesMatchedRight[1];
                nodesMatchedLeft[2] = node;
                return nodesMatchedLeft;
            } else if(nodesMatchedLeft[1]!=null && nodesMatchedRight[0]!=null) {
                nodesMatchedLeft[0] = nodesMatchedLeft[0];
                nodesMatchedLeft[2] = node;
                return nodesMatchedLeft;
            } else if(nodesMatchedRight[0]!=null) {
                nodesMatched[0] = nodesMatchedRight[0];
                return nodesMatched;
            } else if(nodesMatchedRight[1] !=null) {
                nodesMatched[1] = nodesMatchedRight[1];
                return nodesMatched;
            } else if(nodesMatchedLeft[0] !=null) {
                nodesMatched[0] = nodesMatchedLeft[0];
                return nodesMatched;
            } else if(nodesMatchedLeft[1]!=null) {
                nodesMatched[1] = nodesMatchedLeft[1];
                return nodesMatched;
            } else {
                return nodesMatched;
            }
        }

    }

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