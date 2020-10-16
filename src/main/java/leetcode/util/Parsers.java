package leetcode.util;

import common.Exceptions;
import leetcode.input.ListNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Util class with a set of parsers for different Leetcode test inputs.
 *
 */
public class Parsers {

    public static final IntParser INT_PARSER_INSTANCE = new IntParser();
    public static final ListNodeParser LIST_NODE_PARSER_INSTANCE = new ListNodeParser();

    public static IntParser intParser() {
        return INT_PARSER_INSTANCE;
    }

    public static ListNodeParser listNodeParser() {
        return LIST_NODE_PARSER_INSTANCE;
    }

    /**
     * Abstract line aware parser, skips lines in an input, then delegates actual line parsing to a concrete
     * implementation.
     *
     * @param <T> parser output type
     */
    public static abstract class LineParser<T> implements Parser<T> {

        @Override
        public ParseResult<T> parse(String input, int index) {
            final String line = getLineByIndex(input, index);
            if (line != null) {
                return parseLine(line);
            } else {
                throw new NullPointerException("Input line with index " + input + " not found");
            }
        }

        public static String getLineByIndex(String input, int index) {
            String output = null;
            try (BufferedReader reader = new BufferedReader(new StringReader(input))) {
                output = reader.lines().skip(index).findFirst().orElse(null);
            } catch (IOException e) {
                Exceptions.sneakyThrow(e);
            }
            return output;
        }

        protected abstract ParseResult<T> parseLine(String line);
    }

    public static class IntParser extends LineParser<Integer> {

        @Override
        protected ParseResult<Integer> parseLine(String line) {
            return ParseResult.of(Integer.valueOf(line), 1);
        }
    }

    public static class ListNodeParser extends LineParser<ListNode> {

        public static final String UNEXPECTED_CHAR_ERR_MSG = "Unexpected character at position ";

        @Override
        protected ParseResult<ListNode> parseLine(String line) {
            if (line == null) {
                throw new NullPointerException("Unable to parse null input");
            }
            final ListNode holderNode = new ListNode();
            ListNode tailNode = holderNode;
            Integer currentVal = null;
            boolean currentNegative = false;
            int size = 0;

            line = line.trim();

            if (line.length() < 2) {
                throw new IllegalArgumentException("Input length is less than 2 (\"[]\")");
            } else if (line.length() == 2) {
                if (line.charAt(0) != '[' || line.charAt(line.length() - 1) != ']') {
                    throw new IllegalArgumentException("Input must have both enclosing characters (\"[]\")");
                } else {
                    return ParseResult.of(null, 0);
                }
            }

            for (int pos = 1; pos < line.length() - 1; pos ++) {
                final char c = line.charAt(pos);

                if (c >= '0' && c <= '9') {
                    currentVal = currentVal == null ? c - '0' : currentVal * 10 + (c - '0');
                } else if (c == '-') {
                    validateValAtPos(currentVal, true, pos);
                    currentNegative = true;
                } else if (c == ',') {
                    tailNode = createNodeAtPos(tailNode, currentVal, currentNegative, pos);
                    currentVal = null;
                    currentNegative = false;
                    size ++;
                } else if (c != ' ') {
                    throw new IllegalArgumentException(UNEXPECTED_CHAR_ERR_MSG + pos);
                }
            }

            createNodeAtPos(tailNode, currentVal, currentNegative, line.length() - 2);
            size ++;

            return ParseResult.of(holderNode.next, size);
        }

        private static ListNode createNodeAtPos(ListNode parent, Integer val, boolean negative, int pos) {
            validateValAtPos(val, false, pos);
            ListNode newNode = new ListNode(negative ? - val : val);
            parent.next = newNode;
            return newNode;
        }

        private static void validateValAtPos(Integer val, boolean isNull, int pos) {
            if (isNull ^ val == null) {
                throw new IllegalArgumentException(UNEXPECTED_CHAR_ERR_MSG + pos);
            }
        }
    }
}
