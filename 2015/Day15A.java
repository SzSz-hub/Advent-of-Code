package year2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day15A {
    public static void main(String[] args) {
        final int TOTAL = 100;
        List<Ingredient> ingredients = new ArrayList<>();
        try (Scanner input = new Scanner(System.in)) {
            while (input.hasNextLine()) {
                ingredients.add(new Ingredient(input.nextLine()));
            }
        }

        System.out.println(findBestCombination(ingredients, TOTAL));
    }

    private static int findBestCombination(List<Ingredient> ingredients, int total) {
        int maxScore = 0;
        int[] amounts = new int[ingredients.size()];
        while (amounts[0] != total + 1) {
            int sum = 0;
            for (int amount : amounts) {
                sum += amount;
            }
            if (sum == total) {
                int score = calculateScore(ingredients, amounts);
                if (score > maxScore) {
                    maxScore = score;
                }
            }
            amounts[amounts.length - 1]++;
            for (int i = amounts.length - 1; i > 0; i--) {
                if (amounts[i] == total + 1) {
                    amounts[i] = 0;
                    amounts[i - 1]++;
                }
            }
        }
        return maxScore;
    }

    private static int calculateScore(List<Ingredient> ingredients, int[] amounts) {
        int capacity = 0;
        int durability = 0;
        int flavor = 0;
        int texture = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            capacity += ingredients.get(i).capacity * amounts[i];
            durability += ingredients.get(i).durability * amounts[i];
            flavor += ingredients.get(i).flavor * amounts[i];
            texture += ingredients.get(i).texture * amounts[i];
        }
        if (capacity < 0 || durability < 0 || flavor < 0 || texture < 0) return 0;

        return capacity * durability * flavor * texture;
    }
}

class Ingredient {
    String name;
    int capacity;
    int durability;
    int flavor;
    int texture;
    int calories;

    public Ingredient(String line) {
        String[] parts = line.split(" ");
        name = parts[0].substring(0, parts[0].length() - 1);
        capacity = Integer.parseInt(parts[2].substring(0, parts[2].length() - 1));
        durability = Integer.parseInt(parts[4].substring(0, parts[4].length() - 1));
        flavor = Integer.parseInt(parts[6].substring(0, parts[6].length() - 1));
        texture = Integer.parseInt(parts[8].substring(0, parts[8].length() - 1));
        calories = Integer.parseInt(parts[10]);
    }
}
