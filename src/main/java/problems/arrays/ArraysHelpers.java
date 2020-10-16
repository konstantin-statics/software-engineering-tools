package problems.arrays;

import java.util.AbstractList;
import java.util.List;

public class ArraysHelpers {

    /**
     * Swaps two elements in an array of integers
     *
     * @param arr source array
     * @param a first element
     * @param b second element
     * @return source element
     */
    private static int[] swap(int[] arr, int a, int b) {
        int tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
        return arr;
    }

    /**
     * Swaps two elements in an array of objects
     *
     * @param arr source array
     * @param a first element
     * @param b second element
     * @return source element
     */
    private static <T> T[] swap(T[] arr, int a, int b) {
        T tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
        return arr;
    }

    /**
     * Wraps an array of primitive integers into basic implementation of List<Integer>. Does not support any operations
     * that can modify the size of the list.
     *
     * @param source source array
     * @return wrapped array
     */
    private static List<Integer> objectify(final int[] source) {
        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return source[index];
            }

            @Override
            public int size() {
                return source.length;
            }
        };
    }
}
