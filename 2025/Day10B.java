import java.util.*;

public class Day10B {
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

        List<int[]> buttons = new ArrayList<>();
        for (int i = 1; i < words.length - 1; i++) {
            String[] parts = words[i].replaceAll("[()]", "").split(",");
            int[] positions = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
            buttons.add(positions);
        }

        int[] target = Arrays.stream(words[words.length - 1].replaceAll("[{}]", "").split(","))
                .mapToInt(Integer::parseInt).toArray();

        return solveLinearSystem(buttons, target);
    }

    private static int solveLinearSystem(List<int[]> buttons, int[] target) {
        int numButtons = buttons.size();
        int numPositions = target.length;

        double[][] augmented = new double[numPositions][numButtons + 1];
        for (int i = 0; i < numPositions; i++) {
            augmented[i][numButtons] = target[i];
        }
        for (int j = 0; j < numButtons; j++) {
            for (int pos : buttons.get(j)) {
                augmented[pos][j] = 1;
            }
        }

        int[] pivotCol = new int[numPositions];
        Arrays.fill(pivotCol, -1);
        int row = 0;

        for (int col = 0; col < numButtons && row < numPositions; col++) {
            int maxRow = row;
            for (int i = row + 1; i < numPositions; i++) {
                if (Math.abs(augmented[i][col]) > Math.abs(augmented[maxRow][col])) {
                    maxRow = i;
                }
            }

            if (Math.abs(augmented[maxRow][col]) < 1e-9) continue;

            double[] temp = augmented[row];
            augmented[row] = augmented[maxRow];
            augmented[maxRow] = temp;

            pivotCol[row] = col;

            for (int i = 0; i < numPositions; i++) {
                if (i != row && Math.abs(augmented[i][col]) > 1e-9) {
                    double factor = augmented[i][col] / augmented[row][col];
                    for (int k = col; k <= numButtons; k++) {
                        augmented[i][k] -= factor * augmented[row][k];
                    }
                }
            }
            row++;
        }

        Set<Integer> pivotCols = new HashSet<>();
        for (int i = 0; i < row; i++) {
            if (pivotCol[i] >= 0) pivotCols.add(pivotCol[i]);
        }

        List<Integer> freeVars = new ArrayList<>();
        for (int j = 0; j < numButtons; j++) {
            if (!pivotCols.contains(j)) freeVars.add(j);
        }

        if (freeVars.isEmpty()) {
            return extractSolution(augmented, pivotCol, row, numButtons, new int[0], new int[0]);
        }

        int maxVal = Arrays.stream(target).max().orElse(0);
        return searchFreeVars(augmented, pivotCol, row, numButtons, freeVars, 0, new int[freeVars.size()], maxVal);
    }

    private static int searchFreeVars(double[][] aug, int[] pivotCol, int numRows, int numButtons,
                                      List<Integer> freeVars, int idx, int[] freeVals, int maxVal) {
        if (idx == freeVars.size()) {
            return extractSolution(aug, pivotCol, numRows, numButtons, freeVars.stream().mapToInt(i -> i).toArray(), freeVals);
        }

        int best = Integer.MAX_VALUE;
        for (int v = 0; v <= maxVal; v++) {
            freeVals[idx] = v;
            int result = searchFreeVars(aug, pivotCol, numRows, numButtons, freeVars, idx + 1, freeVals, maxVal);
            if (result < best) best = result;
        }
        return best;
    }

    private static int extractSolution(double[][] aug, int[] pivotCol, int numRows, int numButtons,
                                       int[] freeVarCols, int[] freeVals) {
        double[] solution = new double[numButtons];

        for (int i = 0; i < freeVarCols.length; i++) {
            solution[freeVarCols[i]] = freeVals[i];
        }

        for (int i = numRows - 1; i >= 0; i--) {
            int col = pivotCol[i];
            if (col < 0) continue;

            double val = aug[i][numButtons];
            for (int j = 0; j < numButtons; j++) {
                if (j != col) val -= aug[i][j] * solution[j];
            }
            solution[col] = val / aug[i][col];
        }

        int sum = 0;
        for (double v : solution) {
            long rounded = Math.round(v);
            if (rounded < 0 || Math.abs(v - rounded) > 1e-6) return Integer.MAX_VALUE;
            sum += (int) rounded;
        }
        return sum;
    }
}
