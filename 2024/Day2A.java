import java.util.Arrays;
import java.util.Scanner;

public class Day2A {
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
