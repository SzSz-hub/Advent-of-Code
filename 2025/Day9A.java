import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9A {
    public static void main(String[] args) {
        List<Coordinate> coordinates = new ArrayList<>();

        try (Scanner sc = new Scanner(System.in)){
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                coordinates.add(new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            }
        }

        System.out.println(getMaxArea(coordinates));
    }

    private static long getArea(Coordinate c1, Coordinate c2){
        return (long) (Math.abs(c2.x - c1.x) + 1) * (Math.abs(c2.y - c1.y) + 1);
    }

    private static long getMaxArea(List<Coordinate> coordinates) {
        coordinates.sort(Coordinate::compareTo);
        long maxArea = 0;
        for (int i = 0; i < coordinates.size(); i++) {
            for (int j = i + 1; j < coordinates.size(); j++) {
                long area = getArea(coordinates.get(i), coordinates.get(j));
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
}

class Coordinate implements Comparable<Coordinate> {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Coordinate o) {
        if (this.x != o.x) {
            return Integer.compare(this.x, o.x);
        }
        return Integer.compare(this.y, o.y);
    }
}
