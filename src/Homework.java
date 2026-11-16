import java.util.Arrays;

public class Homework {

    public static void main(String[] args) {

        // Zadeklaruj tablice intów pierwszym sposobem, przypisz do niej wartosci, wypisz jej dlugosc i niektore elementy
        header("Tablica zadeklarowana pierwszym sposobem");
        int[] first = new int[5];
        first[0] = 4;
        first[1] = 8;
        first[2] = 15;
        first[3] = 16;
        first[4] = 23;
        System.out.println("Długość: " + first.length);
        System.out.println("Pierwszy element: " + first[0]);
        System.out.println("Trzeci element: " + first[2]);
        System.out.println("Ostatni element: " + first[first.length - 1]);

        // zadeklaruj tablice intow i przypisz do niej 8 liczb
        int[] numbers = {7, 2, 9, 4, 1, 6, 3, 8};

        // wyswietl cała tablice
        header("Cała tablica");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // wyswietl tylko liczby wieksze niz 4
        header("Liczby większe niż 4");
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > 4) {
                System.out.print(numbers[i] + " ");
            }
        }
        System.out.println();

        // policz sume elementów tablicy
        header("Suma elementów");
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        System.out.println(sum);

        // znajdz element najwiekszy
        header("Największy element");
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        System.out.println(max);

        // znajdz element najmniejszy
        header("Najmniejszy element");
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        System.out.println(min);

        // zadeklaruj tablicę długosci 20 i przypisz do niej liczby od 1 do 20
        int[] twenty = new int[20];
        for (int i = 0; i < twenty.length; i++) {
            twenty[i] = i + 1;
        }

        // wypiszmy te tablice odwrotnie, czyli od 20 do 1
        header("Od 20 do 1");
        for (int i = twenty.length - 1; i >= 0; i--) {
            System.out.print(twenty[i] + " ");
        }
        System.out.println();

        // wypiszmy liczby od 1 do 20 ale oddzielone przecinkiem
        header("Od 1 do 20 po przecinku");
        for (int i = 0; i < twenty.length; i++) {
            System.out.print(twenty[i]);
            if (i < twenty.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        // wypiszmy te liczby które sa parzyste z tej tablicy
        header("Liczby parzyste");
        for (int i = 0; i < twenty.length; i++) {
            if (twenty[i] % 2 == 0) {
                System.out.print(twenty[i] + " ");
            }
        }
        System.out.println();

        // Oblicz sume elementow tablicy doubli ale tylko tych ktore sa wieksze niz 3.8
        header("Suma doubli większych niż 3.8");
        double[] doubles = {2.5, 3.8, 4.25, 5.0, 1.2, 3.9, 7.75};
        double doubleSum = 0;
        for (int i = 0; i < doubles.length; i++) {
            if (doubles[i] > 3.8) {
                doubleSum += doubles[i];
            }
        }
        System.out.println(doubleSum);

        // oblicz sumę elementów ktore sa podzielne przez 3
        header("Suma elementów podzielnych przez 3");
        int[] forDivision = {3, 7, 9, 12, 15, 4, 6, 10, 21};
        int divisibleSum = 0;
        for (int i = 0; i < forDivision.length; i++) {
            if (forDivision[i] % 3 == 0) {
                divisibleSum += forDivision[i];
            }
        }
        System.out.println(divisibleSum);

        // Oblicz sume elementow ale tylko tych ktore stoja na nieparzystych indeksach
        header("Suma elementów na nieparzystych indeksach");
        int[] forIndexes = {10, 1, 20, 2, 30, 3, 40, 4};
        int oddIndexSum = 0;
        for (int i = 1; i < forIndexes.length; i += 2) {
            oddIndexSum += forIndexes[i];
        }
        System.out.println(oddIndexSum);

        //    Zadeklaruj tablicę stringów. Wypisz z nich wszystkie te których ilość literek a jest
        //    większa niż pozostałych literek.
        //    Przykład:
        //    Input: arbuz, anka, aabc, anakonda, alaas, bacada, aaboka
        //    Output: alaas, aaboka
        header("Słowa, w których 'a' jest więcej niż innych liter");
        String[] words = {"arbuz", "anka", "aabc", "anakonda", "alaas", "bacada", "aaboka"};
        for (int i = 0; i < words.length; i++) {
            int aCount = 0;
            for (int j = 0; j < words[i].length(); j++) {
                if (words[i].charAt(j) == 'a') {
                    aCount++;
                }
            }
            int otherCount = words[i].length() - aCount;
            if (aCount > otherCount) {
                System.out.print(words[i] + " ");
            }
        }
        System.out.println();

        //    Oblicz ile jest literek 'a' we wszystkich słowach, które znajdują się w tablicy stringów.
        header("Liczba liter 'a' we wszystkich słowach");
        int totalA = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (words[i].charAt(j) == 'a') {
                    totalA++;
                }
            }
        }
        System.out.println(totalA);

        // Posortuj tablicę rosnąco (bez gotowych metod)
        header("Sortowanie rosnąco (bubble sort)");
        int[] toSort = {5, 2, 9, 1, 7, 3};
        for (int i = 0; i < toSort.length - 1; i++) {
            for (int j = 0; j < toSort.length - 1 - i; j++) {
                if (toSort[j] > toSort[j + 1]) {
                    int temp = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(toSort));

        // Skopiuj elementy jednej tablicy do drugiej
        header("Kopiowanie tablicy");
        int[] source = {1, 2, 3, 4, 5};
        int[] target = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }
        target[0] = 100;
        System.out.println("source: " + Arrays.toString(source));
        System.out.println("target: " + Arrays.toString(target));

        // Znajdź drugi największy element
        header("Drugi największy element");
        int[] forSecond = {4, 9, 2, 9, 7, 1};
        int biggest = forSecond[0];
        for (int i = 1; i < forSecond.length; i++) {
            if (forSecond[i] > biggest) {
                biggest = forSecond[i];
            }
        }
        int secondBiggest = Integer.MIN_VALUE;
        for (int i = 0; i < forSecond.length; i++) {
            if (forSecond[i] < biggest && forSecond[i] > secondBiggest) {
                secondBiggest = forSecond[i];
            }
        }
        System.out.println(secondBiggest);

        // Usuń duplikaty z tablicy
        header("Usuwanie duplikatów");
        int[] withDuplicates = {3, 1, 3, 7, 1, 9, 7, 3};
        int[] temp = new int[withDuplicates.length];
        int uniqueCount = 0;
        for (int i = 0; i < withDuplicates.length; i++) {
            boolean found = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (temp[j] == withDuplicates[i]) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                temp[uniqueCount] = withDuplicates[i];
                uniqueCount++;
            }
        }
        int[] unique = new int[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            unique[i] = temp[i];
        }
        System.out.println(Arrays.toString(unique));
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
