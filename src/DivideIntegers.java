/**
 * Created by karthik on 30/11/18.
 */
public class DivideIntegers {

    public int divide(int dividend, int divisor) {
        int sign = 1;
        if(dividend < 0 || divisor < 0) sign = -1;
        if(dividend < 0 && divisor < 0) sign = 1;
        int currentReminder = Math.abs(dividend); divisor = Math.abs(divisor);
        int currentResult = 0;

        while (currentReminder >= divisor) {
            currentResult++;
            currentReminder = currentReminder - divisor;
        }

        return currentResult * sign;
    }

    public static void main(String[] args) {
        DivideIntegers divideIntegers = new DivideIntegers();

        System.out.println(divideIntegers.divide(7, -3));
    }
}
