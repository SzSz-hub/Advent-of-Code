import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0;

        Pattern pattern = Pattern.compile("(?=(\\d|one|two|three|four|five|six|seven|eight|nine))");

        while (sc.hasNext()) {
            String line = sc.nextLine();
            Matcher matcher = pattern.matcher(line);

            String first = "";
            String last = "";

            while (matcher.find()) {
                String match = matcher.group(1);
                if (first.isBlank())
                    first = match;
                last = match;
            }
            sum += convertToDigit(first) * 10 + convertToDigit(last);
        }
        sc.close();

        System.out.println(sum);
    }

    private static int convertToDigit(String s) {
        return switch (s) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            default -> Integer.parseInt(s);
        };
    }
}
