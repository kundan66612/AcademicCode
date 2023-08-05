import java.util.*;

class scanDiskSch
{
    static int size = 8;
    static int disk_size = 200;

    static void SCAN(int arr[], int head, String direction) {
        int seek_count = 0;
        int distance, cur_track;
        Vector<Integer> left = new Vector<Integer>();
        Vector<Integer> right = new Vector<Integer>();
        Vector<Integer> seek_sequence = new Vector<Integer>();

        if (direction.equals("left")) {
            left.add(0);
        } else if (direction.equals("right")) {
            right.add(disk_size - 1);
        }

        for (int i = 0; i < size; i++) {
            if (arr[i] < head)
                left.add(arr[i]);
            if (arr[i] > head)
                right.add(arr[i]);
        }

        Collections.sort(left);
        Collections.sort(right);

        int run = 2;
        while (run-- > 0) {
            if (direction.equals("left")) {
                for (int i = left.size() - 1; i >= 0; i--) {
                    cur_track = left.get(i);
                    seek_sequence.add(cur_track);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
                direction = "right";
            } else if (direction.equals("right")) {
                for (int i = 0; i < right.size(); i++) {
                    cur_track = right.get(i);
                    seek_sequence.add(cur_track);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
                direction = "left";
            }
        }

        System.out.println("Total number of seek operations = " + seek_count);
        System.out.println("Seek Sequence is:");

        for (int i = 0; i < seek_sequence.size(); i++) {
            System.out.println(seek_sequence.get(i));
        }
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

        System.out.println("Enter the direction (left/right):");
        String direction = scanner.next();

        SCAN(arr, head, direction);

        scanner.close();
    }
}
