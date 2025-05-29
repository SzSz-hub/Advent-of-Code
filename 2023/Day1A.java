import java.util.Scanner;

public class Day1A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        while (sc.hasNext()) {
            String[] line = sc.nextLine().replaceAll("[^\\d.]", "").split("");
            sum += Integer.parseInt(line[0] + line[line.length - 1]);
        }
        sc.close();

        System.out.println(sum);
    }
}
