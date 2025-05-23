import java.util.Scanner;

public class Day2B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long result = 0L;
        while(sc.hasNext()){
            result += calculateWrappingPaper(sc.nextLine());
        }
        sc.close();
        System.out.println("They should order a total of " + result + " feet ribbon.");
    }

    public static int calculateWrappingPaper(String input){
        var numbers = java.util.Arrays.stream(input.split("x")).mapToInt(Integer::parseInt).toArray();
        int wrap =  2 * (numbers[0] + numbers[1] + numbers[2] - Math.max(Math.max(numbers[0], numbers[1]), numbers[2]));
        int bowl = numbers[0] * numbers[1] * numbers[2];
        return wrap + bowl;
    }
}
