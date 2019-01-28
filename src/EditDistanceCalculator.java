
/**
 * Created by karthik on 02/11/18.
 */
public class EditDistanceCalculator {

    public static int editDistance(String first, String second) {
        char[] firstChars = first.toCharArray();
        char[] secondChars = second.toCharArray();

        return editDistanceWithMemoization(firstChars, secondChars, firstChars.length - 1, secondChars.length - 1);
    }

    private static int editDistanceWithMemoization(char[] firstChars, char[] secondChars, int firstLastIndex, int secondLastIndex) {
        // Construct a table.
        int [][] memoTable = new int[firstChars.length + 1][secondChars.length + 1];
        for(int index = 0; index < firstChars.length + 1; index++) {
            memoTable[index][0] = index;
        }

        for(int index = 0; index < secondChars.length + 1; index++) {
            memoTable[0][index] = index;
        }

        for(int rIndex = 1; rIndex <= firstChars.length; rIndex++) {
            for(int cIndex = 1; cIndex <= secondChars.length; cIndex++) {
                if(firstChars[rIndex - 1] == secondChars[cIndex - 1]) {
                    memoTable[rIndex][cIndex] = memoTable[rIndex - 1][cIndex - 1];
                } else {
                    memoTable[rIndex][cIndex] = 1 + Math.min(Math.min(memoTable[rIndex - 1][cIndex], memoTable[rIndex][cIndex - 1]),
                            memoTable[rIndex - 1][cIndex - 1]);
                }
            }
        }

        return memoTable[firstChars.length][secondChars.length];
    }

    private static int editDistanceInternal(char[] firstChars, char[] secondChars, int firstLastIndex, int secondLastIndex) {
        if(firstLastIndex >= 0 && secondLastIndex >= 0) {
            if(firstChars[firstLastIndex] == secondChars[secondLastIndex]) {
                return editDistanceInternal(firstChars, secondChars, firstLastIndex - 1, secondLastIndex - 1);
            } else {
                // Three possibilities.
                int removeLastInSecond = editDistanceInternal(firstChars, secondChars, firstLastIndex, secondLastIndex - 1);
                int removeLastInFirst = editDistanceInternal(firstChars, secondChars, firstLastIndex - 1, secondLastIndex);
                int removeLastInBoth = editDistanceInternal(firstChars, secondChars, firstLastIndex - 1, secondLastIndex - 1);

                int min = Math.min(Math.min(removeLastInSecond, removeLastInFirst), removeLastInBoth);
                return 1 + min;
            }
        }

        if(firstLastIndex == secondLastIndex) return 0;
        if(firstLastIndex < 0) {
            return secondLastIndex + 1;
        } else {
            return firstLastIndex + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(EditDistanceCalculator.editDistance("niveditha", "nivetha"));
    }
}
