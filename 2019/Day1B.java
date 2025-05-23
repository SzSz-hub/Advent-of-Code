import java.util.Scanner;

public class Day1B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalFuel = 0;
        int tripFuel;

        while (sc.hasNext()) {
            int current = sc.nextInt();
            tripFuel = fuelRequired(current);
            while(tripFuel >= 0){
                totalFuel += tripFuel;
                tripFuel = fuelRequired(tripFuel);
            }
        }
        sc.close();

        System.out.println(totalFuel);
    }

    public static int fuelRequired(int mass) {
        return (mass / 3) - 2;
    }
}