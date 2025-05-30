import java.util.*;

public class Day6A {
    private static final Map<Integer, Set<Integer>> lights = new HashMap<>();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                processCommand(sc.nextLine());
            }
        }
        System.out.println(countLights());
    }

    private static void processCommand(String line) {
        String[] parts = line.split(" ");

        if (parts[0].equals("toggle")) {
            executeCommand(parts[1], parts[3], Day6A::toggle);
        } else {
            String action = parts[1];
            executeCommand(parts[2], parts[4],
                    action.equals("on") ? Day6A::turnOn : Day6A::turnOff);
        }
    }

    private static void executeCommand(String startCoords, String endCoords,
                                       LightOperation operation) {
        int[] start = parseCoordinates(startCoords);
        int[] end = parseCoordinates(endCoords);

        for (int x = start[0]; x <= end[0]; x++) {
            for (int y = start[1]; y <= end[1]; y++) {
                operation.apply(x, y);
            }
        }
    }

    private static int[] parseCoordinates(String coords) {
        String[] parts = coords.split(",");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
    }

    private static boolean isLightOn(int x, int y) {
        return lights.containsKey(x) && lights.get(x).contains(y);
    }

    private static void turnOn(int x, int y) {
        lights.computeIfAbsent(x,_ -> new HashSet<>()).add(y);
    }

    private static void turnOff(int x, int y) {
        if (lights.containsKey(x)) {
            lights.get(x).remove(y);
            if (lights.get(x).isEmpty()) {
                lights.remove(x);
            }
        }
    }

    private static void toggle(int x, int y) {
        if (isLightOn(x, y)) {
            turnOff(x, y);
        } else {
            turnOn(x, y);
        }
    }

    private static int countLights() {
        return lights.values().stream()
                .mapToInt(Set::size)
                .sum();
    }

    @FunctionalInterface
    private interface LightOperation {
        void apply(int x, int y);
    }
}