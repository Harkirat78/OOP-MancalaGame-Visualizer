package mancala;

import java.io.Serializable;

public class PitNotFoundException extends Exception implements Serializable {

    // Added serialVersionUID to address the PMD error
    private static final long serialVersionUID = 1L;

    public PitNotFoundException() {
        super("Pit not found");
    }

    // Parameter 'message' is now declared final
    public PitNotFoundException(final String message) {
        super(message);
    }
}
