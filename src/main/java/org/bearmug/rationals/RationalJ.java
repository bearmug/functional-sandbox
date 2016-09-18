package org.bearmug.rationals;

/**
 * Class to compare rationals implementation for Scala and Java.
 */
public interface RationalJ {
    static RationalJ rational(int n, int d) {
        return new RationalImpl(n, d);
    }

    static RationalJ rational(int n) {
        return new RationalImpl(n, 1);
    }
}
