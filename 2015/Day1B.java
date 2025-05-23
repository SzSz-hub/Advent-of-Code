import java.util.Scanner;

public class Day1B {
    public static void main(String[] args) {
        int floor = 0;
        int counter = 0;

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        for (char c : input.toCharArray()) {
            floor += c == '(' ? 1 : -1;
            counter++;

            if (floor < 0){
                System.out.println("First time went to basement after " + counter + " instructions.");
                break;
            }
        }
    }
}
