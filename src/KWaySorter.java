import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by karthik on 02/12/18.
 */
public class KWaySorter {

    public void sort(int[] array) {
        Queue<Integer> queuedElements = new PriorityQueue<>();
        Arrays.stream(array).forEach(value -> queuedElements.add(value));

        while (queuedElements.peek() != null) {
            System.out.println(queuedElements.poll());
        }
    }

    public static void main(String[] args) {
        new KWaySorter().sort(new int[] {2, 10, 3, 6, 4, 15, 8});
    }
}
