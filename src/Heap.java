import java.util.Arrays;
import java.util.Random;

/**
 * Created by karthik on 30/10/18.
 */
public class Heap {

    private final int[] array;

    public Heap(int[] givenArray) {
        this.array = givenArray;
    }

    public void heapSort(int[] givenArray) {
        int length = givenArray.length;

        heapify(givenArray);

        for(int index = length - 1; index >= 0; index--) {
            swapElements(givenArray, index, 0);
            siftDown(givenArray, index, 0);
        }
    }

    private void heapify(int[] givenArray) {
        int length = givenArray.length;

        for(int index = length / 2 - 1; index >= 0; index--) {
            siftDown(givenArray, length, index);
        }
    }

    private void siftDown(int[] givenArray, int length, int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if(left < length && givenArray[left] > givenArray[largest]) {
            largest = left;
        }

        if(right < length && givenArray[right] > givenArray[largest]) {
            largest = right;
        }

        if(largest != index) {
            swapElements(givenArray, largest, index);
            siftDown(givenArray, length, largest);
        }
    }

    private void swapElements(int[] givenArray, int first, int second) {
        int temp = givenArray[second];
        givenArray[second] = givenArray[first];
        givenArray[first] = temp;
    }

    public static void main(String[] args) {
        Heap heap = new Heap(new int[10]);
        int max = 6;
        int[] givenArray = new int[max];

        Random random = new Random();
        for(int index = 0; index < max; index++) {
            int i = random.nextInt(100);
            givenArray[index] = i;
        }
        Arrays.stream(givenArray).forEach(value -> System.out.print(value + " "));
        System.out.println("\n---------------");
        heap.heapSort(givenArray);
        Arrays.stream(givenArray).forEach(value -> System.out.print(value + " "));
    }
}
