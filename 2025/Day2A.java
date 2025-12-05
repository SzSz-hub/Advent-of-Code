
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Day2A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ranges = sc.nextLine().split("[,-]");
        BigInteger solution = BigInteger.ZERO;

        for (int i = 0; i < ranges.length; i += 2) {
            Range current = new Range(Long.parseLong(ranges[i]), Long.parseLong(ranges[i + 1]));
            solution = solution.add(current.getSumofInvalidIDs());
        }
        sc.close();
        System.out.println(solution);
    }
}

class Range{
    long start;
    long end;

    Range(long start, long end){
        this.start = start;
        this.end = end;
    }

    public BigInteger getSumofInvalidIDs(){
        BigInteger sum = BigInteger.ZERO;
        for (long i = start; i <= end; i++){
            if (isInvalid(i)){
                sum = sum.add(BigInteger.valueOf(i));
            }
        }
        return sum;
    }

    private boolean isInvalid(long number){
        long lengthOfNumber = String.valueOf(number).length();
        if (lengthOfNumber % 2 == 1) return false;

        long splitter = (long) Math.pow(10, (double) lengthOfNumber / 2);

        if ((Math.floor((double) number /splitter) == number % splitter)){
            System.err.println("Invalid number: " + number);
            return true;
        }
        return false;
    }
}
