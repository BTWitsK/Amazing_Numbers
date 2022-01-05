package numbers;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number: ");
        int number = scanner.nextInt();

        if (number < 1) {
            System.out.println("This number is not natural!");
        } else {
            boolean doesEndIn7 = number % 10 == 7;
            boolean isDivisible7 = number % 7 == 0;
            boolean isBuzz = doesEndIn7 || isDivisible7;

            String parity = number % 2 == 0 ? "This number is Even" : "This number is Odd";
            String buzz = isBuzz ? "It is a Buzz number." : "It is not a Buzz number.";
            String explanation = isBuzz ?
                    doesEndIn7 && isDivisible7 ? "is divisible by 7 and ends with 7."
                            : doesEndIn7 ? "ends with 7." : "is divisible by 7."
                    : "is neither divisible by 7 nor does it end with 7";

            System.out.printf("%s\n%s\nExplanation:\n%d %s", parity, buzz, number, explanation);
        }
    }
}
