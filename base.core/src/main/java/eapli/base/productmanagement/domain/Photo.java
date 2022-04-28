package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import java.io.Serializable;
import java.util.regex.Pattern;

//photo: it might be of any common format (e.g. png, jpeg, svg);
//"From a usability perspective, it would be better having a window (or any other way) to
// select the photo file to be uploaded. However, if by some reason that option is not viable
// by now the user can write the path but the system must validate it."
public class Photo implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    //private static final Pattern VALID_PHOTO_REGEX = Pattern.compile("^(?:[\\w]\\:|\\\\)(\\\\[a-z_\\-\\s0-9\\.]+)+\\.(png|jpg|svg|jpeg|PNG|JPG|SVG|JPEG)$");

    private final String value;

    public Photo(final String value){
        Preconditions.nonEmpty(value, "Photo should neither be null nor empty");
        //Preconditions.matches(VALID_PHOTO_REGEX, value, "The Photo does not follow the required format.");
        this.value=value;
    }

    protected Photo(){
        this.value="";
    }

    public static Photo valueOf(final String value){
        return new Photo(value);
    }

    public String photo(){
        return this.value;
    }

    public boolean equals(final Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof Photo)){
            return false;
        } else {
            Photo photo = (Photo) o;
            return this.value.equals(photo.value);
        }
    }

    public int hashCode(){
        HashCoder coder = (new HashCoder()).with(this.value);
        return coder.code();
    }

    public String toString(){
        return this.value;
    }

    public int compareTo(final Photo o){
        return this.value.compareTo(o.value);
    }
}
