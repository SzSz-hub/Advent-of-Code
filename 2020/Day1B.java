import java.util.*;

public class Day1B {
    public static void main(String[] args) {
        final int TARGET = 2020;
        Scanner sc = new Scanner(System.in);
        List<Integer> accounting = new ArrayList<>();

        while (sc.hasNext()) {
            accounting.add(sc.nextInt());
        }
        sc.close();

        Collections.sort(accounting);
        for (int i = 0; i < accounting.size() - 2; i++) {
            int left = i + 1;
            int right = accounting.size() - 1;

            while (left < right) {
                int sum = accounting.get(i) + accounting.get(left) + accounting.get(right);
                if (sum == TARGET) {
                    System.out.println(accounting.get(i) * accounting.get(left) * accounting.get(right));
                    return;
                } else if (sum < TARGET)
                    left++;
                else
                    right--;
            }
        }
        System.out.println("fail");
    }
}
