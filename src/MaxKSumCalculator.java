/**
 * Created by karthik on 12/12/18.
 */
public class MaxKSumCalculator {

    public static void calculateMaxKSum(int[] input, int k) {
        int soFarMax = Integer.MIN_VALUE;

        int sum = 0;
        for(int i = 0; i < k; i++) {
            sum += input[i];
        }
        if(sum > soFarMax) soFarMax = sum;

        int leftMostIndex = 0;
        for(int i = k; i < input.length; i++) {
            sum -= input[leftMostIndex++];
            sum += input[i];
            if(sum > soFarMax) soFarMax = sum;
        }

        System.out.println(soFarMax);
    }

    public static int calculateLongestOnesWithOneFlipOfZero(int[] input) {

        int flippedIndex = -1;
        int maxOnes = 0; int sofarMaxOnes = 0;
        int currentIndex = 0;

        while (currentIndex < input.length) {

            if(input[currentIndex] == 0) {
                if(flippedIndex == -1) {
                    flippedIndex = currentIndex;
                    maxOnes++;
                } else {
                    maxOnes = currentIndex - flippedIndex;
                    flippedIndex = currentIndex;
                    if(maxOnes > sofarMaxOnes) sofarMaxOnes = maxOnes;
                }
            } else {
                maxOnes++;
            }

            currentIndex++;
        }

        System.out.println("Max Ones :: " + maxOnes);
        return flippedIndex;
    }

    public static void main(String[] args) {
        //MaxKSumCalculator.calculateMaxKSum(new int[]{1, 2, 3, 4, 5, 8, 30, 1}, 3);
        System.out.println(MaxKSumCalculator.calculateLongestOnesWithOneFlipOfZero(new int[]{0,0,1,0,1,1,1,1,0,1}));
    }
}
