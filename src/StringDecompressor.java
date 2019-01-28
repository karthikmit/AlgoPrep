import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by karthik on 30/12/18.
 */
public class StringDecompressor {

    public static String decompressString(String inputString, int repetition) {
        StringBuilder outputStringBuilder = new StringBuilder();

        char[] chars = inputString.toCharArray();
        int index = 0;
        while(index < chars.length) {
            char currentChar = chars[index];
            if(!Character.isDigit(currentChar)) {
                outputStringBuilder.append(currentChar);
                index++;
            } else {
                int startDigitIndex = index;
                int endDigitIndex = index + 1;
                while(Character.isDigit(currentChar)) {
                    endDigitIndex = ++index;
                    currentChar = chars[index];
                }

                String repString = inputString.substring(startDigitIndex, endDigitIndex);
                int subRepetition = Integer.parseInt(repString);
                Queue<Character> characterStack = new ArrayDeque<>();

                do {
                    char newChar = chars[index++];
                    if(newChar == ']') {
                        characterStack.poll();
                    } else if(newChar == '[') {
                        characterStack.add(newChar);
                    }
                } while (characterStack.peek() != null);

                outputStringBuilder.append(decompressString(inputString.substring(endDigitIndex + 1, index - 1), subRepetition));
            }
        }

        String finalOutput = outputStringBuilder.toString();
        String result = "";

        for(index = 0; index < repetition; index++) {
            result += finalOutput;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(decompressString("3[abc]4[ab]c", 1));
    }
}
