import java.util.Scanner;

public class Day1A {
    public static void main(String[] args) {
        int floor = 0;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        for (char c : input.toCharArray()) {
            floor += c == '(' ? 1 : -1;
        }

        System.out.println("Final floor is " + floor + '.');
    }
}
