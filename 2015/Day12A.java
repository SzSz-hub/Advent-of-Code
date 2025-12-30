package year2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12A {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        String text;

        try (Scanner scanner = new Scanner(System.in)) {
            text = scanner.nextLine().trim();
        }

        Pattern pattern = Pattern.compile("(?<![.\\w])-?\\d+(?![.\\w])");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            integers.add(Integer.parseInt(matcher.group()));
        }

        long sum = integers.stream().mapToLong(Integer::longValue).sum();
        System.out.println(sum);

    }
}
