import java.util.Scanner;

public class Day1A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxCalories = 0;
        int currentCalories = 0;

        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.isBlank()) {
                if (currentCalories > maxCalories)
                    maxCalories = currentCalories;
                currentCalories = 0;
            } else {
                currentCalories += Integer.parseInt(line);
            }
        }
        sc.close();

        System.out.println(Math.max(currentCalories, maxCalories));
    }
}
