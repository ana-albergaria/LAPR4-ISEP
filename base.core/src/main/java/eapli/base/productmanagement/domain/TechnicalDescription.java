package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import java.io.Serializable;

//technical description: multiple lines of text, preferably with no limit or the biggest possible.
public class TechnicalDescription implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private final String value;

    public TechnicalDescription(final String value){
        Preconditions.nonEmpty(value, "Reference should not be empty");
        this.value=value;
    }

    protected TechnicalDescription(){
        this.value="";
    }

    public static TechnicalDescription valueOf(final String value){
        return new TechnicalDescription(value);
    }

    public String technicalDescription(){
        return this.value;
    }

    public boolean equals(final Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof TechnicalDescription)){
            return false;
        } else {
            TechnicalDescription technicalDescription = (TechnicalDescription) o;
            return !this.value.equals(technicalDescription.value);
        }
    }

    public int hashCode(){
        HashCoder coder = (new HashCoder()).with(this.value);
        return coder.code();
    }

    public String toString(){
        return this.value;
    }
}
