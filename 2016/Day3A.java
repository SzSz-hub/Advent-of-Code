import java.util.Arrays;
import java.util.Scanner;

public class Day3A {
    public static void main(String[] args) {
        int triangleCount = 0;
        try(Scanner sc = new Scanner(System.in)){
            while(sc.hasNext()){
                int[] numbers = new int[3];
                for(int i = 0; i < 3; i++){
                    numbers[i] = sc.nextInt();
                }
                triangleCount += isTriange(numbers) ? 1 : 0;
            }
        }
        System.out.println(triangleCount);
    }

    private static boolean isTriange(int[] numbers){
        Arrays.sort(numbers);
        return numbers[0] + numbers[1] > numbers[2];
    }
}
