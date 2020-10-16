package problems.arrays;

public class BinarySearch {

    /**
     * Java implementation of binary search for arrays of integers.
     *
     * @param a source array;
     * @param fromIndex start index (inclusive)
     * @param toIndex end index (exclusive)
     * @param key target key
     * @return target key position or -(insert position + 1)
     */
    private static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }
}
