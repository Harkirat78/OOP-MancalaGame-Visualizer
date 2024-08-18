package mancala;

import java.io.Serializable;

public class NoSuchPlayerException extends Exception implements Serializable {

    // Added serialVersionUID to address the PMD error
    private static final long serialVersionUID = 1L;

    public NoSuchPlayerException() {
        super("Player not found");
    }

    // Parameter 'message' is now declared final
    public NoSuchPlayerException(final String message) {
        super(message);
    }
}
