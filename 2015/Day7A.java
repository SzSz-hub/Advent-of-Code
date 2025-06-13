import java.util.*;

public class Day7A {
    private static final SortedMap<String, Integer> wires = new TreeMap<>();
    private static final int UNSIGNED_SHORT_MASK = 0xFFFF; // 65535
    private static final List<String> parseLater = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String line = sc.nextLine();
            parseLine(line);
        }
        sc.close();

        while (!parseLater.isEmpty()) {
            parseLine(parseLater.removeFirst());
        }

        printWires();
    }

    private static void parseLine(String line) {
        String[] parts = line.split(" ");
        switch (parts.length) {
            case 3 -> {
                Integer value = getValue(parts[0]);
                if (value != null) {
                    storeWire(parts[2], value);
                } else {
                    parseLater.add(line);
                }
            }
            case 4 -> {
                Integer value = getValue(parts[1]);
                if (value != null) {
                    negateWire(value, parts[3]);
                } else {
                    parseLater.add(line);
                }
            }
            case 5 -> {
                Integer value1 = getValue(parts[0]);
                Integer value2 = getValue(parts[2]);
                
                if (value1 != null) {
                    switch (parts[1]) {
                        case "AND" -> {
                            if (value2 != null) {
                                andWire(value1, value2, parts[4]);
                            } else {
                                parseLater.add(line);
                            }
                        }
                        case "OR" -> {
                            if (value2 != null) {
                                orWire(value1, value2, parts[4]);
                            } else {
                                parseLater.add(line);
                            }
                        }
                        default -> shiftWire(value1, parts[1], Integer.parseInt(parts[2]), parts[4]);
                    }
                } else {
                    parseLater.add(line);
                }
            }
            default -> System.out.println("Invalid line: " + line);
        }
    }

    private static Integer getValue(String input) {
        if (isIntegerParsable(input)) {
            return Integer.parseInt(input);
        } else if (isWireStored(input)) {
            return getWireValue(input);
        }
        return null; // Value not available yet
    }

    private static void storeWire(String wire, int value) {
        wires.put(wire, toUnsignedShort(value));
    }

    private static void negateWire(int value, String targetWire) {
        wires.put(targetWire, toUnsignedShort(~value));
    }

    private static void shiftWire(int value, String direction, int shift, String targetWire) {
        if (direction.equals("LSHIFT"))
            wires.put(targetWire, toUnsignedShort(value << shift));
        else
            wires.put(targetWire, toUnsignedShort(value >> shift));
    }

    private static void andWire(int value1, int value2, String targetWire) {
        wires.put(targetWire, toUnsignedShort(value1 & value2));
    }

    private static void orWire(int value1, int value2, String targetWire) {
        wires.put(targetWire, toUnsignedShort(value1 | value2));
    }

    private static boolean isWireStored(String wire) {
        return wires.containsKey(wire);
    }

    private static int getWireValue(String wire) {
        return wires.get(wire);
    }

    private static int toUnsignedShort(int value) {
        return value & UNSIGNED_SHORT_MASK;
    }

    private static void printWires() {
        System.out.println(Arrays.toString(wires.entrySet().toArray()));
    }

    public static boolean isIntegerParsable(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}