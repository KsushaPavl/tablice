import java.util.Arrays;

public class Lesson {
/*
 * =====================================================
 * LEKCJA — TABLICE
 * =====================================================
 *
 * Teoria w Notatki.java, pomiary w Performance.java.
 *
 * UWAGA: poprzednia wersja tego pliku rzucała
 * ArrayIndexOutOfBoundsException w środku main() i przerywała
 * wykonanie — przykład został przeniesiony do bloku try/catch,
 * żeby cały main mógł się przejść.
 */

    public static void main(String[] args) {

        // =====================================================
        // 1. TWORZENIE TABLICY
        // =====================================================

        int[] empty = new int[3];                // [0, 0, 0]
        int[] values = {10, 20, 30, 40, 50};     // literał
        String[] words = new String[3];          // [null, null, null]

        System.out.println("empty:  " + Arrays.toString(empty));
        System.out.println("values: " + Arrays.toString(values));
        System.out.println("words:  " + Arrays.toString(words));

        // =====================================================
        // 2. ODCZYT, ZAPIS, length
        // =====================================================

        empty[0] = 5;
        empty[1] = 7;
        System.out.println("po edycji: " + Arrays.toString(empty));
        System.out.println("długość:   " + values.length);    // bez nawiasów — to POLE

        // =====================================================
        // 3. PĘTLE PO TABLICY
        // =====================================================

        for (int i = 0; i < values.length; i++) {
            System.out.println("for[" + i + "] = " + values[i]);
        }

        for (int v : values) {
            System.out.println("for-each: " + v);
        }

        // =====================================================
        // 4. ArrayIndexOutOfBoundsException — KONTROLOWANY DEMO
        // =====================================================

        try {
            System.out.println(values[100]);    // poza zakresem
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Złapane: " + e.getMessage());
        }

        // klasyczne off-by-one — i <= length zamiast <
        try {
            for (int i = 0; i <= values.length; i++) {
                System.out.print(values[i] + " ");
            }
            System.out.println();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nZłapane off-by-one: " + e.getMessage());
        }

        // =====================================================
        // 5. REFERENCJE — KOPIOWANIE TABLICY
        // =====================================================

        int[] a = {1, 2, 3};
        int[] alias = a;        // nie kopia — to ten sam obiekt!
        alias[0] = 999;
        System.out.println("a po edycji aliasu: " + Arrays.toString(a));   // [999, 2, 3]

        int[] copy = a.clone();
        copy[0] = -1;
        System.out.println("a po edycji clone:  " + Arrays.toString(a));   // [999, 2, 3]
        System.out.println("copy:               " + Arrays.toString(copy));

        // System.arraycopy — najszybsze kopiowanie (natywne)
        int[] dst = new int[5];
        System.arraycopy(a, 0, dst, 2, 3);
        System.out.println("arraycopy: " + Arrays.toString(dst));

        // =====================================================
        // 6. Arrays.equals vs ==
        // =====================================================

        int[] x = {1, 2, 3};
        int[] y = {1, 2, 3};
        System.out.println("x == y:           " + (x == y));            // false
        System.out.println("x.equals(y):      " + x.equals(y));         // false (Object.equals)
        System.out.println("Arrays.equals:    " + Arrays.equals(x, y)); // true

        // =====================================================
        // 7. SORTOWANIE I BINARY SEARCH
        // =====================================================

        int[] random = {5, 2, 9, 1, 7, 3};
        Arrays.sort(random);
        System.out.println("sortowane: " + Arrays.toString(random));

        int idx = Arrays.binarySearch(random, 7);
        System.out.println("indeks 7:  " + idx);

        // BinarySearch dla nieistniejącego elementu
        int idxMissing = Arrays.binarySearch(random, 100);
        // zwraca -(punkt_wstawienia) - 1 ; informacja o miejscu, gdzie powinno być
        System.out.println("brak 100, kod: " + idxMissing);

        // =====================================================
        // 8. PRZEKAZYWANIE TABLICY DO METODY
        // =====================================================

        int[] arg = {10, 20, 30};
        zeroFirst(arg);
        System.out.println("po zeroFirst: " + Arrays.toString(arg));

        // =====================================================
        // 9. TABLICA WIELOWYMIAROWA — JAGGED
        // =====================================================

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // klasyczna iteracja
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(Arrays.deepToString(matrix));

        // jagged — wiersze różnej długości
        int[][] jagged = new int[3][];
        jagged[0] = new int[]{1};
        jagged[1] = new int[]{1, 2};
        jagged[2] = new int[]{1, 2, 3};
        System.out.println("jagged: " + Arrays.deepToString(jagged));

        // =====================================================
        // 10. Arrays.fill / copyOfRange
        // =====================================================

        int[] filled = new int[5];
        Arrays.fill(filled, 7);
        System.out.println("fill: " + Arrays.toString(filled));

        int[] slice = Arrays.copyOfRange(values, 1, 4);
        System.out.println("slice [1..4): " + Arrays.toString(slice));
    }

    private static void zeroFirst(int[] a) {
        a[0] = 0;   // zmiana widoczna na zewnątrz — tablica to obiekt
    }
}
