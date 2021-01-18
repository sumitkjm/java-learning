package com.dsa.greedy;

import java.lang.annotation.ElementType;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *  Perimeter with conditions
 * Send Feedback
 * Aahad gives an array of integers and asks Harshit to find which three elements form a triangle (non-degenerate). The task seems easy to Harshit.
 * So, Aahad adds some conditions to this task -
 * 1. Find the triangle with maximum perimeter
 * 2. If there are two or more combinations with same value of maximum perimeter, then find the one with the longest side.
 * 3.If there are more than one combinations which satisfy all the above conditions the find with maximum longest minimum side.
 * Input Format
 *
 * The First line contains no of elements of array: N
 * Each T lines contains N space-separated integers: A [i]
 *
 * Output Format
 *
 * The output contains three space-separated elements that denote the length of the sides of triangle. If no such triangle is possible, then print -1.
 *
 * Constraints
 *
 * 1 =< N <= 10^5
 * 1 <= A[i] <= 10^9
 * Time Limit: 1 second
 *
 * Sample Input1:
 *
 * 5
 * 1 1 1 3 3
 *
 * Sample Output1:
 *
 * 1 3 3
 *
 * Sample Input2:
 *
 * 3
 * 2 2 4
 *
 * Sample Output3:
 *
 * -1
 *
 * Explaination
 *
 * In the First Sample case, the elements that form a triangle with maximum perimeter is 1,3,3.
 * In the Second Sample case, the elements that can form a triangle are degenerate, so, we printed -1.
 */
public class PermiterWithConditions {

    static class Element implements Comparable<Element>{
        long value;


        @Override
        public int compareTo(Element o) {
            if(value>o.value) {
                return -1;
            } else if(value<o.value){
                return 1;
            }
            return 0;
        }
    }

    static class Traingle {

        long a;
        long b;
        long c;

    }

    public static void main(String[] args) {
        /* Your class should be named Main.
         * Read input as specified in the question.
         * Print output as specified in the question.
         */

        // Write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Element[] arr = new Element[n];
        for (int i=0;i<n;i++) {
            Element element = new Element();
            element.value = in.nextLong();
            arr[i] = element;
        }
        Arrays.sort(arr);
        Traingle maxTriangle = new Traingle();
        long maxPerimeter = 0;
        if(arr[0].value<arr[1].value+arr[2].value) {
            maxPerimeter = arr[0].value+arr[1].value+arr[2].value;
            maxTriangle.a = arr[0].value;
            maxTriangle.b = arr[1].value;
            maxTriangle.c = arr[2].value;
        }
        if(n>3) {
            for (int i = 3; i < n; i++) {
                boolean isAllLess = false;
                for (int j = i - 1; j > 0; j--) {
                    for (int k = j - 1; k >= 0; k--) {
                        if (arr[k].value < arr[j].value + arr[i].value) {
                            if (arr[i].value + arr[j].value + arr[k].value > maxPerimeter) {
                                isAllLess = true;
                                maxTriangle.a = arr[k].value;
                                maxTriangle.b = arr[j].value;
                                maxTriangle.c = arr[i].value;
                            } else if (arr[i].value + arr[j].value + arr[k].value == maxPerimeter) {
                                isAllLess = true;
                                if ((maxTriangle.a < arr[k].value) ||
                                        (maxTriangle.a == arr[k].value && maxTriangle.c < arr[i].value)) {
                                    maxTriangle.a = arr[k].value;
                                    maxTriangle.b = arr[j].value;
                                    maxTriangle.c = arr[i].value;
                                }
                            }
                        }
                    }
                }
                if (isAllLess) {
                    break;
                }
            }
        }
        if(maxPerimeter>0) {
            System.out.println(maxTriangle.c + " " + maxTriangle.b + " " + maxTriangle.a);
        } else {
            System.out.println(-1);
        }
    }

}
