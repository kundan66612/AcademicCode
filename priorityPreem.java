// Java program for process allocation using Priority technique using preemptive priority concept

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Process {
    int processID;
    int arrivalTime;
    int burstTime;
    int priority;
}

class priorityPreem {
    static Comparator<Process> compareArrivalTime = Comparator.comparingInt(p -> p.arrivalTime);
    static Comparator<Process> comparePriority = Comparator.comparingInt(p -> p.priority);

    static void priorityScheduling(List<Process> processes) {
        List<Integer> remainingTime = new ArrayList<>(processes.size());
        List<Integer> waitingTime = new ArrayList<>(processes.size());
        List<Integer> turnaroundTime = new ArrayList<>(processes.size());

        for (int i = 0; i < processes.size(); ++i) {
            remainingTime.add(processes.get(i).burstTime);
        }

        int currentTime = 0;
        int completedProcesses = 0;

        while (completedProcesses < processes.size()) {
            int selectedProcess = -1;
            int highestPriority = Integer.MAX_VALUE;

            for (int i = 0; i < processes.size(); ++i) {
                Process currentProcess = processes.get(i);

                if (currentProcess.arrivalTime <= currentTime && remainingTime.get(i) > 0 &&
                        currentProcess.priority < highestPriority) {
                    highestPriority = currentProcess.priority;
                    selectedProcess = i;
                }
            }

            if (selectedProcess == -1) {
                ++currentTime;
                continue;
            }

            remainingTime.set(selectedProcess, remainingTime.get(selectedProcess) - 1);

            if (remainingTime.get(selectedProcess) == 0) {
                completedProcesses++;
                int processID = processes.get(selectedProcess).processID;
                waitingTime.set(processID - 1, currentTime + 1 - processes.get(selectedProcess).arrivalTime - processes.get(selectedProcess).burstTime);
                turnaroundTime.set(processID - 1, currentTime + 1 - processes.get(selectedProcess).arrivalTime);
            }

            currentTime++;
        }

        double totalWaitingTime = 0.0;
        double totalTurnaroundTime = 0.0;

        System.out.println("Process\tArrival Time\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");

        for (int i = 0; i < processes.size(); ++i) {
            System.out.println(processes.get(i).processID + "\t\t" + processes.get(i).arrivalTime + "\t\t"
                    + processes.get(i).burstTime + "\t\t" + processes.get(i).priority + "\t\t"
                    + waitingTime.get(i) + "\t\t" + turnaroundTime.get(i));

            totalWaitingTime += waitingTime.get(i);
            totalTurnaroundTime += turnaroundTime.get(i);
        }

        double averageWaitingTime = totalWaitingTime / processes.size();
        double averageTurnaroundTime = totalTurnaroundTime / processes.size();

        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        List<Process> processes = new ArrayList<>(numProcesses);

        for (int i = 0; i < numProcesses; ++i) {
            Process process = new Process();

            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            process.arrivalTime = scanner.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            process.burstTime = scanner.nextInt();
            System.out.print("Enter priority for process " + (i + 1) + ": ");
            process.priority = scanner.nextInt();
            process.processID = i + 1;

            processes.add(process);
        }

        Collections.sort(processes, compareArrivalTime);

        priorityScheduling(processes);
    }
}
