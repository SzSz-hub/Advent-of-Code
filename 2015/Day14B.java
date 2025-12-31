package year2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day14B {
    public static void main(String[] args) {
        final int TOTAL_TIME = 2503;
        int maxPoints = 0;
        List<Reindeer> reindeers = new ArrayList<>();

        try(Scanner scanner = new Scanner(System.in)){
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                Reindeer current = new Reindeer(line[0], Integer.parseInt(line[3]), Integer.parseInt(line[6]), Integer.parseInt(line[13]));
                reindeers.add(current);
            }
        }

        for(int i = 0; i < TOTAL_TIME; i++){
            int currentMax = 0;
            for(Reindeer r : reindeers){
                r.timeTick(i);
                if(r.distance > currentMax){
                    currentMax = r.distance;
                }
            }
            for (Reindeer r : reindeers) {
                if (r.distance == currentMax) {
                    r.points++;
                }
            }
        }

        for(Reindeer r : reindeers){
            if(r.points > maxPoints){
                maxPoints = r.points;
            }
        }

        System.out.println(maxPoints);
    }

}

class Reindeer {
    String name;
    int speed;
    int flyTime;
    int restTime;
    int distance;
    int points;

    public Reindeer(String name, int speed, int flyTime, int restTime) {
        this.name = name;
        this.speed = speed;
        this.flyTime = flyTime;
        this.restTime = restTime;
        this.distance = 0;
        this.points = 0;
    }

    public void timeTick(int seconds){
        int cycleTime = flyTime + restTime;
        int remainingTime = seconds % cycleTime;

        if(remainingTime < flyTime){
            distance += speed;
        }
    }
}
