package com.geeksforgeeks.bit;

/**
 * Given an array where every element occurs three times, except one element which occurs only once. Find the element that occurs once. Expected time complexity is O(n) and O(1) extra space.
 *
 * Examples:
 *
 *     Input: arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3}
 *     Output: 2
 *     In the given array all element appear three times except 2 which appears once.
 *
 *     Input: arr[] = {10, 20, 10, 30, 10, 30, 30}
 *     Output: 20
 *     In the given array all element appear three times except 20 which appears once.
 */
public class FindDistinct {

    public static void main(String[] args) {
        int[] arr = {12, 1, 12, 3, 12, 1, 1, 5, 3, 3};
        int ones = 0;
        int twos = 0;
        int common_bits = 0;
        for (int i=0;i<arr.length;i++) {
            twos |= (ones & arr[i]);
            ones ^=arr[i];
            common_bits = ~(ones & twos);
            ones&=common_bits;
            twos&=common_bits;
        }
        System.out.println(ones);
    }

}
