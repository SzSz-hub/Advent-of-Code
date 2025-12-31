package year2015;

import java.util.*;

public class Day13A {
    public static void main(String[] args) {
        Map<String, Map<String, Integer>> happinessMap = new HashMap<>();
        Set<String> people = new HashSet<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String person = parts[0];
                String neighbor = parts[10].substring(0, parts[10].length() - 1);
                int value = Integer.parseInt(parts[3]);
                if (parts[2].equals("lose")) {
                    value = -value;
                }
                people.add(person);
                happinessMap.computeIfAbsent(person, k -> new HashMap<>()).put(neighbor, value);
            }
        }
        System.out.println(calculateMaxHappiness(happinessMap, new ArrayList<>(people)));
    }

    private static int calculateMaxHappiness(Map<String, Map<String, Integer>> happinessMap, List<String> people) {
        List<List<String>> permutations = new ArrayList<>();
        permute(people, 0, permutations);

        int maxHappiness = Integer.MIN_VALUE;
        for (List<String> arrangement : permutations) {
            int happiness = calculateArrangementHappiness(arrangement, happinessMap);
            maxHappiness = Math.max(maxHappiness, happiness);
        }
        return maxHappiness;
    }

    private static void permute(List<String> list, int start, List<List<String>> result) {
        if (start == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, start, i);
            permute(list, start + 1, result);
            Collections.swap(list, start, i);
        }
    }

    private static int calculateArrangementHappiness(List<String> arrangement, Map<String, Map<String, Integer>> happinessMap) {
        int total = 0;
        int n = arrangement.size();
        for (int i = 0; i < n; i++) {
            String person = arrangement.get(i);
            String leftNeighbor = arrangement.get((i - 1 + n) % n);
            String rightNeighbor = arrangement.get((i + 1) % n);
            total += happinessMap.get(person).getOrDefault(leftNeighbor, 0);
            total += happinessMap.get(person).getOrDefault(rightNeighbor, 0);
        }
        return total;
    }
}
