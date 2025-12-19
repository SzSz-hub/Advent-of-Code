import java.util.*;

public class Day11A {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Map<String, List<String>> graph = parseGraph(scanner);
            System.out.println(countPaths(graph, "you", "out"));
        }
    }

    public static int countPaths(Map<String, List<String>> graph, String start, String end) {
        Map<String, Integer> inDegree = new HashMap<>();

        for (List<String> neighbors : graph.values()) {
            for (String neighbor : neighbors) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        Deque<String> queue = new ArrayDeque<>();
        for (String node : graph.keySet()) {
            if (inDegree.getOrDefault(node, 0) == 0) {
                queue.add(node);
            }
        }

        Map<String, Integer> pathCount = new HashMap<>();
        pathCount.put(start, 1);

        while (!queue.isEmpty()) {
            String current = queue.removeFirst();
            int count = pathCount.getOrDefault(current, 0);

            for (String neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                pathCount.put(neighbor, pathCount.getOrDefault(neighbor, 0) + count);
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return pathCount.get(end);
    }

    public static Map<String, List<String>> parseGraph(Scanner scanner) {
        Map<String, List<String>> graph = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] parts = line.replace(":", "").split(" ");

            List<String> neighbors = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                neighbors.add(parts[i]);
            }

            graph.put(parts[0], neighbors);
        }

        return graph;
    }
}
