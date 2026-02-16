package year2015;

import java.util.*;

public class Day16B {
    public static void main(String[] args) {
        Aunt baseLine = new Aunt("Sue 0: children: 3, cats: 7, samoyeds: 2, pomeranians: 3, akitas: 0, vizslas: 0, goldfish: 5, trees: 3, cars: 2, perfumes: 1");
        try (Scanner input = new Scanner(System.in)) {
            while (input.hasNextLine()) {
                Aunt aunt = new Aunt(input.nextLine());
                if (aunt.isMatch(baseLine)) {
                    System.out.println(aunt.index);
                }
            }
        }
    }
}

class Aunt {
    int index;
    int children = -1;
    int cats = -1;
    int samoyeds = -1;
    int pomeranians = -1;
    int akitas = -1;
    int vizslas = -1;
    int goldfish = -1;
    int trees = -1;
    int cars = -1;
    int perfumes = -1;

    public Aunt(String input) {
        String[] parts = input.split(" ");
        index = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
        for (int i = 2; i < parts.length; i += 2) {
            String property = parts[i].substring(0, parts[i].length() - 1);
            int value = Integer.parseInt(parts[i + 1].replace(",", ""));
            switch (property) {
                case "children":
                    children = value;
                    break;
                case "cats":
                    cats = value;
                    break;
                case "samoyeds":
                    samoyeds = value;
                    break;
                case "pomeranians":
                    pomeranians = value;
                    break;
                case "akitas":
                    akitas = value;
                    break;
                case "vizslas":
                    vizslas = value;
                    break;
                case "goldfish":
                    goldfish = value;
                    break;
                case "trees":
                    trees = value;
                    break;
                case "cars":
                    cars = value;
                    break;
                case "perfumes":
                    perfumes = value;
                    break;
            }
        }
    }

    public boolean isMatch(Aunt other) {
        Set<String> properties = new HashSet<>(Arrays.asList("children", "cats", "samoyeds", "pomeranians", "akitas", "vizslas", "goldfish", "trees", "cars", "perfumes"));
        for (String property : properties) {
            int thisValue = getPropertyValue(property);
            int otherValue = other.getPropertyValue(property);
            if (property.equals("cats") || property.equals("trees")) {
                if (thisValue != -1 && otherValue != -1 && thisValue < otherValue) {
                    return false;
                }
            } else if (property.equals("pomeranians") || property.equals("goldfish")) {
                if (thisValue != -1 && otherValue != -1 && thisValue > otherValue) {
                    return false;
                }
            } else {
                if (thisValue != -1 && otherValue != -1 && thisValue != otherValue) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getPropertyValue(String property) {
        return switch (property) {
            case "children" -> children;
            case "cats" -> cats;
            case "samoyeds" -> samoyeds;
            case "pomeranians" -> pomeranians;
            case "akitas" -> akitas;
            case "vizslas" -> vizslas;
            case "goldfish" -> goldfish;
            case "trees" -> trees;
            case "cars" -> cars;
            case "perfumes" -> perfumes;
            default -> 0;
        };
    }
}
