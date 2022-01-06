package numbers;
import java.util.*;

class Number {
    private long num;
    private boolean duck;
    private boolean buzz;
    private boolean even;
    private boolean palindromic;

    public Number(long number) {
        this.num = number;
        this.duck = String.valueOf(this.num).contains("0");
        this.buzz = this.num % 10 == 7 || this.num % 7 == 0;
        this.even = this.num % 2 == 0;
        this.palindromic = isPalindromic(number);
    }


    public long getNum() {
        return this.num;
    }

    public boolean isDuck() {
        return this.duck;
    }

    public boolean isBuzz() {
        return this.buzz;
    }

    public boolean isEven() {
        return this.even;
    }

    public boolean isPalindromic(long number) {
        char[] array = String.valueOf(number).toCharArray();
        int first = 0;
        int last = array.length - 1;

        while (first < last) {
            if (array[first] != array[last]) {
                return false;
            }
            first++;
            last--;
        }
        return true;
    }

    public void printProperties() {
        System.out.printf("Properties of %s\n", num);
        System.out.printf("even: %b\n", this.even);
        System.out.printf("odd: %b\n", !this.even);
        System.out.printf("buzz: %b\n", this.buzz);
        System.out.printf("duck: %b\n", this.duck);
        System.out.printf("palindromic: %b\n", this.palindromic);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to Amazing Numbers!\n\n Supported requests:\n");
        System.out.print("- enter a natural number to know its properties;\n- enter 0 to exit.\n");
        System.out.println("Enter a request: ");
        long input = scanner.nextLong();


        while (input != 0) {

            if (input < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                System.out.println("Enter a request: ");
                input = scanner.nextLong();
                continue;
            }

            Number number = new Number(input);
            number.printProperties();

            System.out.println("Enter a request: ");
            input = scanner.nextLong();
        }
        System.out.print("Goodbye!");
    }
}
