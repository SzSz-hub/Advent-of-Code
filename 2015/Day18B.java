package year2015;

import java.awt.*;
import java.util.Scanner;

public class Day18B {
    private static final int DIMENSION = 100;

    public static void main(String[] args) {
        boolean[][] grid = new boolean[DIMENSION][DIMENSION];
        try (Scanner scanner = new Scanner(System.in)) {
            int currentLine = 0;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    grid[currentLine][i] = line.charAt(i) == '#';
                }
                currentLine++;
            }
        }

        for (int i = 0; i < 100; i++) {
            edgeLightsOn(grid);
            grid = nextGen(grid);
        }
        edgeLightsOn(grid);
        System.out.println(countTotalLigthsOn(grid));
    }

    private static void edgeLightsOn(boolean[][] grid) {
        grid[0][0]  = true;
        grid[0][DIMENSION - 1] = true;
        grid[DIMENSION - 1][0] = true;
        grid[DIMENSION - 1][DIMENSION - 1] = true;
    }

    private static boolean[][] nextGen(boolean[][] grid) {
        boolean[][] newGrid = new boolean[DIMENSION][DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                int count = countLigthsOn(grid, i, j);
                if (grid[i][j]) {
                    newGrid[i][j] = count == 2 || count == 3;
                } else {
                    newGrid[i][j] = count == 3;
                }
            }
        }
        return newGrid;
    }

    private static int countLigthsOn(boolean[][] grid, int i, int j) {
        Point[] directions = new Point[]{
                new Point(-1, -1), new Point(-1, 0), new Point(-1, 1),
                new Point(0, -1), /* current */   new Point(0, 1),
                new Point(1, -1), new Point(1, 0), new Point(1, 1)
        };
        int count = 0;
        for (Point direction : directions) {
            int newI = i + direction.x;
            int newJ = j + direction.y;
            if (newI >= 0 && newI < DIMENSION && newJ >= 0 && newJ < DIMENSION && grid[newI][newJ]) {
                count++;
            }
        }
        return count;
    }

    private static int countTotalLigthsOn(boolean[][] grid) {
        int count = 0;
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (grid[i][j]) count++;
            }
        }
        return count;
    }
}
