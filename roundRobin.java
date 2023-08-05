// Java program to implement Process schedulling using Round Robin

import java.util.Scanner;

class Process {
    int AT, BT, WT, FT, TAT, pos;
    int[] ST;

    Process() {
        ST = new int[20];
    }
}

public class roundRobin {
    static int quant;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, i, j;

        // Taking Input
        System.out.print("Enter the no. of processes: ");
        n = scanner.nextInt();
        Process[] p = new Process[n];

        System.out.println("Enter the quantum:");
        quant = scanner.nextInt();

        System.out.println("Enter the process numbers:");
        for (i = 0; i < n; i++) {
            p[i] = new Process();
            p[i].pos = scanner.nextInt();
        }

        System.out.println("Enter the Arrival time of processes:");
        for (i = 0; i < n; i++)
            p[i].AT = scanner.nextInt();

        System.out.println("Enter the Burst time of processes:");
        for (i = 0; i < n; i++)
            p[i].BT = scanner.nextInt();

        // Declaring variables
        int c = n;
        int[][] s = new int[n][20];
        float time = 0;
        float mini = Integer.MAX_VALUE;
        float[] b = new float[n];
        float[] a = new float[n];

        // Initializing burst and arrival time arrays
        int index = -1;
        for (i = 0; i < n; i++) {
            b[i] = p[i].BT;
            a[i] = p[i].AT;
            for (j = 0; j < 20; j++) {
                s[i][j] = -1;
            }
        }

        int tot_wt = 0;
        int tot_tat = 0;
        boolean flag = false;

        while (c != 0) {
            mini = Integer.MAX_VALUE;
            flag = false;

            for (i = 0; i < n; i++) {
                float ptime = time + 0.1f;
                if (a[i] <= ptime && mini > a[i] && b[i] > 0) {
                    index = i;
                    mini = a[i];
                    flag = true;
                }
            }
            // if at = 1 then loop gets out hence set flag to false
            if (!flag) {
                time++;
                continue;
            }

            // calculating start time
            j = 0;
            while (s[index][j] != -1) {
                j++;
            }

            if (s[index][j] == -1) {
                s[index][j] = (int) time;
                p[index].ST[j] = (int) time;
            }

            if (b[index] <= quant) {
                time += b[index];
                b[index] = 0;
            } else {
                time += quant;
                b[index] -= quant;
            }

            if (b[index] > 0) {
                a[index] = time + 0.1f;
            }

            // calculating arrival, burst, final times
            if (b[index] == 0) {
                c--;
                p[index].FT = (int) time;
                p[index].WT = p[index].FT - p[index].AT - p[index].BT;
                tot_wt += p[index].WT;
                p[index].TAT = p[index].BT + p[index].WT;
                tot_tat += p[index].TAT;
            }
        } // end of while loop

        // Printing output
        System.out.println("Process number\tArrival time\tBurst time\tStart time\t\tcompletion time\t\tWait Time\t\tTurnAround Time");
        for (i = 0; i < n; i++) {
            System.out.print(p[i].pos + "\t\t");
            System.out.print(p[i].AT + "\t\t");
            System.out.print(p[i].BT + "\t\t");

            j = 0;
            int v = 0;
            while (s[i][j] != -1) {
                System.out.print(p[i].ST[j] + " ");
                j++;
                v += 3;
            }
            while (v != 40) {
                System.out.print(" ");
                v += 1;
            }

            System.out.print( p[i].FT + "\t\t");
            System.out.print(p[i].WT + "\t\t");
            System.out.println(p[i].TAT);
        }

        // Calculating average wait time and turnaround time
        double avg_wt = (double) tot_wt / n;
        double avg_tat = (double) tot_tat / n;

        // Printing average wait time and turnaround time
        System.out.println("The average wait time is: " + avg_wt);
        System.out.println("The average TurnAround time is: " + avg_tat);
    }
}
