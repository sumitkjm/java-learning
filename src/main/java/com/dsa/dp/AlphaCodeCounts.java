package com.dsa.dp;

import java.util.Scanner;

public class AlphaCodeCounts {

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String input = in.nextLine();
            if(input.equals("0")) {
                break;
            }
            int [] arr = new int[input.length()];
            for(int i=1;i<=input.length();i++) {
                arr[i-1] = Integer.valueOf(input.substring(i-1,i));
            }
            int [] output = new int[input.length()+1];
            System.out.println(getPossibleCodes(arr,arr.length, output));
        }


    }

    public static int getPossibleCodes(int [] input, int size, int [] output) {
        int m = (int)Math.pow(10,9) +7;
        if(size==0 || size == 1) {
            output[size] = 1;
            return 1;
        }
        if(output[size]>0) {
            return output[size];
        }
        if(input[size-1]==0 ) {
            if(input[size-2] == 0 || input[size-2]*10>20) {
                output[size] = 0;
                return 0;
            }  else {
                int count = getPossibleCodes(input, size-2,output);
                output[size] = count%m;
                return output[size];
            }
        }
        int count = getPossibleCodes(input, size-1,output);
        if(count==0) {
            output[size] = 0;
            return 0;
        }
        if(input[size-2]!=0 && ((input[size-2]*10) + input[size-1]) <=26) {
            count += getPossibleCodes(input, size-2, output);
        }
        output[size] = count%m;
        return output[size];
    }


}
