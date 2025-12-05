import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Day5B {
    private static final List<Range> ranges = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger interval = BigInteger.ZERO;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.contains("-")) {
                parseRange(line);
            }
        }
        scanner.close();
        mergeRanges();

        for(Range range: ranges) {
            interval = interval.add(range.getInterval());
        }

        System.out.println(interval);
    }

    private static void parseRange(String line) {
        String[] parts = line.split("-");
        long start = Long.parseLong(parts[0]);
        long end = Long.parseLong(parts[1]);

        for(Range range: ranges) {
            if(range.isInRange(start) && range.isInRange(end)) {
                return;
            }
            if (range.isInRange(start)) {
                range.end = end;
                return;
            }
            if (range.isInRange(end)) {
                range.start = start;
                return;
            }
            if (start <= range.end + 1 && end >= range.start - 1) {
                range.start = Math.min(range.start, start);
                range.end = Math.max(range.end, end);
                return;
            }
        }
        ranges.add(new Range(start, end));
    }

    private static void mergeRanges(){
        int rangesOldSize;
        do {
            rangesOldSize = ranges.size();
            List<Range> toMerge = new ArrayList<>(ranges);
            ranges.clear();
            for (Range range: toMerge) {
                parseRange(range.start + "-" + range.end);
            }
            System.err.println(rangesOldSize + " " + ranges.size());
        } while (rangesOldSize != ranges.size());
    }
}

class Range {
    long start;
    long end;

    public void setStart(long start) {
        this.start = start;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public boolean isInRange(long number) {
        return number >= start && number <= end;
    }

    public BigInteger getInterval() {
        return BigInteger.valueOf(end - start + 1);
    }
}
