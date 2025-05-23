import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> accounting = new ArrayList<>();

        while (sc.hasNext()) {
            accounting.add(sc.nextInt());
        }
        sc.close();

        for (int i = 0; i < accounting.size(); i++) {
            for (int j = 0; j < accounting.size(); j++) {
                if (i != j && accounting.get(i) + accounting.get(j) == 2020) {
                    System.out.println(accounting.get(i) * accounting.get(j));
                }
            }
        }
    }
}
