package year2015;

public class Day20B {
    public static void main(String[] args) {
        int input = 36000000;

        for (int houseNumber = 1; houseNumber < input; houseNumber++) {
            if (sumOfDivisors(houseNumber) * 11 >= input) {
                System.out.println(houseNumber);
                break;
            }
        }
    }

    private static int sumOfDivisors(int n) {
        int sum = 0;
        for (int j = 1; j <= Math.sqrt(n); j++) {
            if (n % j == 0) {
                if (n / j <= 50) {
                    sum += j;
                }
                if (j != n / j && n / (n / j) <= 50) {
                    sum += n / j;
                }
            }
        }
        return sum;
    }
}
