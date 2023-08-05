import java.util.concurrent.Semaphore;

public class producerConsumer {
    private static final int N = 5;
    private static int[] buffer = new int[N];
    private static int in = 0, out = 0;

    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore full = new Semaphore(0);
    private static Semaphore empty = new Semaphore(N);

    public static void main(String[] args) {
        Thread producerThread = new Thread(producerConsumer::producer);
        Thread consumerThread = new Thread(producerConsumer::consumer);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void producer() {
        for (int i = 0; i < 10; i++) {
            try {
                empty.acquire();
                mutex.acquire();

                System.out.println("Produced item is: " + i);
                buffer[in] = i;
                in = (in + 1) % N;

                Thread.sleep(1000); // Sleep for 1 second to simulate production time

                mutex.release();
                full.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void consumer() {
        int item;
        for (int i = 0; i < 10; i++) {
            try {
                full.acquire();
                mutex.acquire();

                item = buffer[out];
                System.out.println("Consumed item is: " + item);
                out = (out + 1) % N;

                Thread.sleep(1000); // Sleep for 1 second to simulate consumption time

                mutex.release();
                empty.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
