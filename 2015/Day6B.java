import java.util.*;

public class Day6B {
    private static final Map<Integer, Map<Integer, Integer>> lights = new HashMap<>();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                processCommand(sc.nextLine());
            }
        }
        System.out.println(getTotalBrightness());
    }

    private static void processCommand(String line) {
        String[] parts = line.split(" ");

        if (parts[0].equals("toggle")) {
            executeCommand(parts[1], parts[3], Day6B::toggle);
        } else {
            String action = parts[1];
            executeCommand(parts[2], parts[4],
                    action.equals("on") ? Day6B::turnOn : Day6B::turnOff);
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

    private static int getBrightness(int x, int y) {
        return lights.containsKey(x) ? lights.get(x).getOrDefault(y, 0) : 0;
    }

    private static void setBrightness(int x, int y, int brightness) {
        if (brightness <= 0) {
            // Remove the light if brightness is 0 or less
            if (lights.containsKey(x)) {
                lights.get(x).remove(y);
                if (lights.get(x).isEmpty()) {
                    lights.remove(x);
                }
            }
        } else {
            lights.computeIfAbsent(x, _ -> new HashMap<>()).put(y, brightness);
        }
    }

    private static void turnOn(int x, int y) {
        int currentBrightness = getBrightness(x, y);
        setBrightness(x, y, currentBrightness + 1);
    }

    private static void turnOff(int x, int y) {
        int currentBrightness = getBrightness(x, y);
        setBrightness(x, y, Math.max(0, currentBrightness - 1));
    }

    private static void toggle(int x, int y) {
        int currentBrightness = getBrightness(x, y);
        setBrightness(x, y, currentBrightness + 2);
    }

    private static long getTotalBrightness() {
        return lights.values().stream()
                .flatMap(row -> row.values().stream())
                .mapToLong(Integer::longValue)
                .sum();
    }

    @FunctionalInterface
    private interface LightOperation {
        void apply(int x, int y);
    }
}