import java.util.Scanner;

class node 
{
    int distance = 0;
    boolean accessed = false;
}

public class sstfDiskSch {
    public static void calculateDifference(int queue[], int head, node diff[]) {
        for (int i = 0; i < diff.length; i++)
            diff[i].distance = Math.abs(queue[i] - head);
    }

    public static int findMin(node diff[]) {
        int index = -1, minimum = Integer.MAX_VALUE;

        for (int i = 0; i < diff.length; i++) {
            if (!diff[i].accessed && minimum > diff[i].distance) {
                minimum = diff[i].distance;
                index = i;
            }
        }
        return index;
    }

    public static void shortestSeekTimeFirst(int request[], int head) {
        if (request.length == 0)
            return;

        node diff[] = new node[request.length];

        for (int i = 0; i < diff.length; i++)
            diff[i] = new node();

        int seek_count = 0;
        int[] seek_sequence = new int[request.length + 1];

        for (int i = 0; i < request.length; i++) {
            seek_sequence[i] = head;
            calculateDifference(request, head, diff);
            int index = findMin(diff);
            diff[index].accessed = true;
            seek_count += diff[index].distance;
            head = request[index];
        }

        seek_sequence[seek_sequence.length - 1] = head;

        System.out.println("Total number of seek operations = " + seek_count);
        System.out.println("Seek Sequence is");

        for (int i = 0; i < seek_sequence.length; i++)
            System.out.println(seek_sequence[i]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of tracks:");
        int numTracks = scanner.nextInt();

        int arr[] = new int[numTracks];
        System.out.println("Enter the track positions:");
        for (int i = 0; i < numTracks; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("Enter the head position:");
        int head = scanner.nextInt();

        shortestSeekTimeFirst(arr, head);

        scanner.close();
    }
}
