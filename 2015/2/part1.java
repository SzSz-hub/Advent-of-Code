import java.util.Scanner;

public class MyClass {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      long result = 0L;
      while(sc.hasNext()){
        result += calculateWrappingPaper(sc.nextLine());
      }
      sc.close();
      System.out.println("They should order a total of " + result + " square feet of wrapping paper.");
  }
  
  public static int calculateWrappingPaper(String input){
    var sizes = java.util.Arrays.stream(input.split("x")).mapToInt(Integer::parseInt).toArray();
    int lw = sizes[0] * sizes[1];
    int wh = sizes[1] * sizes[2];
    int hl = sizes[0] * sizes[2];
    return 2 * lw + 2 * wh + 2 * hl + Math.min(Math.min(lw, wh), hl); 
  }
}
