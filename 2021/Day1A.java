import java.util.Scanner;

public class Day1A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int previous = 0;
        int increaseCounter = 0;

        while (sc.hasNext()) {
            int current = sc.nextInt();
            if (current > previous)
                increaseCounter++;
            previous = current;
        }
        sc.close();

        System.out.println(increaseCounter - 1);
    }
}
