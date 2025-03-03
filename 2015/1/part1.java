public class MyClass {
    public static void main(String[] args) {
        int floor = 0;
        
        for (char c : args[0].toCharArray()) {
            floor += c == '(' ? 1 : -1;
        }

        System.out.println(floor);
    }
}
