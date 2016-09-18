package org.bearmug.rationals;

public class RationalImpl implements RationalJ {

    private final int n;
    private final int d;

    RationalImpl(int n, int d) {
        this.n = n;
        this.d = d;
    }

    @Override
    public String toString() {
        return n + "/" + d;
    }
}
