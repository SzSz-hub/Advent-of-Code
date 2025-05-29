import java.util.*;

public class Day1B {
    public static void main(String[] args) {
        List<Integer> left = new ArrayList<>();
        Map<Integer, Integer> right = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        boolean leftRead = true;

        while(sc.hasNext()){
            int current = sc.nextInt();
            if(leftRead){
                left.add(current);
            } else {
                right.put(current, right.getOrDefault(current, 0) + 1);
            }
            leftRead = !leftRead;
        }

        Integer total = 0;
        for(int i : left){
            if(right.containsKey(i)){
                total += i * right.get(i);
            }
        }

        System.out.println(total);
    }
}
