import java.util.Scanner;


public class Day3A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            sum += calculateJoltage(line);
        }
        scanner.close();
        System.out.println(sum);
    }

    private static int calculateJoltage(String line){
        char[] digits = line.toCharArray();
        int highestNumber = 0;
        int highestIndex = -1;

        for(int i = 0; i < digits.length - 1; i++){
            int currentDigit = Character.getNumericValue(digits[i]);
            if(currentDigit > highestNumber){
                highestNumber = currentDigit;
                highestIndex = i;
            }
        }

        int secondHighestNumber = 0;
        for (int i = highestIndex + 1; i < digits.length; i++) {
            int currentDigit = Character.getNumericValue(digits[i]);
            if(currentDigit > secondHighestNumber){
                secondHighestNumber = currentDigit;
            }
        }

        return highestNumber * 10 + secondHighestNumber;
    }
}
