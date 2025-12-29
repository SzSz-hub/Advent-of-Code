import java.util.*;

public class Day12A {
    private static final int PRESENT_COUNT = 6;

    private final List<List<Present>> variantsByPresent = new ArrayList<>();
    private final int[] cellCountByPresent = new int[PRESENT_COUNT];

    public static void main(String[] args) {
        new Day12A().run();
    }

    private void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            loadPresents(scanner);
            int solvableCount = countSolvablePuzzles(scanner);
            System.out.println(solvableCount);
        }
    }

    private void loadPresents(Scanner scanner) {
        for (int i = 0; i < PRESENT_COUNT; i++) {
            Present present = Present.readFrom(scanner);
            variantsByPresent.add(present.generateUniqueVariants());
            cellCountByPresent[i] = present.countCells();
        }
    }

    private int countSolvablePuzzles(Scanner scanner) {
        int count = 0;
        while (scanner.hasNextLine()) {
            Puzzle puzzle = Puzzle.readFrom(scanner);
            if (canPossiblyFit(puzzle) && solve(puzzle)) {
                count++;
            }
        }
        return count;
    }

    private boolean canPossiblyFit(Puzzle puzzle) {
        int totalCells = puzzle.getTotalCells();
        int neededCells = 0;
        for (int i = 0; i < PRESENT_COUNT; i++) {
            neededCells += puzzle.getPresentsToPlace()[i] * cellCountByPresent[i];
        }
        return neededCells <= totalCells;
    }

    private boolean solve(Puzzle puzzle) {
        List<Integer> placementOrder = buildPlacementOrder(puzzle);
        return solveRecursive(puzzle, placementOrder, 0);
    }

    private List<Integer> buildPlacementOrder(Puzzle puzzle) {
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < PRESENT_COUNT; i++) {
            for (int j = 0; j < puzzle.getPresentsToPlace()[i]; j++) {
                order.add(i);
            }
        }
        
        order.sort(Comparator.comparingInt(t -> variantsByPresent.get(t).size()));
        return order;
    }

    private boolean solveRecursive(Puzzle puzzle, List<Integer> placementOrder, int index) {
        if (index >= placementOrder.size()) {
            return true;
        }

        int presentType = placementOrder.get(index);
        List<Present> variants = variantsByPresent.get(presentType);

        for (int row = 0; row < puzzle.getRows(); row++) {
            for (int col = 0; col < puzzle.getCols(); col++) {
                for (Present variant : variants) {
                    if (puzzle.canPlace(variant, row, col)) {
                        puzzle.place(variant, row, col);
                        if (solveRecursive(puzzle, placementOrder, index + 1)) {
                            return true;
                        }
                        puzzle.remove(variant, row, col);
                    }
                }
            }
        }
        return false;
    }
}

class Present {
    private static final int SIZE = 3;
    private final boolean[][] shape;

    private Present(boolean[][] shape) {
        this.shape = shape;
    }

    public static Present readFrom(Scanner scanner) {
        scanner.nextLine(); // skip header
        boolean[][] shape = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < SIZE; j++) {
                shape[i][j] = line.charAt(j) == '#';
            }
        }
        scanner.nextLine(); // skip footer
        return new Present(shape);
    }

    public boolean isFilledAt(int row, int col) {
        return shape[row][col];
    }

    public int countCells() {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (shape[i][j]) count++;
            }
        }
        return count;
    }

    public Present rotate() {
        boolean[][] rotated = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                rotated[j][SIZE - 1 - i] = shape[i][j];
            }
        }
        return new Present(rotated);
    }

    public Present flip() {
        boolean[][] flipped = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                flipped[i][SIZE - 1 - j] = shape[i][j];
            }
        }
        return new Present(flipped);
    }

    public List<Present> generateUniqueVariants() {
        Set<String> seen = new HashSet<>();
        List<Present> variants = new ArrayList<>();

        Present current = this;
        for (int rotation = 0; rotation < 4; rotation++) {
            addIfUnique(current, seen, variants);
            addIfUnique(current.flip(), seen, variants);
            current = current.rotate();
        }
        return variants;
    }

    private void addIfUnique(Present variant, Set<String> seen, List<Present> list) {
        String key = variant.toKey();
        if (seen.add(key)) {
            list.add(variant);
        }
    }

    private String toKey() {
        StringBuilder sb = new StringBuilder();
        for (boolean[] row : shape) {
            for (boolean cell : row) {
                sb.append(cell ? '#' : '.');
            }
        }
        return sb.toString();
    }
}

class Puzzle {
    private static final int PRESENT_SIZE = 3;
    private final int rows;
    private final int cols;
    private final boolean[][] grid;
    private final int[] presentsToPlace;

    private Puzzle(int rows, int cols, int[] presentsToPlace) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];
        this.presentsToPlace = presentsToPlace;
    }

    public static Puzzle readFrom(Scanner scanner) {
        String[] parts = scanner.nextLine().split("x|: | ");
        return new Puzzle(
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[0]),
                new int[]{
                        Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                        Integer.parseInt(parts[6]), Integer.parseInt(parts[7])
                }
        );
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public int getTotalCells() { return rows * cols; }
    public int[] getPresentsToPlace() { return presentsToPlace; }

    public boolean canPlace(Present present, int row, int col) {
        for (int i = 0; i < PRESENT_SIZE; i++) {
            for (int j = 0; j < PRESENT_SIZE; j++) {
                if (present.isFilledAt(i, j)) {
                    int r = row + i;
                    int c = col + j;
                    if (r >= rows || c >= cols || grid[r][c]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void place(Present present, int row, int col) {
        setGridCells(present, row, col, true);
    }

    public void remove(Present present, int row, int col) {
        setGridCells(present, row, col, false);
    }

    private void setGridCells(Present present, int row, int col, boolean value) {
        for (int i = 0; i < PRESENT_SIZE; i++) {
            for (int j = 0; j < PRESENT_SIZE; j++) {
                if (present.isFilledAt(i, j)) {
                    grid[row + i][col + j] = value;
                }
            }
        }
    }
}
