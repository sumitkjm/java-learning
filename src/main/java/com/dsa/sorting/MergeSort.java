package com.dsa.sorting;

public class MergeSort {

    public static void main(String [] args) {
        long a[] = {52244275,123047899,493394237,922363607,378906890,188674257,222477309,902683641,860884025,339100162};
        mergeSort(0,a.length,a);
        for(int i=0;i<a.length;i++) {
            System.out.print(a[i]+" ");
        }
    }

    private static void mergeSort(int s, int e, long[] arr) {
        if(arr==null||arr.length==0) {
            return;
        }
        if((e-s)==1||(e-s)==0) {
            return;
        }
        int mid = (s+e)/2;
        mergeSort(s,mid,arr);
        mergeSort(mid,e,arr);
        long [] tempArr = new long[e-s];
        int i=s,j=mid,k=0;
        while (j<e&&i<mid) {
            if(arr[i]>arr[j]) {
                tempArr[k] = arr[j];
                j++;
            } else {
                tempArr[k] = arr[i];
                i++;
            }
            k++;
        }
        while (j<e) {
            tempArr[k] = arr[j];
            k++;
            j++;
        }
        while (i<mid) {
            tempArr[k] = arr[i];
            k++;
            i++;
        }
        for(i=0,j=s;i<tempArr.length;i++,j++) {
            arr[j] = tempArr[i];
        }
    }

}
