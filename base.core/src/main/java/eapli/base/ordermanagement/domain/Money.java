package eapli.base.ordermanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.FormattedString;
import eapli.framework.strings.XmlCurrencyAdapter;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.stream.Collector;

@Embeddable
public class Money implements Comparable<Money>, Serializable, ValueObject, FormattedString {
    private static final long serialVersionUID = 1L;
    private static final transient int HUNDRED = 100;
    @XmlAttribute
    @JsonProperty
    @Column(updatable = false, insertable = false)
    private final BigInteger amount;
    @XmlAttribute
    @XmlJavaTypeAdapter(XmlCurrencyAdapter.class)
    @JsonProperty
    @Column(updatable = false, insertable = false)
    private final Currency currency;

    protected Money() {
        this.amount = null;
        this.currency = null;
    }

    public Money(final double amount, final Currency currency) {
        this.amount = BigInteger.valueOf(Math.round(amount * 100.0D));
        this.currency = currency;
    }

    public Money(final long amount, final Currency currency) {
        this.amount = BigInteger.valueOf(amount * 100L);
        this.currency = currency;
    }

    public Money(final BigInteger amount, final String currency) {
        this.amount = amount == null ? BigInteger.ZERO : amount;
        this.currency = Currency.getInstance(currency);
    }

    private Money(final BigInteger amountInCents, final Currency currency) {
        assert amountInCents != null;

        assert currency != null;

        this.amount = amountInCents;
        this.currency = currency;
    }

    public static Money dollars(final double amount) {
        return new Money(amount, Currency.getInstance("USD"));
    }

    public static Money euros(final double amount) {
        return new Money(amount, Currency.getInstance("EUR"));
    }

    public static Money valueOf(final String value) {
        String[] parts = value.split(" ");
        Preconditions.areEqual((long)parts.length, 2L);
        double am = Double.parseDouble(parts[0]);
        Currency curr = Currency.getInstance(parts[1]);
        return new Money(am, curr);
    }

    public static Money valueOf(final double amount, final String currency) {
        Currency curr = Currency.getInstance(currency);
        return new Money(amount, curr);
    }

    public static Collector<Money, Money.MoneyCollector, Money> collector(final Money zero) {
        Preconditions.nonNull(zero);
        return Collector.of(() -> {
            return new Money.MoneyCollector(zero);
        }, (a, e) -> {
            a.add(e);
        }, (a1, a2) -> {
            return a1.combine(a2);
        }, (a) -> {
            return a.current;
        });
    }

    public double amountAsDouble() {
        return this.amount.doubleValue() / 100.0D;
    }

    public BigDecimal amount() {
        return (new BigDecimal(this.amount)).divide(BigDecimal.valueOf(100L));
    }

    public Currency currency() {
        return this.currency;
    }

    public Money add(final Money arg) {
        Preconditions.ensure(this.currency.equals(arg.currency), "Cannot add different currencies");
        return new Money(this.amount.add(arg.amount), this.currency);
    }

    public Money subtract(final Money arg) {
        return this.add(arg.negate());
    }

    public Money negate() {
        return new Money(this.amount.negate(), this.currency);
    }

    public Money multiply(final double arg) {
        return new Money(this.amountAsDouble() * arg, this.currency);
    }

    public Money[] divide(final int denominator) {
        BigInteger bigDenominator = BigInteger.valueOf((long)denominator);
        Money[] result = new Money[denominator];
        BigInteger simpleResult = this.amount.divide(bigDenominator);

        int remainder;
        for(remainder = 0; remainder < denominator; ++remainder) {
            result[remainder] = new Money(simpleResult, this.currency);
        }

        remainder = this.amount.subtract(simpleResult.multiply(bigDenominator)).intValue();

        for(int i = 0; i < remainder; ++i) {
            result[i] = result[i].add(new Money(BigInteger.valueOf(1L), this.currency));
        }

        return result;
    }

    public int compareTo(final Money arg) {
        Preconditions.areEqual(this.currency, arg.currency, "Cannot add different currencies");
        return this.amount.compareTo(arg.amount);
    }

    public boolean isGreaterThan(final Money arg) {
        return this.compareTo(arg) > 0;
    }

    public boolean isLessThan(final Money arg) {
        return this.compareTo(arg) < 0;
    }

    public boolean isGreaterThanOrEqual(final Money arg) {
        return this.compareTo(arg) >= 0;
    }

    public boolean isLessThanOrEqual(final Money arg) {
        return this.compareTo(arg) <= 0;
    }

    public boolean equals(final Object arg) {
        if (!(arg instanceof Money)) {
            return false;
        } else {
            Money other = (Money)arg;
            return this.currency.equals(other.currency) && this.amount.equals(other.amount);
        }
    }

    public int hashCode() {
        int result = 11;
        result = 37 * result + this.amount.hashCode();
        //int result = 37 * result + this.amount.hashCode();
        result = 37 * result + this.currency.hashCode();
        return result;
    }

    public int signum() {
        return this.amount.signum();
    }

    /*@JsonIgnore
    public boolean isNegative() {
        return this.signum() == -1;
    }

    @JsonIgnore
    public boolean isPositive() {
        return this.signum() == 1;
    }

    @JsonIgnore
    public boolean isZero() {
        return this.signum() == 0;
    }*/

    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setCurrency(this.currency);
        nf.setGroupingUsed(true);
        nf.setMaximumFractionDigits(this.currency.getDefaultFractionDigits());
        return nf.format(this.amount().doubleValue());
    }

    public String toSimpleString() {
        double var10000 = this.amountAsDouble();
        return var10000 + " " + this.currency();
    }

    private static class MoneyCollector {
        private Money current;

        public MoneyCollector(final Money zero) {
            this.current = zero;
        }

        public void add(final Money b) {
            this.current = this.current.add(b);
        }

        public Money.MoneyCollector combine(final Money.MoneyCollector other) {
            this.current = this.current.add(other.current);
            return this;
        }
    }
}
