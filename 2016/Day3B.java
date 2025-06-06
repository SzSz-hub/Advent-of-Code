import java.util.Arrays;
import java.util.Scanner;

public class Day3B {
    public static void main(String[] args) {
        int triangleCount = 0;
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNext()) {
                int[][] numbers = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        numbers[i][j] = sc.nextInt();
                    }
                }
                triangleCount += numberOfTriangle(numbers);
            }
        }
        System.out.println(triangleCount);
    }

    private static int numberOfTriangle(int[][] numbers) {
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (isTriangle(new int[]{numbers[0][i], numbers[1][i], numbers[2][i]})) {
                count++;
            }
        }
        return count;
    }

    private static boolean isTriangle(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[0] + numbers[1] > numbers[2];
    }
}
