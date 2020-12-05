package com.puzzle;

public class GreatestCommonDevisor {

    public static void main(String args[]) {

        // Greatest common devisor for given a and b
        long a = 154866576L; // 2388*124*523
        long b = 125255376L; // 2388*124*423

        long startMillSec = System.currentTimeMillis();
        long gCommonDivNum = computeGrtComDivOpt1(a,b);
        long endMilliSec = System.currentTimeMillis();
        System.out.println("Compute By computeGrtComDivOpt1:"+gCommonDivNum);
        System.out.println("TimeTaken computeGrtComDivOpt1:"+(endMilliSec-startMillSec));

        startMillSec = System.currentTimeMillis();
        gCommonDivNum = computeGrtComDiv(a,b);
        endMilliSec = System.currentTimeMillis();
        System.out.println("Compute By computeGrtComDiv:"+gCommonDivNum);
        System.out.println("TimeTaken computeGrtComDiv:"+(endMilliSec-startMillSec));



    }

    private static long computeGrtComDivOpt1(long a, long b) {
        if(a==b) {
            return a;
        }
        long gCommonDivNum = 1;
        //Decide shorter one
        long smallerNum = a;
        if(a>b) {
            smallerNum = b;
        }

        for (long i = smallerNum;i>=2;i--) {
            if(a%i==0 && b%i==0) {
                gCommonDivNum = i;
                break;
            }
        }

        return gCommonDivNum;
    }


    private static long computeGrtComDiv(long a, long b) {
        if(a==b) {
            return a;
        }
        long gCommonDivNum = 1;
        //Decide shorter one
        long smallerNum = a;
        if(a>b) {
            smallerNum = b;
        }

        for (long i = 2; i<=smallerNum;i++) {
            if(a%i==0 && b%i==0) {
                gCommonDivNum = i;
            }
        }

        return gCommonDivNum;
    }


}
