import java.util.*;

public class Day9B {
    public static void main(String[] args) {
        List<Coordinate> coordinates = new ArrayList<>();

        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                coordinates.add(new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            }
        }

        int[] xs = coordinates.stream().mapToInt(c -> c.x).distinct().sorted().toArray();
        int[] ys = coordinates.stream().mapToInt(c -> c.y).distinct().sorted().toArray();

        long maxArea = 0;
        int n = coordinates.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Coordinate c1 = coordinates.get(i);
                Coordinate c2 = coordinates.get(j);

                if (isRectangleValidCompressed(c1, c2, coordinates, xs, ys)) {
                    long area = (long)(Math.abs(c2.x - c1.x) + 1) * (Math.abs(c2.y - c1.y) + 1);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        System.out.println(maxArea);
    }

    private static boolean isRectangleValidCompressed(Coordinate c1, Coordinate c2,
                                                      List<Coordinate> polygon, int[] xs, int[] ys) {
        int minX = Math.min(c1.x, c2.x), maxX = Math.max(c1.x, c2.x);
        int minY = Math.min(c1.y, c2.y), maxY = Math.max(c1.y, c2.y);

        for (int x : xs) {
            if (x < minX || x > maxX) continue;
            for (int y : ys) {
                if (y < minY || y > maxY) continue;
                if (!isInsideOrOnPolygon(new Coordinate(x, y), polygon)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isInsideOrOnPolygon(Coordinate p, List<Coordinate> polygon) {
        int crossings = 0;
        for (int i = 0; i < polygon.size(); i++) {
            Coordinate a = polygon.get(i);
            Coordinate b = polygon.get((i + 1) % polygon.size());
            if (isOnSegment(p, a, b)) return true;
            if ((a.y <= p.y && b.y > p.y) || (b.y <= p.y && a.y > p.y)) {
                double t = (double)(p.y - a.y) / (b.y - a.y);
                if (p.x < a.x + t * (b.x - a.x)) crossings++;
            }
        }
        return crossings % 2 == 1;
    }

    private static boolean isOnSegment(Coordinate p, Coordinate a, Coordinate b) {
        if (a.x == b.x && p.x == a.x) return p.y >= Math.min(a.y, b.y) && p.y <= Math.max(a.y, b.y);
        if (a.y == b.y && p.y == a.y) return p.x >= Math.min(a.x, b.x) && p.x <= Math.max(a.x, b.x);
        return false;
    }
}

class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
