import java.util.Scanner;

public class Day1A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalFuel = 0;

        while (sc.hasNext()){
            int current = sc.nextInt();
            totalFuel += fuelRequired(current);
        }
        sc.close();

        System.out.println(totalFuel);
    }

    public static int fuelRequired(int mass) {
        return (mass / 3) - 2;
    }
}