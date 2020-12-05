package com.dsa.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogestConsecutiveSubsequence {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = longestConsecutiveIncreasingSequence(new int[]{3,7,2,1,9,8,41});
        for (Integer el : arrayList) {
            System.out.print(el+" ");
        }
    }


    public static ArrayList<Integer> longestConsecutiveIncreasingSequence(int[] arr) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        Map<Integer, Boolean> map = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for (int i=0;i<arr.length;i++) {
            map.put(arr[i],true);
        }

        for(int i=-0;i< arr.length;i++) {
            map2.put(arr[i],i);
        }

        int start = arr[0];
        int maxLength = 0;
        int end = arr[0];
        for(int i=0;i<arr.length;i++) {
            int startL = arr[i];
            int length = 1;
            int endL = arr[i];
            if(!map.get(arr[i])) {
                continue;
            } else {
                map.put(arr[i],false);
            }
            int ele = arr[i];
            while(true) {
                if(map.get(++ele)!=null) {
                    length++;
                    endL = ele;
                    map.put(ele,false);
                } else {
                    break;
                }
            }
            ele = arr[i];
            while(true) {
                if(map.get(--ele)!=null) {
                    length++;
                    startL = ele;
                    map.put(ele,false);
                } else {
                    break;
                }
            }
            if(maxLength<length|| (maxLength==length && map2.get(startL)< map2.get(start))) {
                maxLength = length;
                start = startL;
                end = endL;
            }
        }
        ArrayList<Integer> output = new ArrayList<>();
        output.add(start);
        output.add(end);
        return output;
    }

}
