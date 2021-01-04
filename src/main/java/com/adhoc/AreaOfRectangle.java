package com.adhoc;

import java.util.Scanner;

public class AreaOfRectangle {

    // input format
    // first line number of pairs (x,y)
    // sample input /ouput
    //
 // input
//            5
//            2 10
//            4 4
//            2 2
//            8 8
//            6
//     output
//            68
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] height = new int[500000+2];
        int maxX = 0;
        for(int i=0;i<n;i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if(height[x/2]<y) {
                height[x/2] = y;
            }
            if((x/2)>maxX) {
                maxX = x/2;
            }
        }
        int area = 0;
        for(int i=maxX;i>0;i--) {
            if(height[i]<height[i+1]) {
                height[i] = height[i+1];
            }
            area+=height[i];
        }
        System.out.println(area*2);
    }

}
