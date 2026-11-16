public class Notatki {
/*
 * =====================================================
 * NOTATKI — INTERFEJS List I JEGO IMPLEMENTACJE
 * =====================================================
 *
 *
 * =====================================================
 * 1. CZYM JEST List
 * =====================================================
 *
 * UPORZĄDKOWANA kolekcja elementów. Cechy:
 *   - zachowuje KOLEJNOŚĆ dodania
 *   - DOPUSZCZA DUPLIKATY
 *   - dostęp PRZEZ INDEKS (get(i), set(i, x))
 *   - elementy mogą być null
 *
 * Najczęściej używane implementacje:
 *   - ArrayList       — dynamiczna tablica
 *   - LinkedList      — lista podwójnie wiązana
 *   - Vector          — przestarzała, synchronizowana ArrayList (NIE używaj)
 *   - CopyOnWriteArrayList — thread-safe, do specyficznych zastosowań
 *
 *
 * =====================================================
 * 2. ArrayList — JAK TO DZIAŁA POD SPODEM
 * =====================================================
 *
 * Pod spodem to TABLICA z dodatkową logiką.
 *
 *   ArrayList<String> list = new ArrayList<>();    // domyślna pojemność 10
 *                                                   // (dokładniej: tablica
 *                                                   // pusta, alokowana przy
 *                                                   // pierwszym add)
 *   list.add("a"); list.add("b"); ...
 *
 * STRUKTURA:
 *   - pole Object[] elementData
 *   - pole int size                 (ile faktycznie zajętych)
 *
 * GDY DODAJEMY do PEŁNEJ:
 *   - tworzy NOWĄ tablicę większą o ~50%
 *   - kopiuje wszystko
 *   - poprzednia idzie do GC
 *
 * KOSZTY:
 *   - add(elem)           O(1) zamortyzowane (czasem realokacja)
 *   - get(index)          O(1)               — losowy dostęp
 *   - set(index, value)   O(1)
 *   - add(index, value)   O(n)               — przesuwanie elementów
 *   - remove(index)       O(n)
 *   - contains(value)     O(n)               — liniowe szukanie
 *
 * WYDAJNOŚĆ:
 *   - dane LEŻĄ CIĄGLE w pamięci (cache-friendly)
 *   - iteracja bardzo szybka
 *
 * OPTYMALIZACJA:
 *   new ArrayList<>(100_000)   // od razu odpowiednia pojemność
 *                              // — unika kilkunastu realokacji
 *
 *
 * =====================================================
 * 3. LinkedList — JAK TO DZIAŁA POD SPODEM
 * =====================================================
 *
 * Lista DWUKIERUNKOWO WIĄZANA. Każdy element to obiekt Node:
 *
 *   class Node<E> {
 *       E item;
 *       Node<E> prev;
 *       Node<E> next;
 *   }
 *
 * LISTA:
 *   head -> [a] <-> [b] <-> [c] <-> [d] <- tail
 *
 * KOSZTY:
 *   - add(elem)          O(1)              — na koniec (tail)
 *   - addFirst(elem)     O(1)              — na początek
 *   - add(index, value)  O(n)              — trzeba przejść do indeksu
 *   - get(index)         O(n)              — trzeba przejść!
 *   - set(index, value)  O(n)
 *   - remove(index)      O(n)
 *   - contains(value)    O(n)
 *
 * KIEDY LinkedList MA SENS?
 *   - Często wstawiasz/usuwasz NA POCZĄTKU (Deque, kolejka FIFO)
 *   - Czasem jako Deque<> (interfejs kolejki dwustronnej)
 *   - PRAWIE NIGDY indziej. ArrayDeque jest zwykle szybsza
 *     dla tych zastosowań.
 *
 * WADY:
 *   - Każdy element ma narzut ~24-32 bajtów (3 referencje + nagłówek)
 *   - Brak ciągłości pamięci — fatalnie dla cache
 *
 *
 * =====================================================
 * 4. List.of(...) I LISTY NIEMUTOWALNE
 * =====================================================
 *
 *   List<Integer> l = List.of(1, 2, 3);
 *
 * To zwraca NIEMUTOWALNĄ listę. Próba .add / .remove / .set
 * → UnsupportedOperationException.
 *
 * Inne niemutowalne / read-only:
 *   - Collections.unmodifiableList(list)    // widok read-only nad mutowalną
 *   - Arrays.asList(1, 2, 3)                // STAŁY ROZMIAR (można set,
 *                                            //  ale nie add/remove)
 *
 * Jeśli potrzebujesz mutowalnej kopii z niemutowalnej:
 *   List<Integer> copy = new ArrayList<>(List.of(1, 2, 3));
 *
 *
 * =====================================================
 * 5. NAJCZĘSTSZE METODY List
 * =====================================================
 *
 *   add(e)              — dodaj na końcu
 *   add(i, e)           — dodaj na pozycji i (przesuwa resztę)
 *   get(i)              — pobierz
 *   set(i, e)           — zastąp
 *   remove(i)           — usuń po indeksie (int)
 *   remove(Object)      — usuń pierwsze wystąpienie
 *   removeIf(predicate) — bezpiecznie usuwa wszystkie pasujące
 *   indexOf(e)          — pozycja pierwszego wystąpienia lub -1
 *   contains(e)         — true/false
 *   size()              — liczba elementów
 *   isEmpty()           — true gdy size == 0
 *   clear()             — wyczyść wszystko
 *   toArray() / toArray(T[]) — konwersja
 *   subList(from, to)   — UWAGA: WIDOK, nie kopia!
 *
 *
 * =====================================================
 * 6. ConcurrentModificationException — FAIL-FAST
 * =====================================================
 *
 *   for (String s : list) {
 *       if (s.startsWith("X")) list.remove(s);   // CME!
 *   }
 *
 * Bezpieczne sposoby:
 *   - list.removeIf(predicate)
 *   - iterator + Iterator.remove()
 *   - klasyczna pętla od końca (gdy modyfikujesz po indeksie)
 *   - iterowanie po KOPII
 *
 *
 * =====================================================
 * 7. subList — ZAWSZE WIDOK, NIE KOPIA
 * =====================================================
 *
 *   List<Integer> orig = new ArrayList<>(List.of(1,2,3,4,5));
 *   List<Integer> view = orig.subList(1, 4);   // WIDOK!
 *   view.set(0, 99);                            // zmienia orig
 *
 * Jeśli po utworzeniu widoku zmienisz OUTER listę strukturalnie
 * (add/remove), każde kolejne użycie widoku rzuci
 * ConcurrentModificationException.
 *
 * Potrzebna kopia → new ArrayList<>(orig.subList(1, 4))
 *
 *
 * =====================================================
 * 8. PRZEKSZTAŁCENIA — KIEDY List, KIEDY STREAM
 * =====================================================
 *
 * - Iteracja prosta → for-each / for
 * - Mapowanie / filtrowanie do nowej listy → stream + collect
 * - Modyfikacja IN-PLACE → replaceAll(), sort(Comparator)
 *
 *
 * =====================================================
 * 9. WYDAJNOŚĆ — KIEDY CO
 * =====================================================
 *
 * 99% PRZYPADKÓW:  ArrayList.
 *
 * KIEDY LinkedList:
 *   - intensywne addFirst/removeFirst (kolejka FIFO) — ale ArrayDeque lepsze.
 *
 * KIEDY List.of:
 *   - dane, które się nie zmieniają (np. konfiguracja, stałe).
 *
 * KIEDY CopyOnWriteArrayList:
 *   - dużo czytań, BARDZO mało zapisów, wielowątkowo (np. listenery).
 */
}
