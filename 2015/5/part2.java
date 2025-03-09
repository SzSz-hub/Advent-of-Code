import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> messages = new ArrayList<>();
        while(sc.hasNextLine()){
            messages.add(sc.nextLine());
        }
        sc.close();

        int niceStrings = 0;
        for (String message : messages){
            if (isNice(message)){
                niceStrings++;
            }
        }

        System.out.println("Nice strings: " + niceStrings);
    }

    public static boolean isNice(String message){
        return hasDoublePairs(message) && hasDoubleLetterWithGap(message);
    }

    public static boolean hasDoublePairs(String message){
        for (int i = 0; i < message.length() - 1; i++){
            String pair = message.substring(i, i + 2);
            if (message.substring(i + 2).contains(pair)){
                return true;
            }
        }
        return false;
    }

    public static boolean hasDoubleLetterWithGap(String message){
        for (int i = 0; i < message.length() - 2; i++){
            if (message.charAt(i) == message.charAt(i + 2)){
                return true;
            }
        }
        return false;
    }
}