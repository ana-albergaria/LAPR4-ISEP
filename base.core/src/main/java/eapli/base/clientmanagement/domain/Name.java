package eapli.base.clientmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.regex.Pattern;

@Embeddable
public class Name implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Pattern VALID_NAME_REGEX = Pattern.compile("^[A-Z][a-zA-Z ',.\\-]*$", 2);
    private static final Integer SURNAMES_VALID_LENGTH = 50;
    private final String firstNames;
    private final String surnames;

    protected Name(final String firstNames, final String surnames) {
        Preconditions.nonEmpty(firstNames);
        Preconditions.nonEmpty(surnames, "First name and last name should neither be null nor empty");
        Preconditions.matches(VALID_NAME_REGEX, firstNames, "Invalid First Name(s): " + firstNames);
        Preconditions.matches(VALID_NAME_REGEX, surnames, "Invalid Surname(s): " + surnames);
        if(surnames.length() < SURNAMES_VALID_LENGTH)
            throw new IllegalArgumentException("Surname(s) are too short.");
        this.firstNames = firstNames;
        this.surnames = surnames;
    }

    protected Name() {
        this.firstNames = this.surnames = "";
    }

    public static Name valueOf(final String firstName, final String lastName) {
        return new Name(firstName, lastName);
    }

    public String firstName() {
        return this.firstNames;
    }

    public String lastName() {
        return this.surnames;
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Name)) {
            return false;
        } else {
            Name name = (Name)o;
            return !this.firstNames.equals(name.firstNames) ? false : this.surnames.equals(name.surnames);
        }
    }

    public int hashCode() {
        HashCoder coder = (new HashCoder()).with(this.firstNames).with(this.surnames);
        return coder.code();
    }

    public String toString() {
        return this.firstNames + " " + this.surnames;
    }
}
