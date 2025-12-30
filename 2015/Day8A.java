package year2015;

import java.util.Scanner;

public class Day8A {
    public static void main(String[] args) {
        int totalCodeChars = 0;
        int totalMemoryChars = 0;

        try(Scanner input = new Scanner(System.in)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();

                totalCodeChars += line.length();
                totalMemoryChars += calculateMemoryChars(line);
            }
        }

        int difference = totalCodeChars - totalMemoryChars;
        System.out.println(difference);
    }

    private static int calculateMemoryChars(String line) {
        if (line.length() <= 2) {
            return 0;
        }

        int memoryChars = 0;
        for (int i = 1; i < line.length() - 1; i++) {
            char currentChar = line.charAt(i);
            if (currentChar == '\\') {
                char nextChar = line.charAt(i + 1);
                switch (nextChar) {
                    case '\\', '"' -> {
                        memoryChars++;
                        i++;
                    }
                    case 'x' -> {
                        memoryChars++;
                        i += 3;
                    }
                    default -> {
                        memoryChars++;
                    }
                }
            } else {
                memoryChars++;
            }
        }
        return memoryChars;
    }
}
