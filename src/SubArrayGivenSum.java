/**
 * Created by karthik on 30/10/18.
 */
public class SubArrayGivenSum {

    public static int[] getSubarrayWithSum(int[] givenArray, int givenSum) {
        // Base and edge cases.
        if(givenArray == null) return null;
        if(givenArray.length == 0) return null;

        int startIndex = 0, endIndex = 0;
        int currentSum = givenArray[startIndex];
        while(startIndex <= endIndex && endIndex < givenArray.length) {
            if(currentSum == givenSum) return new int[]{startIndex, endIndex};

            if(currentSum < givenSum) {
                currentSum += givenArray[++endIndex];
            } else {
                currentSum -= givenArray[startIndex++];
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] testArray = {1, 2, 3, 4, 6, 5, 10};
        int[] subarrayWithSum = SubArrayGivenSum.getSubarrayWithSum(testArray, 15);
        System.out.println(subarrayWithSum[0] + " :: " + subarrayWithSum[1]);
        int currSum = 0;
        if(subarrayWithSum != null) {
            for(int index = subarrayWithSum[0]; index <= subarrayWithSum[1]; index++) {
                currSum += testArray[index];
            }
        }

        System.out.println("Total in fact, is :: " + currSum);
    }
}
