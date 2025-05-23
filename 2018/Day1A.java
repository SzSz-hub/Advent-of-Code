import java.util.Scanner;

public class Day1A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int frequency = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            frequency = changeFrequency(frequency, line);
        }
        System.out.println("The new frequency is: " + frequency + ".");
    }

    private static int changeFrequency(int frequency, String line){
        return frequency + Integer.parseInt(line);
    }
}
