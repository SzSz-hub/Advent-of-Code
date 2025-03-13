import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class part2 {
    private static HashSet<Integer> history = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int frequency = 0;
        history.add(frequency);
        ArrayList<String> changes = new ArrayList<>();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            changes.add(line);
        }

        int counter = 0;
        while (true) {
            frequency = changeFrequency(frequency, changes.get(counter % changes.size()));
            if (history.contains(frequency)) {
                System.out.println("The first frequency reached twice is: " + frequency + ".");
                break;
            }
            history.add(frequency);
            counter++;
        }

    }

    private static int changeFrequency(int frequency, String line) {
        return frequency + Integer.parseInt(line);
    }
}