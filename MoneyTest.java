package pl.com.bottega.photostock.sales.model.deal;

import org.junit.Assert;
import org.junit.Test;
import pl.com.bottega.commons.math.model.Fraction;

import static pl.com.bottega.photostock.sales.model.deal.Money.Currency.PLN;
import static pl.com.bottega.photostock.sales.model.deal.Money.Currency.USD;

/**
 * lublin-3-16-photostock
 * Sergii
 * 2016-05-04.
 */

public class MoneyTest {

    @Test
    public void shouldCreateMoney() {
        boolean chek = true;
        try {
            Money money = new Money(140.56605, PLN);
            Money money2 = new Money(10, PLN);
            Money money3 = new Money(150, 99, PLN);
            Money money4 = new Money(new Fraction(20, 50), PLN);
        } catch (IllegalArgumentException ex) {
            chek = false;
        }
        Assert.assertTrue(chek);
    }

    @Test
    public void shouldCreateMoneyWithFullCents() {
        Assert.assertEquals(new Money(150, 100, PLN), new Money(151, PLN));
    }


    @Test
    public void shouldCompareMoney() {
        Money money = new Money(140.56605, PLN);
        Money money2 = new Money(140.56605, PLN);
        Assert.assertEquals(money, money2);
    }

    @Test
    public void shouldGetDifferentMoney() {
        Money money = new Money(100, PLN);
        Money money2 = new Money(102, PLN);
        double result = money.getPercentDifferent(money2);
        Assert.assertEquals(2.0, result, 0);
    }

    @Test
    public void shouldCompareGraterOfEqualsMoney() {
        Money money = new Money(140.56700, PLN);
        Money money2 = new Money(140.56605, PLN);
        Assert.assertTrue(money.isGreaterOrEqualsThan(money2));
    }

    @Test
    public void shouldCompareGraterMoney() {
        Money money = new Money(140.56700, PLN);
        Money money2 = new Money(140.56605, PLN);
        Assert.assertTrue(money.isGreaterThan(money2));
    }

    @Test
    public void shouldCompareLessOrEqualsMoney() {
        Money money = new Money(140.56700, PLN);
        Money money2 = new Money(140.56605, PLN);
        Assert.assertTrue(money2.isLessOrEqualsThan(money));
    }

    @Test
    public void shouldCompareLessMoney() {
        Money money = new Money(140.56700, PLN);
        Money money2 = new Money(140.56605, PLN);
        Assert.assertTrue(money2.isLessThan(money));
    }

    @Test
    public void shouldCompareHashCode() {
        Money money = new Money(140.56605, PLN);
        Money money2 = new Money(140.56605, PLN);
        Assert.assertEquals(money.hashCode(), money2.hashCode());
    }

    @Test
    public void shouldAddMoney() {
        Money money = new Money(120.566, PLN);
        Money money2 = new Money(140.56605, PLN);
        Money money3 = new Money(261.13205, PLN);
        Assert.assertEquals(money2.add(money), money3);
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        boolean result = false;
        try {
            Money money = new Money(-10, PLN);
        } catch (IllegalArgumentException iae) {
            result = true;
        }
        Assert.assertTrue(result);
    }

    @Test
    public void shouldThrowIllegalArgumentException2() {
        boolean result = false;
        try {
            Money money = new Money(25, 101, PLN);
        } catch (IllegalArgumentException iae) {
            result = true;
        }
        Assert.assertTrue(result);
    }

    @Test
    public void shouldThrowIllegalArgumentException3() {
        boolean result = false;
        try {
            Money money = new Money(25, 101, null);
        } catch (IllegalArgumentException iae) {
            result = true;
        }
        Assert.assertTrue(result);
    }

    @Test
    public void shouldThrowIllegalArgumentException4() {
        boolean result = false;
        try {
            Money moneyPLN = new Money(25, 25, PLN);
            Money moneyUSD = new Money(25, 25, USD);
            Money money = moneyPLN.add(moneyUSD);
        } catch (IllegalArgumentException iae) {
            result = true;
        }
        Assert.assertTrue(result);
    }

    @Test
    public void shouldSubstractMoney() {
        Money money = new Money(120.566, PLN);
        Money money2 = new Money(140.56605, PLN);
        Money money3 = new Money(20.00005, PLN);
        Assert.assertEquals(money2.substract(money), money3);
    }

    @Test
    public void shouldMultipleMoney() {
        Money money = new Money(10, PLN);
        Money money3 = new Money(20, PLN);
        Assert.assertEquals(money.multiple(2), money3);
    }

    @Test
    public void shouldMultipleMoneyDouble() {
        Money money = new Money(120.566, PLN);
        Money money3 = new Money(1507.075, PLN);
        Assert.assertEquals(money.multiple(12.5), money3);
    }

    @Test
    public void shouldConvertMoneyToCents() {
        Money money = new Money(26, 5, USD);
        int cents = money.getCents();
        Assert.assertEquals(new Money(cents / 100D, USD), new Money(26.05, USD));
    }


}