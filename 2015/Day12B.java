package year2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12B {
    public static void main(String[] args) {
        String text;

        try (Scanner scanner = new Scanner(System.in)) {
            text = scanner.nextLine().trim();
        }

        Stack<Integer> openBrackets = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '{') {
                openBrackets.push(i);
            } else if (text.charAt(i) == '}') {
                int openIndex = openBrackets.pop();
                String subText = text.substring(openIndex, i + 1);
                if (subText.contains("\":\"red\"")) {
                    text = text.substring(0, openIndex) + text.substring(i + 1);
                    i = openIndex - 1;
                }
            }
        }

        long sum = sumNumbers(text);
        System.out.println(sum);

    }

    private static long sumNumbers(String text) {
        List<Integer> integers = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<![.\\w])-?\\d+(?![.\\w])");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            integers.add(Integer.parseInt(matcher.group()));
        }

        return integers.stream().mapToLong(Integer::longValue).sum();
    }
}
