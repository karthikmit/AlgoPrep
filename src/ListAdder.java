import java.util.*;

/**
 * Created by karthik on 24/11/18.
 */
public class ListAdder {

    public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
    }

    public int myAtoi(String str) {
        char[] chars = str.trim().toCharArray();
        if(chars.length == 0) return 0;

        int sign = chars[0] == '-' ? -1 : 1;
        long result = 0;
        int index = chars[0] == '-' ? 1 : 0;
        while(index < chars.length && chars[index] != ' ') {
            int charValue = chars[index] - '0';
            if(charValue >= 0 && charValue <= 9) {
                result = result * 10 + charValue;//Integer.parseInt(new StringBuilder().append(chars[index]).toString());
                index++;
            } else
                break;
        }

        if(result > Integer.MAX_VALUE) return Integer.MIN_VALUE;

        return (int) (result * sign);
    }

    public int reverse(int x) {
        long result = 0;
        int resultSign = x > 0 ? 1 : -1;

        x = Math.abs(x);
        while(x > 0) {
            long modulo = x % 10;

            result = 10 * result + modulo;
            x = x / 10;
            System.out.println(x);
        }

        if(result > Integer.MAX_VALUE) return 0;
        return (int) (result * resultSign);
    }

    public String longestPalindrome(String s) {
        char[] characters = s.toCharArray();
        if(characters.length == 0) return "";

        String[][] map = new String[characters.length][characters.length];
        for(int xi = 0; xi < characters.length; xi++)
            for(int yi = 0; yi < characters.length; yi++)
                map[xi][yi] = null;

        String longestPalindromeInternal = longestPalindromeInternal(characters, map, 0, characters.length - 1);
        return longestPalindromeInternal;
    }

    private String longestPalindromeInternal(char[] s, String[][] map, int xi, int yi) {
        if(xi == yi) return new StringBuilder().append(s[xi]).toString();
        if(s[xi] == s[yi] && yi - xi == 1) {
            return new StringBuilder().append(s[xi]).append(s[yi]).toString();
        }

        if(map[xi][yi] != null) return map[xi][yi];

        if(s[xi] == s[yi]) {
            String innerPalindrome = longestPalindromeInternal(s, map, xi + 1, yi - 1);
            if(innerPalindrome.length() == (yi - xi - 1)) {
                String longest = s[xi] + innerPalindrome + s[yi];
                map[xi][yi] = longest;
                return longest;
            }
        }
        String s1 = longestPalindromeInternal(s, map, xi, yi - 1);
        String s2 = longestPalindromeInternal(s, map, xi + 1, yi);
        String longest = s1.length() > s2.length() ? s1 : s2;
        map[xi][yi] = longest;

        return longest;
    }

    public int lengthOfLongestSubstring(String s) {
        if(s.equals("")) return 0;
        char[] characters = s.toCharArray();

        Map<Character, Integer> charMap = new HashMap<>();

        int startIndex = 0;
        int bestStartIndex = 0; int bestEndIndex = 0;
        for(int index = 0; index < characters.length; index++) {
            char currentChar = characters[index];
            if(charMap.containsKey(currentChar)) {
                // It exists. finish off the scan and store the endIndex and reset the map and startIndex;
                int length = index - startIndex;
                if(length > (bestEndIndex - bestStartIndex)) {
                    bestStartIndex = startIndex;
                    bestEndIndex = index;
                }

                Integer indexOfLastOccurrence = charMap.get(currentChar);
                // reset all and start new scan
                startIndex = indexOfLastOccurrence + 1;
                index = startIndex;
                charMap.clear();
                charMap.put(characters[index], index);
            } else {
                charMap.put(currentChar, index);
            }

        }

        int length = characters.length - startIndex;
        if(length > (bestEndIndex - bestStartIndex)) {
            bestStartIndex = startIndex;
            bestEndIndex = characters.length;
        }

        return bestEndIndex - bestStartIndex;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode currentNode = null;
        ListNode rootNode = null;
        ListNode crPointer = null;

        int reminder = 0;
        while(true) {
            if(l1 != null && l2 != null) {
                int currentSum = reminder + l1.val + l2.val;
                int currentVal = currentSum % 10;
                reminder = currentSum / 10;
                currentNode = new ListNode(currentVal);
                if(crPointer == null) {
                    rootNode = currentNode;
                    crPointer = rootNode;
                } else {
                    crPointer.next = currentNode;
                    crPointer = currentNode;
                }
                l1 = l1.next;
                l2 = l2.next;
            } else {
                if(l1 == null && l2 == null) break;

                ListNode bigNumberNode = l1 != null ? l1 : l2;
                while(bigNumberNode != null) {
                    int currentSum = reminder + bigNumberNode.val;
                    int currentVal = currentSum % 10;
                    reminder = currentSum / 10;
                    currentNode = new ListNode(currentVal);
                    if(crPointer == null) {
                        rootNode = currentNode;
                        crPointer = rootNode;
                    } else {
                        crPointer.next = currentNode;
                        crPointer = currentNode;
                    }
                    bigNumberNode = bigNumberNode.next;
                }

                break;
            }
        }

        if(reminder != 0) {
            crPointer.next = new ListNode(reminder);
        }
        return rootNode;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> list = new HashSet<>();
        Map<Integer, Integer> indexMap = new HashMap<>();
        for(int index = 0; index < nums.length; index++) indexMap.put(nums[index], index);

        for(int index = 0; index < nums.length; index++) {
            for(int sindex = index + 1; sindex < nums.length; sindex++) {
                int subSum = nums[index] + nums[sindex];
                if(indexMap.containsKey(-1 * subSum)) {
                    Integer tIndex = indexMap.get(-1 * subSum);
                    if(tIndex == sindex || tIndex == index) continue;

                    List<Integer> numbers = new ArrayList<Integer>();
                    numbers.add(nums[index]); numbers.add(nums[sindex]);numbers.add(-1 * subSum);

                    Collections.sort(numbers);
                    list.add(numbers);
                }
            }
        }

        ArrayList<List<Integer>> lists = new ArrayList<>();
        lists.addAll(list);
        return lists;
    }

    public static void main(String[] args) {

        /*ListNode first = new ListNode(9);
        ListNode fTemp = first;
        fTemp.next = new ListNode(7);

        ListNode second = new ListNode(8);
        ListNode sTemp = second;
        sTemp.next = new ListNode(7);

        ListAdder adder = new ListAdder();
        ListNode result = adder.addTwoNumbers(first, second);
        while(result != null) {
            System.out.println(result.val);
            result = result.next;
        }*/
        //System.out.println(new ListAdder().lengthOfLongestSubstring("HelloHello World@23"));
        //System.out.println(new ListAdder().longestPalindrome("HelloollH"));
        //System.out.println(new ListAdder().reverse(1534236469));
        /*System.out.println(Integer.parseInt("2147483648"));
        System.out.println(new ListAdder().myAtoi("2147483648"));*/
        System.out.println(new ListAdder().threeSum(new int[]{1, 0, -1, 0, 3, -3}));
    }
}
