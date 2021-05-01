package com.dsa.graph.graph2;

import java.util.*;

/**
 *  Connected horses
 * Send Feedback
 *
 * You all must be familiar with the chess-board having
 * 8*8 squares of alternate black and white cells. Well, here
 * we have for you a similar
 * N*M size board with similar arrangement of black and white
 * cells.
 * A few of these cells have Horses placed over them.
 * Each horse is unique. Now these horses are not the
 * usual horses which could jump to any of the
 * 8 positions they usually jump in. They can move only if there is another horse on one of the 8-positions that it can     go to usually and then both the horses will swap their positions. This swapping can happen infinitely times.
 * A photographer was assigned to take a picture of all the different ways that the horses occupy the board! Given the state of the board, calculate answer. Sincethis answer may be quite large, calculate in modulo
 * 10^9+7
 *
 * Input:
 *
 * First line contains
 * T which is the number of test cases.
 * T test cases follow first line of each containing three integers
 * N, M and Q where
 * N,M is the size of the board and
 * Q is the number of horses on it.
 *
 * Q lines follow each containing the 2 integers
 * X and Y which are the coordinates of the Horses.
 *
 * Output:
 *
 * For each test case, output the number of photographs taken by photographer.
 *
 * Constraints:
 *
 *  1<=T<=10
 *  1<=N,M<=1000
 *  1<=Q<=N*M
 *
 * SAMPLE INPUT
 *
 * 2
 * 4 4 4
 * 1 1
 * 1 2
 * 3 1
 * 3 2
 * 4 4 4
 * 1 1
 * 1 2
 * 3 1
 * 4 4
 *
 * SAMPLE OUTPUT
 *
 * 4
 * 2
 */
public class ConnectedHorses {

    static class Cell {
        Integer i;
        Integer j;
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int l=0;l<t;l++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int qn = in.nextInt();
            int [][] board = new int[n][m];
            Vector<Cell>[][] edges = new Vector[n][m];
            for (int qi=0;qi<qn;qi++) {
                int i = in.nextInt();
                int j = in.nextInt();
                board[i-1][j-1] = 1;
            }
            for (int i=2;i<n;i++) {
                for (int j=0;j<m;j++) {
                    if (board[i][j] == 0) {
                        continue;
                    }
//                        Vector<Integer> endPoint = new
                    // make an edge between p,q and r,s and vice versa
                    if (j > 0 && board[i - 2][j - 1] == 1) {
                        int p = i - 2;
                        int q = j - 1;
                        int r = i;
                        int s = j;
                        makeAnEdge(edges, p, q, r, s);
                        makeAnEdge(edges, r, s, p, q);
                    }
                    if (j < m - 1 && board[i - 2][j + 1] == 1) {
                        int p = i - 2;
                        int q = j + 1;
                        int r = i;
                        int s = j;
                        makeAnEdge(edges, p, q, r, s);
                        makeAnEdge(edges, r, s, p, q);
                    }
                }
            }
            List<List<Cell>> componentLists = new ArrayList<>();
            visitGraph(edges,n,m,componentLists);
            int mod = (int)Math.pow(10,9) + 7;
            long ans = 1;
            for (List<Cell> components : componentLists) {
                ans = (ans%mod * getFactorial(components.size(),mod)%mod)%mod;
            }
            System.out.println(ans);
        }
    }

    private static int getFactorial(int n, int mod) {
        int ans = 1;
        for (int i=2;i<=n;i++) {
            ans = (ans%mod * i%mod)%mod;
        }
        return ans;
    }

    public static void visitGraph(Vector<Cell>[][] edges, int n, int m, List<List<Cell>> componentLists) {
        boolean [][] visited = new boolean[n][m];
        Queue<Cell> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j=0;j<m;j++) {
                if (visited[i][j]) {
                    continue;
                }
                Cell cell = new Cell();
                cell.i = i;
                cell.j =j;
                queue.offer(cell);
                visited[i][j] = true;
                List<Cell> components = new ArrayList<>();
                componentLists.add(components);
                components.add(cell);
                visitGraphBFS(edges, visited, queue, components);
//                Collections.sort(components);
            }
        }
    }

    public static void visitGraphBFS(Vector<Cell>[][] edges, boolean[][] visited, Queue<Cell> queue,List<Cell> components) {
        while (!queue.isEmpty()){
            Cell vertex = queue.poll();
            // get all edges starting from vertex
            Vector<Cell> edgesFromVertex = edges[vertex.i][vertex.j];
            if(edgesFromVertex==null) {
                continue;
            }
            for (Cell v : edgesFromVertex) {
                if(visited[v.i][v.j]) {
                    continue;
                }
                queue.offer(v);
                components.add(v);
                visited[v.i][v.j] = true;
            }
        }
    }

    private static void makeAnEdge(Vector<Cell>[][] edge, int p, int q, int r, int s) {
        Vector<Cell> swapPoint = edge[p][q];
        if(swapPoint==null) {
            swapPoint = new Vector<>();
            edge[p][q] = swapPoint;
        }
        Cell cell = new Cell();
        cell.i = r;
        cell.j = s;
        swapPoint.add(cell);
    }

}
