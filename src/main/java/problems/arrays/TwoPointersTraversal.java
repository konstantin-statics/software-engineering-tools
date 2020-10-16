package problems.arrays;

import problems.common.CommonHelpers;

import java.util.function.Predicate;

public class TwoPointersTraversal extends CommonHelpers {

    /**
     * Loops over an array of integers while predicate condition is satisfied. Predicate call wrapper structure is:</br>
     * <ol>
     *     <li>Index 0: Source array - int[]</li>
     *     <li>Index 1: Pointer 1 - int</li>
     *     <li>Index 2: Pointer 2 - int</li>
     *     <li>Indexes 3...(N-3): Other variables</li>
     * </ol></br>
     * Predicate output: true to continue, false to stop
     * @param arr source array
     * @param pointer1 pointer 1
     * @param pointer2 pointer 2
     * @param loopPredicate loop predicate
     * @param variables other variables
     */
    private static CompactCallWrapper twoPointerTraversal(int[] arr, int pointer1, int pointer2,
                                                          Predicate<CompactCallWrapper> loopPredicate, Object... variables) {
        CompactCallWrapper callWrapper = CompactCallWrapper
                .allocate(3 + variables.length)
                .set(0, arr)
                .set(1, pointer1)
                .set(2, pointer2);
        for (int i = 3; i < 3 + variables.length; i++) callWrapper.set(i, variables[i - 3]);
        boolean testResult = true;
        while (testResult) {
            testResult = loopPredicate.test(callWrapper);
        }
        return callWrapper;
    }

    /**
     * Loops over an array of objects while predicate condition is satisfied. Predicate call wrapper structure is:</br>
     * <ol>
     *     <li>Index 0: Source array - Object[]</li>
     *     <li>Index 1: Pointer 1 - int</li>
     *     <li>Index 2: Pointer 2 - int</li>
     *     <li>Indexes 3...(N-3): Other variables</li>
     * </ol></br>
     * Predicate output: true to continue, false to stop
     * @param arr source array
     * @param pointer1 pointer 1
     * @param pointer2 pointer 2
     * @param loopPredicate loop predicate
     * @param variables other variables
     */
    private static CompactCallWrapper twoPointerTraversal(Object[] arr, int pointer1, int pointer2,
                                                          Predicate<CompactCallWrapper> loopPredicate, Object... variables) {
        CompactCallWrapper callWrapper = CompactCallWrapper
                .allocate(3 + variables.length)
                .set(0, arr)
                .set(1, pointer1)
                .set(2, pointer2);
        for (int i = 3; i < 3 + variables.length; i++) callWrapper.set(i, variables[i - 3]);
        boolean testResult = true;
        while (testResult) {
            testResult = loopPredicate.test(callWrapper);
        }
        return callWrapper;
    }
}
