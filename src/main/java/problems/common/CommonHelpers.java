package problems.common;

import java.util.AbstractQueue;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.function.Consumer;

public class CommonHelpers {

    /*
    Compressed call wrapper for function calls, useful mainly for call stacks.
     */
    public static class CompactCallWrapper {
        private final Object[] source;
        public CompactCallWrapper(Object[] source) {this.source = source;}
        public static CompactCallWrapper wrap(Object... args) {return new CompactCallWrapper(args);}
        public static CompactCallWrapper allocate(int size) {return new CompactCallWrapper(new Object[size]);}
        public CompactCallWrapper set(int key, Object value) {source[key] = value; return this;}
        public CompactCallWrapper set(Object... args) {for(int i = 0; i < args.length; i ++) source[i] = args[i]; return this;}
        public CompactCallWrapper compute(Consumer<Object[]> sourceConsumer) {sourceConsumer.accept(source); return this;}
        public <T> T get(int key) {return (T) source[key];}
    }
}
