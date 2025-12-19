import java.util.*;

public class Day8B {
    private static final List<JunctionDistance> allDistances = new ArrayList<>();
    private static int connectionsToMake = 0;
    private static final List<Circuit> circuits = new ArrayList<>();

    public static void main(String[] args) {
        List<JunctionBox> boxes = new ArrayList<>();
        try(Scanner sc = new Scanner(System.in)){
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                int[] split = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                boxes.add(new JunctionBox(split[0], split[1], split[2]));
                connectionsToMake++;
            }
        }
        calculateDistance(boxes);
        System.out.println(createCircuit());
    }

    public static void calculateDistance(List<JunctionBox> boxes){
        for (int i = 0; i < boxes.size()-1; i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                allDistances.add(new JunctionDistance(boxes.get(i), boxes.get(j)));
            }
        }
        allDistances.sort(null);
    }

    public static int createCircuit(){
        JunctionBox boxA = null, boxB = null;
        for (int i = 0; i < allDistances.size(); i++) {
            boxA = allDistances.get(i).a;
            boxB = allDistances.get(i).b;

            Circuit circuitA = null;
            Circuit circuitB = null;

            for(Circuit c : circuits){
                if(c.isMember(boxA)){
                    circuitA = c;
                }
                if(c.isMember(boxB)){
                    circuitB = c;
                }
            }

            if(circuitA != null && circuitA == circuitB){
                continue;
            }

            if(circuitA != null && circuitB != null){
                circuitA.boxes.addAll(circuitB.boxes);
                circuitA.size = circuitA.boxes.size();
                circuits.remove(circuitB);
            }
            else if(circuitA != null){
                circuitA.boxes.add(boxB);
                circuitA.size = circuitA.boxes.size();
            }
            else if(circuitB != null){
                circuitB.boxes.add(boxA);
                circuitB.size = circuitB.boxes.size();
            }
            else{
                circuits.add(new Circuit(new HashSet<>(Arrays.asList(boxA, boxB))));
            }

            if(circuits.size() == 1 && circuits.get(0).size == connectionsToMake){
                break;
            }
        }
        assert boxA != null;
        System.out.println(boxA + " <-> " + boxB);
        return boxA.X * boxB.X;
    }
}

class Circuit implements Comparable<Circuit>{
    Set<JunctionBox> boxes;
    int size;

    Circuit(Set<JunctionBox> boxes){
        this.boxes = boxes;
        size = boxes.size();
    }

    public boolean isMember(JunctionBox box){
        return boxes.contains(box);
    }

    @Override
    public int compareTo(Circuit o) {
        if(o == null) return 1;
        if(o == this) return 0;
        return o.size - size;
    }
}

class JunctionDistance implements Comparable<JunctionDistance>{
    final JunctionBox a,b;
    final double distance;

    JunctionDistance(JunctionBox a, JunctionBox b){
        this.a = a;
        this.b = b;
        distance = a.distance(b);
    }

    @Override
    public String toString(){
        return String.format("%s -> %s = %f", a, b, distance);
    }

    @Override
    public int compareTo(JunctionDistance o) {
        if(o == null) return 1;
        if(o == this) return 0;

        return Double.compare(distance, o.distance);
    }
}

class JunctionBox{
    int X,Y,Z;

    JunctionBox(int x, int y, int z){
        X = x;
        Y = y;
        Z = z;
    }

    public double distance(JunctionBox other){
        return Math.sqrt(Math.pow(X - other.X, 2) + Math.pow(Y - other.Y, 2) + Math.pow(Z - other.Z, 2));
    }

    @Override
    public String toString(){
        return String.format("(%d,%d,%d)", X, Y, Z);
    }
}
