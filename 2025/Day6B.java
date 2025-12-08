import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6B {

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }

        int numRows = lines.size();
        int numCols = lines.stream().mapToInt(String::length).max().orElse(0);

        lines.replaceAll(s -> String.format("%-" + numCols + "s", s));

        BigInteger grandTotal = BigInteger.ZERO;
        int col = numCols - 1;

        while (col >= 0) {
            if (isSpaceColumn(lines, col)) {
                col--;
                continue;
            }

            List<BigInteger> numbers = new ArrayList<>();
            String operator = "";

            while (col >= 0 && !isSpaceColumn(lines, col)) {
                StringBuilder numStr = new StringBuilder();
                for (int row = 0; row < numRows - 1; row++) {
                    char c = lines.get(row).charAt(col);
                    if (c != ' ') {
                        numStr.append(c);
                    }
                }
                if (!numStr.isEmpty()) {
                    numbers.add(new BigInteger(numStr.toString()));
                }

                char op = lines.get(numRows - 1).charAt(col);
                if (op == '+' || op == '*') {
                    operator = String.valueOf(op);
                }
                col--;
            }

            if (!numbers.isEmpty() && !operator.isEmpty()) {
                BigInteger result = operator.equals("*") ? BigInteger.ONE : BigInteger.ZERO;
                for (BigInteger num : numbers) {
                    result = operator.equals("+") ? result.add(num) : result.multiply(num);
                }
                grandTotal = grandTotal.add(result);
            }
        }

        System.out.println("Final Solution: " + grandTotal);
    }

    private static boolean isSpaceColumn(List<String> lines, int col) {
        for (String line : lines) {
            if (col < line.length() && line.charAt(col) != ' ') {
                return false;
            }
        }
        return true;
    }
}
