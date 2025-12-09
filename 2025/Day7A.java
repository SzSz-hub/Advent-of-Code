import java.util.HashSet;
import java.util.Scanner;

public class Day7A {
    private static final HashSet<Integer> beams = new HashSet<>();

    public static void main(String[] args) {
        int tachyonCounter = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("S")) {
                    parseStartLine(line);
                } else {
                    tachyonCounter += parseLine(line);
                }
            }
        }
        System.out.println(tachyonCounter);
    }

    private static void parseStartLine(String line) {
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'S') {
                beams.add(i);
            }
        }
    }

    private static int parseLine(String line) {
        int newBeams = 0;

        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '^') {
                if (beams.contains(i)) {
                    beams.remove(i);
                    newBeams++;
                    if (i - 1 > 0 && chars[i - 1] == '.') {
                        beams.add(i - 1);
                    }
                    if (i + 1 < chars.length && chars[i + 1] == '.') {
                        beams.add(i + 1);
                    }
                }
            }
        }
        return newBeams;
    }
}