package leetcode.util;

/**
 * Parser interface for various Leetcode tst input types.
 *
 * @param <T> parser output type
 */
public interface Parser<T> {

    /**
     * Parses a line of an output and returns parsed value.
     *
     * @param input raw input
     * @param index line index (starting from 0)
     * @return parse result
     */
    ParseResult<T> parse(String input, int index);
}
