package com.dsa.tree;

import com.dsa.tree.common.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CheckBST {

    public static Node insert(com.dsa.tree.common.Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data >= root.getData()) {
                cur = insert(root.getLeft(), data);
                root.setLeft(cur);
            } else {
                cur = insert(root.getRight(), data);
                root.setRight(cur);
            }
            return root;
        }
    }

    public static void printTreeView(com.dsa.tree.common.Node node, Boolean isLeft, int treeSize, int h, Map<Integer, StringBuilder> treePrintMap ) {
        if(node==null) {
            return;
        }
        if(isLeft==null){
            getTreeLevelSB(treePrintMap,h,treeSize).append(getSpace(treeSize-2*h+1)).append(node.getData());
        } else if(isLeft) {
            getTreeLevelSB(treePrintMap,h,treeSize).append(getSpace(treeSize-2*h+h)).append("l").append(node.getData());
        } else {
            getTreeLevelSB(treePrintMap,h,treeSize).append(getSpace(treeSize-2*h+h)).append("r").append(node.getData());
        }
        printTreeView(node.getLeft(), true, treeSize,h+1,treePrintMap);
        printTreeView(node.getRight(), false, treeSize,h+1,treePrintMap);
    }

    private static StringBuilder getTreeLevelSB(Map<Integer, StringBuilder> treePrintMap, int h, int size) {
        if(treePrintMap.get(h)==null) {
            treePrintMap.put(h,new StringBuilder());
            treePrintMap.get(h).append(getSpace(size-2*h));
        }
        return treePrintMap.get(h);
    }

    private static String getSpace(int ns) {
        StringBuilder spaces = new StringBuilder();

        for (int i=0;i<ns;i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        int size = t;
        com.dsa.tree.common.Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        Map<Integer, StringBuilder> treePrintMap = new HashMap<>();
        printTreeView(root,null,size,0, treePrintMap);
        for (StringBuilder stringBuilder:treePrintMap.values()
        ) {
            System.out.println(stringBuilder.toString());
        }

        System.out.println("isBST:"+checkBST(root));

    }

    static boolean checkBST(Node root) {
        if(root==null) {
            return false;
        }
        Node leftNode = traverseNode(root.getLeft(), true);
        Node rightNode = traverseNode(root.getRight(), true);
        if((leftNode!=null && leftNode.getData()==-1)||(rightNode!=null && rightNode.getData()==-1)) {
            return false;
        } else {
            return true;
        }
    }

    static Node traverseNode(Node node, boolean isLeft) {
        if(node==null) {
            return null;
        }
        Node nodeLeft = traverseNode(node.getLeft(), true);
        if(nodeLeft!=null && nodeLeft.getData()==-1) {
            return nodeLeft;
        }
        Node nodeRight = traverseNode(node.getRight(), false);
        if(nodeRight!=null && nodeRight.getData()==-1) {
            return nodeRight;
        }
        if(nodeLeft==null && nodeRight==null) {
            return node;
        } else if((nodeLeft!=null && nodeLeft.getData()>node.getData())||(nodeRight!=null && nodeRight.getData()<node.getData())) {
            //
            Node negNode = new Node(-1);
            return negNode;
        } else if(nodeLeft!=null && nodeRight!=null) {
            if(isLeft) {
                return nodeRight;
            } else {
                return nodeLeft;
            }
        } else {
            return node;
        }
    }



    private static void printLeftView(com.dsa.tree.common.Node root) {

        if(root==null) {
            return;
        }
        Map<Integer, Integer> hMap = new HashMap<>();
        System.out.println(root.getData());
        hMap.put(0,1);
        printLeftView(root.getLeft(),1, hMap);
        printLeftView(root.getRight(),1, hMap);

    }

    private static void printLeftView(Node node, int h, Map<Integer, Integer> hMap) {
        if(node==null) {
            return;
        }
        if(hMap.get(h)==null) {
            System.out.println(node.getData());
            hMap.put(h,1);
        }
        printLeftView(node.getLeft(), h+1, hMap);
        printLeftView(node.getRight(), h+1, hMap);
    }


}
