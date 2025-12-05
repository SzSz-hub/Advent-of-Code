
import java.math.BigInteger;
import java.util.Scanner;

public class Day2B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String[] rangeValues = scanner.nextLine().split("[,-]");
        scanner.close();
        BigInteger totalSum = BigInteger.ZERO;

        for (int i = 0; i < rangeValues.length; i += 2) {
            long rangeStart = Long.parseLong(rangeValues[i]);
            long rangeEnd = Long.parseLong(rangeValues[i + 1]);
            Range currentRange = new Range(rangeStart, rangeEnd);
            
            totalSum = totalSum.add(currentRange.getSumOfInvalidIDs());
        }
      
        System.out.println(totalSum);
    }
}

class Range {
    private final long start;
    private final long end;

    Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public BigInteger getSumOfInvalidIDs() {
        BigInteger sum = BigInteger.ZERO;
        
        for (long id = start; id <= end; id++) {
            if (hasRepeatingPattern(id)) {
                sum = sum.add(BigInteger.valueOf(id));
            }
        }
        
        return sum;
    }


    private boolean hasRepeatingPattern(long number) {
        char[] digits = String.valueOf(number).toCharArray();
        int digitCount = digits.length;

        for (int patternLength = 1; patternLength <= digitCount / 2; patternLength++) {
            if (digitCount % patternLength != 0) {
                continue;
            }

            if (allChunksMatch(digits, patternLength)) {
                return true;
            }
        }

        return false;
    }

    private boolean allChunksMatch(char[] digits, int chunkSize) {
        int chunkCount = digits.length / chunkSize;
        
        for (int chunkIndex = 0; chunkIndex < chunkCount - 1; chunkIndex++) {
            for (int positionInChunk = 0; positionInChunk < chunkSize; positionInChunk++) {
                int currentChunkPos = chunkIndex * chunkSize + positionInChunk;
                int nextChunkPos = (chunkIndex + 1) * chunkSize + positionInChunk;
                
                if (digits[currentChunkPos] != digits[nextChunkPos]) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
