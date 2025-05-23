import java.awt.*;
import java.util.HashSet;
import java.util.Scanner;

public class Day1B {
    public static void main(String[] args)  {
        String[] direction = {"N", "E", "S", "W"};
        Scanner sc = new Scanner(System.in);
        String[] instructions = sc.nextLine().split(",");
        sc.close();
        HashSet<Point> visitedLocations = new HashSet<>();

        Point pos = new Point(0, 0);
        visitedLocations.add(new Point(pos));
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
            switch(direction[currentDirection]){
                case "N":
                    for (int i = 1; i <= distance; i++){
                        Point temp = new Point(pos.x, pos.y + i);
                        if (visitedLocations.contains(temp)){
                            System.out.println("Distance from Easter Bunny Headquarters are " + (Math.abs(temp.x) + Math.abs(temp.y)) + " blocks.");
                            return;
                        }
                        visitedLocations.add(temp);
                    }
                    pos = new Point(pos.x, pos.y + distance);
                    break;
                case "E":
                    for (int i = 1; i <= distance; i++){
                        Point temp = new Point(pos.x + i, pos.y);
                        if(visitedLocations.contains(temp)){
                            System.out.println("Distance from Easter Bunny Headquarters are " + (Math.abs(temp.x) + Math.abs(temp.y)) + " blocks.");
                            return;
                        }
                        visitedLocations.add(temp);
                    }
                    pos = new Point(pos.x + distance, pos.y);
                    break;
                case "S":
                    for (int i = 1; i <= distance; i++){
                        Point temp = new Point(pos.x, pos.y - i);
                        if(visitedLocations.contains(temp)){
                            System.out.println("Distance from Easter Bunny Headquarters are " + (Math.abs(temp.x) + Math.abs(temp.y)) + " blocks.");
                            return;
                        }
                        visitedLocations.add(temp);
                    }
                    pos = new Point(pos.x, pos.y - distance);
                    break;
                case "W":
                    for (int i = 1; i <= distance; i++){
                        Point temp = new Point(pos.x - i, pos.y);
                        if(visitedLocations.contains(temp)){
                            System.out.println("Distance from Easter Bunny Headquarters are " + (Math.abs(temp.x) + Math.abs(temp.y)) + " blocks.");
                            return;
                        }
                        visitedLocations.add(temp);
                    }
                    pos = new Point(pos.x - distance, pos.y);
                    break;
            }
        }
    }
}
