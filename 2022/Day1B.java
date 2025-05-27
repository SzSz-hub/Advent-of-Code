import java.util.PriorityQueue;
import java.util.Scanner;

public class Day1B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> topThree = new PriorityQueue<>();
        int currentCalories = 0;

        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.isBlank()) {
                updateTopThree(topThree, currentCalories);
                currentCalories = 0;
            } else {
                currentCalories += Integer.parseInt(line);
            }
        }
        sc.close();
        updateTopThree(topThree, currentCalories);

        System.out.println(getQueuValue(topThree));
    }

    public static void updateTopThree(PriorityQueue<Integer> queue, int number) {
        queue.offer(number);
        if (queue.size() > 3)
            queue.poll();
    }

    public static int getQueuValue(PriorityQueue<Integer> queue) {
        int result = 0;
        for (int n : queue)
            result += n;
        return result;
    }
}
