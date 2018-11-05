package com.dsa.sorting;

/**
 * Created by skumar6 on 9/10/17.
 */
public class QuickSort {

    private void sort(int[] arr) {
        quickSort(arr,0,arr.length-1);
    }

    private void quickSort(int [] arr, int low, int high) {

        int pivotIndex = (low + high)/2;
        int pivot = arr[pivotIndex];
        int i = low;
        int j = high;
        while(i<=j) {

            while(arr[i]<pivot) {
                i++;
            }

            while(arr[j]>pivot) {
                j--;
            }
            if(i<=j) {
                exchange(arr,i,j);
                i++;
                j--;
            }
        }

        if(low<j) {
            quickSort(arr, low, j);
        }
        if(i<high) {
            quickSort(arr,i, high);
        }

    }

    private void exchange(int [] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String [] args) {
        int [] sortArray = {2,4,1,67,1,8,10,100,30};
        new QuickSort().sort(sortArray);
        for (int num:sortArray
             ) {
            System.out.println(num);
        }
    }
}
