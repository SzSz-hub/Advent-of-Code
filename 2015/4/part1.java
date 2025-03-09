import java.util.Scanner;
import java.security.*;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        int number = 0;

        while(true){
            String input = message + number;
            String hash = getMD5Hash(input);
            if (hash.startsWith("00000")) {
                System.out.println("The lowest number is: " + number);
                System.out.println("MD5 hash: " + hash);
                break;
            }
            number++;
        }
    }

    public static String getMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}