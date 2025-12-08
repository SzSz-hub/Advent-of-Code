import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Day6A {

    public static void main(String[] args) {
        BigInteger solution = BigInteger.ZERO;
        List<Integer[]> input = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.trim().split(" +");
                if(parts[0].equals("*") || parts[0].equals("+")){
                    for (int i = 0; i < parts.length; i++) {
                        solution = solution.add(calculateResult(input, parts[i], i));
                    }

                } else{
                    Integer[] numbers = Arrays.stream(parts)
                            .map(Integer::parseInt)
                            .toArray(Integer[]::new);
                    input.add(numbers);
                }
            }
            System.out.println("Final Solution: " + solution);
        }
    }

    private static BigInteger calculateResult(List<Integer[]> input, String operator, int index) {
        BigInteger result = operator.equals("*") ? BigInteger.ONE : BigInteger.ZERO;
        for (Integer[] numbers : input) {
            if (operator.equals("+")) {
                result = result.add(BigInteger.valueOf(numbers[index]));
            } else if (operator.equals("*")) {
                result = result.multiply(BigInteger.valueOf(numbers[index]));
            }
        }
        return result;
    }
}
