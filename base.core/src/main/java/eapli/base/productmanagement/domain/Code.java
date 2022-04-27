package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

//internal code: not empty alphanumeric code with at 23 chars maximum however it might meet a given regular expression defined/configured at deployment time.
//"For example, 4 letters followed by a dot (".") and ending with 5 digits."
public class Code implements ValueObject, Serializable, Comparable<Code> {
    private static final long serialVersionUID = 1L;

    public static Code valueOf(String uniqueInternalCode) {
        throw new IllegalStateException("to develop");
    }

    @Override
    public int compareTo(Code o) {
        throw new IllegalStateException("to develop");
    }
}
