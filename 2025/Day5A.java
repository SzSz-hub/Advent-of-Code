import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Day5A {
    private static final List<Range> ranges = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int freshCount = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.contains("-")) {
                parseRange(line);
            } else if (!line.isEmpty()){
                freshCount += isFresh(Long.parseLong(line)) ? 1 : 0;
            }
        }
        scanner.close();
        System.out.println(freshCount);
    }

    private static void parseRange(String line) {
        String[] parts = line.split("-");
        long start = Long.parseLong(parts[0]);
        long end = Long.parseLong(parts[1]);
        ranges.add(new Range(start, end));
    }

    private static boolean isFresh(long number){
        for(Range range : ranges){
            if(range.isInRange(number)){
                return true;
            }
        }
        return false;
    }
}

class Range {
    long start;
    long end;

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public boolean isInRange(long number) {
        return number >= start && number <= end;
    }
}
