import java.util.Arrays;
import java.util.Random;

/**
 * Created by karthik on 30/10/18.
 */
public class QuickSort {

    public static void main(String[] args) throws Exception {
        QuickSort quickSort = new QuickSort();
        int[] givenArray = new int[100];

        Random random = new Random();
        for(int index = 0; index < 100; index++) {
            int i = random.nextInt(100);
            givenArray[index] = i;
        }
        Arrays.stream(givenArray).forEach(value -> System.out.print(value + " "));
        System.out.println("\n---------------");
        quickSort.sort(givenArray);
        Arrays.stream(givenArray).forEach(value -> System.out.print(value + " "));
    }

    private static boolean contains(int[] givenArray, int i) {
        for(int index = 0; index < givenArray.length; index++) {
            if(givenArray[index] == i) return true;
        }

        return false;
    }

    public void sort(int[] givenArray) throws Exception {
        // Base and edge cases.
        if(givenArray == null) return;
        sortInternal(givenArray, 0, givenArray.length - 1);
    }

    private void sortInternal(int[] givenArray, int startIndex, int endIndex) throws Exception {
        // Base case
        if(startIndex >= endIndex) return;

        int pIndex = partition(givenArray, startIndex, endIndex);
        sortInternal(givenArray, startIndex, pIndex - 1);
        sortInternal(givenArray, pIndex + 1, endIndex);
    }

    private int partition(int[] givenArray, int startIndex, int endIndex) {
        int pivot = givenArray[endIndex];

        int i = startIndex;

        for(int j = i; j < endIndex; j++) {
            if(givenArray[j] < pivot) {
                if(i != j) {
                    int temp = givenArray[i];
                    givenArray[i] = givenArray[j];
                    givenArray[j] = temp;
                }

                i++;
            }
        }

        int temp = givenArray[endIndex];
        givenArray[endIndex] = givenArray[i];
        givenArray[i] = temp;

        return i;
    }
}
