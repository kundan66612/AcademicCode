import java.util.Scanner;

class Process {
    int pid;
    int arrival_time;
    int burst_time;
    int priority;
    int start_time;
    int completion_time;
    int turnaround_time;
    int waiting_time;
    int response_time;
}

public class priorityNonPreem{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n;
        Process[] p = new Process[100];
        float avg_turnaround_time;
        float avg_waiting_time;
        float avg_response_time;
        float cpu_utilisation;
        int total_turnaround_time = 0;
        int total_waiting_time = 0;
        int total_response_time = 0;
        int total_idle_time = 0;
        float throughput;
        int[] is_completed = new int[100];
        for (int i = 0; i < 100; i++) {
            is_completed[i] = 0;
        }

        System.out.print("Enter the number of processes: ");
        n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            System.out.print("Enter arrival time of process " + (i + 1) + ": ");
            p[i].arrival_time = scanner.nextInt();
            System.out.print("Enter burst time of process " + (i + 1) + ": ");
            p[i].burst_time = scanner.nextInt();
            System.out.print("Enter priority of the process " + (i + 1) + ": ");
            p[i].priority = scanner.nextInt();
            p[i].pid = i + 1;
            System.out.println();
        }

        int current_time = 0;
        int completed = 0;
        int prev = 0;

        while (completed != n) {
            int idx = -1;
            int mx = -1;
            for (int i = 0; i < n; i++) {
                if (p[i].arrival_time <= current_time && is_completed[i] == 0) {
                    if (p[i].priority > mx) {
                        mx = p[i].priority;
                        idx = i;
                    }
                    if (p[i].priority == mx) {
                        if (p[i].arrival_time < p[idx].arrival_time) {
                            mx = p[i].priority;
                            idx = i;
                        }
                    }
                }
            }
            if (idx != -1) {
                p[idx].start_time = current_time;
                p[idx].completion_time = p[idx].start_time + p[idx].burst_time;
                p[idx].turnaround_time = p[idx].completion_time - p[idx].arrival_time;
                p[idx].waiting_time = p[idx].turnaround_time - p[idx].burst_time;
                p[idx].response_time = p[idx].start_time - p[idx].arrival_time;

                total_turnaround_time += p[idx].turnaround_time;
                total_waiting_time += p[idx].waiting_time;
                total_response_time += p[idx].response_time;
                total_idle_time += p[idx].start_time - prev;

                is_completed[idx] = 1;
                completed++;
                current_time = p[idx].completion_time;
                prev = current_time;
            } else {
                current_time++;
            }
        }

        int min_arrival_time = 10000000;
        int max_completion_time = -1;
        for (int i = 0; i < n; i++) {
            min_arrival_time = Math.min(min_arrival_time, p[i].arrival_time);
            max_completion_time = Math.max(max_completion_time, p[i].completion_time);
        }

        avg_turnaround_time = (float) total_turnaround_time / n;
        avg_waiting_time = (float) total_waiting_time / n;
        avg_response_time = (float) total_response_time / n;
        cpu_utilisation = ((max_completion_time - total_idle_time) / (float) max_completion_time) * 100;
        throughput = (float) n / (max_completion_time - min_arrival_time);

        System.out.println("\n\n");
        System.out.println("#P\tAT\tBT\tPRI\tST\tCT\tTAT\tWT\tRT\t\n");

        for (int i = 0; i < n; i++) {
            System.out.println(
                p[i].pid + "\t" + p[i].arrival_time + "\t" + p[i].burst_time + "\t" + p[i].priority + "\t" +
                p[i].start_time + "\t" + p[i].completion_time + "\t" + p[i].turnaround_time + "\t" +
                p[i].waiting_time + "\t" + p[i].response_time + "\t\n"
            );
        }
        System.out.println("Average Turnaround Time = " + avg_turnaround_time);
        System.out.println("Average Waiting Time = " + avg_waiting_time);
        System.out.println("Average Response Time = " + avg_response_time);
        System.out.println("CPU Utilization = " + cpu_utilisation + "%");
        System.out.println("Throughput = " + throughput + " process/unit time");

    }
}