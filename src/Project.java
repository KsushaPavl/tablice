import java.util.Scanner;

public class Project {

    static double[] history = new double[5];
    static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Wpisz działanie (np. 2 + 3), \"historia\" albo \"koniec\":");
            String first = scanner.next();

            if (first.equals("koniec")) {
                break;
            }
            if (first.equals("historia")) {
                printHistory();
                continue;
            }

            double a = Double.parseDouble(first);
            String operator = scanner.next();
            double b = Double.parseDouble(scanner.next());

            double result;
            if (operator.equals("+")) {
                result = a + b;
            } else if (operator.equals("-")) {
                result = a - b;
            } else if (operator.equals("*")) {
                result = a * b;
            } else if (operator.equals("/")) {
                if (b == 0) {
                    System.out.println("Nie można dzielić przez zero");
                    continue;
                }
                result = a / b;
            } else {
                System.out.println("Nieznany operator: " + operator);
                continue;
            }

            System.out.println("Wynik: " + result);
            addToHistory(result);
        }

        printHistory();
    }

    static void addToHistory(double result) {
        if (count == history.length) {
            double[] bigger = new double[history.length * 2];
            for (int i = 0; i < history.length; i++) {
                bigger[i] = history[i];
            }
            history = bigger;
        }
        history[count] = result;
        count++;
    }

    static void printHistory() {
        if (count == 0) {
            System.out.println("Historia jest pusta");
            return;
        }
        System.out.println("Historia wyników:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + history[i]);
        }
    }
}
