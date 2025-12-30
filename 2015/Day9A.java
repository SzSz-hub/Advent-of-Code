package year2015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Day9A {
    public static void main(String[] args) {
        List<travel> travels = new ArrayList<>();
        HashSet<String> travelSet = new HashSet<>();

        try(Scanner scanner = new Scanner(System.in)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                travel currentTravel = parseTravel(line);
                travels.add(currentTravel);
                travelSet.add(currentTravel.cityA);
                travelSet.add(currentTravel.cityB);
            }
        }
        System.out.println(shortestTravel(travels, travelSet));
    }

    private static travel parseTravel(String line) {
        String[] parts = line.split(" ");
        String cityA = parts[0];
        String cityB = parts[2];
        int distance = Integer.parseInt(parts[4]);
        return new travel(cityA, cityB, distance);
    }

    private static int shortestTravel(List<travel> travels, HashSet<String> travelSet) {
        int shortestDistance = Integer.MAX_VALUE;
        for(String cityA: travelSet) {
            HashSet<String> remainingCities = new HashSet<>(travelSet);
            remainingCities.remove(cityA);
            int distance = travelDistance(travels, 0, cityA, remainingCities, shortestDistance);
            if(distance < shortestDistance) {
                shortestDistance = distance;
            }
        }
        return shortestDistance;
    }

    private static int travelDistance(List<travel> travels, int currentDistance, String currentCity, HashSet<String> remainingCities, int shortestDistance) {
        if (remainingCities.isEmpty()) {
            return currentDistance;
        }
        for (String city : remainingCities) {
            for (travel t : travels) {
                if (t.involvesCity(currentCity) && t.involvesCity(city)) {
                    HashSet<String> newRemainingCities = new HashSet<>(remainingCities);
                    newRemainingCities.remove(city);
                    int distance = travelDistance(travels, currentDistance + t.distance, city, newRemainingCities, shortestDistance);
                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                    }
                }
            }
        }
        return shortestDistance;
    }
}

class travel{
    String cityA, cityB;
    int distance;
    travel(String cityA, String cityB, int distance){
        this.cityA = cityA;
        this.cityB = cityB;
        this.distance = distance;
    }

    boolean involvesCity(String city) {
        return cityA.equals(city) || cityB.equals(city);
    }
}
