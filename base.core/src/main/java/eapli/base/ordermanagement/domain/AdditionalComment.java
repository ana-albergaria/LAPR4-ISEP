package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AdditionalComment implements ValueObject, Serializable {

    private final String additionalComment;

    public AdditionalComment(final String additionalComment) {
        Preconditions.nonEmpty(additionalComment, "Additional Comment should neither be null nor empty");
        this.additionalComment = additionalComment;
    }

    protected AdditionalComment() {
        this.additionalComment = null;
        //for ORM purposes
    }

}
