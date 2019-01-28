import java.util.*;

/**
 * Created by karthik on 05/12/18.
 */
public class PermGenerator {

    public static void generatePermutations(int n, int[] elements, List<int[]> results) {

        if(n == 1) {
            int[] clone = elements.clone();
            results.add(clone);
        } else {
            for(int index = 0; index < n; index++) {
                generatePermutations(n-1, elements, results);

                int j = ( n % 2 == 0 ) ? index : 0;
                swap(n - 1, j, elements);
            }
        }
    }

    public static void main(String[] args) {
        /*int[] list = new int[]{1, 2, 3};

        List<int[]> results = new ArrayList<>();
        generatePermutations(3, list, results);

        results.stream().forEach(elements -> System.out.println(Arrays.toString(elements)));*/
        System.out.println(headOrTail());
    }

    public static String headOrTail() {
        Random random = new Random(new Date().getTime());
        int tossResult = random.nextInt(2);
        String result = tossResult == 0 ? "HEAD" : "TAIL";
        return result;
    }

    private static void swap(int first, int second, int[] elements) {
        int t = elements[first];
        elements[first] = elements[second];
        elements[second] = t;
    }
}
