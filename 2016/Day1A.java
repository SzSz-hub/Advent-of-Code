import java.awt.*;
import java.util.Scanner;

public class Day1A {
    public static void main(String[] args)  {
        String[] direction = {"N", "E", "S", "W"};
        Scanner sc = new Scanner(System.in);
        String[] instructions = sc.nextLine().split(",");
        sc.close();

        Point pos = new Point(0, 0);
        int currentDirection = 0;
        for (String instruction : instructions){
            instruction = instruction.trim();
            char turn = instruction.charAt(0);
            int distance = Integer.parseInt(instruction.substring(1));
            if (turn == 'R'){
                currentDirection = (currentDirection + 1) % 4;
            } else {
                currentDirection = (currentDirection + 3) % 4;
            }
            pos = switch (direction[currentDirection]){
                case "N" -> new Point(pos.x, pos.y + distance);
                case "E" -> new Point(pos.x + distance, pos.y);
                case "S" -> new Point(pos.x, pos.y - distance);
                case "W" -> new Point(pos.x - distance, pos.y);
                default -> pos;
            };
        }
        System.out.println("Distance from Easter Bunny Headquarters are " + (Math.abs(pos.x) + Math.abs(pos.y)) + " blocks.");
    }
}
