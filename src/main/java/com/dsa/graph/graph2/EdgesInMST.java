package com.dsa.graph.graph2;

import java.util.*;

/**
 *  Edges in MST
 * Send Feedback
 * Edges in MST
 *
 * You are given a connected weighted undirected graph without any loops and multiple edges.
 *
 * Let us remind you that a graph's spanning tree is defined as an acyclic connected subgraph of the given graph that includes all of the graph's vertexes. The weight of a tree is defined as the sum of weights of the edges that the given tree contains. The minimum spanning tree (MST) of a graph is defined as the graph's spanning tree having the minimum possible weight. For any connected graph obviously exists the minimum spanning tree, but in the general case, a graph's minimum spanning tree is not unique.
 *
 * Your task is to determine the following for each edge of the given graph: whether it is either included in any MST, or included at least in one MST, or not included in any MST.
 *
 * Input
 *
 * The first line contains two integers n and m (2 ≤ n ≤ 10^5, ) — the number of the graph's vertexes and edges, correspondingly. Then follow m lines, each of them contains three integers — the description of the graph's edges as "ai bi wi" (1 ≤ ai, bi ≤ n, 1 ≤ wi ≤ 10^6, ai ≠ bi), where ai and bi are the numbers of vertexes connected by the i-th edge, wi is the edge's weight. It is guaranteed that the graph is connected and doesn't contain loops or multiple edges.
 *
 * Output
 *
 * Print m lines — the answers for all edges. If the i-th edge is included in any MST, print "any"; if the i-th edge is included at least in one MST, print "at least one"; if the i-th edge isn't included in any MST, print "none". Print the answers for the edges in the order in which the edges are specified in the input.
 *
 * Sample input 1
 *
 * 4 5
 * 1 2 101
 * 1 3 100
 * 2 3 2
 * 2 4 2
 * 3 4 1
 *
 * sample output 1
 *
 * none
 * any
 * at least one
 * at least one
 * any
 *
 * Sample input 2
 *
 * 3 3
 * 1 2 1
 * 2 3 1
 * 1 3 2
 *
 * sample output 2
 *
 * any
 * any
 * none
 *
 * sample input 3
 *
 * 3 3
 * 1 2 1
 * 2 3 1
 * 1 3 1
 *
 * sample output 3
 *
 * at least one
 * at least one
 * at least one
 *
 * Note
 *
 * In the second sample the MST is unique for the given graph: it contains two first edges.
 *
 * In the third sample any two edges form the MST for the given graph. That means that each edge is included at least in one MST.
 */
public class EdgesInMST {

    static class EdgeTopParents {

        int startTopParent;

        int endTopParent;

        public boolean equals(Object o) {
            EdgeTopParents edgeTopParents = (EdgeTopParents) o;
            if( edgeTopParents.startTopParent==startTopParent && edgeTopParents.endTopParent==endTopParent) {
                return true;
            }
            if(edgeTopParents.startTopParent==endTopParent&&edgeTopParents.endTopParent==startTopParent) {
                return true;
            }
            return false;
        }
        public int hashCode() {
            return ((Integer)startTopParent).hashCode()+((Integer)endTopParent).hashCode();
        }
    }


    static class Edge implements Comparable<Edge> {
        int startVertex;
        int endVertex;
        int weight;

        @Override
        public int compareTo(Edge o) {
            if(o==null) {
                return 0;
            }
            if(this.weight<o.weight) {
                return -1;
            } else if(this.weight>o.weight) {
                return 1;
            }
            return 0;
        }
        public String toString() {
            if(startVertex<endVertex) {
                return startVertex+" "+endVertex+" "+ weight;
            } else {
                return endVertex+" "+startVertex+" "+ weight;
            }
        }
    }


    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] edges = new Edge[m];
        Edge[] edgesOriginal = new Edge[m];
        for (int i=0;i<m;i++) {
            Edge edge = new Edge();
            edge.startVertex = in.nextInt()-1;
            edge.endVertex = in.nextInt()-1;
            edge.weight = in.nextInt();
            edges[i] = edge;
            edgesOriginal[i] = edge;
        }
        Arrays.sort(edges);
        int[] parents = new int[n];
        for (int i=0;i<n;i++) {
            parents[i] = i;
        }
        int count  = 0;
        int edgeNumber = 0;
        Map<Edge,String> edgeMap = new HashMap<>();
        while (count<n-1) {
            Edge edge = edges[edgeNumber];
            List<Edge> edgesWithSameWeight = getEdgeWithSameWeight(edges,edgeNumber, edge.weight);

            if(edgesWithSameWeight.size()==1) {
                int startParent = getTopParent(parents,edge.startVertex);
                int endParent = getTopParent(parents,edge.endVertex);
                if(startParent!=endParent) {
                    // when both vertex parents are not equal that means they are not part of
                    // same component or in other words edge is bridge between 2 components
                    edgeMap.put(edge,"any");
                    parents[startParent] = endParent;
                    count++;
                }
                edgeNumber++;
            } else {
                Map<EdgeTopParents, List<Integer>> edgeTopParentsMap = new HashMap();
                for(int i=0;i<edgesWithSameWeight.size();i++) {
                    edge = edgesWithSameWeight.get(i);
                    int startParent = getTopParent(parents,edge.startVertex);
                    int endParent = getTopParent(parents,edge.endVertex);
                    if(startParent!=endParent) {
                        // when both vertex parents are not equal that means they are not part of
                        // same component or in other words edge is bridge between 2 components
                        edgeMap.put(edge,"any");
                        EdgeTopParents edgeTopParents = new EdgeTopParents();
                        edgeTopParents.startTopParent = startParent;
                        edgeTopParents.endTopParent = endParent;
                        List<Integer> sameTopParentsIndexes = edgeTopParentsMap.get(edgeTopParents);
                        if(sameTopParentsIndexes==null) {
                            sameTopParentsIndexes = new ArrayList<>();
                        }
                        sameTopParentsIndexes.add(i);
                    }
                }
                for(List<Integer> sameIndexes : edgeTopParentsMap.values()) {
                    // use any index to connect the graph
                    edge = edgesWithSameWeight.get(sameIndexes.get(0));
                    int startParent = getTopParent(parents, edge.startVertex);
                    int endParent = getTopParent(parents, edge.endVertex);
                    if (startParent != endParent) {
                        // when both vertex parents are not equal that means they are not part of
                        // same component or in other words edge is bridge between 2 components
                        parents[startParent] = endParent;
                        count++;
                    }
                    if(sameIndexes.size()>1) {
                        // that means there are more than 1 edges which are bridge so mark all of them as
                        // at least one
                        for (Integer index : sameIndexes) {
                            edgeMap.put(edgesWithSameWeight.get(index),"at least one");
                        }

                    }
                }
                edgeNumber = edgeNumber+edgesWithSameWeight.size();
            }
        }
        for (Edge edge : edgesOriginal) {
            System.out.println(edgeMap.get(edge)==null?"none":edgeMap.get(edge));
        }
    }

    private static List<Edge> getEdgeWithSameWeight(Edge[] sortedEdges, int edgeNumber, int weight) {
        List<Edge> edgesWithSameWeight = new ArrayList<>();
        edgesWithSameWeight.add(sortedEdges[edgeNumber]);
        for(int i=edgeNumber+1;i<sortedEdges.length;i++) {
            if(sortedEdges[i].weight==weight) {
                edgesWithSameWeight.add(sortedEdges[i]);
            }
            break;
        }
        return edgesWithSameWeight;
    }

    private static int getTopParent(int[] parents, int vertex) {
        if(parents[vertex]==vertex) {
            return vertex;
        }
        return getTopParent(parents,parents[vertex]);
    }

}
