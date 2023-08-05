import java.util.Scanner;

public class BankersAlgorithm {

    private int[][] maximum; // Maximum resource needs of processes
    private int[][] allocated; // Currently allocated resources to processes
    private int[] available; // Available resources
    private int[][] need; // Remaining resource needs of processes
    private int[] safeSequence; // Safe sequence of processes

    private int n; // Number of processes
    private int m; // Number of resource types

    public BankersAlgorithm(int n, int m) {
        this.n = n;
        this.m = m;
        this.maximum = new int[n][m];
        this.allocated = new int[n][m];
        this.available = new int[m];
        this.need = new int[n][m];
        this.safeSequence = new int[n];
    }

    public void initializeData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the maximum resource needs of processes:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maximum[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the currently allocated resources to processes:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocated[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the available resources:");
        for (int i = 0; i < m; i++) {
            available[i] = scanner.nextInt();
        }

        scanner.close();
    }

    public boolean isSafe() {
        boolean[] finish = new boolean[n];
        int[] work = available.clone();

        int count = 0;
        while (count < n) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (need[i][j] > work[j]) {
                            break;
                        }
                    }

                    if (j == m) {
                        for (int k = 0; k < m; k++) {
                            work[k] += allocated[i][k];
                        }
                        safeSequence[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                break; // System is not in a safe state
            }
        }

        return count == n; // If count is equal to n, the system is in a safe state
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        int n = scanner.nextInt();

        System.out.println("Enter the number of resource types:");
        int m = scanner.nextInt();

        BankersAlgorithm bankersAlgorithm = new BankersAlgorithm(n, m);
        bankersAlgorithm.initializeData();

        if (bankersAlgorithm.isSafe()) {
            System.out.println("System is in a safe state.");
            System.out.println("Safe sequence of processes:");
            for (int i = 0; i < n; i++) {
                System.out.print("P" + bankersAlgorithm.safeSequence[i]);
                if (i != n - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        } else {
            System.out.println("System is NOT in a safe state.");
        }

        scanner.close();
    }
}
