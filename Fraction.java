package pl.com.bottega.commons.math.model;

import static pl.com.bottega.commons.math.utilits.MathUtilits.getSymbolCount;
import static pl.com.bottega.commons.math.utilits.PrintFraction.print;


public class Fraction {
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ZERO = new Fraction(0, 1);
    private int nominator, denominator;


    public Fraction(int nominator, int denominator) throws IllegalArgumentException {
        setNominator(nominator);
        setDenominator(denominator);
    }

    public Fraction(int nominator) {
        this(nominator, 10);
    }

    public Fraction(String literal) throws IllegalArgumentException {
        String[] parts = literal.split("/");
        if (parts.length != 2)
            throw new IllegalArgumentException("It is not a fraction");
        try {
            setNominator(Integer.parseInt(parts[0]));
            setDenominator(Integer.parseInt(parts[1]));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("It is not a fraction", ex);
        }
    }

    public Fraction add(Fraction addend) {
        if (addend == null)
            throw new IllegalArgumentException("Addend is not a fraction");
        if (denominator == addend.denominator) {
            return new Fraction(nominator + addend.nominator, denominator);
        } else {
            int resultNominator = nominator * addend.denominator + addend.nominator * denominator;
            int resultDenominator = denominator * addend.denominator;
            return new Fraction(resultNominator, resultDenominator);
        }
    }

    public Fraction reverse() throws IllegalStateException {
        if (nominator == 0)
            throw new IllegalStateException("Can not reverse zero");
        return new Fraction(denominator, nominator);
    }

    public String toString() {
        return print(this);
    }

    public String toString(PrintMode printMode) {
        return print(this, printMode);
    }

    public int getDenominator() {
        return denominator;
    }

    private void setDenominator(int denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException("Denominator can not be zero");
        this.denominator = denominator;
    }

    public int getNominator() {
        return nominator;
    }

    private void setNominator(int nominator) {
        this.nominator = nominator;
    }

    public int getWholePartOfFraction() {
        return nominator == 0 ? nominator : nominator / denominator;
    }

    private int getGreatestCommonDivisor() {
        if(nominator == 0 || denominator == 0)
            return 1;
        int tempNominator = nominator;
        int tempDenominator = denominator;
        while (tempNominator != 0 && tempDenominator != 0) {
            if (tempNominator > tempDenominator)
                tempNominator = tempNominator % tempDenominator;
            else
                tempDenominator = tempDenominator % tempNominator;
        }
        return tempNominator + tempDenominator;
    }

    public int getNominatorLong() {
        return getSymbolCount(nominator);
    }

    public int getDenominatorLong() {
        return getSymbolCount(denominator);
    }

    public int getNominatorWithoutWholePart() {
        return (nominator - getWholePartOfFraction() * denominator);
    }

    public int getSimplifiedNominator() {
        return (nominator - getWholePartOfFraction() * denominator) / getGreatestCommonDivisor();
    }

    public int getSimplifiedDenominator() {
        return denominator / getGreatestCommonDivisor();
    }

    public boolean isGreaterOrEqualsThan(Fraction fraction) {
        return nominator * fraction.denominator >= fraction.nominator * denominator;
    }

    public boolean isLessOrEqualsThan(Fraction fraction) {
        return nominator * fraction.denominator <= fraction.nominator * denominator;
    }

    public boolean isLessThan(Fraction fraction) {
        return nominator * fraction.denominator < fraction.nominator * denominator;
    }

    public boolean isGreaterThan(Fraction fraction) {
        return nominator * fraction.denominator > fraction.nominator * denominator;
    }

    public Fraction substract(Fraction fraction) {
        int resultNominator = nominator * fraction.denominator - fraction.nominator * denominator;
        int resultDenominator = denominator * fraction.denominator;
        return new Fraction(resultNominator, resultDenominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (denominator == fraction.denominator)
            return nominator == fraction.nominator;
        return nominator * fraction.denominator == fraction.nominator * denominator;
    }

    @Override
    public int hashCode() {
        int result = getSimplifiedNominator();
        result = 31 * result + getSimplifiedDenominator();
        return result;
    }

    public enum PrintMode {ONLY_FRACTION, WITH_WHOLE_PART, SIMPLIFIED_WITH_WHOLE_PART, IN_TEXT_PERFORMANCE}
}
