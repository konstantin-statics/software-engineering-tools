package leetcode.util;

import leetcode.input.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListNodeParserTest {

    @Test
    void testHappyPath() {
        ListNode expected = new ListNode(1);
        expected.next = new ListNode(-2);
        expected.next.next = new ListNode(3);

        ParseResult<ListNode> result = Parsers.listNodeParser().parseLine("[1,-2,3]");

        assertEquals(3, result.getSize());
        assertListsEqual(expected, result.getOutput());
    }

    @Test
    void testSingleValue() {
        ListNode expected = new ListNode(1);

        ParseResult<ListNode> result = Parsers.listNodeParser().parseLine("[1]");

        assertEquals(1, result.getSize());
        assertListsEqual(expected, result.getOutput());
    }

    @Test
    void testEmptyList() {
        ParseResult<ListNode> result = Parsers.listNodeParser().parseLine("[]");

        assertEquals(0, result.getSize());
        assertNull(result.getOutput());
    }

    @Test
    void testEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> Parsers.listNodeParser().parseLine(""));
    }

    @Test
    void testIllegalCharacter() {
        assertThrows(IllegalArgumentException.class, () -> Parsers.listNodeParser().parseLine("[2,5,n]"));
    }

    @Test
    void testNoProperEnclosure() {
        assertThrows(IllegalArgumentException.class, () -> Parsers.listNodeParser().parseLine("[2,5"));
    }

    @Test
    void testCorruptedStructure() {
        assertThrows(IllegalArgumentException.class, () -> Parsers.listNodeParser().parseLine("[2,,6]"));
    }

    @Test
    void testNullInput() {
        assertThrows(NullPointerException.class, () -> Parsers.listNodeParser().parseLine(null));
    }

    private static void assertListsEqual(ListNode expected, ListNode actual) {
        assertFalse(expected == null ^ actual == null);
        while (expected != null && actual != null) {
            assertEquals(expected.val, actual.val);
            expected = expected.next;
            actual = actual.next;
        }
        assertTrue(expected == null && actual == null);
    }
}
