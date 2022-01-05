package numbers;
import java.util.*;

class Number {
    private int num;
    private boolean duck;
    private boolean buzz;
    private boolean even;

    public Number(int number) {
        if (number < 1) {
            System.out.println("This number is not natural!");
            return;
        }
        this.num = number;
        this.duck = String.valueOf(this.num).contains("0");
        this.buzz = this.num % 10 == 7 || this.num % 7 == 0;
        this.even = this.num % 2 == 0;
    }


    public int getNum() {
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

    public void printProperties() {
        System.out.printf("Properties of %d\n", this.num);
        System.out.printf("even: %b\n", this.even);
        System.out.printf("odd: %b\n", !this.even);
        System.out.printf("buzz: %b\n", this.buzz);
        System.out.printf("duck: %b\n", this.duck);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number: ");
        Number number = new Number(scanner.nextInt());

        number.printProperties();


    }
}
