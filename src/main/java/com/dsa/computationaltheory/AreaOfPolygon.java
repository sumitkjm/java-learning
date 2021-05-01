package com.dsa.computationaltheory;

import java.util.Scanner;

public class AreaOfPolygon {

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
    static double area (Polygon polygon, int n) {
        double area  = 0;
        for (int i=1;i<n-1;i++) {
            double x1 = polygon.points[i].x - polygon.points[0].x;
            double y1 = polygon.points[i].y - polygon.points[0].y;
            double x2 = polygon.points[i+1].x - polygon.points[0].x;
            double y2 = polygon.points[i+1].y - polygon.points[0].y;

            double crossProduct = x1*y2 - y1*x2;
            area+=crossProduct/2;
        }
        return Math.abs(area);
    }

    public static void main(String[] args) {
        System.out.println("Enter the number of points for Polygon");
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        Polygon polygon = new Polygon(n);
        System.out.println("Enter the co-ordinates for the polygon");
        for (int i=0;i<n;i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            polygon.points[i].x = x;
            polygon.points[i].y = y;
        }
        System.out.println("Area of the polygon="+area(polygon,n));
    }
}
