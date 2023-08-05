import java.util.Scanner;

class fcfsDiskSch
{
    static void FCFS(int arr[], int head) {
        int seek_count = 0;
        int distance, cur_track;

        for (int i = 0; i < arr.length; i++) {
            cur_track = arr[i];

            // calculate absolute distance
            distance = Math.abs(cur_track - head);

            // increase the total count
            seek_count += distance;

            // accessed track is now new head
            head = cur_track;
        }

        System.out.println("Total number of seek operations = " + seek_count);

        // Seek sequence would be the same as request array sequence
        System.out.println("Seek Sequence is");

        for (int i = 0; i <arr.length ;i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       
        System.out.println("Enter total number of request:");
        int size= scanner.nextInt();
        // Request array input
        int arr[] = new int[size];
        System.out.println("Enter " + size + " track positions:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        // Head position input
        System.out.println("Enter the head position:");
        int head = scanner.nextInt();

        FCFS(arr, head);

        scanner.close();
    }
}
