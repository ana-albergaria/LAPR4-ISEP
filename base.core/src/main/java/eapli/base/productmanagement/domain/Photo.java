package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import java.io.Serializable;

//photo: it might be of any common format (e.g. png, jpeg, svg);
//"From a usability perspective, it would be better having a window (or any other way) to
// select the photo file to be uploaded. However, if by some reason that option is not viable
// by now the user can write the path but the system must validate it."
public class Photo implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    public static String valueOf(String photo) {
        throw new IllegalStateException("to develop");
    }

    public String name() {
        throw new IllegalStateException("to develop");
    }

    public String path() {
        throw new IllegalStateException("to develop");
    }
}
