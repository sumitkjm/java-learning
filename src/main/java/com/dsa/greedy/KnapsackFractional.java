package com.dsa.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  Fractional Knapsack
 * Send Feedback
 * You want to paint your house. The total area of your house is D units. There are a total of N workers. The ith worker is available after time Ti, has hiring cost Xi and speed Yi. This means he becomes available for hiring from time Ti and remains available after that. Once available, you can hire him with cost Xi, after which he will start painting the house immediately, covering exactly Yi units of house with paint per time unit. You may or may not hire a worker and can also hire or fire him at any later point of time. However, no more than 1 worker can be painting the house at a given time.
 * Since you want the work to be done as fast as possible, figure out a way to hire the workers, such that your house gets painted at the earliest possible time, with minimum cost to spend for hiring workers.
 * Note: You can hire a previously hired worker without paying him again.
 * Input
 *
 * The first line of input contains two integers "N D", the number of workers and the area of your house respectively. The ith of the next N lines denotes the ith worker, and contains three integers "Ti Xi Yi", described in the statement.
 *
 * Output
 *
 * Output one integer, the minimum cost that you can spend in order to get your house painted at the earliest.
 *
 * Constraints
 * 1 ≤ N, T, X, Y ≤ 10^5
 * 1 ≤ D ≤ 10^11
 * Sample Input
 *
 * 3 3
 * 1 1 1
 * 2 2 2
 * 3 1 5
 *
 * Sample Output
 *
 * 3
 */
public class KnapsackFractional {

    static class Worker implements Comparable<Worker> {
        public int getTi() {
            return ti;
        }

        public int getXi() {
            return xi;
        }

        public int getYi() {
            return yi;
        }

        public void setTi(int ti) {
            this.ti = ti;
        }

        // available time
        int ti;

        // Cost of worker
        int xi;

        // speed or area covered by worker
        int yi;

        public Worker(int ti, int xi, int yi) {
            this.ti=ti;
            this.xi =xi;
            this.yi = yi;
        }

        public Worker() {

        }

        @Override
        public int compareTo(Worker o) {
            if(o==null) {
                return 0;
            }
            if(ti>o.getTi()) {
                return 1;
            } else if(ti<o.getTi()) {
                return -1;
            } else {
                if(yi>o.yi) {
                    return -1;
                } else if(yi<o.yi) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }


    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        // N = number of works
        int n = in.nextInt();
        // D = area
        long d = in.nextLong();
        Worker[] workers = new Worker[n];
        for (int i=0;i<n;i++) {
            Worker worker = new Worker(in.nextInt(),in.nextInt(),in.nextInt());
            workers[i] = worker;
        }
        System.out.println(getMinCost(workers,n,d));
    }

    public static int getMinCost(Worker[] workers,int n , long d) {
        Arrays.sort(workers);
//        for (int i=0;i<n;i++) {
//            System.out.println(workers[i].ti+" "+workers[i].getXi()+" "+workers[i].getYi());
//        }
        int minCost = 0;
        long area = 0;
        float maxSpeed = 0;
        int previousTime = -1;
        for (int i=0;i<n-1;i++) {
            if(area==d) {
                break;
            }
            if(previousTime == workers[i].ti) {
                // same time multiple workers available so we have choosen first one
                // sorted based on high speed first
                continue;
            }
            int nextAvailableWorkerTime = getNextAvailableTime(i,workers);
            // any new worker which has more speed than existing workers
            // then updated the maxSpeed
            if(workers[i].yi>maxSpeed) {
                maxSpeed = workers[i].yi;
                // let this worker paint until next Available worker
                for (int j=workers[i].ti;j<nextAvailableWorkerTime;j++) {
                    if(area==d) {
                        break;
                    }
                    if (area + workers[i].yi <= d) {
                        if(j==workers[i].ti) {
                            minCost += workers[i].xi;
                        }
                        // cost need to be added only once for same worker
                        area += workers[i].yi;
                    } else {
                        minCost += (d - area) * (workers[i].xi / workers[i].yi);
                        area = d;
                    }
                }

            } else {
                // take existing worker so that cost can be saved until new worker is available.
                for (int j=workers[i].ti;j<nextAvailableWorkerTime;j++) {
                    if(area==d) {
                        break;
                    }
                    if(area+maxSpeed<=d) {
                        area+=maxSpeed;
                    } else {
                        area = d;
                    }
                }
            }
            previousTime = workers[i].ti;
        }
        System.out.println("area="+area);
        return minCost;
    }

    private static int getNextAvailableTime(int currentIndex, Worker[] workers) {
        int i = currentIndex;
        while (i+1<workers.length&&workers[currentIndex].ti==workers[++i].ti);
        return workers[i].ti;
    }

}


/**
 *  C++ code shared by TA as above java code is giving wrong answer
 * #include<bits/stdc++.h>
 * using namespace std;
 *
 * int main(){
 *
 *     long int n, d;
 *   cin >> n >> d;
 *    pair<int, int> painters[100001];
 *     vector<int> helper;
 *  int a, b, c;
 *  for( int i =0; i < n; i++){
 *      cin >> a >> b >> c;
 *      if(painters[a].first == 0){
 *          painters[a].first = b;
 *          painters[a].second = c;
 *           helper.push_back(a);
 *      }
 *      else{
 *          if(c > painters[a].second){
 *          painters[a].first = b;
 *          painters[a].second = c;
 *          }
 *          else if( c == painters[a].second && b < painters[a].first ){    // imp if both time and speed are equal
 *              painters[a].first = b;
 *          }
 *      }
 *  }
 *  sort(helper.begin(), helper.end());
 *
 *      int index = helper.at(0);
 *
 * //      for( int i = 0; i < helper.size(); i++){
 * //           index = helper.at(i);
 * //          cout << index << " "<< painters[index].first << " "<<  painters[index].second<<endl;
 * //      }
 *
 *   //  index = helper.at(0);
 *     long ans = painters[index].first, maximum = painters[index].second;
 *      d -= maximum;
 *
 *     for( int i = 1; i < helper.size(); i++){
 *           if(d > 0){
 *            //   cout << painters[i].first << endl;
 *             //  cout << ans << endl;
 *               index = helper.at(i);
 *
 *             if(painters[index].second > maximum){
 *             //
 *                 maximum = painters[index].second;
 *                 d -= maximum;
 *                 ans += painters[index].first;
 *             }
 *             else
 *               d -= maximum;
 *         continue;
 *         }
 *         break;
 *     }
 *     cout << ans << endl;
 *     return 0;
 * }
 */