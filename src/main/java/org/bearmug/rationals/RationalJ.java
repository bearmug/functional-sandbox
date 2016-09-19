package org.bearmug.rationals;

public final class RationalJ {

    private final int num;
    private final int den;

    public static RationalJ rational(int number, int denominator) {
        return new RationalJ(number, denominator);
    }

    public static RationalJ rational(int number) {
        return new RationalJ(number, 1);
    }

    private RationalJ(int number, int denominator) {
        final int gcd = gcd(number, denominator);
        this.num = number/ gcd;
        this.den = denominator / gcd;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(RationalJ.class)) {
            return false;
        }
        RationalJ other = (RationalJ) obj;
        return num == other.num && den == other.den;
    }

    public RationalJ negateUnary() {
        return new RationalJ(-num, den);
    }

    public RationalJ multiply(RationalJ other) {
        return RationalJ.rational(num * other.num, den * other.den);
    }

    public RationalJ divide(RationalJ other) {
        return RationalJ.rational(num * other.den, den * other.num);
    }

    public RationalJ plus(RationalJ other) {
        return RationalJ.rational(num * other.den + other.num * den, den * other.den);
    }

    public RationalJ minus(RationalJ other) {
        return RationalJ.rational(num * other.den - other.num * den, den * other.den);
    }

    public boolean gt(RationalJ other) {
        return this.minus(other).num > 0;
    }

    public boolean less(RationalJ other) {
        return this.minus(other).num < 0;
    }

    public boolean gtEq(RationalJ other) {
        return !less(other);
    }

    public boolean lessEq(RationalJ other) {
        return !gt(other);
    }

    public RationalJ min(RationalJ other) {
        return this.lessEq(other) ? this : other;
    }

    public RationalJ max(RationalJ other) {
        return this.gtEq(other) ? this : other;
    }
}
