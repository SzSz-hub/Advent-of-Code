import java.util.Scanner;

public class part2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] input = sc.nextLine().toCharArray();
        int captcha = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == input[(i + input.length / 2) % input.length]) {
                captcha += Character.getNumericValue(input[i]);
            }
        }
        System.out.println("The solution is: " + captcha + ".");
    }
}