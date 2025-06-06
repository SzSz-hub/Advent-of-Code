import java.awt.*;
import java.util.Scanner;

public class Day2A {
    public static void main(String[] args) {
        Point current = new Point(1, 1);
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNext()) {
                current = processCommand(sc.nextLine(), current);
                print(current);
            }
        }
    }

    private static Point processCommand(String s, Point current) {
        Point position = new Point(current);
        for (char c : s.toCharArray()) {
            position = switch (c) {
                case 'U' -> position.y - 1 < 0 ? position : new Point(position.x, position.y - 1);
                case 'D' -> position.y + 1 > 2 ? position : new Point(position.x, position.y + 1);
                case 'L' -> position.x - 1 < 0 ? position : new Point(position.x - 1, position.y);
                case 'R' -> position.x + 1 > 2 ? position : new Point(position.x + 1, position.y);
                default -> throw new IllegalArgumentException("Invalid command: " + c);
            };
        }
        return position;
    }

    private static void print(Point p) {
        System.out.print(p.y * 3 + p.x + 1);
    }
}
