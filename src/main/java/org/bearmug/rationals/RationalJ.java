package org.bearmug.rationals;

public class RationalJ {

    private final int number;
    private final int denominator;

    public static RationalJ rational(int number, int denominator) {
        return new RationalJ(number, denominator);
    }

    public static RationalJ rational(int number) {
        return new RationalJ(number, 1);
    }

    private RationalJ(int number, int denominator) {
        this.number = number;
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        return number + "/" + denominator;
    }

    public RationalJ negateUnary() {
        return new RationalJ(-number, denominator);
    }

    public RationalJ multiply(RationalJ other) {
        return RationalJ.rational(number * other.number, denominator * other.denominator);
    }

    public RationalJ divide(RationalJ other) {
        return RationalJ.rational(number * other.denominator, denominator * other.number);
    }
}
