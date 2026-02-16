package year2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day17B {
    private static int minContainers = Integer.MAX_VALUE;
    private static int minContainerCount = 0;

    public static void main(String[] args){
        List<Integer> containers = new ArrayList<>();
        int target = 150;

        try(Scanner scanner = new Scanner(System.in)){
            while(scanner.hasNextInt()){
                containers.add(scanner.nextInt());
            }
        }
        int[] containerArray = containers.stream().mapToInt(i -> i).toArray();

        findMinContainers(containerArray, target, 0, 0);
        System.out.println(minContainerCount);
    }

    private static void findMinContainers(int[] containers, int target, int index, int usedCount){
        if(target == 0){
            if(usedCount < minContainers){
                minContainers = usedCount;
                minContainerCount = 1;
            } else if(usedCount == minContainers){
                minContainerCount++;
            }
            return;
        }
        if(target < 0 || index >= containers.length || usedCount > minContainers){
            return;
        }
        // Include the current container
        findMinContainers(containers, target - containers[index], index + 1, usedCount + 1);

        // Exclude the current container
        findMinContainers(containers, target, index + 1, usedCount);
    }
}
