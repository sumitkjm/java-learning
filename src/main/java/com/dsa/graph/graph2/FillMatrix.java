package com.dsa.graph.graph2;

import java.util.*;

/**
 *  FILLMTR
 * Send Feedback
 * Fill The Matrix
 *
 * A matrix B (consisting of integers) of dimension N × N is said to be good if there exists an array A (consisting of integers) such that B[i][j] = |A[i] - A[j]|, where |x| denotes absolute value of integer x.
 *
 * You are given a partially filled matrix B of dimension N × N. Q of the entries of this matrix are filled by either 0 or 1. You have to identify whether it is possible to fill the remaining entries of matrix B (the entries can be filled by any integer, not necessarily by 0 or 1) such that the resulting fully filled matrix B is good.
 *
 * Input
 *
 * The first line of the input contains an integer T denoting the number of test cases.
 *
 * The first line of each test case contains two space separated integers N, Q.
 *
 * Each of the next Q lines contain three space separated integers i, j, val, which means that B[i][j] is filled with value val.
 *
 * Output
 *
 * For each test case, output "yes" or "no" (without quotes) in a single line corresponding to the answer of the problem.
 *
 * Constraints
 *
 * 1 ≤ T ≤ 10^6
 * 2 ≤ N ≤ 10^5
 * 1 ≤ Q ≤ 10^6
 * 1 ≤ i, j ≤ N
 * 0 ≤ val ≤ 1
 * Sum of each of N, Q over all test cases doesn't exceed 106
 *
 * Input
 *
 * 4
 * 2 2
 * 1 1 0
 * 1 2 1
 * 2 3
 * 1 1 0
 * 1 2 1
 * 2 1 0
 * 3 2
 * 2 2 0
 * 2 3 1
 * 3 3
 * 1 2 1
 * 2 3 1
 * 1 3 1
 *
 * Output
 *
 * yes
 * no
 * yes
 * no
 */
public class FillMatrix {

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-->0) {
            int n = in.nextInt();
            int q = in.nextInt();
            int [][] matrix = new int[n][n];
            for (int i=0;i<n;i++) {
                Arrays.fill(matrix[i],-1);
            }
            Vector<Integer>[] edges = new Vector[n];
            Set<Integer> equalVertexComponent = new HashSet<>();
            while (q-->0) {
                int i = in.nextInt() - 1;
                int j = in.nextInt() - 1;
                int v = in.nextInt();
                matrix[i][j] = v;
                if(v==0) {
                    equalVertexComponent.add(i);
                    equalVertexComponent.add(j);
                    continue;
                }
                Vector<Integer> edge = edges[i];
                if (edge == null) {
                    edge = new Vector<>();
                    edges[i] = edge;
                }
                Vector<Integer> edge1 = edges[j];
                if (edge1 == null) {
                    edge1 = new Vector<>();
                    edges[j] = edge1;
                }
                edges[i].add(j);
                edges[j].add(i);
            }
            boolean basicValidation = true;
            for(int c=0;c<n;c++) {
                if(matrix[c][c]!=-1&&matrix[c][c]!=0) {
                    basicValidation = false;
                }
            }
            if(!basicValidation) {
                System.out.println("no");
                continue;
            }
            for(int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    if(matrix[i][j]==-1||matrix[j][i]==-1) {
                        continue;
                    }
                    if(matrix[i][j]!=matrix[j][i]) {
                        basicValidation = false;
                        break;
                    }
                }
                if(!basicValidation) {
                    break;
                }
            }
            if(!basicValidation) {
                System.out.println("no");
                continue;
            }
            if(equalVertexComponent.size()>0) {
                int baseVertex = -1;
                for (int i = 0; i < n; i++) {
                    if (edges[i] == null) {
                        continue;
                    }
                    if (equalVertexComponent.contains(i)) {
                        if(baseVertex==-1) {
                            baseVertex = i;
                        } else {
                            edges[baseVertex] = edges[i];
                            Vector<Integer> neighbours = edges[i];
                            Vector<Integer> neighboursCopy = new Vector<>();
                            neighboursCopy.addAll(neighbours);
                            for(Integer v : neighboursCopy) {
                                if(edges[v]==null) {
                                    edges[v] = new Vector<>();
                                }
                                edges[v].remove((Integer) i);
                                edges[v].add(baseVertex);
                            }
                            edges[i] = null;
                        }
                    }

                }
            }
            if(isBiPartiteGraph(n,edges,equalVertexComponent)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }

    }

    public static boolean isBiPartiteGraph(int n, Vector<Integer>[] edges, Set<Integer> component) {
        if(n==0) {
            return true;
        }
        for(int i=0;i<n;i++) {
            if(component.contains(i)&& edges[i]==null) {
                continue;
            }
            Queue<Integer> queue = new ArrayDeque<>();
            Set<Integer>[] sets = new HashSet[2];
            sets[0] = new HashSet<>();
            sets[1] = new HashSet<>();
            sets[0].add(i);
            queue.add(i);
            while (!queue.isEmpty()) {
                Integer vertex = queue.poll();
                Vector<Integer> neighbors = edges[vertex];
                int currentSet = sets[0].contains(vertex) ? 0 : 1;
                if(neighbors!=null) {
                    for (Integer neighbor : neighbors) {
                        if (!sets[0].contains(neighbor) && !sets[1].contains(neighbor)) {
                            sets[1 - currentSet].add(neighbor);
                            queue.add(neighbor);
                        } else if (sets[currentSet].contains(neighbor)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


}
