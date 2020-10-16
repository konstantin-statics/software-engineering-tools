package leetcode.util;

/**
 * Immutable bean that represents a result of Leetcode test input parsing. Consists of:</br>
 * <ol>
 *     <li>Actual output</li>
 *     <li>Size of an output (if measurable)</li>
 * </ol>
 * @param <T>
 */
public final class ParseResult<T> {
    private final T output;
    private final int size;

    public ParseResult(T output, int size) {
        this.output = output;
        this.size = size;
    }

    public static <T> ParseResult<T> of(T output, int size) {
        return new ParseResult<>(output, size);
    }

    public T getOutput() {
        return output;
    }

    public int getSize() {
        return size;
    }
}
