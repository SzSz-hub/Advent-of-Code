import java.util.Scanner;

public class Day1A {
    public static void main(String[] args) {
        final int STARTING_POSITION = 50;
        final int LOWEST_POSITION = 0;
        final int HIGHEST_POSITION = 99;
        final int TOTAL_POSITION = 100;

        Scanner sc = new Scanner(System.in);
        int hits = 0;
        int position = STARTING_POSITION;

        while(sc.hasNext()){
            String line  = sc.nextLine();
            char direction = line.charAt(0);
            int rotation = Integer.parseInt(line.substring(1));

            position += direction == 'L' ? -rotation : rotation;
            if (position % TOTAL_POSITION == 0) {
                hits++;
            }
        }

        System.out.println(hits);
    }
}
