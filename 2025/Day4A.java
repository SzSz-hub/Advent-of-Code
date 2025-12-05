import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;


public class Day4A {
    private static final ArrayList<char[]> rollMapList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int movableRolls = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            rollMapList.add(line.toCharArray());
        }
        scanner.close();

        movableRolls = countMovableRolls();
        System.out.println(movableRolls);
    }

    private static int countMovableRolls() {
        int movableRolls = 0;
        for (int i = 0; i < rollMapList.size(); i++) {
            for (int j = 0; j < rollMapList.get(i).length; j++) {
                if (isRoll(i, j)) {
                    int adjacentRolls = countAdjacentRolls(i, j);
                    if (adjacentRolls < 4) {
                        movableRolls++;
                    }
                }
            }
        }
        return movableRolls;
    }

    private static boolean isRoll(int i, int j) {
        if (i < 0 || j < 0 || i >= rollMapList.size() || j >= rollMapList.get(i).length) {
            return false;
        }
        return rollMapList.get(i)[j] == '@';
    }

    private static int countAdjacentRolls(int i, int j) {
        int count = 0;
        int[] directions = {-1, 0, 1};
        for (int di : directions) {
            for (int dj : directions) {
                if (di == 0 && dj == 0) continue; // Skip the center cell
                if (isRoll(i + di, j + dj) && rollMapList.get(i + di)[j + dj] == '@') {
                    count++;
                }
            }
        }
        return count;
    }
}
