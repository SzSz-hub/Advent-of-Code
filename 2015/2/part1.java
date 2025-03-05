import java.util.Scanner;

public class MyClass {
  public static void main(String args[]) {
      Scanner sc = new Scanner(System.in);
      Long result = 0l;
      while(sc.hasNext()){
        result += calcuateWrappingPaper(sc.nextLine());
      }
      sc.close();
      System.out.println("They should order a total of " + result + " square feet of wrapping paper.");
  }
  
  public static int calcuateWrappingPaper(String input){
    var nums = java.util.Arrays.stream(input.split("x")).mapToInt(Integer::parseInt).toArray();
    int lw = nums[0] * nums[1];
    int wh = nums[1] * nums[2];
    int hl = nums[0] * nums[2];
    return 2 * lw + 2 * wh + 2 * hl + Math.min(Math.min(lw, wh), hl); 
  }
}
