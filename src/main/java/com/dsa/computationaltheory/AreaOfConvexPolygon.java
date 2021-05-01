package com.dsa.computationaltheory;

import java.util.Scanner;

/**
 *  Area Of Convex Polygon
 * Send Feedback
 * A convex polygon is a set of n vertices that are joined by n edges, such that no two edges intersect and all angles are less than 180 degrees. We can represent a polygon by listing all the vertices, starting at one vertex and following the edges until that vertex is reached again. Thus, element 0 in the array represents the first vertex. The first vertex is connected to the second vertex (element 1), the second vertex is connected to the third vertex (element 2) and so on. The last element represents the last vertex, which is connected to the first vertex.
 * Given the vertices of a polygon, where the x-coordinate of vertex i is element i of int[] x and its y-coordinate is element i of int[] y, return its exact area.
 * Input Format
 *
 *  Integer N
 *  Two arrays x and y of size N
 *
 * Output Format
 *
 * Area of polygon
 *
 * Notes
 *
 * Get the integer part of the area. (It can be long)
 * So get your answer in double, and typecast it into long.
 *
 * Constraints
 *
 * x and y will have the same number of elements.
 * x will have between 3 and 50 elements inclusive.
 * y will have between 3 and 50 elements inclusive.
 * each element in x will be between -10000 and 10000 inclusive.
 * each element in y will be between -10000 and 10000 inclusive.
 * the represented polygon will NOT intersect itself.
 * the represented polygon will NOT have any angles equal to or greater than 180 degrees.
 *
 * Sample Input
 *
 * 3
 * 0 0 1
 * 0 1 0
 *
 * Sample Output
 *
 * 0
 *
 * (Exact area was 0.5 . In Integer It is 0)
 */
public class AreaOfConvexPolygon {

    static class Point {
        public int x = 0;
        public int y = 0;
    }
    static class Polygon {

        public Point[] points = null;
        public Polygon(int n) {
            this.points = new Point[n];
            for (int i=0;i<n;i++) {
                points[i] = new Point();
            }
        }
    }
    static int area (Polygon polygon, int n) {
        double area  = 0;
        for (int i=1;i<n-1;i++) {
            double x1 = polygon.points[i].x - polygon.points[0].x;
            double y1 = polygon.points[i].y - polygon.points[0].y;
            double x2 = polygon.points[i+1].x - polygon.points[0].x;
            double y2 = polygon.points[i+1].y - polygon.points[0].y;

            double crossProduct = x1*y2 - y1*x2;
            area+=crossProduct/2;
        }
        return (int)Math.abs(area);
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] xArr = new int[n];
        int[] yArr = new int[n];
        for (int i =0;i<n;i++) {
            xArr[i] = in.nextInt();
        }
        for (int i=0;i<n;i++) {
            yArr[i] = in.nextInt();
        }
        Polygon polygon = new Polygon(n);
        for (int i=0;i<n;i++) {
            polygon.points[i].x = xArr[i];
            polygon.points[i].y = yArr[i];
        }
        System.out.println(area(polygon,n));
    }
}
