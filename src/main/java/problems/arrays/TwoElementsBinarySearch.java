package problems.arrays;

import java.util.Comparator;

public class TwoElementsBinarySearch {

    /**
     * Modified two elements binary search. Keeps searching and doing comparisons of two nearest indexes X and X - 1
     * until condition.compare(a[X-1], a[X]) != 0;
     *
     * @param a source array
     * @param fromIndex start index (inclusive)
     * @param toIndex end index (exclusive)
     * @param condition comparison condition
     * @return target key position or -(insert position + 1)
     */
    private static int twoElementsBinarySearch(int[] a, int fromIndex, int toIndex, Comparator<Integer> condition) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            if (mid < 1 || condition.compare(a[mid - 1], a[mid]) > 0)
                high = mid - 1;
            else if (condition.compare(a[mid - 1], a[mid]) < 0)
                low = mid + 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }
}
