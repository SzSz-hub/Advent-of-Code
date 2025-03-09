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
        return hasThreeVowels(message) && hasDoubleLetter(message) && !hasForbiddenStrings(message);
    }

    public static boolean hasThreeVowels(String message){
        int vowels = 0;
        for (int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                vowels++;
            }
        }
        return vowels >= 3;
    }

    public static boolean hasDoubleLetter(String message){
        for (int i = 0; i < message.length() - 1; i++){
            if (message.charAt(i) == message.charAt(i + 1)){
                return true;
            }
        }
        return false;
    }

    public static boolean hasForbiddenStrings(String message){
        return message.contains("ab") || message.contains("cd") || message.contains("pq") || message.contains("xy");
    }
}