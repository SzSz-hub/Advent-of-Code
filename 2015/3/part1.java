import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Point> points = new ArrayList<>();
        Point current = new Point(0, 0);
        points.add(current);
        int hausesWithGifts = 1;

        var instructions = sc.nextLine().toCharArray();
        sc.close();
        for (var instruction : instructions) {
            current = switch (instruction) {
                case '^' -> new Point(current.x, current.y + 1);
                case 'v' -> new Point(current.x, current.y - 1);
                case '>' -> new Point(current.x + 1, current.y);
                case '<' -> new Point(current.x - 1, current.y);
                default -> current;
            };
            if (!points.contains(current)) {
                hausesWithGifts++;
                points.add(current);
            }
        }
        System.out.println("The number of houses which received gifts is " + hausesWithGifts + ".");
    }
}