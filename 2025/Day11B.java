import java.util.*;

public class Day11B {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Map<String, List<String>> graph = parseGraph(scanner);
            Set<String> required = new HashSet<>(Arrays.asList("fft", "dac"));
            System.out.println(countPathsThrough(graph, "svr", "out", required));
        }
    }

    public static long countPathsThrough(Map<String, List<String>> graph, String start, String end, Set<String> required) {
        Map<String, Map<Set<String>, Long>> memo = new HashMap<>();

        return dfs(graph, start, end, new HashSet<>(), required, memo);
    }

    private static long dfs(Map<String, List<String>> graph, String current, String end,
                            Set<String> visited, Set<String> required,
                            Map<String, Map<Set<String>, Long>> memo) {
        if (current.equals(end)) {
            return visited.containsAll(required) ? 1 : 0;
        }

        Map<Set<String>, Long> currentMemo = memo.get(current);
        if (currentMemo != null && currentMemo.containsKey(visited)) {
            return currentMemo.get(visited);
        }

        Set<String> newVisited = new HashSet<>(visited);
        if (required.contains(current)) {
            newVisited.add(current);
        }

        long total = 0;
        for (String neighbor : graph.getOrDefault(current, Collections.emptyList())) {
            total += dfs(graph, neighbor, end, newVisited, required, memo);
        }

        memo.putIfAbsent(current, new HashMap<>());
        memo.get(current).put(new HashSet<>(visited), total);

        return total;
    }

    public static Map<String, List<String>> parseGraph(Scanner scanner) {
        Map<String, List<String>> graph = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] parts = line.replace(":", "").split(" ");

            List<String> neighbors = new ArrayList<>(Arrays.asList(parts).subList(1, parts.length));

            graph.put(parts[0], neighbors);
        }

        return graph;
    }
}
