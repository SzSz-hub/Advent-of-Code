import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1B {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<Integer> measurements = new ArrayList<>();
        int increaseCounter = 0;

        while(sc.hasNext()){
            int current = sc.nextInt();
            measurements.add(current);
            if(measurements.size() == 4){
                int first = measurements.removeFirst();
                if (first < current)
                    increaseCounter++;
            }
        }
        sc.close();

        System.out.println(increaseCounter);
    }
}
