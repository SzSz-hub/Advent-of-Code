import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day2B {
    private static int validLines = 0;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String line = sc.nextLine();
            parseLine(line);
        }
        sc.close();
        System.out.println(validLines);
    }

    private static void parseLine(String line){
        int[] split = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
        if (isLineValid(split))
            validLines++;
        else {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < split.length; i++){
                for (int j =0 ; j <split.length; j++){
                    if (i != j){
                        numbers.add(split[i] + split[j]);
                    }
                }
                if (isLineValid(numbers.stream().mapToInt(Integer::intValue).toArray())){
                    validLines++;
                    return;
                }
                numbers.clear();
            }
        }
    }

    private static boolean isLineValid(int[] numbers){
        boolean isIncreasing = numbers[0] - numbers[1] < 0;
        for(int i = 1; i < numbers.length; i++){
            int difference = numbers[i - 1] - numbers[i];
            boolean isCurrentIncreasing = difference < 0;
            if (!(isDifferenceInRange(difference) && isIncreasing == isCurrentIncreasing))
                return false;
        }
        return true;
    }

    private static boolean isDifferenceInRange(int difference){
        return difference != 0 && Math.abs(difference) <= 3;
    }
}
