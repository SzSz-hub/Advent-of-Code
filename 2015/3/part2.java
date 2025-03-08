import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Point> points = new ArrayList<>();
        Point currentSanta = new Point(0, 0);
        Point currentRobot = new Point(0, 0);

        points.add(currentSanta);
        int hausesWithGifts = 1;

        var instructions = sc.nextLine().toCharArray();
        sc.close();
        int i = 0;
        for (var instruction : instructions) {
            if(i % 2 == 0){
                currentSanta = switch (instruction) {
                    case '^' -> new Point(currentSanta.x, currentSanta.y + 1);
                    case 'v' -> new Point(currentSanta.x, currentSanta.y - 1);
                    case '>' -> new Point(currentSanta.x + 1, currentSanta.y);
                    case '<' -> new Point(currentSanta.x - 1, currentSanta.y);
                    default -> currentSanta;
                };
                if (!points.contains(currentSanta)) {
                    hausesWithGifts++;
                    points.add(currentSanta);
                }
            } else {
                currentRobot = switch (instruction) {
                    case '^' -> new Point(currentRobot.x, currentRobot.y + 1);
                    case 'v' -> new Point(currentRobot.x, currentRobot.y - 1);
                    case '>' -> new Point(currentRobot.x + 1, currentRobot.y);
                    case '<' -> new Point(currentRobot.x - 1, currentRobot.y);
                    default -> currentRobot;
                };
                if (!points.contains(currentRobot)) {
                    hausesWithGifts++;
                    points.add(currentRobot);
                }
            }
            i++;
        }
        System.out.println("The number of houses which received gifts is " + hausesWithGifts + ".");
    }
}