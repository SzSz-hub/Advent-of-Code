package year2015;

import java.util.Scanner;

public class Day8B {
    public static void main(String[] args) {
        int totalCodeChars = 0;
        int totalEncodedChars = 0;

        try(Scanner input = new Scanner(System.in)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();

                totalCodeChars += line.length();
                totalEncodedChars += calculateMemoryChars(line);
            }
        }

        int difference = totalEncodedChars - totalCodeChars;
        System.out.println(difference);
    }

    private static int calculateMemoryChars(String line) {
        if (line.length() <= 2) {
            return 6;
        }

        int encodedChars = line.length() + 4; // start with original length + 4 for surrounding quotes and escaping existing quotes
        for (int i = 1; i < line.length() - 1; i++) {
            char currentChar = line.charAt(i);
            if (currentChar == '\\') {
                char nextChar = line.charAt(i + 1);
                switch (nextChar) {
                    case '\\', '"' -> {
                        encodedChars += 2;
                        i++;
                    }
                    case 'x' -> {
                        encodedChars++;
                        i++; // skip 'x'
                    }
                    default -> {
                        encodedChars++;
                    }
                }
            } else if (currentChar == '"') {
                encodedChars += 2;
            }
        }
        return encodedChars;
    }
}
