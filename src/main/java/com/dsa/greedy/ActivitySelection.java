package com.dsa.greedy;

import java.util.*;

/**
 *  Activity Selection
 * Send Feedback
 * You are given n activities with their start and finish times. Select the maximum number of activities that can be performed by a single person, assuming that a person can only work on a single activity at a time.
 * Input
 *
 * The first line of input contains one integer denoting N.
 *
 * Next N lines contains two space separated integers denoting the start time and finish time for the ith activity.
 * Output
 *
 * Output one integer, the maximum number of activities that can be performed
 *
 * Constraints
 * 1 ≤ N ≤ 10^6
 * 1 ≤ ai, di ≤ 10^9
 * Sample Input
 *
 * 6
 * 1 2
 * 3 4
 * 0 6
 * 5 7
 * 8 9
 * 5 9
 *
 * Sample Output
 */
public class ActivitySelection {

    public static void main(String[] args) {
        // Write your code here

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if(n==0) {
            System.out.println(0);
        }

        int []startArr = new int[n];
        int []endArr = new int[n];
        for(int i=0;i<n;i++) {
            startArr[i] = in.nextInt();
            endArr[i] = in.nextInt();
        }

        int[] endArraySorted = Arrays.copyOf(endArr,n);

        Arrays.sort(endArraySorted);
        Map<Integer, List<Integer>> endToStartTimeMap = new HashMap<>();
        for (int i=0;i<n;i++) {
            List<Integer> arrayList = endToStartTimeMap.get(endArr[i]);
            if(arrayList == null) {
                arrayList = new ArrayList<>();
                endToStartTimeMap.put(endArr[i],arrayList);
            }
            arrayList.add(startArr[i]);
        }

        int maxActivities = 1;
        int prevEndTime = endArraySorted[0];
        for (int i=1;i<n;i++) {
            // you can only pick one task with finishing on same time
            if(endArraySorted[i]==endArraySorted[i-1]) {
                continue;
            }
            //get start time of corresponding finish time
            int startTime = getStartTime(endToStartTimeMap,endArraySorted[i],prevEndTime);
            // you can pick this task if it starts after previous task end time
            if(startTime!=-1) {
                maxActivities++;
                prevEndTime = endArraySorted[i];
            }
        }
        System.out.println(maxActivities);

    }

    public static int getStartTime(Map<Integer, List<Integer>> endToStartTimeMap, int endTime, int previousEndTime) {
        int startTime = -1;
        List<Integer> startTimes = endToStartTimeMap.get(endTime);
        for (Integer startTimeL : startTimes) {
            if (startTimeL >= previousEndTime) {
                startTime = startTimeL;
            }
        }
        return startTime;
    }

}
