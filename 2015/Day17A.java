package year2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day17A {
    public static void main(String[] args){
        List<Integer> containers = new ArrayList<>();
        int target = 150;

        try(Scanner scanner = new Scanner(System.in)){
            while(scanner.hasNextInt()){
                containers.add(scanner.nextInt());
            }
        }
        int[] containerArray = containers.stream().mapToInt(i -> i).toArray();
        System.out.println(countCombinations(containerArray, target, 0));
    }

    private static int countCombinations(int[] containers, int target, int index){
        if(target == 0){
            return 1;
        }
        if(target < 0 || index >= containers.length){
            return 0;
        }

        // Include the current container
        int include = countCombinations(containers, target - containers[index], index + 1);

        // Exclude the current container
        int exclude = countCombinations(containers, target, index + 1);

        return include + exclude;
    }
}
