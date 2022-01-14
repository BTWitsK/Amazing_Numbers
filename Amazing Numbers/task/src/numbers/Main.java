package numbers;
import java.util.*;

enum Properties {
    DUCK("duck"),
    BUZZ("buzz"),
    EVEN("even"),
    ODD("odd"),
    PALINDROMIC("palindromic"),
    GAPFUL("gapful"),
    SPY("spy"),
    SUNNY("sunny"),
    SQUARE("square"),
    JUMPING("jumping");

    final String property;

    Properties(String property) {
        this.property = property;
    }

    public static boolean containsProperty(String value) {
        for (Properties properties: Properties.values()) {
            if (properties.property.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static String getProperties() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Properties properties : Properties.values()) {
            stringBuilder.append(String.format("%s, ", properties.property));
        }
        return stringBuilder.toString();
    }
}

class Number {
    private final long num;
    private final boolean duck;
    private final boolean buzz;
    private final boolean even;
    private final boolean odd;
    private final boolean palindromic;
    private final boolean gapful;
    private final boolean spy;
    private final boolean sunny;
    private final boolean square;
    private final boolean jumping;
    List<Properties> properties;
    final static String[] exclusive = {"[even, odd]", "[odd, even]", "[duck, spy]",
            "[spy, duck]", "[sunny, square]", "[square, sunny]"};
    final static String mutuallyExclusive = "even odd duck spy sunny square";
    static Properties availableProperties;

    public Number(long number) {
        this.num = number;
        this.duck = String.valueOf(this.num).contains("0");
        this.buzz = this.num % 10 == 7 || this.num % 7 == 0;
        this.even = this.num % 2 == 0;
        this.odd = !this.even;
        this.palindromic = isPalindromic(number);
        this.gapful = isGapful(number);
        this.spy = isSpy(number);
        this.sunny = isSquare(number + 1);
        this.square = isSquare(number);
        this.jumping = isJumping(number);
        this.properties = setProperties(number);
    }

    public boolean isJumping(long number) {
        String[] numbers = String.valueOf(number).split("");

        if (numbers.length == 1) {
            return true;
        }

        for (int i = 1; i < numbers.length ; i++) {
            long dif = Math.abs(Long.parseLong(numbers[i - 1]) - Long.parseLong(numbers[i]));
            if (dif != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isSquare(long number) {
        return Math.sqrt(number) % 1 == 0;
    }

    public boolean isSpy(long number) {
        String[] numList = String.valueOf(number).split("");
        long sum = 0;
        long mul = 1;

        for (String num : numList) {
            sum += Integer.parseInt(num);
            mul *= Integer.parseInt(num);
        }
        return sum == mul;
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

    public List<Properties> setProperties(long number) {
        List<Properties> list = new ArrayList<>();

        if (number % 10 == 7 || number % 7 == 0) {
            list.add(Properties.BUZZ);
        }
        if (String.valueOf(number).contains("0")) {
            list.add(Properties.DUCK);
        }
        if (isGapful(number)) {
            list.add(Properties.GAPFUL);
        }
        if (isPalindromic(number)) {
            list.add(Properties.PALINDROMIC);
        }
        if (isSpy(number)) {
            list.add(Properties.SPY);
        }
        if (isSquare(number)) {
            list.add(Properties.SQUARE);
        }
        if (isSquare(number + 1)) {
            list.add(Properties.SUNNY);
        }
        if (isJumping(number)) {
            list.add(Properties.JUMPING);
        }
        if (this.num % 2 == 0) {
            list.add(Properties.EVEN);
        } else {
            list.add(Properties.ODD);
        }
        return list;
    }

    public boolean containsProperty(String value) {
        for (Properties properties : this.properties) {
            if (properties.property.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public void printProperties() {
        System.out.printf("Properties of %s\n", num);
        System.out.printf("even: %b\n", this.even);
        System.out.printf("odd: %b\n", this.odd);
        System.out.printf("buzz: %b\n", this.buzz);
        System.out.printf("duck: %b\n", this.duck);
        System.out.printf("palindromic: %b\n", this.palindromic);
        System.out.printf("gapful: %b\n", this.gapful);
        System.out.printf("spy: %b\n", this.spy);
        System.out.printf("sunny: %b\n", this.sunny);
        System.out.printf("square: %b\n", this.square);
        System.out.printf("jumping: %b\n\n", this.jumping);
    }

    public static void printProperties(List<Number> list) {
        StringBuilder output = new StringBuilder();

        for (Number number : list) {
            output.append(number.num).append(" is");

            for (Properties properties : number.properties) {
                output.append(String.format("%s, ", properties.property));
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
        System.out.print(" * the second parameter shows how many consecutive numbers are to be printed\n");
        System.out.print("- two natural numbers and properties to search for;\n");
        System.out.print("- separate the parameters with one space;\n");
        System.out.print("- enter 0 to exit.\n");
    }

    public static int correctProperty(String property) {
        if (!Properties.containsProperty(property)) {
            System.out.printf("The Property [%s] is wrong.\n", property);
            System.out.printf("Available properties: %s\n", Properties.getProperties());
            return -1;
        }
        return 0;
    }

    public static int correctProperty(List<String> properties) {
        int count = 0;
        for (String property : properties) {
            for (String exclusiveProperty : mutuallyExclusive.split(" ")) {
                if (exclusiveProperty == property) {
                    count++;
                }
            }
        }
        if (count > 1) return -1;
        return 0;
    }

    public static int correctProperty(String firstProperty, String secondProperty) {
        String paramPair = String.format("[%s, %s]", firstProperty, secondProperty);

        if (!Properties.containsProperty(firstProperty) && Properties.containsProperty(secondProperty)) {
            System.out.printf("The Property [%s] is wrong.\n", firstProperty);
            System.out.printf("Available properties: %s\n", Properties.getProperties());
            return -1;
        }

        if (!Properties.containsProperty(secondProperty) && Properties.containsProperty(firstProperty)) {
            System.out.printf("The Property [%s] is wrong.\n", secondProperty);
            System.out.printf("Available properties: %s\n", Properties.getProperties());
            return -1;
        }

        if (!Properties.containsProperty(firstProperty) && !Properties.containsProperty(secondProperty)) {
            System.out.printf("The properties %s are wrong.\n", paramPair);
            System.out.printf("Available properties: %s\n", Properties.getProperties());
            return -1;
        }

        for (String pair : Number.exclusive) {
            if (pair.contains(paramPair)) {
                System.out.printf("The request contains mutually exclusive properties: %s\n", paramPair);
                System.out.println("There are no numbers with these properties.");
                return -1;
            }
        }
        return 0;
    }

    public static List<String> inputRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a request: \n");
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

        if (list.size() == 3 ){
            if (correctProperty(list.get(2).toLowerCase()) < 0) {
                return inputRequest();
            }
        }

        if (list.size() == 4) {
            if (correctProperty(list.get(2).toLowerCase(), list.get(3).toLowerCase()) < 0) {
                return inputRequest();
            }
        }

        if (list.size() > 4) {
            for (int i = 2; i < list.size(); i++) {
                String param = list.get(i);
                if (correctProperty(param) < 0) {
                    return inputRequest();
                }
            }
            /*if (correctProperty(list) < 0) {
                return inputRequest();
            }*/
        }
        return list;
    }

    public static void processRequest(List<String> requestList) {
        List<Number> numList = new ArrayList<>();
        Number number;
        long firstParam = Long.parseLong(requestList.get(0));
        int size = requestList.size();
        int counter = 0;

        switch (size) {
            case 1:
                number = new Number(firstParam);
                number.printProperties();
                break;

            case 2:
                long secondParam = Long.parseLong(requestList.get(1));

                for (int i = 0; i < secondParam; i++) {
                    number = new Number(firstParam + i);
                    numList.add(number);
                }
                printProperties(numList);
                break;

            case 3:
                secondParam = Long.parseLong(requestList.get(1));
                String thirdParam = requestList.get(2).toLowerCase();

                for (long i = firstParam; counter < secondParam; i++) {
                    number = new Number(i);
                    if (number.containsProperty(thirdParam)) {
                        numList.add(number);
                        counter++;
                    }
                }
                printProperties(numList);
                break;

            case 4:
                secondParam = Long.parseLong(requestList.get(1));
                thirdParam = requestList.get(2).toLowerCase();
                String fourthParam = requestList.get(3).toLowerCase();

                for (long i = firstParam; counter < secondParam; i++) {
                    number = new Number(i);
                    if (number.containsProperty(thirdParam) && number.containsProperty(fourthParam)) {
                        numList.add(number);
                        counter++;
                    }
                }
                printProperties(numList);
                break;

            default:
                secondParam = Long.parseLong(requestList.get(1));
                List<String> propertyList = new ArrayList<>();

                for (int i = 2; i < requestList.size(); i++) {
                    propertyList.add(requestList.get(i).toLowerCase());
                }

                for (long i = firstParam; counter < secondParam; i++) {
                    boolean containsAllProperties = true;
                    number = new Number(i);
                    for (String property : propertyList) {
                        if (!number.containsProperty(property)) {
                            containsAllProperties = false;
                            break;
                        }
                    }
                    if (containsAllProperties) {
                        numList.add(number);
                        counter++;
                    }
                }
                printProperties(numList);
        }
    }
}

    public class Main {
        public static void main(String[] args) {
            Number.printInstructions();
            long firstParam;

            do {
                List<String> requestList = Number.inputRequest();
                Number.processRequest(requestList);
                firstParam = Long.parseLong(requestList.get(0));

            } while (firstParam!= 0);

        System.out.print("Goodbye!");
    }
}