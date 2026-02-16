package year2015;

import java.util.*;

public class Day19A {
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

        Set<String> molecules = new HashSet<>();
        for (String[] rule : rules) {
            String from = rule[0];
            String to = rule[1];
            for (int i = 0; i < Objects.requireNonNull(molecule).length() - from.length() + 1; i++) {
                if (molecule.startsWith(from, i)) {
                    molecules.add(molecule.substring(0, i) + to + molecule.substring(i + from.length()));
                }
            }
        }

        System.out.println(molecules.size());
    }
}
