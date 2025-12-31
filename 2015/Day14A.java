package year2015;

import java.util.Scanner;

public class Day14A {
    public static void main(String[] args) {
        final int TOTAL_TIME = 2503;
        int maxDistance = 0;

        try(Scanner scanner = new Scanner(System.in)){
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                Reindeer current = new Reindeer(line[0], Integer.parseInt(line[3]), Integer.parseInt(line[6]), Integer.parseInt(line[13]));
                int distance = current.distanceAfter(TOTAL_TIME);

                if (distance > maxDistance) maxDistance = distance;
            }
        }

        System.out.println(maxDistance);
    }

}

class Reindeer {
    String name;
    int speed;
    int flyTime;
    int restTime;

    public Reindeer(String name, int speed, int flyTime, int restTime) {
        this.name = name;
        this.speed = speed;
        this.flyTime = flyTime;
        this.restTime = restTime;
    }

    public int distanceAfter(int totalTime) {
        int cycleTime = flyTime + restTime;
        int fullCycles = totalTime / cycleTime;
        int remainingTime = totalTime % cycleTime;

        int distance = fullCycles * speed * flyTime;
        distance += Math.min(remainingTime, flyTime) * speed;

        return distance;
    }
}
