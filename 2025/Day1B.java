
import java.util.Scanner;

public class Day1B {
    public static void main(String[] args) {
        final int TOTAL = 100;
        int position = 50;
        int hits = 0;

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            char direction = line.charAt(0);
            int rotation = Integer.parseInt(line.substring(1));

            if (direction == 'R') {
                hits += (position + rotation) / TOTAL;
                position = (position + rotation) % TOTAL;
            } else {
                if (position == 0) {
                    hits += rotation / TOTAL;
                } else if (rotation < position) {
                } else {
                    hits += 1 + (rotation - position) / TOTAL;
                }
                position = ((position - rotation) % TOTAL + TOTAL) % TOTAL;
            }
        }

        System.out.println(hits);
    }
}
