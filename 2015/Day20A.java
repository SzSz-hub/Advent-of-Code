package year2015;

public class Day20A {
    public static void main(String[] args) {
        int input = 36000000;

        for (int houseNumber = (int) Math.sqrt(input) ; houseNumber < input; houseNumber++) {
            if (sumOfDivisors(houseNumber) * 10 >= input) {
                System.out.println(houseNumber);
                break;
            }
        }
    }

    private static int sumOfDivisors(int n) {
        int sum = 0;
        for (int j = 1; j <= Math.sqrt(n); j++) {
            if (n % j == 0) {
                sum += j;
                if (j != n / j) {
                    sum += n / j;
                }
            }
        }
        return sum;
    }
}
