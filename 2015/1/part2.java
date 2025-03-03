public class MyClass {
    public static void main(String[] args) {
        int floor = 0;
        int counter = 0;
        
        for (char c : args[0].toCharArray()) {
            floor += c == '(' ? 1 : -1;
            counter++;
            
            if (floor < 0){
                System.out.println("First time went to basement after " + counter + " instructions.");
                break;
            }
        }
    }
}
