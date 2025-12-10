
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day7B {
    private static final HashMap<Integer, Long> beams = new HashMap<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("S")) {
                    parseStartLine(line);
                } else {
                    parseLine(line);
                }
            }
        }

        long totalTimelines = beams.values().stream().mapToLong(Long::longValue).sum();
        System.out.println(totalTimelines);
    }

    private static void parseStartLine(String line) {
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'S') {
                beams.put(i, beams.getOrDefault(i, 0L) + 1);
            }
        }
    }

    private static void parseLine(String line) {
        char[] chars = line.toCharArray();
        HashMap<Integer, Long> currentBeams = new HashMap<>(beams);

        for (Map.Entry<Integer, Long> entry : currentBeams.entrySet()) {
            int pos = entry.getKey();
            long count = entry.getValue();

            if (chars[pos] == '^') {
                beams.put(pos, beams.get(pos) - count);
                if (beams.get(pos) == 0) {
                    beams.remove(pos);
                }

                boolean canGoLeft = (pos - 1 >= 0 && chars[pos - 1] == '.');
                boolean canGoRight = (pos + 1 < chars.length && chars[pos + 1] == '.');

                if (canGoLeft && canGoRight) {
                    beams.put(pos - 1, beams.getOrDefault(pos - 1, 0L) + count);
                    beams.put(pos + 1, beams.getOrDefault(pos + 1, 0L) + count);
                } else if (canGoLeft) {
                    beams.put(pos - 1, beams.getOrDefault(pos - 1, 0L) + count);
                } else if (canGoRight) {
                    beams.put(pos + 1, beams.getOrDefault(pos + 1, 0L) + count);
                }
            }
        }
    }
}