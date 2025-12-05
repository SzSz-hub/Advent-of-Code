import java.math.BigInteger;
import java.util.Scanner;


public class Day3B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger sum = BigInteger.ZERO;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            sum = sum.add(calculateJoltage(line));
        }
        scanner.close();
        System.out.println(sum);
    }

    private static BigInteger calculateJoltage(String line){
        final int JOLTAGE_LENGTH = 12;
        StringBuilder joltageBuilder = new StringBuilder();

        char[] digits = line.toCharArray();
        int highestIndex = -1;
        for(int digit = 1; digit <= JOLTAGE_LENGTH; digit++){
            int highestNumber = 0;
            for (int i = highestIndex + 1; i < digits.length - (JOLTAGE_LENGTH - digit) ; i++) {
                if (Character.getNumericValue(digits[i]) > highestNumber) {
                    highestNumber = Character.getNumericValue(digits[i]);
                    highestIndex = i;
                }
            }
            joltageBuilder.append(highestNumber);
        }
        return new BigInteger(joltageBuilder.toString());
    }
}
