package year2015;

import java.util.Scanner;

public class Day10B {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {
            String input = sc.nextLine();

            int iterations = 50;
            for (int i = 0; i < iterations; i++) {
                input = lookAndSay(input);
            }

            System.out.println(input.length());
        }
    }

    private static String lookAndSay(String input) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        char currentChar = input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == currentChar) {
                count++;
            } else {
                result.append(count).append(currentChar);
                currentChar = input.charAt(i);
                count = 1;
            }
        }
        result.append(count).append(currentChar);
        return result.toString();
    }
}
