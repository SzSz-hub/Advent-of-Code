package year2015;

import java.util.*;

public class Day19B {
    public static void main(String[] args){
        List<String[]> rules = new ArrayList<>();
        String molecule = null;
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    molecule = scanner.nextLine();
                    break;
                }
                String[] parts = line.split(" => ");
                rules.add(parts);
            }
        }

        System.out.println(findMinSteps(rules, molecule));
    }

    private static int findMinSteps(List<String[]> rules, String target) {
        Random random = new Random();

        while (true) {
            String current = target;
            int steps = 0;
            boolean changed = true;

            while (changed && !current.equals("e")) {
                changed = false;
                for (String[] rule : rules) {
                    String from = rule[0];
                    String to = rule[1];

                    int idx = current.indexOf(to);
                    if (idx != -1) {
                        current = current.substring(0, idx) + from + current.substring(idx + to.length());
                        steps++;
                        changed = true;
                        break;
                    }
                }
            }

            if (current.equals("e")) {
                return steps;
            }

            Collections.shuffle(rules, random);
        }
    }
}
