// Java program for process allocation using FCFS technique 


import java.util.*;

public class fcfsSchedule 
{
    public static void sortReq(int arrAt[], int arrBt[])
    {
        for(int i=0; i<arrAt.length-1; i++)
        {
            if(arrAt[i]>=arrAt[i+1])
            {
                int temp = arrAt[i];
                arrAt[i]= arrAt[i+1];
                arrAt[i+1] =temp;

                int temp2 = arrBt[i];
                arrBt[i] = arrBt[i+1];
                arrBt[i+1] = temp2;
            }
        }
    }

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

        System.out.println("Enter the arrival time and burst time for each process:");

        for (int i = 0; i < n; i++) 
        {
            System.out.print("P" + (i + 1) + " Arrival Time: ");
            arrivalTime[i] = sc.nextInt();

            System.out.print("P" + (i + 1) + " Burst Time: ");
            burstTime[i] = sc.nextInt();
        }

        sortReq(arrivalTime, burstTime);

        int currentTime = 0;

        for (int i = 0; i < n; i++) 
        {
            if (currentTime < arrivalTime[i]) 
            {
                currentTime = arrivalTime[i];
            }

            startTime[i] = currentTime;
            completionTime[i] = startTime[i] + burstTime[i];
            turnaroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnaroundTime[i] - burstTime[i];

            currentTime = completionTime[i];
        }

        // Calculate the average turnaround time and average waiting time for all processes
        double avgTurnaroundTime = 0;
        double avgWaitingTime = 0;

        for (int i = 0; i < n; i++) {
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
