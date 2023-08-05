// Java program for process allocation using SJF technique with Arrival time

import java.util.*;

public class sjfSchedule
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] startTime = new int[n];
        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];
        boolean[] isCompleted = new boolean[n];

        System.out.println("Enter the arrival time and burst time for each process:");

        for (int i = 0; i < n; i++) 
        {
            System.out.print("P" + (i + 1) + " Arrival Time: ");
            arrivalTime[i] = sc.nextInt();

            System.out.print("P" + (i + 1) + " Burst Time: ");
            burstTime[i] = sc.nextInt();
        }

        int currentTime = 0;
        int completed = 0;

        while (completed != n) 
        {
            int shortest = -1;
            int shortestBurst = Integer.MAX_VALUE;

            // Find the process with the shortest burst time that has arrived
            for (int i = 0; i < n; i++) 
            {
                if (!isCompleted[i] && arrivalTime[i] <= currentTime && burstTime[i] < shortestBurst) 
                {
                    shortest = i;
                    shortestBurst = burstTime[i];
                }
            }

            // If no process is found, wait until the next arrival
            if (shortest == -1) 
            {
                currentTime++;
            } 
            else 
            {
                startTime[shortest] = currentTime;
                completionTime[shortest] = startTime[shortest] + burstTime[shortest];
                turnaroundTime[shortest] = completionTime[shortest] - arrivalTime[shortest];
                waitingTime[shortest] = turnaroundTime[shortest] - burstTime[shortest];
                isCompleted[shortest] = true;

                currentTime = completionTime[shortest];
                completed++;
            }
        }

        // Calculate the average turnaround time and average waiting time for all processes
        double avgTurnaroundTime = 0;
        double avgWaitingTime = 0;

        for (int i = 0; i < n; i++) 
        {
            avgTurnaroundTime += turnaroundTime[i];
            avgWaitingTime += waitingTime[i];
        }

        avgTurnaroundTime /= n;
        avgWaitingTime /= n;

        // Print the results
        System.out.println("Process\tArrival Time\tBurst Time\tStart Time\tCompletion Time\tTurnaround Time\tWaiting Time");

        for (int i = 0; i < n; i++) 
        {
            System.out.println("P" + (i + 1) + "\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + startTime[i] + "\t\t" + completionTime[i] + "\t\t" + turnaroundTime[i] + "\t\t" + waitingTime[i]);
        }

        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
        System.out.println("Average Waiting Time: " + avgWaitingTime);
    }
}
