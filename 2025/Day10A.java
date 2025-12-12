import java.util.*;

public class Day10A {
    public static void main(String[] args) {
        int totalPresses = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                totalPresses += processLine(line);
            }
        }
        System.out.println(totalPresses);
    }

    private static int processLine(String line) {
        String[] words = line.split(" ");
        Machine machine = new Machine(words[0].replaceAll("[\\[\\]]", ""));

        List<Button> buttons = new ArrayList<>();
        for (int i = 1; i < words.length - 1; i++) {
            buttons.add(new Button(words[i].replaceAll("[()]", "")));
        }
        return countPresses(buttons, machine);
    }

    private static int countPresses(List<Button> buttons, Machine targetMachine) {
        Machine currentMachine = new Machine(".".repeat(targetMachine.indicatorLights.length));

        if (currentMachine.equals(targetMachine)) {
            return 0;
        }

        Set<Machine> visited = new HashSet<>();
        Queue<Machine> queue = new LinkedList<>();
        queue.add(currentMachine);
        visited.add(currentMachine);
        int presses = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            presses++;

            for (int i = 0; i < levelSize; i++) {
                Machine machine = queue.poll();

                for (Button button : buttons) {
                    Machine newMachine = machine.clone();
                    newMachine.pushButton(button);

                    if (newMachine.equals(targetMachine)) {
                        return presses;
                    }

                    if (!visited.contains(newMachine)) {
                        visited.add(newMachine);
                        queue.add(newMachine);
                    }
                }
            }
        }

        return -1;
    }

}

class Machine implements Cloneable {
    boolean[] indicatorLights;

    Machine(String line) {
        indicatorLights = new boolean[line.length()];
        for (int i = 0; i < line.length(); i++) {
            indicatorLights[i] = line.charAt(i) == '#';
        }
    }

    public void pushButton(Button button) {
        for (int pos : button.positions) {
            indicatorLights[pos] = !indicatorLights[pos];
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Machine machine = (Machine) obj;
        return Arrays.equals(indicatorLights, machine.indicatorLights);
    }


    @Override
    public Machine clone() {
        Machine cloned = new Machine(".".repeat(indicatorLights.length));
        cloned.indicatorLights = this.indicatorLights.clone();
        return cloned;
    }
}

class Button {
    List<Integer> positions;

    Button(String line) {
        String[] parts = line.split(",");
        positions = new ArrayList<>();
        for (String part : parts) {
            positions.add(Integer.parseInt(part.trim()));
        }
    }
}
