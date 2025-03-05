import java.util.Scanner;

public class MyClass {
  public static void main(String args[]) {
      Scanner sc = new Scanner(System.in);
      Long result = 0l;
      while(sc.hasNext()){
        result += calcuateWrappingPaper(sc.nextLine());
      }
      sc.close();
      System.out.println("They should order a total of " + result + " feet ribbon.");
  }
  
  public static int calcuateWrappingPaper(String input){
    var nums = java.util.Arrays.stream(input.split("x")).mapToInt(Integer::parseInt).toArray();
    int wrap =  2 * (nums[0] + nums[1] + nums[2] - Math.max(Math.max(nums[0], nums[1]), nums[2]));
    int bowl = nums[0] * nums[1] * nums[2];
    return wrap + bowl;
  }
}
