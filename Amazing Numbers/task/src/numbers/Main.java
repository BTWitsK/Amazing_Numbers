package numbers;
import java.util.*;

class Number {
    private final long num;
    private final boolean duck;
    private final boolean buzz;
    private final boolean even;
    private final boolean palindromic;
    private final boolean gapful;

    public Number(long number) {
        this.num = number;
        this.duck = String.valueOf(this.num).contains("0");
        this.buzz = this.num % 10 == 7 || this.num % 7 == 0;
        this.even = this.num % 2 == 0;
        this.palindromic = isPalindromic(number);
        this.gapful = isGapful(number);
    }

    public boolean isGapful(long number) {
        char[] array = String.valueOf(number).toCharArray();
        int firstDigit = Character.digit(array[0], 10);
        int lastDigit = Character.digit(array[array.length - 1], 10);
        int concact = firstDigit * 10 + lastDigit;

        return String.valueOf(number).length() > 2 && number % concact == 0;
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
        System.out.printf("gapful: %b\n", this.gapful);

    }

    public static void printList(List<Number> list) {
        StringBuilder output = new StringBuilder();

        for (Number number : list) {
            output.append(number.num).append(" is");

            if (number.buzz) {
                output.append(" buzz,");
            }
            if (number.duck) {
                output.append(" duck,");
            }
            if (number.gapful) {
                output.append(" gapful,");
            }
            if (number.palindromic) {
                output.append(" palindromic,");
            }
            if (number.even) {
                output.append(" even.");
            } else {
                output.append(" odd.");
            }
            output.append("\n");
        }

        System.out.println(output);
    }

    public static void printInstructions(){
        System.out.print("Welcome to Amazing Numbers!\n\n Supported requests:\n");
        System.out.print("- enter a natural number to know its properties;\n");
        System.out.print("- enter two natural numbers to obtain the properties of the list:\n");
        System.out.print(" * the first parameter represents a starting number; \n");
        System.out.print(" * the second parameter shows how many consecutive numbers are to be processed\n");
        System.out.print("- separate the parameters with one space;\n");
        System.out.print("- enter 0 to exit.\n");
    }

    public static List<String> inputRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a request: ");
        String input = scanner.nextLine();

        if (" ".equals(input) || "".equals(input)) {
            printInstructions();
            return inputRequest();
        }

        List<String> list = Arrays.asList(input.split(" "));

        try {
            Long.parseLong(list.get(0));
        } catch (NumberFormatException error) {
            System.out.println("The first parameter should be a natural number or zero");
            return inputRequest();
        }

        if (Long.parseLong(list.get(0)) < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            return inputRequest();
        }

        if (list.size() > 1 && Long.parseLong(list.get(1)) < 1) {
            System.out.println("The second parameter should be a natural number.");
            return inputRequest();
        }

        return list;
    }
}

public class Main {
    public static void main(String[] args) {
        Number.printInstructions();
        List<Number> numList = new ArrayList<>();
        List<String> requestList;
        long firstParam;

       do {
           requestList = Number.inputRequest();
           firstParam = Long.parseLong(requestList.get(0));

           if (requestList.size() == 1) {
               Number number = new Number(firstParam);
               number.printProperties();
           } else {
               long secondParam = Long.parseLong(requestList.get(1));
               for (int i = 0; i < secondParam; i++) {
                   numList.add(new Number(firstParam + i));
               }
               Number.printList(numList);
           }
       } while (firstParam != 0);

       System.out.print("Goodbye!");
    }
}