package utils;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner sc = new Scanner(System.in);

    public static String nextLine(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    public static int nextInt(String message) {
        System.out.print(message);
        int num = sc.nextInt();
        sc.nextLine(); // clear buffer
        return num;
    }
}
