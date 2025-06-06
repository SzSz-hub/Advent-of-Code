import java.awt.*;
import java.util.Scanner;

public class Day2B {
    private static final char[][] KEYPAD = {
        {'.', '.', '1', '.', '.'},
        {'.', '2', '3', '4', '.'},
        {'5', '6', '7', '8', '9'},
        {'.', 'A', 'B', 'C', '.'},
        {'.', '.', 'D', '.', '.'}
    };
    
    private static final Point STARTING_POSITION = new Point(0, 2);
    private static final char EMPTY_CELL = '.';

    public static void main(String[] args) {
        Point current = new Point(STARTING_POSITION);
        
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNext()) {
                current = processCommand(sc.nextLine(), current);
                printCurrentKey(current);
            }
        }
    }

    public static Point processCommand(String commands, Point current) {
        Point position = new Point(current);
        
        for (char command : commands.toCharArray()) {
            position = movePosition(position, command);
        }
        
        return position;
    }
    
    private static Point movePosition(Point position, char direction) {
        return switch (direction) {
            case 'U' -> moveUp(position);
            case 'D' -> moveDown(position);
            case 'L' -> moveLeft(position);
            case 'R' -> moveRight(position);
            default -> position;
        };
    }
    
    private static Point moveUp(Point position) {
        int newY = position.y - 1;
        return isValidPosition(position.x, newY) ? 
            new Point(position.x, newY) : position;
    }
    
    private static Point moveDown(Point position) {
        int newY = position.y + 1;
        return isValidPosition(position.x, newY) ? 
            new Point(position.x, newY) : position;
    }
    
    private static Point moveLeft(Point position) {
        int newX = position.x - 1;
        return isValidPosition(newX, position.y) ? 
            new Point(newX, position.y) : position;
    }
    
    private static Point moveRight(Point position) {
        int newX = position.x + 1;
        return isValidPosition(newX, position.y) ? 
            new Point(newX, position.y) : position;
    }
    
    private static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < KEYPAD[0].length && 
               y >= 0 && y < KEYPAD.length && 
               KEYPAD[y][x] != EMPTY_CELL;
    }

    public static void printCurrentKey(Point position) {
        System.out.print(KEYPAD[position.y][position.x]);
    }
}