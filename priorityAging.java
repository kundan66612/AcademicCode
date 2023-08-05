import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Process {
    private int processId;
    private int burstTime;
    int priority;
    private int arrivalTime;

    public Process(int processId, int burstTime, int priority, int arrivalTime) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
    }

    public int getProcessId() {
        return processId;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
}

public class priorityAging {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        List<Process> processes = new ArrayList<>();

        // Input process details
        for (int i = 1; i <= n; i++) {
            System.out.println("Process " + i);
            System.out.print("Enter burst time: ");
            int burstTime = scanner.nextInt();
            System.out.print("Enter priority: ");
            int priority = scanner.nextInt();
            System.out.print("Enter arrival time: ");
            int arrivalTime = scanner.nextInt();

            processes.add(new Process(i, burstTime, priority, arrivalTime));
        }

        // Sort processes based on arrival time and priority (in ascending order)
        processes.sort((p1, p2) -> {
            if (p1.getArrivalTime() != p2.getArrivalTime()) {
                return p1.getArrivalTime() - p2.getArrivalTime();
            } else {
                return p1.getPriority() - p2.getPriority();
            }
        });

        // Perform priority scheduling with aging
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println("\nPriority Scheduling with Aging:");
        System.out.println("Process\tBurst Time\tPriority\tArrival Time\tWaiting Time\tTurnaround Time");

        while (!processes.isEmpty()) {
            Process currentProcess = processes.get(0);
            int waitingTime = Math.max(currentTime - currentProcess.getArrivalTime(), 0);
            int turnaroundTime = waitingTime + currentProcess.getBurstTime();

            // Aging: Increase the priority of all processes in the queue
            for (Process process : processes) {
                process.priority++;
            }

            currentTime += currentProcess.getBurstTime();
            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;

            System.out.println(currentProcess.getProcessId() + "\t\t" +
                    currentProcess.getBurstTime() + "\t\t" +
                    currentProcess.getPriority() + "\t\t" +
                    currentProcess.getArrivalTime() + "\t\t" +
                    waitingTime + "\t\t" +
                    turnaroundTime);

            processes.remove(0);
        }

        double averageWaitingTime = (double) totalWaitingTime / n;
        double averageTurnaroundTime = (double) totalTurnaroundTime / n;
        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);

        scanner.close();
    }
}
