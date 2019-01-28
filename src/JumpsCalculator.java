import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple Jumps Calculator.
 *
 * Not taken care of OOPS principles, as wanted to demo the logic only.
 */
public class JumpsCalculator {

    private Map<Integer, Integer> nextIndexMap = new HashMap<>();
    private Map<Integer, Integer> jumpsTable = new HashMap<>();

    private Integer calculateMinJumpsRequired(int[] values, int startIndex) {
        if(values == null) return 0;
        if(startIndex < 0 || startIndex >= values.length) return 0;
        if(startIndex == values.length - 1) return 0;

        if(jumpsTable.containsKey(startIndex)) {
            return jumpsTable.get(startIndex);
        }

        int value = values[startIndex];
        int sofarMinimum = Integer.MAX_VALUE; Integer sofarMinIndex = null;

        for(int index = 1; index <= value; index++) {
            if(index + startIndex < values.length) {
                Integer current = calculateMinJumpsRequired(values, startIndex + index);
                if(current != null && current < sofarMinimum) {
                    sofarMinimum = current;
                    sofarMinIndex = index + startIndex;
                }
            }
        }

        int reqdJumps = sofarMinimum == Integer.MAX_VALUE ? Integer.MAX_VALUE : sofarMinimum + 1;

        nextIndexMap.put(startIndex, sofarMinIndex);
        jumpsTable.put(startIndex, reqdJumps);

        if(reqdJumps == Integer.MAX_VALUE) return null;

        return reqdJumps;
    }

    public void printJumpsPath(int[] values) {
        Integer minJumpsRequired = calculateMinJumpsRequired(values, 0);
        if(minJumpsRequired == null) {
            System.out.println("Path is not available");
        } else {
            System.out.println("Min Jumps :: " + minJumpsRequired);
            calculatePath(values);
        }
    }

    private void calculatePath(int[] values) {
        Integer nextIndex = 0;
        while(nextIndex != null && nextIndex != values.length - 1) {
            System.out.println(nextIndex);
            nextIndex = nextIndexMap.get(nextIndex);
        }
    }

    public static void main(String[] args) {
        int[] values = new int[] {2, 0, 0, 3, 2, 6, 1, 1};
        JumpsCalculator calculator = new JumpsCalculator();
        calculator.printJumpsPath(values);
    }
}
