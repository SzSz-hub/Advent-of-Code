import java.util.PriorityQueue;
import java.util.Scanner;

public class Day1A {
    public static void main(String[] args) {
        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();
        Scanner sc = new Scanner(System.in);
        boolean leftRead = true;

        while(sc.hasNext()){
            int current = sc.nextInt();
            if(leftRead){
                left.offer(current);
            } else {
                right.offer(current);
            }
            leftRead = !leftRead;
        }

        int totalDistance = 0;
        while(!left.isEmpty() && !right.isEmpty()){
            int leftValue = left.poll();
            int rightValue = right.poll();
            totalDistance += Math.abs(leftValue - rightValue);
        }
        System.out.println(totalDistance);
    }
}
